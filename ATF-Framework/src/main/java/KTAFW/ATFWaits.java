	package KTAFW;
	
	import java.util.concurrent.TimeUnit;
	
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.NoSuchElementException;
	import org.openqa.selenium.StaleElementReferenceException;
	import org.openqa.selenium.TimeoutException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedCondition;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.FluentWait;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	
	import com.google.common.base.Function;
	
	import KTAFW.ATFVerify;
	
	public class ATFWaits {
	
		public WebDriverWait wait;
		public ATFActions atfAction;
		public WebDriver myDriver;
		public Config config;
		public ATFVerify atfVerify;
	
		public ATFWaits(Driver driver){
			myDriver = driver.getWebDriver();
			atfAction = new ATFActions(driver);
			config = new Config();
			wait = driver.getWebDrivertWait();
		}
	
		public enum waitConditionsUsingBy{
			ElementToBeClickable,
			ElementToBeSelected,
			VisibilityOfElementLocated,
			InvisibilityOfElementLocated,
			presenceOfElementLocated,
			frameToBeAvailableAndSwitchToIt
		}
	
		//Reusable method to use explicit wait with By class
		public void customExplicitWait(waitConditionsUsingBy condition, By locator, int waitTimeInSec){
	
			WebDriverWait wait = new WebDriverWait(myDriver, waitTimeInSec);
	
			switch(condition){
			case ElementToBeClickable:
				wait.until(ExpectedConditions.elementToBeClickable(locator));
				break;
			case ElementToBeSelected:
				wait.until(ExpectedConditions.elementToBeSelected(locator));
				break;
			case VisibilityOfElementLocated:
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				break;
			case InvisibilityOfElementLocated:
				waitForElementNotVisible(locator, waitTimeInSec);
				break;
			case presenceOfElementLocated:
				wait.until(ExpectedConditions.presenceOfElementLocated(locator));				
				break;
			case frameToBeAvailableAndSwitchToIt:
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
				break;
			}
		}
	
		public void waitForElementNotVisible(By locator, int waitTimeInSec){	
			resetImplicitWait();
	
			try {
				int waitTimeCount = 0;
	
				while(atfVerify.isElementDisplayed(locator)){
					Thread.sleep(1000);
					waitTimeCount++;
	
					if(waitTimeCount == waitTimeInSec){
						Assert.fail("The Element {" + locator.toString() + "} does not disappear with given wait time of " + waitTimeInSec + " secs");
					}				
				}
				setImplicitWait();
			}
			catch(Exception e) {
				setImplicitWait();
				Assert.fail("Exception occured on waitForElementNotVisible for the element {"+ locator.toString() +"} and the exception is " + e.getMessage());
			}
		}
	
		public void setImplicitWait() {
			myDriver.manage().timeouts().implicitlyWait(config.getImplicitWaitTime(), TimeUnit.SECONDS);
		}
	
		public void resetImplicitWait() {
			myDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		}
	
		public void forBrowserLoad() throws InterruptedException  {   		      
			forBrowserLoad(config.getpageLoadTimeout());
		}
		
		//Reusable method to make the driver to wait until the document loads completely within given maximum wait time
		public void forBrowserLoad(int maxWaitTimeInSec) throws InterruptedException {		
			forGlobalAjaxLoader();
			ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
				}
			};
	
			try {
				WebDriverWait wait = new WebDriverWait(myDriver, maxWaitTimeInSec);
				wait.until(expectation);
			} catch (Throwable error) {
				Assert.fail("Timeout waiting for Page Load Request to complete.");
			}
		}	
	
		public void forGlobalAjaxLoader() {
			forGlobalAjaxLoader(config.getpageLoadTimeout());
		}
	
		public void forGlobalAjaxLoader(int maxWaitTimeInSec) {
			try {
				//Added the Thread.sleep to handle the wait time in billing manager's ready dropdown, 
				//Since it is not showing any ajax or page load after clicking the ready button.
				Thread.sleep(2000);	

				//If the below 'by' present in DOM which means Ajax loading is completed
				By byAjax = By.xpath(".//*[@id='divLoaderImage' and contains(@style,'display: none')]");

				//Common 'By' for AjaxLoader
				By by = By.xpath(".//*[@id='divLoaderImage']");

				if(atfVerify.isAttributePresent(by, "style") && atfVerify.isAttributeHasValue(by, "style", "display: block;")){			
					WebDriverWait wait = new WebDriverWait(myDriver, maxWaitTimeInSec);
					wait.until(ExpectedConditions.presenceOfElementLocated(byAjax));	 
				}

			} catch (Exception e) {
				//Do nothing
			}
		}
	
		public void forPageWait() {
			myDriver.manage().timeouts().pageLoadTimeout(config.getpageLoadTimeout(), TimeUnit.SECONDS);
		}		 
		
		public void setImplicitWait(int timeUnitInSec) {
			myDriver.manage().timeouts().implicitlyWait(timeUnitInSec, TimeUnit.SECONDS);
		}
		
		/**
		 * Method to wait for element until its visible and clickable
		 * @param WebElement
		 */
		public void waitForElementToBeEnabled(By locator)
		{
			try
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				wait.until(ExpectedConditions.elementToBeClickable(locator));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Assert.fail("Error in waiting for element "+locator);
			}
		}
	
		/**
		 * Method to wait until web element is disappeared on DOM
		 * @param WebElement
		 */
		public void waitForElementInvisible(By locator) 
		{
			try
			{
				wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Assert.fail("Error waiting for element to be invisible "+locator);
			}

		}

		public void waitForBrowserLoad(){
			try
			{
				JavascriptExecutor js;
				String pageLoadStatus = null;
				do {
					js = (JavascriptExecutor) myDriver;
					pageLoadStatus = (String) js.executeScript("return document.readyState");
					Thread.sleep(2000);
				} while (!pageLoadStatus.equals("complete"));
			}
			catch(Exception e){
				e.printStackTrace();
				Assert.fail("Error in waiting for the browser to load");
			}

		}

		public void waitForGlobalAjaxLoad(){
			try {
				WebElement element = myDriver.findElement(By.id("globalAjaxLoader"));
				do 
				{
					Thread.sleep(1000);
				} while (!element.getAttribute("style").trim().equals("display: none;"));
			} 
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Error waiting for Ajax content to load: "+e.getMessage());
			}
		}

		int time = 2;
		/**
		 * Fluent wait for WebElement to be enabled
		 * polls page every second for time set in minutes(timeout)
		 * @param element(getter)
		 * @param timeout
		 */
		public boolean fluentWaitIsEnabled(By locator) {	
			try {
				new FluentWait<WebDriver>(myDriver)
				.withTimeout(time, TimeUnit.MINUTES)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.until(new Function<WebDriver, Boolean>() {
					public Boolean apply(WebDriver d) {
						return myDriver.findElement(locator).isDisplayed();
					}
				});
			} catch (TimeoutException te) {
				te.printStackTrace();
				Assert.fail("Unalbe to load element :"+locator +" in -"+time +" minutes");
				return false;
			}
			return false;
		}


		/**
		 * Fluent wait for WebElement to be disabled
		 * polls page every second for time set in minutes(timeout)
		 * @param element(getter)
		 * @param timeout
		 */
		public void fluentWaitIsNotEnabled(By locator){
			try {
				new FluentWait<WebDriver>(myDriver)
				.withTimeout(time, TimeUnit.MINUTES)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.until(new Function<WebDriver, Boolean>() {
					public Boolean apply(WebDriver d) {
						return !(myDriver.findElement(locator).isDisplayed());
					}
				});
			} catch (TimeoutException te) {
				System.out.println(te);
				return;
			}
		}

		/**
		 * Fluent wait for WebElement to be displayed
		 * polls page every second for time set in minutes(timeout)
		 * @param element(getter)
		 * @param timeout
		 */
		public boolean fluentWaitIsDisplayed(By locator) {
			try {
				new FluentWait<WebDriver>(myDriver)
				.withTimeout(time, TimeUnit.MINUTES)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.until(new Function<WebDriver, Boolean>() {
					public Boolean apply(WebDriver d) {
						return myDriver.findElement(locator).isDisplayed();
					}
				});
			} catch (TimeoutException te) {
				System.out.println(te);
				return false;
			}
			return false;
		}


		/**
		 * @param myDriver
		 * @param element
		 * @param timeout
		 */
		public void fluentWaitIsNotDisplayed(By locator) {
			try {
				new FluentWait<WebDriver>(myDriver)
				.withTimeout(time, TimeUnit.MINUTES)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.until(new Function<WebDriver, Boolean>() {
					public Boolean apply(WebDriver d) {
						return myDriver.findElement(locator).isDisplayed();
					}
				});
			} catch (TimeoutException te) {
				System.out.println(te);
				return;
			}
		}


		/**
		 * FluentWait to wait for element to be displayed.
		 * polls page every second for time set in minutes(timeout)
		 * @throws Exception
		 */
		public void fluentWaitTextIsDisplayed(By locator, final String text) {
			try {
				new FluentWait<WebDriver>(myDriver)
				.withTimeout(time, TimeUnit.MINUTES)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.until(new Function<WebDriver, Boolean>() {
					public Boolean apply(WebDriver d) {
						ExpectedConditions.textToBe(locator, text);
						return myDriver.findElement(locator).isDisplayed();
					}
				});
			} catch (TimeoutException te) {
				System.out.println(te);
				return;
			}
		}
		
		public void waitForElementforTesting(By locator)
		{
			try
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				wait.until(ExpectedConditions.elementToBeClickable(locator));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Assert.fail("Error in waiting for element "+locator);
			}
		}
	}

package KTAFW;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import DataSource.GlobalData;

public class Driver {
		
	protected WebDriver myDriver;
	protected WebDriverWait wait;
	protected WebElement element;
	protected List<WebElement> elements;
	protected Actions action;
	protected JavascriptExecutor js;
	protected Config config = new Config();
	
	public Driver(Config config) 
	{
		this.config = config;
		this.myDriver = getWebDriver();
	}

	/**
	 * Method to launch browser and return driver object
	 * @return driver
	 */
	public WebDriver getWebDriver() {
		try {
			if (myDriver == null) {
				switch (config.getBrowserType().toLowerCase()) 
				{
					case "firefox":
						System.setProperty("webdriver.gecko.driver", config.getDriversPath() + "geckodriver.exe");
						myDriver = new FirefoxDriver();
						break;
	
					case "chrome":
						System.setProperty("webdriver.chrome.driver", config.getDriversPath() + "chromedriver.exe");
						myDriver = new ChromeDriver();
						break;
	
					case "ie":
						System.setProperty("webdriver.ie.driver", config.getDriversPath() + "iedriver.exe");
						myDriver = new InternetExplorerDriver();
						break;
					case "android":
						
					case "ios":
						
					case "safari":
					
					case "grid":
					
					default:
						Assert.fail("No Driver selected, please configure your driverType in config file");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error when creating a driver");
		}
		return myDriver;
	}

	/**
	 * Method to get WebDriverWait object
	 * @return WebDriverWait
	 */
	public WebDriverWait getWebDrivertWait() {
		return wait = new WebDriverWait(myDriver, config.getExplicitWait());
	}

	/**
	 * Method to get Implicit wait
	 * @return wait time
	 */
	public Timeouts getImplicitWait() {
		return myDriver.manage().timeouts().implicitlyWait(config.getImplicitWait(), TimeUnit.SECONDS);
	}

	/**
	 * Method to find element using its locator and retry the same on exception
	 * @param element locator
	 * @return element locator
	 */
	public By findElement(By locator) {
		int retrycount = 0;
		try {
			myDriver.findElement(locator);
		} catch (Exception e) {
			while (retrycount != config.getRetryCount()) {
				retrycount++;
				try {
					element = (new WebDriverWait(myDriver, config.getExplicitWait()))
							.until(ExpectedConditions.visibilityOfElementLocated(locator));
					if (element != null) {
						return locator;
					}
				} catch (org.openqa.selenium.TimeoutException ez) {
					continue;
				}
			}
		}
		if (locator != null) {
			return locator;
		}
		Assert.fail("Element not found"+locator);
		return null;
	}

	/**
	 * Method to find elements using locator and retry the same on exception
	 * @param element locator
	 * @return element locator
	 */
	public List<WebElement> findElements(By locator) {
		int retrycount = 0;
		List<WebElement> element = null;
		try {
			elements = myDriver.findElements(locator);
		} catch (Exception e) {
			while (retrycount != config.getRetryCount()) {
				retrycount++;
				try {
					(new WebDriverWait(myDriver, config.getExplicitWait()))
							.until(ExpectedConditions.visibilityOfElementLocated(locator));
					if (element != null) {
						return elements;
					}
				} catch (org.openqa.selenium.TimeoutException ez) {
					continue;
				}
			}
		}
		if (elements != null) {
			return elements;
		}
		Assert.fail("Element not found"+locator);
		return null;
	}

	/**
	 * Method to open a desired URL
	 * 
	 * @param url
	 */
	public void openURL(String url) {
		getImplicitWait();
		maximize();
		myDriver.navigate().to(url);
	}

	/**
	 * Method to maximize browser window
	 */
	public void maximize() {
		myDriver.manage().window().maximize();
	}

	/**
	 * Method to get page source as string
	 * 
	 * @return Page Source
	 */
	public String getPageSource() {
		return myDriver.getPageSource();
	}

	/**
	 * Method to get page title as string
	 * 
	 * @return page title
	 */
	public String getTitle() {
		return myDriver.getTitle();
	}

	/**
	 * Method to get window handle
	 * 
	 * @return window handle
	 */
	public String getWindowHandle() {
		return myDriver.getWindowHandle();
	}

	/**
	 * Method to get Action Class object
	 * 
	 * @return action class reference
	 */
	public Actions getActions() {
		return action = new Actions(myDriver);
	}
	
	public JavascriptExecutor getJSExecutor()
	{
		return js = (JavascriptExecutor) myDriver;
	}
	
	/**
	 * Method to switch to active alert
	 */
	public void swithToAlert() {
		myDriver.switchTo().alert();
	}

	/**
	 * Method to accept alert
	 */
	public void acceptAlert() {
		myDriver.switchTo().alert().accept();
	}

	/**
	 * Method to dismiss alert
	 */
	public void dismissAlert() {
		myDriver.switchTo().alert().dismiss();
	}

	/**
	 * Method to get alert text as string
	 * 
	 * @return alert text
	 */
	public String getAlertText() {
		return myDriver.switchTo().alert().getText();
	}

	/**
	 * Method to switch to active Element
	 */
	public void switchToActiveElement() {
		myDriver.switchTo().activeElement();
	}

	public void closeDriver(WebDriver driver) {
		driver.close();
	}

	public void teardownTest() {
		GlobalData.resetGlobalData();
		if (myDriver != null){
			myDriver.quit();
		}
	}

}

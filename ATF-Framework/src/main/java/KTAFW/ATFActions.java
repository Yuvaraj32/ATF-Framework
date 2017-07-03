package KTAFW;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;


/**
 * Class for all the generic driver actions
 * @author sadiqs
 */
public class ATFActions {
	
	public ATFActions(Driver driver) {
		this.driver=driver;
		this.myDriver = driver.getWebDriver();
		actions = driver.getActions();
	}

	Alert alert;
	Actions actions;
	JavascriptExecutor js;
	boolean flag = false;
	public WebDriver myDriver;
	public Driver driver;
	

	/**
	 * Method to check if the element is displayed and enabled on any exception retry looking for element else fails
	 * @param locator
	 * @return
	 */
	public By isReady(By locator) {
		try {
			if (isDisplayed(locator) || isEnabled(locator)) {
				return locator;
			} else {
				Assert.fail("Element " + locator.toString() + " identified but it is not visible or enabled");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Element " + locator.toString() + " is not visiable or enabled, Going to retry");
			return retry(locator);
		}
	}

	/**
	 * Method to find if the element is displayed in the DOM
	 * @param locator
	 * @return true if the element is enabled else false
	 */
	public boolean isDisplayed(By locator) {
		try {
			flag = myDriver.findElement(locator).isDisplayed();
			return flag;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to find if the element is enabled in the DOM
	 * @param locator
	 * @return true if the element is enabled else false
	 */
	public boolean isEnabled(By locator) {
		try {
			flag = myDriver.findElement(locator).isEnabled();
			return flag;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to find of the element is selected or not
	 * @param locator
	 * @return true if the element is selected else false
	 */
	public boolean isSelected(By locator) {
		try {
			flag = myDriver.findElement(locator).isSelected();
			return flag;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to retry finding an element in DOM
	 * @param locator
	 * @return WebElement to retry
	 */
	public By retry(By locator) {
		By element = driver.findElement(locator);
		//By element = myDriver.findElement(locator);
		if (element != null && locator != null) {
			return locator;
		}
		return null;
	}

	/**
	 * Method to scroll to specific web-element position
	 * @param locator
	 */
	public void scrollToElement(By locator) {
			if ((isReady(locator)) != null) {
			try {
				actions.moveToElement(myDriver.findElement(locator));
				actions.perform();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Error scrolling to desired element " + locator);
			}
		}
	}

	/**
	 * Method to perform click operation
	 * @param locator
	 */
	public void click(By locator) {
		if ((isReady(locator)) != null) {
			try {
				scrollToElement(locator);
				myDriver.findElement(locator).click();
				
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Exception in clicking element: " + locator);
			}
		}
	}
	
	/**
	 * Method to perform click operation
	 * @param locator
	 */
	public void clear(By locator) {
		if ((isReady(locator)) != null) {
			try {
				myDriver.findElement(locator).clear();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Exception in clicking element: " + locator);
			}
		}
	}
	
	/**
	 * Method to perform click operation
	 * @param locator
	 */
	public void sendKeys(By locator, String value) {
		if ((isReady(locator)) != null) {
			try {
				scrollToElement(locator);
				clear(locator);
				myDriver.findElement(locator).sendKeys(value);
			} catch (Exception e) {
				e.printStackTrace();;
				Assert.fail("Exception in entering text to: " + locator);
			}
		}
	}
	
	/**
	 * Method to get the number of rows present in the table
	 * @param tableName
	 * @return row count
	 */
	public int getRowSize(By locator)
	{
		int rowsCount = 0;
		if ((isReady(locator))!=null)
		{
			Reporter.log("Getting rows count of table "+locator);
			try
			{
				List<WebElement> rows_table = myDriver.findElements(By.tagName("tr"));
				rowsCount = rows_table.size();
				return rowsCount;
			}
			
			catch (Exception e)
			{
				e.printStackTrace();
				Assert.fail("Excpetion in finding table: " +locator);
				return 0;
			}
		}
		else
			return 0;
	}
	
	/**
	 * Method to get reference to all rows in the table
	 * @param element
	 * @return List of rows in the table as web element
	 */
	public List<WebElement> getTablerows(By locator)
	{
		List<WebElement> tableRows = null;
		if ((isReady(locator))!=null)
		{
			try
			{
				tableRows = myDriver.findElements(By.tagName("tr"));
				return tableRows;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Assert.fail("Exception in finding table: "+locator);
				return null;
			}
		}
		else 
			return null;
	}
	
	/**
	 * Method to select from drop down using matching text
	 * @param WebElement
	 * @param Matching Text
	 */
	public void selectByVisibleText(By locator, String text) 
	{
		if ((isReady(locator))!=null)
		{
			try
			{
				scrollToElement(locator);
				Select sel = new Select(myDriver.findElement(locator));
				sel.selectByVisibleText(text);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Assert.fail("Error in selecting from dropdown "+locator);
			}
		}
	}
	
	/**
	 * Method to select from drop down using value
	 * @param WebElement
	 * @param Matching Text
	 */
	public void selectByValue(By locator, String value) 
	{
		if ((isReady(locator))!=null)
		{
			try
			{
				scrollToElement(locator);
				Select sel = new Select(myDriver.findElement(locator));
				sel.selectByValue(value);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Assert.fail("Error in selecting from dropdown "+locator);
			}
		}
	}
	
	/**
	 * Method to check if the element is displayed
	 * @param WebElement
	 * @return true if the element is displayed else return false
	 */
	public boolean isElementPresent(By locator)
	{
	     try
	        {
	        	return flag = myDriver.findElement(locator).isDisplayed();
	        }
	        catch(NoSuchElementException e)
	     	{
	        	Assert.fail("The element "+locator +" is not displayed yet...");
	        	return false;
	        }
    }
	

	
	/**
	 * Method to return the text of the element
	 * @param locator
	 * @return elements text as string
	 */
	public String getText(By locator)
	{
		if((isReady(locator))!=null)
		{
			try
			{
				return myDriver.findElement(locator).getText();
 			}
			catch (Exception e)
			{
				  e.printStackTrace();
				  Assert.fail("Error fetching text of element " +locator);
				  return null;
			}
		}
		Assert.fail("Unable to get text of the element"+locator);
		return null;
	}
	
	public void acceptAlert(){
		myDriver.switchTo().alert().accept();
	}
	
	/**
	 * Method to Switch to Active Window
	 */
	public void switchToActiveWindow()
	{
		myDriver.switchTo().activeElement();
		System.out.println("Successfully Navigated to active Window");
	}
	
	/**
	 * Method to switch to desired frame
	 * @param element
	 */
	public void switctoIFrame(By locator)
	{
		if((isReady(locator))!=null)
		{
			try
			{
				myDriver.switchTo().frame(myDriver.findElement(locator));
 			}
			catch (Exception e)
			{
				  e.printStackTrace();
				  Assert.fail("Error fetching text of element " +locator);
			}
		}
	}
	
	/**
	 * Method to pass desired Keboard key
	 * @param key
	 */
	public void keyboardActions(Keys key) 
	{
		actions.sendKeys(key).build().perform();
	}
	
	/**
	 * Method to Enter date using Event Fire
	 */
	public void enterDate(By locator, String date){
		if((isReady(locator))!=null)
		{
			try
			{
				actions.moveToElement(myDriver.findElement(locator)).click().perform();
				clear(locator);
				sendKeys(locator, date);
 			}
			catch (Exception e)
			{
				  e.printStackTrace();
				  Assert.fail("Error fetching text of element " +locator);
			}
		}
	}
	
	/**
	 * Method to select date by date picker
	 */
	public void selectCustomDateInPicker(By locator, String date){
		try
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy");
			LocalDate localDate = LocalDate.parse(date , formatter);
			
			String year = (""+localDate.getYear()).trim();
			String month = (""+localDate.getMonthValue()).trim();
			String day = (""+localDate.getDayOfMonth()).trim();
			
			By lst_month = By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]");
			By lst_year = By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]");
			By lnk_day = By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[text()='"+day+"']");
			
			System.out.println("Year -->"+year+" Month--->"+month+" Day--->"+day);
			if(isReady(locator)!=null)
			{
				//Check for waits if required
				click(locator);
				selectByVisibleText(lst_month, month);
				selectByVisibleText(lst_year, year);
				click(lnk_day);
			}
		}
		catch(DateTimeException de)
		{
			de.printStackTrace();
			Assert.fail("Error in parsing the date"+date);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Error when selecting date"+locator);
		}
		
	}
	
	/**
	 * Method to select today or highlighted default date in picker
	 * @param locator
	 */
	public void selectCurrentDateInPicker(By locator){
		By selectedDate = By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr/td/a[contains(@class, 'highlight')]");
		if(isReady(locator)!=null)
		{
			try
			{
				click(locator);
				//Add wait if required
				click(selectedDate);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Assert.fail("Error when selecting current date "+myDriver.findElement(selectedDate).getText()+" from angular date picker"+locator);
			}
		}
	}
	
}

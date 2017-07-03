package KTAFW;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import KTAFW.ATFActions;
import KTAFW.ATFWaits;
import KTAFW.Driver;

public class ATFVerify {
	
	public Driver driver;
	public ATFActions atfActions;
	public ATFWaits atfWaits;
	public WebDriver myDriver;
	
	public ATFVerify(Driver driver) {
		this.myDriver = driver.getWebDriver();
		atfWaits = new ATFWaits(driver);
		atfActions = new ATFActions(driver);
	}
	
	By alertMessage = By.xpath("//*[@id='alert']/span");
	By pageHeader = By.xpath("//h1[not(parent::div/parent::div[@id='releaseNoteContainer'])]");

	// Application Generic MEthods
	/**
	 * Method to verify if the entire page title matches with the provided string
	 * 
	 * @param pageTitle
	 */
	public void exactPageTitle(String pageTitle) {
		try {
			atfWaits.waitForBrowserLoad();
			String title = myDriver.getTitle();
			if (title.trim().equals(pageTitle)) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("The page title displayed is - '" + title + "' NOT '" + pageTitle + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error when comparing page title: " + pageTitle);
		}

	}

	/**
	 * Method to verify if the provide string is avaialble in the page title
	 * @param pageTitle
	 */
	public void partialPageTitle(String pageTitle) {
		try {
			atfWaits.waitForBrowserLoad();
			String title = myDriver.getTitle();
			if (title.trim().contains(pageTitle)) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("The page title displayed is - '" + title + "' NOT '" + pageTitle + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error when comparing page title: " + pageTitle);
		}

	}

	/**
	 * Method to verify
	 * @param locator
	 * @param text
	 */
	public void exactPageHeader(String text) {
		try {
			atfWaits.waitForBrowserLoad();
			String header = atfActions.getText(pageHeader);
			if (header.trim().equals(text)) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("The page header displayed is - '" + header + "' NOT '"+text);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error when comparing page header: "+text);
		}
	}
	
	public void partialPageHeader(String text) {

		try {
			atfWaits.waitForBrowserLoad();
			String header = atfActions.getText(pageHeader);
			if (header.trim().contains(text)) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("The page header displayed is - '" + header + "Do not contain "+text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to validate Home Page
	 * @param myDriver
	 */
	public void homepage() {
		String header = null;
		try {
			atfWaits.waitForBrowserLoad();
			header = atfActions.getText(pageHeader);
			if ((header.trim().equals("Patient Manager")) || (header.trim().equals("K-Mail"))|| (header.trim().equals("Hotbox"))) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("The page header displayed is - '" + header);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error when validating home page: "+header);
		}
	}
	
	public void elementContainsText(By locator, String Text) {

		try {
			atfWaits.waitForBrowserLoad();
			String elementTxt = atfActions.getText(locator);
			if (elementTxt.trim().contains(Text)) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("The text displayed is - '" + elementTxt + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void verifyOptionSelectedByValue(By locator, String expectedValue) 
	{
		String selectedValue=null;
		try
		{
			if (atfActions.isReady(locator)!=null)
			{
				Select sel = new Select(myDriver.findElement(locator));
				selectedValue = sel.getFirstSelectedOption().getAttribute("value");
				Assert.assertEquals(selectedValue, expectedValue,"Selected Value and Exptected value do not match");
			}
		}
		catch (AssertionError ae) {
			ae.printStackTrace();
			Assert.fail("Selected Option is: "+selectedValue + " but expected is - " +expectedValue);
		}
	}

	public void verifyOptionSelectedByText(By locator, String expectedValue) {
		String selectedValue=null;
		try
		{
			if (atfActions.isReady(locator)!=null)
			{
				Select sel = new Select(myDriver.findElement(locator));
				selectedValue = sel.getFirstSelectedOption().getText();
				Assert.assertEquals(selectedValue, expectedValue,"Selected Value and Exptected value do not match");
			}
		}
		catch (AssertionError ae) {
			ae.printStackTrace();
			Assert.fail("Selected Option is: "+selectedValue + " but expected is - " +expectedValue);
		}
	}
	
	
	public void verifyAlertMessage(String text) throws Exception {
		String getMsg;
		try {
			atfWaits.waitForBrowserLoad();
			getMsg = atfActions.getText(alertMessage);
			if (getMsg.trim().equals(text.trim())) {
				Assert.assertTrue(true);
			} else 
			{
				Assert.fail("Alert " + text + " is NOT displayed. Alert displayed is " + getMsg);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	public void verifyAlertMessageContainsText(String text) throws Exception {
		String getMsg;
		try {
			getMsg =driver.getAlertText();
			if (getMsg.contains(text)) {
			} else {
				Assert.fail("Alert " + text + " is NOT displayed.  Alert Message was: " + getMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("An error was thrown - " + e.getMessage());
		}
	}

	// Method to verify the element is displayed or not - Duplicates by one in
	// ATFActions
	public boolean isElementDisplayed(By by) {
		try {
			atfWaits.setImplicitWait(5);
			return myDriver.findElement(by).isDisplayed();
		} catch (Exception e) {
			//do Nothing
		}
		return false;
	}
	
	//Method to verify the element is Present in DOM  or not
	public boolean isElementPresent(By by)
	{	
		Boolean result = false;
		try{
			atfWaits.setImplicitWait(5);			
			myDriver.findElement(by);
			result = true;
		} catch(Exception e) {
			//do Nothing
		}
		atfWaits.setImplicitWait();
		return result;
	}

	//Method to verify the Attribute is present in DOM or not
	public boolean isAttributePresent(By by, String attribute) throws InterruptedException {
		Boolean result = false;
		atfWaits.setImplicitWait(5);
		try {
			if(!myDriver.findElement(by).getAttribute(attribute).isEmpty()){
				result = true;
			}
		} catch (Exception e) {}

		atfWaits.setImplicitWait();
		return result;
	}

	//Method to verify the Attribute is present in DOM or not
	public boolean isAttributeHasValue(By by, String attribute, String expectedValue) throws InterruptedException {
		Boolean result = false;
		String actualValue ="";
		atfWaits.setImplicitWait(5);
		try {
			actualValue = myDriver.findElement(by).getAttribute(attribute);
			if(actualValue.contains(expectedValue)){
				result = true;
			}
		} catch (Exception e) {}

		atfWaits.setImplicitWait();
		return result;
	}

	// Framework Generic Methods

	public void PDFText(String PDFlink, String toVerify) throws IOException {
		myDriver.get(PDFlink);
		URL url = new URL(PDFlink);
		BufferedInputStream pdoc = new BufferedInputStream(url.openStream());
		PDDocument doc = PDDocument.load(pdoc);
		String text = new PDFTextStripper().getText(doc);
		if (text.contains(toVerify)) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("PDF does not have the given text : " + toVerify + "");
		}
		doc.close();
		pdoc.close();
		System.out.println(text);

	}

	// To handle content type issue for pdf file, used recursive concept to get
	// the content type as application/pdf
	public void PDFTextWithoutUrl(String toVerify) throws Exception {
		HttpURLConnection con = null;
		InputStream is = null;

		try {
			atfWaits.waitForBrowserLoad();
			URL url = new URL(myDriver.getCurrentUrl());

			try {
				con = (HttpURLConnection) new URL(myDriver.getCurrentUrl()).openConnection();
				boolean isError = con.getResponseCode() >= 400;
				is = isError ? con.getErrorStream() : con.getInputStream();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (!con.getContentType().equalsIgnoreCase("application/pdf")) {
				System.out.println("-----------------------------" + con.getContentType().toString()
						+ "-----------------------------");
				System.out.println("FAILED.\n[Sorry. This is not a PDF.]");
				myDriver.navigate().refresh();
				PDFTextWithoutUrl(toVerify);
			} else {
				System.out.println("-----------------------------" + con.getContentType().toString()
						+ "-----------------------------");
				BufferedInputStream pdoc = new BufferedInputStream(is);
				PDDocument doc = PDDocument.load(pdoc);
				String text = new PDFTextStripper().getText(doc);

				for (String item : toVerify.split(",")) {
					if (text.contains(item)) {
						Assert.assertTrue(true, "PDF has the given text : " + item + "");
					} else {
						Assert.fail("PDF does not have the given text : " + toVerify + "");
						break;
					}
				}
				doc.close();
				pdoc.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			is.close();
			con.disconnect();
		}
	}






	// Reusable method to verify the text in the excel sheet
	public void verifyTextInExcel(String excelFilePath, String sheetName, ArrayList<String> toVerify)
			throws IOException, Exception {

		if (!isFileExists(excelFilePath)) {
			Assert.fail("File does not exists in the expected path : " + excelFilePath);
		}

		HSSFWorkbook excelBook = new HSSFWorkbook(new FileInputStream(new File(excelFilePath)));

		DataFormatter objDefaultFormat = new DataFormatter();
		FormulaEvaluator objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) excelBook);
		HSSFSheet excelSheet = excelBook.getSheet(sheetName);
		int totalVerificationCount = 0;

		Iterator<Row> rows = excelSheet.rowIterator();

		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.cellIterator();
			while (cells.hasNext()) {
				Cell cellValue = (HSSFCell) cells.next();
				objFormulaEvaluator.evaluate(cellValue);
				String cellValueStr = objDefaultFormat.formatCellValue(cellValue, objFormulaEvaluator);
				if (validateTextInCell(toVerify, cellValueStr)) {
					totalVerificationCount++;
				}
			}
			if (totalVerificationCount == toVerify.size()) {

				Assert.assertTrue(true, "Excel contains the expected text: " + toVerify + "");
				break;
			}
		}

		if (totalVerificationCount != toVerify.size()) {
			Assert.fail("Excel doesn't have given text: " + toVerify + "");
		}

		excelBook.close();
	}
	
	//Method to validate the excel cell's text with the array of string
	public boolean validateTextInCell(ArrayList<String> stringsToVerify, String cellValue ){
		boolean result = false;		
		for (String s : stringsToVerify) {
        	if(cellValue.contains(s)){
        		result = true;
        		break;
        	}
        }
		return result;
	}

	// Reusable method to verify the file exist in the file path or not, if not
	// wait for 1 minute and check the existence
	public boolean isFileExists(String FullFilePath) throws InterruptedException {
		boolean result = true;
		File f = new File(FullFilePath);
		int i = 0;
		do {
			Thread.sleep(3000);
			i++;
			if (i >= 20) {
				result = false;
				break;
			}
		} while (!f.exists());

		return result;
	}




	public void pdfTextWithoutUrl(String toVerify) throws FileNotFoundException, IOException {
		try {
			Thread.sleep(5000);
			URL url = new URL(myDriver.getCurrentUrl());
			BufferedInputStream pdoc = new BufferedInputStream(url.openStream());
			PDDocument doc = PDDocument.load(pdoc);
			String text = new PDFTextStripper().getText(doc);
			if (text.contains(toVerify)) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("PDF does not have the given text : " + toVerify + "");
			}
			doc.close();
			pdoc.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void verifyText(String text, By locator) {
		try {
			atfWaits.waitForBrowserLoad();
			String sentence = myDriver.findElement(locator).getText();
			if (sentence.trim().equals(text)) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("The sentence displayed is - '" + sentence + "' NOT Accurate'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyLinkNotPresent(String text) throws Exception {
		List<WebElement> link = myDriver.findElements(By.linkText(text));
		if (link.isEmpty() == false) {
			Assert.fail(text + " Link is present.");
		}
	}

}

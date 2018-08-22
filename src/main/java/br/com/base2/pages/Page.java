/**********************************************
 * @author Vagner Clementino
 * 
 * ********************************************/
package br.com.base2.pages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

//import leaf.qa.keywords.Screenshot;

/*
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

	public WebDriverWait wait;
	
	protected WebDriver webDriver;
	
	protected TakesScreenshot scrShot; 
	
	protected String screenshotFileName;
	
	

	protected Page(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.scrShot  = ((TakesScreenshot) webDriver);
		this.wait = new WebDriverWait(webDriver, 30);
		this.screenshotFileName = new SimpleDateFormat("yyyyMMddHHmmss'-test.png'").format(new Date());
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public String getTitle() {
		return webDriver.getTitle();
	}
	
	public Page getPage() {
		
		return this;
		
	}
	
	 public void takeSnapShot() throws Exception{

					
			// Call getScreenshotAs method to create image file
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

			// Move image file to new destination
			File DestFile = new File(new SimpleDateFormat("'./screenshots/'yyyyMMddHHmmss'-"  + this.screenshotFileName + "'")
										.format(new Date()));

			// Copy file at destination
			FileUtils.copyFile(SrcFile, DestFile);

	    }


}

package br.com.base2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

//import leaf.qa.keywords.Screenshot;

/*
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

	//public Screenshot screenshot;	
	public WebDriverWait wait;
	
	protected WebDriver webDriver;

	protected Page(WebDriver webDriver) {
		this.webDriver = webDriver;
	//	this.screenshot = new Screenshot(webDriver);
		this.wait = new WebDriverWait(webDriver, 30);
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

}

package br.com.base2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewIssuesPage extends Page {
	
	@FindBy(css = ".width50 > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > p:nth-child(1)")
	@CacheLookup
	private WebElement pageMessage;

	public ViewIssuesPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(webDriver, this);
	}
	
	public String getPageMessage() {
		
		return pageMessage.getText();
	}


}

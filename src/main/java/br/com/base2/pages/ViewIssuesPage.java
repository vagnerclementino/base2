package br.com.base2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewIssuesPage extends Page {
	
	@FindBy(css = "td.menu:nth-child(1) > a:nth-child(3)") 
	@CacheLookup
	private WebElement pageMessage;
	
	@FindBy(css = ".width50 > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > p:nth-child(1)")
	@CacheLookup
	private WebElement erroMessage;
	
	@FindBy(css ="td.menu:nth-child(1) > a:nth-child(3)") 
	@CacheLookup
	private WebElement menuItemBugReport;

	public ViewIssuesPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(webDriver, this);
	}
	
	public String getPageMessage() {
		
		return pageMessage.getText();
	}
	
	public String getErroMessage() {
		
		return erroMessage.getText();
	}
	public BugReportPage goToBugReportPage() {
		this.menuItemBugReport.click();
		return new BugReportPage(webDriver);
	}


}

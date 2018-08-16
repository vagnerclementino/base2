package br.com.base2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewIssueDetailsPage extends Page {
	
	//WebElements
	@FindBy(css = "table.width100:nth-child(6) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1)")
	@CacheLookup
	private WebElement issueID;

	public ViewIssueDetailsPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(webDriver, this);

	}
	
	public String getIssueID() {
		
		return this.issueID.getText();
		
	}

}

package br.com.base2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewIssueDetailsPage extends Page {
	
	//WebElements
	@FindBy(xpath = "/html/body/table[3]/tbody/tr[3]/td[1]") 
	@CacheLookup
	private WebElement issueID;
	
	@FindBy(css ="td.menu:nth-child(1) > a:nth-child(1)") 
	@CacheLookup
	private WebElement menuItemMyView;

	public ViewIssueDetailsPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(webDriver, this);

	}
	
	public String getIssueID() {
		
		return this.issueID.getText();
		
	}
	
	public HomePage goToHomePage() {
		this.menuItemMyView.click();
		return new HomePage(webDriver);
	}

}

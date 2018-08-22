/**********************************************
 * @author Vagner Clementino
 * 
 * ********************************************/
package br.com.base2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/*
 * Sample page
 *
 */
public class HomePage extends Page {
	

	@FindBy(css ="td.menu:nth-child(1) > a:nth-child(3)") 
	@CacheLookup
	private WebElement menuItemBugReport;
	
	@FindBy(name = "project_id")
	@CacheLookup
	private WebElement projectSelector;
	
	@FindBy(css = ".login-info-right > form:nth-child(1) > input:nth-child(2)")
	@CacheLookup
	private WebElement switchProjectButton;
	
	@FindBy(name = "bug_id")
	@CacheLookup
	private WebElement issueIDInput;
	
	@FindBy(css = "td.menu:nth-child(2) > form:nth-child(1) > input:nth-child(2)")
	@CacheLookup
	private WebElement jumpIssueButton;

	public HomePage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(webDriver, this);
		screenshotFileName =  "home-page-test.png";
	}


	public BugReportPage openBugReportPage(String projectName) throws Exception{
		
		Select projectDropdown = new Select(this.projectSelector);
		projectDropdown.selectByVisibleText(projectName);
		this.switchProjectButton.click();
		
		this.menuItemBugReport.click();
		//Tirando um screnshot da tela
		takeSnapShot();
		return new BugReportPage(webDriver);
		
	}
	
public ViewIssueDetailsPage jumpToIssue(String issueNumber) throws Exception{
		
	
		this.issueIDInput.sendKeys(issueNumber);
		this.jumpIssueButton.click();
		return new ViewIssueDetailsPage(webDriver);
		
	}

}

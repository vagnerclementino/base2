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
	
	@FindBy(using = "nav-item-staff")
	@CacheLookup
	private WebElement employeeIcon;
	
	@FindBy(css = "li.nav-item-staff li.submenu-item-staff")
	@CacheLookup
	private WebElement employeeList;
	
	@FindBy(css = ".nav-item-utilities a")
	private WebElement settingsNav;

	@FindBy(id = "content_nav_item_2")
	private WebElement leftNavigationElementList;
	
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
	}

//	//@Step
//	public EmployeePage goToEmployeePage () throws Exception{
////		Actions action = new Actions(webDriver);
//		// precaution for moveTo not to fail, do it seperately.
////		action.moveToElement(employeeIcon).perform();
////		action.moveToElement(employeeList).click(employeeList).perform();
//		employeeIcon.click();
//		return new EmployeePage(webDriver);
//	}
	
	public BugReportPage openBugReportPage(String projectName) throws Exception{
		
		Select projectDropdown = new Select(this.projectSelector);
		projectDropdown.selectByVisibleText(projectName);
		this.switchProjectButton.click();
		
		this.menuItemBugReport.click();
		return new BugReportPage(webDriver);
		
	}
	
public ViewIssueDetailsPage jumpToIssue(String issueNumber) throws Exception{
		
	
		this.issueIDInput.sendKeys(issueNumber);
		this.jumpIssueButton.click();
		return new ViewIssueDetailsPage(webDriver);
		
	}

}

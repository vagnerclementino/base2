/**********************************************
 * @author Vagner Clementino
 * 
 * ********************************************/


package br.com.base2.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BugReportPage extends Page {
	
	//WebElements	
	@FindBy(name = "category_id")
	@CacheLookup
	private WebElement categorySelector;
	
	@FindBy(name = "summary")
	@CacheLookup
	private WebElement summaryInput;

	@FindBy(name = "description")
	@CacheLookup
	private WebElement descriptionInput;
	
	@FindBy(css = ".button")
	@CacheLookup
	private WebElement submitReportButton;
	

	public BugReportPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(webDriver, this);
		screenshotFileName =  "bugreport-page-test.png";
		
	}
	
	public ViewIssuesPage submitReport(String category, String summary, String description) {
		
		Select categoryDropdown = new Select(categorySelector);
		categoryDropdown.selectByVisibleText(category);
		summaryInput.sendKeys(summary);
		descriptionInput.sendKeys(description);
		submitReportButton.click();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		return new ViewIssuesPage(webDriver);
		
	}
	
	
}

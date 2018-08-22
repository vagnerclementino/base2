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

public class LoginPage extends Page {
	
	//WebElements
	@FindBy(name = "username")
	@CacheLookup
	private WebElement username;

	@FindBy(name = "password")
	@CacheLookup
	private WebElement password;

	@FindBy(css = ".button")
	@CacheLookup
	private WebElement loginbtn;
	
	@FindBy(css ="body > div:nth-child(3) > font:nth-child(1)")
	@CacheLookup
	private WebElement userMessage;
	

	public LoginPage(WebDriver webDriver) {
		
		super(webDriver);
		PageFactory.initElements(webDriver, this);
		screenshotFileName =  "login-page-test.png";
		
		// Verificando se estamos na página correta
        if (!"MantisBT".equals(webDriver.getTitle())) {
            
            throw new IllegalStateException("Esta não é a página de login.");
         }		
        
	}

	public HomePage login(String username, String password) throws Exception{
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		loginbtn.click();
		takeSnapShot();
		return new HomePage(webDriver);
	}
	
	public String getUserMessage() {
		return this.userMessage.getText();
	}

}

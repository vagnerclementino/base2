package br.com.base2.testes;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.base2.pages.HomePage;
import br.com.base2.pages.LoginPage;

public class LoginPageTest {
	
	// Declarando um objeto do tipo WebDriver, utilizado pelo Selenium WebDriver.
    private WebDriver driver;
 
    // Método que inicia tudo que for necessário para o teste
    // Cria uma instância do navegador e abre a página inicial da DevMedia.
    @Before
    public void setUp() throws Exception {
    	
		System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
		driver = new FirefoxDriver();
		
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		
		//Realizando a requisicao para o site a ser testado
        driver.get("http://mantis-prova.base2.com.br");

    }
 
    // Método que finaliza o teste, fechando a instância do WebDriver.    
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
 
     
    // Método que testa o login com sucesso no site.
    @Test
    public void testaLoginComSucesso() {
 

    LoginPage loginPage = new LoginPage(this.driver);
    HomePage homePage = loginPage.login("vagner.clementino", "base2");
            
    //Testa o título para verificar se o login foi realizado
    //e  o usuário foi redirecionado para a página incial    
    assertEquals("My View - MantisBT", homePage.getTitle());

    }
    
 // Método que testa o login no site com um usuário ou senha inválidos.
    @Test
    public void testaLoginSemSucesso() throws Exception{
 
     LoginPage loginPage = new LoginPage(this.driver);
     loginPage.login("invaliduser", "invalidpass");
     
     assertEquals("Your account may be disabled or blocked or the username/password you entered is incorrect.",loginPage.getUserMessage());
        
    }

}

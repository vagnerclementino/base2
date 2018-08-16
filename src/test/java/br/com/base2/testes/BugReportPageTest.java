package br.com.base2.testes;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.base2.pages.BugReportPage;
import br.com.base2.pages.HomePage;
import br.com.base2.pages.LoginPage;
import br.com.base2.pages.ViewIssuesPage;

public class BugReportPageTest {
	
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
    public void testaCriaBugReport() throws Exception{
 

    LoginPage mantisLoginPage = new LoginPage(this.driver);
    HomePage  mantisHomePage = mantisLoginPage.login("vagner.clementino", "base2");
    BugReportPage vagnerProjectBugReportPage = mantisHomePage.openBugReportPage("Vagner Clementino's project");
     
    ViewIssuesPage viewIssuePage =  vagnerProjectBugReportPage.submitReport("[All Projects] Teste Patrick", 
    									    "Erro nos testes automáticos", 
    									    "Encontrado erro nos testes automáticos");
    
    //Testa o título para verificar se o login foi realizado
    //e  o usuário foi redirecionado para a página incial    
    assertEquals("View Issues - MantisBT", viewIssuePage.getTitle());

    }
    
    @Test
    public void testaCriaBugReportDescricaoNula() throws Exception{
 

    LoginPage mantisLoginPage = new LoginPage(this.driver);
    HomePage  mantisHomePage = mantisLoginPage.login("vagner.clementino", "base2");
    BugReportPage vagnerProjectBugReportPage = mantisHomePage.openBugReportPage("Vagner Clementino's project");
     
    ViewIssuesPage viewIssuePage =  vagnerProjectBugReportPage.submitReport("[All Projects] Teste Patrick", 
    									    "Erro nos testes ", 
    									    "");
    
    //Testa o título para verificar se o login foi realizado
    //e  o usuário foi redirecionado para a página incial    
    assertEquals("A necessary field \"Description\" was empty. Please recheck your inputs.", viewIssuePage.getPageMessage());

    }

}

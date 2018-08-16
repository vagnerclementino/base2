package br.com.base2.testes;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.base2.pages.BugReportPage;
import br.com.base2.pages.HomePage;
import br.com.base2.pages.LoginPage;
import br.com.base2.pages.ViewIssuesPage;
import br.com.base2.util.Base2CSVReader;

public class BugReportPageTest {
	
	// Declarando um objeto do tipo WebDriver, utilizado pelo Selenium WebDriver. 
    private WebDriver driver;
    
    private Base2CSVReader csvReader;

 
    // Método que inicia tudo que for necessário para o teste
    // Cria uma instância do navegador e abre a página inicial da DevMedia.
    @Before
    public void setUp() throws Exception {
    	
    	/*********************************************************************************
    	 * 
    	 * Firefox in Windows x64 
    	 * 
    	 * *******************************************************************************/
		System.setProperty("webdriver.gecko.driver", "./bin/geckodriver/geckodriver.exe");
		
	 	/*********************************************************************************
    	 * 
    	 * Firefox in Linux x64 property 
    	 * 
    	 * *******************************************************************************/
		//System.setProperty("webdriver.gecko.driver", "./bin/geckodriver/geckodriver");
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
 
    	//Iniciando o leitor do arquivo csv
	    String cvsTestData = "./data/bug-report/bug-reports.csv";
	    csvReader = new Base2CSVReader(cvsTestData);
	    List<String[]>testaData = this.csvReader.readAll();
	    LoginPage mantisLoginPage = new LoginPage(this.driver);
	    HomePage  mantisHomePage = mantisLoginPage.login("vagner.clementino", "base2");
	    BugReportPage vagnerProjectBugReportPage = mantisHomePage.openBugReportPage("Vagner Clementino's project");
	     
	    
	    for (String[] line : testaData) {	    	
		 
		    ViewIssuesPage viewIssuePage =  vagnerProjectBugReportPage.submitReport(line[0], line[1], line[2]);	    
		    
		    //Testa o título para verificar se o login foi realizado
		    //e  o usuário foi redirecionado para a página incial    
		    assertEquals("Report Issue", viewIssuePage.getPageMessage());
		    vagnerProjectBugReportPage = viewIssuePage.goToBugReportPage();
		
		    }
    }
    
    @Test
    public void testaCriaBugReportDescricaoNula() throws Exception{
 
		// Iniciando o leitor do arquivo csv
		String cvsTestData = "./data/bug-report/bug-reports-null-description.csv";
		csvReader = new Base2CSVReader(cvsTestData);
		List<String[]> testaData = this.csvReader.readAll();
		LoginPage mantisLoginPage = new LoginPage(this.driver);
		HomePage mantisHomePage = mantisLoginPage.login("vagner.clementino", "base2");
		BugReportPage vagnerProjectBugReportPage = mantisHomePage.openBugReportPage("Vagner Clementino's project");

		for (String[] line : testaData) {
		

			ViewIssuesPage viewIssuePage = vagnerProjectBugReportPage.submitReport(line[0], line[1], line[2]);
			// Testa o título para verificar se o login foi realizado
			// e o usuário foi redirecionado para a página incial
			assertEquals("A necessary field \"Description\" was empty. Please recheck your inputs.",
					     viewIssuePage.getErroMessage());		
			
			vagnerProjectBugReportPage  = viewIssuePage.goToBugReportPage();
				
		}

	}

}

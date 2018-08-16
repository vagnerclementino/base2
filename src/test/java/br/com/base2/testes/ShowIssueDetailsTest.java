package br.com.base2.testes;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.base2.pages.HomePage;
import br.com.base2.pages.LoginPage;
import br.com.base2.pages.ViewIssueDetailsPage;
import br.com.base2.util.Base2CSVReader;

public class ShowIssueDetailsTest {
	
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
		
		/*********************************************************************************
    	 * 
    	 * Chrome in Windows x64 
    	 * 
    	 * *******************************************************************************/		
		//System.setProperty("webdriver.chrome.driver","./bin/geckodriver/chromedriver.exe");		
		//WebDriver driver = new ChromeDriver();
		
		/*********************************************************************************
    	 * 
    	 * Chrome in Linux x64 
    	 * 
    	 * *******************************************************************************/
		//System.setProperty("webdriver.chrome.driver","./bin/geckodriver/chromedriver");		
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
    public void testaVisualizarIssueDetalhes() throws Exception{
 
		// Iniciando o leitor do arquivo csv
		String cvsTestData = "./data/issue-details/bugs.csv";
		csvReader = new Base2CSVReader(cvsTestData);
		List<String[]> testaData = this.csvReader.readAll();
		LoginPage mantisLoginPage = new LoginPage(this.driver);
		HomePage mantisHomePage = mantisLoginPage.login("vagner.clementino", "base2");
		

		for (String[] line : testaData) {
			
			
			ViewIssueDetailsPage issueDetail = mantisHomePage.jumpToIssue(line[0]);
			// Testa o título para verificar se o login foi realizado
			// e o usuário foi redirecionado para a página incial
			assertEquals(line[0], issueDetail.getIssueID());
			
			//Voltando para a página inicial
			mantisHomePage = issueDetail.goToHomePage();
			
		}
    }

}
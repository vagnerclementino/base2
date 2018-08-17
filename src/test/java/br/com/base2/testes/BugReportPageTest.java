/**********************************************
 * @author Vagner Clementino
 * @create 16/08/2018
 * @todo:
 *   - Criação de uma classe base para
 *     realizar as operações comuns
 *     a todos os teste.
 * 
 * ********************************************/
package br.com.base2.testes;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.base2.pages.BugReportPage;
import br.com.base2.pages.HomePage;
import br.com.base2.pages.LoginPage;
import br.com.base2.pages.ViewIssuesPage;
import br.com.base2.util.Base2CSVReader;

public class BugReportPageTest {
	
    private WebDriver driver;    
    private Base2CSVReader csvReader;

 
	/****************************************************
	 * A configuração do teste está disponível para
	 * dois webdrivers: Firefox e Chrome. Para a utilização
	 * de um webdriver diferente remova o comentário do 
	 * webdriver a ser utilizado.
	 * *****************************************************/
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
        
      //Call take screenshot function
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss'-bug-report-test.png'").format(new Date());
        takeSnapShot(driver, "./screenshots/" + fileName);

    }
 
    @After
    public void tearDown() throws Exception {
    	//Fechando a instância do webdriver.
        driver.quit();
    }
 
    @Test
    /**********************************************
     * Nesse teste realizamos a criação de
     * um bug report. Nesse cenário preenchemos
     * apenas os campos obrigatórios do bug report:
     * category, summary e description. Para 
     * identificarmos que o teste executou com sucesso
     * verificamos se após o bug report ser submetido
     * o usuário foi redirecionado para página de listagem
     * de issues que possui o texto "Bug Report" na página.
     * ******************************************************/    
    public void testaCriaBugReport() throws Exception{
 
    	//Obtendo os dados de teste do arquivo CSV
	    String cvsTestData = "./data/bug-report/bug-reports.csv";
	    csvReader = new Base2CSVReader(cvsTestData);
	    List<String[]>testaData = this.csvReader.readAll();
	    
	    //Acessando a página de login do site
	    LoginPage mantisLoginPage = new LoginPage(this.driver);
	    //Realizando o login na página
	    HomePage  mantisHomePage = mantisLoginPage.login("vagner.clementino", "base2");
	    //Selecionando o projeto de interesse
	    BugReportPage vagnerProjectBugReportPage = mantisHomePage.openBugReportPage("Vagner Clementino's project");
	    
	    
	    //Para cada linha do arquivo csv com os dados do
	    //teste realiza a submissão de um
	    //bug report
	    for (String[] line : testaData) {	    	
		    
	    	//line[0] - category
	    	//line[1] - summary
	    	//line[2] - description
	    	
	    	//Criando um bug report
		    ViewIssuesPage viewIssuePage =  vagnerProjectBugReportPage.submitReport(line[0], line[1], line[2]);	    
		    
		    //Verificando se o bug report foi criado com sucesso 
		    //e o usuário foi redirecionado para a página correta
		    assertEquals("Report Issue", viewIssuePage.getPageMessage());
		    //Volta para a página de criação de bug reports
		    // para criar um novo bug report
		    vagnerProjectBugReportPage = viewIssuePage.goToBugReportPage();		
		    }
    }
    
    @Test
    /**********************************************
     * Nesse teste realizamos a criação de
     * um bug report. Nesse cenário preenchemos
     * apenas dois dos obrigatórios do bug report:
     * category esummary. O campo description será nulo.
     * Para identificarmos que o caso de uso funcionou 
     * corretamente verificamos a exibição da 
     * mensagem "A necessary field "Description\" was empty. 
     * Please recheck your inputs" ao usuário.
     * ******************************************************/
    public void testaCriaBugReportDescricaoNula() throws Exception{
 
    	//Obtendo os dados de teste do arquivo CSV
		String cvsTestData = "./data/bug-report/bug-reports-null-description.csv";
		csvReader = new Base2CSVReader(cvsTestData);
		List<String[]> testaData = this.csvReader.readAll();
		
	    //Acessando a página de login do site
		LoginPage mantisLoginPage = new LoginPage(this.driver);
	    //Realizando o login na página
		HomePage mantisHomePage = mantisLoginPage.login("vagner.clementino", "base2");
	    //Selecionando o projeto de interesse
		BugReportPage vagnerProjectBugReportPage = mantisHomePage.openBugReportPage("Vagner Clementino's project");

		for (String[] line : testaData) {
			
		  	//line[0] - category
	    	//line[1] - summary
	    	//line[2] - null
			
	    	//Criando um bug report
			ViewIssuesPage viewIssuePage = vagnerProjectBugReportPage.submitReport(line[0], line[1], line[2]);

			// Verifica a exibição da mensagem de erro.
			assertEquals("A necessary field \"Description\" was empty. Please recheck your inputs.",
					     viewIssuePage.getErroMessage());
			
		    //Volta para a página de criação de bug reports
		    // para criar um novo bug report
			vagnerProjectBugReportPage  = viewIssuePage.goToBugReportPage();
		}
		csvReader.close();


	}
    
    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception{

		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination
		File DestFile = new File(fileWithPath);

		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);

    }

}

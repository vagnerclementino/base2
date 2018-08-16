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

import br.com.base2.pages.HomePage;
import br.com.base2.pages.LoginPage;
import br.com.base2.util.Base2CSVReader;

public class LoginPageTest {
	
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
    public void testaLoginComSucesso() throws Exception{
 

	    
	    //Iniciando o leitor do arquivo csv
	    String cvsTestData = "./data/login/valid-credentials.csv";
	    csvReader = new Base2CSVReader(cvsTestData);
	    List<String[]>testaData = this.csvReader.readAll();
	    
	    for (String[] line : testaData) { 
	    	
	    	LoginPage mantisLoginPage = new LoginPage(this.driver);
	    	HomePage  mantisHomePage = mantisLoginPage.login(line[0], line[1]);
	        
	        //Testa o título para verificar se o login foi realizado
	        //e  o usuário foi redirecionado para a página incial    
	    	 //Call take screenshot function
	    	String fileName = new SimpleDateFormat("yyyyMMddHHmmss'-login-page-test.png'").format(new Date());
	        takeSnapShot(driver, "./screenshots/" + fileName);
	        assertEquals("My View - MantisBT", mantisHomePage.getTitle());    	
			
		}
	    
	    csvReader.close();

    }
    
    // Método que testa o login no site com um usuário ou senha inválidos.
    @Test
    public void testaLoginSemSucesso() throws Exception{
 
	     
	     String cvsTestData = "./data/login/invalid-credentials.csv";
	     csvReader = new Base2CSVReader(cvsTestData);
	     List<String[]>testaData = this.csvReader.readAll();
	     
	     for (String[] line : testaData) {
	    	 
	    	 LoginPage loginPage = new LoginPage(this.driver);		     
	    	 loginPage.login(line[0], line[1]); 
	    	 String fileName = new SimpleDateFormat("yyyyMMddHHmmss'-login-page-com-sucesso-test.png'").format(new Date());
	    	 takeSnapShot(driver, "./screenshots/" + fileName);
	    	 assertEquals("Your account may be disabled or blocked or the username/password you entered is incorrect.",loginPage.getUserMessage());
	     }
	     
	     csvReader.close();
        
    }
    
    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

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

package br.com.base2.testes;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.base2.util.Base2CSVReader;

public class TestBase {
	// Declarando um objeto do tipo WebDriver,
		//utilizado pelo Selenium WebDriver. 
	    protected WebDriver driver;
	    protected Base2CSVReader csvReader;
	   
	 
		/****************************************************
		 * A configuração do teste está disponível para
		 * dois webdrivers: Firefox e Chrome. Para a utilização
		 * de um webdriver diferente remova o comentário do 
		 * webdriver a ser utilizado.
		 * *****************************************************/
	    @Before
	    public void setUp() throws Exception {
	    	
	    	/****************************************************
	    	 * A configuração do teste está disponível para
	    	 * dois webdrivers: Firefox e Chrome. Para a utilização
	    	 * de um webdriver diferente remova o comentário do 
	    	 * webdriver a ser utilizado.
	    	 * *****************************************************/
	    	
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

}

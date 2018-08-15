package br.com.base2.testes;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginPageTest {
	
	// Declarando um objeto do tipo WebDriver, utilizado pelo Selenium WebDriver.
    private WebDriver driver;
 
    // Método que inicia tudo que for necessário para o teste
    // Cria uma instância do navegador e abre a página inicial da DevMedia.
    @Before
    public void setUp() throws Exception {
    	
    	//System.setProperty("webdriver.firefox.marionette","/usr/local/bin/geckodriver");
		System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		
		driver = new FirefoxDriver();
        driver.get("http://mantis-prova.base2.com.br");

    }
 
    // Método que finaliza o teste, fechando a instância do WebDriver.    
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
 
    // Testa título "DevMedia - Cursos, Tutoriais e Vídeos para Desenvolvedores".
    @Test
    public void testaTituloDaPagina() throws Exception {
        assertEquals("MantisBT", driver.getTitle());
    }
 
    // Método que testa o login no site.
    @Test
    public void testaLoginComSucesso() {
 
    // Instancia um novo objeto do tipo "WebElement", e passa como parâmetro
    // um elemento da tela cujo valor do atributo "name" seja igual a "usuario".
    WebElement element = driver.findElement(By.name("username"));
     
    // Insere dados no elemento "usuario".
    element.sendKeys("vagner.clementino");
 
    // Atribui ao objeto “element” o elemento de atributo "name" igual a "senha".
    element = driver.findElement(By.name("password"));
 
    // Insere dados no elemento "senha".
    element.sendKeys("base2");
 
    // Clica no botão "OK" e submete os dados para concluir o login.
        driver.findElement(By.className("button")).click();
    
    //Testa o título para verificar se o login foi realizado
    //e  o usuário foi redirecionado para a página incial    
    assertEquals("My View - MantisBT", driver.getTitle());

    }
    
 // Método que testa o login no site com um usuário ou senha inválidos.
    @Test
    public void testaLoginSemSucesso() {
 
    // Instancia um novo objeto do tipo "WebElement", e passa como parâmetro
    // um elemento da tela cujo valor do atributo "name" seja igual a "usuario".
    WebElement element = driver.findElement(By.name("username"));
     
    // Insere dados no elemento "usuario".
    element.sendKeys("usuario");
 
    // Atribui ao objeto “element” o elemento de atributo "name" igual a "senha".
    element = driver.findElement(By.name("password"));
 
    // Insere dados no elemento "senha".
    element.sendKeys("123456");
 
    // Clica no botão "OK" e submete os dados para concluir o login.
     driver.findElement(By.className("button")).click();
     
     element = driver.findElement(By.cssSelector("body > div:nth-child(3) > font:nth-child(1)"));
     
     assertEquals("Your account may be disabled or blocked or the username/password you entered is incorrect.",element.getText());
        
    }

}

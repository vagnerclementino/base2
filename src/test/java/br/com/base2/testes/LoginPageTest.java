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

import java.util.List;

import org.junit.Test;

import br.com.base2.pages.HomePage;
import br.com.base2.pages.LoginPage;
import br.com.base2.util.Base2CSVReader;

public class LoginPageTest extends TestBase{
	
 
    @Test
    /**********************************************
     * Nesse teste realizamos  login no site.
     * Nesse cenário passamos as credenciais de um
     * usuário válido.
     * ******************************************************/
    public void testaLoginComSucesso() throws Exception{
 
    	//Obtendo os dados de teste do arquivo CSV
	    String cvsTestData = "./data/login/valid-credentials.csv";
	    csvReader = new Base2CSVReader(cvsTestData);
	    List<String[]>testaData = csvReader.readAll();
	    
	    for (String[] line : testaData) { 
	    	
	    	//line[0] - username
	    	//line[1] - password
	   
	    	
	    	//Acessa a página de login do site
	    	LoginPage mantisLoginPage = new LoginPage(driver);
	    	//Realiza o login no site
	    	HomePage  mantisHomePage = mantisLoginPage.login(line[0], line[1]);
	           
	        //Testa o título para verificar se o login foi realizado
	        //e  o usuário foi redirecionado para a página incial  
	        assertEquals("My View - MantisBT", mantisHomePage.getTitle());    	
		}
	    csvReader.close();
    }
    
    @Test
    /**********************************************
     * Nesse teste realizamos  login no site.
     * Nesse cenário passamos as credenciais de um
     * usuário inválido. Para identificamos que o caso de uso
     * funciou corretamente esperamos a exibição da seguinte
     * mensagem para o usuário: "Your account may be disabled 
     * or blocked or the username/password you entered is incorrect."
     * ******************************************************/
    public void testaLoginSemSucesso() throws Exception{
 
	     
	     String cvsTestData = "./data/login/invalid-credentials.csv";
	     csvReader = new Base2CSVReader(cvsTestData);
	     List<String[]>testaData = this.csvReader.readAll();
	     
	     for (String[] line : testaData) {
		     
	    	 //Acessa a página de login do site
	    	 LoginPage loginPage = new LoginPage(this.driver);
	    	 //Realiza o login no site
	    	 loginPage.login(line[0], line[1]); 
	    	 
 
	    	 //Verificando a exibição da mensagem de erro
	    	 assertEquals("Your account may be disabled or blocked or the username/password you entered is incorrect.",loginPage.getUserMessage());
	     }
	     
	     csvReader.close();
        
    }
    
  

}

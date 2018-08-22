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
import br.com.base2.pages.ViewIssueDetailsPage;
import br.com.base2.util.Base2CSVReader;

public class ShowIssueDetailsTest extends TestBase {

     
    // Método que testa o login com sucesso no site.
    @Test
    public void testaVisualizarIssueDetalhes() throws Exception{
 
       	//Obtendo os dados de teste do arquivo CSV
		String cvsTestData = "./data/issue-details/bugs.csv";
		csvReader = new Base2CSVReader(cvsTestData);
		List<String[]> testaData = this.csvReader.readAll();
		
		//Acessa a página de login do site
		LoginPage mantisLoginPage = new LoginPage(this.driver);
    	//Realiza o login no site
		HomePage mantisHomePage = mantisLoginPage.login("vagner.clementino", "base2");
		

		for (String[] line : testaData) {
			
			//line[0] - bug ID	    	
			
			//Buscando uma issue pelo seu ID
			ViewIssueDetailsPage issueDetail = mantisHomePage.jumpToIssue(line[0]);
    	 
			// Testa o título para verificar se o login foi realizado
			// e o usuário foi redirecionado para a página incial
			assertEquals(line[0], issueDetail.getIssueID());
			
			//Voltando para a página inicial
			mantisHomePage = issueDetail.goToHomePage();
			
		}
		csvReader.close();
    }
    
  

}

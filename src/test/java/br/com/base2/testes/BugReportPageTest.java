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

import br.com.base2.pages.BugReportPage;
import br.com.base2.pages.HomePage;
import br.com.base2.pages.LoginPage;
import br.com.base2.pages.ViewIssuesPage;
import br.com.base2.util.Base2CSVReader;

public class BugReportPageTest extends TestBase{
	
    
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
    
   
}

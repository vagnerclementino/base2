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
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

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
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss'-view-issue-details-test.png'").format(new Date());
	    	takeSnapShot(driver, "./screenshots/" + fileName);
	    	 
			// Testa o título para verificar se o login foi realizado
			// e o usuário foi redirecionado para a página incial
			assertEquals(line[0], issueDetail.getIssueID());
			
			//Voltando para a página inicial
			mantisHomePage = issueDetail.goToHomePage();
			
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

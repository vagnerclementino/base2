/**********************************************
 * @author Vagner Clementino
 * 
 * ********************************************/
package br.com.base2.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Base2CSVReader {
	
	
	//private String csvFilePath; 
	private final CSVReader reader;
	private final char SEPARATOR = ';';
	private boolean hasNext;
	
	public Base2CSVReader(String csvFilePath) throws FileNotFoundException {
		
		//this.csvFilePath = csvFilePath;
		
		
		CSVParser parser = 	new CSVParserBuilder()
								.withSeparator(SEPARATOR)
								.withIgnoreQuotations(true)
								.build();
		
	    this.reader =	new CSVReaderBuilder(new FileReader(csvFilePath))
							.withSkipLines(1)
							.withCSVParser(parser)
							.build();
		
		this.hasNext = true;
	}
	
	public String[] readLine()  {
		
		String[] line = null;
		try {
            
            line = reader.readNext();
            if (line == null) {
            	this.hasNext = false;
            }
       
        } catch (IOException e) {
            e.printStackTrace();
        }
		return line;
	}
	
	public boolean hasNext() {
		return this.hasNext;
	}
	
	public List<String[]> readAll() throws IOException {
		return this.reader.readAll();
	}
	
	public void close () throws IOException {
		this.reader.close();
	}
 
     

}

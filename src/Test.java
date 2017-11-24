
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import parse.Parser;
import token.Token;
import token.Tokenizer;

public class Test {
	
	/**
	 * Program entry point.
	 * @param args
	 */
	public static void main (String[] args) {
		new Test().processFile("src/expression.chpz");
	}
	
	/**
	 * Read tokens from a source code file and return a list of tokens.
	 * @param filePath
	 * @return
	 */
	public void processFile(String filePath) {
		
		try {
			Scanner sc = new Scanner(new File(filePath));
			
			// Create a new parser with which to build a script from the file.
			Parser parser = new Parser();
			
			while(sc.hasNextLine()) {
				
				// Read the next line form the file, this will represent a single statement or label.
				String line = sc.nextLine();
				
				// Process this line into tokens.
				ArrayList<Token> lineTokens = Tokenizer.processLine(line);
				
				// Get the parser to convert the line tokens into a statement.
				parser.processLineTokens(lineTokens);
			}
			
			parser.testRun();
			
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("error: file " + filePath + " not found");
		}
	}
}

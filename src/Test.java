
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import parse.Parser;
import script.Script;

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
			
			// Parse our source code input and generate a script object.
			Script script = Parser.generateScript(sc);
			
			// Manually execute each script statement individually.
			while(script.hasNextStatement()) {
				script.executeNextStatement();
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("error: file " + filePath + " not found");
		}
	}
}

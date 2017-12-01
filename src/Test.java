import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import expression.Value;
import parse.Parser;
import parse.ParserException;
import script.InputProvider;
import script.OutputHandler;
import script.Script;

public class Test {
	
	/**
	 * Program entry point.
	 * @param args
	 */
	public static void main (String[] args) {
		new Test().processFile("src/test.chpz");
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
			Script script = null;
			try {
				script = Parser.generateScript(sc);
			} catch (ParserException e) {
				System.out.println(e.getMessage());
			}

			// Set the input provider for the script.
			script.setInputProvider(new InputProvider() {
				@Override
				public String getInput() {
					// Read input from the console and return it.
					return new Scanner(System.in).nextLine();
				}
			});
			
			// Set the output handler for the script.
			script.setOutputHandler(new OutputHandler() {
				@Override
				public void onPrint(Value value) {}

				@Override
				public void onEnd() {
					System.out.println("Ended!");
				}
			});
			
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

package parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import script.Script;
import script.VariableScope;
import statement.LabelStatement;
import statement.Statement;
import statement.factories.GoToStatementFactory;
import statement.factories.IfStatementFactory;
import statement.factories.InputStatementFactory;
import statement.factories.LetStatementFactory;
import statement.factories.PrintStatementFactory;
import statement.factories.StatementFactory;
import token.Token;
import token.Tokenizer;

/**
 * The language parser.
 * Converts a list of tokens into a list of program statements in order to generate scripts.
 */
public class Parser {
	
	/** The statement factories. */
	@SuppressWarnings("serial")
	private static Map<String, StatementFactory> statementFactories = new HashMap<String, StatementFactory>() {{
		put("print", new PrintStatementFactory());
		put("input", new InputStatementFactory());
		put("let", new LetStatementFactory());
		put("if", new IfStatementFactory());
		put("goto", new GoToStatementFactory());
		// ... TODO Add other factories ...
	}};
	
	/**
	 * Generate a script object, reading source code from a Scanner instance.
	 * @param scanner
	 * @return script
	 */
	public static Script generateScript(Scanner scanner) {
		
		// Label to statement mappings for program labels.
		HashMap<String, Statement> labelPositions = new HashMap<String, Statement>();
		
		// The list of statements.
		ArrayList<Statement> statements = new ArrayList<Statement>();
		
		// The variable scope.
		VariableScope variableScope = new VariableScope();
		
		// Process each line from the scanner input.
		while(scanner.hasNextLine()) {
			
			// Read the next line form the file, this will represent a single statement or label.
			String line = scanner.nextLine();
			
			// Process this line into tokens.
			ArrayList<Token> lineTokens = Tokenizer.processLine(line);
			
			// Do nothing if our token list is empty and move on to the next line.
			if(lineTokens.isEmpty()) {
				continue;
			}
			
			// Get the initial token.
			Token initial = lineTokens.get(0);
			
			// Switch on the initial line token.
			switch(initial.getType()) {
				case KEYWORD:
					// Delegate the responsibility of creating statements to our statement factories.
					statements.add(statementFactories.get(initial.getText()).create(lineTokens, variableScope));
					break;
					
				case LABEL:
					// Get the label name.
					String labelName = initial.getText();
					// Create a placeholder statement for the label, this is only used as a bookmark for this label.
					LabelStatement labelStatement  = new LabelStatement();
					// Add the label statement to the statements list.
					statements.add(labelStatement);
					// Add the label position with a reference to our bookmark label statement.
					labelPositions.put(labelName, labelStatement);
					break;
					
				default:
					// Whoops! We got an unexpected token type. Bum out!
					throw new Error("error: unexpected token type: " + initial.getType());
			}
		}
		
		return new Script(statements, labelPositions, variableScope);
	}
}

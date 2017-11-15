package parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import script.Script;
import statement.Statement;
import statement.factories.IfStatementFactory;
import statement.factories.InputStatementFactory;
import statement.factories.LetStatementFactory;
import statement.factories.PrintStatementFactory;
import statement.factories.StatementFactory;
import token.Token;

/**
 * The language parser.
 * Converts a list of tokens into a list of program statements in order to generate scripts.
 */
public class Parser {
	
	/** Label to line number mappings for program labels. */
	private HashMap<String, Integer> labelPositions = new HashMap<String, Integer>();
	
	/** Line number to statement mappings. */
	private HashMap<Integer, Statement> statements = new HashMap<Integer, Statement>();
	
	/** The statement factories. */
	@SuppressWarnings("serial")
	private static Map<String, StatementFactory> statementFactories = new HashMap<String, StatementFactory>() {{
		put("print", new PrintStatementFactory());
		put("input", new InputStatementFactory());
		put("let", new LetStatementFactory());
		put("if", new IfStatementFactory());
		// ... TODO Add other factories ...
	}};
	
	/**
	 * Process the line tokens to produce a single statement.
	 * @param tokens
	 */
	public void processLineTokens(ArrayList<Token> tokens) {
		
		// Do nothing if our token list is empty.
		if(tokens.isEmpty()) {
			return;
		}
		
		// The index of the next statement to be added.
		int nextStatementIndex = statements.size();
		
		// Get the initial token.
		Token initial = tokens.get(0);
		
		// Switch on the initial line token.
		switch(initial.getType()) {
		
			case KEYWORD:
				// Delegate the responsibility of creating statements to our statement factories.
				statementFactories.get(initial.getText()).create(tokens);
				break;
				
			case LABEL:
				// Get the label name.
				String labelName = initial.getText();
				// Add the label to the statements list. Pass null as the statement and we don't need one.
				statements.put(nextStatementIndex, null);
				// Add the label position.
				labelPositions.put(labelName, nextStatementIndex);
				break;
				
			default:
				// Whoops! We got an unexpected token type. Bum out!
				System.out.println("error: unexpected token type: " + initial.getType());
				break;
		}
		
		// TODO REMOVE Print the tokens to the console.
		for (Token token : tokens) {
			System.out.println(token.getType() + " : " + token.getText());
		}
	}
	
	/**
	 * Generate a script object.
	 * @return
	 */
	public Script generateScript() {
		return null;
	}
}

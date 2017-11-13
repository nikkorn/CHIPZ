package parse;

import java.util.ArrayList;
import java.util.HashMap;
import script.Script;
import statement.Statement;
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
		
		// Switch on the initial line token.
		switch(tokens.get(0).getType()) {
		
			case KEYWORD:
				break;
				
			case LABEL:
				// Get the label name.
				String labelName = tokens.get(0).getText();
				// Add the label to the statements list. Pass null as the statement and we don't need one.
				statements.put(nextStatementIndex, null);
				// Add the label position.
				labelPositions.put(labelName, nextStatementIndex);
				break;
				
			default:
				// Whoops! We got an unexpected token type. Bum out!
				System.out.println("error: unexpected token type: " + tokens.get(0).getType());
				break;
		}
		
		// TODO REMOVE Print the tokens to the console.
		for (Token token : tokens) {
			System.out.println(token.getType() + " : " + token.getText());
		}
	}
	
	public Script generateScript() {
		return null;
	}
}

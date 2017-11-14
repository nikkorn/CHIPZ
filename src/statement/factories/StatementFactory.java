package statement.factories;

import java.util.ArrayList;
import statement.Statement;
import token.Token;
import token.TokenType;

/**
 * A factory which produces a Statement.
 */
public abstract class StatementFactory {
	
	/** The line tokens which make up the statement to create.. */
	ArrayList<Token> tokens;
	
	/** The current token position. */
	int position;
	
	/**
	 * Create a statement based on the tokens provided.
	 * @param tokens
	 * @return statement
	 */
	public Statement create(ArrayList<Token> tokens) {
		this.position = 0;
		this.tokens   = tokens;
		return this.create();
	}
	
	/**
	 * Create and return the statement.
	 * @return statement
	 */
	protected abstract Statement create();
	
	/**
	 * Consume the current token if it has the specified name and type
	 * @param value
	 * @param type
	 */
	protected void consume(String value, TokenType type) {
		Token current = current();
		if (current.getText() == value && current.getType() == type) {
			consume();
		} else {
			throw new Error("Wrong token encountered during parsing. Expected: '" + type + ":" + value + "'");
		}
	}
	
	/**
	 * Get the current token.
	 * @return current token.
	 */
	protected Token current() { return tokens.get(position); }
	
	/**
	 * Consume the current token.
	 * @return current token.
	 */
	protected Token consume() { return tokens.get(position++); }
}

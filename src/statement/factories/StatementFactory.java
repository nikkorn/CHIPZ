package statement.factories;

import java.util.ArrayList;
import script.VariableScope;
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
	 * @param variable scope
	 * @return statement
	 */
	public Statement create(ArrayList<Token> tokens, VariableScope variableScope) {
		this.position = 0;
		this.tokens   = tokens;
		return this.create(variableScope);
	}
	
	/**
	 * Create and return the statement.
	 * @param variable scope
	 * @return statement
	 */
	protected abstract Statement create(VariableScope variableScope);
	
	/**
	 * Consume the current token if it has the specified type
	 * @param type
	 * @return consumed token.
	 */
	protected Token consume(TokenType type) {
		Token current = current();
		if (current.getType() == type) {
			consume();
		} else {
			throw new Error("Wrong token encountered during parsing. Expected: '" + type + "'");
		}
		return current;
	}
	
	/**
	 * Consume the current token if it has the specified name and type
	 * @param value
	 * @param type
	 * @return consumed token.
	 */
	protected Token consume(String value, TokenType type) {
		Token current = current();
		if (current.getText().equals(value) && current.getType() == type) {
			consume();
		} else {
			throw new Error("Wrong token encountered during parsing. Expected: '" + type + ":" + value + "'");
		}
		return current;
	}

	/**
	 * Consume tokens up until finding a token of the specified type, returning a list of the consumed tokens.
	 * @param type
	 * @return consumed tokens.
	 */
	protected ArrayList<Token> consumeUntil(TokenType type) {
		ArrayList<Token> consumed = new ArrayList<Token>();
		// While we have tokens to consume...
		while(hasTokensLeft()) {
			// ... Get the next token ...
			Token current = current();
			// ... If the type of the token matches the type we were supposed to consume up to then ...
			if (current.getType() == type) {
				// ... Stop gathering tokens ...
				break;
			} else {
				// ... Otherwise consume the next token and add it to the returning list.
				consumed.add(consume());
			}
		}
		// Return the list of consumed tokens. 
		return consumed;
	}
	
	/**
	 * Consume tokens up until finding a token of the specified type and value, returning a list of the consumed tokens.
	 * @param value
	 * @param type
	 * @return consumed tokens.
	 */
	protected ArrayList<Token> consumeUntil(String value, TokenType type) {
		ArrayList<Token> consumed = new ArrayList<Token>();
		// While we have tokens to consume...
		while(hasTokensLeft()) {
			// ... Get the next token ...
			Token current = current();
			// ... If the type of the token matches the type we were supposed to consume up to then ...
			if (current.getText().equals(value) && current.getType() == type) {
				// ... Stop gathering tokens ...
				break;
			} else {
				// ... Otherwise consume the next token and add it to the returning list.
				consumed.add(consume());
			}
		}
		// Return the list of consumed tokens. 
		return consumed;
	}
	
	/**
	 * Consume the rest of the tokens, returning a list of the consumed tokens.
	 * @return consumed tokens.
	 */
	protected ArrayList<Token> consumeRest() {
		ArrayList<Token> consumed = new ArrayList<Token>();
		// While we have tokens to consume...
		while(hasTokensLeft()) {
			// ... Consume the next token and add it to the returning list.
			consumed.add(consume());
		}
		// Return the list of consumed tokens. 
		return consumed;
	}
	
	/**
	 * Gets whether there are more tokens to consume.
	 * @return has tokens left.
	 */
	protected boolean hasTokensLeft() { return position <= (tokens.size() - 1); }
	
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

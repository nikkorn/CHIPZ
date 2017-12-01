package statement.factories;

import parse.InvalidStatementException;
import script.VariableScope;
import statement.InputStatement;
import statement.Statement;
import token.Token;
import token.TokenType;

/**
 * Statement factory that produces an INPUT statement.
 */
public class InputStatementFactory extends StatementFactory {

	@Override
	public Statement create(VariableScope variableScope) throws InvalidStatementException {
		
		// Consume our initial token.
		consume();
		
		// Consume the next token which should be a variable identifier. 
		Token variableToken = consume(TokenType.IDENTIFIER);
		
		return new InputStatement(variableToken.getText(), variableScope);
	}
}
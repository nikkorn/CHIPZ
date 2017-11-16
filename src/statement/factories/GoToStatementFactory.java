package statement.factories;

import statement.GoToStatement;
import statement.Statement;
import token.Token;
import token.TokenType;

/**
 * Statement factory that produces a GOTO statement.
 */
public class GoToStatementFactory extends StatementFactory {

	@Override
	public Statement create() {
		
		// Consume our initial token.
		consume();
		
		// Consume the next token which should be an identifier operator, this is actually a label reference. 
		Token labelReference = consume(TokenType.IDENTIFIER);
		
		return new GoToStatement(labelReference.getText());
	}
}
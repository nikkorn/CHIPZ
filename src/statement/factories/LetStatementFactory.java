package statement.factories;

import expression.Expression;
import parse.ExpressionBuilder;
import statement.LetStatement;
import statement.Statement;
import token.Token;
import token.TokenType;

/**
 * Statement factory that produces an LET statement.
 */
public class LetStatementFactory extends StatementFactory {

	@Override
	public Statement create() {
		
		// Consume our initial token.
		consume();
		
		// Consume the next token which should be a variable identifier. 
		Token variableToken = consume(TokenType.IDENTIFIER);
		
		// Consume the next token which should be an assignment operator. 
		consume(TokenType.ASSIGNMENT);
		
		// Consume the rest of the tokens into an expression.
		Expression expression = ExpressionBuilder.build(consumeRest());
		
		return new LetStatement(variableToken.getText(), expression);
	}
}
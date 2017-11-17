package statement.factories;

import expression.Expression;
import parse.ExpressionBuilder;
import statement.IfStatement;
import statement.Statement;
import token.Token;
import token.TokenType;

/**
 * Statement factory that produces an IF statement.
 */
public class IfStatementFactory extends StatementFactory {

	@Override
	public Statement create() {
		
		// Consume our initial token.
		consume();
		
		// Consume the tokens which should make up a conditional expression, up until the 'then' keyword.
		Expression condition = ExpressionBuilder.build(consumeUntil("then", TokenType.KEYWORD));
		
		// Consume our 'then' keyword.
		consume("then", TokenType.KEYWORD);
		
		// Consume the next token which should be an identifier operator, this is actually a label reference. 
		Token labelReference = consume(TokenType.IDENTIFIER);
		
		return new IfStatement(labelReference.getText(), condition);
	}
}
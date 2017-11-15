package statement.factories;

import java.util.ArrayList;
import expression.Expression;
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
		ArrayList<Token> expressionTokens = consumeUntil("then", TokenType.KEYWORD);
		Expression condition              = null;
		
		// Consume our 'then' keyword.
		consume("then", TokenType.KEYWORD);
		
		// Consume the next token which should be an identifier operator, this is actually a label reference. 
		Token labelReference = consume(TokenType.IDENTIFIER);
		
		return new IfStatement(labelReference.getText(), condition);
	}
}
package statement.factories;

import java.util.ArrayList;

import statement.PrintStatement;
import statement.Statement;
import token.Token;

/**
 * Statement factory that produces a PRINT statement.
 */
public class PrintStatementFactory extends StatementFactory {

	@Override
	public Statement create() {
		
		// Consume our initial token.
		consume();
		
		// Consume the remaining tokens which should make up an expression, the result of which we will be printing.
		ArrayList<Token> expressionTokens = consumeRest();
		
		// TODO Auto-generated method stub
		return new PrintStatement(null);
	}
}
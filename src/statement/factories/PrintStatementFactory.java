package statement.factories;

import expression.Expression;
import parse.ExpressionBuilder;
import statement.PrintStatement;
import statement.Statement;

/**
 * Statement factory that produces a PRINT statement.
 */
public class PrintStatementFactory extends StatementFactory {

	@Override
	public Statement create() {
		
		// Consume our initial token.
		consume();
		
		// Consume the rest of the tokens into an expression, the result of which we will be printed.
		Expression expression = ExpressionBuilder.build(consumeRest());
		
		return new PrintStatement(expression);
	}
}
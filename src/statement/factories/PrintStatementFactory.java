package statement.factories;

import expression.Expression;
import parse.ExpressionBuilder;
import parse.InvalidExpressionException;
import parse.InvalidStatementException;
import script.VariableScope;
import statement.PrintStatement;
import statement.Statement;

/**
 * Statement factory that produces a PRINT statement.
 */
public class PrintStatementFactory extends StatementFactory {

	@Override
	public Statement create(VariableScope variableScope) throws InvalidStatementException, InvalidExpressionException {
		
		// Consume our initial token.
		consume();
		
		// Consume the rest of the tokens into an expression, the result of which we will be printed.
		Expression expression = new ExpressionBuilder(variableScope).build(consumeRest());
		
		return new PrintStatement(expression);
	}
}
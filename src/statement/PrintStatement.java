package statement;

import expression.Expression;

/**
 * The PRINT statement.
 * Evaluates an expression and prints the result.
 */
public class PrintStatement implements Statement {
	
	/** The expression which produces the value to print. */
	private Expression expression;
	
	/**
	 * Create a new instance of the PrintStatement class.
	 * @param expression
	 */
	public PrintStatement(Expression expression) { this.expression = expression; }

	@Override
	public void execute() {
		System.out.println(expression.evaluate().toString());
	}
}

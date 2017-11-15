package statement;

import expression.Expression;

/**
 * The LET statement.
 * Assigns the result of an expression to a variable.
 */
public class LetStatement implements Statement {
	
	/** The variable name. */
	private String variable;
	
	/** The expression. */
	private Expression expression;
	
	/**
	 * Create a new instance of the LetStatement class.
	 * @param variable
	 * @param expression
	 */
	public LetStatement(String variable, Expression expression) { 
		this.variable   = variable; 
		this.expression = expression;
	}

	@Override
	public void execute() {
		System.out.println("Assigning: " + expression.evaluate() + " to variable: " + variable);
	}
}

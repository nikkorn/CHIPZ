package statement;

import expression.Expression;

/**
 * The IF statement.
 * Moves processing to a specified label if its conditional expression resolves to anything other than 0.
 */
public class IfStatement implements Statement {
	
	/** The target label. */
	private String label;
	
	/** The conditional expression. */
	private Expression condition;
	
	/**
	 * Create a new instance of the IfStatement class.
	 * @param label
	 * @param condition
	 */
	public IfStatement(String label, Expression condition) { 
		this.label     = label; 
		this.condition = condition;
	}

	@Override
	public void execute() {
		System.out.println("If condition result != 0 could move to : " + label);
	}
}

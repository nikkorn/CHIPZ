package statement;

import expression.Expression;
import script.Script;

/**
 * The IF statement.
 * Moves processing to a specified label if its conditional expression resolves to anything other than 0.
 */
public class IfStatement implements Statement {
	
	/** The target label to move processing to if the condition is truthy. */
	private String thenLabel;
	
	/** The target label to move processing to if the condition is falsy. */
	private String elseLabel;
	
	/** The conditional expression. */
	private Expression condition;
	
	/**
	 * Create a new instance of the IfStatement class.
	 * @param thenLabel the label to move processing to if the condition is truthy
	 * @param elseLabel the label to move processing to if the condition is falsy
	 * @param condition
	 */
	public IfStatement(String thenLabel, String elseLabel, Expression condition) { 
		this.thenLabel = thenLabel; 
		this.elseLabel = elseLabel;
		this.condition = condition;
	}

	@Override
	public void execute(Script executor) {
		// Evaluate whether the condition evaluates to 'true'. 
		if (condition.evaluate().isTruthy()) {
			// Move processing to the specified then label.
			executor.setNextStatement(this.thenLabel);
		} else if (elseLabel != null) {
			// If an else label has been defined then move processing to this instead.
			executor.setNextStatement(this.elseLabel);
		}
	}
}

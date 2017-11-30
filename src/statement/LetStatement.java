package statement;

import expression.Expression;
import script.Script;
import script.VariableScope;

/**
 * The LET statement.
 * Assigns the result of an expression to a variable.
 */
public class LetStatement extends Statement {
	
	/** The variable name. */
	private String variable;
	
	/** The expression. */
	private Expression expression;
	
	/** The target variable scope to assign values to. */
	private VariableScope variableScope;
	
	/**
	 * Create a new instance of the LetStatement class.
	 * @param variable
	 * @param expression
	 * @param variable scope
	 */
	public LetStatement(String variable, Expression expression, VariableScope variableScope) { 
		this.variable      = variable; 
		this.expression    = expression;
		this.variableScope = variableScope;
	}

	@Override
	public void execute(Script executor) { variableScope.set(variable, expression.evaluate()); }
}

package expression;

import script.VariableScope;

/**
 * A reference to a value in the global variable scope.
 */
public class Variable implements Expression {
	
	/** The target variable scope. */
	private VariableScope variableScope;
	
	/** The variable name. */
	private String name;
	
	/**
	 * Creates a new instance of the Variable class.
	 * @param name the name of the variable
	 * @param variableScope the variable scope for the application
	 */
	public Variable(String name, VariableScope variableScope) {
		this.name          = name; 
		this.variableScope = variableScope;
	}

	@Override
	public Value evaluate() { return this.variableScope.get(this.name); }
}

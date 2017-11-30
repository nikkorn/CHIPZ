package statement;

import expression.Value;
import script.InputProvider;
import script.Script;
import script.VariableScope;

/**
 * The INPUT statement.
 * Reads input into a variable.
 */
public class InputStatement extends Statement {
	
	/** The variable name. */
	private String variable;

	/** The target variable scope to assign input values to. */
	private VariableScope variableScope;
	
	/**
	 * Create a new instance of the InputStatement class.
	 * @param variable
	 * @param variableScope
	 */
	public InputStatement(String variable, VariableScope variableScope) {
		this.variable      = variable;
		this.variableScope = variableScope;
	}

	@Override
	public void execute(Script executor) {
		// Get the input provider for the script.
		InputProvider inputProvider = executor.getInputProvider();
		// If an input provider has not been defined then we cannot continue.
		if (inputProvider == null) {
			throw new Error("error: no input provider has been defined");
		}
		// Get input from the input provider.
		String input = inputProvider.getInput();
		// Deduce the data type of the input by attempting to parse it as a number.
		try {
			Double value = Double.parseDouble(input);
			// We were able to parse the input as a double, treat it as numerical input.
			variableScope.set(variable, new Value(value));
		}
		catch (NumberFormatException e) {
			// The input is not a number as we failed to parse it as a double, treat it as a string.
			variableScope.set(variable, new Value(input));
		}
	}
}

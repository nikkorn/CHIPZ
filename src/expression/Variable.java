package expression;

/**
 * A reference to a value in the global variable scope.
 */
public class Variable implements Expression {
	
	/** The variable name. */
	private String name;
	
	/**
	 * Creates a new instance of the Variable class.
	 * @param name
	 */
	public Variable(String name) { this.name = name; }

	@Override
	public Value evaluate() {
		// TODO Fetch value from global variable scope and return it.
		return null;
	}
}

package expression;

/**
 * Operators to use in operations.
 */
public enum Operator {
	MULTIPLY(6),
	DIVIDE(6),
	ADD(5),
	SUBTRACT(5),
	LESS_THAN(4),
	GREATER_THAN(4),
	LESS_THAN_OR_EQUAL(4),
	GREATER_THAN_OR_EQUAL(4),
	EQUALS(3);
	
	/** The operator precedence. */
	private int precedence;
	
	/**
	 * Create a new Operator enumeration.
	 * @param precedence
	 */
	private Operator(int precedence) { this.precedence = precedence; }
	
	/**
	 * Get the precedence of the operator.
	 * @return precedence
	 */
	public int getPrecedence() { return this.precedence; }
	
	/**
	 * Gets whether this operator precedes (has higher precedence) than the specified operator.
	 * @param subject
	 * @return Whether this operator precedes the specified operator
	 */
	public boolean precedes(Operator subject) { return this.precedence > subject.getPrecedence(); }
}
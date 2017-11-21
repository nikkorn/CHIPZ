package expression;

/**
 * Operators to use in operations.
 */
public enum Operator {
	MULTIPLY(6, "*"),
	DIVIDE(6, "/"),
	ADD(5, "+"),
	SUBTRACT(5, "-"),
	LESS_THAN(4, "<"),
	GREATER_THAN(4, ">"),
	LESS_THAN_OR_EQUAL(4, "<="),
	GREATER_THAN_OR_EQUAL(4, ">="),
	EQUALS(3, "==");
	
	/** The operator precedence. */
	private String rawOperator;
	
	/** The raw operator string. */
	private int precedence;
	
	/**
	 * Create a new Operator enumeration.
	 * @param precedence
	 * @param rawOperator
	 */
	private Operator(int precedence, String rawOperator) { 
		this.precedence  = precedence;
		this.rawOperator = rawOperator;
	}
	
	/**
	 * Gets the operator enumeration which matches the raw operator string.
	 * @param raw
	 * @return operator
	 */
	public static Operator getEnum(String raw) {
        for(Operator operator : values()) {
        	if(operator.getRawOperator().equals(raw)) {
        		return operator;
        	}
        }
        throw new Error("error: unexpected operator value: " + raw);
    }
	
	/**
	 * Get the raw operator string.
	 * @return raw operator string
	 */
	public String getRawOperator() { return this.rawOperator; }
	
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
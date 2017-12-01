package parse;

/**
 * An exception thrown in response to an issue parsing an expression.
 */
public class InvalidExpressionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of the InvalidExpressionException class.
	 * @param message
	 */
	public InvalidExpressionException(String message) { super(message); }
}

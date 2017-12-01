package parse;

/**
 * An exception thrown in response to an issue parsing a statement.
 */
public class InvalidStatementException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of the InvalidStatementException class.
	 * @param message
	 */
	public InvalidStatementException(String message) { super(message); }
}

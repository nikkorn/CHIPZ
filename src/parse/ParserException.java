package parse;

/**
 * An exception raised during script parsing.
 */
public class ParserException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a new instance of the ParserException class.
	 * @param message
	 */
	public ParserException(String message) {
        super(message);
    }
}

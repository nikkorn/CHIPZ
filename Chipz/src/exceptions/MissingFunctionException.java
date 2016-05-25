package exceptions;

/**
 * 
 * @author Nikolas Howard
 *
 */
public class MissingFunctionException extends Exception {
	private static final long serialVersionUID = 1L;

	public MissingFunctionException(String message) {
        super(message);
    }
}

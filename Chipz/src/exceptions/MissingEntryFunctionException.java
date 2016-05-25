package exceptions;

/**
 * 
 * @author Nikolas Howard
 *
 */
public class MissingEntryFunctionException extends Exception {
	private static final long serialVersionUID = 1L;

	public MissingEntryFunctionException(String message) {
        super(message);
    }
}

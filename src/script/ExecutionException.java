package script;

/**
 * An exception raised during script runtime.
 */
public class ExecutionException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a new instance of the ScriptRuntimeException class.
	 * @param message
	 */
	public ExecutionException(String message) {
        super(message);
    }
}

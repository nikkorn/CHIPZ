package script;

/**
 * An exception raised during script runtime.
 */
public class ScriptRuntimeException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a new instance of the ScriptRuntimeException class.
	 * @param message
	 */
	public ScriptRuntimeException(String message) {
        super(message);
    }
}

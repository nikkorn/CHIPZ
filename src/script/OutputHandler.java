package script;

/**
 * An interface which defines a handler for script output.
 */
public interface OutputHandler {

    /**
     * Called in response to the execution of a PRINT statement.
     * @param value the printed value
     */
    void onPrint(String value);
}

package script;

import expression.Value;

/**
 * An interface which defines a handler for script output.
 */
public interface OutputHandler {

    /**
     * Called in response to the execution of a PRINT statement.
     * @param value the printed value
     */
    void onPrint(Value value);
    
    /**
     * Signifies that the script has finished executing.
     * Called in response to either:
     *     - The execution of an END statement.
     *     - There being no remaining statements to execute.
     */
    void onEnd();
}

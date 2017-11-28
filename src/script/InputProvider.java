package script;

/**
 * An interface which defines an input provider for an executing script.
 */
public interface InputProvider {

    /**
     * Provide input in response to executing an INPUT statement.
     * @return input
     */
    String getInput();
}

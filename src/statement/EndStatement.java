package statement;

import script.Script;

/**
 * The END statement.
 * Stops the executing script.
 */
public class EndStatement extends Statement {

	@Override
	public void execute(Script executor) {
		// Stop the currently executing script.
		executor.stop();
	}
}

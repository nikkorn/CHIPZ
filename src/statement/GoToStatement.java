package statement;

import script.Script;

/**
 * The GOTO statement.
 * Moves processing to a specified label.
 */
public class GoToStatement extends Statement {
	
	/** The target label. */
	private String label;
	
	/**
	 * Create a new instance of the GoToStatement class.
	 * @param label
	 */
	public GoToStatement(String label) { 
		this.label = label; 
	}

	@Override
	public void execute(Script executor) {
		// Move processing to the specified label.
		executor.setNextStatement(this.label);
	}
}

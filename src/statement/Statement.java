package statement;

import script.Script;

/**
 * Represents a single line statement.
 */
public abstract class Statement {
	
	/** The line number of the statement in the script source. */
	private int lineNumber;
	
	/**
	 * Execute the statement.
	 * @param executor The script executing the statement.
	 */
	public abstract void execute(Script executor);

	/**
	 * Get the line number of the statement in the script source.
	 * @return line number.
	 */
	public int getLineNumber() { return lineNumber; }
	
	/**
	 * Set the line number of the statement in the script source.
	 * @param line number.
	 */
	public void setLineNumber(int lineNumber) { this.lineNumber = lineNumber; }
}

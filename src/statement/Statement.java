package statement;

import script.Script;

/**
 * Represents a single line statement.
 */
public interface Statement {
	
	/**
	 * Execute the statement.
	 * @param executor The script executing the statement.
	 */
	public void execute(Script executor);
}

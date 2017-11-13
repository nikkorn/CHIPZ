package statement;

/**
 * Represents a single line statement.
 */
public class Statement {
	
	/** The line number of the statement. */
	private int lineNumber;
	
	/** The statement type. */
	private StatementType type;
	
	/**
	 * Create a new instance of the statement class.
	 * @param lineNumber
	 * @param type
	 */
	public Statement(int lineNumber, StatementType type) {
		this.lineNumber = lineNumber;
		this.type       = type;
	}
	
	/**
	 * Gets the type of this statement.
	 * @return type
	 */
	public StatementType getType() { return this.type; }
	
	/**
	 * Gets the line number of this statement.
	 * @return line number.
	 */
	public int getLineNumber() { return this.lineNumber; }
}

package statement;

/**
 * The GOTO statement.
 * Moves processing to a specified label.
 */
public class GoToStatement implements Statement {
	
	/** The target label. */
	private String label;
	
	/**
	 * Create a new instance of the GoToStatement class.
	 * @param label
	 */
	public GoToStatement(String label) { this.label = label; }

	@Override
	public void execute() {
		System.out.println("Go to label : " + label);
	}
}

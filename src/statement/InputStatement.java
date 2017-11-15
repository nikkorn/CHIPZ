package statement;

/**
 * The INPUT statement.
 * Reads input into a variable.
 */
public class InputStatement implements Statement {
	
	/** The variable name. */
	private String variable;
	
	/**
	 * Create a new instance of the InputStatement class.
	 * @param variable
	 */
	public InputStatement(String variable) { this.variable = variable; }

	@Override
	public void execute() {
		System.out.println("Read input into variable : " + variable);
	}
}

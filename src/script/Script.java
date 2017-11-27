package script;

import java.util.ArrayList;
import java.util.HashMap;
import statement.Statement;

/**
 * An executable script.
 */
public class Script {
	
	/** Label to statement mappings for program labels. */
	private HashMap<String, Statement> labelPositions;
	
	/** The list of script statements. */
	private ArrayList<Statement> statements;
	
	/** The variable scope. */
	private VariableScope variableScope;
	
	/** The index of the next statement to execute. */
	private int nextStatementIndex = 0;
	
	/**
	 * Create a new instance of the Script class.
	 * @param statements
	 * @param labelPositions
	 * @param variableScope
	 */
	public Script(ArrayList<Statement> statements, HashMap<String, Statement> labelPositions, VariableScope variableScope) {
		this.statements     = statements;
		this.labelPositions = labelPositions;
		this.variableScope  = variableScope;
	}
	
	/**
	 * Execute the next program statement.
	 * If no statement is available an error will be thrown.
	 */
	public void executeNextStatement() {
		// We can only execute the next statement if we actually have one.
		if (this.hasNextStatement()) {
			// Get and execute the next statement.
			this.statements.get(nextStatementIndex++).execute(this);
		} else {
			throw new Error("error: next statement not available");
		}
	}
	
	/**
	 * Set the next statement to be executed by label.
	 * @param label
	 */
	public void setNextStatement(String label) {
		// Does this label exist?
		if (labelPositions.containsKey(label)) {
			// Get the program statement this label refers to.
			Statement target = labelPositions.get(label);
			// Set the index of the next statement to execute to be the target one.
			this.nextStatementIndex = statements.indexOf(target);
		} else {
			throw new Error("error: label'" + label + "' cannot be found");
		}
	}
	
	/**
	 * Set the next statement to be executed by statement index.
	 * @param statement index
	 */
	public void setNextStatement(int index) {
		// Does this statement exist?
		if ((index <= (this.statements.size() - 1)) && (index >= 0)) {
			// Set the index of the next statement to be the specified one.
			this.nextStatementIndex = index;
		} else {
			throw new Error("error: no statement at index: " + index);
		}
	}
	
	/**
	 * Returns whether there is another statement to execute.
	 * @return whether there is another statement to execute.
	 */
	public boolean hasNextStatement() { return this.nextStatementIndex <= (this.statements.size() - 1); }
	
	/**
	 * Reset the script.
	 */
	public void reset() { this.nextStatementIndex = 0; }
}

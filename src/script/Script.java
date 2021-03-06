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

	/** The input provider responsible for providing input when processing an INPUT statement. */
	private InputProvider inputProvider = null;

	/** The output handler responsible for handling program output. */
	private OutputHandler outputHandler = null;
	
	/** The index of the next statement to execute. */
	private int nextStatementIndex = 0;
	
	/** The flag defining whether the script has stopped. */
	private boolean isStopped = false;
	
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
			// If this is the last available statement then call stop.
			if (!this.hasNextStatement()) {
				this.stop();
			}
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
	 * @param index
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
	 * Set the input provider.
	 * The input provider is responsible for providing input when processing an INPUT statement.
	 * @param inputProvider
     */
	public void setInputProvider(InputProvider inputProvider) { this.inputProvider = inputProvider; }

	/**
	 * Get the input provider.
	 * The input provider is responsible for providing input when processing an INPUT statement.
	 * @return input provider
     */
	public InputProvider getInputProvider() { return this.inputProvider; }

	/**
	 * Set the output handler.
	 * The output handler is responsible for handling program output.
	 * @param outputHandler
     */
	public void setOutputHandler(OutputHandler outputHandler) { this.outputHandler = outputHandler; }

	/**
	 * Get the output handler.
	 * The output handler is responsible for handling program output.
	 * @return output handler
     */
	public OutputHandler getOutputHandler() { return this.outputHandler; }

	/**
	 * Get the variable scope.
	 * @return variable scope.
	 */
	public VariableScope getVariableScope() { return variableScope; }
	
	/**
	 * Stop the currently executing script.
	 * This is done by moving processing to an unavailable statement.
	 */
	public void stop() { 
		// Do nothing if the script has already stopped.
		if (!this.isStopped) {
			// Move processing to an unavailable statement.
			this.nextStatementIndex = this.statements.size(); 
			// If we have an output handler then let it know we are done.
			if (this.outputHandler != null) {
				this.outputHandler.onEnd();
			}
			this.isStopped = true;
		}
	}
}

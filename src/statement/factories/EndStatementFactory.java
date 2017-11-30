package statement.factories;

import script.VariableScope;
import statement.EndStatement;
import statement.Statement;

/**
 * Statement factory that produces an END statement.
 */
public class EndStatementFactory extends StatementFactory {

	@Override
	public Statement create(VariableScope variableScope) {
		
		// Consume our initial token.
		consume();
		
		return new EndStatement();
	}
}
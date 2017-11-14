package statement.factories;

import statement.PrintStatement;
import statement.Statement;

/**
 * Statement factory that produces a PRINT statement.
 */
public class PrintStatementFactory extends StatementFactory {

	@Override
	public Statement create() {
		
		// TODO Auto-generated method stub
		return new PrintStatement(null);
	}
}
package statement.factories;

import expression.Expression;
import parse.ExpressionBuilder;
import parse.InvalidExpressionException;
import parse.InvalidStatementException;
import script.VariableScope;
import statement.IfStatement;
import statement.Statement;
import token.Token;
import token.TokenType;

/**
 * Statement factory that produces an IF statement.
 */
public class IfStatementFactory extends StatementFactory {

	@Override
	public Statement create(VariableScope variableScope) throws InvalidExpressionException, InvalidStatementException {
		
		// Consume our initial token.
		consume();
		
		// Consume the tokens which should make up a conditional expression, up until the 'then' keyword.
		Expression condition = new ExpressionBuilder(variableScope).build(consumeUntil("then", TokenType.KEYWORD));
		
		// Consume our 'then' keyword.
		consume("then", TokenType.KEYWORD);
		
		// Consume the next token which should be an identifier operator. This is actually
		// a reference to a label to move processing to if the the condition is truthy.
		Token thenLabelReference = consume(TokenType.IDENTIFIER);
		
		// IF statements allow an optional 'else'.
		if (this.hasTokensLeft()) {
			
			// Consume our 'else' keyword.
			consume("else", TokenType.KEYWORD);
			
			// Consume the next token which should be an identifier operator. This is actually
			// a reference to a label to move processing to if the the condition is falsy.
			Token elseLabelReference = consume(TokenType.IDENTIFIER);
			
			// We are creating an IF statement with an the optional 'else'
			return new IfStatement(thenLabelReference.getText(), elseLabelReference.getText(), condition);
		} else {
			
			// We are creating an IF statement without an the optional 'else'
			return new IfStatement(thenLabelReference.getText(), null, condition);
		}
	}
}
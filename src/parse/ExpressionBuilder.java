package parse;

import java.util.ArrayList;
import expression.Expression;
import expression.Value;
import token.Token;

public class ExpressionBuilder {
	
	/**
	 * Build an expression using infix ordered (NUMBER,STRING,IDENTIFIER,OPERATOR) tokens.
	 * @param expressionTokens
	 * @return expression
	 */
	public static Expression build(ArrayList<Token> expressionTokens) {
		
		System.out.println("------ Building Expression From ------- ");
		for (Token token : expressionTokens) {
			System.out.println(token.getType() + " : " + token.getText());
		}
		System.out.println("---------------------------------------");
		
		// TODO Remove Create a fake placeholder expression which returns a value for now.
		Expression placeholder = new Expression() {
			@Override
			public Value evaluate() { return new Value(1); }
		};
		
		return placeholder;
	}
}

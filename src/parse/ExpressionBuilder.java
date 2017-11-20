package parse;

import java.util.ArrayList;
import java.util.Stack;

import expression.Expression;
import expression.Operator;
import expression.Value;
import token.Token;
import token.TokenType;

public class ExpressionBuilder {
	
	/**
	 * Build an expression using infix ordered (NUMBER,STRING,IDENTIFIER,OPERATOR,LEFT_PAREN,RIGHT_PAREN) tokens.
	 * @param expressionTokens
	 * @return expression
	 */
	public static Expression build(ArrayList<Token> expressionTokens) {
		
		// Our tokens will be in infix form, convert them to postfix before building our expressions. 
		expressionTokens = ExpressionBuilder.convertExpressionTokensToPostfix(expressionTokens);
		
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
	
	/**
	 * Takes a infix ordered list of expression tokens and converts them to postfix.
	 * @param expressionTokens
	 * @return postfix ordered tokens
	 */
	private static ArrayList<Token> convertExpressionTokensToPostfix(ArrayList<Token> expressionTokens) {
		
		// The output queue.
		ArrayList<Token> outputQueue = new ArrayList<Token>();
		
		// The operator stack.
		Stack<Token> operatorStack = new Stack<Token>();
		
		for (Token token : expressionTokens) {
			// What we do with this token is based on its type.
			switch (token.getType()) {
				case OPERATOR:
					// While there are operators on the operator stack, and the current token operator
	                // has higher or equal precedence than the top operator on the stack, pop the top 
	                // operator on the stack onto the output queue.
					while (!operatorStack.isEmpty() && ExpressionBuilder.stackHasHigherPrecedenceOperators(token, operatorStack)) {
						
					}
					// Push this operator onto the stack.
					operatorStack.push(token);
					break;
					
				case LEFT_PAREN:
					break;
					
				case RIGHT_PAREN:
					break;
					
				default:
					// This is an operand. Add it to the output queue.
					outputQueue.add(token);
					break;
			
			}
		}	
		
		return outputQueue;
	}
	
	/**
	 * Gets whether the operator token stack has a top operator which precedes the specified operator.
	 * @param operator
	 * @param stack
	 * @return has higher precedence operators.
	 */
	private static boolean stackHasHigherPrecedenceOperators(Token operator, Stack<Token> stack) {
		// Check whether the stack is empty.
		if (stack.isEmpty()) {
			return false;
		} else {
			// Get the operator at the top of the stack.
			Token top = stack.peek();
			// Return whether the specified operate has higher precedence than the one at the top of the stack.
			return Operator.valueOf(top.getText()).getPrecedence() >  Operator.valueOf(operator.getText()).getPrecedence();
		}
	}
}

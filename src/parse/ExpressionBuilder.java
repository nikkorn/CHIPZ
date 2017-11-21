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
		
		System.out.println("------ Building Expression From ------- ");
		
		for (Token token : expressionTokens) {
			System.out.println(token.getType() + " : " + token.getText());
		}
		
		// Our tokens will be in infix form, convert them to postfix before building our expressions. 
		expressionTokens = ExpressionBuilder.convertExpressionTokensToPostfix(expressionTokens);
		
		System.out.println("-------- Converted to postfix ----------");
		
		for (Token token : expressionTokens) {
			System.out.println(token.getType() + " : " + token.getText());
		}
		
		System.out.println("----------------------------------------");
		
		// TODO Remove Create a fake placeholder expression which returns a value for now.
		Expression placeholder = new Expression() {
			@Override
			public Value evaluate() { return new Value(1); }
		};
		
		return placeholder;
	}
	
	/**
	 * Takes a infix ordered list of expression tokens and converts them to post-fix.
	 * @param expressionTokens
	 * @return post-fix ordered tokens
	 */
	private static ArrayList<Token> convertExpressionTokensToPostfix(ArrayList<Token> expressionTokens) {
		
		// The output queue.
		ArrayList<Token> outputQueue = new ArrayList<Token>();
		
		// The operator stack.
		Stack<Token> operatorStack = new Stack<Token>();
		
		// Iterate over the expression tokens, sorting them into the output queue in postfix form.
		for (Token token : expressionTokens) {
			// What we do with this token is based on its type.
			switch (token.getType()) {
				case OPERATOR:
					// While there are operators on the operator stack, and the current token operator
	                // has higher or equal precedence than the top operator on the stack, pop the top 
	                // operator on the stack onto the output queue.
					while (!operatorStack.isEmpty() && ExpressionBuilder.stackHasHigherOrEqualPrecedenceOperators(token, operatorStack)) {
						outputQueue.add(operatorStack.pop());
					}
					// Push this operator onto the stack.
					operatorStack.push(token);
					break;
					
				case LEFT_PAREN:
					operatorStack.push(token);
					break;
					
				case RIGHT_PAREN:
					// While there are operators on the operator stack, pop them into the output
					// queue until we find the opening left parenthesis.
					while(!operatorStack.isEmpty() && (operatorStack.peek().getType() != TokenType.LEFT_PAREN)) {
						outputQueue.add(operatorStack.pop());
					}
					// Check for opening parenthesis.
					if (operatorStack.isEmpty()) {
						// We never found our opening left bracket! We simply cannot go on.
						System.out.println("error: malformed parenthesis in expression.");
					} else {
						// Get rid of the opening parenthesis!
						operatorStack.pop();
					}
					break;
					
				default:
					// This is an operand. Add it to the output queue.
					outputQueue.add(token);
					break;
			
			}
		}	
		
		// Pop remaining operators off the stack into the output queue.
		while (!operatorStack.isEmpty()) {
			outputQueue.add(operatorStack.pop());
		}
		
		// Return the output queue, which contains out postfix ordered tokens.
		return outputQueue;
	}
	
	/**
	 * Gets whether the operator token stack has a top operator which precedes or has matching precedence than the specified operator.
	 * @param operator
	 * @param stack
	 * @return has higher or matching precedence operators.
	 */
	private static boolean stackHasHigherOrEqualPrecedenceOperators(Token operator, Stack<Token> stack) {
		// Check whether the stack is empty or whether the top token is a '('.
		if (stack.isEmpty() || stack.peek().getType() == TokenType.LEFT_PAREN) {
			return false;
		} else {
			// Get the operator at the top of the stack.
			Token top = stack.peek();
			// Return whether the specified operate has higher precedence than the one at the top of the stack.
			return Operator.getEnum(top.getText()).getPrecedence() >=  Operator.getEnum(operator.getText()).getPrecedence();
		}
	}
}

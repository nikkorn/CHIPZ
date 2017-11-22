package parse;

import java.util.ArrayList;
import java.util.Stack;
import expression.Expression;
import expression.Operation;
import expression.Operator;
import expression.Value;
import expression.Variable;
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
		
		// Convert our post-fix ordered tokens into a single expression and return it.
		return ExpressionBuilder.convertPostfixTokens(expressionTokens);
	}
	
	/**
	 * Convert a list of post-fix ordered expression tokens into a single expression.
	 * @param expressionTokens
	 * @return expression
	 */
	private static Expression convertPostfixTokens(ArrayList<Token> tokens) {
		// Handle cases where we only have a single token, this should be either a string, number or variable identifier.
		if (tokens.size() == 1) {
			return ExpressionBuilder.createFromToken(tokens.get(0));
		} else if (getNextOperatorIndex(tokens) != -1) {
		
			Expression current           = null;
			boolean isLastExpressionLeft = false;
			
			while (ExpressionBuilder.getNextOperatorIndex(tokens) != -1) {
				
				// Get the index of the next operator.
				int operatorIndex = getNextOperatorIndex(tokens);
				
				// Get the operator token.
				Token operator = tokens.get(operatorIndex);
				
				// If current is null then this is the first expression we are building.
				if (current == null) {
					
					// Get the operand tokens.
					Token leftOperand  = tokens.get(operatorIndex - 2);
					Token rightOperand = tokens.get(operatorIndex - 1);
					
					// If we have another token after the operator then how the current expression 
					// is nested within (left or right) the next operation depends on its type. 
					isLastExpressionLeft = ExpressionBuilder.getTokenType(tokens, operatorIndex + 1) != TokenType.OPERATOR;
					
					// Create an operation expression using the operator and operands.
					current = new Operation(ExpressionBuilder.createFromToken(leftOperand), ExpressionBuilder.createFromToken(rightOperand), Operator.getEnum(operator.getText()));
					
					// Remove the operands from the token list.
					tokens.remove(leftOperand);
					tokens.remove(rightOperand);
				} else {
					
					// Get the operand token, our other operand is the current expression.
					Token operand = tokens.get(operatorIndex - 1);
					
					// Create a new operation expression where the current expression is one of its operands, the order is important.
					if (isLastExpressionLeft) {
						current = new Operation(current, ExpressionBuilder.createFromToken(operand), Operator.getEnum(operator.getText()));
					} else {
						current = new Operation(ExpressionBuilder.createFromToken(operand), current, Operator.getEnum(operator.getText()));
					}
					
					// If we have another token after the operator then how the current expression 
					// is nested within (left or right) the next operation depends on its type. 
					isLastExpressionLeft = ExpressionBuilder.getTokenType(tokens, operatorIndex + 1) != TokenType.OPERATOR;
					
					// Remove the operand from the token list.
					tokens.remove(operand);
				}
				
				// Remove the operator from the token list.
				tokens.remove(operator);
			}
			
			// Return the root operation expression.
			return current;
		} else {
			throw new Error("error: missing operator in expression");
		}
	}
	
	/**
	 * Takes a list of tokens and returns the type of token at the specified index.
	 * Returns null if there is no item at the index.
	 * @param tokens
	 * @param index
	 * @return token type
	 */
	private static TokenType getTokenType(ArrayList<Token> tokens, int index) {
		if ((index) <= (tokens.size() - 1)) {
			return tokens.get(index).getType();
		} else {
			return null;
		}
	}
	
	/**
	 * Create an atomic expression from a single token.
	 * @param token
	 * @return expression
	 */
	private static Expression createFromToken(Token token) {
		// The type of expression we make depends on the token type.
		switch (token.getType()) {
			case IDENTIFIER:
				return new Variable(token.getText());
			case NUMBER:
				return new Value(Double.parseDouble(token.getText()));
			case STRING:
				return new Value(token.getText());
			default:
				throw new Error("error: cannot create expression from '" + token.getType() + "' token");
		}
	}
	
	/**
	 * Returns the index of the next operator in the tokens list..
	 * @param tokens
	 * @return index
	 */
	private static int getNextOperatorIndex(ArrayList<Token> tokens) {
		int index = 0;
		for (Token token : tokens) {
			if (token.getType() == TokenType.OPERATOR) {
				return index;
			}
			index++;
		}
		return -1;
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

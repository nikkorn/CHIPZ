package parse;

import java.util.ArrayList;
import java.util.Stack;
import expression.Expression;
import expression.Operation;
import expression.Operator;
import expression.Value;
import expression.Variable;
import script.VariableScope;
import token.Token;
import token.TokenType;

/**
 * Builder of expressions from lists of expression tokens.
 */
public class ExpressionBuilder {
	
	/** The variable scope being used when creating variable atomic expression. */
	private VariableScope variableScope;
	
	/**
	 * Create a new instance of the ExpressionBuilder class.
	 * @param variableScope the variable scope to use when creating variable atomic expression.
	 */
	public ExpressionBuilder(VariableScope variableScope) { this.variableScope = variableScope; }
	
	/**
	 * Build an expression using infix ordered (NUMBER,STRING,IDENTIFIER,OPERATOR,LEFT_PAREN,RIGHT_PAREN) tokens.
	 * @param expressionTokens
	 * @return expression
	 * @throws InvalidExpressionException 
	 */
	public Expression build(ArrayList<Token> expressionTokens) throws InvalidExpressionException {
		
		// Our tokens will be in infix form, convert them to post-fix before building our expressions. 
		expressionTokens = ExpressionBuilder.convertExpressionTokensToPostfix(expressionTokens);
		
		// Convert our post-fix ordered tokens into a single expression and return it.
		return convertPostfixTokens(expressionTokens);
	}
	
	/**
	 * Convert a list of post-fix ordered expression tokens into a single expression.
	 * @param expressionTokens
	 * @return expression
	 * @throws Exception 
	 */
	private Expression convertPostfixTokens(ArrayList<Token> tokens) throws InvalidExpressionException {
		// Handle cases where we only have a single token, this should be either a string, number or variable identifier.
		if (tokens.size() == 1) {
			
			// Our single token must represent an atomic expression.
			return createFromToken(tokens.get(0));
		} else if (getNextOperatorIndex(tokens) != -1) {
			
			// Create an expression stack.
			Stack<Expression> stack = new Stack<Expression>();
			
			// Process every token in the expression in order.
			for (Token token : tokens) {
				
				// Determine whether this token is an operator or an operand.
				if (token.getType() == TokenType.OPERATOR) {
					
					// This is an operator, meaning that our two top-most expression in the stack are 
					// operands for an operation. Create an operation and add the operation to the stack.
					stack.push(new Operation(stack.pop(), stack.pop(), Operator.getEnum(token.getText())));
				} else {
					
					// This is not an operator, It is an operand which represents an atomic expression.
					stack.push(createFromToken(token));
				}
			}
			
			// The top expression in the stack is our root expression.
			return stack.peek();
		} else {
			throw new InvalidExpressionException("missing operator in expression");
		}
	}
	
	/**
	 * Create an atomic expression from a single token.
	 * @param token
	 * @return expression
	 * @throws Exception 
	 */
	private Expression createFromToken(Token token) throws InvalidExpressionException {
		// The type of expression we make depends on the token type.
		switch (token.getType()) {
			case IDENTIFIER:
				return new Variable(token.getText(), this.variableScope);
			case NUMBER:
				return new Value(Double.parseDouble(token.getText()));
			case STRING:
				return new Value(token.getText());
			default:
				throw new InvalidExpressionException("cannot create expression from '" + token.getType() + "' token");
		}
	}
	
	/**
	 * Returns the index of the next operator in the tokens list.
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
	 * @throws Exception 
	 */
	private static ArrayList<Token> convertExpressionTokensToPostfix(ArrayList<Token> expressionTokens) throws InvalidExpressionException {
		
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
						throw new InvalidExpressionException("malformed parenthesis in expression.");
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

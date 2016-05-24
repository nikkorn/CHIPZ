import java.util.ArrayList;
import java.util.Stack;

import Tokens.Token;

public class ChipzASTGenerator {
	
	public static Token generateAST(ArrayList<Token> tokenList) {
		Stack<Token> ASTStack = new Stack<Token>();
		// Create our root token.
		Token root = new Token(null, Token.Type.ROOT, 0);
		// Put the root token at the base of our stack.
		ASTStack.push(root);
		// Process every token in our token list to create our basic tree.
		for(Token token : tokenList) {
			if(token.wrapsTokens()) {
				ASTStack.peek().addSubToken(token);
				ASTStack.push(token);
			} else {
				if(token.getType() == Token.Type.END || token.getType() == Token.Type.PAREN_CLOSE) {
					ASTStack.pop();
				} else {
					ASTStack.peek().addSubToken(token);
				}
			}
		}
		// Return our program root.
		return root;
	}
}

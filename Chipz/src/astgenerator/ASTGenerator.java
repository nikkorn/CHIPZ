package astgenerator;

import java.util.ArrayList;
import java.util.Stack;

import exceptions.ASTGenerationException;
import token.Token;

public class ASTGenerator {
	// Reference to the token list to be processed.
	private ArrayList<Token> tokens;
	
	public ASTGenerator(ArrayList<Token> tokens) {
		this.tokens = tokens;
	}
	
	public Program generateAST() throws ASTGenerationException {
		// Our program stack.
		Stack<Node> ASTStack = new Stack<Node>();
		// Create our Program node.
		Program program = new Program();
		// Put the Program node at the base of our stack.
		ASTStack.push(program);
		
		// -----------------------------------------------
		// Process every token in our token list to create our basic tree.
		// -----------------------------------------------

		// Get our first token.
		Token token = this.grabToken();
		// If our token is null then we simply have no more tokens.
		while(token != null) {
			// Handle tokens based on their type. We are only expecting tokens that define the start of a process. 
			switch(token.getType()){
			case DEFINE:
				Define defineNode = processDefineToken();
				// Add this as child node to current top node of program stack.
				ASTStack.peek().addChild(defineNode);
				break;
			case SET:
				Set setNode = processSetToken();
				// Add this as child node to current top node of program stack.
				ASTStack.peek().addChild(setNode);
				break;
			case FUNCTION:
				// TODO ....
				break;
			case IF:
				// TODO ....
				break;
			case LOOP:
				// TODO ....
				break;
			case RETURN:
				// TODO ....
				break;
			case END:
				// TODO Have a think about this, but pretty sure we just need to pop current program stack.
				break;
			default:
				throw new ASTGenerationException("encountered unexpected token of type '" + token.getType() + "'");
			}
			
			// Grab the next token.
			token = this.grabToken();
		}
		// Return our program AST.
		return program;
	}
	
	/**
	 * 
	 * @return
	 */
	private Set processSetToken() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @return
	 */
	private Define processDefineToken() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get the next token from the token list, removing it.
	 * @return
	 */
	private Token grabToken() {
		if(tokens.size() > 0) {
			return tokens.remove(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Get the next token from the token list without removing it.
	 * @return
	 */
	private Token peekAtToken() {
		return peekAtToken(1);
	}
	
	/**
	 * Get the token from the token list offset by the lookahead without removing it.
	 * @return
	 */
	private Token peekAtToken(int lookahead) {
		try {
			Token returnToken = tokens.get(lookahead - 1);
			return returnToken;
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
}

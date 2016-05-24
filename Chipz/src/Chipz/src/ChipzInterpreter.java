import java.util.ArrayList;
import java.util.Stack;

import token.Token;

public class ChipzInterpreter {
	
	public void process(Token programRoot) {
		boolean finished = false;
		// Create our program stack.
		Stack<ArrayList<Token>> stack = new Stack<ArrayList<Token>>();
		// Add our root token list to the stack.
		stack.push(programRoot.getSubTokens());
		// Loop repeatedly until we've processed all of our tokens.
		while(!finished) {
			
			if(stack.peek().size() == 0) {
				if(stack.size() == 0) {
					finished = true;
				} else {
					stack.pop();
				}
			} else {
				
				Token nextToken = stack.peek().get(0);
				stack.peek().remove(0);
				
			}
			
		}
	}
}
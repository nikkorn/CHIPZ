import java.util.ArrayList;
import token.Token;
import token.Token.Type;

public class Line {
	ArrayList<Token> lineTokens = new ArrayList<Token>();
	
	public void appendToken(Token token) {
		// only add the token if it is not an EOL token.
		if(token.getType() != Type.EOL) {
			lineTokens.add(token);
		}
	}
	
	public ArrayList<Token> getLineTokens() {
		return lineTokens;
	}
	
	public void printLine() {
		System.out.println("------------------------------------------");
		for(Token t : lineTokens) {
			System.out.println("TYPE: " + t.getType() + "   VALUE: " + t.getValue());
		}
		System.out.println("------------------------------------------");
	}
}

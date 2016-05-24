import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import token.Token;

public class Lexer {
	public static void main(String[] args) {
		ArrayList<Token> tokenList = new ArrayList<Token>();
		readFileIntoTokenList(tokenList);
		Token program = ChipzASTGenerator.generateAST(tokenList);
		// Print our AST
		System.out.println("Root Tokens: " + program.getSubTokens().size());
		prettyPrint(program.getSubTokens(), 0);
	}
	
	public static void readFileIntoTokenList(ArrayList<Token> tokenList) {
		try {
			Scanner sc = new Scanner(new File("src/test.chpz"));
			String input = sc.nextLine();
			while(input != null) {
				processLineIntoTokens(input, tokenList);
				// This is the end on a line, add an EOL token.
				tokenList.add(new Token(null, Token.Type.EOL, 0));
				if(sc.hasNextLine()) {
					input = sc.nextLine();
				} else {
					input = null;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static void processLineIntoTokens(String input, ArrayList<Token> tokenList) {
		int charsEaten = 0;
		Token currentToken = ChipzLexer.getArtifact(input.substring(charsEaten));
		while(charsEaten < input.length()) {
			if(currentToken != null) {
				charsEaten += currentToken.getCharsEaten();
				if(currentToken.getType() != Token.Type.WHITESPACE) {
					tokenList.add(currentToken);
				}
				currentToken = ChipzLexer.getArtifact(input.substring(charsEaten));
			} else {
				System.out.println("Failed to process line, is it valid?");
				break;
			}
		}
	}
	
	public static void prettyPrint(ArrayList<Token> tokenList, int indent) {
		for(Token t : tokenList) {
			System.out.println(makePrettyPrintPadding(indent) + "TYPE: " + t.getType() + "   VALUE: " + t.getValue());
			if(t.getSubTokens().size() > 0) {
				prettyPrint(t.getSubTokens(), indent + 2);
			}
		}
	}
	
	public static String makePrettyPrintPadding(int size) {
		String padding = "";
		for(int i = 0; i < size; i++) {
			padding += "--";
		}
		return padding;
	}
}

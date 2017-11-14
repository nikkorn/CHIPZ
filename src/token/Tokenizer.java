package token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Creates tokens from source code.
 */
public class Tokenizer {
	
	/** The language keywords. */
	public static String[] KEYWORDS = new String[] { "let", "if", "then", "for", "step", "print", "next", "goto", "gosub", "data", "return", "rem", "read", "input", "end" };
	
	/** Single character mapping to token types. */
	@SuppressWarnings("serial")
	private static final HashMap<String, TokenType> SINGLE_CHARACTER_TOKENS_MAP = new HashMap<String, TokenType>() {
        {
            put("+", TokenType.OPERATOR);
            put("-", TokenType.OPERATOR);
            put("*", TokenType.OPERATOR);
            put("/", TokenType.OPERATOR);
            put("<", TokenType.OPERATOR);
            put(">", TokenType.OPERATOR);
            put("(", TokenType.LEFT_PAREN);
            put(")", TokenType.RIGHT_PAREN);
            put(",", TokenType.COMMA);
            put("=", TokenType.EQUALS);
        }
    };
    
    /** Double character mapping to token types. */
	@SuppressWarnings("serial")
	private static final HashMap<String, TokenType> DOUBLE_CHARACTER_TOKENS_MAP = new HashMap<String, TokenType>() {
        {
            put("==", TokenType.OPERATOR);
            put("<=", TokenType.OPERATOR);
            put(">=", TokenType.OPERATOR);
        }
    };
	
	/**
	 * Process a line into tokens.
	 * @param line
	 * @return
	 */
	public static ArrayList<Token> processLine(String line) {
		
		// The list of line tokens.
		ArrayList<Token> tokenList = new ArrayList<Token>();
		
		// The number of characters eaten up in processing this line.
		int charsEaten = 0;
		
		// Try to get the first token in the line.
		Token currentToken = getToken(line.substring(charsEaten), true);
		
		// Keep trying to get the next token as long as there are characters left in the line.
		while(charsEaten < line.length()) {
			
			// If we have a token then add it to our list of tokens.
			// If we did not get one then something went wrong.
			if(currentToken != null) {
				
				// Update the count of chars eaten.
				charsEaten += currentToken.getCharsEaten();
				
				// Add the current token IF it is not just whitespace.
				if(currentToken.getType() != TokenType.WHITESPACE) {
					tokenList.add(currentToken);
				}
				
				// Try to get the next token.
				currentToken = getToken(line.substring(charsEaten), false);
			} else {
				// There are characters left in the line but we can't make a token of them!
				System.out.println("error: failed to process line, is it valid?");
				break;
			}
		}
		
		return tokenList;
	}
	
	/**
	 * Get the next token from the string input.
	 * @param input
	 * @param isNewLine Flag defining whether this is the first time this method was called for a new line.
	 * @return token details
	 */
	private static Token getToken(String input, boolean isNewLine) {
		
		// Return null if we got empty string
		if(input.length() == 0) {
			return null;
		}
		
		// If this is a new line then check whether this line is dedicated to a label.
		// A label must:
		//    - Be on its own line.
		//    - Start with a letter.
		//    - End with a ':'
		if (isNewLine) {
			// Create a regular expression to find a label.
			Matcher labelMatcher = Pattern.compile("^[a-zA-Z]{1}(\\w+)?:$").matcher(input);
			// Did we get a match for the line number?
			if (labelMatcher.find()) {
				return new Token(input.replace(":", ""), TokenType.LABEL, input.length());
		    }
		}
		
		// Is our first character whitespace or a tab? if it is then eat all of it.
		if(input.charAt(0) == ' ' || input.charAt(0) == '\t') {
			for(int pos = 0 ; pos < input.length(); pos++) {
				if(input.charAt(pos) != ' ' && input.charAt(pos) != '\t') {
					// We have finished out meal of whitespace.
					return new Token(null, TokenType.WHITESPACE, pos);
				}
			}
			// The whole rest of the line was whitespace. 
			return new Token(null, TokenType.WHITESPACE, input.length());
		}
		
		// Is our first character a letter? if it is then this could be a keyword or identifier.
		if(Character.isLetter(input.charAt(0))) {
			String charSequence = null;
			for(int pos = 0 ; pos < input.length(); pos++) {
				// Is the current character a letter or digit?
				if(!Character.isLetterOrDigit(input.charAt(pos))) {
					// This is the end of our alphanumeric sequence.
					charSequence = input.substring(0, pos);
					break;
				}
			}
			// We got to the end of the line. It is all one long keyword of identifier.
			if(charSequence == null) {
				charSequence = input.substring(0, input.length());
			}
			// This is the end of our alphanumeric sequence are we dealing with a keyword or identifier?
			for(String keyword : KEYWORDS) {
				// Check whether we have a keyword match.
				if(keyword.equals(charSequence.toLowerCase())){
					return new Token(charSequence.toLowerCase(), TokenType.KEYWORD, charSequence.length());
				} 
			}
			// Must be an identifier.
			return new Token(charSequence, TokenType.IDENTIFIER, charSequence.length());
		}
		
		// Is our first character a number? if it is then this will be a number consisting of
		if(Character.isDigit(input.charAt(0))) {
			boolean encounteredPoint = false;
			for(int pos = 0 ; pos < input.length(); pos++) {
				if(Character.isDigit(input.charAt(pos)) || input.charAt(pos) == '.' ) {
					// We can only have only one point
					if(input.charAt(pos) == '.') {
						if(encounteredPoint) {
							return new Token(input.substring(0, pos), TokenType.NUMBER, pos);
						} else {
							encounteredPoint = true;
						}
					}
				} else {
					// We have finished our number.
					return new Token(input.substring(0, pos), TokenType.NUMBER, pos);
				}
			}
			// The whole rest of the line was whitespace. 
			return new Token(input.substring(0, input.length()), TokenType.NUMBER, input.length());
		}
		
		// Check for string literals
		// Is our first character a single or double quote? if it is then get this whole string
		if(input.charAt(0) == '\'' || input.charAt(0) == '"') {
			char openingQuote = input.charAt(0);
			if(input.length() > 1) {
				for(int pos = 1 ; pos < input.length(); pos++) {
					if(input.charAt(pos) == openingQuote) {
						return new Token(input.substring(1, pos), TokenType.STRING, pos + 1);
					} 
				}	
				// Our string literal was never closed.
			}
		}
		
		// The last hope we have of parsing a token from the input string
		// is that it is either a single or double character token.
		if(input.length() > 1) {
			String doubleCharConst = input.substring(0,2);
			String singleCharConst = input.substring(0,1);
			
			// Check whether we have a double character token.
			TokenType doubleCharacterToken = Tokenizer.DOUBLE_CHARACTER_TOKENS_MAP.get(doubleCharConst);
			if (doubleCharacterToken != null) {
				return new Token(doubleCharConst, doubleCharacterToken, 2);
			}
			
			// Check whether we have a single character token.
			TokenType singleCharacterToken = Tokenizer.SINGLE_CHARACTER_TOKENS_MAP.get(singleCharConst);
			if (singleCharacterToken != null) {
				return new Token(singleCharConst, singleCharacterToken, 1);
			}
			
		} else {
			// Check whether the last character of the input is a single character token.
			TokenType singleCharacterToken = Tokenizer.SINGLE_CHARACTER_TOKENS_MAP.get(input);
			if (singleCharacterToken != null) {
				return new Token(input, singleCharacterToken, 1);
			}
		}
		
		// Something went wrong.
		return null;
	}
}

import token.Token;

public class ChipzLexer {
	public static String[] KEYWORDS = new String[] {
		"define",
		"set",
		"function",
		"call",
		"print",
		"return",
		"loop",
		"true",
		"false",
		"if",
		"end",
		"number",
		"string", 
		"array"
	};
	
	public static String[] LANGUAGE_CONSTRUCTS = new String[] {
		"(",
		")",
		"[",
		"]",
		"!=",
		"<",
		">",
		"<=",
		">=",
		"=",
		"*",
		"+",
		"/",
		"-",
		"%",
		","
	};
	
	public static Token getArtifact(String input) {
		// Return null if we got empty string
		if(input.length() == 0) {
			return null;
		}
		
		// Is our first character whitespace or a tab? if it is then eat all of it.
		if(input.charAt(0) == ' ' || input.charAt(0) == '\t') {
			for(int pos = 0 ; pos < input.length(); pos++) {
				if(input.charAt(pos) != ' ' && input.charAt(pos) != '\t') {
					// We have finished out meal of whitespace.
					return new Token(null, Token.Type.WHITESPACE, pos);
				}
			}
			// The whole rest of the line was whitespace. 
			return new Token(null, Token.Type.WHITESPACE, input.length());
		}
		
		// Is our first character a letter? if it is then this could be a keyword or identifier.
		if(Character.isLetter(input.charAt(0))) {
			String charSequence = null;
			for(int pos = 0 ; pos < input.length(); pos++) {
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
				if(keyword.equals(charSequence.toLowerCase())){
					// We have a keyword match.
					switch(keyword) {
					case "define":
						return new Token(charSequence, Token.Type.DEFINE, charSequence.length());
					case "set":
						return new Token(charSequence, Token.Type.SET, charSequence.length());
					case "function":
						return new Token(charSequence, Token.Type.FUNCTION, charSequence.length());
					case "print":
						return new Token(charSequence, Token.Type.PRINT, charSequence.length());
					case "println":
						return new Token(charSequence, Token.Type.PRINTLN, charSequence.length());
					case "return":
						return new Token(charSequence, Token.Type.RETURN, charSequence.length());
					case "loop":
						return new Token(charSequence, Token.Type.LOOP, charSequence.length());
					case "if":
						return new Token(charSequence, Token.Type.IF, charSequence.length());
					case "array":
						return new Token(charSequence, Token.Type.ARRAY, charSequence.length());
					case "end":
						return new Token(charSequence, Token.Type.END, charSequence.length());
					}
				} 
			}
			// Must be an identifier.
			return new Token(charSequence, Token.Type.IDENTIFIER, charSequence.length());
		}
		
		// Is our first character a number? if it is then this will be a number consisting of
		if(Character.isDigit(input.charAt(0))) {
			boolean encounteredPoint = false;
			for(int pos = 0 ; pos < input.length(); pos++) {
				if(Character.isDigit(input.charAt(pos)) || input.charAt(pos) == '.' ) {
					// We can only have only one point
					if(input.charAt(pos) == '.') {
						if(encounteredPoint) {
							return new Token(input.substring(0, pos), Token.Type.NUMBER, pos);
						} else {
							encounteredPoint = true;
						}
					}
				} else {
					// We have finished our number.
					return new Token(input.substring(0, pos), Token.Type.NUMBER, pos);
				}
			}
			// The whole rest of the line was whitespace. 
			return new Token(input.substring(0, input.length()), Token.Type.NUMBER, input.length());
		}
		
		// Check for string literals
		// Is our first character a single or double quote? if it is then get this whole string
		if(input.charAt(0) == '\'' || input.charAt(0) == '"') {
			char openingQuote = input.charAt(0);
			if(input.length() > 1) {
				for(int pos = 1 ; pos < input.length(); pos++) {
					if(input.charAt(pos) == openingQuote) {
						return new Token(input.substring(1, pos), Token.Type.STRING, pos + 1);
					} 
				}	
				// Our string literal was never closed.
			}
		}
		
		if(input.length() > 1) {
			String doubleCharConst = input.substring(0,2);
			String singleCharConst = input.substring(0,1);
			for(String cnst : LANGUAGE_CONSTRUCTS) {
				if(doubleCharConst.equals(cnst)) {
					return new Token(null, getSymbolTokenType(cnst), 2);
				}
			}
			for(String cnst : LANGUAGE_CONSTRUCTS) {
				if(singleCharConst.equals(cnst)) {
					return new Token(null, getSymbolTokenType(cnst), 1);
				}
			}
		} else {
			for(String cnst : LANGUAGE_CONSTRUCTS) {
				if(input.equals(cnst)) {
					return new Token(null, getSymbolTokenType(cnst), 1);
				}
			}
		}
		
		// Something went wrong.
		return null;
	}
	
	public static Token.Type getSymbolTokenType(String input) {
		switch(input) {
		case "(":
			return Token.Type.PAREN_OPEN;
		case ")":
			return Token.Type.PAREN_CLOSE;
		case "[":
			return Token.Type.SQR_BRK_OPEN;
		case "]":
			return Token.Type.SQR_BRK_CLOSE;
		case "!=":
			return Token.Type.NOT_EQUALS;
		case "<":
			return Token.Type.LESS_THAN;
		case ">":
			return Token.Type.GREATER_THAN;
		case "<=":
			return Token.Type.LESS_THAN_EQUAL;
		case ">=":
			return Token.Type.GREATER_THAN_EQUAL;
		case "=":
			return Token.Type.EQUALS;
		case "*":
			return Token.Type.MULTIPLY;
		case "+":
			return Token.Type.ADD;
		case "/":
			return Token.Type.DIVIDE;
		case "-":
			return Token.Type.MINUS;
		case "%":
			return Token.Type.MODULUS;
		case ",":
			return Token.Type.COMMA;
		default:
			return null;
		}
	}
}

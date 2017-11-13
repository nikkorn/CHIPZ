package token;

/**
 * Represents a token.
 */
public class Token {
	
	/** The type of the token. */
	private TokenType type;
	
	/** The text which represents the value of the token. */
	private String text;
	
	/** The number of chars eaten to construct the token. */
	private int charsEaten;
	
	/**
	 * Create a new instance of the Token class.
	 * @param text
	 * @param type
	 * @param chars eaten
	 */
	public Token (String text, TokenType type, int charsEaten) {
		this.text       = text;
		this.type       = type;
		this.charsEaten = charsEaten;
	}
	
	/**
	 * Gets the type of this token.
	 * @return token type.
	 */
	public TokenType getType() { return this.type; }
	
	/**
	 * Gets the text which represents the value of the token.
	 * @return text.
	 */
	public String getText() { return this.text; }
	
	/**
	 * Gets the number of chars eaten to construct the token.
	 * @return chars eaten
	 */
	public int getCharsEaten() { return this.charsEaten; }
}

package token;
import java.util.ArrayList;

import Contexts.Context;

public class Token {
	private String value;
	private int charsEaten;
	private boolean wrapsTokens = false;
	private ArrayList<Token> subTokens = new ArrayList<Token>();
	private Type type;
	
	public enum Type {
		IDENTIFIER,
		NUMBER,
		STRING,
		BOOLEAN,
		WHITESPACE, 
		EQUALS,
		NOT_EQUALS,
		PAREN_OPEN,
		PAREN_CLOSE,
		SQR_BRK_OPEN,
		SQR_BRK_CLOSE,
		GREATER_THAN,
		LESS_THAN,
		GREATER_THAN_EQUAL,
		LESS_THAN_EQUAL,
		MULTIPLY,
		DIVIDE,
		ADD,
		MINUS,
		MODULUS,
		COMMA,
		DEFINE,
		SET,
		FUNCTION,
		PRINT,
		PRINTLN,
		RETURN,
		LOOP,
		IF,
		ARRAY,
		END,
		ROOT,
		EOL
	}
	
	public Token(String value, Type type, int charsEaten) {
		if(type == Type.FUNCTION || type == Type.LOOP || type == Type.IF || type == Type.PAREN_OPEN) {
			wrapsTokens = true;
		}
		this.charsEaten = charsEaten;
		this.value = value;
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public int getCharsEaten() {
		return charsEaten;
	}

	public Type getType() {
		return type;
	}

	public boolean wrapsTokens() {
		return wrapsTokens;
	}

	public ArrayList<Token> getSubTokens() {
		return subTokens;
	}

	public void setSubTokens(ArrayList<Token> subTokens) {
		this.subTokens = subTokens;
	}
	
	public void addSubToken(Token t) {
		subTokens.add(t);
	}
}

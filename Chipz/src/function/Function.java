package function;

import java.util.ArrayList;
import token.Token;

/**
 * Represents a function defined in the source code
 * @author Nikolas Howard
 *
 */
public class Function {
	// function name
	private String funtionName = "undefined";
	// tokens wrapped by this function.
	private ArrayList<Token> tokens;
	// parameters of this function.
	private ArrayList<String> params;
	
	/**
	 * constructor.
	 * @param functionName
	 */
	public Function(String functionName) {
		this.funtionName = functionName;
	}
	
	/**
	 * Get the function name.
	 * @return function name
	 */
	public String getFuntionName() {
		return funtionName;
	}
	
	/**
	 * Set the function name.
	 * @param funtionName
	 */
	public void setFuntionName(String funtionName) {
		this.funtionName = funtionName;
	}
	
	/**
	 * Get the list of tokens wrapped by this function.
	 * @return list of tokens
	 */
	public ArrayList<Token> getTokens() {
		return tokens;
	}
	
	/**
	 * Set the list of tokens wrapped by this function.
	 * @param tokens
	 */
	public void setTokens(ArrayList<Token> tokens) {
		this.tokens = tokens;
	}
	
	/**
	 * Get list of parameters for this function.
	 * @return list of parameters.
	 */
	public ArrayList<String> getParams() {
		return params;
	}
	
	/**
	 * Set list of parameters for this function.
	 * @param params
	 */
	public void setParams(ArrayList<String> params) {
		this.params = params;
	}
}

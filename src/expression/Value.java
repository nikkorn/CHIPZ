package expression;

/**
 * Represents either a string or number value.
 */
public class Value implements Expression {
	
	public enum DataType { STRING, NUMBER }
	
	/** The string value. */
	private String stringValue = null;
	
	/** The number value. */
	private Double numberValue = null;
	
	/**
	 * Create a new instance of the Value class, holding a string.
	 * @param value
	 */
	public Value(String value) { this.stringValue = value; }
	
	/**
	 * Create a new instance of the Value class, holding a number.
	 * @param value
	 */
	public Value(Double value) { this.numberValue = value; }
	
	/**
	 * Create a new instance of the Value class, holding a number (primarily an integer).
	 * @param value
	 */
	public Value(int value) { this.numberValue = (double) value; }
	
	/**
	 * Get the data type of this variable.
	 * @return data type.
	 */
	public DataType getDataType() {
		if (stringValue != null) {
			return DataType.STRING;
		} else {
			return DataType.NUMBER;
		}
	}
	
	/**
	 * Get the value as a number.
	 * @return value as number.
	 */
	public Double asNumber() {
		return getDataType() == DataType.NUMBER ? numberValue : Double.parseDouble(stringValue);
	}
	
	/**
	 * Get the value as a string.
	 * @return value as string.
	 */
	public String asString() {
		return getDataType() == DataType.STRING ? stringValue : String.valueOf(numberValue);
	}
	
	/**
	 * Gets whether the value is truthy (any value other than the number 0) 
	 * @return is truthy.
	 */
	public boolean isTruthy() { return numberValue != 0; }
	
	@Override
	public Value evaluate() { return this; }
}

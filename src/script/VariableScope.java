package script;

import java.util.HashMap;
import expression.Value;

/**
 * Defines a variable scope.
 */
public class VariableScope {
	
	/** The mapping of variable names to values. */
	private HashMap<String, Value> values = new HashMap<String, Value>();
	
	/**
	 * Gets a value from the map by name.
	 * Errors if the value does not exist.
	 * @param name
	 * @return value
	 */
	public Value get(String name) {
		// Check whether this value exists in our map.
		if (values.containsKey(name)) {
			return values.get(name);
		} else {
			// We tried to get a value that does not exist!
			throw new Error("error: variable '" + name + "' does not exist");
		}
	}
	
	/**
	 * Sets a value.
	 * Will replace mapping with same name if one exists. 
	 * @param name
	 * @param value
	 */
	public void set(String name, Value value) {
		this.values.put(name, value);
	}
	
	/**
	 * Get the values map.
	 * @return values map
	 */
	public HashMap<String, Value> getAll() { return this.values; }
}

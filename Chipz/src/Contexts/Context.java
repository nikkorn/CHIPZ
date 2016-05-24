package Contexts;

import java.util.HashMap;

/**
 * Represents a scope for variables
 * @author Nikolas Howard
 *
 */
public class Context {
	private HashMap<String, DataItem> contextDataItemMap = new HashMap<String, DataItem>();
	
	/**
	 * Define a data item without setting its value
	 * @param name
	 * @param type
	 */
	public void defineDataItem(String name, DataItem.Type type) {
		// Has this variable already been defined?
		if(contextDataItemMap.containsKey(name)) {
			// Does data type match, if it does then leave it, replace it otherwise.
			DataItem existingItem = contextDataItemMap.get(name);
			if(!(existingItem.getType() == type)) {
				contextDataItemMap.put(name, new DataItem(type));
			}
		} else {
			// Never been defined, define it.
			contextDataItemMap.put(name, new DataItem(type));
		}
	}
	
	/**
	 * Set the value for a data item.
	 * @param name
	 * @param value
	 */
	public void setDataValue(String name, String value) {
		// Does the variable exist
		if(contextDataItemMap.containsKey(name)) {
			// Does data type match, if it does then leave it, replace it otherwise.
			DataItem existingItem = contextDataItemMap.get(name);
			existingItem.setValue(value);
		} else {
			// Never been defined, fails
			System.out.println(name + " has not been defined");
		}
	}
}

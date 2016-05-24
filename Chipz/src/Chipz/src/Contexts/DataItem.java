package Contexts;

/**
 * Represents a data item.
 * @author nh163
 *
 */
public class DataItem {
	private String value = "";
	private Type type;
	
	public enum Type {
		STRING,
		NUMBER,
		BOOLEAN
	}
	
	public DataItem(Type type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}

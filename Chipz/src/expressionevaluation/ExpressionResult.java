package expressionevaluation;

/**
 * The result of an evaluated expression.
 * @author nh163
 *
 */
public class ExpressionResult {
	private String value;
	private Type type;
	
	public enum Type {
		BOOL,
		NUM
	}
	
	public ExpressionResult(String value, Type type) {
		this.value = value;
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
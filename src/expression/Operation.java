package expression;

/**
 * Represents an operation on two operands using a specified operator.
 */
public class Operation implements Expression {
	
	/** The operands. */
	private Expression left, right;
	
	/** The operator. */
	private Operator operator;
	
	/**
	 * Creates a new instance of the Operation class.
	 * @param left
	 * @param right
	 * @param operator
	 */
	public Operation(Expression left, Expression right, Operator operator) {
		this.right    = right;
		this.left     = left;
		this.operator = operator;
	}

	/**
	 * Evaluate this expression and return the value.
	 */
	@Override
	public Value evaluate() {
		
		// Evaluate both sides of the expression.
		Value leftResult  = left.evaluate();
		Value rightResult = right.evaluate();
		
		// Operate on the results.
		switch(this.operator) {
			case ADD:
				break;
			case DIVIDE:
				break;
			case EQUALS:
				break;
			case GREATER_THAN:
				break;
			case GREATER_THAN_OR_EQUAL:
				break;
			case LESS_THAN:
				break;
			case LESS_THAN_OR_EQUAL:
				break;
			case MULTIPLY:
				break;
			case SUBTRACT:
				break;
			default:
				break;
		}

		// TODO Auto-generated method stub
		return null;
	}
}

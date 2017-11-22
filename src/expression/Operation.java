package expression;

import expression.Value.DataType;

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
				// Is this an addition or string concatenation? If either value is a string then it is a concatenation.
				boolean isStringConcatenation = (leftResult.getDataType() == DataType.STRING || rightResult.getDataType() == DataType.STRING);
				// The type of value we return depends on whether this is a string concatenation.
				if (isStringConcatenation) {
					String concatenation = leftResult.asString() + rightResult.asString();
					return new Value(concatenation);
				} else {
					Double result = leftResult.asNumber() + rightResult.asNumber();
					return new Value(result);
				}
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

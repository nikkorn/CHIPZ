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
				// We cannot divide non-numeric operands.
				if (leftResult.getDataType() == DataType.NUMBER && rightResult.getDataType() == DataType.NUMBER) {
					if (rightResult.asNumber() != 0) {
						return new Value(leftResult.asNumber() / rightResult.asNumber());
					} else {
						throw new Error("error: cannot divide by 0");
					}
				} else {
					throw new Error("error: cannot divide using non-numeric operands");
				}
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
				// We cannot multiply non-numeric operands.
				if (leftResult.getDataType() == DataType.NUMBER && rightResult.getDataType() == DataType.NUMBER) {
					return new Value(leftResult.asNumber() * rightResult.asNumber());
				} else {
					throw new Error("error: cannot multiply using non-numeric operands");
				}
			case SUBTRACT:
				// We cannot subtract non-numeric operands.
				if (leftResult.getDataType() == DataType.NUMBER && rightResult.getDataType() == DataType.NUMBER) {
					return new Value(leftResult.asNumber() - rightResult.asNumber());
				} else {
					throw new Error("error: cannot subtract using non-numeric operands");
				}
			default:
				break;
		}

		// TODO Auto-generated method stub
		return null;
	}
}

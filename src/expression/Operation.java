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
	 * @param right
	 * @param left
	 * @param operator
	 */
	public Operation(Expression right, Expression left, Operator operator) {
		this.left     = left;
		this.right    = right;
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
				// This will not be a strict comparison based on type. 1 will equal "1".
				int result = leftResult.toString().equals(rightResult.toString()) ? 1 : 0;
				return new Value(result); 
			case GREATER_THAN:
				// We cannot compare non-numeric values.
				if (leftResult.getDataType() == DataType.NUMBER && rightResult.getDataType() == DataType.NUMBER) {
					return new Value(leftResult.asNumber() > rightResult.asNumber() ? 1 : 0);
				} else {
					throw new Error("error: cannot apply '>' to non-numeric operands");
				}
			case GREATER_THAN_OR_EQUAL:
				// We cannot compare non-numeric values.
				if (leftResult.getDataType() == DataType.NUMBER && rightResult.getDataType() == DataType.NUMBER) {
					return new Value(leftResult.asNumber() >= rightResult.asNumber() ? 1 : 0);
				} else {
					throw new Error("error: cannot apply '>=' to non-numeric operands");
				}
			case LESS_THAN:
				// We cannot compare non-numeric values.
				if (leftResult.getDataType() == DataType.NUMBER && rightResult.getDataType() == DataType.NUMBER) {
					return new Value(leftResult.asNumber() < rightResult.asNumber() ? 1 : 0);
				} else {
					throw new Error("error: cannot apply '<' to non-numeric operands");
				}
			case LESS_THAN_OR_EQUAL:
				// We cannot compare non-numeric values.
				if (leftResult.getDataType() == DataType.NUMBER && rightResult.getDataType() == DataType.NUMBER) {
					return new Value(leftResult.asNumber() <= rightResult.asNumber() ? 1 : 0);
				} else {
					throw new Error("error: cannot apply '<=' to non-numeric operands");
				}
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

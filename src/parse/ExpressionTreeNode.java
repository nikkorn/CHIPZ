package parse;

import expression.Expression;
import expression.Operator;

/**
 * A node in an expression tree.
 */
public class ExpressionTreeNode {
	
	/** The left child node. */
	private ExpressionTreeNode leftNode;
	
	/** The right child node. */
	private ExpressionTreeNode rightNode;
	
	/** The operator which defines this node an an operation. */
	private Operator operator = null;
	
	/** The expression which defines this node as an atomic expression. */
	private Expression expression = null;
	
	/**
	 * Create a new instance of the ExpressionTreeNode class, where the node represents an atomic expression.
	 * @param expression
	 */
	public ExpressionTreeNode(Expression expression) { this.expression = expression; }
	
	/**
	 * Create a new instance of the ExpressionTreeNode class, where the node represents an operation.
	 * @param operator
	 */
	public ExpressionTreeNode(Operator operator) { this.operator = operator; }
	
	/**
	 * Gets whether this node represents an operation.
	 * @return is operation.
	 */
	public boolean isOperation() { return this.operator != null; }
	
	/**
	 * Get the expression for this node.
	 * This wont be used if this node represents an operation.
	 * @return expression.
	 */
	public Expression getExpression() { return this.expression; }
	
	/**
	 * Get the operator for this node.
	 * This wont be used if this node represents an atomic expression.
	 * @return expression.
	 */
	public Operator getOperator() { return this.operator; }

	/**
	 * Get the right node.
	 * @return right node
	 */
	public ExpressionTreeNode getRight() { return rightNode; }

	/**
	 * Set the right node.
	 * @param rightNode
	 */
	public void setRight(ExpressionTreeNode rightNode) { this.rightNode = rightNode; }

	/**
	 * Get the left node.
	 * @return left node
	 */
	public ExpressionTreeNode getLeft() { return leftNode; }

	/**
	 * Set the left node.
	 * @param leftNode
	 */
	public void setLeft(ExpressionTreeNode leftNode) { this.leftNode = leftNode; } 
}

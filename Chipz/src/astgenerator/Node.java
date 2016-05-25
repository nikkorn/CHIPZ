package astgenerator;

import java.util.ArrayList;

/**
 * Represents a node in our AST.
 * @author nh163
 *
 */
public class Node {
	// type of the node
	private NodeType nodeType = NodeType.NONE;
	// list of child nodes.
	private ArrayList<Node> children = new ArrayList<Node>();
	
	public NodeType getNodeType() {
		return nodeType;
	}
	
	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}
	
	public ArrayList<Node> getChildren() {
		return children;
	}
	
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	
	public void addChild(Node child) {
		this.children.add(child);
	}
}

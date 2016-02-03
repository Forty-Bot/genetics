package science_fair_genetics;

/**
 * Part of a tree which represents an algebraic function in the form of 
 * {@literal y=f(x)}.
 */
public interface Node extends Cloneable {
	
	public NodeType getType();
	public int numChildNodes();
	public void setChildNode(int i, Node node);
	public Node getChildNode(int i);
	public Node[] getChildNodes();
	public Node getParent();
	public Node randomChild();
	
	public Node clone() throws CloneNotSupportedException;	
	
	/**
	 * Invalidate cached hash values
	 */
	public void invalidate();
	
	/**
	 * Apply the function of the node recursively onto its children with
	 * the given input.
	 * @param input The input to the function; may be thought of as 'x'.
	 */
	public double evaluate(double input);
	
}

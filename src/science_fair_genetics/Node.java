package science_fair_genetics;

public interface Node {
	
	public NodeType getType();
	public int numChildNodes();
	public void setChildNode(int i, Node node);
	public Node getChildNode(int i);
	public Node[] getChildNodes();
	
	/**
	 * Invalidate cached hash values
	 */
	public void invalidate();
	
	public double parse(double input);

}

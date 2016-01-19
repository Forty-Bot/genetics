package science_fair_genetics;

/**
 * @author sean
 *
 */
public interface Node {
	
	public NodeType getType();
	public int numChildNodes();
	public void setChildNode(int i, Node node);
	public Node getChildNode(int i);
	public Node[] getChildNodes();
	
	public double parse(double input);

}

package science_fair_genetics;

public abstract class AbstractNode implements Node {

	protected NodeType type;
	protected Node[] children;
	
	@Override
	public NodeType getType() {
		return type;
	}

	@Override
	public int numChildNodes() {
		return children.length;
	}

	@Override
	public void setChildNode(int i, Node node) {
		children[i] = node;

	}

	@Override
	public Node getChildNode(int i) {
		return children[i];
	}

	@Override
	public Node[] getChildNodes() {
		return children;
	}

}

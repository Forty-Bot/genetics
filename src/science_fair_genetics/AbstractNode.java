package science_fair_genetics;

public abstract class AbstractNode implements Node {

	protected NodeType type;
	protected Node parent;
	protected Node[] children;
	protected String operator;
	
	protected int hash;
	protected boolean valid;
	
	protected AbstractNode(Node parent) {
		this.parent = parent;
		valid = false;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} else if(!this.getClass().equals(o.getClass())) {
			return false;
		}
		
		Node root = (Node) o;
		// This shouldn't be false, but it can't hurt to check
		if(root.numChildNodes() != this.numChildNodes()) {
			return false;
		}
		for(int i = 0; i < children.length; i++) {
			if(!this.children[i].equals(root.getChildNode(i))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		if(valid) {
			return hash;
		}
		int localHash = this.getClass().hashCode();
		for(Node n: children) {
			localHash = localHash * 31 + n.hashCode();
		}
		hash = localHash;
		valid = true;
		return hash;
	}
	
	@Override
	public void invalidate() {
		valid = false;
		if(parent != null) {
			parent.invalidate();
		}
	}
	
	@Override
	public String toString() {
		if(this.type == NodeType.FUNCTION) {
			return "(" + children[0].toString() + " " + operator + " " +
				children[1].toString() + ")";
		} else {
			return super.toString();
		}
	}
	
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
		valid = false;
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

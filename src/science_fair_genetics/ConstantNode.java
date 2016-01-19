package science_fair_genetics;

public class ConstantNode extends AbstractNode {

	private final double value;
	
	protected ConstantNode(Node parent, double value) {
		super(parent);
		type = NodeType.TERMINATOR;
		children = new Node[0];
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		if(valid) {
			return hash;
		}
		hash = this.getClass().hashCode() * 31 + Double.hashCode(value);
		valid = true;
		return hash;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} else if(!this.getClass().equals(o.getClass())) {
			return false;
		}
		ConstantNode root = (ConstantNode) o;
		return root.value == this.value;
	}
	
	@Override
	public String toString() {
		return Double.toString(value);
	}
	
	@Override
	public double parse(double input) {
		return value;
	}

}

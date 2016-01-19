package science_fair_genetics;

public class InputNode extends AbstractNode {

	protected InputNode(Node parent) {
		super(parent);
		type = NodeType.TERMINATOR;
		children = new Node[0];
	}
	
	@Override
	public String toString() {
		return "x";
	}
	
	@Override
	public double parse(double input) {
		return input;
	}

}

package science_fair_genetics;

public class InputNode extends AbstractNode {

	protected InputNode() {
		type = NodeType.TERMINATOR;
		children = new Node[0];
	}
	
	@Override
	public double parse(double input) {
		return input;
	}

}

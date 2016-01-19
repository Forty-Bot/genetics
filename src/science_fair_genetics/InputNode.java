package science_fair_genetics;

/**
 * Returns the input when evaluated
 */
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
	public double evaluate(double input) {
		return input;
	}

}

package science_fair_genetics;

/**
 * Multiplies its children together when evaluated.
 */
public class MultiplicationNode extends AbstractNode {

	protected MultiplicationNode(Node parent) {
		super(parent);
		type = NodeType.FUNCTION;
		children = new Node[2];
		operator = "*";
	}
	
	@Override
	public double evaluate(double input) {
		return children[0].evaluate(input) * children[1].evaluate(input);
	}

}

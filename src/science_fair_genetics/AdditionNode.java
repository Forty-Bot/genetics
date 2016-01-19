package science_fair_genetics;

/**
 * Adds its two children together.
 */
public class AdditionNode extends AbstractNode {

	protected AdditionNode(Node parent) {
		super(parent);
		type = NodeType.FUNCTION;
		children = new Node[2];
		operator = "+";
	}
	
	@Override
	public double evaluate(double input) {
		return children[0].evaluate(input) + children[1].evaluate(input);
	}

}

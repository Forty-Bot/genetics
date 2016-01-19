package science_fair_genetics;

/**
 * Divides one of its children by the other
 */
public class DivisionNode extends AbstractNode {

	protected DivisionNode(Node parent) {
		super(parent);
		type = NodeType.FUNCTION;
		children = new Node[2];
		operator = "/";
	}
	
	@Override
	public double evaluate(double input) {
		double rval = children[1].evaluate(input);
		return rval == 0 ? 0 : children[0].evaluate(input) / rval;
	}

}

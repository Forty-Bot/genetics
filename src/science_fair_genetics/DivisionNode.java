package science_fair_genetics;

public class DivisionNode extends AbstractNode {

	protected DivisionNode(Node parent) {
		super(parent);
		type = NodeType.FUNCTION;
		children = new Node[2];
		operator = "/";
	}
	
	@Override
	public double parse(double input) {
		double rval = children[1].parse(input);
		return rval == 0 ? 0 : children[0].parse(input) / rval;
	}

}

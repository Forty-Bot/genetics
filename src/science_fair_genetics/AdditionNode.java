package science_fair_genetics;

public class AdditionNode extends AbstractNode {

	protected AdditionNode() {
		type = NodeType.FUNCTION;
		children = new Node[2];
	}
	
	@Override
	public double parse(double input) {
		return children[0].parse(input) + children[1].parse(input);
	}

}

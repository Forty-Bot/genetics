package science_fair_genetics;

public class SubtractionNode extends AbstractNode {

	protected SubtractionNode(Node parent) {
		super(parent);
		type = NodeType.FUNCTION;
		children = new Node[2];
		operator = "-";
	}
	
	@Override
	public double parse(double input) {
		return children[0].parse(input) - children[1].parse(input);
	}

}

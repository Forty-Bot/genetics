package science_fair_genetics;

public class PowerNode extends AbstractNode {

	protected PowerNode(Node parent) {
		super(parent);
		type = NodeType.FUNCTION;
		children = new Node[2];
		operator = "^";
	}
	
	@Override
	public double evaluate(double input) {
		return Math.pow(children[0].evaluate(input), children[1].evaluate(input));
	}

}

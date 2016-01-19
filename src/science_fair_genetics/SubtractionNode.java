package science_fair_genetics;

/**
 * Subtracts the value of one of its children from the other when
 * {@link Node#evaluate}d.
 */
public class SubtractionNode extends AbstractNode {

	protected SubtractionNode(Node parent) {
		super(parent);
		type = NodeType.FUNCTION;
		children = new Node[2];
		operator = "-";
	}
	
	@Override
	public double evaluate(double input) {
		return children[0].evaluate(input) - children[1].evaluate(input);
	}

}

package science_fair_genetics;

public class ConstantNode extends AbstractNode {

	private final double value;
	
	protected ConstantNode(double value) {
		type = NodeType.TERMINATOR;
		children = new Node[0];
		this.value = value;
	}
	
	public double getValue(){
		return value;
	}
	
	@Override
	public double parse(double input) {
		return value;
	}

}

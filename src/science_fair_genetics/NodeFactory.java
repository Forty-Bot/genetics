package science_fair_genetics;

import java.util.concurrent.ThreadLocalRandom;

public abstract class NodeFactory {
	
	public static Node getTerminator() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		switch(random.nextInt(2)) {
		case 0:
			return new InputNode();
		case 1:
			return new ConstantNode(random.nextDouble(-8,8));
		default:
			throw new RuntimeException("This should never happen.");
		}
	}
	
	public static Node getFunction() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		switch(random.nextInt(2)) {
		case 0:
			return new AdditionNode();
		case 1:
			return new SubtractionNode();
		default:
			throw new RuntimeException("This should never happen.");
		}
	}
	
	public static Node getNode() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextBoolean() ? getTerminator() : getFunction();
	}
	
	public static Node generateFull(int maxDepth) {
		Node root = maxDepth > 1 ? getFunction() : getTerminator();
		for(int i = 0; i < root.numChildNodes(); i++) {
			root.setChildNode(i, generateFull(maxDepth - 1));
		}
		return root;
	}
	
	public static Node generateGrow(int maxDepth) {
		Node root = maxDepth > 1 ? getNode() : getTerminator();
		for(int i = 0; i < root.numChildNodes(); i++) {
			root.setChildNode(i, generateGrow(maxDepth - 1));
		}
		return root;
	}

}

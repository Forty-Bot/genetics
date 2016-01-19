package science_fair_genetics;

import java.util.concurrent.ThreadLocalRandom;

public abstract class NodeFactory {
	
	public static Node getTerminator(Node parent) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		switch(random.nextInt(2)) {
		case 0:
			return new InputNode(parent);
		case 1:
			return new ConstantNode(parent, random.nextDouble(-8,8));
		default:
			throw new RuntimeException("This should never happen.");
		}
	}
	
	public static Node getFunction(Node parent) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		switch(random.nextInt(4)) {
		case 0:
			return new AdditionNode(parent);
		case 1:
			return new SubtractionNode(parent);
		case 2:
			return new MultiplicationNode(parent);
		case 3:
			return new DivisionNode(parent);
		default:
			throw new RuntimeException("This should never happen.");
		}
	}
	
	public static Node getNode(Node parent) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextBoolean() ? getTerminator(parent) : getFunction(parent);
	}
	
	public static Node generateFull(Node parent, int maxDepth) {
		Node root = maxDepth > 1 ? getFunction(parent) : getTerminator(parent);
		for(int i = 0; i < root.numChildNodes(); i++) {
			root.setChildNode(i, generateFull(root, maxDepth - 1));
		}
		return root;
	}
	
	public static Node generateGrow(Node parent, int maxDepth) {
		Node root = maxDepth > 1 ? getNode(parent) : getTerminator(parent);
		for(int i = 0; i < root.numChildNodes(); i++) {
			root.setChildNode(i, generateGrow(root, maxDepth - 1));
		}
		return root;
	}

}

package science_fair_genetics;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Various factory methods for creating random {@link Node}s and populating them.
 */
public abstract class NodeFactory {
	
	/**
	 * Creates a random {@link NodeType#TERMINATOR} which is guaranteed to have
	 * no children. Constants are initialized to a random value between 8 and -8. 
	 * @param parent The parent {@link Node}. May be null if this is the root node.
	 */
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
	
	/**
	 * Creates a random {@link NodeType#FUNCTION} which performs an operation
	 * on its children when evaluated. Children are not auto-generated.
	 * @param parent The parent {@link Node}. May be null if this is the root node.
	 */
	public static Node getFunction(Node parent) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		switch(random.nextInt(5)) {
		case 0:
			return new AdditionNode(parent);
		case 1:
			return new SubtractionNode(parent);
		case 2:
			return new MultiplicationNode(parent);
		case 3:
			return new DivisionNode(parent);
		case 4:
			return new PowerNode(parent);
		default:
			throw new RuntimeException("This should never happen.");
		}
	}
	
	/**
	 * Creates either a {@link NodeType#FUNCTION} or a {@link NodeType#TERMINATOR}
	 * with equal probability.
	 * @param parent The parent {@link Node}. May be null if this is the root node.
	 */
	public static Node getNode(Node parent) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextBoolean() ? getTerminator(parent) : getFunction(parent);
	}
	
	/**
	 * Generates a program filled completely out to {@code maxDepth}.
	 * @param parent The parent {@link Node}. Usually {@code null} if called
	 * from outside code.
	 * @param maxDepth The depth of the {@link Node} tree returned.
	 */
	public static Node generateFull(Node parent, int maxDepth) {
		Node root = maxDepth > 1 ? getFunction(parent) : getTerminator(parent);
		for(int i = 0; i < root.numChildNodes(); i++) {
			root.setChildNode(i, generateFull(root, maxDepth - 1));
		}
		return root;
	}
	
	/**
	 * Generate a program which may be filled completely, or may be cut short before then.
	 * @param parent The parent {@link Node}. Usually {@code null} if called
	 * from outside code.
	 * @param maxDepth The {@link Node} tree generated will never exceed this depth. 
	 */
	public static Node generateGrow(Node parent, int maxDepth) {
		Node root = maxDepth > 1 ? getNode(parent) : getTerminator(parent);
		for(int i = 0; i < root.numChildNodes(); i++) {
			root.setChildNode(i, generateGrow(root, maxDepth - 1));
		}
		return root;
	}

}

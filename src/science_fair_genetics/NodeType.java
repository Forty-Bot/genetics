package science_fair_genetics;

public enum NodeType {
	/**
	 * An endpoint of a {@link Node} tree. Will never have children.
	 */
	TERMINATOR,
	/**
	 * Performs some calculation on its children when evaluated.
	 */
	FUNCTION
}
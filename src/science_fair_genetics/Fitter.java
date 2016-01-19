package science_fair_genetics;

/**
 * Evaluates {@link Node}s on their fitness using some criterion.
 */
public interface Fitter {

	/**
	 * Evaluates a @{link Node}'s fitness.
	 * @param node The program being evaluated
	 * @return A number between 0 and infinity, where 0 is the ideal program,
	 * and infinity is the worst possible program
	 */
	public double getFitness(Node node);
	
	/**
	 * Converts raw fitness from {@link getFitness} to adjusted fitness with
	 * the formula 1/(1+raw).
	 * @param node The program being evaluated
	 * @return A number between 0 and 1, where 1 is the best possible program,
	 * and 0 is the worst possible program
	 */
	public double getAdjustedFitness(Node node);
	
}

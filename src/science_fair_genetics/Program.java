package science_fair_genetics;

/**
 * A container class which holds a {@link Node} and its fitnesses as determined
 * by a {@link Population}.
 */
public class Program {
	/**
	 * The adjusted fitness values as determined by a {@link Population}'s
	 * {@link Fitter}s.
	 */
	public double fitness[];
	public Node node;
	public boolean fitted = false;
	
	public Program(double fitness[], Node node) {
		this.fitness = fitness;
		this.node = node;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} else if(!this.getClass().equals(o.getClass())) {
			return false;
		}
		
		Program p = (Program) o;
		
		return node.equals(p.node);
	}
	
	@Override
	public int hashCode() {
		return node.hashCode();
	}

	/**
	 * Fit a program using the {@link Population}'s fitters.
	 * @param p The program to be fitted
	 */
	public void fit(Fitter[] fitters) {
		if(fitness.length != fitters.length) {
			throw new IllegalArgumentException("The length of our internal "
					+ "fitness array is " + fitness.length + " but the "
					+ "length of the fitters we are to be fitted to is "
					+ fitters.length + ".");
		}
		for(int i = 0; i < fitters.length; i++) {
			fitness[i] = fitters[i].getAdjustedFitness(node);
		}
		fitted = true;
	}
}
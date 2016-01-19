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
}
package science_fair_genetics;

public class Program {
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
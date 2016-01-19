package science_fair_genetics;

import java.util.TreeMap;
import java.util.Map.Entry;

public class DataFitter extends AbstractFitter {

	private TreeMap<Double, Double> data;
	
	public DataFitter(TreeMap<Double, Double> data) {
		this.data = data;
	}
	
	@Override
	public double getFitness(Node node) {
		double sum = 0;
		for(Entry<Double, Double> e: data.entrySet()) {
			double in = e.getKey();
			double expected = e.getValue();
			double actual = node.parse(in);
			sum += Math.pow((expected - actual), 2);
		}
		return sum;
	}

}

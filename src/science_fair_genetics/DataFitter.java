package science_fair_genetics;

import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Determines how close a {@link Program} is to a set of data using the sum
 * of the squared difference between returned and expected values.
 */
public class DataFitter extends AbstractFitter {

	private TreeMap<Double, Double> data;
	
	/**
	 * Creates a new {@link DataFitter} from a given set of data.
	 * @param data The input and expected output to fit to.
	 */
	public DataFitter(TreeMap<Double, Double> data) {
		this.data = data;
	}
	
	/**
	 * For each target pair of data, evaluate the output of the program, and
	 * compare it to the expected value. 
	 * @param node The program to be evaluated
	 * @return The sum of the squared differences between the target and
	 * returned value.
	 */
	@Override
	public double getFitness(Node node) {
		double sum = 0;
		for(Entry<Double, Double> e: data.entrySet()) {
			double in = e.getKey();
			double expected = e.getValue();
			double actual = node.evaluate(in);
			sum += Math.pow((expected - actual), 2);
		}
		return sum;
	}

}

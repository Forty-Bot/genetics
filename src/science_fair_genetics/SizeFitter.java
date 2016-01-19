package science_fair_genetics;

public class SizeFitter extends AbstractFitter {

	@Override
	public double getFitness(Node node) {
		double size = 1;
		for(Node child : node.getChildNodes()) {
			size += getFitness(child);
		}
		return size;
	}

}

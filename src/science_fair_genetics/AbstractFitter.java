package science_fair_genetics;

public abstract class AbstractFitter implements Fitter {

	@Override
	public double getAdjustedFitness(Node node) {
		return 1 / ( 1 + getFitness(node));
	}

}

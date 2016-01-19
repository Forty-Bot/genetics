package science_fair_genetics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Population {
	
	public static final int DEFAULT_SIZE = 512;
	public static final int DEFAULT_MAX_DEPTH = 10;
	public static final int DEFAULT_MIN_DEPTH = 4;
	public static final int DEFAULT_TOURNAMENT_SIZE = 16;
	
	public class Program {
		public double fitness[];
		public Node node;
		public boolean fitted = false;
		public Program(double fitness[], Node node) {
			this.fitness = fitness;
			this.node = node;
		}
	}
	
	private ArrayList<Program> population;
	private Fitter[] fitters;
	private int tournamentSize;
	
	/*
	 * Uses ramped half-and-half generation
	 */
	public Population(Fitter[] fitters, int size, int maxDepth,
			int minDepth, int tournamentSize) {
		population = new ArrayList<Program>(size);
		this.fitters = fitters;
		this.tournamentSize = tournamentSize;
		
		// The amount of times we run each generator for each depth
		int group_size = size/(2*(maxDepth - minDepth + 1));
		boolean usingFull = false;
		int depth = minDepth - 1;
		for(int i = 0; i < size; i++) {
			if(i % group_size == 0 && depth != maxDepth){
				depth++;
				usingFull = !usingFull;
			}
			population.add(i, new Program(new double[fitters.length], 
					usingFull ? NodeFactory.generateFull(depth) :
					NodeFactory.generateGrow(depth)));
		}
	}
	
	public Population(Fitter[] fitters, int size, int maxDepth,
			int minDepth) {
		this(fitters, size, maxDepth, minDepth, DEFAULT_TOURNAMENT_SIZE);
	}
	
	public Population(Fitter[] fitters, int size, int maxDepth) {
		this(fitters, size, maxDepth, DEFAULT_MIN_DEPTH);
	}
	
	public Population(Fitter[] fitters, int size) {
		this(fitters, size, DEFAULT_MAX_DEPTH);
	}
	
	public Population(Fitter[] fitters) {
		this(fitters, DEFAULT_SIZE);
	}
	
	public Program[] tournament(int tournamentSize) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		
		// Get some contestants using Knuth's shuffling algorithm
		for(int i = 0; i < tournamentSize; i++) {
			int pos = i + random.nextInt(population.size() - i);
			Program tmp = population.get(pos);
			population.set(pos, population.get(i));
			population.set(i, tmp);
		}
		ArrayList<Program> contestants = 
				new ArrayList<Program>(population.subList(0,
				tournamentSize));
		
		// Find the Pareto frontier
		ArrayList<Program> nonDominated = new ArrayList<Program>();
		outer:
		for(Program p: contestants) {
			if(!p.fitted) {
				fit(p);
			}
			for(Iterator<Program> i = nonDominated.iterator(); i.hasNext();) {
				boolean pdominated = true;
				boolean qdominated = true;
				Program q = i.next();
				for(int j = 0; j < fitters.length; j++) {
					if(p.fitness[j] > q.fitness[j]) {
						pdominated = false;
					} else if(p.fitness[j] < q.fitness[j]) {
						qdominated = false;
					}
				}
				// If p is dominated, ignore it
				if(pdominated && !qdominated) {
					continue outer;
				// If q is dominated, remove it
				} else if (!pdominated && qdominated) {
					i.remove();
				}
			}
			// p was never dominated
			nonDominated.add(p);
		}
		return nonDominated.toArray(new Program[nonDominated.size()]);
	}
	
	public Program[] tournament() {
		return tournament(this.tournamentSize);
	}
	
	public void fit(Program p) {
		for(int i = 0; i < fitters.length; i++) {
			p.fitness[i] = fitters[i].getAdjustedFitness(p.node);
		}
		p.fitted = true;
	}

}

package science_fair_genetics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Population {
	
	public static final int DEFAULT_MAX_DEPTH = 10;
	public static final int DEFAULT_STARTING_DEPTH = 4;
	
	private HashSet<Program> population;
	/**
	 * Creates a new population of programs which may be operated on with the
	 * given {@link Fitter}s to determine fitness. Programs are created by
	 * running {@link NodeFactory#generateFull} and {@link NodeFactory#generateGrow}
	 * in equal parts for each depth between {@code startingDepth} and {@code maxDepth}. 
	 * @param size The amount of programs to generate.
	 * @param maxDepth The maximum depth of the @{link Node} tree.
	 * @param startingDepth The initial maximum depth used when generating programs.
	 */
	public Population(HashSet<Program> population) {
		this.population = population;
	}
	
	public static HashSet<Program> generatePopulation(Fitter[] fitters, int size,
			int maxDepth, int startingDepth) {
		HashSet<Program> population = new HashSet<Program>();
		// The amount of times we run each generator for each depth
		int group_size = size/(2*(maxDepth - startingDepth + 1));
		boolean usingFull = false;
		int depth = startingDepth - 1;
		// Fill the population with programs, half full and half grown, at varying depths
		for(int i = 0; i < size; i++) {
			if(i % group_size == 0 && depth != maxDepth){
				depth++;
				usingFull = !usingFull;
			}
			// Create new programs until we get an unique one
			while(!population.add(new Program(new double[fitters.length], 
				usingFull ? NodeFactory.generateFull(null, depth) :
				NodeFactory.generateGrow(null, depth))));
		}
		return population;
	}
	
	public static HashSet<Program> generatePopulation(Fitter[] fitters, int size,
			int maxDepth) {
		return generatePopulation(fitters, size, maxDepth, DEFAULT_STARTING_DEPTH);
	}
	
	public static HashSet<Program> generatePopulation(Fitter[] fitters, int size) {
		return generatePopulation(fitters, size, DEFAULT_MAX_DEPTH);
	}
	
	/**
	 * Gets an {@code amount} of programs randomly from the {@link Population}. 
	 * @param amount The amount of programs to retrieve.
	 */
	public Program[] getRandomPrograms(int amount) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		
		Program[] selected = new Program[amount];
		int seen = 0;
		for(Program p: population) {
			seen++;
			// Fill the first amount items in the array with programs, then randomly select programs
			int index = seen > amount ? random.nextInt(seen) : seen - 1;
			if(index < amount) {
				selected[index] = p;
			}
		}
		return selected;
	}	
	
	/**
	 * Runs a tournament to find the nondominated programs (Pareto Frontier).
	 */
	public static Program[] tournament(Program[] contestants, Fitter[] fitters) {
		ArrayList<Program> nonDominated = new ArrayList<Program>();
		outer:
		for(Program p: contestants) {
			if(!p.fitted) {
				p.fit(fitters);
			}
			//Use an iterator so we can remove dominated programs from nonDominated
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

}

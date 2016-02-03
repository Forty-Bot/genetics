package science_fair_genetics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws FileNotFoundException,
	IOException, CloneNotSupportedException {
		
		BufferedReader reader =
				new BufferedReader(new FileReader(
				new File("/home/sean/school/12/phys/gamma.csv")));
		TreeMap<Double, Double> data = new TreeMap<Double, Double>();
		for(String input = reader.readLine();; input = reader.readLine()) {
			if(input == null) {
				break;
			}
			String[] values = input.split(",");
			data.put(Double.valueOf(values[0]), Double.valueOf(values[1]));
		}
		reader.close();
		
		Fitter[] fitters = {new SizeFitter(), new DataFitter(data)};
		Population p = new Population(Population.generatePopulation(fitters, 512));
		for(int i = 0; i < 20; i++) {
			Program[] nonDominated = Population.tournament(p.getRandomPrograms(16), fitters);
			System.out.println("Tournament "+i+":");
			for(Program prog: nonDominated) {
				System.out.println("\t" + java.util.Arrays.toString(prog.fitness)
				+ "\t" + prog.node.toString());
			}
		}
	}

}

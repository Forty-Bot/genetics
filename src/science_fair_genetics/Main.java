package science_fair_genetics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws FileNotFoundException,
	IOException {
		BufferedReader reader =
				new BufferedReader(new FileReader(
				new File("/home/sean/school/12/phys/gamma.csv")));
		HashMap<Double, Double> data = new HashMap<Double, Double>(1001);
		for(String input = reader.readLine();; input = reader.readLine()) {
			if(input == null) {
				break;
			}
			String[] values = input.split(",");
			data.put(Double.valueOf(values[0]), Double.valueOf(values[1]));
		}
		reader.close();
		
		Fitter[] fitters = {new SizeFitter(), new DataFitter(data)};
		Population p = new Population(fitters);
		for(int i = 0; i < 20; i++) {
			Population.Program[] nonDominated = p.tournament();
			System.out.println("Tournament "+i+":");
			for(Population.Program prog: nonDominated) {
				System.out.println("\t"+prog.fitness[0]+"\t"+prog.fitness[1]);
			}
		}
	}

}

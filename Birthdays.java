import java.util.ArrayList;
import java.util.Random;

public class Birthdays {
	
	public static double findSameBday(int size) {
		double chance = 0;
		Random rand = new Random();
		ArrayList<Integer> bdays = new ArrayList<>();
		ArrayList<Double> chances = new ArrayList<>();
		double count = 0;
		
		//Run the simulation 1000 times
		for (int i = 0; i < 1000; i++) {
			
			//Populate bdays array with "size" number of birthdays
			for (int j = 0; j < size; j++) {
				
				//I'll leave it as 365 to account for leap years
				bdays.add(rand.nextInt(365));
			}
			
			//Check for duplicates
			for (int b: bdays) {
				for (int a = 1; a < size; a++) {
					if(b == bdays.get(a)) {
						count++;
					}
				}
			}
		
			chance = count/1000.0;
			chances.add(chance*size);
			count = 0;
			bdays.clear();
			
		}
		
		//Calculate probability
		chance = StatsLibrary.findMean(chances);
		System.out.println("Chances are: " + chance);
		
		return chance;
	}
	
}

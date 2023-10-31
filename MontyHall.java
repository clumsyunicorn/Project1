import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MontyHall {
	public static String good = "G";
	
	//create doors
	public static ArrayList<String> door(){
		ArrayList<String> doors = new ArrayList<>();
		doors.add("G");
		doors.add("D1");
		doors.add("D2");
		return doors;
	}
	
	public static double noChange() {
		//define variables
		String choice;
		double wins = 0;
		double outcome = 0;
		
		//populate doors array with doors
		ArrayList<String> doors = new ArrayList<>();
		doors = door();
		Collections.shuffle(doors);
		
		Random rand = new Random();
		for (int i = 0; i < 10000; i++) {
			 choice = doors.get(rand.nextInt(doors.size()));
			 
			 if (choice.equals(good)) {
				 	wins++;
			 }
		 }
		
		 
		outcome = (wins/10000.0);
		
		return outcome;
		
	}
	
	public static double change() {
		//variables for doors
		int first = 0;
		int second = 0;
		int temp = 0;
		
		//variables for calculation
		double wins = 0;
		double outcome = 0;
		
		Random rand = new Random();
		
		for (int i = 0; i < 10000; i++) {
			
			ArrayList<String> doors = door();
			
			Collections.shuffle(doors);
			
			//pick first door with random index
			first = rand.nextInt(doors.size());
			
			//check if it's the right door
			for(int j = 0; j < doors.size(); j++) {
				if(!doors.get(j).equals(good) && j != first) {
					temp = j;
				}
			}
			
			//if not, mark the door as checked
			doors.set(temp, "X");
			
			//select a different door
			for(int j = 0; j < doors.size(); j++) {
				if(!doors.get(j).equals("X") && j != first) {
					second = j;
				}
			}
			
			if (doors.get(second).equals(good)) {
				wins++;
			}

		}
		 
		outcome = (wins/10000.0);

		return outcome;
	}
	
}

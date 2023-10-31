import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class Factory {
	
	//Car type requirements
	private static String[] CarTypes = {"Sedan", "Toyota", "Honda", "Chevrolet", "Sedan", "Lamborghini"};
	private static int minYear = 1975;
	private static int maxYear = 2023;
	private static String[] Color = {"Blue", "Green", "Yellow", "Red"};
	private static int maxMiles = 250000;
	
	private static String filename = "src/cars.csv";
	
	//Generate car
	public static Car generateCar() {
		Random random = new Random();
		
		//generate random car type from CarType array
		String carType = CarTypes[random.nextInt(CarTypes.length)];
		
		//generate random year starting from 1975
		int year = random.nextInt(minYear, maxYear);
		
		//generate random color from Color array
		String color = Color[random.nextInt(Color.length)];
		
		//generate random miles 
		int miles = random.nextInt(maxMiles);
		
		//generate random color from Color array
		return new Car(carType, year, color, miles);
	}
	
	//This part I got from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
	public static void exportCars(int num) {
		
		try 
		{
			FileWriter writeFile = new FileWriter(filename);
			BufferedWriter writer = new BufferedWriter(writeFile);
			
			//headers
			writer.write("CarType,Year,Color,Miles\n");
			
			//generate a given number of cars and write them to CSV file
			for(int i=0; i < num; i++) {
				Car car = generateCar();
				writer.write(car.getCarType() + "," + car.getYear() + "," + car.getColor() + "," + car.getMiles() + "\n");
				System.out.println("Generated car: " + car.getCarType() + ", " + car.getYear() + ", " + car.getColor() + ", " + car.getMiles());
			}
			
			//Close the BufferedWriter
			writer.close();
			writeFile.close();
		}
		catch(IOException except) {
			System.out.println("Something went wrong.");
			except.printStackTrace();
		}
	}
	
	//This part I got from https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
	public static void importCars() {
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			reader.readLine();
			String line;
			
			while((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				String carType = data[0];
				int year = Integer.parseInt(data[1]);
				String color = data[2];
				int miles = Integer.parseInt(data[3]);
				Car car = new Car(carType, year, color, miles);
				System.out.println("Loaded car: " + car.getCarType() + ", " + car.getYear() + ", " + car.getColor() + ", " + car.getMiles());
				
			}
			
			//Close the BufferredReader
			reader.close();
		}
		catch(IOException except) {
			System.out.println("Something went wrong.");
			except.printStackTrace();
		}
	}
	
}

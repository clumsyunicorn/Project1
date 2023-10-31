
public class Car {

	//Global Variables
	private String carType;
	private int year;
	private String color;
	private int miles;
	
	//Constuctor
	public Car(String carType, int year, String color, int miles) {
		this.carType = carType;
		this.year = year;
		this.color = color;
		this.miles = miles;
	}
	
	public Car() {
		// TODO Auto-generated constructor stub
	}

	public String getCarType(){
		return carType;
	}
	
	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getMiles() {
		return miles;
	}
	
	public void setMiles(int miles) {
		this.miles = miles;
	}
	
	public String toString(){
		return getCarType()+ ", " + getYear() + ", " + getColor() + ", " + getMiles();
	}

}

package inheritance;

public abstract class Vehicle {
	// the java Object class, is the super class of every class in java
	
	
	// we have moved the common properties from the child/sub-class into a parent/base-class Vehicle
	private String color;		// this was made private, so setters and getters must be created because private variables are not inherited
	public int maxSpeed;
	protected int minSpeed;
	
	// to avoid errors make a default vehicle constructor and an implicit vehicle constructor 
	
	public Vehicle(int num) {
		this.maxSpeed=num;
		System.out.println("Vehicles constructor");
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void print() {
		System.out.println("Vehicle " + "color "+ color+" maxspeed "+ maxSpeed);
	}
	
	public int getMaxSpeed() {
		return maxSpeed;
	}
	
	public abstract boolean isMotorized();
}

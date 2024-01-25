

package inheritance;
//to indicate inheritance, use 'extends' keyword
public class car extends Vehicle{
	
	int numDoors;
	
	int maxSpeed;
	
	public car(int numDoors, int maxSpeed) {   // compile error because superclass is called and the superclass constructor requires properties to be defined which are not being defined here
//		super(maxSpeed);
		System.out.println("cars constructor");
		this.numDoors = numDoors;
	}
	
	public void printCar() {
		System.out.println("car " + "color "+ getColor() +" maxspeed "+ maxSpeed+ " numDoors " + numDoors);
	}
	
	public void print() {
		super.print();  // super refers to the parent class, and will cause the print function from parent to execute
		System.out.println("This is an example of function overriding, print() is defined in vehicle and car, but car.print() will override and run first if it exists in car");
	}
	
	public void printMaxSpeed() {
		super.maxSpeed=150; //sets parent class maxSpeed
		System.out.println(maxSpeed);  // will print cars maxSpeed
		System.out.println(super.maxSpeed);  // will print parent class maxSpeed
	}
	
	@Override
	public boolean isMotorized() { // completes the incomplete method isMorotrized in abstract class Vehicle
		return true;
	}
	
	
}

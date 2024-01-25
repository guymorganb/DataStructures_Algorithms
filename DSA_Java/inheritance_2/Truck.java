package inheritance_2;
import inheritance.Vehicle;
public class Truck extends Vehicle {
	
	int maxCapacity;
	
	
	// color and maxSpeed have default scope which is available only inside its package, so we need to use a getColor() which has public scope
	public void print() {
		// minSpeed is available here because Truck is a class derived from Vehicle class, and minSpeed has protected scope
		System.out.println("Vehicle " + "color "+ getColor() +" maxspeed "+ maxSpeed + " maxCapacity " + maxCapacity + " minSpeed " + minSpeed);
	}
	
	public Truck(int maxCapacity, int maxSpeed) {
		super(maxSpeed);
		System.out.println("cars constructor");
		this.maxCapacity = maxCapacity;
	}
}

package inheritance;

public class VehicleUse {

	public static void main(String[] args) {
		
		Vehicle v = new Vehicle(30);		// output is "Vehicles constructor"// requires maxSpeed argument
		
		v.setColor("red");
		v.maxSpeed = 85;
		v.print();
		// car extends vehicle class, and the properties from vehicle will be available in car
		car c = new car(4, 100);				// output is "vehicles constructor" and "cars constructor", whenever you call constructor of child class, it calls constructor of super class
		
		c.setColor("Black");
		c.maxSpeed = 100;
		c.numDoors = 4;
		c.print();
//		c.printCar();
		// bicycle extends vehicle class, and the properties from vehicle will be available in bicycle
		bicycle b = new bicycle();
		
		b.setColor("Green");
		b.print();

	}

}

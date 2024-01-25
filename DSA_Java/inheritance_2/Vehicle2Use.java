package inheritance_2;

import inheritance.Vehicle;

public class Vehicle2Use extends Vehicle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle v = new Vehicle();
		
		v.maxSpeed = 10;
		
		v.minSpeed = 5;	// cannot use minSpeed here because Vehicle2Use is not a derived class of Vehicle.
	}

}
// Solve:

//package p1;
//
//public class Vehicle {
//    protected String name;
//}

//Which of the following code(s) will give error?
//A. 
//package p1;
//
//public class Car {
//    void props() {
//        Vehicle v = new Vehicle();
//        v.name = "Vehicle 1st";      
//    }
//}
//
//B. 
//package p2;
//
//class Car{
//    public static void main(String args[]) {
//        Car c = new Car();
//        c.name = "Ferrari";
//    }
//}
//
//C. 
//package p2;
//
//import p1.Vehicle;
//class Car {
//    void props() {
//        Vehicle v = new Vehicle();
//        v.name = "BMW";
//    }
//}
//D. 
//package p2;
//
//class Car extends Vehicle {
//    public static void main(String args[]) {
//        Car c = new Car();
//        c.name = "Ferrari";
//    }
//}

//Solution Description
//Option B defines a class Car in package p2 which cannot directly access the protected variable name from Vehicle since Car is not a subclass of Vehicle and is in a different package.
//
//
//
//
//Option C defines a class Car in package p2 that imports the Vehicle class from package p1. 
//Since Car is not a subclass of Vehicle and is not in the same package as Vehicle, it cannot directly access the protected variable name. Therefore, option C will give an error.
package generics;

public class pairUse {

	public static void main(String[] args) {
		
//		Pair p = new Pair(10, 20);
//		System.out.println(p.getFirst() + " " + p.getSecond());
//		PairString pS = new PairString("aa", "bb");
//		PairDouble pS = new PairDouble(3.1, 7.2);
		// <String> will be replaced at all instances of T in the constructor
		Pair<String> pS = new Pair<String>("aa", "bb");
		System.out.println(pS.getFirst() + " " + pS.getSecond());
		pS.setFirst("10");
		
		// cannot write primitive data types (char, double, int) must use Integer/Character/Double (wrapper class)
		Pair<Integer> pI = new Pair<Integer>(10,20);
		System.out.println(pI.getFirst() + " " + pI.getSecond());
		
		Pair<Double> pD = new Pair<Double>(10.1,20.2);
		System.out.println(pD.getFirst() + " " + pD.getSecond());
	
		// could make a pair of vehicle
//		Pair<Vehicle> pV = new Pair<vehicle>(new Vehicle(), new Vehicle());
	
		// chained generic pairs
		Pair<String, Integer> pInner = new Pair<String, Integer>();
		Pair<Pair<String, Integer>, String> p = new Pair<>();
		
		p.setFirst(pInner);
		System.out.println(p.getFirst());
		p.getSecond("abcd");
		System.out.println(p.getSecond());
		
	
	}

}

package generics;

public class sumUpNumsofList {
	
	public static int cost(List <Integer> num) {
	    int sum = 0;
	    for (Integer number : num) {
	        sum += number;
	    }
	    return sum;
	}
	//  more modern and functional programming style, you can use Java's stream API to calculate the sum:
	public static int cost(List <Integer> num) {
	    return num.stream().mapToInt(Integer::intValue).sum();
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

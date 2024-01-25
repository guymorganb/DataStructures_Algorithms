

package Recursion;

public class recursion {

	public static int factorial(int n) {
		if(n==0) {
			return 1;
		}
		
		int smallerOutput = factorial(n-1);
		int output = n * smallerOutput;
		return output;
	}
	
	
	public static void main(String[] args) {
		System.out.println(factorial(5));

	}

}

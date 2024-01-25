package Recursion;

public class PrintNums1toN {
	
	//base case n=0 - at this point do nothing
	
	public static void printNums(int n) {
		if(n == 0) {
			return;
		}
		printNums(n-1);
		System.out.println(n);
	}
	
	public static void main(String[] args) {
	
		printNums(10);

	}

}
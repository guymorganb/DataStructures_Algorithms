package Recursion;

public class StackSizeExample {
	
	static int count = 0;

	public static void printNums(int n) {
		if(n == 0) {
			return;
		}
		count++;
		printNums(n-1);
		System.out.println(n);
	}
	
	public static void main(String[] args) {
		try {
			printNums(350000);
		} catch (StackOverflowError e) {
			System.out.println("Max depth reached: " + count);
			//System.exit(1); // exit the program as it's generally not safe to continue after a StackOverflowError
		}
	}
}

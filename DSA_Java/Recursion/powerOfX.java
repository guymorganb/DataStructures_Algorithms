package Recursion;
import java.util.Scanner;
public class powerOfX {
	
	public static int powerOfX(int x, int n) {
		if(n == 0) {
			return 1;
		}
		int smallOutput = powerOfX(x, n-1);
		int output = x * smallOutput;
		return output;
	}
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		int n = s.nextInt();
		System.out.println(powerOfX(x,n));
	}

}

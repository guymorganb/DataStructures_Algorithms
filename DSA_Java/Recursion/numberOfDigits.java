package Recursion;

import java.util.Scanner;

public class numberOfDigits {
	
	public static int count(int n) {
		if(n < 10) {
			return 1;
		}
		int smallOutput = count(n/10);
		return smallOutput + 1;
		
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		System.out.println(count(n));
	}

}

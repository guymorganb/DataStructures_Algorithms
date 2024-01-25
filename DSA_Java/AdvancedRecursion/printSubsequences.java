package AdvancedRecursion;

import java.util.Scanner;

public class printSubsequences {

	public static void stringSubsequences (String input, String stringSoFar) {
		if(input.length() == 0) {
			System.out.println(stringSoFar);
			return;
		}
		// study this
		String smallInput = input.substring(1);
		stringSubsequences(smallInput, stringSoFar);
		stringSubsequences(smallInput, stringSoFar + input.charAt(0));
				
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a string to get its subsequences");
		String str = s.nextLine();
		
		stringSubsequences(str, "");
		
		s.close();

	}

}

package AdvancedRecursion;

import java.util.Scanner; 
public class return_Subsequences {
		
	public static String[] subsequences (String input) {
		if(input.length() == 0) {
			// base case
			String[] output = new String[1];
			output[0] = "";
			return output;
		}
		
		String[] smallOutput = subsequences(input.substring(1));
		String[] output = new String[smallOutput.length * 2]; // double the size to fit all subsequences
		
		// copy small output into the output array, output length is 2, smallOutput length is 1
		for(int i = 0; i < smallOutput.length; i++) {
			output[i] = smallOutput[i];
		}
		// append the beginning char of input to the last char
		for(int i = 0; i < smallOutput.length; i++) {
			// copy into the new array 
			output[smallOutput.length + i] = input.charAt(0) + smallOutput[i]; // append the first char we skipped over back to the string
		}
		return output;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a string");
		String input = s.next();
		
		String[] output = subsequences(input);
		for(String outputString: output) {
			System.out.println(outputString);
		}
	}

}

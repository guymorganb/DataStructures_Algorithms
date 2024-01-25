package AdvancedRecursion;

import java.util.Scanner;

public class printKeypad_recursiveGoingDown {

	public static String lastDigitToString(int input) {	
		
		switch (input) {
			case 0:
			case 1:
				return "";  // Make it an empty string
			   
			case 2:
				return "abc";
				
			case 3:
				return "def";
				
			case 4: 
				return "ghi";
				
			case 5: 
				return "jkl";
				
			case 6:
				return "mno";
				
			case 7 :
				return "pqrs";
				
			case 8:
				return "tuv";
				
			case 9:
				return "wxyz";
			default:
				return "";
				
			}
		}
	
	public static void printKeypadCombinaions(int input, String stringSoFar) {
		if(input == 0) {
			System.out.println(stringSoFar);
			return;
		}
		// logic is to call recursion as many times as the digit has inputs
		int lastDigit = input % 10;
		int smallInput = input/10;
		String optionsLastDigit = lastDigitToString(lastDigit);
		for(int i = 0; i < optionsLastDigit.length(); i++) {
			printKeypadCombinaions(smallInput, optionsLastDigit.charAt(i) + stringSoFar);
		}
		
	}
	
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Input a number");
		int input = s.nextInt();
		
		printKeypadCombinaions(input, "");
		
		s.close();
		
	}

}

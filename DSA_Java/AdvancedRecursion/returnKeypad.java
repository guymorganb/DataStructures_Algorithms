package AdvancedRecursion;
import java.util.Scanner;

public class returnKeypad {
		
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
	
	public static String[] keypadCombinations(int input) {
		if(input == 0) {
			String[] output = new String[1];
			output[0] ="";
			return output;
		}
		
		String[] smallOutput = keypadCombinations(input/10);
		int lastDigit = input % 10;
		String lastDigitString = lastDigitToString(lastDigit);
		
		String[] NumberOfOptions = new String[smallOutput.length * lastDigitString.length()];
		
		// copy the last digit into the array
		int k = 0;
		for(int i = 0; i < smallOutput.length; i++) {
			
			for(int j = 0; j < lastDigitString.length(); j++) {
				NumberOfOptions[k] = smallOutput[i] + lastDigitString.charAt(j);
				k++;
			}
		}
		return NumberOfOptions;
		
	}
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Input a number");
		int input = s.nextInt();
		String[] output = keypadCombinations(input);
		for(String outputString : output) {
			System.out.println(outputString.length());
		}
		
		s.close();

	}

}

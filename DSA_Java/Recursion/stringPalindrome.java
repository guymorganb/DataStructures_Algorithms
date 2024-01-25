package Recursion;

public class stringPalindrome {
	
	   public static boolean isPalindrome(String s) {
	        if (s.length() <= 1) {
	            return true;
	        }
	        
	        char firstChar = s.charAt(0);
	        char lastChar = s.charAt(s.length() - 1);
	        
	        if (firstChar == lastChar) {
	            String subString = s.substring(1, s.length() - 1);
	            return isPalindrome(subString);
	        } else {
	            return false;
	        }
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "racecar"; // Change this string to test different cases
        boolean isPalin = isPalindrome(input);
        
        System.out.println("Is \"" + input + "\" a palindrome? " + isPalin);
	}

}

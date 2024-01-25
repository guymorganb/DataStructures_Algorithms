package Recursion;
//Suppose you have a string, S, made up of only 'a's and 'b's. Write a recursive function that checks if the string was generated using the following rules:
//a. The string begins with an 'a'
//b. Each 'a' is followed by nothing or an 'a' or "bb"
//c. Each "bb" is followed by nothing or an 'a'
//If all the rules are followed by the given string, return true otherwise return false.
public class checkString {
    public static boolean checkString(String s) {
        // base cases
        if (s.length() == 0) {
            return true;
        } else if (s.charAt(0) != 'a') {
            return false;
        } else if (s.length() == 1) {
            return true;
        }

        // recursive cases
        if (s.charAt(1) == 'a') {
            return checkString(s.substring(1));
        } else if (s.length() > 2 && s.substring(1, 3).equals("bb")) {
            if (s.length() == 3 || s.charAt(3) == 'a') {
                return checkString(s.substring(3));
            }
        }

        return false;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println(checkString("a"));     // Output: true
        System.out.println(checkString("aabba")); // Output: true
        System.out.println(checkString("aab"));   // Output: false
        System.out.println(checkString("bba"));   // Output: false
	}

}

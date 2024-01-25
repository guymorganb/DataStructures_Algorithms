package Recursion;
//Given a string S, compute recursively a new string where identical chars 
//that are adjacent in the original string are separated from each other by a "*".
public class pairStar {
    public static String addStars(String s) {
        // base case: if the string is empty or has only one character, return the string as it is
        if (s.length() <= 1) {
            return s;
        }
        // recursive case: if the current character is the same as the next one, insert a '*' between them
        if (s.charAt(0) == s.charAt(1)) {
            return s.charAt(0) + "*" + addStars(s.substring(1));
        } else {
            return s.charAt(0) + addStars(s.substring(1));
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	      System.out.println(addStars("aaaa"));  // Output: "a*a*a*a"
	      System.out.println(addStars("abcdd"));  // Output: "abcd*d"
	}

}

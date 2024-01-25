package Recursion;

public class stringRecursion {
	// take a string and replace all occurrences of char a with char b
	public static String replace(String s, char a, char b) {
		if(s.length() == 0) { // this is returning an empty string
			return s;
		}
		String smallOutput = replace(s.substring(1), a, b); // .substring method takes a starting index and goes to the end or, takes a start and end index. 
		
		System.out.println("small : " + smallOutput);
		System.out.println("s : " + s);
		
		if (s.charAt(0) == a) {
			return b + smallOutput; // at first we are appending to an empty string and this order matters
		}else {
			return s.charAt(0) + smallOutput;
		}
	}
	/////////////////////2 function////////////////////////////////
	public static String removeDuplicates(String s) {
		// remove consecutive duplicates
		if(s.length() <= 1) {
			System.out.println("hit");
			return s;
		}
	
		String smallStr = removeDuplicates(s.substring(1));
		if(s.charAt(0) == smallStr.charAt(0)) {
			return  "" + smallStr ;
		}
		return s.charAt(0) + smallStr ;
	}
	public static void main(String[] args) {
//		String s = "abxcdxedx";
//		System.out.println(replace(s, 'x', 'y'));
		String t = "abbbcdefff";
		System.out.println(removeDuplicates(t));
	}

}

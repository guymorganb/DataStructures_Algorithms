package Recursion;

public class removeXinString {
	// remove target char from a string
	public static String removeX(String str) {
		if(str.length() == 0) {
			return str;
		}
		String smallerOutput = removeX(str.substring(1));
		
		//System.out.println("small : " + smallerOutput);
		//System.out.println("str : " + str);
		
		
		if(str.charAt(0) == 'x') {
			return smallerOutput + ""; // changed from str.charAt(0) to smallerOutput and it worked, why?
			// because we are building smallerOutput 1 char at a time and checking if each charAt(0) is out target
		}else {
			return str.charAt(0) + smallerOutput;
		}
	}
	public static void main(String[] args) {
		String s = "abxcdxedx";
		System.out.println(removeX(s));

	}

}

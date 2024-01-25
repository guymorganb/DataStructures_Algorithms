package Recursion;

public class replacePi {
	// replace instances of 'pi' in a string with '3.14'
	public static String replacePi(String str) {
		if(str.length() <=1 ) {
			return str;
		}
		if(str.charAt(0) == 'p' && str.charAt(1) == 'i') {
			// call recursion on length n-2 for case "pipi"
			String smallOutput = replacePi(str.substring(2));
			return "3.14" + smallOutput;
		}else {
			//call recursion on string length n-1
			String smallOutput = replacePi(str.substring(1));
			return str.charAt(0) + smallOutput;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(replacePi("apiapiapipipi"));
	}

}

package Recursion;

public class sumOfDigits {

	public static int sum(int n){
		if(n == 0){
			return n;
		}

		int smallSum = sum(n / 10);
	    int digit = n % 10;
	        
	    return smallSum + digit;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int total = sum(12345);
		System.out.println(total);
	}

}

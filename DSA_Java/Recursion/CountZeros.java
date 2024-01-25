package Recursion;

public class CountZeros {
	
	public static int countZeros(int N) {
        if (N == 0) {
            return 1;
        }
        
        if (N < 10) {
            return 0;
        }
        
        int lastDigit = N % 10;
        int zerosInRest = countZeros(N / 10);
        
        if (lastDigit == 0) {
            return zerosInRest + 1;
        } else {
            return zerosInRest;
        }
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int zeros = countZeros(102304);
	     System.out.println("Number of zeros: " + zeros);
	}

}

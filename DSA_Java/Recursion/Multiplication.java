package Recursion;

//Given two integers M & N, 
//calculate and return their multiplication using recursion. 
//You can only use subtraction and addition for your calculation. 
//No other operators are allowed.

public class Multiplication {
	
    public static int multiply(int M, int N) {
        if (N == 0) {
            return 0;
        }
        
        if (N > 0) {
            return M + multiply(M, N - 1);
        } else {
            return -multiply(M, -N);
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int result = multiply(5, 4);
	     System.out.println("Multiplication result: " + result);
	}

}

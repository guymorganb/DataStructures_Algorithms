package Recursion;

public class geometricSum {
	public static double calculateGeometricSum(int k) {
        if (k == 0) {
            return 1;
        }
        
        double previousSum = calculateGeometricSum(k - 1);
        double currentTerm = 1 / Math.pow(2, k);
        
        return previousSum + currentTerm;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int k = 5; // Change this value to your desired k
		 double sum = calculateGeometricSum(k);
		 System.out.println(sum);
	}

}

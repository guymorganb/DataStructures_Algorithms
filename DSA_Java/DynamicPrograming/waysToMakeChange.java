package DynamicPrograming;

public class waysToMakeChange {

	 public static int countWaysToMakeChange(int denominations[], int value) {
	        int[] dp = new int[value + 1];
	        dp[0] = 1;  // one way to make change for value 0: no coins

	        for (int d : denominations) {
	            for (int v = d; v <= value; v++) {
	                dp[v] += dp[v - d];
	            }
	        }

	        return dp[value];
	    }

	    public static void main(String[] args) {
	        int[] denominations = {1, 2, 3};
	        int value = 4;
	        System.out.println(countWaysToMakeChange(denominations, value));  // Output: 4
	    }
	}







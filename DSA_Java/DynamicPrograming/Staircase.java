package DynamicPrograming;

public class Staircase {
	
//	At each step, the child has 3 choices (1-step, 2-step, or 3-step). 
//	To find the total number of ways to reach a given step 'n', 
//	we can sum up the number of ways to reach 'n-1', 'n-2', and 'n-3' steps.
	
	public static long staircase(int n) {
        // Base cases
        if (n == 0) {
            return 1; // Only 1 way to climb 0 stairs
        }
        if (n < 0) {
            return 0; // No way to climb negative stairs
        }
        
        // We create an array to store the number of ways for each step
        long[] ways = new long[n + 1];
        
        // Base initialization
        ways[0] = 1;
        ways[1] = 1;  // Only 1 way to climb 1 stair
        if(n >= 2) {
            ways[2] = 2;  // 2 ways to climb 2 stairs: (1,1) and (2)
        }
        
        // Start filling the ways array based on the previous results
        for (int i = 3; i <= n; i++) {
            ways[i] = ways[i - 1] + ways[i - 2] + ways[i - 3];
        }
        
        return ways[n];
    }
    
	public static void main(String[] args) {
		
		System.out.println(staircase(4));  // Should print 7

	}

}

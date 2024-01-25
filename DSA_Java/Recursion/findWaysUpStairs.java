package Recursion;
//A child is running up a staircase with N steps, and can hop either 1 step, 2 steps or 3 steps at a time. 
//Implement a method to count how many possible ways the child can run up to the stairs. 
//You need to return number of possible ways W.

public class findWaysUpStairs {
    public static int findWays(int n) {
        int[] ways = new int[n + 1];
        ways[0] = 1;  // base case: 1 way to climb 0 stairs
        ways[1] = 1;  // base case: 1 way to climb 1 stair
        ways[2] = 2;  // base case: 2 ways to climb 2 stairs

        for (int i = 3; i <= n; i++) {
            ways[i] = ways[i - 1] + ways[i - 2] + ways[i - 3];
        }

        return ways[n];
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	       System.out.println(findWays(4));  // Output: 7
	       System.out.println(findWays(5));  // Output: 13
	       System.out.println(findWays(6));  // Output: 24
	       
//Here is the recursive relation:
//For the first step, the child can reach in only one way.
//For the second step, the child can reach in two ways (one step + one step or two steps).
//For the third step, the child can reach in four ways (one step + one step + one step, one step + two steps, two steps + one step, three steps).
//For the remaining steps, the child can reach in ways which is the sum of ways of reaching (n-1)th, (n-2)th and (n-3)th step.
	}

}

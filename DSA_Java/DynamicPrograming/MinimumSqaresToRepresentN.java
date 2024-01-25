package DynamicPrograming;

import java.util.Scanner;

public class MinimumSqaresToRepresentN {

	////////////////////////////////////////////////
	// depth first approach
	public static int minSquaresRecursive(int n) {
		if(n == 0) {
			return 0;
		}
		int minAns = Integer.MAX_VALUE;
		
		for(int i=1; i * i <= n; i++) {
			// looking for the largest integer when squared is <= n
			int currAns = minSquaresRecursive(n-(i * i));
			if(minAns > currAns) {
				minAns = currAns;
			}
		}
		int myAns = 1 + minAns;
		return myAns;
	}
	
	public static int minSquaresRecursiveDynamicProgramming(int n, int[] dp) {
		if(n == 0) {
			return 0;
		}
		int minAns = Integer.MAX_VALUE;
		
		for(int i=1; i * i <= n; i++) {
			int currAns;
			
			if(dp[n-(i*i)] == -1) {
				currAns = minSquaresRecursiveDynamicProgramming(n-(i*i), dp);
				dp[n-(i*i)] = currAns;
			}else {
				currAns = dp[n-(i*i)];
			}
			if(minAns > currAns) {
				minAns = currAns;
			}
		}
		int myAns = 1 + minAns;
		return myAns;
	}
	
	public static int minSquaresIterative(int n) {
		int[] dp = new int[n+1];
		if(n == 0) {
			return 0;
		}
		
		for(int i =1; i < n; i++ ) {
			
			int minAns = Integer.MAX_VALUE;
			for(int j=1; j*j <= i; j++) {
				
				int currAns = dp[i-(j*j)];
				if(minAns > currAns) {
					minAns = currAns;
				}
			}
			dp[i] = 1 + minAns;
		}
		return dp[n];
	}
	
	
	public static void main(String[] args) {
		
		int k = 41; 
		int[] dp = new int[k + 1];
	for(int i = 0; i < dp.length; i++) {
			dp[i] = -1;
	}
		int ans = minSquaresRecursiveDynamicProgramming(k, dp);
		System.out.println(ans);
		////////////////////////////////////////////////////////////////
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter a num");
		int n = s.nextInt();
		
		System.out.println(minSquaresRecursive(n));
		s.close();
	}

}
/**
 *Problem Understanding:
We're trying to represent a number n as the sum of the fewest perfect squares possible.
t's a depth-first exploration. Each recursive call (each node in our "tree") 
fully explores all its children (all its possible recursive paths) before returning and allowing the parent to move on.

Let's use a smaller example to break down the approach:

For n = 12:
Think of n=12 as an end goal, and imagine you have a decision to make:

Either you can subtract a perfect square from it and then solve the smaller problem, or
Try a different perfect square.
What are the perfect squares less than 12?

1^2 (1)
2^2 (4)
3^2 (9)
Let's try subtracting each one:

Option 1: Subtract 1^2 = 1
Now, you have a smaller problem: minSquaresRecursive(11)
Option 2: Subtract 2^2 = 4
Smaller problem: minSquaresRecursive(8)
Option 3: Subtract 3^2 = 9
Smaller problem: minSquaresRecursive(3)
Each of these smaller problems follows the same pattern. For minSquaresRecursive(11), you can subtract 1, 4, or 9, and then solve the yet smaller problem.

How the Recursive Function Works:
Base Case: If n is 0, it returns 0 because you need 0 squares to sum up to 0.

Loop: For every i from 1 to sqrt(n), it considers subtracting i*i from n and solves the smaller problem.

Recursion: Inside the loop, the function calls itself on the smaller problem (minSquaresRecursive(n-(i * i))).

Minimum: Among all the options tried in the loop, it picks the one that gives the minimum count.

Return: It adds 1 to the minimum count and returns. The addition of 1 accounts for the square we subtracted to get the smaller problem.

Let's Go Through n=3:
For n=3:

You can subtract 1. This leads to minSquaresRecursive(2).
You can't subtract 4 (since 4 > 3).
Now, for minSquaresRecursive(2):

You can subtract 1. This leads to minSquaresRecursive(1).
Finally, for minSquaresRecursive(1):

You can subtract 1. This leads to the base case minSquaresRecursive(0) which returns 0.
Combine all steps:

minSquaresRecursive(3) needs minSquaresRecursive(2) + 1 square.
minSquaresRecursive(2) needs minSquaresRecursive(1) + 1 square.
minSquaresRecursive(1) needs minSquaresRecursive(0) + 1 square.
Therefore, minSquaresRecursive(3) = 3, as you need three 1*1 squares to represent 3.

The function basically tries every possible option and picks the best one through recursion. This can indeed be a complex concept to grasp initially, but visualizing smaller examples can help in understanding the recursive flow. 
 * 
 * 
 * 
 * The initial call is minSquaresRecursive(5).
We hit the for loop, starting with i=1.
We compute 5 - 1*1 = 4 and make a recursive call to minSquaresRecursive(4). Execution for the minSquaresRecursive(5) call is paused at this point.
Now, inside minSquaresRecursive(4), we run the for loop again.
For i=1, we call minSquaresRecursive(3). Again, the minSquaresRecursive(4) call is paused.
Inside minSquaresRecursive(3), the loop runs, eventually making more recursive calls, and so on.
Once we reach the base case (i.e., n=0), that call simply returns 0 without making further recursive calls.
Execution resumes for the most recent paused function (for our example, this might be a deeper call such as minSquaresRecursive(2) or minSquaresRecursive(1)).
That function will finish its current loop iteration, possibly update its minAns value, then move to the next loop iteration, making more recursive calls if necessary.
Once all loop iterations finish for a particular recursive call, we finalize the value of myAns, return it, and execution picks up where it left off in the parent recursive call.
This process continues, moving back up the chain of paused functions, until we finally return from the top-most minSquaresRecursive(5) call.
 * 
 * 
*/
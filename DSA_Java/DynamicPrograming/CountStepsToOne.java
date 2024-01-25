package DynamicPrograming;

import java.util.Scanner;

public class CountStepsToOne {
	
	 public static int countMinStepsToOneDynamicProgramming(int n) {
	        if(n == 1) return 0;
	        
	        int[] steps = new int[n+1];
	        
	        // Base case
	        steps[1] = 0;
	        
	        for(int i = 2; i <= n; i++) {
	            int minSteps = steps[i-1]; // subtract 1 from i
	            
	            if(i % 2 == 0) {
	                minSteps = Math.min(minSteps, steps[i/2]);
	            }
	            if(i % 3 == 0) {
	                minSteps = Math.min(minSteps, steps[i/3]);
	            }
	            
	            steps[i] = 1 + minSteps; // Add 1 for the current step
	        }
	        
	        return steps[n];
	    }
	 
	 public static int recursiveMinStepsToOne(int n) {
		 
		 if(n == 1) {
			 return 0;
		 }
		 
		 int ans1 = recursiveMinStepsToOne(n-1);
		 
		 int ans2 = Integer.MAX_VALUE;
		 if((n % 2) == 0) {
			 ans2 = recursiveMinStepsToOne(n/2);
		 }
		 
		 int ans3 = Integer.MAX_VALUE;
		 if((n % 3) == 0) {
			 ans3 = recursiveMinStepsToOne(n/3);
		 }
		 
		 int myAns = Math.min(ans1, Math.min(ans2,  ans3)) + 1;
		 return myAns;
	 }

	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter a num");
		int n = s.nextInt();
		System.out.println(countMinStepsToOneDynamicProgramming(n));
		s.close();
	}

}

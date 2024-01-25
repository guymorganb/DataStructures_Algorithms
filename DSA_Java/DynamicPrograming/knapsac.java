package DynamicPrograming;

public class knapsac {
	
	public static int knapsacProblem(int W, int[] weights, int[] val, int i) {
		
		if(i == val.length) {
			return 0;
		}
		
		int ans;
		if(weights[i] <= W) {
			// including ith item
			int ans1 = val[i] + knapsacProblem(W - weights[i], weights, val, i+1);
			// excluding ith item
			int ans2 = knapsacProblem(W, weights, val, i+1);
			ans = Math.max(ans1, ans2);
		}else {
			ans = knapsacProblem(W, weights, val, i);
		}
		
		return ans;

	}
	
	public static int knapsacIterative(int W, int[] val,int[] weights) {
		int n = val.length;
		int[][] dp = new int[n+1][W+1];
		
		for(int i=n-1; i>=0; i--) {
			for(int w=0; w <= W; w++) {
				
				int ans;
				if(weights[i] <= w) {
					int ans1 = val[i] + dp[i+1][w-weights[i]];
					int ans2 = dp[i+1][w];
					ans = Math.max(ans1, ans2);
				}else {
					ans = dp[i+1][w];
				}
				dp[i][w] = ans;
			}
		}
		
		return dp[0][W];
		
	}
	
	public static void main(String[] args) {
		int[] val = {200,300,100};
		int[] weights = {20,25,30};
		
		int W = 50;
		
		int ans = knapsacIterative(W, val, weights);
		System.out.println(ans);
	}

}

package DynamicPrograming;

public class longestCommonSubsequence_LCS {
	
	public static int lcsRecursion(String str1, String str2, int start1, int start2) {
		int i = start1;
		int j = start2;
		
		if(i == str1.length() || j == str2.length()) {
			return 0;
		}
		
		int myAns;
		if(str1.charAt(i) == str2.charAt(j)) {
			int smallAns = lcsRecursion(str1, str2, i+1, j+1);
			myAns = 1 + smallAns;
		}else {
			int ans1 = lcsRecursion(str1, str2, i+1, j);
			int ans2 = lcsRecursion(str1, str2, i, j+1);
			myAns = Math.max(ans1, ans2);
		}
		return myAns;
	}
	
	
	public static int lcsMemorization(String str1, String str2, int start1, int start2, int[][] dp) {
		int i = start1;
		int j = start2;
		
		if(i == str1.length() || j == str2.length()) {
			return 0;
		}
		
		int myAns;
		if(str1.charAt(i) == str2.charAt(j)) {
			int smallAns;
			
			if(dp[i+1][j+1] == -1) {
				smallAns= lcsMemorization(str1, str2, i+1, j+1 ,dp);
				dp[i+1][j+1] = smallAns;
			}else {
				smallAns = dp[i+1][j+1];
			}
			myAns = 1 + smallAns;
			
		}else {
			int ans1, ans2;
			if(dp[i+1][j] == -1) {
				
				ans1 = lcsMemorization(str1, str2, i+1, j, dp);
				dp[i+1][j] =ans1;
			}else {
				ans1 = dp[i+1][j];
			}
			
			if(dp[i][j+1] == -1) {
				ans2 = lcsMemorization(str1, str2, i, j+1, dp);
				dp[i][j+1] = ans2;
			}else {	
				ans2 = dp[i][j+1];
			}
		
			myAns = Math.max(ans1, ans2);
		}
		return myAns;
	}
	
	public static int lcsIteratively(String str1, String str2, int start1, int start2) {
		
		int m = str1.length();
		int n = str2.length();
		
		int[][] dp = new int[m+1][n+1];// by default all the values are set to zero
		// Iteratively fill from bottom to the top
		for(int i =  m-1; i >= 0; i--) {
			for(int j = n-1; j >=0; j--) {
				
				// remember we are filling the dp array and comparing the string values
				// if the string values are the same we add 1
				int ans;
				if(str1.charAt(i) == str2.charAt(j)) {
					ans = 1 + dp[i+1][j+1];
				}else {
					int ans1 = dp[i][j+1];
					int ans2 = dp[i+1][j];
					// if the values are not 
					ans = Math.max(ans1, ans2);
				}
				dp[i][j] = ans;
			}
		}
		return dp[0][0];
	}
	
	public static void main(String[] args) {
		String str1 = "wncpoddas";
		String str2 = "wqegesggfff";

//		int[][] dp = new int[str1.length()+1][str2.length()+1];
//		
//		for(int i = 0; i < dp.length; i++) {
//			for(int j = 0; j < dp[0].length; j++) {
//				dp[i][j] = -1;
//			}
//		}
//		int ans = lcsRecursion(str1, str2, 0,0);
//		System.out.println(ans1);
		
		// using memorization
		//int ans0 = lcsMemorization(str1, str2, 0, 0, dp);
	
		// using iteration
		int ans1 = lcsIteratively(str1, str2, 0,0);
		System.out.println(ans1);
		
	}

}

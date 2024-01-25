package DynamicPrograming;

public class minCostPath {

	
	
	public static int findMinCostPath(int[][] matrix,int row, int col) {
		
		int m = matrix.length;
		int n = matrix[0].length;
		// we are at the bottom left corner
		if(row == m-1 && col == n-1) {
			return matrix[row][col];
		}
		// base case
		if(row >= m || col>=n) {
			// if out of bounds return infinity
			return Integer.MAX_VALUE;
		}
		
		// can move down, right or diagonal
		// this algorithm will explore all cells and add up the minimums
		// Recurrence relations
		int ans1 = findMinCostPath(matrix, row + 1, col);
		int ans2 = findMinCostPath(matrix, row, col + 1);
		int ans3 = findMinCostPath(matrix, row+1, col+1);
		// adds the minimum value of the cells 
		int myAns = matrix[row][col] + Math.min(ans1,  Math.min(ans2, ans3));
		return myAns;
		
	}
	
	public static int minCostUsingMemorization(int[][] matrix, int row, int col, int[][] dp) {
		int m = matrix.length;
		int n = matrix[0].length;
		// we are at the bottom left corner
		if(row == m-1 && col == n-1) {
			return matrix[row][col];
		}
		// base case
		if(row >= m || col>=n) {
			// if out of bounds return infinity
			return Integer.MAX_VALUE;
		}
		// declares these here, we want to check if the cell we are moving to has Integer.MIN_VALUE to determine which one to call
		// this will prevent overlapping subproblems
		int ans1, ans2, ans3;
		// if true we mean we haven't been to this cell so we need to call this cell
		if(dp[row+1][col] == Integer.MIN_VALUE) { 
			// call the cell below
			ans1 = minCostUsingMemorization(matrix, row + 1, col , dp); 
			// store the answer
			dp[row+1][col] = ans1;
		}else {	
			// we already have the answer
			ans1 = dp[row+1][col];
		}
		// if we have negative infinity we haven't got an answer for this cell
		if(dp[row][col + 1] == Integer.MIN_VALUE) {
			ans2 = minCostUsingMemorization(matrix, row, col+1, dp);
			dp[row][col +1] = ans2;
		}else {
			ans2 = dp[row][col+1];
		}
		
		if(dp[row+1][col+1] == Integer.MIN_VALUE) {
			ans3 = minCostUsingMemorization(matrix, row+1, col+1, dp);
			dp[row+1][col+1] = ans3;
		}else {
			ans3 = dp[row+1][col+1];
		}
		// adds the minimum value of the cells 
		int myAns = matrix[row][col] + Math.min(ans1,  Math.min(ans2, ans3));
		return myAns;
	}
	public static int minCostIterativelyTopDown(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		
		int[][]dp = new int[row+ 1][col +1];
		
		for(int i=0; i< dp.length; i++) {
			for(int j = 0; j < dp[0].length; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for(int i = 1; i < row+1; i++) {
			for(int j= 1;j < col+1; j++) {

				if(i == 1 && j == 1) {
					// special case: to set the top right corner, just need to set it to the value
					// the value 18 is coming from the path matrix we will be passing this function
					dp[i][j] = matrix[0][0];
					continue;
				}
				// Recurrence relation
				int ans1 = dp[i - 1][j];
				int ans2 = dp[i][j - 1];
				int ans3 = dp[i-1][j-1];
				// we are setting the value of the dynamic programming array to the minimum value of these 3 values
				// we are going to each cell in the matrix and summing the minimum of the values and placing it in the dp array
				dp[i][j] = matrix[i - 1][j - 1] + Math.min(ans1, Math.min(ans2, ans3));
			}
		}
		return dp[row][col];
	}
	
	public static int minCostIterativelyBottomUp(int[][] matrix) {
		
		int row = matrix.length;
		int col = matrix[0].length;
		
		int[][] dp = new int[row + 1][col + 1];
		// initialize all values wit infinity in the dynamic programming array
		for(int i =0; i < dp.length; i++) {
			for(int j = 0; j< dp[0].length; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		// we are iteratively fill the 2D array from the bottom right to the top left
		for(int i = row -1; i > 0; i--) {
			for(int j= col-1;j >= 0; j--) {

				if(i == row-1 && j == col-1) {
					// special case to set the bottom right corner, because it wont be the sum of infinity plus 18, thats wrong, we just need to set it to 18
					// the value 18 is coming from the path matrix we will be passing this function
					dp[i][j] = matrix[i][j];
					continue;
				}
				// Recurrence relation
				int ans1 = dp[i][j+1];
				int ans2 = dp[i +1][j];
				int ans3 = dp[i+1][j+1];
				// we are setting the value of the dynamic programming array to the minimum value of these 3 values
				// we are going to each cell in the matrix and summing the minimum of the values and placing it in the dp array
				dp[i][j] = matrix[i][j] + Math.min(ans1, Math.min(ans2, ans3));
			}
		}
		return dp[0][0];
	}
	
	public static void main(String[] args) {
		int[][] path = {
				{1,5,11},
				{8,13,12},
				{2,3,7},
				{15,16,18}
		};
		// for min cost using memorization
		int [][] dp = new int[path.length+1][path[0].length+1]; // we are maintaining one extra column so we don't get an array index out of bound
		// initialize the dynamic programming array with -infinity values
		// so we know what's valid and what's not
		for(int i =0; i < dp.length; i++) {
			for(int j = 0; j< dp[0].length; j++) {
				dp[i][j] = Integer.MIN_VALUE;
			}
		}
		int ans0 = minCostUsingMemorization(path,0,0,dp);
		System.out.println(ans0);
		// int ans = findMinCostPath(path, 0, 0);
		// System.out.println(ans);

	}

}

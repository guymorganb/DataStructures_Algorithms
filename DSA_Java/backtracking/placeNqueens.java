package backtracking;

public class placeNqueens {
	 
	
	public static void placeNQueens() {
	        int[][] board =  {
					{1, 1, 1},
					{1, 1, 0},
					{0, 1, 1}
			};
	        
	        int n = board.length;
	        solveNQueens(board, 0, n);
	    }
	    
	    private static void solveNQueens(int[][] board, int row, int n) {
	        if (row == n) {
	            // Print the board
	            for (int i = 0; i < n; i++) {
	                for (int j = 0; j < n; j++) {
	                    System.out.print(board[i][j] + " ");
	                }
	            }
	            System.out.println();
	            return;
	        }
	        
	        for (int col = 0; col < n; col++) {
	            if (isSafe(board, row, col, n)) {
	                board[row][col] = 1;
	                solveNQueens(board, row + 1, n);
	                board[row][col] = 0;  // backtrack
	            }
	        }
	    }
	    
	    private static boolean isSafe(int[][] board, int row, int col, int n) {
	        // Check column
	        for (int i = 0; i < row; i++) {
	            if (board[i][col] == 1) {
	                return false;
	            }
	        }
	        
	        // Check upper left diagonal
	        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
	            if (board[i][j] == 1) {
	                return false;
	            }
	        }
	        
	        // Check upper right diagonal
	        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
	            if (board[i][j] == 1) {
	                return false;
	            }
	        }
	        
	        return true;
	    }
	    
	  
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package backtracking;

public class printAllPathsOnOneLine {
	/*
	Note:
	To get all the test cases accepted, make recursive calls in following order: Top, Down, Left, Right.
	This means that if the current cell is (x, y), then order of calls should be: top cell (x-1, y), 
	down cell (x+1, y), left cell (x, y-1) and right cell (x, y+1).
*/  

	static int[] dx = {1, 0, -1, 0}; // Right, Down, Left, Up
	static int[] dy = {0, 1, 0, -1}; // Corresponding y direction for each x direction
	
	static boolean isValid(int[][] maze, int x, int y, int N, int[][] path) {
		
	    return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1 && path[x][y] == 0);
	}
	
	static void findPath(int[][] maze, int x, int y, int[][] path, int N) {
		
	    if (x == N - 1 && y == N - 1) {
	        path[x][y] = 1;
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                System.out.print(path[i][j] + " ");
	            }
	        }
	        System.out.println();
	        path[x][y] = 0;  // Backtrack for other possible paths
	        return;
	    }
	
	    if (isValid(maze, x, y, N, path)) {
	    	
	        path[x][y] = 1;
	        for (int d = 0; d < 4; d++) {
	            int newX = x + dx[d];
	            int newY = y + dy[d];
	            findPath(maze, newX, newY, path, N);
	        }
	        path[x][y] = 0;  // Backtrack for other possible paths
	    }
	}
	
	static int[][] maze = {
			{1, 1, 1},
			{1, 1, 0},
			{0, 1, 1}
	};
	
	public static void main(String[] args) {
		int n = maze.length;
	   
	    int sol[][] = new int[n][n];
	    for (int i = 0; i < n; i++) {
	       for (int j = 0; j < n; j++) {
	           sol[i][j] = 0;
	       }
	        

	    findPath(maze, 0, 0, sol, n);
	    }
	
	}
}

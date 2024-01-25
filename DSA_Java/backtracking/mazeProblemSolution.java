package backtracking;

public class mazeProblemSolution {

	
		public static boolean ratInMaze(int[][] maze) {
			int n = maze.length;
			int path[][] = new int[n][n];
			return solveMaze(maze, 0, 0, path);
		}
		
		// row and col variable represent the current position, and path keeps track of our travels
		public static boolean solveMaze(int[][] maze, int row, int col, int[][] path) {
			// check if row, col is valid cell 
			int n = maze.length;
			
			// Check if out of bounds (row < 0 || row >= n || col < 0 || col >= n)
			// Check if its a blocked cell (maze[row][col] == 0) 
			// Check if part of our path, has already been visited (path[row][col] == 1) <-- this will only be checked after all preceding conditions have evaluated to false
			if(row < 0 || row >= n || col < 0 || col >= n || maze[row][col] == 0 || path[row][col] == 1) {
				return false;
			}
			// Include the cell in the path
			path[row][col] = 1;
			//check if we've reached the destination cell (the bottom right corner)
			if(row == n-1 && col == n -1) {
				for(int i = 0; i < maze.length; i++) {
					// inner loop
					for(int j = 0; j < maze[i].length; j++) {
							System.out.print(path[i][j] + " ");
						}
						System.out.println();
				}
				return true;
			}
			
			// explore top
			if(solveMaze(maze, row - 1, col, path)) {
				return true;
			}
			// explore right
			if (solveMaze(maze, row, col + 1, path)) {
				return true;
			}
			// explore below
			if(solveMaze(maze, row + 1, col, path)) {
				return true;
			}
			// explore left
			if(solveMaze(maze, row, col - 1, path)) {
				return true;
			}
			// path doesn't exist
			return false;
		}
	

	public static void main(String[] args) {
		int[][] maze = {
				{1, 1, 1},
				{1, 1, 0},
				{1, 1, 1}
		};
		
		boolean pathPossible = ratInMaze(maze);
		System.out.println(pathPossible);
	}

}

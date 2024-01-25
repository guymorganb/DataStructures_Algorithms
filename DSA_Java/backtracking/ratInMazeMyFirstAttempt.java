package backtracking;


public class ratInMazeMyFirstAttempt {
	
//	given 3x3 matrix find a path through the maze
	// each cell in the maze can have  1 or 0, 0 represents blocked cell.
	// Traverse the maze up down left or Right
	// ex.
// 		start --> 1 1 0
		// 		  1 1 0
		//  	  1 1 1 <--destination
	public static int[][] myMaze = {
			{1, 1, 1},
			{1, 0, 0},
			{1, 1, 1}
	};
	
	public static int[][] path = {
			{0, 0, 0},
			{0, 0, 0},
			{0, 0, 0}
	};
	// matrix.length = the height
	// matrix[i].length = the length of the i'th row
	// make a isOutOfBounds function so your maze solver knows if its out of bounds in the matrix
	public static boolean isOutOfbounds(int[][] maze, int row, int col) {
		// check if its out of bounds top or bottom
		if(row < 0 || row >= maze.length) {
			return true;
		}
		// check if its out of bounds right or left
		if(col < 0 || col >= maze[row].length) {
			return true;
		}
		// if not out of bounds, return false
		return false;
	}
	

	// check if move is valid
	public static boolean isValidMove(int[][] matrix, int row, int col, String fromDirection) {
		// check if the path matrix has been visited at this position
	
			// check if the move is valid by what the space contains either 0 or 1
			if(matrix[row][col] == 1 && path[row][col] == 0) {
				// if maze cell == 1 and path cell = 0 its a valid move
				return true;
			}
			// if path[row][col] == 1 we are backtracking
			if(path[row][col] == 1) {
				// if we have to backtrack its not a valid move
				
			}
		
		return false;
	}
	
	// update the path
	public static void updatePath(int updateValue, int row, int col) {
		// update the path to 1 if maze is 1
		if(updateValue == 1) {
			// update the value in path matrix
			path[row][col] = updateValue;
			return;
		}
	}
	
	// handle the move logic
	public static void move(int[][] maze, int startRow, int startCol, int endRow, int endCol) {
		// currPosition will be a 1 representing the value at that cell
		int currPosition = maze[startRow][startCol];
		// check if currPosition is at the end
		if(!((startRow == endRow && startCol == endCol) && (currPosition == 1))) {
			// check if up is in bounds
			if(!isOutOfbounds(maze, startRow-1, startCol)) {
				// move up
				if(isValidMove(maze, startRow - 1, startCol, "fromAbove")) {
					// update the path (all blocks start as zero so no update required unless path is valid)
					updatePath(currPosition, startRow - 1, startCol);
					// update position
					currPosition = maze[startRow - 1][startCol];
					// recursive call to move up again
					move(maze, startRow-1, startCol, endRow, endCol);
				}
				// execution continues to the next block (Move Right) if move is not valid
			}
			// check if right is in bounds
			if(!isOutOfbounds(maze, startRow, startCol + 1)) {
				// move right 
				if(isValidMove(maze, startRow, startCol + 1)) {
					// update the path
					updatePath(currPosition, startRow, startCol + 1);
					// update position
					currPosition = maze[startRow][startCol + 1];
					// recursive call to move right again
					move(maze, startRow, startCol + 1, endRow, endCol);
				}
			}
			// check if down is in bounds
			if(!isOutOfbounds(maze, startRow + 1, startCol)) {
				// move down 
				if(isValidMove(maze, startRow + 1, startCol)) {
					// update the path
					updatePath(currPosition, startRow + 1, startCol);
					// update position
					currPosition = maze[startRow + 1][startCol];
					// recursive call to move down again
					move(maze, startRow + 1, startCol, endRow, endCol);
				}
			}
			// check if left is in bounds
			if(!isOutOfbounds(maze, startRow, startCol - 1)) {
				// move left 
				if(isValidMove(maze, startRow, startCol - 1)) {
					// update the path
					updatePath(currPosition, startRow, startCol - 1);
					// update position
					currPosition = maze[startRow][startCol - 1];
					// recursive call to move left again
					move(maze, startRow, startCol - 1, endRow, endCol);
				}
			}
		}
		else {
			System.out.println("Your current position is: " + "(" + startRow + "," + startCol + ")" + " your destination is " + "(" + endRow + "," + endCol + ")");
			System.out.println("your final path is\n");
			printPath(path);
		}
	}
	
	public static void printPath(int[][] finalPath) {
		// loop over the height finalPath.length, nest an inner for loop to loop over the width
		for(int i = 0; i < finalPath.length; i++) {
			// inner loop
			for(int j = 0; j < finalPath[i].length; j++) {
				if(finalPath[i][j] == 1) {
					// print the element at this index
					System.out.print(finalPath[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

	
	public static void main(String[] args) {
		// move(int[][] maze, int startRow, int startCol, int endRow, int endCol)
		move(myMaze, 0, 0, 2, 2);
		
	
	}

}

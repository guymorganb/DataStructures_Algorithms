package Arrays2D;

import java.util.Scanner;

public class printMatrixSpiral {
	public static int[][] takeInput(){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the number of rows");
		
		int rows = s.nextInt();
		System.out.println("Enter number of columns");
		int cols = s.nextInt();
		
		int[][] arr = new int[rows][cols];
		
		for(int i=0; i < rows; i ++) {
			for(int j=0; j<cols; j++) {
				System.out.println("Enter the element at " + i + " row" + j + "column");
				arr[i][j]=s.nextInt();
			}
		}return arr;
	}
	public static void printMatrixSpiral(int[][] arr) {
		// we are printing clockwise in a spiral
		//  this is for a square array
		int nRows = arr.length;
		int mCols = arr[0].length;
		int total = nRows*mCols;
		// set 4 bounds upper left row, upper left column, upper right column, lower left row// values are printed moving clockwise around
		int upperRow = 0;
		int lowerRow = nRows-1;
		int LeftColumn = 0;
		int RightColumn = mCols-1;
		
		int count = 0;
		while(count < total) {
			// left -> right
			for(int i = LeftColumn; count<total && i <= RightColumn;i++) {
				System.out.print(arr[upperRow][i] + " ");
				count++;
			}
			upperRow++;
			
			// top -> bottom
			for(int i = upperRow; count < total && i <= lowerRow; i++) {
				System.out.print(arr[i][RightColumn] + " ");
				count++;
			}
			RightColumn--;
			
			// right -> left
			for(int i = RightColumn; count < total && i >= LeftColumn; i--) {
				System.out.print(arr[lowerRow][i] + " ");
				count++;
			}
			lowerRow--;
			
			// bottom -> top
			for(int i = lowerRow; count<total && i >= upperRow; i--) {
				System.out.print(arr[i][LeftColumn] + " ");
				count++;
			}
			LeftColumn++;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] input = takeInput();
		printMatrixSpiral(input);
	}

}

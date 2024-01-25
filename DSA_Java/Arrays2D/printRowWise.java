package Arrays2D;

import java.util.Scanner;
import java.util.Arrays;
public class printRowWise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the number of rows");
		
		int rowss = s.nextInt();
		System.out.println("Enter number of columns");
		int cols = s.nextInt();
		
		int[][] arr = new int[rowss][cols];
		
		for(int i=0; i < rowss; i ++) {
			for(int j=0; j<cols; j++) {
				System.out.println("Enter the element at row " + i + " column " + j);
				arr[i][j]=s.nextInt();
			}
		}
//		traverse row-wise
		int index = 0;
		int row = arr.length;		 // reference to the 2 dimensional, array number of rows
		int columns = arr[0].length; // reference to 1 dimensional array inside, number of columns
		int[] result = new int[row * columns];

		for (int i = 0; i < row; i++) {
		    for (int j = 0; j < columns; j++) {
		        result[index] = arr[i][j];
		        System.out.print(result[index] + " ");
		        index++;
		    }
		}System.out.print(Arrays.toString(result));
		
		
//		traverse column-wise
		int ind = 0;
		int col = arr.length;		// reference to the 2 dimensional array, number of rows
		int rows = arr[0].length; 	// reference to 1 dimensional array inside, number of columns
		int[] res = new int[rows * col];

		for (int i = 0; i < rows; i++) {
		    for (int j = 0; j < col; j++) {
		        res[ind] = arr[j][i];
		        ind++;
		    }
		}

		System.out.print(Arrays.toString(res));
	}

}

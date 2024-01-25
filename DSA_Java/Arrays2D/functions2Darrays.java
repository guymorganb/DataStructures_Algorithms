package Arrays2D;

import java.util.Scanner;

public class functions2Darrays {
	
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
	public static void print2darray(int[][] array) {
		int rows = array.length;
		int cols = array[0].length;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				System.out.print(array[i][j] +  " ");
			}
			System.out.println();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] input = takeInput();
		print2darray(input);
	}

}

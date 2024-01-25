package Arrays2D;

import java.util.Scanner;

public class largestColumnSum {
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
	public static int largestColSum(int[][] arr) {
		int rows = arr.length;
		int cols = arr[0].length;
		int largest = Integer.MIN_VALUE;
		for(int i = 0; i < cols; i++) {
			int sum = 0;
			for(int j = 0; j < rows; j++) {
				sum = sum + arr[j][i];
			}
			if(sum > largest) {
				largest = sum;
			}
		}return largest;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] input = takeInput();
		int largestCol = largestColSum(input);
		System.out.println(largestCol);
	}

}

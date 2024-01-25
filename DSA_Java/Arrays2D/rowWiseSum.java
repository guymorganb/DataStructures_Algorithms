package Arrays2D;

public class rowWiseSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] mat = {{1, 2, 3, 4}, {5, 6, 7, 8}}; 
		int rows = mat.length;
		int cols = mat[0].length;
		
		for(int i = 0; i< rows; i++){
			int sum = 0;
			for(int j = 0; j< cols; j++){
				sum += mat[i][j];
			}
			System.out.print(sum + " ");
			
		}
	}

}

package Arrays2D;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] arr2d = new int [3][4];
		
		//System.out.println(arr2d[1][2]);
		
		arr2d[2][1] = 12;
		
		System.out.println(arr2d[2][1]);
		
		int[][] ar2d = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		System.out.println(ar2d.length);  // number of rows
		System.out.println(ar2d[0].length);  // number of columns
	}

}

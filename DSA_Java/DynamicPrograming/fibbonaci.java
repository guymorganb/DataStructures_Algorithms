package DynamicPrograming;
import java.util.Scanner;
public class fibbonaci {
	// dynamic programming is applied for overlapping subproblems
	
	public static int itterativeFibbonaci(int n) {
		if(n == 1 || n == 0) {
			return n;
		}
		int[] arr = new int[n + 1];
		arr[0] = 0;
		arr[1] = 1;
		for(int i = 2; i < n; i++) {	// memorization
			arr[i] = arr[i-1] + arr[i-2];
		}
		return arr[n];
	}
	
	
	public static int fibbonaci (int n, int[] array) {
		if(n == 1 || n == 0) {
			return n;
		}
		int ans1, ans2;
		// we are checking the array to see if the value has already been called
		if(array[n-1] == -1) {	// memorization
			ans1 = fibbonaci(n - 1, array);
			// set the value of the array at this location
			array[n-1] = ans1;
		}
		else {
			ans1 = array[n - 1];
		}
		// we are checking the array to see if the value has already been called
		if(array[n-2] == -1) {	// memorization
			ans2 = fibbonaci(n - 2, array);
			// set the value of the array at this location
			array[n-2] = n-2;
		}
		else {
			ans2 = array[n-2];
		}
		
		int myAns = ans1 + ans2;
		
		return myAns;
		// fibbonaci sequence with time complexity O(2^n)
		// after adding the array to keep track time complexity becomes O(n);
	}
	
	public static void main(String[] args) {
		// maintain an array of size n+1 which holds values of fibonacci we've already called so we are not calculating values we have already called
		
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		
		int[] arr = new int[n + 1];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = -1;
		}
		int ans = fibbonaci(n, arr);
		System.out.println(ans);
		s.close();
	}

}

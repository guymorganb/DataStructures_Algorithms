package Recursion;

public class CheckforNumInArray {
	// check if int x is in array, return true if it is and false if its not
	public static boolean checkNumber(int[] arr, int x) {
		// base case
		if(arr.length == 1) {
			System.out.println("top level "+arr[0]);
			if(arr[0] == x) {
				return true;
			}else {
				return false;
			}
		}
		int[] smallerArray = new int[arr.length-1]; // make a new array so you can do this recursively
		for(int i = 1; i < arr.length; i++) {
			smallerArray[i-1] = arr[i];
		}
		boolean answer = checkNumber(smallerArray, x);
		
		if(arr[0] == x) {
			return true;
		}
		return answer;
	}
	public static void main(String[] args) {
		int[] array = {1,2,3,4,5,6,56,78,90,25,32,54};
		int x = 1;
		System.out.println("outsite "+checkNumber(array, x));
	}

}

package Recursion;

public class sortedArray {

	public static boolean isSorted(int[] arr) {
		if(arr.length == 1) {
			return true;
		}
		if(arr[0] > arr[1]) {
			return false;
		}
		int[] smallerArray = new int[arr.length-1];
		for(int i = 1; i < arr.length; i++) {		// make a new array and copy over arr
			smallerArray[i-1] = arr[i];
		}
		boolean isSmallArraySorted = isSorted(smallerArray);
		return isSmallArraySorted;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {5,1,2,7,9,3,4};
		int[] array1 = {1,2,3,4,5,6};
		System.out.println(isSorted(array));
		System.out.println(isSorted(array1));
	}

}

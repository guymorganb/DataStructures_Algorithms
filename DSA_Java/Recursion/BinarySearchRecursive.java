 package Recursion;

public class BinarySearchRecursive {
	// return index of target
	
	
	// IMPORTANT: the array must be sorted for binary search to work
	
	public static int binarySearch(int[] arr, int startIndex, int endIndex, int target) {
		if(startIndex > endIndex) {
			// element not in array
			return -1;
		}
		//get middle index
		int middleIndex = (startIndex + endIndex)/2;
		if(arr[middleIndex] == target) {
			return middleIndex;
		}
		else if(arr[middleIndex] < target) {
			return binarySearch(arr, middleIndex + 1, endIndex, target);
		}
		else {
			return binarySearch(arr, startIndex, middleIndex-1, target);
		}
	}
	
	public static void main(String[] args) {
		// IMPORTANT: the array must be sorted for binary search to work
		int[] array = {1,3,6,8,11,15,34,56,89,90,93,100,145,158,163,190};
		System.out.println(binarySearch(array, 0, array.length-1, 3));
	}

}

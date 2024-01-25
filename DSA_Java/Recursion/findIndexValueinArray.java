package Recursion;

public class findIndexValueinArray {
	// find first occurrence of index value and return it, otherwise return -1, you can make copies
	public static int findIndex(int[] arr, int x) {
		
		if(arr.length == 0) {
				return -1;
			}
		if(arr[0] == x) {		// gets the element if it exists at index 0, 
			return 0;			// then sends 0 back because 0 is the index of the element at arr[0]
		}
		int [] smallerArray = new int[arr.length-1];
		for(int i = 1; i < arr.length; i++) {
			smallerArray[i-1] = arr[i];
		}
		int firstIndex = findIndex(smallerArray, x);	// first index will become = 0
		
		System.out.println(firstIndex);
		
		if(firstIndex == -1) {
			return -1;
		}
			return firstIndex + 1; // this causes the firstIndex to increment, as each unresolved recursive call unwinds
	}
	
	// find first occurrence of index value in an array, given a start index, without making copies of the array
	public static int getIndex(int[] arr,int target, int startIndex) {
		if(arr[startIndex] == target) {
			return startIndex;
		}
		if(startIndex == arr.length-1) {
			return -1;
		}
		int targetIndex = getIndex(arr, target, startIndex + 1);
		return targetIndex;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {5,2,6,3,51,66,23};
		int x = 23;
		//System.out.println("index is: "+findIndex(array, x));
		System.out.println("outside"+getIndex(array, x, 0));
	}

}

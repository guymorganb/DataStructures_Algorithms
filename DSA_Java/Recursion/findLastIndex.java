package Recursion;
//Given an array of length N and an integer x, you need to find and return the last index of integer x present in the array. Return -1 if it is not present in the array.
//Last index means - if x is present multiple times in the array, return the index at which x comes last in the array.
//You should start traversing your array from 0, not from (N - 1).
//Do this recursively. Indexing in the array starts from 0.
public class findLastIndex {
	
	public static int lastIndex(int[] arr, int target) {
		if(arr.length == 0) {
			return -1;
		}
		int[] smallerArray = new int[arr.length-1];
		
		for(int i = 1; i < arr.length; i++) {		// make a new array and copy over arr
			smallerArray[i-1] = arr[i];
		}
		
		int index = lastIndex(smallerArray, target);
		///////////////////////////////////////////////
		System.out.println("index " + index);
		///////////////////////////////////////////////
		if (index >= 0) {
			index +=1;
		}
		if(index == -1) {
			if(arr[0] == target) {
				index += 1;			
			}else {
				index = -1;
			}
		}
		///////////////////////////////////////////////
		System.out.println("arr[0] " + arr[0]);
		
		//System.out.println("smallerArray[0] " + smallerArray[0] ); // don't run this, it will break the code
		
		for (int element : arr) {
	          System.out.println("arr: " + element);
	      }
		for (int element : smallerArray) {
	          System.out.println("smallerArr: " + element);
	      }
		///////////////////////////////////////////////
		return index;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {5,4,6,2,7,4,3,4,6};
		System.out.println("outside" + lastIndex(array, 6));
	}

}

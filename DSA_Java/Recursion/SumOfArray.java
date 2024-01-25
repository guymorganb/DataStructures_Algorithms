package Recursion;

public class SumOfArray {
	public static int sumUpArray(int[] arr) {
		if(arr.length == 1) {
			return arr[0]; // at this point arr[0] = 4, but as soon as its kicked back out of the if statement arr[0]=3 
		}
		int[] smallerArray = new int[arr.length-1];
		
		for(int i = 1; i < arr.length; i++) {		// make a new array and copy over arr
			smallerArray[i-1] = arr[i];
		}
		int total = sumUpArray(smallerArray)+ arr[0]; // arr[0]=3 now after it got kicked back from the if statement
		
//		for (int element : arr) {
//	          System.out.println("arr: " + element);	// print out the unwinding array
//	    }
		
		return total;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {5,1,2,7,9,3,4};
		System.out.println(sumUpArray(array));
	}

}

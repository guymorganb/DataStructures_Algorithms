package Time_SpaceComplexity;
//You have been given an integer array/list(ARR) of size N. Where N is equal to [2M + 1].
//Now, in the given array/list, 'M' numbers are present twice and one number is present only once.
//You need to find and return that number which is unique in the array/list.
public class findUniqueElement {
//	The XOR operator has some unique properties:
//
//It returns zero when we take XOR of two same numbers.
//It returns the same number when we take XOR of a number with zero.
//By taking the XOR of all the elements in the array, 
//we will be left with the unique number as all pairs will cancel each other out 
//and the XOR will return the unique number.

	public static int uniqueElement(int[] arr) {
		int uniqueElement = 0;
		for(int i = 0; i < arr.length; i++) {
			uniqueElement ^= arr[i];  
// A number XORed with itself will always result in 0. 
// This is because each bit in the number is identical to the corresponding bit in the other number, 
// and XORing identical bits results in 0.
			
//	A number XORed with 0 will always result in the original number. 
//	This is because XORing any bit with 0 results in the original bit.
		}
		return uniqueElement;
	
	}
//This code will iterate through all elements in the array and apply XOR operation. 
//All numbers that are present twice will cancel out each other and the result will be the unique number 
//that is present only once. The time complexity is O(n) and space complexity is O(1).	
//	XOR (^=)
//	A B A XOR B
//	-----------
//	0 0    0
//	0 1    1
//	1 0    1
//	1 1    0 (0 is false and 1 is true)
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1, 3, 1, 3, 6, 6, 7, 10, 7};
		int element = uniqueElement(arr);
		System.out.println(element);
	}

}

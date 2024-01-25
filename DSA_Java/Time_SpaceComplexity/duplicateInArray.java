package Time_SpaceComplexity;
//The first line contains an Integer 't' which denotes the number of test cases or queries to be run. 
//Then the test cases follow.
//First line of each test case or query contains an integer 'N' representing the size of the array/list.
//Second line contains 'N' single space separated integers representing the elements in the array/list
public class duplicateInArray {
//	Hashing: Keep a boolean array, initially all set to false. Iterate over the array/list, 
//	and if a value at index arr[i] is marked true in boolean array, 
//	then this is the repeating element. If it is not marked, then mark it.
	public static int findDuplicate(int[] arr) {
	    boolean[] boolArr = new boolean[arr.length - 1];
	    for(int i = 0; i < arr.length; i++) {
	        if(boolArr[arr[i]]) {
	            return arr[i];
	        }
	        boolArr[arr[i]] = true;
	    }
	    return -1; // In case no duplicate is found
	}
	
//	alternative: 
//	Sum formula: Sum all the elements in the array and subtract the sum of numbers from 0 to N-2 from this sum. 
//	The result will be the duplicate number. 
//	Note that this method could potentially lead to integer overflow if N is large.
	
	public static int findDuplicate1(int[] arr) {
	    int sum = 0;
	    for(int i = 0; i < arr.length; i++) {
	        sum += arr[i];
	    }
	    int n = arr.length - 2;
	    int sumToN = n * (n + 1) / 2;
	    return sum - sumToN;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {0, 3, 1, 5, 4, 3, 2};
		int solution = findDuplicate(arr);
		System.out.println(solution);
	}

}

package Time_SpaceComplexity;
import java.util.HashMap;
//You have been given an integer array/list(ARR) and a number 'num'. 
//Find and return the total number of pairs in the array/list which sum to 'num'.
public class sumToNum {
//	You can solve this problem by creating a map or an array to store the frequencies of each element in the array. 
//	Then, iterate through the array again and for each element check if there exists 
//	a pair that makes up the sum to 'num'. 
//	Be careful to avoid counting any pair twice.
	
	    public static int numPairsSumToNum(int[] arr, int num) {
	        HashMap<Integer, Integer> frequencies = new HashMap<>();
	        int count = 0;
	        
	        for (int i = 0; i < arr.length; i++) {
	            int complement = num - arr[i];
	            if (frequencies.containsKey(complement)) {
	                count += frequencies.get(complement);
	            }
	            
	            if (!frequencies.containsKey(arr[i])) {
	                frequencies.put(arr[i], 0);
	            }
	            frequencies.put(arr[i], frequencies.get(arr[i]) + 1);
	        }
	        
	        return count;
	    }

//	    In this code, we first create a map to store the frequencies of each number in the array. 
//	    Then, for each number, we calculate its complement with respect to the target sum 'num'. 
//	    If this complement is in our map, we add its frequency to our count. This accounts for all 
//	    the pairs that include the current number and sum to 'num'. Finally, 
//	    we update the frequency of the current number in the map.
//
//	    The time complexity of this solution is O(n), where n is the size of the input array, 
//	    because we do a constant amount of work for each element
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1, 3, 6, 2, 5, 4, 3, 2, 4};
		int num = 7;
		int solution = numPairsSumToNum(arr, num);
		System.out.println(solution);
	}

}

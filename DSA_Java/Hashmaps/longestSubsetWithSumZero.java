package Hashmaps;
import java.util.HashMap;
public class longestSubsetWithSumZero {

	public static int lengthOfLongestSubsetWithZeroSum(int arr[]) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		int maxLength = 0;

		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			
			// Special case: when subarray starts from index 0
			if (sum == 0) {
				maxLength = Math.max(maxLength, i + 1);
			}
			
			// If prefix sum is found again, update maxLength
			if (map.containsKey(sum)) {
				maxLength = Math.max(maxLength, i - map.get(sum));
			} else {
				map.put(sum, i);
			}
		}
		
		return maxLength;
	}

	public static void main(String[] args) {
		int[] arr = { 6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7 };
		System.out.println(lengthOfLongestSubsetWithZeroSum(arr));  // Output will be 10
	}
}


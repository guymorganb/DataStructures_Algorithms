package Hashmaps;
import java.util.HashMap;
public class printPairsWirhDifferenceK {

	public static int getPairsWithDifferenceK(int arr[], int k) {
		HashMap<Integer, Integer> numCounts = new HashMap<>();
		int pairCount = 0;

		// Count the frequency of each element in the array
		for (int num : arr) {
			numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
		}

		// Count pairs with difference k
		for (int num : arr) {
			// Check for pair (num + k)
			if (numCounts.containsKey(num + k)) {
				pairCount += numCounts.get(num) * numCounts.get(num + k);
			}
			
			// Check for pair (num - k), but only if k is not 0
			// (to avoid double-counting pairs when k = 0)
			if (k != 0 && numCounts.containsKey(num - k)) {
				pairCount += numCounts.get(num) * numCounts.get(num - k);
			}

			// Remove the current element to avoid double-counting
			numCounts.put(num, 0);
		}

		return pairCount;
	}

	public static void main(String[] args) {
		int[] arr = {1, 5, 3, 4, 2};
		int k = 2;
		System.out.println(getPairsWithDifferenceK(arr, k));  // Output will be 2
	}
}



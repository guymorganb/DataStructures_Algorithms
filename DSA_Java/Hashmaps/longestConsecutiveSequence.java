package Hashmaps;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
public class longestConsecutiveSequence {

	public static ArrayList<Integer> longestConsecutiveIncreasingSequence(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int longestStreakStart = 0;
        int longestStreakLength = 0;
        
        for (int num : arr) {
            if (!map.containsKey(num)) {
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                
                // current streak length considering the current number
                int streakLength = left + right + 1;

                // update the longest streak variables if needed
                if (streakLength > longestStreakLength) {
                    longestStreakStart = num - left;
                    longestStreakLength = streakLength;
                } else if (streakLength == longestStreakLength) {
                    // check if the sequence starting with this number occurs earlier
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == num - left || arr[i] == longestStreakStart) {
                            if (arr[i] == num - left) {
                                longestStreakStart = num - left;
                            }
                            break;
                        }
                    }
                }

                // Update map values
                map.put(num, streakLength);
                map.put(num - left, streakLength);
                map.put(num + right, streakLength);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(longestStreakStart);
        if (longestStreakLength > 1) {
            result.add(longestStreakStart + longestStreakLength - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {3, 7, 2, 1, 9, 8, 1};
        ArrayList<Integer> result = longestConsecutiveIncreasingSequence(arr);
        System.out.println(result);  // Output will be [7, 9]
    }
}



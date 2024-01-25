package Hashmaps;
import java.util.HashMap;

import java.util.ArrayList;


public class removeDuplicates {
	
	public static ArrayList<Integer> removeDuplicate(int[] array){
		ArrayList<Integer> list = new ArrayList<>();
		
		HashMap<Integer,Boolean> map = new HashMap<>();
		
		
		for(int i = 0; i <  array.length; i++) {
			if(map.containsKey(array[i])) {
				continue;
			}
			list.add(array[i]);
			map.put(array[i], true);
		}
		return list;
	}
	
	public static int maximumFrequency(int[] arr) {
		 HashMap<Integer, Integer> frequencyMap = new HashMap<>();
	        int maxFrequencyElement = arr[0]; // Initialize with the first element
	        int maxFrequency = 1; // Initial maximum frequency
	        
	        for (int num : arr) {
	            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
	            
	            if (frequencyMap.get(num) > maxFrequency ||
	                (frequencyMap.get(num) == maxFrequency && num < maxFrequencyElement)) {
	                maxFrequency = frequencyMap.get(num);
	                maxFrequencyElement = num;
	            }
	        }
	        
	        return maxFrequencyElement;
	}
	
	public static void printArrayIntersection(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        // put elements in the map, and count their frequency
        for (int i=0; i < arr1.length; i++) {
            if(frequencyMap.containsKey(arr1[i])) {
            	int value = frequencyMap.get(arr1[i]);
            	frequencyMap.put(arr1[i], value+1);
            }else {
            	frequencyMap.put(arr1[i], 1);
            }
        }
        
        for (int i = 0; i < arr2.length; i++) {
            int freq = frequencyMap.get(arr2[i]);
        	if (freq > 0) {
                System.out.print(arr2[i]);
                // subtract 1 from the frequency, and when frequency is 0 this is no longer an intersection
                frequencyMap.put(arr2[i], freq - 1);
            }
        }
    }
	
	public static int PairSum(int[] input, int size) {
	    HashMap<Integer, Integer> map = new HashMap<>();
	    int count = 0;

	    // Counting the occurrences of each element in the array
	    for (int i = 0; i < size; i++) {
	        int current = map.getOrDefault(input[i], 0);
	        map.put(input[i], current + 1);
	    }

	    // Iterating through the map to find pairs that sum up to 0
	    for (int key : map.keySet()) {
	        if (key > 0) {
	            if (map.containsKey(-key)) {
	                count += map.get(key) * map.get(-key);
	            }
	        } else if (key == 0) { // Handling the special case where the key is 0
	            int zeroCount = map.get(key);
	            count += (zeroCount * (zeroCount - 1)) / 2; // Counting pairs of zeros
	        }
	    }

	    return count;
	}

	public static void main(String[] args) {
		int[] arr = {1,3,2,2,3,1,6,2,5};
		
		ArrayList<Integer> output = removeDuplicate(arr);
		
		
	        
	   // Print the unique elements
	     System.out.println(output);
	}

}

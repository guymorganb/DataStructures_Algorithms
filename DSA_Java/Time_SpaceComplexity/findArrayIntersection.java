package Time_SpaceComplexity;
//QuickSort Time Complexity: QuickSort, in the average case, has a time complexity of O(n log n), 
//where n is the number of elements in the array. This is because the algorithm divides the array into two halves at each level (log n levels) and performs linear work (n) at each level. 
//However, in the worst-case scenario (when the array is already sorted or reverse sorted), QuickSort runs in O(n^2) time. This worst-case scenario is rare, especially when the pivot is chosen wisely (e.g., randomly or using the median-of-three method).
//
//Intersection Time Complexity: The intersection part of the code has a time complexity of O(n + m), 
//where n is the number of elements in arr1 and m is the number of elements in arr2. 
//This is because, in the worst-case scenario, the code needs to go through all the elements in both arrays once.
//
//Combining both parts, in the average case, the time complexity of the entire program is O(n log n) for sorting arr1, 
//O(m log m) for sorting arr2, and O(n + m) for finding the intersection, resulting in a total time complexity of O(n log n + m log m + n + m).
//
//However, the comment in your code states the time complexity as O(n^2), which seems to be considering the worst-case scenario for the QuickSort algorithm. 
//But remember, this scenario is quite rare with proper pivot selection strategies.
//
//Keep in mind that the Big O notation describes the upper bound of the time complexity in the worst case, 
//so it is not incorrect to say the time complexity is O(n^2), but it does not represent the average case for this code, which is more likely to be encountered.
public class findArrayIntersection {
	
	public static void quickSort(int[] arr, int start, int end){
		if (start < end){
			int pivotIndex = partition(arr, start, end);
			quickSort(arr, start, pivotIndex -1);
			quickSort(arr, pivotIndex+1, end);
		}
	}

	public static int partition(int[] arr, int start, int end){
		int pivot = arr[end];
		int i = (start - 1);
		for(int j = start; j < end; j++){
			if (arr[j] <= pivot) {
				i++;

				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			} 
		}
		int temp = arr[i + 1];
		arr[i + 1] = arr[end];
		arr[end] = temp;
		return i +1;
	}
	public static void main(String[] args) {
		// Time complexity = O(n^2) 
		// find intersection
		int[] arr1 = {2, 6, 8, 5, 4, 3};
		int[] arr2 = {6, 9, 8, 5, 9, 2};
		// sort the arrays first
		quickSort(arr1, 0, arr1.length - 1);
        quickSort(arr2, 0, arr2.length - 1);
        
        // Use two pointers to find common elements
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            // if (i > 0 && arr1[i] == arr1[i-1]) {
            //     // Skip duplicate elements in arr1
            //     i++;
            //     continue;
            // }
        	// compare element in each sorted array and increment
            if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                // Print intersection element
                System.out.print(arr1[i] + " ");
                i++;
                j++;
            }
        }
        System.out.println();
	}

}

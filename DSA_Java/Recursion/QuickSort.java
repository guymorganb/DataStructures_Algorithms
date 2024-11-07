package Recursion;

public class QuickSort {
	
    /**
     * Main quickSort method that recursively sorts the array
     * @param arr Array to be sorted
     * @param start Starting index of the subarray
     * @param end Ending index of the subarray
     */
    public static void quickSort(int[] arr, int start, int end) {
        // Only proceed if there are at least 2 elements to sort
        if (start < end) {
            // Get the partition index where the pivot is placed in its final position
            int pivotIndex = partition(arr, start, end);
            
            // Recursively sort the left side of the pivot
            quickSort(arr, start, pivotIndex - 1);
            
            // Recursively sort the right side of the pivot
            quickSort(arr, pivotIndex + 1, end);
        }
    }

    /**
     * Partitions the array and returns the pivot index
     * Uses the last element as the pivot
     * Places smaller elements to the left of pivot and larger to the right
     * @param arr Array to be partitioned
     * @param start Starting index of the partition
     * @param end Ending index of the partition
     * @return The final position of the pivot
     */
    public static int partition(int[] arr, int start, int end) {
        // Choose the last element as pivot
        int pivotPoint = arr[end];
        
        // i keeps track of the boundary between smaller and larger elements
        // Initially set to one position before start
        int i = (start - 1);
        
        // Iterate through all elements except the pivot
        for (int j = start; j < end; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivotPoint) {
                // Move the boundary forward
                i++;
                
                // Swap current element with the element at boundary
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        // Place pivot in its final position
        // All elements to left are smaller, all to right are larger
        int temp = arr[i + 1];
        arr[i + 1] = arr[end];  // Move pivot to its final spot
        arr[end] = temp;
        
        // Return the pivot's final position
        return i + 1;
    }

    public static void main(String[] args) {
        // Test array
        int[] arr = {90,31,94,80,46,28,48,31,28,90,9,27,81,43,94,5,56,13,1,23,85,71,93,53,82};
        
        // Sort the entire array
        quickSort(arr, 0, arr.length-1);
        
        // Print the sorted array
        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr: " + arr[i]);
        }
    }
}
package Recursion;

public class QuickSort {
	
    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            // pivotIndex is partitioning index, arr[pivotIndex] is now at right place
            int pivotIndex = partition(arr, start, end);

            // Recursively sort elements before and after partition
            quickSort(arr, start, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, end);
        }
    }

    public static int partition(int[] arr, int start, int end) {
        int pivotPoint = arr[end];
        int i = (start - 1); // index of smaller element

        for (int j = start; j < end; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivotPoint) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[end] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;

        return i + 1;
    }

	public static void main(String[] args) {
		int[] arr = {90,31,94,80,46,28,48,31,28,90,9,27,81,43,94,5,56,13,1,23,85,71,93,53,82};
		/////
		quickSort(arr, 0, arr.length-1);
		/////
		for (int i = 0; i<arr.length; i++) {
        System.out.println("arr: " + arr[i]);
		}
	}

}


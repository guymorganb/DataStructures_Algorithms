package Recursion;
// merge and sort array recursively, this algorithm is splitting the arrays into a tree
public class MergeSort2 {
		public static void mergeSort(int[] arr) {
			//base case
			if(arr.length <= 1) {
				return;
			}
			int[] arr1 = new int[arr.length/2];
			int[] arr2 = new int[arr.length - arr1.length];
			// copy array
			for(int i = 0; i < arr.length/2; i++) {
				arr1[i] = arr[i];
			}
			for(int i = arr.length/2; i < arr.length; i++) {
				arr2[i - arr.length/2] = arr[i];
			}
			mergeSort(arr1);
			mergeSort(arr2);
			merge(arr1, arr2, arr);
			
		}
		// merge the 2 arrays
		public static void merge(int[] source1, int[] source2, int[] destination) {
			int i = 0;
			int j = 0;
			int k = 0;
			while(i < source1.length && j < source2.length) {
				if(source1[i] <= source2[j]) {
					destination[k] = source1[i];
					i++;
					k++;
				}else {
					destination[k] = source2[j];
					k++;
					j++;
				}
			}
			// copy whatever is remaining from source 1
			if(i < source1.length) {
				while(i < source1.length) {
					destination[k] = source1[i];
					i++;
					k++;
				}
			}
			// copy whatever remains from source 2
			if(j < source2.length) {
				while(j < source2.length) {
					destination[k] = source2[j];
					k++;
					j++;
				}
			}
		}
	public static void main(String[] args) {
		// merge and sort array recursively, this algorithm is splitting the arrays into a tree
		int[] array = {90, 31, 94, 80, 46, 28, 48, 31, 28, 90, 9, 27, 81, 43, 94, 5, 56, 13, 1, 23, 85, 71, 93, 53, 82 };
		mergeSort(array);
		for(int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

}

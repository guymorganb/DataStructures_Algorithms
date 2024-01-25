package Time_SpaceComplexity;
//You have been given a random integer array/list(ARR) of size N. 
//Write a function that rotates the given array/list by D elements(towards the left).
//Note:
//Change in the input array/list itself. You don't need to return or print the elements.
public class rotateArray {
	
//	Please note that this approach has a time complexity of O(n) and a space complexity of O(d) 
//	because we are using an additional temporary array of size d. However, this is a straightforward 
//	method and is easy to understand. There are other methods with better space complexity 
//	(like the Juggling algorithm) that you can explore for more efficiency.
	
	public static void rotate(int[] arr, int d) {
        int n = arr.length;
        int[] temp = new int[d];
        
        // Step 1: Copy the first 'd' elements in a temporary array
        for (int i = 0; i < d; i++) {
            temp[i] = arr[i];
        }
        
        // Step 2: Shift the rest of the arr[] elements towards left 
        for (int i = d; i < n; i++) {
            arr[i - d] = arr[i];
        }
        
        // Step 3: Store back the d elements
        for (int i = 0; i < d; i++) {
            arr[i + n - d] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int d = 2;
        
        rotate(arr, d);
        
        // Print the rotated array
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
	}

}

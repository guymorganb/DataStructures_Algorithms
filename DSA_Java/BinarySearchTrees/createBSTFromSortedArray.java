package BinarySearchTrees;

import BinaryTrees.BinaryTreeNode;

public class createBSTFromSortedArray {

//	Given a sorted integer array A of size n, which contains all unique elements. You need to construct a balanced BST from this input array. Return the root of constructed BST.
//	Note: If array size is even, take first mid as root.
	
	 // Helper method that constructs BST recursively
    private static BinaryTreeNode<Integer> ToBST(int[] arr, int start, int end){
        // Base case: if start index is more than end index, it means we have traversed all elements in this section of the array
        if(start > end) {
            return null;
        }
        
        // Calculate the middle index
        int mid = start + (end - start) / 2;

        // Create a new node with the middle element
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(arr[mid]);

        // Construct the left subtree by recursively finding the middle element in the left half of the current section of the array
        root.left = ToBST(arr, start, mid - 1);

        // Construct the right subtree by recursively finding the middle element in the right half of the current section of the array
        root.right = ToBST(arr, mid + 1, end);

        // Return the root node of the constructed BST
        return root;
    }

    // Method that initiates the recursive BST construction
    public static BinaryTreeNode<Integer> SortedArrayToBST(int[] arr, int n){
        // Call the helper method with the full array (from index 0 to n-1)
        return ToBST(arr, 0, n - 1);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1,2,3,4,5,6};
		
	}

}

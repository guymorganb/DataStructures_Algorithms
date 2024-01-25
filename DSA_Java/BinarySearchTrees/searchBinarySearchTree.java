package BinarySearchTrees;

import BinaryTrees.BinaryTreeNode;

public class searchBinarySearchTree {

	public static boolean searchBST(BinaryTreeNode<Integer> root, int k) {
		if(root == null) {
			return false;
		}
		if (root.data == k) {
	        return true; // Element k found in the current node
	    } else if (root.data > k) {
	        return searchBST(root.left, k); // Search in the left subtree
	    } else {
	        return searchBST(root.right, k); // Search in the right subtree
	    }
	}
	
	public static void findAndPrintElementsBetweenK1andK2(BinaryTreeNode<Integer> root, int k1, int k2) {
	    if (root == null) {
	        return;
	    }

	    // If the current node's data is greater than k1, search the left subtree
	    if (root.data > k1) {
	        findAndPrintElementsBetweenK1andK2(root.left, k1, k2);
	    }

	    // If the current node's data is between k1 and k2, print the node's data
	    if (root.data >= k1 && root.data <= k2) {
	        System.out.print(root.data + " ");
	    }

	    // If the current node's data is less than k2, search the right subtree
	    if (root.data < k2) {
	        findAndPrintElementsBetweenK1andK2(root.right, k1, k2);
	    }
	}
	
	// Check if tree is Binary search tree///////////////////////////////////
	 // Helper class to store the min and max values
    private static class MinMax {
        int min;
        int max;
        boolean isBST;
    }

    public static boolean isBST(BinaryTreeNode<Integer> root) {
        return isBSTHelper(root).isBST;
    }

    private static MinMax isBSTHelper(BinaryTreeNode<Integer> node) {
        // An empty tree is BST
        if (node == null) {
            MinMax m = new MinMax();
            m.min = Integer.MAX_VALUE;
            m.max = Integer.MIN_VALUE;
            m.isBST = true;
            return m;
        }

        // Recursively check left and right subtrees
        MinMax left = isBSTHelper(node.left);
        MinMax right = isBSTHelper(node.right);

        MinMax m = new MinMax();
        m.isBST = (left.isBST && right.isBST && (node.data >= left.max && node.data < right.min));
        m.min = Math.min(node.data, Math.min(left.min, right.min));
        m.max = Math.max(node.data, Math.max(left.max, right.max));
        
        return m;
    }
    	///////////////////////////////////
    
	public static void main(String[] args) {
		// create a Binary search tree(all elements to left of root on main tree and subtrees are less than root, all elements to the right side are greater 
		// root		
		// first level		
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(8);
		//second level
			BinaryTreeNode<Integer> rootLeft = new BinaryTreeNode<Integer>(5);
			BinaryTreeNode<Integer> rootRight = new BinaryTreeNode<Integer>(10);
			// set roots left node to point to rootLeft
			root.left = rootLeft;
			// set roots right node to point toward rootRight
			root.right = rootRight;
			// third level (left side)
				BinaryTreeNode<Integer> rootLeft_LeftChild = new BinaryTreeNode<Integer>(2);
				BinaryTreeNode<Integer> rootLeft_RightChild = new BinaryTreeNode<Integer>(6);
				// set rootLeft's node to point to the left child
				rootLeft.left = rootLeft_LeftChild;
				// set rootLeft's node to point to the right child
				rootLeft.right = rootLeft_RightChild;
			//third level right side
			BinaryTreeNode<Integer> rootRight_leftChild = new BinaryTreeNode<Integer>(9);
			BinaryTreeNode<Integer> rootRight_rightChild = new BinaryTreeNode<Integer>(13);
				rootRight.left = rootRight_leftChild;
				rootRight.right = rootRight_rightChild;
				
		
				
			
				

	}

}

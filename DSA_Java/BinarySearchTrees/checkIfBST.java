package BinarySearchTrees;

import BinaryTrees.BinaryTreeNode;

public class checkIfBST {
	
	public static int minimum(BinaryTreeNode<Integer> root) {
		if(root == null) {
			// if its an empty set return the maximum sized minimum value
			return Integer.MAX_VALUE;
		}
		int leftMin = minimum(root.left);
		int rightMin = minimum(root.right);
		
		return Math.min(root.data, Math.min(leftMin,  rightMin));
		
	}
	
	public static int maximum(BinaryTreeNode<Integer> root) {
		if(root == null) {
			return Integer.MIN_VALUE;
		}
		int largestLeft = maximum(root.left);
		int largestRight = maximum(root.right);
		return Math.max(root.data, Math.max(largestLeft, largestRight));
	}
	
	// check if Binary Tree is a Binary Search tree
	public static boolean isBST(BinaryTreeNode<Integer> root) {
		if(root == null) {
			return true;
		}
		// root has to be larger than everything on the left side making a function for this
		int leftMax = maximum(root.left);
		if(leftMax >= root.data) {
			return false;
		}
		// root must be smaller than everything on the right side for Tree to be BinarySearchTree
		int rightMin = minimum(root.right);
		if(rightMin < root.data) {
			return false;
		}
		
		boolean isLeftBST = isBST(root.left);
		boolean isRightBST = isBST(root.right);
		return isLeftBST && isRightBST;
		
	}
	// to improve the speed of the isBST function we need to create a class(isBSTReturn) which will return min/max/isBST so we only
	// need to make one call to each side of the binary tree to determine if it s a BST
	public static isBSTReturn isBSTBetter(BinaryTreeNode<Integer> root) {
		if(root == null) {
			isBSTReturn ans = new isBSTReturn(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
			return ans;
		}
	
		isBSTReturn leftSide = isBSTBetter(root.left);
		isBSTReturn rightSide = isBSTBetter(root.right);
		
		// finding the minimum and maximum value between the top level root/leftSide/rightSide 
		int min = Math.min(root.data, Math.min(leftSide.min, rightSide.min));
		int max = Math.max(root.data, Math.max(leftSide.max, rightSide.max));
		
		// no we need to find if overall is the tree a BST
		boolean isBST = true;
		if(leftSide.max >= root.data) {
			// violation of BST rules
			isBST = false;
		}
		if(rightSide.min <= root.data) {
			// violates isBST rules
			isBST = false;
		}
		if(!leftSide.isBst) {
			isBST = false;
		}
		if(!rightSide.isBst) {
			isBST = false;
		}
		isBSTReturn ans = new isBSTReturn(min, max, isBST);
		return ans;
	 
	}
	
	// another way to solve isBST
	public static boolean isBST2(BinaryTreeNode<Integer> root, int minRange, int maxRange) {
		if(root == null) {
			return true;
		}
		
		if(root.data < minRange || root.data > maxRange) {
			return false;
		}
		// max on left side must be smaller than roots data
		boolean isLeftWithinRange = isBST2(root.left, minRange, root.data - 1);
		// max on right side must be larger than roots data
		boolean isRightWithinRange = isBST2(root.right, root.data, maxRange);
		return isLeftWithinRange && isRightWithinRange;
		
	}
	
	public static void main(String[] args) {
		

	}

}

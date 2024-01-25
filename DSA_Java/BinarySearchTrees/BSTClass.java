package BinarySearchTrees;

import BinaryTrees.BinaryTreeNode;

public class BSTClass {
	
	private BinaryTreeNode<Integer> root;
	private int size;
//////////////element isPresent/////////////////////////////////	
	// we use a helper function so we dont have to search the BST in an iterative manner
	// this function is static because it operates independently and only works on the node which the user passes it, not the entire BST
	private static boolean isPresentHelper(BinaryTreeNode<Integer> RootNode, int x) {
		if(RootNode == null) {
			return false;
		}
		if(RootNode.data == x) {
			return true;
		}
		if(x < RootNode.data) {
			// call left side
			return isPresentHelper(RootNode.left, x);
		}else {
			// call on the right side
			return isPresentHelper(RootNode.right, x);
		}
	}
	
	public boolean isPresent(int x) {
		return isPresentHelper(root, x);
		
	}
//////////////insert/////////////////////////////////
	private static BinaryTreeNode<Integer> InsertHelper(BinaryTreeNode<Integer> node, int x){
		// if there is no root node create one and set it equal to x
		if(node == null) {
			BinaryTreeNode<Integer> newRoot = new BinaryTreeNode<Integer>(x);
			return newRoot;
		}
		// if root node is present but x is greater than root, x must be inserted on the right side of BST 
		if(x >= node.data) {
			BinaryTreeNode<Integer> newRoot = InsertHelper(node.right, x);
			node.right = newRoot;
		}else {
			// if x is not greater than root.data must insert on the left side of BST
			BinaryTreeNode<Integer> newRoot = InsertHelper(node.left, x);
			node.left = newRoot;
		}
		return node;
	}
	
	public void insert(int x) {
		// update the root using the InsertHelper function
		root = InsertHelper(root, x);
		size++;
		
	}
//////////////delete: requires many functions//////////////////////////////////
	// find the minimum on the right
	private static int minimum(BinaryTreeNode<Integer> root) {
		if(root == null) {
			return Integer.MAX_VALUE;
		}
		
		int minimumLeft = minimum(root.left);
		int minimumRight = minimum(root.right);
		return Math.min(root.data, Math.min(minimumLeft, minimumRight));
	}
	// we want to return if the element was deleted and the new root
	private static class BSTdeleteReturn{
		boolean elementDeleted;
		BinaryTreeNode<Integer> newRoot;
		// constructor function
		public BSTdeleteReturn(BinaryTreeNode<Integer> newRoot, boolean elementDeleted) {
			this.newRoot = newRoot;
			this.elementDeleted = elementDeleted;
		}

	}
	
	private static BSTdeleteReturn deleteDataHelper(BinaryTreeNode<Integer> root, int x) {
		if(root == null) {
			return new BSTdeleteReturn(null, false);
		}
		
		if(root.data < x) {
			BSTdeleteReturn outputRight = deleteDataHelper(root.right, x);
			root.right = outputRight.newRoot;
			outputRight.newRoot = root;
			return outputRight;
		}
		
		if(root.data > x) {
			BSTdeleteReturn outputLeft = deleteDataHelper(root.left, x);
			root.left = outputLeft.newRoot;
			outputLeft.newRoot = root;
			return outputLeft;
		}
		// 0 children
		if(root.left == null && root.right == null) {
			return new BSTdeleteReturn(null, true);
		}
		// only left child
		if(root.left != null && root.right == null) {
			return new BSTdeleteReturn(root.left, true);
		}
		
		// only right child
		if(root.left == null && root.right != null) {
			return new BSTdeleteReturn(root.right, true);
		}
		
		// both children are present, find the minimum on the right, and delete x, then set the root to the minimum
		int rightMin = minimum(root.right);
		root.data = rightMin;
		BSTdeleteReturn outputRight = deleteDataHelper(root.right, rightMin);
		root.right = outputRight.newRoot;
		return new BSTdeleteReturn(root, true);
	}
	
	public boolean deleteData(int x) {
		BSTdeleteReturn output = deleteDataHelper(root, x);
		root = output.newRoot;
		if(output.elementDeleted) {
			size--;
		}
		return output.elementDeleted;
	}
//////////////size/////////////////////////////////
	public int size() {
		return size;
	}
//////////////print/////////////////////////////////
	private static void printTreeHelper(BinaryTreeNode<Integer> rootNode) {
		if(rootNode == null) {
			return;
		}
		System.out.print(rootNode.data + ": ");
		
		if(rootNode.left != null) {
			System.out.print("L"+ rootNode.left.data + ", ");
		}
		if(rootNode.right != null) {
			System.out.print("R"+ rootNode.right.data + ", ");
		}
	
		System.out.println();
		
		printTreeHelper(rootNode.left);
		printTreeHelper(rootNode.right);
	}
	
	public void printTree() {
		printTreeHelper(root);
	}
//////////////height/////////////////////////////////
	private static int heightHelper(BinaryTreeNode<Integer> node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int height() {
        return heightHelper(root);
    }
	
}

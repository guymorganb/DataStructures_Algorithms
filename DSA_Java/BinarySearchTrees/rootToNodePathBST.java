package BinarySearchTrees;

import java.util.ArrayList;

import BinaryTrees.BinaryTreeNode;

public class rootToNodePathBST {
	// search for a value in a binary tree and return the path
	public static ArrayList<Integer> nodeToRootPath(BinaryTreeNode<Integer> root, int x){
		if(root == null) {
			return null;
		}
		if(root.data == x) {
			// if x is found create an array list and add the path to it.
			ArrayList<Integer> path = new ArrayList<>();
			path.add(x);
			return path;
		}
		// search for x value on the left side of the binary tree
		ArrayList<Integer> leftOutput = nodeToRootPath(root.left, x);
		if(leftOutput != null) {
			leftOutput.add(root.data);
			return leftOutput;
		}
		// search for x value on the right side and add to the array list
		ArrayList<Integer> rightOutput = nodeToRootPath(root.right, x);
		if(rightOutput != null) {
			// if right output is not null we've found it
			rightOutput.add(root.data);
			return rightOutput;
		}
		// if we haven't found x anywhere return null
		return null;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

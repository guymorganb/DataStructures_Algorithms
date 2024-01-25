package BinaryTrees;
import java.util.ArrayList;
public class BinaryTreeUse {
	
	public static void printPre_orderedTreeDetailed(BinaryTreeNode<Integer> root) {
		// base case
		if(root == null) {
			return;
		}
		System.out.print(root.data + ": ");
		
		if(root.left != null) {
			System.out.print("L"+ root.left.data + ", ");
		}
		
		if(root.right != null) {
			System.out.print("R"+ root.right.data + ", ");
		}
		
		System.out.println();
		
		
		printPre_orderedTreeDetailed(root.left);
		printPre_orderedTreeDetailed(root.right);
		
	}
	
	
	public static void printTreePre_Ordered(BinaryTreeNode<Integer> root) {
		// base case
		if(root == null) {
			return;
		}
		System.out.print(root.data + " ");
		printTreePre_Ordered(root.left);
		printTreePre_Ordered(root.right);
		
	}
////////////////////////////////////////////////////////////////////////
//	Given the binary Tree and two nodes say ‘p’ and ‘q’. Determine whether the two nodes are cousins of each other or not. 
//	Two nodes are said to be cousins of each other if they are at same level of the Binary Tree and have different parents
	private static int level(BinaryTreeNode<Integer> root, int x, int lev) {
		if (root == null) {
			return 0;
		}
		if (root.data == x) {
			return lev;
		}

		int level = level(root.left, x, lev + 1);
		if (level != 0) {
			return level;
		}

		return level(root.right, x, lev + 1);
	}

	private static boolean isSibling(BinaryTreeNode<Integer> root, int a, int b) {
		if (root == null) {
			return false;
		}

		return (root.left != null && root.right != null && 
				((root.left.data == a && root.right.data == b) || 
				 (root.left.data == b && root.right.data == a))) || 
				isSibling(root.left, a, b) || 
				isSibling(root.right, a, b);
	}

	public static boolean isCousin(BinaryTreeNode<Integer> root, int p, int q) {
		return (level(root, p, 1) == level(root, q, 1)) && !isSibling(root, p, q);
	}
////////////////////////////////////////////////////////////////////////
	// Given a binary tree, return the longest path from leaf to root. 
	// Longest means, a path which contain maximum number of nodes from leaf to root.
	private static ArrayList<Integer> longestPath(BinaryTreeNode<Integer> root) {
		if (root == null) {
			return new ArrayList<Integer>();
		}

		ArrayList<Integer> leftPath = longestPath(root.left);
		ArrayList<Integer> rightPath = longestPath(root.right);

		if (leftPath.size() > rightPath.size()) {
			leftPath.add(root.data);
			return leftPath;
		} else {
			rightPath.add(root.data);
			return rightPath;
		}
	}

	public static ArrayList<Integer> longestRootToLeafPath(BinaryTreeNode<Integer> root) {
		ArrayList<Integer> result = longestPath(root);
		return result;
	}
/////////////////////////////////////////////////////////////////////
	
	
	
	public static void main(String[] args) {
		// create the root of the binary tree 
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
		// set the data of the root if no constructor function
		// root.data = 1;
		// making a 
		BinaryTreeNode<Integer> rootLeft = new BinaryTreeNode<Integer>(2);
		BinaryTreeNode<Integer> rootRight = new BinaryTreeNode<Integer>(3);
		// set roots left node to point to rootLeft
		root.left = rootLeft;
		// set roots right node to point toward rootRight
		root.right = rootRight;
		
		
		
		BinaryTreeNode<Integer> rootLeft_RightChild = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> rootRight_LeftChild = new BinaryTreeNode<Integer>(5);
		// set rootLeft's node to point to the right side child rootLeft_RightChild
		rootLeft.right = rootLeft_RightChild;
		// set rootRight's node to point to the left side child rootRight_leftChild
		rootRight.left = rootRight_LeftChild;
		
		printPre_orderedTreeDetailed(root);
		
	}

}

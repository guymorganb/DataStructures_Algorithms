package BinaryTrees;
import java.util.ArrayList;
import java.util.*;

public class extraProblemsBinaryTrees {
	//For a given Binary Tree of type integer and a number K, 
	//print out all root-to-leaf paths where the sum of all the node data along the path is equal to K.
	
//	To solve this problem, you can use a recursive helper function that maintains a current path and its sum. 
//	When it reaches a leaf node, it checks if the total sum equals K and if so, 
//	it prints the path. Here is the solution code:
	
	public static void rootToLeafPathsSumToK(BinaryTreeNode<Integer> root, int k) {
        ArrayList<Integer> path = new ArrayList<>();
        rootToLeafPathsSumToKHelper(root, k, 0, path);
    }

    private static void rootToLeafPathsSumToKHelper(BinaryTreeNode<Integer> root, int k, int currentSum, ArrayList<Integer> path) {
        if (root == null) {
            return;
        }

        // add current node's data to the path and update the current sum
        path.add(root.data);
        currentSum += root.data;

        // if the current node is a leaf node and the current sum equals k, print the path
        if (root.left == null && root.right == null && currentSum == k) {
            for (int i : path) {
                System.out.print(i + " ");
            }
            System.out.println();
        } else {
            // otherwise, recursively call the function on the left and right subtrees
            rootToLeafPathsSumToKHelper(root.left, k, currentSum, path);
            rootToLeafPathsSumToKHelper(root.right, k, currentSum, path);
        }

        // remove the current node from the path before returning
        path.remove(path.size() - 1);
    }
////////////////////////////////////////////////////////////////////////////////////////////
//    For a given a Binary Tree of type integer, find and return the minimum and the maximum data values.
//    		Return the output as an object of Pair class, which is already created.
//    		Note:
//    		All the node data will be unique and hence there will always exist a minimum and maximum 
    
//    To solve this problem, you can use a recursive approach to traverse the entire binary tree and find the minimum and maximum values. 
//    The base case of the recursion would be when the node is null, in which case it would return a Pair with both elements set to null. 
//    Otherwise, it would compare the current node's value with the minimum and maximum values of the left and right subtrees 
//    to find the overall minimum and maximum values.
    
//	Representation of the Pair Class

    static class Pair<T, U> {
		T minimum;
		U maximum;

		public Pair(T minimum, U maximum) {
			this.minimum = minimum;
			this.maximum = maximum;
		}
	}



public class Solution {

    public static Pair<Integer, Integer> getMinAndMax(BinaryTreeNode<Integer> root) {
        // If the tree is empty, return null for both min and max values
        if (root == null) {
            return new Pair<>(null, null);
        }

        // Recursively get min and max from left and right subtrees
        Pair<Integer, Integer> left = getMinAndMax(root.left);
        Pair<Integer, Integer> right = getMinAndMax(root.right);

        // Calculate the minimum and maximum values
        int min = root.data;
        int max = root.data;

        // Compare with left subtree
        if (left.minimum != null && left.minimum < min) {
            min = left.minimum;
        }
        if (left.maximum != null && left.maximum > max) {
            max = left.maximum;
        }

        // Compare with right subtree
        if (right.minimum != null && right.minimum < min) {
            min = right.minimum;
        }
        if (right.maximum != null && right.maximum > max) {
            max = right.maximum;
        }

        // Return the pair of minimum and maximum values
        return new Pair<>(min, max);
    }
    
}
////////////////////////////////////////////////////////////////////////////////////////////

//You are given a Binary Tree of type integer, a target node, and an integer value K.
//Print the data of all nodes that have a distance K from the target node. The order in which they would be printed will not matter.

	private static void printNodesAtKDistance(BinaryTreeNode<Integer> node, int k) {
	    if (node == null || k < 0) {
	        return;
	    }
	    if (k == 0) {
	        System.out.println(node.data);
	        return;
	    }
	    printNodesAtKDistance(node.left, k - 1);
	    printNodesAtKDistance(node.right, k - 1);
	}
	
	// Helper method to print nodes that are at k distance from target node
	private static int printNodesAtKDistance(BinaryTreeNode<Integer> node, int target, int k) {
	    // Base cases
	    if (node == null) {
	        return -1;
	    }
	    if (node.data == target) {
	        printNodesAtKDistance(node, k);
	        return 0;
	    }
	
	    // Check if target node is in left subtree or right subtree
	    int dl = printNodesAtKDistance(node.left, target, k);
	
	    if (dl != -1) {
	        if (dl + 1 == k) {
	            System.out.println(node.data);
	        } else {
	            printNodesAtKDistance(node.right, k - dl - 2);
	        }
	        return 1 + dl;
	    }
	
	    int dr = printNodesAtKDistance(node.right, target, k);
	    if (dr != -1) {
	        if (dr + 1 == k) {
	            System.out.println(node.data);
	        } else {
	            printNodesAtKDistance(node.left, k - dr - 2);
	        }
	        return 1 + dr;
	    }
	
	    return -1;
	}
	
	public static void nodesAtDistanceK(BinaryTreeNode<Integer> root, int node, int k) {
	    printNodesAtKDistance(root, node, k);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////
//	For a given a Binary Tree of type integer, duplicate every node of the tree and attach it to the left of itself.
//	The root will remain the same. So you just need to insert nodes in the given Binary Tree.
	public static void insertDuplicateNode(BinaryTreeNode<Integer> root) {
		// Base case: if root is null, return
		if (root == null) {
			return;
		}

		// Create a new node duplicating the current node
		BinaryTreeNode<Integer> newNode = new BinaryTreeNode<>(root.data);
		
		// Save the current left child
		BinaryTreeNode<Integer> temp = root.left;
		
		// Make the new node as the left child
		root.left = newNode;
		
		// Make the old left child as the left child of new node
		newNode.left = temp;
		
		// Recursively do this for the remaining tree
		insertDuplicateNode(temp);
		insertDuplicateNode(root.right);
	}
////////////////////////////////////////////////////////////////////////////////////////////
//	For a given a Binary Tree of type integer, print it in a level order fashion where each level will be printed on a new line. 
//	Elements on every level will be printed in a linear fashion and a single space will separate them.
	
	public static void printLevelWise(BinaryTreeNode<Integer> root) {
		if (root == null) {
			return;
		}

		Queue<BinaryTreeNode<Integer>> currentLevel = new LinkedList<>();
		Queue<BinaryTreeNode<Integer>> nextLevel = new LinkedList<>();

		currentLevel.add(root);

		while (!currentLevel.isEmpty()) {
			BinaryTreeNode<Integer> currNode = currentLevel.poll();
			System.out.print(currNode.data + " ");

			if (currNode.left != null) {
				nextLevel.add(currNode.left);
			}

			if (currNode.right != null) {
				nextLevel.add(currNode.right);
			}

			if (currentLevel.isEmpty()) {
				System.out.println();
				Queue<BinaryTreeNode<Integer>> temp = currentLevel;
				currentLevel = nextLevel;
				nextLevel = temp;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

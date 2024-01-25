package GenericTrees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GenericTreeUse {
////////////////////////////////////////////////////////////////////////////
	public static GenericTreeNode<Integer> levelWise() {
	    Scanner s = new Scanner(System.in);
	    Queue<GenericTreeNode<Integer>> pendingNodes = new LinkedList<>();
	    System.out.println("Enter root data: ");
	    int rootData = s.nextInt();
	    if (rootData == -1) {
	        return null;
	    }
	    GenericTreeNode<Integer> root = new GenericTreeNode<>(rootData);
	    pendingNodes.add(root);

	    while (!pendingNodes.isEmpty()) {
	        GenericTreeNode<Integer> currentNode = pendingNodes.remove();
	        System.out.println("Enter the number of children for: " + currentNode.data);
	        int numChildren = s.nextInt();
	        // take input children
	        for (int i = 1; i <= numChildren; i++) {
	            System.out.println("Enter the " + i + "th child data for: " + currentNode.data);
	            int childData = s.nextInt();
	            GenericTreeNode<Integer> childNode = new GenericTreeNode<>(childData);
	            currentNode.children.add(childNode);
	            pendingNodes.add(childNode);
	        }
	    }
	    return root;
	}
////////////////////////////////////////////////////////////////////////////
	public static void printLevelWise(GenericTreeNode<Integer> root) {
        if (root == null) {
            return;
        }

        Queue<GenericTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();

            for (int i = 0; i < currentLevelSize; i++) {
                GenericTreeNode<Integer> currentNode = queue.poll();
                System.out.print(currentNode.data + " ");

                for (GenericTreeNode<Integer> child : currentNode.children) {
                    queue.add(child);
                }
            }

            System.out.println();
        }
    }
////////////////////////////////////////////////////////////////////////////
	// count number of nodes greater than x
	public static int numNodeGreater(GenericTreeNode<Integer> root, int x) {
        // Base case
        if (root == null) {
            return 0;
        }

        // Count the current node if its data is greater than x
        int count = root.data > x ? 1 : 0;

        // Iterate through the children and recursively call numNodeGreater
        for (int i = 0; i < root.children.size(); i++) {
            count += numNodeGreater(root.children.get(i), x);
        }

        return count;
    }
////////////////////////////////////////////////////////////////////////////
	public static int getHeight(GenericTreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}

		int height = 0;
		for (int i = 0; i < root.children.size(); i++) {
			int childHeight = getHeight(root.children.get(i));
			if (childHeight > height) {
				height = childHeight;
			}
		}
		return height + 1;
	}
////////////////////////////////////////////////////////////////////////////
	public static void printPostOrder(GenericTreeNode<Integer> root) {
		if (root == null) {
			return;
		}

		// Visit the child nodes first
		for (int i = 0; i < root.children.size(); i++) {
			printPostOrder(root.children.get(i));
		}

		// Print the root node
		System.out.print(root.data + " ");
	}
////////////////////////////////////////////////////////////////////////////
	public static boolean checkIfContainsX(GenericTreeNode<Integer> root, int x) {
		// Base condition, if root is null, x is not found
		if (root == null) {
			return false;
		}

		// Check if the current root data is equal to x
		if (root.data == x) {
			return true;
		}

		// Recursively check in the children
		for (int i = 0; i < root.children.size(); i++) {
			if (checkIfContainsX(root.children.get(i), x)) {
				return true;
			}
		}

		return false; // If x is not found in the entire tree
	}
////////////////////////////////////////////////////////////////////////////
	public static GenericTreeNode<Integer> maxSumNode(GenericTreeNode<Integer> root) {
		if (root == null) {
			return null;
		}

		GenericTreeNode<Integer> maxNode = root; // Initially, assume root node has the maximum sum
		int maxSum = root.data; // Sum for the root node
		for (int i = 0; i < root.children.size(); i++) {
			maxSum += root.children.get(i).data; // Add the sum of immediate children
		}

		for (int i = 0; i < root.children.size(); i++) {
			GenericTreeNode<Integer> childMaxNode = maxSumNode(root.children.get(i)); // Recurse into children
			int childSum = childMaxNode.data;
			for (int j = 0; j < childMaxNode.children.size(); j++) {
				childSum += childMaxNode.children.get(j).data;
			}

			if (childSum > maxSum) { // Update the maxNode if a child's sum is greater
				maxSum = childSum;
				maxNode = childMaxNode;
			}
		}

		return maxNode;
	}
	
////////////////////////////////////////////////////////////////////////////
	public static boolean checkIdentical(GenericTreeNode<Integer> root1, GenericTreeNode<Integer> root2) {

        // If either of the trees is null, they are identical if and only if both are null
        if (root1 == null || root2 == null) {
            return root1 == root2;
        }

        // If the data of the root nodes is different, the trees cannot be identical
        if (!root1.data.equals(root2.data)) {
            return false;
        }

        // If the number of children is different, the trees cannot be identical
        if (root1.children.size() != root2.children.size()) {
            return false;
        }

        // Recursively check for identical structure in corresponding child nodes
        for (int i = 0; i < root1.children.size(); i++) {
            if (!checkIdentical(root1.children.get(i), root2.children.get(i))) {
                return false;
            }
        }

        return true; // If all checks passed, the trees are identical
    }
////////////////////////////////////////////////////////////////////////////
	// Given a generic tree and an integer n. Find and return the node with next larger element in the Tree i.e. find a node with value just greater than n.
	// Return NULL if no node is present with value greater than n.
	 public static GenericTreeNode<Integer> findNextLargerNode(GenericTreeNode<Integer> root, int n) {
	        // If the tree is empty, return null
	        if (root == null) {
	            return null;
	        }

	        // Initialize the result as null
	        GenericTreeNode<Integer> nextLarger = null;

	        // If the root's data is greater than n, update the result
	        if (root.data > n) {
	            nextLarger = root;
	        }

	        // Iterate through the children of the root and recursively find the next larger node in each subtree
	        for (GenericTreeNode<Integer> child : root.children) {
	            GenericTreeNode<Integer> childNextLarger = findNextLargerNode(child, n);
	            
	            // If a next larger node is found in the child's subtree, update the result if it's the smallest value found so far
	            if (childNextLarger != null &&
	                    (nextLarger == null || childNextLarger.data < nextLarger.data)) {
	                nextLarger = childNextLarger;
	            }
	        }

	        return nextLarger; // Return the final result
	    }
////////////////////////////////////////////////////////////////////////////
	 static class Pair {
	        GenericTreeNode<Integer> largest;
	        GenericTreeNode<Integer> secondLargest;
	    }

	    private static Pair getLargestAndSecondLargest(GenericTreeNode<Integer> root) {
	        Pair result = new Pair();
	        if (root == null) return result;

	        result.largest = root;

	        for (GenericTreeNode<Integer> child : root.children) {
	            Pair childPair = getLargestAndSecondLargest(child);

	            if (childPair.largest.data > result.largest.data) {
	                result.secondLargest = result.largest;
	                result.largest = childPair.largest;
	            } else if (result.secondLargest == null || 
	                      (childPair.largest.data > result.secondLargest.data && 
	                      !childPair.largest.data.equals(result.largest.data))) {
	                result.secondLargest = childPair.largest;
	            }

	            if (childPair.secondLargest != null && 
	                (result.secondLargest == null || 
	                 childPair.secondLargest.data > result.secondLargest.data) && 
	                !childPair.secondLargest.data.equals(result.largest.data)) {
	                result.secondLargest = childPair.secondLargest;
	            }
	        }

	        return result;
	    }

	    public static GenericTreeNode<Integer> findSecondLargest(GenericTreeNode<Integer> root) {
	        return getLargestAndSecondLargest(root).secondLargest;
	    }
	    
////////////////////////////////////////////////////////////////////////////   
//	    In a given Generic Tree, replace each node with its depth value. You need to just update the data of each node, 
//	    no need to return or print anything. Depth of a node is defined as the distance of the node from root. You may assume that depth of root node is 0.
	    public static void replaceWithDepthValue(GenericTreeNode<Integer> root){
	        replaceWithDepthValueHelper(root, 0);
	    }

	    public static void replaceWithDepthValueHelper(GenericTreeNode<Integer> root, int depth){
	        if (root == null) {
	            return;
	        }
	        
	        // Replacing the value with depth
	        root.data = depth;

	        for (GenericTreeNode<Integer> child : root.children) {
	            // Call recursively for each child with incremented depth
	            replaceWithDepthValueHelper(child, depth + 1);
	        }
	    }
////////////////////////////////////////////////////////////////////////////
	    public static int countLeafNodes(GenericTreeNode<Integer> root){
	        // Base case: if the root is null, return 0
	        if (root == null) {
	            return 0;
	        }

	        // Check if the root node is a leaf node (has no children)
	        if (root.children.size() == 0) {
	            return 1;
	        }

	        // Recursive case: traverse the children and count leaf nodes
	        int leafCount = 0;
	        for (GenericTreeNode<Integer> child : root.children) {
	            leafCount += countLeafNodes(child);
	        }

	        return leafCount;
	    }
////////////////////////////////////////////////////////////////////////////
	    public static GenericTreeNode<Integer> removeLeafNodes(GenericTreeNode<Integer> root) {
			if (root == null) {
				return null; // Base case: if root is null, return null
			}
			
			// Iterate over the children of the current node
			for (int i = 0; i < root.children.size(); i++) {
				GenericTreeNode<Integer> child = root.children.get(i);
				if (child.children.size() == 0) { // Check if the child is a leaf node
					root.children.remove(i); // Remove the child if it's a leaf
					i--; // Decrement the index to adjust for the removed child
				} else {
					removeLeafNodes(child); // Recursively call the function for non-leaf children
				}
			}

			// If the root itself becomes a leaf node, return null
			if (root.children.size() == 0) {
				return null;
			}
			
			return root; // Return the root of the modified tree
		}
////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		
		// creates a new generic tree root containing 4 as its data, and an ArrayList with references to its children
		GenericTreeNode<Integer> root = new GenericTreeNode<>();
		root.inputLevelWise();
		root.printTreeBetter(root);
		
		// creating many nodes
//		GenericTreeNode<Integer> node1 = new GenericTreeNode<Integer>(2); 
//		GenericTreeNode<Integer> node2 = new GenericTreeNode<Integer>(3);
//		GenericTreeNode<Integer> node3 = new GenericTreeNode<Integer>(1);
//		GenericTreeNode<Integer> node4 = new GenericTreeNode<Integer>(5);
//		GenericTreeNode<Integer> node5 = new GenericTreeNode<Integer>(6);
//		// added the reference to roots' children
//		root.children.add(node1);
//		root.children.add(node2);
//		root.children.add(node3);
//		// added references to node2's children
//		node2.children.add(node4);
//		node2.children.add(node5);
//		// print the generic tree
//		
//		//System.out.println(root.countNodes(root));
//		System.out.println(root.sumOfNodes(root));
	}

}

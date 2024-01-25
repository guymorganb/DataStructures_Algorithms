package BinaryTrees;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class takeInputLevelwise {
// to take input level wise for a tree we must implement use of a queue	
	// take root input
	// insert it into a queue
	// while queue is not empty: 
//		-take the first element out
//		-Ask for its left child node
//			-if left is not = -1, attach to parent element on left side, & push left to the queue
//		-Ask for its right child node
//			-if right is not = -1, attach to parent element on right side, and push to the queue
	
	public static BinaryTreeNode<Integer> takeInput(){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Root data");
		int rootData = s.nextInt();
		
		if(rootData == -1) {
			return null;
		}
		
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
		
		// because Queue is an interface we need to use a class which implements this interface, we will use linked list class
		// Note: we have to put the entire Node inside the queue in order to assign the left and right children
		Queue<BinaryTreeNode<Integer>> pendingChildren = new LinkedList<BinaryTreeNode<Integer>>();
		
		// insert the root inside the queue
		pendingChildren.add(root);
		
		// while pending children queue is not empty, take out the first element 
		while(!pendingChildren.isEmpty()) {
			BinaryTreeNode<Integer> front = pendingChildren.poll();
			// ask for fronts left child
			System.out.println("Enter left child of " + front.data);
			int left = s.nextInt();
			//
			if(left != -1) {
				// creates a new left child node
				BinaryTreeNode<Integer> leftChild = new BinaryTreeNode<Integer>(left);
				front.left = leftChild;
				// add the new leftChild to the pending queue, so we can ask for its children later
				pendingChildren.add(leftChild);
			}
			// repeat the work for the right side
			System.out.println("Enter right child of " + front.data);
			int right = s.nextInt();
			//
			if(right != -1) {
				// creates a new right child node
				BinaryTreeNode<Integer> rightChild = new BinaryTreeNode<Integer>(right);
				front.right = rightChild;
				// add the new rightChild to the pending queue, so we can ask for its children later
				pendingChildren.add(rightChild);
			}
		}
		return root;
	}
	
	public static void printLevelwise(BinaryTreeNode<Integer> root) {
		// add root to the queue, if root is null, do nothing
		// while queue is not empty take out first node, print it, check for children, if children are not null, add to queue.
		if(root == null) {
	        return;
	    }
	    
	    Queue<BinaryTreeNode<Integer>> pendingChildren = new LinkedList<BinaryTreeNode<Integer>>();
	    pendingChildren.add(root);
	    
	    while(!pendingChildren.isEmpty()) {
	        BinaryTreeNode<Integer> front = pendingChildren.poll();

	        String output = front.data + ":";
	        
	        // Check and append left child info
	        if(front.left != null) {
	            pendingChildren.add(front.left);
	            output += "L:" + front.left.data + ",";
	        } else {
	            output += "L:-1,";
	        }
	        
	        // Check and append right child info
	        if(front.right != null) {
	            pendingChildren.add(front.right);
	            output += "R:" + front.right.data;
	        } else {
	            output += "R:-1";
	        }

	        System.out.println(output);
	    }
	}
	
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
	
	// Build binary tree from pre-order and in-order list.////////////////////////////////
	 static int preIndex = 0;

	    private static int search(int[] inOrder, int start, int end, int value) {
	        for (int i = start; i <= end; i++) {
	            if (inOrder[i] == value) {
	                return i;
	            }
	        }
	        return -1;
	    }

	    private static BinaryTreeNode<Integer> buildTreeHelper(int[] preOrder, int[] inOrder, int inStart, int inEnd) {
	        // Base case: if no elements are remaining in the subarray
	        if (inStart > inEnd) {
	            return null;
	        }

	        // The next element in preOrder is the root of the current subtree
	        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preOrder[preIndex++]);

	        // If the current subtree has no children, return the root
	        if (inStart == inEnd) {
	            return root;
	        }

	        // Find the root in inOrder to split into left and right subtrees
	        int inIndex = search(inOrder, inStart, inEnd, root.data);

	        // Recursively construct the left and right subtrees
	        root.left = buildTreeHelper(preOrder, inOrder, inStart, inIndex - 1);
	        root.right = buildTreeHelper(preOrder, inOrder, inIndex + 1, inEnd);

	        return root;
	    }

	    public static BinaryTreeNode<Integer> buildTree(int[] preOrder, int[] inOrder) {
	        preIndex = 0;  // reset the preIndex
	        return buildTreeHelper(preOrder, inOrder, 0, inOrder.length - 1);
	    }
	 // Build binary tree from pre-order and in-order list.////////////////////////////////
	    
	 // Build binary tree from post-order and in-order list.////////////////////////////////
	    public class Solution {
	        static int postIndex;

	        private static int search(int[] inOrder, int start, int end, int value) {
	            for (int i = start; i <= end; i++) {
	                if (inOrder[i] == value) {
	                    return i;
	                }
	            }
	            return -1;  // should never reach here!
	        }

	        private static BinaryTreeNode<Integer> buildTreeHelper(int[] postOrder, int[] inOrder, int inStart, int inEnd) {
	            // Base case: if no elements are remaining in the subarray
	            if (inStart > inEnd) {
	                return null;
	            }

	            // The next element in postOrder is the root of the current subtree
	            BinaryTreeNode<Integer> root = new BinaryTreeNode<>(postOrder[postIndex--]);

	            // If the current subtree has no children, return the root
	            if (inStart == inEnd) {
	                return root;
	            }

	            // Find the root in inOrder to split into left and right subtrees
	            int inIndex = search(inOrder, inStart, inEnd, root.data);

	            // Recursively construct the right and left subtrees (note the order is reversed compared to preOrder)
	            root.right = buildTreeHelper(postOrder, inOrder, inIndex + 1, inEnd);
	            root.left = buildTreeHelper(postOrder, inOrder, inStart, inIndex - 1);

	            return root;
	        }

	        public static BinaryTreeNode<Integer> buildTree(int[] postOrder, int[] inOrder) {
	            postIndex = postOrder.length - 1;  // initialize postIndex to the last element
	            return buildTreeHelper(postOrder, inOrder, 0, inOrder.length - 1);
	        }
	    }
	 // Build binary tree from post-order and in-order list.////////////////////////////////
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTreeNode<Integer> root = takeInput();
		printPre_orderedTreeDetailed(root);
	}

}

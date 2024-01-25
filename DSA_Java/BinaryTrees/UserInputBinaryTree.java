package BinaryTrees;

import java.util.Scanner;
// working from the root first, then -> left then -> right: is called "Pre-order" because root is being worked first

// working on children elements first, then -> parent then -> back to the root: its called "Post-order".
public class UserInputBinaryTree {
		
	public static BinaryTreeNode<Integer> TakeTreeInput(boolean isRoot, int parentData, boolean isLeft){
		if(isRoot) {
			System.out.println("Enter Root Data");
		}else {
			if(isLeft) {
				System.out.println("Enter left child of " + parentData);				
			}else {
				System.out.println("Enter right child of " + parentData);
			}
		}
		Scanner s = new Scanner(System.in);
		int rootData = s.nextInt();
			
		// use minus one as a dummy for the input to null
		if(rootData == -1) {
			return null;
		}
			
		// create the root with the input taken from the user
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
		BinaryTreeNode<Integer> leftChild = TakeTreeInput(false, rootData, true);
		BinaryTreeNode<Integer> rightChild = TakeTreeInput(false, rootData, false);
		// point the root to their respective children
		root.left = leftChild;
		root.right = rightChild;
		return root;
	}
	
	public static int countNodes(BinaryTreeNode<Integer> root) {
		
		if(root == null) {
			return 0;
		}
		
		int leftNodeCount = countNodes(root.left);
		int rightNodeCount = countNodes(root.right);
		// going to each and every node, and if its not null we are adding 1
		return 1 + leftNodeCount + rightNodeCount;
	}
	
	public static int countNodesLargerThanX(BinaryTreeNode<Integer> root, int X) {
		
		if(root == null) {
			return 0;
		}
		
		int leftNodeCount = countNodesLargerThanX(root.left, X);
		int rightNodeCount = countNodesLargerThanX(root.right, X);
		// going to each and every node, and if its not null we are adding 1
		if(root.data > X) {
			return 1 + leftNodeCount + rightNodeCount;
		}
		return leftNodeCount + rightNodeCount;
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
	
	public static void printTreePost_Ordered(BinaryTreeNode<Integer> root) {
		// base case
		if(root == null) {
			return;
		}
		
		printTreePost_Ordered(root.left);
		printTreePost_Ordered(root.right);
		System.out.print(root.data + " ");
	}
	
	public static int largestNodeValue(BinaryTreeNode<Integer> root) {
		if(root == null) {
			return -1;
		}
		int largestLeft = largestNodeValue(root.left);
		int largestRight = largestNodeValue(root.right);
		return Math.max(root.data, Math.max(largestLeft, largestRight));
		
	}
	
	public static int sumOfNodeData(BinaryTreeNode<Integer> root) {
		
		if(root == null) {
			return 0;
		}
		
		int leftNodeCount = sumOfNodeData(root.left);
		int rightNodeCount = sumOfNodeData(root.right);
		// going to each and every node, and if its not null we are adding 1
		return root.data + leftNodeCount + rightNodeCount;
	}
	
	public static int heightOfTree(BinaryTreeNode<Integer> root) {
		if(root == null) {
			return 0;
		}
		int leftHeight = heightOfTree(root.left);
		int rightHeight = heightOfTree(root.right);
				  
		return Math.max(leftHeight, rightHeight) + 1;
	}
	public static int countLeavesOfTree(BinaryTreeNode<Integer> root) {
		if(root == null) {
			return 0;
		}
		
		if(root.left == null && root.right == null) {
			return 1;
		}
		
		return countLeavesOfTree(root.left) + countLeavesOfTree(root.right);
	}
	
	public static void printNodesAtDepthK(BinaryTreeNode<Integer> root, int k) {
		if(root == null) {
			return;
		}
		
		if(k == 0) {
			System.out.println(root.data);
			return;
		}
		
		printNodesAtDepthK(root.left, k-1);
		printNodesAtDepthK(root.right, k-1);
		
	}
	
	public static void changeToDepthTree(BinaryTreeNode<Integer> root) {
        class Helper {
            void changeDepth(BinaryTreeNode<Integer> node, int depth) {
                if (node == null) {
                    return;
                }

                node.data = depth;

                changeDepth(node.left, depth + 1);
                changeDepth(node.right, depth + 1);
            }
        }
        new Helper().changeDepth(root, 0);
	}
	
	public static boolean isNodePresent(BinaryTreeNode<Integer> root, int x) {
        // if root is null, return false as empty tree doesn't contain any nodes
        if (root == null) {
            return false;
        }

        // if the root node's value is equal to x, return true
        if (root.data.equals(x)) {
            return true;
        }

        // check left and right subtrees
        boolean foundInLeft = isNodePresent(root.left, x);
        boolean foundInRight = isNodePresent(root.right, x);

        // return true if x was found in either subtree
        return foundInLeft || foundInRight;
    }
	
	 public static void printNodesWithoutSibling(BinaryTreeNode<Integer> root) {
	        if (root == null) {
	            return;
	        }

	        // check if the left child has a sibling or not
	        if (root.left != null && root.right == null) {
	            System.out.print(root.left.data + " ");
	        }

	        // check if the right child has a sibling or not
	        if (root.right != null && root.left == null) {
	            System.out.print(root.right.data + " ");
	        }

	        // recursive call to the left child
	        printNodesWithoutSibling(root.left);

	        // recursive call to the right child
	        printNodesWithoutSibling(root.right);
	    }
	 
	 public static BinaryTreeNode<Integer> RemoveLeaves(BinaryTreeNode<Integer> root) {
		 if(root == null) {
			 return null;
		 }
		 if(root.left == null && root.right == null) {
			 return null;
		 }
		 root.left = RemoveLeaves(root.left);
		 root.right = RemoveLeaves(root.right);
		 
		 return root;
		 
	 }
	 
	 public static void mirrorBinaryTree(BinaryTreeNode<Integer> root) {
	        if (root == null) {
	            return;
	        }

	        // Swap the left and the right child of each node
	        BinaryTreeNode<Integer> temp = root.left;
	        root.left = root.right;
	        root.right = temp;

	        // Recur for the left and right subtrees
	        mirrorBinaryTree(root.left);
	        mirrorBinaryTree(root.right);
	    }
//	 Time complexity O(nlogn)
	 public static boolean isTreeBalanced(BinaryTreeNode<Integer> root) {
		 if(root == null) {
			 return true;
		 }
		 int leftHeight = height(root.left);
		 int rightHeight = height(root.right);
		 
		 if(Math.abs(leftHeight - rightHeight) > 1) {
			 return false;
		 }
		 
		 boolean isLeftBalanced = isTreeBalanced(root.left);
		 boolean isRightBalanced = isTreeBalanced(root.right);
		 
		 return isLeftBalanced && isRightBalanced;
		 
	 }
	 
	 // time complexity O(n)
	 public static int height(BinaryTreeNode<Integer> root) {
		 if(root == null) {
			 return 0;
		 }
		 int leftHeight = height(root.left);
		 int rightHeight = height(root.right);
		 return 1 + Math.max(leftHeight, rightHeight);
		 
	 }
	 // this is O(n) time complexity
	 //Improving the time complexity of isTreeBalanced by returning height and is balanced at the same time
	 public static optimizedBalancedTree isBalancedBetter(BinaryTreeNode<Integer> root) {
		 if(root == null) {
			 int height = 0;
			 boolean isBal = true;
			 optimizedBalancedTree ans = new optimizedBalancedTree();
			 ans.height = height;
			 ans.isBalanced = isBal;
			 return ans;
		 }
		 
		 optimizedBalancedTree leftOutpt = isBalancedBetter(root.left);
		 optimizedBalancedTree rightOutput = isBalancedBetter(root.right);
		 boolean isBal = true;
		 int height = 1 + Math.max(leftOutpt.height, rightOutput.height);
		 
		 if(Math.abs(leftOutpt.height - rightOutput.height) >1 ) {
			 isBal = false;
		 }
		 
		 if(!leftOutpt.isBalanced || !rightOutput.isBalanced) {
			 isBal = false;
		 }
		 
		 optimizedBalancedTree ans = new optimizedBalancedTree();
		 ans.height = height;
		 ans.isBalanced = isBal;
		 return ans;
	 } 
	 /// find binary tree diameter/////////////////////////////////////
	 static class Height {
	        int h;
	    }
	 
	 public static int findDiameter(BinaryTreeNode<Integer> root) {
		 Height height = new Height();
	     return findDiameterUtil(root, height);
			  
	 }
	 
	 private static int findDiameterUtil(BinaryTreeNode<Integer> root, Height height) {
	        Height lh = new Height(), rh = new Height();

	        if (root == null) {
	            height.h = 0;
	            return 0; // Diameter is also 0
	        }

	        lh.h++; rh.h++; // Increase height of left and right subtrees

	        // Recursive case: get the diameters of left and right subtrees
	        int ldiameter = findDiameterUtil(root.left, lh);
	        int rdiameter = findDiameterUtil(root.right, rh);

	        // Height of current node is max of heights of left and
	        // right subtrees plus 1
	        height.h = Math.max(lh.h, rh.h) + 1;

	        return Math.max(lh.h + rh.h + 1, Math.max(ldiameter, rdiameter));
	    }
	 
	/// find binary tree diameter/////////////////////////////////////^^^^^^
	 
	 
	 // insert node into BST
	 public static BinaryTreeNode<Integer> insertNodeIntoTree(BinaryTreeNode<Integer> root, int x){
		 // if there is no root node create one and set it equal to x
		 if(root == null) {
			 BinaryTreeNode<Integer> newRoot = new BinaryTreeNode<Integer>(x);
			 return newRoot;
		 }
		 // if root node is present but x is greater than root, x must be inserted on the right side of BST 
		 if(x >= root.data) {
			 BinaryTreeNode<Integer> newRoot = insertNodeIntoTree(root.right, x);
			 root.right = newRoot;
		 }else {
			 // if x is not greater than root.data must insert on the left side of BST
			 BinaryTreeNode<Integer> newRoot = insertNodeIntoTree(root.left, x);
			 root.left = newRoot;
		 }
		 return root;
	 }

	 
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = TakeTreeInput(true, 0, true);
		printTreePost_Ordered(root);
		
		System.out.println("sum of nodes " + sumOfNodeData(root));
		System.out.println(largestNodeValue(root));
		
		
		
	}

}

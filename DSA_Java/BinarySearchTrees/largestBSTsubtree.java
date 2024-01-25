package BinarySearchTrees;

import BinaryTrees.BinaryTreeNode;
// Given a Binary tree, find the largest BST subtree. 
// That is, you need to find the BST with maximum height in the given binary tree. You have to return the height of largest BST.
public class largestBSTsubtree {
	static class BSTInfo {
        boolean isBST;
        int min;
        int max;
        int size; // height of the tree

        public BSTInfo() {
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            isBST = true;
            size = 0;
        }
    }

    public static int largestBSTSubtree(BinaryTreeNode<Integer> root) {
        return largestBSTHelper(root).size;
    }

    public static BSTInfo largestBSTHelper(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return new BSTInfo(); // base case
        }

        BSTInfo leftInfo = largestBSTHelper(root.left);
        BSTInfo rightInfo = largestBSTHelper(root.right);

        BSTInfo current = new BSTInfo();

        // If the subtree rooted at this node is not a BST, then this subtree cannot be a BST
        if (!leftInfo.isBST || !rightInfo.isBST || (root.data <= leftInfo.max) || (root.data > rightInfo.min)) {
            current.isBST = false;
            current.size = Math.max(leftInfo.size, rightInfo.size); // height of the largest BST in the left or right subtree
            return current;
        }

        // If the subtree rooted at this node is a BST
        current.isBST = true;
        current.size = Math.max(leftInfo.size, rightInfo.size) + 1; // height of this subtree
        current.min = root.left != null ? leftInfo.min : root.data;
        current.max = root.right != null ? rightInfo.max : root.data;

        return current;
    }
}

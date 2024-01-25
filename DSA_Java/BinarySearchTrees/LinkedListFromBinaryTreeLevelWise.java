package BinarySearchTrees;

import java.util.*;

public class LinkedListFromBinaryTreeLevelWise {

    // BinaryTreeNode class
    static class BinaryTreeNode<T> {
        T data;
        BinaryTreeNode<T> left;
        BinaryTreeNode<T> right;

        public BinaryTreeNode(T data) {
            this.data = data;
        }
    }

    // LinkedListNode class
    static class LinkedListNode<T> {
        T data;
        LinkedListNode<T> next;

        public LinkedListNode(T data) {
            this.data = data;
        }
    }

    public static ArrayList<LinkedListNode<Integer>> constructLinkedListForEachLevel(BinaryTreeNode<Integer> root) {
        // Initialize an ArrayList to hold the head of the linked lists for each level
        ArrayList<LinkedListNode<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        // Initialize a queue to hold the nodes for level order traversal
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedListNode<Integer> levelHead = null;
            LinkedListNode<Integer> previousNode = null;

            // Iterate through the current level
            for (int i = 0; i < levelSize; i++) {
                BinaryTreeNode<Integer> currentNode = queue.poll();

                // Create a new linked list node for this element
                LinkedListNode<Integer> newNode = new LinkedListNode<>(currentNode.data);

                // Link the previous node to the current node
                if (previousNode != null) {
                    previousNode.next = newNode;
                } else {
                    // If it's the first node of this level, set the head
                    levelHead = newNode;
                }

                // Move to the next linked list node
                previousNode = newNode;

                // Add left and right children to the queue
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

            // Add the head of the linked list for this level to the result
            result.add(levelHead);
        }

        return result;
    }
}

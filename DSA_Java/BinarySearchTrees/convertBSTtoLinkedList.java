package BinarySearchTrees;

import BinaryTrees.BinaryTreeNode;

import linkedLists.LinkedListNodeClass;

public class convertBSTtoLinkedList {

	private static LinkedListNodeClass<Integer> head, tail;

    private static void convertToLinkedList(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }

        convertToLinkedList(root.left);

        LinkedListNodeClass<Integer> newNode = new LinkedListNodeClass<>(root.data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        convertToLinkedList(root.right);
    }
    
    public static LinkedListNodeClass<Integer> constructLinkedList(BinaryTreeNode<Integer> root) {
        head = null;
        tail = null;
        convertToLinkedList(root);
        return head;
    }
    
	public static void main(String[] args) {
		
		
		
	}

}

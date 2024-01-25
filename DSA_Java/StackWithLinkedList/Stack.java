package StackWithLinkedList;
// full implementation of a singly linked list with a stack
public class Stack {
    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void push(int element) {
        Node newNode = new Node(element);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public int pop() {
        if(isEmpty()) {
            return -1; // stack is empty
        }
        int temp = top.data;
        top = top.next;
        size--;
        return temp;
    }

    public int top() {
        if(isEmpty()) {
            return -1; // stack is empty
        }
        return top.data;
    }
}

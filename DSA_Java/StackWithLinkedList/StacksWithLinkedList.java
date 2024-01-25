package StackWithLinkedList;

import Stacks.StackEmptyException;
import linkedLists.LinkedListNodeClass; // imported from previous package

public class StacksWithLinkedList <T> {
	// head of linked list
	private LinkedListNodeClass<T> head;
	// to optimize speed rather than iterating over the linked list to count it for the size() method, just keep a size variable
	private int size;
	
	public StacksWithLinkedList() {
		head = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void push(T elem) {
		// the newly inserted element is the new head
		LinkedListNodeClass<T> p = new LinkedListNodeClass<T>(elem);
		p.next = head;
		head = p;
		size++;
	}
	
	public T top() throws StackEmptyException {
		if(head == null || size == 0) {
			throw new StackEmptyException();
		}
		return head.data;
	}
	
	public T pop() throws StackEmptyException {
		if(head == null) {
			throw new StackEmptyException();
		}
		T temp = head.data;
		head = head.next;
		size--;
		return temp;
		
	}
}

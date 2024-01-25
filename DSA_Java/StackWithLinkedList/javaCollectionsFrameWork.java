package StackWithLinkedList;

import java.util.Stack;
import java.util.Vector;

public class javaCollectionsFrameWork {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(10);
		stack.size();
		System.out.println(stack.size());
		// give the top element
		stack.peek();
		// removes an element
		stack.pop();
		// checks if stack is empty
		stack.isEmpty();
		// travel the elements not the indicies
		int[] arr= {10, 11, 13, 14, 5, 8};
		for(int elem : arr) {
			stack.push(elem);
		}
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
		stack.push(5);
        stack.push(10);
        stack.push(15);
        System.out.print(stack.pop()+stack.size());
	}

}

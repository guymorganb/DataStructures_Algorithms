package Stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseStack {
	 public static void reverseStack(Deque<Integer> input, Deque<Integer> extra) {
	        while (!input.isEmpty()) {
	            extra.push(input.pop());
	        }
	        
	        while (!extra.isEmpty()) {
	            System.out.print(extra.pop() + " ");
	        }
	    }

    public static void main(String[] args) {
    	
    	Deque<Integer> stack1 = new ArrayDeque<>();
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        stack1.push(4);
        Deque<Integer> stack2 = new ArrayDeque<>();

        reverseStack(stack1, stack2); // This will print: 1 2 3 4 
    }
}

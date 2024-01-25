package Queues;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
//In this problem, you need to reverse only the first K elements of the queue. 
//To do this, you can use an auxiliary stack and push the first K elements from 
//the queue onto the stack, effectively reversing their order. 
public class reverseKthElementsInQueue {
	public static Queue<Integer> reverseKElements(Queue<Integer> input, int k) {
		//Your code goes here
		// If the queue is empty or k is greater than the size of the queue, return the same queue
		if (input.isEmpty() || k > input.size())
			return input;

		// If k is 0 or 1, the queue remains unchanged
		if (k <= 0 || k == 1)
			return input;

		// Create a stack and push the first k elements into it
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < k; i++) {
			stack.push(input.poll());
		}

		// Pop the elements from the stack back into the queue
		while (!stack.isEmpty()) {
			input.add(stack.pop());
		}

		// Move the remaining elements from the front of the queue to the rear
		for (int i = 0; i < input.size() - k; i++) {
			input.add(input.poll());
		}

		return input;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

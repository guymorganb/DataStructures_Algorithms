package Queues;
import java.util.Queue;
import java.util.LinkedList;

//The key to solve this problem without using any other data structures is to use recursion. 
//You can dequeue each element from the queue and then recursively call the reverse function. 
//Once you reach the end of the queue, you start enqueuing the elements back to the queue.

public class reverseQueue {
	public static void reverseQueue(Queue<Integer> input) {
		// Base case: If the queue is empty, return
		if (input.isEmpty()) {
			return;
		}
		
		// Dequeue the front element from the queue
		int front = input.poll();
		
		// Recursive call for the remaining elements in the queue
		reverseQueue(input);
		
		// Enqueue the front element back into the queue
		input.add(front);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

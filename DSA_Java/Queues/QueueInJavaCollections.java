package Queues;
import java.util.Queue;
import java.util.LinkedList;
public class QueueInJavaCollections {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Queue<Integer> queue = new Queue<Integer>; You can't do this, because Queue superclass is an interface, Remember:
		
//		and interface is a class with methods which are declared, but only defined within its subclasses.
		
//		to use queue, you must make it a variable referring to the object of the class thats implementing the queue interface, 
//		in this case, Linked List is a class that implements the Queue interface.

		Queue<Integer> queue = new LinkedList<Integer>(); 
		
		queue.add(10);
		queue.add(20);
		System.out.println(queue.size());
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		
		
	}

}

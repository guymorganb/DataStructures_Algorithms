package Queues;


// Queue works as First in First out sequence / unlike stacks, which are last in first out

// Queue elements are added to the rear, and removed from the front. 
public class Queues {

	public static void main(String[] args) {
		
		QueueClass Rs = new QueueClass(3);
		
		int[] arr = {10,20,30,40,45};
		
		for(int elem : arr) {
			Rs.enqueue(elem);
		}
		
		while(! Rs.isEmpty()) {
			try {
				System.out.println(Rs.dequeue());
			} catch (QueueEmptyException e) {
				
				e.printStackTrace();
			}
		}

	}

}

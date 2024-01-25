package Queues;
import linkedLists.LinkedListNodeClass;
public class QueueUsingLinkedList<T>{
	
	private LinkedListNodeClass<T>front;
	private LinkedListNodeClass <T>rear;
	private int size;
	
	public QueueUsingLinkedList() {
		front = null;
		rear = null;
		int size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void enqueue(T elem) {
		LinkedListNodeClass<T> Rs = new LinkedListNodeClass<>(elem);
		size ++;
		if(front == null) {
			front = Rs;
			rear = Rs;
			return;
		}
		rear.next = Rs;
		rear = Rs;
	}
	
	public T front() {
		if(front == null) {
			throw new NullPointerException();
		}
		return front.data;
	}
	
	public T dequeue() throws QueueEmptyException {
		if(front == null) {
			throw new QueueEmptyException();
		}
		T temp = front.data;
		front = front.next;
		if(front == null) {
			rear = null;
		}
		return temp;
	}
	
}

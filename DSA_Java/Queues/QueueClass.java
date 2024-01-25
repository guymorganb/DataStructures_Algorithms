package Queues;

public class QueueClass {
	
	
	// circular queue
	private int[] data;
	// keep index of front and rear
	private int frontIndex;
	private int rearIndex;
	private int size;
	
	public QueueClass(){
		data = new int[5];
		frontIndex = -1;
		rearIndex = -1;
		
	}
	
	public QueueClass(int capacity){
		data = new int[capacity];
		frontIndex = -1;
		rearIndex = -1;
		
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int front() throws QueueEmptyException {
		if(size == 0) {
			throw new QueueEmptyException(); 
		}
		return data[frontIndex];
	}
	
	public void enqueue(int elem)  {
		if(size == data.length) {
			doubleCapacity();
		}
		if(size == 0) {
			frontIndex = 0;
		}
//		rearIndex++;
//		if(rearIndex == data.length) {
//			rearIndex = 0;
//		}
		rearIndex = (rearIndex + 1) % data.length;
		data[rearIndex] = elem;
		size++;
	}
	
	private void doubleCapacity() {
		int[] temp = data;
		data = new int[2* temp.length];
		int index = 0;
		// elements must be copied according to their order because a queue is First in First out (Right side, tail, left side head)
		// copy all elements from frontIndex to end of array:
		for(int i = frontIndex; i < temp.length; i++) {
		// copy at index 0, then keep increasing the index while copying over the array
			data[index++] = temp[i];
		}
		// then copy all elements from start of the array, to frontIndex - 1.
		for(int i = 0; i < frontIndex -1; i++) {
			data[index++] = temp[i];
		}
		frontIndex = 0;
		rearIndex = temp.length - 1;
	}

	public int dequeue() throws QueueEmptyException {
		if(size == 0) {
			throw new QueueEmptyException();
		}
		int temp = data[frontIndex];
		
//		frontIndex++;
//		if(frontIndex == data.length) {
//			frontIndex = 0;
//		}
		
		frontIndex = (frontIndex + 1) % data.length;
		size--;
		if(size == 0) {
			frontIndex = -1;
			rearIndex = -1;
		}
		return temp;
	}
}

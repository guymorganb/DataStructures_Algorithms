package Stacks;

public class Stack {
	// we are storing our stack in an array
	
	private int data[]; // 
	private int topIndex; // holds the top index for pushing elements to the array
	
	
	public Stack(int elem) {
		data = new int[elem];
		topIndex = -1;
	}
//	public Stack() {
//		data = new int[10];
//		topIndex = -1;
//	}
	// O(1)
	public void push(int elem){
		// if stack full
		if(topIndex == data.length-1) {
//			StackFullException e = new StackFullException();
//			//throw e;
//			throw new StackFullException();
			doubleCapacity();
		}
		topIndex ++;
		data[topIndex] = elem;
	}
	private void doubleCapacity() {
		System.out.println("double capacity");
		// create a larger array
		int[] temp = data;
		data = new int[2 * temp.length];
		// copy over the array
		for(int i = 0; i < temp.length; i++) {
			data[i] = temp[i];
		}
	}
	
	// time complexity = O(1)
	public int size() {
		return topIndex +1;
	}
	// O(1)
	public boolean isEmpty() {
		if(topIndex == -1) {
			return true;
		}return false;
	}
	// O(1)
	public int top() throws StackEmptyException{
		if(topIndex == -1) {
			// throw stack empty
			throw new StackEmptyException();
		}
		return data[topIndex];
	}
	// O(1)
	public int pop() throws StackEmptyException {
		// remove from the stack
		if(topIndex == -1) {
			throw new StackEmptyException();
		} 
		int temp = data[topIndex];
		topIndex --;
		return temp;
	}
	
}

package Stacks;

public class StackUse {

	public static void main(String[] args) throws StackFullException, StackEmptyException {
		// write down the functions that you want the software to perform
		
		Stack Newstack = new Stack(2);
		
		int[] arr= {10, 11, 13, 14, 5, 8};
		
		for(int i = 0; i < arr.length; i++) {
			Newstack.push(arr[i]);
		}
		while (!Newstack.isEmpty()) {
			System.out.println(Newstack.pop());
		}
		
//		
//		Newstack.push(10); // add something to the stack
//		Newstack.top(); // read the top element of the stack
//		Newstack.pop(); // remove an element from the stack
//		Newstack.size(); // get the size of the stack
//		Newstack.isEmpty(); // check if the stack is empty
	}

}

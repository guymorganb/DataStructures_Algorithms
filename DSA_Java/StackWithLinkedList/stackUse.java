package StackWithLinkedList;

import Stacks.StackEmptyException;

public class stackUse {

	public static void main(String[] args) throws StackEmptyException {
		
		StacksWithLinkedList<Integer> Newstack = new StacksWithLinkedList<>();
		
		int[] arr = {10, 11, 13, 14, 5, 8};;
		
	
		for(int i = 0; i < arr.length; i++) {
			Newstack.push(arr[i]);
		}
		while (!Newstack.isEmpty()) {
			System.out.println(Newstack.pop());
		}
	}

}

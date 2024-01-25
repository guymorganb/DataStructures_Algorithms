package Hashmaps;

public class mapNode<k,v> {
	// setting up the linked list to be store inside the array list, for separate chaining of hashed values
	k key;
	v value;
	mapNode<k,v> next;
	
	public mapNode(k key, v value) {
		this.key = key;
		this.value = value;
	}
}

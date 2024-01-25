package Hashmaps;

import java.util.ArrayList;

public class hashMap_separate_chaining<k,v> {
	// Internally the hap will maintain a generic array list of nodes, with every element of the array stored in a linked list
	// of all the hashed key/values from the hashmap
	
	ArrayList<mapNode<k,v>> buckets;
	int count; // how many elements are present in hashmap
	int numBuckets;  
	
//	Constructor
	public hashMap_separate_chaining() {
		buckets = new ArrayList<>();
		numBuckets = 5;
		for(int i = 0; i< numBuckets; i++) {  // sets initial size of array list to 20
			buckets.add(null);  // sets all the spaces in the arrayList to null at start
		}
	}
//	hash the key and get the index from it through compression
	private int getBucketIndex(k key) {
		int hashCode = key.hashCode(); // hashed the key
		int index = hashCode % numBuckets;	// compresses the index where we will insert the hashed key, % numBuckets keep the index between 0-19(for size 20)
		return index;
	}
//  The size of arraylist should maintain a load factor of less than 0.7 meaning the number of 
//	Elements to insert should always be kept to 70% of the total number of buckets so for 100 elements to keep a load factor of 0.7
//	there should be 120-130 buckets, this keeps the time complexity manageable
//  Rehashing is the process of increasing the number of available buckets
	public void insert(k key, v value) {
		int bucketIndex = getBucketIndex(key); // gets the hashed & compressed value which is the index where it will be inserted in the array list
		mapNode<k, v> head = buckets.get(bucketIndex); // gets the head of a node already inside the index
		// if element is already there just update its value
		while(head != null) {
			if(head.key.equals(key)) {	// we are comparing if the key is already present in the linked list
				head.value = value;	// if the value is present than we will update the value
				return;
			}
			head = head.next;	// set the next item of the linked list to equal head, and run the check again
		}
		// element is not present, insert the new element at 0th position of linked list
		head = buckets.get(bucketIndex);	// 
		mapNode<k, v> newNode = new mapNode<>(key, value); // making a new node which needs to be inserted
		newNode.next = head;
		buckets.set(bucketIndex, newNode); // set the new element inside the arrayList at the index
		count++;
		double loadFactor = (1.0 * count)/numBuckets;
		if(loadFactor > 0.7) {
			reHash();
		}
	}
	// return the load factor
	public double loadFactor() {
		return (1.0*count)/numBuckets;
	}
	
//  re hash when the loadFactor >= 0.7, all item must be rehashed, they can just be copied over because the arrayList size has changed
	private void reHash() {
		ArrayList<mapNode<k,v>> temp = buckets;
		buckets = new ArrayList<>();
		// double the size of the arrayList
		for(int i = 0; i < (2 * numBuckets); i++) {
			buckets.add(null);
		}
		
		count = 0;
		numBuckets = numBuckets * 2;
		
		for(int i = 0; i < temp.size(); i++) {
			mapNode<k, v> head = temp.get(i);
			// go to each element of the linked list and re-insert it into the new arrayList, and re-has all keys and reset all values
			while(head != null) {
				k key = head.key;
				v value = head.value;
				insert(key, value);
				head = head.next;
			}
		}
	}
	// search if key is present at the index, in the linked list, by the hashed value.
	public Boolean search(k key) {
		int bucketIndex = getBucketIndex(key);
		mapNode<k, v> head = buckets.get(bucketIndex);
		while(head != null) {
			if(head.key.equals(key)) {
				return true;
			}
			head = head.next; // set the head equal to the next item in the linked list
		}
		return false;
	}
	
	// remove a key
	public v removeKey(k key) {
		int bucketIndex = getBucketIndex(key);
		mapNode<k, v> head = buckets.get(bucketIndex);
		// maintain a previous node
		mapNode<k, v> prev = null;
		while(head != null) {
			if(head.key.equals(key)) {	// we are comparing if the key is already present in the linked list
				if(prev != null) {
					prev.next = head.next;
				}else {
					// if the first element in the linked list equals the key, remove it by setting the second element in the linked list as the head
					buckets.set(bucketIndex, head.next);
				}
				count--;
				return head.value;
			}
			// set prev to head so we maintain the previous node behind the head
			prev = head;
			head = head.next;	// set the next item of the linked list to equal head, and run the check again
		}
		return null;
	}
	
	// search the linked list at the index, and get the value for that key and return it
	public v getValue(k key) {
		int bucketIndex = getBucketIndex(key);
		mapNode<k, v> head = buckets.get(bucketIndex);
		
		while(head != null) {
			if(head.key.equals(key)) {	// we are comparing if the key is already present in the linked list
				return head.value;
			}
			head = head.next;	// set the next item of the linked list to equal head, and run the check again
		}
		return null;
	}
	// get the size
	public int size() {
		return count;
	}
	
	
	
}

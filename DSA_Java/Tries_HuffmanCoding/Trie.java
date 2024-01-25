package Tries_HuffmanCoding;


class TrieNode{
	
    char data;
    boolean isTerminalNode; // Terminal node is where the word ends, signifying it is part of the collection
    TrieNode[] children; // Each node at the top level will have an array of 26 elements corresponding to the letters that are its children
	int childCount;

    // Assuming only lowercase English letters
    private static final int ALPHABET_SIZE = 26;

    public TrieNode(char c) {
        this.data = c;
        this.isTerminalNode = false;
         // this array hold the references to the beginning letter of the children
        this.children = new TrieNode[ALPHABET_SIZE];
        this.childCount = 0;
    }
}

public class Trie {

	private TrieNode root;	// declare the root node as a TrieNode
	
	public Trie() {
		// initialize a root node with null character
		root = new TrieNode('\0');
	}
	
	// Recursively insert
	private void insertHelper(TrieNode root, String word) {
		// base case
		if(word.length() == 0) {
			root.isTerminalNode = true;
			return;
		}
		// first index of the first character,
		// we will input the first index into the nodes children array
		// we subtract "A" from the first letter and it will return the index, ex. A - A = 0; A - B = 1 
		//....this allows us to check that index of the array
		int childIndex = word.charAt(0) - 'a';
		// set the node to so we can check if it exists
		TrieNode child = root.children[childIndex]; 
		if(child == null) {
			// no child node in the array create a child node with the first character
			child = new TrieNode(word.charAt(0));
			// insert node into the child array
			root.children[childIndex] = child;
			root.childCount ++;
		}
		// recursive call, sets the root to the next node.
		insertHelper(child, word.substring(1));
	}
	
	public void insert(String word) {
		insertHelper(root, word);
	}
	
	private boolean searchHelper(TrieNode root, String word) {
		if(word.length() == 0) {
			return root.isTerminalNode;
		}	
		
			// get index of first char
		int childIndex = word.charAt(0) - 'a';
			// create a trie node of the childIndex and set it as the root
		TrieNode child = root.children[childIndex];
			// check if the node is located in the children array
		
		if(child != null) {
			return searchHelper(child, word.substring(1));	
		}
			return false;
	}

	public boolean search(String word) {
		return searchHelper(root, word);
	}
	
	public void removeHelper(TrieNode root, String word) {
		
		if(word.length() == 0) {
			root.isTerminalNode = false;
			return; 
		}
		
		// get the child index based off the letters ascii value
		int childIndex = word.charAt(0) - 'a';
		// sets the new root to be passed recursively
		TrieNode child = root.children[childIndex];
		
		// checks if null to ensure the letter exists in the array
		if(child == null) {
			return;
		}
		
		 removeHelper(child, word.substring(1));
		 if(!child.isTerminalNode && child.childCount == 0) {
			 root.children[childIndex] = null;
			 root.childCount --;
		 }
	}
	
	public void remove(String word) {
		remove(word);
	}
	
	public int countWordsHelper(TrieNode root) {
		
		if(root == null) {
			return 0;
		}
		
		int wordCount = 0;
		
		// if this node is a terminal node it represents a word
		if(root.isTerminalNode) {
			wordCount++;
		}
		
		// loop through the children
		for(int i = 0; i < root.children.length; i++) {
			if(root.children[i] != null) {
				wordCount += countWordsHelper(root.children[i]); 
			}
		}
		
		return wordCount;
	}
	
	public int countWords() {
		return countWordsHelper(root);
	}
	
}

package Tries_HuffmanCoding;
import java.util.ArrayList;

public class auto_complete {

	class TrieNode {
	    char data;
	    boolean isTerminating;
	    TrieNode children[];

	    public TrieNode(char data) {
	        this.data = data;
	        isTerminating = false;
	        children = new TrieNode[26];
	    }
	}

	public class Trie {
	    private TrieNode root;

	    public Trie() {
	        root = new TrieNode('\0');
	    }

	    private void add(TrieNode root, String word) {
	        if (word.length() == 0) {
	            root.isTerminating = true;
	            return;
	        }
	        int childIndex = word.charAt(0) - 'a';
	        TrieNode child = root.children[childIndex];
	        if (child == null) {
	            child = new TrieNode(word.charAt(0));
	            root.children[childIndex] = child;
	        }
	        add(child, word.substring(1));
	    }

	    public void add(String word) {
	        add(root, word);
	    }

	    private void autoCompleteDFS(TrieNode node, String current, ArrayList<String> results) {
	        if (node.isTerminating) {
	            results.add(current);
	        }

	        for (int i = 0; i < 26; i++) {
	            TrieNode child = node.children[i];
	            if (child != null) {
	                autoCompleteDFS(child, current + child.data, results);
	            }
	        }
	    }

	    public void autoComplete(ArrayList<String> input, String word) {
	        // Adding all the words to the Trie
	        for (String w : input) {
	            add(w);
	        }

	        // Navigate through Trie to find last node of incomplete word
	        TrieNode temp = root;
	        for (int i = 0; i < word.length(); i++) {
	            int index = word.charAt(i) - 'a';
	            if (temp.children[index] == null) {
	                return;  // Word is not present as a prefix
	            }
	            temp = temp.children[index];
	        }

	        // Perform DFS from the last node of the incomplete word
	        ArrayList<String> results = new ArrayList<>();
	        autoCompleteDFS(temp, word, results);

	        // Print the results
	        for (String w : results) {
	            System.out.println(w);
	        }
	    }

	    public static void main(String[] args) {
	        Trie trie = new auto_complete().new Trie();;
	        ArrayList<String> words = new ArrayList<>();
	        words.add("dog");
	        words.add("dove");
	        words.add("duck");
	        trie.autoComplete(words, "du");  // Should print "dove" and "duck"
	    }
	}
	
}


package Tries_HuffmanCoding;

public class TriesUse {

	public static void main(String[] args) {
		
		Trie t = new Trie();
		
		t.insert("notes");
		
		t.insert("and");
		
		System.out.println(t.search("and"));
		
		System.out.println(t.search("bet"));
	}

}

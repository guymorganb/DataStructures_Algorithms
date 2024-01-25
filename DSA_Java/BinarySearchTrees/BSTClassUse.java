package BinarySearchTrees;

public class BSTClassUse {

	public static void main(String[] args) {
		
		BSTClass tree = new BSTClass();
		
		tree.insert(5);
		tree.insert(2);
		tree.insert(7);
		tree.insert(1);
		tree.insert(3);
		tree.insert(6);
		tree.insert(8);
		
		tree.printTree();
		System.out.println(tree.height());
		System.out.println();
		
		System.out.println(tree.isPresent(5));
		tree.deleteData(5);	// removes node and replaces it so tree stays BST
		System.out.println(tree.isPresent(5));
		tree.printTree();
		System.out.println();
		
		tree.deleteData(2);
		tree.printTree();
		System.out.println();
		tree.deleteData(6);
		tree.deleteData(1);
		tree.printTree();
		//System.out.println(tree.size());
		System.out.println(tree.height());
	}

}

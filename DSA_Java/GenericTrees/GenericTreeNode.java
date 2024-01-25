package GenericTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class GenericTreeNode<T> {
	// generic trees at the root contain an Array List that holds reference to its children
	T data;
	ArrayList<GenericTreeNode<T>> children;
	
    public GenericTreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public GenericTreeNode() {
        this.data = null;
        this.children = new ArrayList<>();
    }

    
    public void setData(T data) {
        this.data = data;
    }
	
	///////////////////print pre order generic tree///////////////////////////////
	// print in pre-order not the best way because we don't know who the children belong to
	public void printTree(GenericTreeNode<T> root) {
		// special case, this is not a base case, base case is reached by the for loop, because it will not run if a child doesn't exist
		if(root == null) {
			return;
		}
		System.out.println(root.data);
		// iterate over the array list references and print them, then call for the children, if they exist they will be printed
		for(int i = 0; i < root.children.size(); i++) {
			GenericTreeNode<T> child = root.children.get(i);
			printTree(child);
		}
		
	}
	///////////////////print tree better///////////////////
	public void printTreeBetter(GenericTreeNode<T> root) {
		// special case is main root == null
		if(root == null) {
			return;
		}
		System.out.print(root.data + ": ");
		for( int i = 0; i < root.children.size(); i++) {
			System.out.print(root.children.get(i).data + " ");
		}
		System.out.println();
		for(int i = 0; i < root.children.size(); i++) {
			GenericTreeNode<T> child = root.children.get(i);
			printTreeBetter(child);
		}
	}
	
	///////////////////////take input////////////////////////
	// put input into a queue
	public void takeInputLevelWise(GenericTreeNode<T> root) {
		
	}
	///////////////////////count number of nodes///////////////////
	public int countNodes(GenericTreeNode<T> root) {
		int count = 1;
		for(int i = 0; i < root.children.size(); i++) {
			int childCount = countNodes(root.children.get(i));
			count += childCount;
		}
		return count;
	}
	
	//////////////////////sum of nodes////////////////////////////
	public int sumOfNodes(GenericTreeNode<T> root) {
		int data = (int)root.data;
		for(int i = 0; i < root.children.size(); i++) {
			int childData = sumOfNodes(root.children.get(i));
			data += childData;
		}
		return data;
	}
	//////////////////////////take input level wise needs fixing//////////////////
	
	public GenericTreeNode<Integer> inputLevelWise() {
	    Scanner s = new Scanner(System.in);
	    Queue<GenericTreeNode<Integer>> pendingNodes = new LinkedList<>();
	    System.out.println("Enter root data: ");
	    int rootData = s.nextInt();
	    if (rootData == -1) {
	        return null;
	    }
	    GenericTreeNode<Integer> root = new GenericTreeNode<>(rootData);
	    pendingNodes.add(root);

	    while (!pendingNodes.isEmpty()) {
	        GenericTreeNode<Integer> currentNode = pendingNodes.remove();
	        System.out.println("Enter the number of children for: " + currentNode.data);
	        int numChildren = s.nextInt();
	        // take input children
	        for (int i = 1; i <= numChildren; i++) {
	            System.out.println("Enter the " + i + "th child data for: " + currentNode.data);
	            int childData = s.nextInt();
	            GenericTreeNode<Integer> childNode = new GenericTreeNode<>(childData);
	            currentNode.children.add(childNode);
	            pendingNodes.add(childNode);
	        }
	    }
	    return root;
	}
}

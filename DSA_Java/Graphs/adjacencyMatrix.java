package Graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class adjacencyMatrix {
	
	
	
	
	public static void bfTraversal(int adjMatrix[][]){
		// use a queue to insert vertices
		Queue<Integer> pendingVertices = new LinkedList<>(); 
		boolean visited[] = new boolean[adjMatrix. length]; 
		visited[0] = true; 
		pendingVertices.add(0);
		
		// keep traveling until queue is empty
		while(! pendingVertices.isEmpty()){
			int currentVertex = pendingVertices.poll(); 
			System.out.print(currentVertex +" "); 
			
			for(int i = 0; i < adjMatrix.length; i++){ 
				
				if(adjMatrix[currentVertex][i] == 1 && !visited[i]){
				// i is unvisited neighbor of currentVertex 
				pendingVertices.add(i); 
				// mark it visited
				visited[i] = true;
				}
			}
		}
	}
	
	
	public static void depthFirstTraversal(int[][] graph, int currentVertex, boolean[] visited) {
	    // keep track of nodes visited
		visited[currentVertex] = true;
		System.out.println(currentVertex + " ");
	    // iterate through the row
	    for (int i = 0; i < graph.length; i++) {
	        if (graph[currentVertex][i] == 1 && visited[i] == false) {
	            depthFirstTraversal(graph, i, visited);
	        }
	    }
	}
	
	public static void depthFirstTraversalHelper(int[][] graph, int currentVertex) {
		// keep an array of visited nodes
		boolean[] visited = new boolean[graph.length];
		
		depthFirstTraversal(graph, currentVertex, visited);
	}
	
	public static void main(String args[]) {
		// take input for a graph
		Scanner s = new Scanner(System.in);
		
		// take input for number of edges and number of verticies
		System.out.println("Enter number of verticies and edges for graph");
		int vertices = s.nextInt();
		int edges = s.nextInt();
		
		// meake new matrix
		int[][] adjMatrix = new int[vertices][vertices];
		
		// take input for adjacent nodes
		System.out.println("Enter connected verticies for graph");
		for(int i = 0; i < edges; i++) {
			int v1 = s.nextInt();
			int v2 = s.nextInt();
			adjMatrix[v1][v2] = 1;
			adjMatrix[v2][v1] = 1;
		}
		depthFirstTraversalHelper(adjMatrix, 0);
		// any index which contains a 1 means those nodes are connected.
//		for(int i = 0; i < vertices; i++) {
//			for(int j = 0; j < vertices; j++) {
//				System.out.print(adjMatrix[i][j] + " ");
//			}
//			System.out.println();
//		}
		s.close();
		
		
	}

}

package Graphs;
import java.util.Scanner;

public class DijkstrasAlgorithm {

    /**
     * This function returns the vertex with the minimum distance value
     * from the set of vertices not yet included in the shortest path tree.
     * 
     * @param visited - an array that tracks which vertices have been visited.
     * @param distance - an array that holds the shortest distance from source to each vertex.
     * @return the vertex with the shortest distance.
     */
    private static int findMinVertex(boolean[] visited, int[] distance) {
        int minVertex = -1; 

        for(int i = 0; i < visited.length; i++) { 
            if(!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }

    /**
     * Implements Dijkstra's algorithm to compute the shortest path from a source
     * vertex to every other vertex in a given weighted graph.
     * 
     * @param adjMatrix - the weighted graph represented as an adjacency matrix.
     */
    public static void dijkstra(int adjMatrix[][]) {
        int n = adjMatrix.length; 
        boolean visited[] = new boolean[n]; 
        int distance[] = new int[n]; 

        // Initializing distance of the source vertex to itself as 0.
        distance[0] = 0; 
        
        // Initializing all other distances as INFINITE.
        for(int i = 1; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < n-1; i++) {
            int minVertex = findMinVertex(visited, distance); 
            visited[minVertex] = true; 
            
            for(int j = 0; j < n; j++) { 
                // Check for an edge from minVertex to j, and if j has not been visited.
                if(adjMatrix[minVertex][j] > 0 && !visited[j] && adjMatrix[minVertex][j] < Integer.MAX_VALUE) {
                    // Update the shortest path for vertex j, if there is a shorter path via minVertex.
                    int newDist = distance[minVertex] + adjMatrix[minVertex][j]; 
                    if(newDist < distance[j]) {
                        distance[j] = newDist;
                    }
                }
            }
        }

        // Print the shortest path values for all vertices from the source.
        for(int i = 0; i < n; i++) {
            System.out.println(i + " " + distance[i]);
        }
    }

    /**
     * The main method to execute Dijkstra's algorithm. 
     * Reads input for vertices, edges and weights and then computes the shortest paths.
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in); 

        // Read number of vertices and edges.
        int n = s.nextInt(); 
        int e = s.nextInt(); 
        int adjMatrix[][] = new int[n][n]; 

        // Populate the adjacency matrix with edge weights.
        for(int i = 0; i < e; i++) {
            int v1 = s.nextInt(); 
            int v2 = s.nextInt(); 
            int weight = s.nextInt(); 
            adjMatrix[v1][v2] = weight; 
            adjMatrix[v2][v1] = weight; // As it's an undirected graph
        }

        // Execute Dijkstra's algorithm.
        dijkstra(adjMatrix); 
        s.close();
    }
}

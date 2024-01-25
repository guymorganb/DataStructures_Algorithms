package Graphs;

import java.util.Scanner;

public class PrimsAlgorithm {

    // This function finds the vertex with the minimum weight which has not been visited.
    private static int findMinVertex(boolean[] visited, int[] weight) {
        int minVertex = -1;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && (minVertex == -1 || weight[i] < weight[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }

    // Prim's algorithm to find Minimum Spanning Tree
    public static void prims(int adjMatrix[][]) {
        int n = adjMatrix.length;
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        int[] weight = new int[n];

        parent[0] = -1;  // Setting the source vertex as 0
        weight[0] = 0;
        for (int i = 1; i < n; i++) {
            weight[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < n; i++) {
            int minVertex = findMinVertex(visited, weight);
            visited[minVertex] = true;

            // Explore neighbors of min vertex and update values
            for (int j = 0; j < n; j++) {
                if (adjMatrix[minVertex][j] != 0 && !visited[j] && weight[j] > adjMatrix[minVertex][j]) {
                    weight[j] = adjMatrix[minVertex][j];
                    parent[j] = minVertex;
                }
            }
        }

        // Print the Minimum Spanning Tree
        for (int i = 1; i < n; i++) {
            if (i < parent[i]) {
                System.out.println(i + " " + parent[i] + " " + weight[i]);
            } else {
                System.out.println(parent[i] + " " + i + " " + weight[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int e = s.nextInt();
        int adjMatrix[][] = new int[n][n];

        // Input the edges and their weights
        for (int i = 0; i < e; i++) {
            int v1 = s.nextInt();
            int v2 = s.nextInt();
            int weight = s.nextInt();
            adjMatrix[v1][v2] = weight;
            adjMatrix[v2][v1] = weight;
        }
        prims(adjMatrix);  // Execute Prim's algorithm
        s.close();
    }
}
/* Why do we need to find the minimum spanning tree? 
 * 
A minimum spanning tree (MST) is a subset of the edges of a connected, edge-weighted undirected graph that connects all the vertices together, without any cycles and with the minimum possible total edge weight. Finding the minimum spanning tree has several practical applications:

Network Design: One of the most popular applications of MST is in designing networks, including computer networks, telecommunication networks, and road networks. Given a network with cities as vertices and roads (with associated costs) as edges, MST can help design the minimum-cost network that connects all cities.

Cluster Analysis: In clustering algorithms, the concept of MST can be used to group similar items together, based on some distance measure.

Handwriting Recognition: MST has been used in algorithms to recognize handwritten characters in optical character recognition systems.

Image Segmentation: MST can be applied to segment images in computer vision problems.

Approximation Algorithms: MST serves as a subroutine in various approximation algorithms for NP-hard problems.

Reducing Graph Complexity: If you're working with a dense graph (where many edges exist), but you only need a simplified version of the graph that preserves connectivity, then an MST can be a good choice.

Power Grids: In designing power grids, MST can help ensure that all houses receive electricity while minimizing the total length of wire used.

Pipeline Distribution: In cases where you need to lay pipes to distribute gas/water to various locations, MST can help find the layout with the minimum total length.

Conceptually, anytime you have a scenario where you need to connect a set of points (be it cities, computers, or other entities) in the most economical way (minimizing cost, distance, time, or other measures), the minimum spanning tree becomes relevant.
 * 
 */

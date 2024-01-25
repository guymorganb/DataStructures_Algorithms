package Graphs;

import java.util.Arrays;
import java.util.Scanner;

class Edge implements Comparable<Edge> {
    int v1; // starting vertex of the edge
    int v2; // ending vertex of the edge
    int weight; // weight of the edge

    Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    // compareTo method to help sort edges based on weight
    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}
//In the context of graph algorithms like Kruskal's, the weight of an edge typically represents the cost or distance between two vertices.
// overall time complexity = ElogE + EV
// the best algorithm for this would be Union Find with Rank & Path Compression
// Algorithms for Minimum Spanning Tree (Tree with minimum weights)
public class KruskalAlgorithm {

    // Utility function to find parent of a vertex in a disjoint set
    private static int findParent(int v, int parent[]) {
        if (v == parent[v]) {
            return v;
        }
        return findParent(parent[v], parent);
    }

    public static Edge[] kruskalAlgorithm(Edge[] edges, int n) {
        // Sort the edges based on weight
        Arrays.sort(edges);

        // MST will have at max 'n-1' edges where 'n' is number of vertices
        Edge mst[] = new Edge[n - 1];

        // To keep track of parent of every vertex
        int parent[] = new int[n];
        for (int j = 0; j < n; j++) {
            parent[j] = j;
        }

        int count = 0, i = 0;

        // Process each edge till we have n-1 edges in our MST
        while (count != n - 1) {
            Edge currentEdge = edges[i++];

            // Finding the topmost parent (representative) of the set
            int v1Parent = findParent(currentEdge.v1, parent);
            int v2Parent = findParent(currentEdge.v2, parent);

            // If the parents are different, then this edge won't form a cycle
            if (v1Parent != v2Parent) {
                mst[count] = currentEdge;
                count++;

                // Make parent of v1Parent as v2Parent
                parent[v1Parent] = v2Parent;
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt(); // Number of vertices
        int e = s.nextInt(); // Number of edges

        Edge edges[] = new Edge[e];

        // Input each edge
        for (int i = 0; i < e; i++) {
            int v1 = s.nextInt();
            int v2 = s.nextInt();
            int weight = s.nextInt();

            edges[i] = new Edge(v1, v2, weight);
        }

        // Get the MST using Kruskal's algorithm
        Edge mst[] = kruskalAlgorithm(edges, n);

        // Print the MST
        for (int i = 0; i < mst.length; i++) {
            if (mst[i].v1 < mst[i].v2) {
                System.out.println(mst[i].v1 + " " + mst[i].v2 + " " + mst[i].weight);
            } else {
                System.out.println(mst[i].v2 + " " + mst[i].v1 + " " + mst[i].weight);
            }
        }

        s.close();
    }
}

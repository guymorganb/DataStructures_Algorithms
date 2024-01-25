package Graphs;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class CheckPath {
	public static boolean checkPath(int[][] graph, int v1, int v2) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        
        queue.add(v1);
        visited[v1] = true;
        
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            if (currentVertex == v2) {
                return true; // Found the destination vertex v2
            }
            for (int i = 0; i < graph.length; i++) {
                if (graph[currentVertex][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
        return false; // Destination vertex v2 not reached
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");
        int V = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        
        int[][] graph = new int[V][V];
        for (int i = 0; i < E; i++) {
            String[] edgeVertices = br.readLine().trim().split(" ");
            int a = Integer.parseInt(edgeVertices[0]);
            int b = Integer.parseInt(edgeVertices[1]);
            graph[a][b] = 1;
            graph[b][a] = 1; // Because it's an undirected graph
        }
        
        String[] vertices = br.readLine().trim().split(" ");
        int v1 = Integer.parseInt(vertices[0]);
        int v2 = Integer.parseInt(vertices[1]);
        
        System.out.println(checkPath(graph, v1, v2));
    }
}


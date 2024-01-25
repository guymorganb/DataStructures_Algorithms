package Graphs;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
// find path from start to end points
public class GetPathDepthFirst {
	// depth first path
	 public static boolean dfs(int[][] graph, int v1, int v2, boolean[] visited, ArrayList<Integer> path) {
	        if(v1 == v2) {
	            path.add(v2);
	            return true;
	        }
	        
	        visited[v1] = true;
	        path.add(v1);
	        
	        for(int i = 0; i < graph.length; i++) {
	            if(graph[v1][i] == 1 && !visited[i]) {
	                boolean found = dfs(graph, i, v2, visited, path);
	                if(found) return true;  // if path found, terminate early
	            }
	        }
	        
	        path.remove(path.size() - 1); // remove current vertex if path is not found
	        return false;
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
	        
	        boolean[] visited = new boolean[V];
	        ArrayList<Integer> path = new ArrayList<>();
	        
	        boolean found = dfs(graph, v1, v2, visited, path);
	        if(found) {
	            for(int i = path.size() - 1; i >= 0; i--) {
	                System.out.print(path.get(i) + " ");
	            }
	        }
	    }
}

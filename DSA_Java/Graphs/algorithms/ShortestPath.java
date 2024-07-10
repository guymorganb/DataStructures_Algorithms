package algorithms;
import util.*;
import java.util.*;
public class ShortestPath {
    public static void bellman_ford(WeightedGraph weightedGraph,
                                    String sourceVertex) throws Exception{
        weightedGraph.resetForShortestPathProblem();
        Vertex source = weightedGraph.getVertex(sourceVertex);
        source.rank = 0;//rank means distance from source
        for(int rep = 0; rep < weightedGraph.vertexCount; rep++)
            for(Vertex from: weightedGraph.vertexSet())
                for(Vertex to: weightedGraph.neighborSet(from)) {
                    int weight = weightedGraph.getWeight(from.name, to.name);
                    if(from.rank != Integer.MAX_VALUE && to.rank > from.rank + weight){//relaxing edge (from, to)
                        if(rep == weightedGraph.vertexCount - 1) {
                            Exception negativeCycle = new Exception("Bellmanford Error: Can't solve the shortest " +
                                    "path problem as the graph has negative cycle!");
                            throw negativeCycle;
                        }
                        to.rank = from.rank + weight;
                        to.pred = from;
                    }
                }
    }
    public static void dijkstra(WeightedGraph weightedGraph,
                                String sourceVertex) throws Exception{
        weightedGraph.resetForShortestPathProblem();
        Vertex source = weightedGraph.getVertex(sourceVertex);
        source.rank = 0;
        Queue<Vertex> q = new PriorityQueue<Vertex>();//min-heap!
        q.add(source);
        int time = 0;
        while (!q.isEmpty()) {
            Vertex current = q.remove();
            current.discovered = ++time;
            for (Vertex neighbor : weightedGraph.neighborSet(current)) {
                if(neighbor.discovered > 0)//the path from source to neighbor is already known. No need to process again
                    continue;
                int distanceFromNeighbor = weightedGraph.getWeight(current.name, neighbor.name);
                if(distanceFromNeighbor < 0)//negative distance
                    throw new Exception("Error: Dijkstra's algorithm cannot handle negative weights. use BellmanFord!");
                if (neighbor.rank > current.rank + distanceFromNeighbor) {//relaxing the edge (current, neighbor)
                    neighbor.rank = current.rank + distanceFromNeighbor;
                    neighbor.pred = current;
                    q.add(neighbor);
                }
            }
        }
    }

    public static void bfs(Graph graph, String sourceVertex) {
        graph.resetForShortestPathProblem();
        Vertex source = graph.getVertex(sourceVertex);
        int time = 0;
        source.rank = 0;
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(source);
        source.discovered = ++time;
        while (!q.isEmpty()) {
            Vertex current = q.remove();
            for (Vertex neighbor : graph.neighborSet(current))
                if (neighbor.discovered == 0) {//not discovered yet
                    neighbor.rank = current.rank + 1;
                    neighbor.pred = current;
                    q.add(neighbor);
                    neighbor.discovered = ++time;
                }
        }
    }
    public static void printShortestPathSolution(String sourceVertex, Graph graph) {
        Vertex source = graph.getVertex(sourceVertex);
        System.out.println("Source is " + source);
        for (Vertex v : graph.vertexSet()) {
            if (v.equals(source))
                continue;
            System.out.println("Shortest distance/path from " + source + " to "
                    + v + ": " + v.rank + findPath(sourceVertex, v.name, graph));
        }
    }
    public static List<Vertex> findPath(String source, String target, Graph graph){
        LinkedList<Vertex> rv = new LinkedList<>();
        helper(graph.getVertex(source), graph.getVertex(target), rv);
        return rv;
    }
    private static void helper(Vertex source, Vertex target, LinkedList<Vertex> path) {

        if (source.equals(target)) {
            path.addFirst(source);
            return;
        }
        if (target.rank == Integer.MAX_VALUE) {
            path.clear();//path doesn't exist
            return;
        }
        path.addFirst(target);
        helper(source, target.pred, path);
    }
    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(
                new String[]{//vertices
                        "v1", "v2", "v3", "v4", "v5", "v6", "v7"
                }, new String[]{//edges
                "v3,v1,4", "v3,v6,5", "v1,v2,2", "v1,v4,1",
                "v2,v5,10", "v2,v4,3", "v5,v7,6", "v7,v6,1",
                "v4,v5,2", "v4,v7,4", "v4,v6,8", "v4,v3,2"
        }
        );
        System.out.println("Running BFS on an unweighted graph with 7 vertices\n");
        bfs(graph, "v3");
        printShortestPathSolution("v3", graph);//say source is v3
        System.out.println("\nRunning Dijkstra's on a weighted graph with 7 vertices\n");
        try{
            dijkstra(graph, "v1");
            printShortestPathSolution("v1", graph);//say source is v1
        }catch (Exception exp){
            System.out.println(exp.getMessage());
        }
        System.out.println("\nChanging the weight of edge (v7, v6) from 1 to -4 and (v3, v6) from 5 to 1...\n");
        graph.setWeight("v7", "v6", -4);
        graph.setWeight("v3", "v6", 1);
        System.out.println("\nRunning Bellman-Ford's on the same graph after change...\n");
        try{
            bellman_ford(graph, "v1");
            printShortestPathSolution("v1", graph);//say source is v1
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nChanging the weight of edge (v4, v3) from 2 to -6 to create a negative cycle:" +
                " v1 -> v4 -> v3 -> v1\n");
        graph.setWeight("v4", "v3", -6);
        System.out.println("\nRunning Bellman-Ford's on the same graph after change...\n");
        try{
            bellman_ford(graph, "v1");
            printShortestPathSolution("v1", graph);//say source is v1
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

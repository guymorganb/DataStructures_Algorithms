import java.util.*;

public class ShortestPath {
    ////////////////Setup the vertex class
    public static class Vertex implements Comparable<Vertex> {
        public String name;
        public int indegree;
        public int rank;//used to compare two vertices. Can be topological number, distance from source, etc
        public int discovered;//discovery time in DFS... Is always positive
        public int finished;//finish time in DFS... Is always positive
        public Vertex pred;

        @Override
        public int hashCode() {
            return Objects.hash(name);

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            // Two vertices are equal if they have the same name
            return Objects.equals(name, vertex.name);
        }

        @Override
        public String toString() {
            return name;
        }

        public Vertex(String name) {
            this.name = name;
            this.indegree = 0;
            this.discovered = 0;
            this.finished = 0;
            this.pred = null;
            this.rank = 0;
        }

        public int compareTo(Vertex another) {
            if (another == null) {
                return 1; // Consider this vertex greater than null
            }
            return Integer.compare(this.rank, another.rank);
        }
    }
    ////////////////Setup the Edge class
    public static class Edge implements Comparable<Edge>{
        public Vertex from, to;
        int weight;
        public Edge(Vertex from, Vertex to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
        @Override
        public String toString() {
            return from + "--(" + weight + ")-->" + to;
        }
    }
    ////////////////Setup the Graph class
    public static class Graph {
        public Map<Vertex, Set<Vertex>> adjacencyMap;
        private Map<String, Vertex> vertexFinder;
        //returns all vertices...
        public Set<Vertex> vertexSet(){
            return adjacencyMap.keySet();
        }
        //returns set of all neighbors for a given vertex
        public Set<Vertex> neighborSet(Vertex v){
            return adjacencyMap.getOrDefault(v, new HashSet<>());
        }
        public int vertexCount;
        public int edgeCount;
        public Graph(String[] vertices, String[] edges){
            adjacencyMap = new HashMap<>();
            vertexFinder = new HashMap<>();
            vertexCount = vertices.length;
            edgeCount = edges.length;
            for(String v: vertices) {
                Vertex temp = new Vertex(v);
                vertexFinder.put(v, temp);
                adjacencyMap.put(temp, new HashSet<>());
            }
            for(String e: edges){//"(u,v)" is represented by "u,v"
                String[] endpoints = e.split(",");
                addEdge(vertexFinder.get(endpoints[0]), vertexFinder.get(endpoints[1]));
            }
        }
        public void resetInDegrees(){
            for(Vertex v: adjacencyMap.keySet())
                v.indegree = 0;
            for(Vertex v: adjacencyMap.keySet())
                for(Vertex w: adjacencyMap.get(v))
                    w.indegree++;
        }
        public void resetForDFS(){
            for(Vertex v: adjacencyMap.keySet()){
                v.pred = null;
                v.finished = v.discovered = 0;
            }
        }
        public void resetForShortestPathProblem(){
            for(Vertex v: vertexSet()){
                v.discovered = 0;
                v.pred = null;
                v.rank = Integer.MAX_VALUE;
            }
        }
        public Vertex getVertex(String name){
            return vertexFinder.get(name);
        }
        private void addEdge(Vertex from, Vertex to) {
            adjacencyMap.get(from).add(to);
            to.indegree++;
        }
    }
    ////////////////Setup the Weighted Graph class
    public static class WeightedGraph extends Graph{
        public Map<Vertex, Map<Vertex,Integer>> weight;
        public WeightedGraph(String[] vertices, String[]edges){
            super(vertices, edges);
            weight = new HashMap<>();
            for(Vertex v: vertexSet()) {
                weight.put(v, new HashMap<>());
            }
            for(String e: edges){ // go over all the edges
                // because we have weights, we will have (vertex,vertex,weight) so we need to split on the commas
                String[] endpoints = e.split(",");
                if(endpoints.length == 3){ // endpoints[0] is the 'from' index, endpoints[1] is the 'to' index, and endpoints[2] is the weight
                    setWeight(endpoints[0], endpoints[1], Integer.parseInt(endpoints[2]));
                }
                else{ // otherwise, theres only 2 peices and its an unweighted graph, the weight of every edge it 1
                    setWeight(endpoints[0], endpoints[1], 1);
                }
            }
        }

        public int getWeight(String from, String to) {
            return weight.get(getVertex(from)).getOrDefault(getVertex(to), Integer.MAX_VALUE);
        }
        public int setWeight(String from, String to, int newWeight) {
            Vertex fromVertex = getVertex(from);
            Vertex toVertex = getVertex(to);
            int rv = weight.get(fromVertex).getOrDefault(toVertex, Integer.MAX_VALUE);
            weight.get(fromVertex).put(toVertex, newWeight);
            return rv;
        }
    }
    ////////////////Setup the Dijkstra function
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
    ////////////////Setup for printing
    public static void printShortestPathSolution(String sourceVertex, Graph graph) {
        Vertex source = graph.getVertex(sourceVertex);
        System.out.println("Source is " + source);
        for (Vertex v : graph.vertexSet()) {
            if (v.equals(source))
                continue;  // Skip the source vertex
            // Check if the vertex is reachable
            String path = v.rank == Integer.MAX_VALUE ? "No path" : findPath(sourceVertex, v.name, graph).toString();
            System.out.println("Shortest distance/path from " + source + " to "
                    + v + ": " + (v.rank == Integer.MAX_VALUE ? "Infinity" : v.rank) + " " + path);
        }
    }
    ////////////////Setup the Function to handle the List of Vertex's
    public static List<Vertex> findPath(String source, String target, Graph graph){
        LinkedList<Vertex> rv = new LinkedList<>();
        Vertex sourceVertex = graph.getVertex(source);
        Vertex targetVertex = graph.getVertex(target);
        // Check if both source and target vertices exist in the graph
        if (sourceVertex == null || targetVertex == null) {
            return rv; // Return empty list if source or target doesn't exist
        }
        helper(sourceVertex, targetVertex, rv);
        return rv;
    }
    /// Helper
    private static void helper(Vertex source, Vertex target, LinkedList<Vertex> path) {
        if (source.equals(target)) {
            path.addFirst(source);
            return;
        }
        // Check if the target is unreachable or has no predecessor
        if (target.rank == Integer.MAX_VALUE || target.pred == null) {
            path.clear(); // Path doesn't exist
            return;
        }
        path.addFirst(target);
        helper(source, target.pred, path); // Recursively build the path
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(
                new String[]{//vertices
                        "vA", "vO", "vF", "vT", "vB", "vD", "vC", "vE"
                }, new String[]{//edges
                "vA,vB,2", "vA,vF,12", "vA,vD,7", "vF,vA,12",
                "vO,vA,2", "vO,vB,5", "vO,vC,4", "vA,vO,2",
                "vF,vT,3", "vB,vA,2", "vB,vO,5", "vB,vD,4",
                "vB,vE,3", "vB,vC,1", "vD,vB,4", "vD,vA,7",
                "vD,vE,4", "vD,vT,5", "vT,vE,7", "vT,vD,5",
                "vT,vF,3", "vC,vO,4", "vC,vB,1", "vC,vE,4",
                "vE,vC,4", "vE,vB,3", "vE,vD,4", "vE,vT,7"
        }
        );
        System.out.println("\nRunning Dijkstra's on a weighted graph with 8 vertices\n");
        try{
            dijkstra(graph, "vA");
            printShortestPathSolution("vA", graph);//say source is vA
        }catch (Exception exp){
            System.out.println(exp.getMessage());
        }
    }
}

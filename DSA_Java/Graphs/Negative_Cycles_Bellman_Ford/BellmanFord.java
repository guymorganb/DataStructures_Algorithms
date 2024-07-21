package HW5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.*;

public class BellmanFord {
    public static class Vertex implements Comparable<Vertex> {
        public String name;
        public int indegree;
        // changed rank to double
        public double rank;//used to compare two vertices. Can be topological number, distance from source, etc
        public int discovered;//discovery time in DFS... Is always positive
        public int finished;//finish time in DFS... Is always positive
        public Vertex pred;

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            Vertex another = (Vertex) o;
            return this.name.equals(another.name);
        }

        @Override
        public String toString() {
            return name;
        }

        public Vertex(String name) { // constructor
            this.name = name;
            this.indegree = 0;
            this.discovered = 0;
            this.finished = 0;
            this.pred = null;
            this.rank = 0.0;
        }

        public int compareTo(Vertex another) {
            return Double.compare(this.rank , another.rank); // change to double //////// from (this.rank - another.rank)
        }
    }

    public static class WeightedGraph extends Graph{
        public Map<Vertex, Map<Vertex,Double>> weight; // change to double////////////////

        public WeightedGraph(String[] vertices, String[]edges){ // constructor
            super(vertices, edges);
            weight = new HashMap<>();
            for(Vertex v: vertexSet()) {
                weight.put(v, new HashMap<>());
            }
            for(String e: edges){ // go over all the edges
                // because we have weights, we will have (vertex,vertex,weight) so we need to split on the commas
                String[] endpoints = e.split(",");
                if(endpoints.length == 3){ // endpoints[0] is the 'from' index, endpoints[1] is the 'to' index, and endpoints[2] is the weight
                    setWeight(endpoints[0], endpoints[1], Double.parseDouble(endpoints[2])); // change to double//////////////////////////////////
                }
                else{ // otherwise, theres only 2 peices and its an unweighted graph, the weight of every edge it 1
                    setWeight(endpoints[0], endpoints[1], 1);
                }
            }
        }
        // change to double//////////////////////////////
        public double getWeight(String from, String to) {
            return weight.get(getVertex(from)).getOrDefault(getVertex(to), Double.MAX_VALUE); // change to double//////////////////////////////
        }
        // change to double//////////////////////////////
        public double setWeight(String from, String to, double newWeight) { // change to double//////////////////////////////
            Vertex fromVertex = getVertex(from);
            Vertex toVertex = getVertex(to);
            double rv = weight.get(fromVertex).getOrDefault(toVertex, Double.MAX_VALUE); // change to double//////////////////////////////
            weight.get(fromVertex).put(toVertex, newWeight);
            return rv;
        }
    }
    public static class Graph {
        public Map<Vertex, Set<Vertex>> adjacencyMap;
        private Map<String, Vertex> vertexFinder;
        //returns all vertices...
        public Set<Vertex> vertexSet(){
            return adjacencyMap.keySet(); // gets all the vertexes
        }
        //returns set of all neighbors for a given vertex
        public Set<Vertex> neighborSet(Vertex v){
            return adjacencyMap.getOrDefault(v, new HashSet<>());
        }
        public int vertexCount;
        public int edgeCount;

        public Graph(String[] vertices, String[] edges){ // constructor
            adjacencyMap = new HashMap<>();
            vertexFinder = new HashMap<>();
            vertexCount = vertices.length;
            edgeCount = edges.length;

            for(String v: vertices) {
                Vertex temp = new Vertex(v);
                vertexFinder.put(v, temp);
                adjacencyMap.put(temp, new HashSet<>()); /// I think this shows all edges adjacent to a vertex
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
                v.rank = Double.MAX_VALUE; // this rank is for relaxing the edges
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

    public static List<List<Vertex>> bellman_ford(WeightedGraph weightedGraph, String sourceVertex) {
        weightedGraph.resetForShortestPathProblem();
        Vertex source = weightedGraph.getVertex(sourceVertex);
        source.rank = 0;
        List<List<Vertex>> negativeCycles = new ArrayList<>();

        for (int rep = 0; rep < weightedGraph.vertexCount * 2; rep++) {
            boolean updated = false;
            for (Vertex from : weightedGraph.vertexSet()) {
                for (Vertex to : weightedGraph.neighborSet(from)) {
                    double weight = weightedGraph.getWeight(from.name, to.name);

                    if (from.rank != Double.MAX_VALUE && to.rank > from.rank + weight) {
                        to.rank = from.rank + weight;
                        to.pred = from;
                        updated = true;

                        if (rep <= weightedGraph.vertexCount - 1) {
                            List<Vertex> cycle = traceNegativeCycle(to);
                            if (!cycleAlreadyDetected(negativeCycles, cycle)) {
                                negativeCycles.add(cycle);
                            }
                        }
                    }
                }
            }
            if (!updated) break;
        }
        return negativeCycles;
    }

    private static boolean cycleAlreadyDetected(List<List<Vertex>> negativeCycles, List<Vertex> newCycle) {
        for (List<Vertex> existingCycle : negativeCycles) {
            if (cyclesAreEquivalent(existingCycle, newCycle)) {
                return true;
            }
        }
        return false;
    }

    private static boolean cyclesAreEquivalent(List<Vertex> cycle1, List<Vertex> cycle2) {
        if (cycle1.size() != cycle2.size()) return false;
        return Collections.indexOfSubList(cycle1, cycle2) != -1 ||
                Collections.indexOfSubList(cycle2, cycle1) != -1;
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
        Set<Vertex> visited = new HashSet<>();
        while (!target.equals(source) && !visited.contains(target)) {
            path.addFirst(target);
            visited.add(target);
            if (target.pred == null) {
                path.clear(); // Path doesn't exist
                return;
            }
            target = target.pred;
        }
        if (target.equals(source)) {
            path.addFirst(source);
        } else {
            path.clear(); // Cycle detected, path doesn't exist
        }
    }

    private static double calculateArbitrageProfit(List<Vertex> cycle, WeightedGraph graph) {
        double product = 1.0;
        for (int i = 0; i < cycle.size(); i++) {
            Vertex from = cycle.get(i);
            Vertex to = cycle.get((i + 1) % cycle.size());
            double weight = graph.getWeight(from.name, to.name);
            product *= Math.exp(-weight);
        }
        return product - 1.0;
    }

    private static List<Vertex> traceNegativeCycle(Vertex start) {
        Set<Vertex> visited = new HashSet<>(); // keep track of the vertices that have been visited during the traversal helps in identifying when a cycle begins.
        List<Vertex> cycle = new ArrayList<>(); // store the sequence of vertices that forms the cycle.
        Vertex current = start; // starting point for tracing a cycle.

        //  continues until the current vertex has already been visited
        while (!visited.contains(current)) { //visiting the same vertex twice indicates a loop.
            visited.add(current);
            cycle.add(current);
            current = current.pred;
            if (current == null) return Collections.emptyList(); // No cycle found
        }

        int cycleStart = cycle.indexOf(current); // This index represents the starting point of the cycle within the list.
        return new ArrayList<>(cycle.subList(cycleStart, cycle.size()));  // returns a new list containing only the part of the cycle list from cycleStart to the end
    }

    private static void printNegativeCycle(List<Vertex> cycle, WeightedGraph graph) {
        if (cycle.isEmpty()) {
            System.out.println("Empty cycle detected. This should not happen.");
            return;
        }
        System.out.println("Negative cycle (arbitrage opportunity) detected:");
        System.out.println("Number of currencies in the cycle: " + cycle.size());
        System.out.println(cycle);
        double totalWeight = 0;
        // check how its being calculated for more than 2 vertecies
        for (int i = 0; i < cycle.size(); i++) {
            Vertex from = cycle.get(i);

            Vertex to = cycle.get((i + 1) % cycle.size());///

            double weight = graph.getWeight(from.name, to.name);
            double exchangeRate = Math.exp(-weight);
            totalWeight += weight;
            System.out.printf("%s -> %s: %f (Exchange rate: %f)\n", from, to, weight, exchangeRate);
        }
        if (cycle.size() > 2) {
            System.out.println("This is a triangular (or higher) arbitrage opportunity.");
        } else {
            System.out.println("This is a direct arbitrage opportunity between two currencies.");
        }

        /// i think this needs to be adjusted to calculate the  triangular arbitrage
        System.out.printf("Total cycle weight: %f\n", totalWeight);
        double totalExchangeRate = Math.exp(-totalWeight);
        System.out.printf("Total exchange rate: %f\n", totalExchangeRate);

        double profit = calculateArbitrageProfit(cycle, graph);

        System.out.printf("Arbitrage profit: %.2f%%\n", profit * 100);
    }



    public static void main(String[] args) {
        final int NUM_CURRENCIES = 54;
        String filePath = "/Users/guymorganb/Desktop/GitHub_Repos/NonPortfolioItems/FIU_Assignments/DSAFIU/Homework4/src/HW5/exchangerates.csv";
        String[] vertexArr = new String[NUM_CURRENCIES];
        double[][] currencyData = new double[NUM_CURRENCIES][NUM_CURRENCIES];
        int lineIndex = 0;
        String temp;
        ArrayList<String> currencyNames = new ArrayList<>();
        ArrayList<String> localEdgeList = new ArrayList<>();
        // read the input
        try{
            FileInputStream file = new FileInputStream(filePath);
            Scanner scnr = new Scanner(file);
            temp = scnr.nextLine(); // read the first line, we need to read it, but we dont need it

            // put the input in an array
            while(scnr.hasNext()){
                temp = scnr.nextLine();

                String[] tempArr = temp.split(",");
                currencyNames.add(tempArr[0].replace(" ", "")); // add the currency names to arr list, remove the spaces

                // cut off the name of the currency
                for(int i = 1; i < tempArr.length; i++){
                    int j = i - 1;
                    tempArr[j] = tempArr[i];
                }

                // read the data into a 2d array
                for(int i = 0, j = 0; i < NUM_CURRENCIES && j < NUM_CURRENCIES; i++, j++){
                    currencyData[lineIndex][j] = -Math.log(Double.parseDouble(String.valueOf(tempArr[j])));
                }
                lineIndex++;
            }

            scnr.close();
            // now format names(vertexes) input for the algorithm
            vertexArr = currencyNames.toArray(vertexArr);
            // format all the edges in the way the algorithm expects
            for(int i = 0; i < currencyData.length; i++){
                for(int j = 0; j < currencyData[i].length; j++){
                    if (i != j) {  // Skip self-connections
                        String edge = vertexArr[i].replace(" ", "") + "," + vertexArr[j].replace(" ", "") + "," + currencyData[i][j]; // take the spaces out
                        localEdgeList.add(edge);
                    }
                }
            }

            // this is the array of edges from the for loop above we pas this to bellman ford
            String[] edgeArr = localEdgeList.toArray(new String[0]);

//        System.out.println("edgeArr:" + Arrays.toString(edgeArr));
//        System.out.println("vertexArr:" + Arrays.toString(vertexArr));

            // instantiate the weighted graph class with edges and verticies

            WeightedGraph graph = new WeightedGraph(vertexArr, edgeArr);

            System.out.println("\nRunning Bellman-Ford's on the graph ...\n");

            try{
                List<List<Vertex>> negativeCycle = bellman_ford(graph, "USD");

                if (negativeCycle != null) {
                    for (List<Vertex> cycle : negativeCycle) {
                        printNegativeCycle(cycle, graph);
                        System.out.println();
                    }
                } else {
                    printShortestPathSolution("USD", graph);
                }
            }catch(Exception e) {
                System.out.println(e.getMessage());
            }

        }catch(FileNotFoundException err){
            System.err.println(err + filePath);
        }

    }

}
package HW5;
import java.sql.Array;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Kruskals {

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

    public static class Edge implements Comparable<Edge> {
        public Vertex from, to;
        int weight;

        public Edge(Vertex from, Vertex to, int weight) {
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

    public static class DisjointSet<T> {
        //class DijointSet keeps a set of disjoint sets in an array
        //Example: say we want to store {{A,B}, {C}, {D,E,F}}
        //elements = [A, B, C, D, E, F]
        //parent = [-2, 0, -1, -3, 3, 3]
        //size = 6
        //index = {A:0, B:1, C:2, D:3, E:4, F:5}
        //find(A) and find B return A
        //find(C) returns C
        //find(D), find(E), and find(F) all return D
        //union (B, E) will change the set to become like this:
        //{{A,B,D,E,F}, {C}}
        //elements array array, index map and size won't change
        //parent becomes [3, 3, -1, -5, 3, 3]
        private int[] parent;//stores the parent of each element
        private T[] elements;//stores all elements
        private int size;//stores the current size
        private HashMap<T, Integer> index;//maps each element to its index in the elements array
        public DisjointSet(){
            this(100);
        }
        public DisjointSet(int capacity) {//constructor receives an integer specifying the maximum # of elements
            index = new HashMap<T, Integer>();
            int i = 0;
            parent = new int[capacity];
            elements = (T[]) new Object[capacity];
            size = 0;
            for(i = 0; i < parent.length;i++)
                parent[i] = -1;
        }
        public T find(T t) {
            int i = index.getOrDefault(t, -1);
            if(i == -1){
                index.put(t, size);
                elements[size++] = t;
                return t;
            }
            while(parent[i] >= 0)
                i = parent[i];
            return elements[i];
        }
        public void union(T u, T v) {
            int i = index.get(find(u));
            int j = index.get(find(v));
            if(i == j)//u & v are already in the same set
                return;
            if(parent[i] < parent[j]){//union by size: u is in a bigger disjoint set
                parent[i] += parent[j];//update the size
                parent[j] = i;//add v's set to u's set
            }
            else{//union by size: u is not in a bigger disjoint set
                parent[j] += parent[i];//update the size
                parent[i] = j;//add u's set to v's set
            }
        }
        @Override
        public String toString(){
            Map<T, Set<T>> mapOfDisjointSets = new HashMap<>();
            for(T t: index.keySet()){
                T rep = find(t);
                Set<T> set = mapOfDisjointSets.getOrDefault(rep, new HashSet<>());
                set.add(t);
                mapOfDisjointSets.put(rep, set);
            }
            return mapOfDisjointSets.values().toString();
        }
    }

    public static Edge[] kruskal(WeightedGraph weightedGraph) {
        ArrayList<Edge>edges = new ArrayList<>();
        Edge[] mst = new Edge[weightedGraph.vertexCount - 1];
        for(Vertex from: weightedGraph.vertexSet())
            for(Vertex to: weightedGraph.neighborSet(from))
                edges.add(new Edge(from, to , weightedGraph.getWeight(from.name, to.name)));
        Collections.sort(edges);
        DisjointSet<Vertex> conntectedComponents = new DisjointSet<>(weightedGraph.vertexCount);
        int cur = 0;
        for(Edge e: edges)
            if(!conntectedComponents.find(e.from).equals(conntectedComponents.find(e.to))) {
                mst[cur++] = e;
                conntectedComponents.union(e.from, e.to);
            }
        return mst;
    }

    public static class Vertex implements Comparable<Vertex> {
        public String name;
        public int indegree;
        public int rank;//used to compare two vertices. Can be topological number, distance from source, etc
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

        public Vertex(String name) {
            this.name = name;
            this.indegree = 0;
            this.discovered = 0;
            this.finished = 0;
            this.pred = null;
            this.rank = 0;
        }

        public int compareTo(Vertex another) {
            return this.rank - another.rank;
        }
    }

    public static double getDirectDistance(double lat1, double lon1, double lat2, double lon2) {
        //finds the direct distance (in meters) on earth surface between
        //point 1 at (lat1Â° N, lon1Â° W) and point 2 at (lat2Â° N, lon2Â° W)
        double psi1 = lat1 * Math.PI/180;
        double psi2 = lat2 * Math.PI/180;
        double deltaPsi = psi2 - psi1;
        double deltaLambda = (lon2-lon1) * Math.PI/180;
        double a = Math.sin(deltaPsi/2) * Math.sin(deltaPsi/2) +
                Math.cos(psi1) * Math.cos(psi2) *
                        Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);
        return  2 * 6371e3 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    }

    public static void main(String args[]) {
        String filePath = "/Users/guymorganb/Desktop/GitHub_Repos/NonPortfolioItems/FIU_Assignments/DSAFIU/Homework4/src/HW5/islandsLocations.csv";
        String[] lineIn;
        String[] preparedStringArray = new String[(20*(20-1)/2)];
        ArrayList<String[]> arrList = new ArrayList<>();
        String temp;
        ArrayList<String[]> formattedValues = new ArrayList<>();
        String lastIndex = "";
        // read the input
        try{
            FileInputStream file = new FileInputStream(filePath);
            Scanner scnr = new Scanner(file);

        // put the input in an array
            while(scnr.hasNext()){
                temp = scnr.nextLine();
                // return an array using the comma as a delimiter
                lineIn = temp.split(",");
                arrList.add(lineIn);
            }

            arrList.remove(0);
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < arrList.size() - 1; i++){ // for each element of the list
                // get the lat and long
                String[] s = arrList.get(i);
                double latt1 = Double.parseDouble(s[1]);
                double Long1 = Double.parseDouble(s[2]);

                for(int j = i + 1; j < arrList.size(); j++){ // iterate over the next element
                    String[] s2 = arrList.get(j);
                    double latt2 = Double.parseDouble(s2[1]);
                    double long2 = Double.parseDouble(s2[2]);

                    // calc distance public static double getDirectDistance(double lat1, double lon1, double lat2, double lon2)
                    double dist = getDirectDistance(latt1, Long1, latt2, long2);
                    String distance = Integer.toString((int)dist);

                    sb = sb.append("v").append(i).append(",").append("v").append(j).append(",").append(distance).append("/");
                    String[] sbTemp = sb.toString().split("/");
                    formattedValues.add(sbTemp);
                }
            }

            for(int i = 0; i < formattedValues.get(formattedValues.size() -1).length; i++){
                preparedStringArray[i] = formattedValues.get(formattedValues.size() - 1)[i];
            }


        }catch(FileNotFoundException err){
            System.err.println(err + filePath);
        }


        ////////////////////////////////////////////////
        WeightedGraph graph = new WeightedGraph(
                new String[]{//vertices
                        "v0", "v1", "v2", "v3", "v4", "v5", "v6","v7", "v8", "v9", "v10","v11", "v12", "v13", "v14","v15", "v16", "v17", "v18", "v19"
                },preparedStringArray);

        Edge[] mst = kruskal(graph);
        for(Edge e: mst)
            System.out.println(e);
    }
}


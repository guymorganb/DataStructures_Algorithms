package util;
import java.util.*;
public class Graph {
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

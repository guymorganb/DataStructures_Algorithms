package util;
import java.util.*;
public class WeightedGraph extends Graph{
    public Map<Vertex, Map<Vertex,Integer>> weight;
    public WeightedGraph(String[] vertices, String[]edges){
        super(vertices, edges);
        weight = new HashMap<>();
        for(Vertex v: vertexSet()) {
            weight.put(v, new HashMap<>());
        }
        for(String e: edges){
            String[] endpoints = e.split(",");
            if(endpoints.length == 3)
                setWeight(endpoints[0], endpoints[1], Integer.parseInt(endpoints[2]));
            else
                setWeight(endpoints[0], endpoints[1], 1);
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

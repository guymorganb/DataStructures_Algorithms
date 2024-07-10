package algorithms;
import java.util.*;
import util.*;
public class MinimumSpanningTree {
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
    public static void main(String[] args){
        WeightedGraph graph = new WeightedGraph(
                new String[]{//vertices
                        "v1", "v2", "v3", "v4", "v5", "v6", "v7"
                }, new String[]{//edges
                "v3,v1,4", "v3,v6,5", "v1,v2,2", "v1,v4,1",
                "v2,v5,10", "v2,v4,3", "v5,v7,6", "v7,v6,1",
                "v4,v5,7", "v4,v7,4", "v4,v6,8", "v4,v3,2"
                }
        );
        Edge[] mst = kruskal(graph);
        for(Edge e: mst)
            System.out.println(e);
    }
}

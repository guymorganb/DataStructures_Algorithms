package algorithms;
import util.*;
import java.util.*;

public class TopologicalSort {
    //Topological sort creates an ordering of vertices of a graph like v1, v2, ..., vn such that
    //if i < j, there is no edge from vj to vi
    public static List<Vertex> kahnsAlgorithm(Graph graph) throws Exception{
        List<Vertex> rv = new ArrayList<>();
        Queue<Vertex> q = new LinkedList<>();
        int counter = 0;
        for(Vertex v: graph.vertexSet())
            if(v.indegree == 0)
                q.add(v);
        while(!q.isEmpty()){
            Vertex v = q.remove();
            v.rank = ++counter;
            rv.add(v);
            for(Vertex w: graph.neighborSet(v))
                    if(--w.indegree == 0)
                        q.add(w);
        }
        graph.resetInDegrees();//undo the side effects of the algorithm on vertices
        if(counter != graph.vertexSet().size())//Only DAGs have topological sort
            throw new Exception("cycle found! No top sort exists!");

        return rv;
    }
    public static List<Vertex> topSortUsingDFS(Graph graph) throws Exception
    {
        LinkedList<Vertex> rv = new LinkedList<>();
        LinkedList<Vertex> starters = new LinkedList<Vertex>();
        graph.resetForDFS();
        for (Vertex v : graph.vertexSet())
            if (v.indegree == 0)
                starters.addFirst(v);
        Stack<Vertex> stack = new Stack<Vertex>();
        int time = 0;
        int topNum = graph.vertexCount;
        for (Vertex startVertex : starters) {
            stack.push(startVertex);
            while (!stack.empty()) {
                Vertex current = stack.pop();
                if (current.finished > 0)//finished processing before
                    continue;
                if (current.discovered > 0) {//discovered before
                    current.finished = ++time;
                    current.rank = topNum--;
                    rv.addFirst(current);
                    continue;
                }
                current.discovered = ++time;//discovered for the first time
                stack.push(current);
                for (Vertex neighbor : graph.neighborSet(current))
                    if (neighbor.discovered == 0) {//haven't discovered yet!
                        neighbor.pred = current;
                        stack.push(neighbor);
                    }else if(neighbor.finished == 0)//have been discovered before but not finished yet...cycle found!
                        throw new Exception("cycle found! No top sort exists!");
            }
        }
        return rv;
    }
    public static void main(String[] args){
        Graph graph = new Graph(new String[]{
                "watch", "shirt", "socks",
                "undershorts", "pants", "shoes",
                "tie", "belt", "jacket"
        }, new String[]{
                "socks,shoes", "undershorts,pants",
                "undershorts,shoes", "pants,shoes",
                "pants,belt", "shirt,belt", "shirt,tie",
                "belt,jacket", "tie,jacket"
        });
        try {
            System.out.println("Order of wearing clothes: " + kahnsAlgorithm(graph));
            System.out.println("Order of wearing clothes: " + topSortUsingDFS(graph));
        }catch (Exception exp){//if Graph is not a DAG
            System.out.println(exp.getMessage());
        }

    }
}

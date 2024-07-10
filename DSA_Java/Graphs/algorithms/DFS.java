package algorithms;
import util.*;
import java.util.*;
public class DFS {
    private static void helper(Vertex current, Graph graph, int time){
        System.out.print(current + "\t");
        current.discovered = ++time;
        for(Vertex neighbor: graph.neighborSet(current))
            if(neighbor.discovered == 0)//haven't been discovered yet
                helper(neighbor, graph, time);
    }
    public static void dfsRecursivePrint(Graph graph){
        int time = 0;
        graph.resetForDFS();
        for(Vertex start: graph.vertexSet()){
            if(start.discovered > 0)
                continue;
            helper(start, graph, time);
        }
    }
    public static void dfsIterativePrint(Graph graph){
        graph.resetForDFS();
        Stack<Vertex> stack = new Stack<Vertex>();
        int time = 0;
        for (Vertex v : graph.vertexSet()) {
            if (v.discovered > 0)
                continue;
            stack.push(v);
            while (!stack.empty()) {
                Vertex current = stack.pop();
                System.out.print(current + "\t");
                current.discovered = ++time;
                for (Vertex neighbor : graph.neighborSet(current)) {
                    if (neighbor.discovered == 0)//haven't been discovered yet!
                        stack.push(neighbor);
                }
            }
        }
    }
    public static void dfsIterativePrint(Graph graph, String startVertex){
        graph.resetForDFS();
        Stack<Vertex> stack = new Stack<Vertex>();
        int time = 0;
        stack.push(graph.getVertex(startVertex));
        while (!stack.empty()) {
            Vertex current = stack.pop();
            System.out.print(current + "\t");
            current.discovered = ++time;
            for (Vertex neighbor : graph.neighborSet(current)) {
                if (neighbor.discovered == 0)//haven't been discovered yet!
                    stack.push(neighbor);
            }
        }
        for (Vertex v : graph.vertexSet()) {
            if (v.discovered > 0)
                continue;
            stack.push(v);
            while (!stack.empty()) {
                Vertex current = stack.pop();
                System.out.print(current + "\t");
                current.discovered = ++time;
                for (Vertex neighbor : graph.neighborSet(current)) {
                    if (neighbor.discovered == 0)//haven't been discovered yet!
                        stack.push(neighbor);
                }
            }
        }
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
        dfsIterativePrint(graph);
        System.out.println();
        dfsRecursivePrint(graph);
        System.out.println("\nDFS with a given start vertex; e.g. shirt:");
        dfsIterativePrint(graph, "shirt");
    }
}

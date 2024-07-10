package util;

public class Edge implements Comparable<Edge>{
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

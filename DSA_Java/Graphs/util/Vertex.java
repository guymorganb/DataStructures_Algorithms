package util;

public class Vertex implements Comparable<Vertex> {
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

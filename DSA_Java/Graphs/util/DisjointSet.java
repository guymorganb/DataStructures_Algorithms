package util;
import java.util.*;
public class DisjointSet<T> {
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
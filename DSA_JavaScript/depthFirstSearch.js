class Graph {
    constructor() {
      this.adjacencyList = {};
    }
  
    addVertex(vertex) {
      if (!this.adjacencyList[vertex]) this.adjacencyList[vertex] = [];
    }
  
    addEdge(v1, v2) {
      this.adjacencyList[v1].push(v2);
      this.adjacencyList[v2].push(v1);
    }
    // depth first
    dfsRecursive(start) {
      const result = [];
      const visited = {};
      const adjacencyList = this.adjacencyList;
  
      (function dfs(vertex) {
        if (!vertex) return null;
        visited[vertex] = true;
        result.push(vertex);
        adjacencyList[vertex].forEach(neighbor => {
          if (!visited[neighbor]) {
            return dfs(neighbor);
          }
        });
      })(start);
  
      return result;
    }
    // bredth first
    bfs(start) {
        const queue = [start];
        const result = [];
        const visited = {};
        visited[start] = true;
    
        while (queue.length) {
          let currentVertex = queue.shift();
          result.push(currentVertex);
    
          this.adjacencyList[currentVertex].forEach(neighbor => {
            if (!visited[neighbor]) {
              visited[neighbor] = true;
              queue.push(neighbor);
            }
          });
        }
    
        return result;
      }
  }
  
  const g = new Graph();
g.addVertex("A");
g.addVertex("B");
g.addVertex("C");
g.addVertex("D");
g.addVertex("E");
g.addVertex("F");

g.addEdge("A", "B");
g.addEdge("A", "C");
g.addEdge("B", "D");
g.addEdge("C", "E");
g.addEdge("D", "E");
g.addEdge("D", "F");
g.addEdge("E", "F");

console.log("DFS:", g.dfsRecursive("A")); // DFS: [ 'A', 'B', 'D', 'E', 'C', 'F' ]
console.log("BFS:", g.bfs("A")); // BFS: [ 'A', 'B', 'C', 'D', 'E', 'F' ]

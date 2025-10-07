package Graph;

/*

Description:
    -> This program implements **Dijkstra’s Algorithm** to find the shortest path 
       from a source vertex to all other vertices in a weighted, directed graph 
       with **non-negative edge weights**.

    -> Dijkstra’s Algorithm is one of the most efficient algorithms for single-source 
       shortest path problems and works using a **greedy approach** with the help 
       of a **Priority Queue (Min-Heap)** for optimal selection of the next vertex 
       with the smallest known distance.

Problem Statement:
    -> Given a graph represented as an adjacency list where:
         graph[u] = List of {v, weight} pairs,
       find the minimum distance from a source vertex `src` to all vertices.

Key Concepts:
    • Each vertex maintains the shortest known distance from the source.
    • Initially, all distances are set to infinity except for the source (set to 0).
    • Using a min-priority queue, at each step, the vertex with the smallest 
      distance is picked, and its neighbors are relaxed (updated if a shorter path is found).

Algorithm Steps:
    1. Initialize:
         - Create a distance array and set all values to infinity (MAX_VALUE).
         - Set distance[src] = 0.
         - Use a PriorityQueue (min-heap) to process nodes based on smallest distance.
    
    2. Relaxation Process:
         - Remove the node with the smallest distance from the priority queue.
         - For each adjacent node:
              -> Calculate new distance = current distance + edge weight.
              -> If this distance is smaller than the existing value:
                     update it and push the node into the priority queue.
    
    3. Continue until all reachable nodes are processed.

Data Representation:
    -> The graph is represented as:
          ArrayList<ArrayList<ArrayList<Integer>>> graph
       where:
          graph.get(u).get(i).get(0) = adjacent vertex
          graph.get(u).get(i).get(1) = edge weight

Example Graph (for visualization):
        (0)
       /   \
     4/     \1
     /       \
   (1)---2---(2)
     \5     /3
      \   /
       (3)
        \
         \1
          (4)

Execution Example:
    Input:
        vertices = 5
        source = 0
    Output:
        Shortest distances from vertex 0:
            Vertex 0: 0
            Vertex 1: 3
            Vertex 2: 1
            Vertex 3: 4
            Vertex 4: 3

Time and Space Complexity:
    -> Time Complexity: O((V + E) * log V)
         - Each vertex is inserted into the priority queue once.
         - Heap operations (insert + extract-min) take O(log V).
    -> Space Complexity: O(V + E)
         - To store graph and distance arrays.

Key Points:
    -> Works only with non-negative edge weights.
    -> Efficient for sparse graphs.
    -> PriorityQueue ensures that the smallest distance node is always processed next.

*/

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra_Algorithm {

    static class Pair {
        int node;
        int distance;

        public Pair(int node, int dist) {
            this.node = node;
            this.distance = dist;
        }

    }

    private static int[] dijkstra(ArrayList<ArrayList<ArrayList<Integer>>> graph, int vertices, int src) {

        int[] distances = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y) -> x.distance - y.distance);

        distances[src] = 0;
        pq.add(new Pair(src, 0));

        while (! pq.isEmpty()) {

            Pair currPair = pq.poll();
            int currNode = currPair.node;
            int currDistance = currPair.distance;

            for (int i = 0; i < graph.get(currNode).size(); i++) {

                int edgeWeight = graph.get(currNode).get(i).get(1);
                int adjNode = graph.get(currNode).get(i).get(0);

                if (currDistance + edgeWeight < distances[adjNode]) {
                    distances[adjNode] = currDistance + edgeWeight;
                    pq.add(new Pair(adjNode, distances[adjNode]));
                }

            }

        }

        return distances;

    }

    public static void main(String[] args) {

        int vertices = 5;
        ArrayList<ArrayList<ArrayList<Integer>>> graph = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Node 0 edges
        graph.get(0).add(new ArrayList<>());
        graph.get(0).get(0).add(1);
        graph.get(0).get(0).add(4);

        graph.get(0).add(new ArrayList<>());
        graph.get(0).get(1).add(2);
        graph.get(0).get(1).add(1);

        // Node 1 edges
        graph.get(1).add(new ArrayList<>());
        graph.get(1).get(0).add(0);
        graph.get(1).get(0).add(4);

        graph.get(1).add(new ArrayList<>());
        graph.get(1).get(1).add(2);
        graph.get(1).get(1).add(2);

        graph.get(1).add(new ArrayList<>());
        graph.get(1).get(2).add(3);
        graph.get(1).get(2).add(5);

        // Node 2 edges
        graph.get(2).add(new ArrayList<>());
        graph.get(2).get(0).add(0);
        graph.get(2).get(0).add(1);

        graph.get(2).add(new ArrayList<>());
        graph.get(2).get(1).add(1);
        graph.get(2).get(1).add(2);

        graph.get(2).add(new ArrayList<>());
        graph.get(2).get(2).add(3);
        graph.get(2).get(2).add(3);

        graph.get(2).add(new ArrayList<>());
        graph.get(2).get(3).add(4);
        graph.get(2).get(3).add(2);

        // Node 3 edges
        graph.get(3).add(new ArrayList<>());
        graph.get(3).get(0).add(1);
        graph.get(3).get(0).add(5);

        graph.get(3).add(new ArrayList<>());
        graph.get(3).get(1).add(2);
        graph.get(3).get(1).add(3);

        graph.get(3).add(new ArrayList<>());
        graph.get(3).get(2).add(4);
        graph.get(3).get(2).add(1);

        // Node 4 edges
        graph.get(4).add(new ArrayList<>());
        graph.get(4).get(0).add(2);
        graph.get(4).get(0).add(2);

        graph.get(4).add(new ArrayList<>());
        graph.get(4).get(1).add(3);
        graph.get(4).get(1).add(1);

        int source = 0;

        int[] distances = dijkstra(graph, vertices, source);

        System.out.println("Shortest distances from source vertex " + source + ":");

        for (int i = 0; i < vertices; i++) {

            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("Vertex " + i + ": INFINITY (Not reachable)");
            } else {
                System.out.println("Vertex " + i + ": " + distances[i]);
            }

        }

    }
    
}

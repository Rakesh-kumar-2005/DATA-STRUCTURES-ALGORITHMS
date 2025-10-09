package Graph;

/*

Description:
    -> This program implements **Dijkstra’s Algorithm** to find the shortest distance 
       from a source vertex to all other vertices in a weighted graph represented 
       using an **adjacency matrix**...

    -> It uses a **greedy approach** with a **Priority Queue (Min-Heap)** to always 
       select the vertex with the smallest known distance and update its adjacent vertices...

Problem Statement:
    -> Given a weighted, connected graph represented as a 2D adjacency matrix, 
       determine the shortest distance from a given source vertex `src` 
       to all other vertices using Dijkstra’s algorithm...

    -> Each cell `graph[i][j]` in the matrix represents the weight of the edge 
       between vertex `i` and vertex `j`...
       If the value is 0, it means there is no direct edge between the vertices...

Approach:
    > Adjacency Matrix Representation:
        -> Instead of using adjacency lists, the graph is stored as a 2D array (matrix)...
        -> Traverse each row to check edges and their corresponding weights...

    > Dijkstra’s Algorithm Steps:
        1. Initialize a distance array with all values as infinity (MAX_VALUE)...
        2. Set distance of source vertex `src` = 0...
        3. Use a PriorityQueue (Min-Heap) to store vertices based on minimum distance...
        4. Extract the vertex with the smallest distance and relax all its adjacent vertices:
             - If a shorter path to a vertex is found, update its distance...
             - Push the updated vertex into the priority queue...
        5. Continue until all vertices are processed or queue becomes empty...

Algorithm Steps:
    1. Initialize:
         - distances[i] = ∞ for all i
         - distances[src] = 0
         - Use a PriorityQueue to store pairs (vertex, distance)
    
    2. While queue not empty:
         - Remove vertex with minimum distance (currNode)
         - For each vertex adjNode:
               - If an edge exists (graph[currNode][adjNode] > 0)
               - And new distance < current distance:
                     Update distances[adjNode] and push to queue...
    
    3. Return the distances array containing shortest paths...

Data Representation:
    -> Graph stored as a 2D adjacency matrix:
         graph[i][j] = weight of edge between vertex i and j
         graph[i][j] = 0 → no edge present

Example Graph Visualization:
        (0)
       /   \
     4/     \1
     /       \
   (1)---2---(2)
     \5     /3 \
      \   /     \
       (3)-------(4)
             1

Demonstration:
    -> Input:
           vertices = 5
           source = 0
    -> Output:
           Vertex 0: 0
           Vertex 1: 3
           Vertex 2: 1
           Vertex 3: 4
           Vertex 4: 3

Key Characteristics:
    -> Works only with non-negative edge weights...
    -> Efficient and accurate for dense graphs represented by matrices...
    -> Uses a min-priority queue for optimal vertex selection...

Time and Space Complexity:
    -> Time Complexity: O(V² log V)
         - Because adjacency matrix requires O(V²) traversal and heap operations add log V overhead...
    -> Space Complexity: O(V²)
         - For storing the adjacency matrix and distance arrays...

*/

import java.util.PriorityQueue;

public class Dijkstra_Algorithm_Grid {

    static class Pair {
        int node;
        int distance;

        public Pair(int node, int dist) {
            this.node = node;
            this.distance = dist;
        }

    }


    private static int[] dijkstra(int[][] graph, int vertices, int src) {

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

            for (int adjNode = 0; adjNode < vertices; adjNode++) {

                int edgeWeight = graph[currNode][adjNode];

                if (edgeWeight > 0 && adjNode != currNode) {

                    if (currDistance + edgeWeight < distances[adjNode]) {
                        distances[adjNode] = currDistance + edgeWeight;
                        pq.add(new Pair(adjNode, distances[adjNode]));
                    }

                }

            }

        }

        return distances;
        
    }

    public static void main(String[] args) {

        int vertices = 5;
        int[][] graph = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = 0;
            }
        }

        // Node 0 edges
        graph[0][1] = 4;
        graph[0][2] = 1;

        // Node 1 edges
        graph[1][0] = 4;
        graph[1][2] = 2;
        graph[1][3] = 5;

        // Node 2 edges
        graph[2][0] = 1;
        graph[2][1] = 2;
        graph[2][3] = 3;
        graph[2][4] = 2;

        // Node 3 edges
        graph[3][1] = 5;
        graph[3][2] = 3;
        graph[3][4] = 1;

        // Node 4 edges
        graph[4][2] = 2;
        graph[4][3] = 1;

        int source = 0;

        System.out.println("Adjacency Matrix:");

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

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

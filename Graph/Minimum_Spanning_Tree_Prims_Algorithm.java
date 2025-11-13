package Graph;

/*

Description:
    -> This program implements Prim’s Algorithm to find the Minimum Spanning Tree (MST) of a given connected, undirected, weighted graph...
    -> The MST of a graph is a subset of its edges that connects all vertices with the minimum possible total edge weight and contains no cycles...
    -> Prim’s Algorithm uses a greedy approach to grow the MST one edge at a time, always choosing the edge with the smallest weight that connects a visited node to an unvisited node...

Problem Statement:
    Given a weighted undirected graph represented by a list of edges, find the cost of its Minimum Spanning Tree using Prim’s Algorithm...

Example:
    Input:
        V = 4...
        Edges = {{0, 1, 5}, {1, 2, 3}, {0, 2, 1}, {0, 3, 6}, {1, 3, 2}, {2, 3, 7}}...
    Output:
        The cost of minimum spanning tree is : 6...

Explanation:
    The edges that form the MST are:
        (0 - 2, weight 1), (1 - 2, weight 3), (1 - 3, weight 2)...
    Total MST cost = 1 + 3 + 2 = 6...

Approach:
    1. Create an adjacency list from the edge list to represent the undirected weighted graph...
    2. Use a `PriorityQueue` (min-heap) to always select the edge with the smallest weight...
    3. Start from node 0 and initialize its distance as 0...
    4. Repeatedly extract the node with the minimum distance from the priority queue...
    5. If the node has already been visited, skip it...
    6. Otherwise, add its edge weight to the total MST sum and mark it as visited...
    7. For each unvisited adjacent node, push it into the priority queue with its edge weight...
    8. Continue the process until all nodes have been visited...
    9. The final `sum` represents the total cost of the Minimum Spanning Tree...

Key Observations:
    -> Prim’s Algorithm efficiently builds the MST without forming cycles...
    -> The priority queue ensures that at each step, the minimum weighted edge is chosen...
    -> The algorithm can handle both sparse and dense graphs effectively...

Time and Space Complexity:
    -> Time Complexity: O(E log V), where E is the number of edges and V is the number of vertices (due to the priority queue operations)...
    -> Space Complexity: O(V + E), for storing the adjacency list and visited array...

Example Walkthrough:
    Input:
        V = 4...
        Edges = {{0, 1, 5}, {1, 2, 3}, {0, 2, 1}, {0, 3, 6}, {1, 3, 2}, {2, 3, 7}}...
    Process:
        Step 1: Start from node 0 → choose edge (0–2) with weight 1...
        Step 2: Choose edge (1–2) with weight 3...
        Step 3: Choose edge (1–3) with weight 2...
        Step 4: MST total weight = 6...
    Output:
        The cost of minimum spanning tree is : 6...

*/

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Minimum_Spanning_Tree_Prims_Algorithm {

    static class Pair {
        int node;
        int dist;

        public Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

    }

    private static int spanningTree(int V, int[][] edges) {

        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            graph.get(u).add(new Pair(v, wt));
            graph.get(v).add(new Pair(u, wt));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y) -> x.dist - y.dist);
        pq.add(new Pair(0, 0));

        int sum = 0;
        int[] visited = new int[V];

        while (! pq.isEmpty()) {
            
            Pair curr = pq.poll();
            int node = curr.node;
            int dist = curr.dist;

            if (visited[node] == 1) {
                continue;
            }

            sum += dist;
            visited[node] = 1;

            for (Pair neighbour : graph.get(node)) {
                if (visited[neighbour.node] == 0) {
                    pq.add(neighbour);
                }
            }
            
        }

        return sum;
    }

    public static void main(String[] args) {

        int[][] edges = {{0, 1, 5}, {1, 2, 3}, {0, 2, 1}, {0, 3, 6}, {1, 3, 2}, {2, 3, 7}};
        int V = 4;
        System.out.println("The cost of minimum spanning tree is : " + spanningTree(V, edges));

    }

}

package Graph;

/*

Description:
    -> This program finds and prints the **Shortest Path** between 
       two vertices (from node 1 to node n) in a **weighted undirected graph** 
       using **Dijkstra’s Algorithm**.

    -> It not only computes the minimum distance but also reconstructs 
       the actual path taken from the source to the destination.

Problem Statement:
    -> Given a list of edges (grid), where each edge connects two nodes (u, v)
       with a given weight, find the shortest path from node 1 to node n.
       If no path exists, print “No path exists!”.

Approach:
    -> The graph is represented as an **Adjacency List**, where each node 
       stores a list of pairs (neighbor, edge weight).

    -> Dijkstra’s algorithm is used to calculate the shortest distance 
       from source node `1` to all other nodes.

    -> A **parent[] array** is maintained to trace back the path from 
       the destination node `n` to the source node `1`.

Algorithm Steps:
    1. Initialize:
         - A graph (Adjacency List) from the given edge list.
         - A distance array `distances[]` with initial values = ∞ (MAX_VALUE).
         - A parent array `parent[]` where each node is its own parent initially.
         - A Min-Priority Queue (based on distance).

    2. Set distance of source node (1) = 0 and push it into the priority queue.

    3. While the queue is not empty:
         - Extract the node with the smallest distance.
         - For each adjacent node:
               - If a shorter path is found:
                     * Update distances[adjNode]
                     * Update parent[adjNode]
                     * Push (adjNode, newDistance) into the queue

    4. After processing all nodes:
         - If the destination node (n) is unreachable, print "No path exists!"
         - Otherwise, backtrack using the `parent[]` array to reconstruct the path.

Path Reconstruction:
    -> Start from the destination node `n` and keep moving to its parent 
       until reaching the source node `1`.
    -> Reverse the path to get the correct order from source to destination.

Example Graph:
        (1)
         |
         |1
         |
        (2)
         |
         |1
         |
        (3)
         |
         |1
         |
        (4)
         |
         |1
         |
        (5)

Input Representation:
    -> grid = {
         {1, 2, 1},
         {2, 3, 1},
         {3, 4, 1},
         {4, 5, 1}
       }

    -> n = 5 (number of vertices)
    -> m = 4 (number of edges)

Output:
    The Shortest Path is:
    1 -> 2 -> 3 -> 4 -> 5

    Shortest Distance: 4

Key Concepts Used:
    -> Dijkstra’s Algorithm (Greedy approach)
    -> Priority Queue (Min-Heap)
    -> Adjacency List Representation
    -> Path Reconstruction using parent array

Edge Cases Handled:
    -> If no path exists between node 1 and node n, output “No path exists!”.
    -> Supports graphs with weighted undirected edges.

Time and Space Complexity:
    -> Time Complexity: O((V + E) * log V)
         - Each vertex and edge is processed once, with heap operations.
    -> Space Complexity: O(V + E)
         - For adjacency list, distance, and parent arrays.

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Print_Shortest_Path {

    static class Pair {
        int distance;
        int node;

        public Pair(int dist, int node) {
            this.distance = dist;
            this.node = node;
        }
    }

    private static ArrayList<Integer> printShortestPath(int[][] grid, int n, int m) {

        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = grid[i][0];
            int v = grid[i][1];
            int weight = grid[i][2];

            graph.get(u).add(new Pair(weight, v));
            graph.get(v).add(new Pair(weight, u));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.distance - y.distance);
        int[] distances = new int[n + 1];
        int[] parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            distances[i] = Integer.MAX_VALUE;
            parent[i] = i;
        }

        distances[1] = 0;
        pq.add(new Pair(0, 1));

        while (! pq.isEmpty()) {

            Pair currPair = pq.poll();
            int currNode = currPair.node;
            int currDistance = currPair.distance;

            if (currDistance > distances[currNode]) {
                continue;
            }

            for (Pair adjacents : graph.get(currNode)) {

                int adjNode = adjacents.node;
                int adjDistance = adjacents.distance;

                if (currDistance + adjDistance < distances[adjNode]) {
                    distances[adjNode] = currDistance + adjDistance;
                    pq.add(new Pair(distances[adjNode], adjNode));
                    parent[adjNode] = currNode;
                }

            }

        }

        ArrayList<Integer> path = new ArrayList<>();

        if (distances[n] == Integer.MAX_VALUE) {
            path.add(- 1);
            return path;
        }

        int node = n;

        while (parent[node] != node) {
            path.add(node);
            node = parent[node];
        }

        path.add(1);
        Collections.reverse(path);

        return path;

    }

    private static void print(ArrayList<Integer> path) {

        if (path.size() == 1 && path.get(0) == - 1) {
            System.out.println("No path exists!");
            return;
        }

        for (int i = 0; i < path.size() - 1; i++) {
            System.out.print(path.get(i) + " -> ");
        }

        System.out.println(path.get(path.size() - 1));
    }

    public static void main(String[] args) {

        int[][] grid = {{1, 2, 1}, {2, 3, 1}, {3, 4, 1}, {4, 5, 1}};
        int n = 5;
        int m = 4;

        ArrayList<Integer> path = printShortestPath(grid, n, m);

        System.out.println("The Shortest Path is: ");
        print(path);

        System.out.println("\nShortest Distance: " + (path.get(0) == - 1 ? "Unreachable" : (path.size() - 1)));
    }

}

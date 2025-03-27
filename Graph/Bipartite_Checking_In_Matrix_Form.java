package Graph;

/*

Description:
  Following program demonstrates the solution to the "Bipartite Graph Checking" problem using Breadth-First Search (BFS) and Color-Based Approach.

Problem Statement:
  -> Given an undirected graph represented as an adjacency list or matrix.
  -> Determine whether the graph is bipartite or not.
  -> A bipartite graph is a graph that can be divided into two disjoint sets such that
     every edge connects a vertex from one set to a vertex from the other set.

Approach:
  > Color-Based Breadth-First Search (BFS):
     i. Use a color array to mark vertices with two different colors (1 and -1).
     ii. Start BFS from each uncolored vertex.
     iii. Color adjacent vertices with the opposite color of the current vertex.
     iv. If an adjacent vertex is already colored and has the same color as the current vertex,
         the graph is not bipartite.

> Algorithm Steps:
  -> Initialize a color array with 0 (uncolored), 1 (first color), and -1 (second color).
  -> Iterate through all vertices of the graph.
  -> For each uncolored vertex, start a BFS traversal:
     1. Add the starting vertex to the queue.
     2. Color the starting vertex with color 1.
     3. While the queue is not empty:
        - Poll a vertex from the queue.
        - For each adjacent vertex:
          * If uncolored, color it with the opposite color and add to queue.
          * If already colored, check if it has the same color as the current vertex.
          * If same color found, return false (not bipartite).
  -> If no conflict is found, return true (graph is bipartite).

> Key Characteristics:
  -> Works for both connected and disconnected graphs.
  -> Handles graphs with multiple components.
  -> Uses BFS for systematic graph traversal.

> Color Representation:
  -> 0: Uncolored vertex
  -> 1: First color set
  -> -1: Second color set
  -> Adjacent vertices must have opposite colors

> Time and Space Complexity:
  -> Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges.
  -> Space Complexity: O(V) for the color array and queue.

*/

import java.util.LinkedList;
import java.util.Queue;

public class Bipartite_Checking_In_Matrix_Form {
    
    private static boolean check(int[][] graph, int start, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 1;

        while (! q.isEmpty()) {
            int node = q.poll();

            for (int curr : graph[node]) {
                if (color[curr] == 0) {
                    color[curr] = - color[node];
                    q.add(curr);
                } else if (color[curr] == color[node]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isBipartite(int[][] graph) {

        int n = graph.length;
        int[] color = new int[n];

        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (! check(graph, i, color)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        if (isBipartite(graph)) {
            System.out.println("This graph is a bipartite graph...");
        } else {
            System.out.println("This graph is not a bipartite graph...");
        }
    }
}

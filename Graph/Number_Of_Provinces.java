package Graph;

/*

Description:
  This program solves the "Number of Provinces" problem using Depth-First Search (DFS) technique...

Problem Statement:
  -> Given an undirected graph represented as an adjacency matrix...
  -> Count the number of provinces (connected components) in the graph...
  -> A province is a group of directly or indirectly connected vertices...

Approach:
  > Depth-First Search (DFS) Traversal:
     i. Use a visited array to track explored vertices...
     ii. Iterate through all vertices...
     iii. For each unvisited vertex, increment the province count...
     iv. Perform DFS to mark all connected vertices as visited...

> Algorithm Steps:
  -> Initialize a visited boolean array to track explored vertices...
  -> Initialize a province count variable to 0...
  -> Iterate through all vertices in the graph:
     1. If a vertex is unvisited:
        - Increment the province count...
        - Perform DFS from that vertex...
     2. In DFS traversal:
        - Mark the current vertex as visited...
        - Explore all adjacent vertices...
        - Recursively visit unvisited adjacent vertices...
  -> Return the total number of provinces...

> Key Characteristics:
  -> Works for both connected and disconnected graphs...
  -> Uses DFS for systematic graph exploration...
  -> Efficiently identifies separate graph components...

> Graph Representation:
  -> Adjacency Matrix:
     * 1 indicates an edge between vertices...
     * 0 indicates no direct connection...
  -> Symmetric matrix for undirected graphs...

> Time and Space Complexity:
  -> Time Complexity: O(V^2) due to matrix traversal, where V is the number of vertices...
  -> Space Complexity: O(V) for the visited array and recursive call stack...

> Practical Applications:
  -> Social network analysis...
  -> Finding isolated groups in networks..
  -> Identifying disconnected regions in computer networks..
*/

public class Number_Of_Provinces {

    private static void helper(int[][] graph, int start, boolean[] vis) {

        vis[start] = true;

        for (int i = 0; i < graph.length; i++) {
            if (graph[start][i] == 1 && ! vis[i]) {
                helper(graph, i, vis);
            }
        }
    }

    private static int findCircleNum(int[][] graph) {

        int v = graph.length;
        boolean[] vis = new boolean[v];
        int count = 0;

        for (int i = 0; i < v; i++) {
            if (! vis[i]) {
                count++;
                helper(graph, i, vis);
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int[][] graph = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int provinces = findCircleNum(graph);

        System.out.println("Number of provinces or connected components are = " + provinces);
    }
}x
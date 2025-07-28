package Graph;

/*

Description:
    -> This program finds the shortest path from a given source node to all other nodes in an **undirected, unweighted graph**...
    -> It uses **Breadth-First Search (BFS)** to compute the shortest path distances efficiently...

Problem Statement:
    -> You are given an undirected graph with `nodes` number of vertices and `edges` number of edges...
    -> The graph is represented as a list of edges in a 2D array `grid`, where each row is a pair [u, v] representing an undirected edge between nodes u and v...
    -> You are also given a source node `src`...
    -> The task is to compute the shortest distance from `src` to every other node in the graph...

Approach:
    -> Step 1: Create an adjacency list representation of the graph from the edge list...
    -> Step 2: Initialize a `distanceFromSrc` array with maximum values to store shortest distances...
    -> Step 3: Use a queue to perform BFS starting from the source node...
        - Set distance of source as 0...
        - For each node visited, update the distance of its unvisited neighbors as `distance[curr] + 1`...
        - Add the neighbor to the queue for further exploration...
    -> Step 4: Return the final `distanceFromSrc` array...

Edge Case Handling:
    -> If a node is unreachable from the source, its distance remains `Integer.MAX_VALUE`...

Time and Space Complexity:
    -> Time Complexity: O(V + E), where V is the number of nodes and E is the number of edges...
    -> Space Complexity: O(V + E), due to adjacency list and queue...

Demonstration:
    -> Test Case:
        Input:
            grid = {{0, 1}, {0, 2}, {1, 2}, {1, 3}, {2, 3}, {3, 4}},
            nodes = 5,
            edges = 6,
            source = 0
        Output:
            Shortest path from 0 to 0 is 0
            Shortest path from 0 to 1 is 1
            Shortest path from 0 to 2 is 1
            Shortest path from 0 to 3 is 2
            Shortest path from 0 to 4 is 3

*/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Shortest_Path_In_Undirected_Graph {

    private static int[] shortestPath(int[][] grid, int nodes, int edges, int src) {

        // Formation of The Graph...
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }

        // Connecting the edges...
        for (int i = 0; i < edges; i++) {
            graph.get(grid[i][0]).add(grid[i][1]);
            graph.get(grid[i][1]).add(grid[i][0]);
        }

        int[] distanceFromSrc = new int[nodes];
        Arrays.fill(distanceFromSrc, Integer.MAX_VALUE);

        Queue<Integer> q = new ArrayDeque<>();
        q.add(src);
        distanceFromSrc[src] = 0;

        while (! q.isEmpty()) {

            int currNode = q.poll();

            for (int adjacent : graph.get(currNode)) {

                if (distanceFromSrc[currNode] + 1 < distanceFromSrc[adjacent]) {
                    distanceFromSrc[adjacent] = distanceFromSrc[currNode] + 1;
                    q.add(adjacent);
                }

            }

        }

        return distanceFromSrc;

    }

    public static void main(String[] args) {

        int[][] grid = {{0, 1}, {0, 2}, {1, 2}, {1, 3}, {2, 3}, {3, 4}};
        int nodes = 5, edges = 6, source = 0;

        System.out.println("Shortest Path from " + source + " to all nodes in the graph is: ");

        int[] shortestPath = shortestPath(grid, nodes, edges, source);

        for (int i = 0; i < shortestPath.length; i++) {
            System.out.println("The shortest path from " + source + " to " + i + " is " + shortestPath[i]);
        }

    }

}

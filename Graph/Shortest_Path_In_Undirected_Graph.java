package Graph;

/*

Description:
    -> This program finds the shortest path from a given source node to all other nodes in an undirected, unweighted graph...
    -> It uses Breadth-First Search (BFS) to efficiently compute the shortest distance from the source node to each other node...

Problem Statement:
    -> You are given an undirected graph represented as an edge list in a 2D array `grid`, where each row [u, v] indicates an undirected edge between node u and node v...
    -> The number of nodes is given by `nodes`, and the number of edges by `edges`...
    -> You are also given a source node `src`...
    -> The goal is to return an array `distanceFromSrc` where each index i represents the shortest distance from `src` to node i...
    -> If a node is not reachable from the source, its distance is returned as -1...

Approach:
    -> Step 1: Build the graph as an adjacency list from the given edge list...
    -> Step 2: Initialize the distance array with Integer.MAX_VALUE to indicate unvisited nodes...
    -> Step 3: Use a queue to perform BFS starting from the source...
        - Set distance of source to 0 and enqueue it...
        - For each node dequeued, update the distance of its neighbors if a shorter path is found...
    -> Step 4: After BFS, update all unreachable node distances (Integer.MAX_VALUE) to -1...

Time and Space Complexity:
    -> Time Complexity: O(V + E), where V = number of nodes and E = number of edges...
    -> Space Complexity: O(V + E), for the adjacency list and distance array...

Demonstration:
    -> Test Case:
        Input:
            grid = {{0, 1}, {0, 2}, {1, 2}, {1, 3}, {2, 3}, {3, 4}}
            nodes = 5
            edges = 6
            source = 0
        Output:
            The shortest path from 0 to 0 is 0
            The shortest path from 0 to 1 is 1
            The shortest path from 0 to 2 is 1
            The shortest path from 0 to 3 is 2
            The shortest path from 0 to 4 is 3

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

        for (int i = 0; i < nodes; i++) {
            if (distanceFromSrc[i] == Integer.MAX_VALUE) {
                distanceFromSrc[i] = - 1;
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

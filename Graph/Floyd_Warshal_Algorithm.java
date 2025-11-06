package Graph;

/*

Description:
    -> This program implements the Floyd-Warshall Algorithm to find the shortest distances between all pairs of vertices in a weighted directed graph...
    -> The algorithm can handle both positive and negative edge weights but cannot handle negative weight cycles...
    -> If a negative cycle is detected, the program displays a message indicating its presence...

Problem Statement:
    Given a graph represented by an adjacency matrix, where each cell dist[i][j] indicates the weight of the edge from node i to node j...
    If there is no direct edge between two nodes, the value is given as -1...
    The task is to compute the shortest distance between every pair of vertices and update the matrix accordingly...

Example:
    Input:
        dist = {
            {0, 1, 4},
            {1, 0, 2},
            {4, 2, 0}
        }...
    Output:
        Following matrix represents the shortest distances between every pair of vertices:
        0 1 3 
        1 0 2 
        3 2 0 ...

Approach:
    1. Replace all -1 values in the adjacency matrix with a large value (representing infinity)...
    2. Set the distance from each vertex to itself as 0...
    3. Iterate through all nodes as intermediate vertices (k)...
        -> For every pair (i, j), update dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])...
    4. After processing, check for negative weight cycles by verifying if dist[i][i] < 0 for any i...
    5. Replace all unreachable paths (infinity) back with -1 for clarity...

Key Observations:
    -> The Floyd-Warshall Algorithm uses dynamic programming to compute all-pairs shortest paths...
    -> It works for both directed and undirected graphs...
    -> The algorithm iteratively improves the path estimates using each vertex as an intermediate point...

Time and Space Complexity:
    -> Time Complexity: O(V³)...
       (Since there are three nested loops over the vertices)...
    -> Space Complexity: O(V²)...
       (Because the algorithm stores the graph in a 2D matrix)...

Example Walkthrough:
    Step 1: Replace -1 with infinity and set diagonal elements to 0...
    Step 2: Use each node as an intermediate vertex to update shortest paths...
    Step 3: Detect negative cycles if dist[i][i] < 0...
    Step 4: Print the final distance matrix representing all-pairs shortest paths...

*/

public class Floyd_Warshal_Algorithm {

    private static void floydWarshall(int[][] dist) {

        int n = dist.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == - 1) {
                    dist[i][j] = (int) 1e8;
                }

                if (i == j) {
                    dist[i][j] = 0;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dist[i][i] < 0) {
                System.out.println("There's a negative cycle in the graph...");
                return;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == (int) 1e8) {
                    dist[i][j] = - 1;
                }
            }
        }

    }

    public static void main(String[] args) {

        int[][] dist = {{0, 1, 4}, {1, 0, 2}, {4, 2, 0}};
        int n = dist.length;

        floydWarshall(dist);
        System.out.println("Following matrix represents the shortest distances between every pair of vertices : ");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == (int) 1e8) {
                    System.out.print("INFINITY ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }

    }

}

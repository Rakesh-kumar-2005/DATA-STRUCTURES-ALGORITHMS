package Graph;

/*
Description:
    This program finds the number of distinct shortest paths from a source node (0)
    to a destination node (n - 1) in a weighted undirected graph.

Problem Statement:
    Given a graph represented by `n` nodes (numbered from 0 to n-1) and a list of roads,
    where each road is represented as {u, v, time}, meaning there is an undirected edge
    between node `u` and node `v` with travel time `time`, find:
        - The number of different paths from node 0 to node n-1
        - Such that each path has the **minimum possible travel time**
    Since the answer can be large, return it modulo 1e9 + 7.

Example:
    Input:
        n = 7
        roads = {
            {0, 6, 7},
            {0, 1, 2},
            {1, 2, 3},
            {1, 3, 3},
            {6, 3, 3},
            {3, 5, 1},
            {6, 5, 1}
        }
    Output:
        The number of ways to arrive at the destination with the minimum cost is : 4

Explanation:
    - The shortest distance from node 0 to node 6 is 7.
    - There are 4 different paths from 0 to 6 that have the same minimum cost.

Approach:
    1. Represent the graph using an adjacency list where each node stores pairs (adjNode, weight).
    2. Use a modified version of **Dijkstra’s Algorithm** to find the shortest paths:
        - `distances[i]` keeps track of the minimum distance to reach node `i`.
        - `ways[i]` keeps count of the number of shortest paths to reach node `i`.
    3. Initialize:
        - `distances[0] = 0`
        - `ways[0] = 1`
    4. Use a priority queue (min-heap) to always process the node with the smallest distance first.
    5. For each adjacent node:
        - If a new shorter path is found, update its distance and set `ways[adjNode] = ways[currNode]`.
        - If another path with the same shortest distance is found, increment `ways[adjNode]` by `ways[currNode]` (mod 1e9 + 7).
    6. After processing all nodes, return `ways[n - 1]`, which represents the total number of shortest paths to reach the destination.

Key Concepts Used:
    - Dijkstra’s Algorithm (for shortest paths)
    - Priority Queue (Min-Heap)
    - Dynamic counting of paths
    - Modular arithmetic to handle large results

Time and Space Complexity:
    Time Complexity: O((V + E) * logV)
        (Each edge is relaxed once and nodes are processed using a priority queue)
    Space Complexity: O(V + E)
        (For storing adjacency lists, distances, and path counts)
*/

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Network_Delay_Time {

    static class Pair {
        int node;
        int distance;

        public Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    private static int networkDelayTime(int[][] times, int n, int src) {

        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int m = times.length;
        for (int i = 0; i < m; i++) {
            graph.get(times[i][0]).add(new Pair(times[i][1], times[i][2]));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y) -> Integer.compare(x.distance, y.distance));
        pq.add(new Pair(src, 0));

        int[] distances = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        distances[src] = 0;

        while (! pq.isEmpty()) {

            Pair currPair = pq.poll();
            int currNode = currPair.node;
            int currDistance = currPair.distance;

            if (currDistance > distances[currNode]) {
                continue;
            }

            for (Pair adjPair : graph.get(currNode)) {

                int adjNode = adjPair.node;
                int edgeWeight = adjPair.distance;
                int newDistance = currDistance + edgeWeight;

                if (newDistance < distances[adjNode]) {
                    distances[adjNode] = newDistance;
                    pq.add(new Pair(adjNode, newDistance));
                }
            }
        }

        int maxDistance = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                return - 1;
            }
            maxDistance = Math.max(maxDistance, distances[i]);
        }

        return maxDistance;
    }

    public static void main(String[] args) {

        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4;
        int src = 2;

        int ans = networkDelayTime(times, n, src);
        System.out.println("The maximum time taken to reach all nodes is: " + ans);

    }
}

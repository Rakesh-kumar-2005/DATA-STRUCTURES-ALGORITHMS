package Graph;

/*

Description:
    -> This program implements Kruskal’s Algorithm to find the Minimum Spanning Tree (MST) of a given undirected weighted graph...
    -> The MST of a graph is a subset of edges that connects all vertices with the minimum possible total edge weight and without forming any cycles...
    -> Kruskal’s algorithm follows a greedy approach by selecting the smallest available edge that does not form a cycle...

Data Structures Used:
    -> A Disjoint Set (Union-Find) structure is used for efficient cycle detection...
    -> It supports:
         1) findParent() → Finds the representative (ultimate parent) of a node using path compression...
         2) unionByRank() → Merges two disjoint sets based on rank to keep the tree shallow...

Working of the Algorithm:
    1. Extract all edges from the adjacency list representation of the graph...
    2. Sort all edges in non-decreasing order based on weight...
    3. Iterate through each edge:
         -> If the edge connects two different sets (i.e., does not form a cycle), include it in the MST...
         -> Use unionByRank() to merge both sets...
    4. Continue until V - 1 edges are added to the MST...

Important Observations:
    -> The graph is represented as a 3D ArrayList where adj[u] contains pairs (v, weight)...
    -> Edges are only added once to avoid duplication in an undirected graph...
    -> Sorting the edges ensures the greedy selection of smallest possible edge at each step...

Time Complexity:
    -> Sorting edges: O(E log E)...
    -> Union-Find operations: Nearly O(1) per operation due to path compression and rank optimization...
    -> Overall complexity: O(E log E), where E is the number of edges...

Space Complexity:
    -> O(V + E) due to storage of adjacency list and edge list...

Example Explanation:
    -> The input graph contains 5 vertices and several weighted edges...
    -> Kruskal’s algorithm selects the smallest edges step by step while ensuring no cycle is formed...
    -> The final output is the total minimum cost required to connect all vertices...

Use Cases:
    -> Network design (minimum wiring cost)...
    -> Road, pipeline, and electrical grid planning...
    -> Clustering algorithms in Machine Learning...

*/

import java.util.ArrayList;
import java.util.Collections;

public class Minimum_Spanning_Tree_Kruskals_Algorithm {

    static class Disjoint {
        ArrayList<Integer> parent = new ArrayList<>();
        ArrayList<Integer> rank = new ArrayList<>();

        public Disjoint(int n) {
            for (int i = 0; i < n; i++) {
                parent.add(i);
                rank.add(0);
            }
        }

        public int findParent(int child) {
            if (child == parent.get(child)) {
                return child;
            }

            int ultimateParent = findParent(parent.get(child));
            parent.set(child, ultimateParent);
            return ultimateParent;
        }

        public void unionByRank(int u, int v) {

            int ulp_u = findParent(u);
            int ulp_v = findParent(v);

            if (ulp_u == ulp_v) {
                return;
            }

            if (rank.get(ulp_u) > rank.get(ulp_v)) {
                parent.set(ulp_v, ulp_u);
            } else if (rank.get(ulp_u) < rank.get(ulp_v)) {
                parent.set(ulp_u, ulp_v);
            } else {
                parent.set(ulp_v, ulp_u);
                rank.set(ulp_u, rank.get(ulp_u) + 1);
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int weight;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.weight = w;
        }

        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }

    private static int minimumSpanningTree(ArrayList<ArrayList<ArrayList<Integer>>> adj, int V) {

        ArrayList<Edge> edges = getEdges(adj, V);
        Collections.sort(edges);
        
        Disjoint ds = new Disjoint(V);
        int minimumCost = 0;

        for (int i = 0; i < edges.size(); i++) {
            Edge currEdge = edges.get(i);
            int u = currEdge.src;
            int v = currEdge.dest;
            int weight = currEdge.weight;

            if (ds.findParent(u) != ds.findParent(v)) {
                ds.unionByRank(u, v);
                minimumCost += weight;
            }
        }

        return minimumCost;
    }

    private static ArrayList<Edge> getEdges(ArrayList<ArrayList<ArrayList<Integer>>> adj, int v) {
        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                
                int adjNode = adj.get(i).get(j).get(0);
                int weight = adj.get(i).get(j).get(1);
                int src = i;

                // Only add edge once (avoid duplicates in undirected graph)
                if (src < adjNode) {
                    Edge temp = new Edge(src, adjNode, weight);
                    edges.add(temp);
                }
                
            }
        }
        
        return edges;
    }

    public static void main(String[] args) {
        
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        int V = 5;

        // FIX: Initialize adjacency list properly
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Edge 0-1 with weight 10
        adj.get(0).add(new ArrayList<>());
        adj.get(0).get(0).add(1);
        adj.get(0).get(0).add(10);

        // Edge 0-2 with weight 20
        adj.get(0).add(new ArrayList<>());
        adj.get(0).get(1).add(2);
        adj.get(0).get(1).add(20);

        // Edge 1-2 with weight 15
        adj.get(1).add(new ArrayList<>());
        adj.get(1).get(0).add(2);
        adj.get(1).get(0).add(15);

        // Edge 1-3 with weight 30
        adj.get(1).add(new ArrayList<>());
        adj.get(1).get(1).add(3);
        adj.get(1).get(1).add(30);

        // Edge 2-3 with weight 25
        adj.get(2).add(new ArrayList<>());
        adj.get(2).get(0).add(3);
        adj.get(2).get(0).add(25);

        // Edge 2-4 with weight 18
        adj.get(2).add(new ArrayList<>());
        adj.get(2).get(1).add(4);
        adj.get(2).get(1).add(18);

        // Edge 3-4 with weight 35
        adj.get(3).add(new ArrayList<>());
        adj.get(3).get(0).add(4);
        adj.get(3).get(0).add(35);

        int ans = minimumSpanningTree(adj, V);
        System.out.println("The cost of the minimum Spanning Tree is " + ans);
    }

}

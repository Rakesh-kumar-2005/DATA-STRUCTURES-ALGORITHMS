package Graph;

/*

Description:
    -> This program determines the total number of provinces in a graph using the Disjoint Set (Union-Find) data structure...
    -> A province is defined as a group of directly or indirectly connected cities, where the adjacency matrix specifies whether two cities are connected...
    -> The problem can be visualized as counting the number of connected components in an undirected graph...

Core Concept:
    -> A Disjoint Set (Union-Find) structure efficiently groups connected nodes and identifies their representative parent...
    -> The algorithm uses:
         1) findParent() → Utilizes path compression to find the ultimate parent of a node...
         2) unionBySize() → Merges two sets, ensuring the larger set becomes the parent to optimize tree height...

Working of the Algorithm:
    1. Initialize a DisjointSet object with each node being its own parent...
    2. Traverse the adjacency matrix:
         -> If connections[i][j] == 1, merge node i and node j using unionBySize()...
    3. After all unions, count how many nodes remain ultimate parents...
         -> Each unique parent represents one province (connected component)...

Explanation of Implementation:
    -> The adjacency matrix is of size n × n, where connections[i][j] = 1 means there is a path between node i and node j...
    -> Because this is an undirected graph, both (i, j) and (j, i) represent the same connection, but union operations safely handle duplicates...
    -> The final count of parents where parent[i] == i gives the number of provinces...

Time Complexity:
    -> Traversing the matrix: O(n²)...
    -> Union-Find operations: Almost O(1) per union due to path compression and union-by-size...
    -> Overall complexity: O(n²)...

Space Complexity:
    -> O(n) for maintaining parent and size arrays...

Use Cases:
    -> Identifying isolated networks in social graphs...
    -> Counting clusters in communication networks...
    -> Determining isolated communities in geographical or administrative datasets...

Example:
    -> For the matrix {{1,1,0},{1,1,0},{0,0,1}}:
         -> Cities 0 and 1 are in one province...
         -> City 2 forms its own province...
         -> Total provinces = 2...

*/

import java.util.ArrayList;

public class Number_Of_Provinces_DisjointSet_Algorithm {

    static class DisjointSet {
        
        ArrayList<Integer> parent = new ArrayList<>();
        ArrayList<Integer> size = new ArrayList<>();

        public DisjointSet(int v) {
            for (int i = 0; i < v; i++) {
                parent.add(i);
                size.add(1);
            }
        }

        public int findParent(int node) {
            if (parent.get(node) == node) {
                return node;
            }

            int ulp = findParent(parent.get(node));
            parent.set(node, ulp);
            return ulp;
        }

        public void unionBySize(int u, int v) {

            int ulp_u = findParent(u);
            int ulp_v = findParent(v);

            if (ulp_u == ulp_v) {
                return;
            }

            if (size.get(ulp_u) > size.get(ulp_v)) {
                parent.set(ulp_v, ulp_u);
                size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
            } else {
                parent.set(ulp_u, ulp_v);
                size.set(ulp_v, size.get(ulp_u) + size.get(ulp_v));
            }

        }


    }

    private static int makeConnected(int n, int[][] connections) {

        DisjointSet ds = new DisjointSet(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (connections[i][j] == 1) {
                    ds.unionBySize(i, j);
                }
            }
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            if (ds.parent.get(i) == i) {
                count++;
            }
        }

        return count;

    }

    public static void main(String[] args) {

        int[][] graph = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int noOfProvinces = makeConnected(3, graph);

        System.out.println("Total Number of Provinces : " + noOfProvinces);

    }

}

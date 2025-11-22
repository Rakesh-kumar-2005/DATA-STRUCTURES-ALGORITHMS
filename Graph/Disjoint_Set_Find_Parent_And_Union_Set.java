package Graph;

/*

Description:
    -> This program implements the Disjoint Set Union (DSU) or Union-Find data structure...
    -> DSU is an efficient data structure used to keep track of elements partitioned into multiple disjoint (non-overlapping) sets...
    -> It supports two main operations: 
         1) Find the ultimate parent (with path compression)... 
         2) Union two sets either by Rank or by Size...
    -> These operations are extremely important in graph algorithms such as Kruskal’s Minimum Spanning Tree, cycle detection, and connected components...

Key Functionalities:
    1. findParent(node):
         -> Uses path compression to directly connect each node to its ultimate parent...
         -> This optimization greatly reduces future lookup time...

    2. unionByRank(u, v):
         -> Attaches the tree with smaller rank under the tree with larger rank...
         -> If both ranks are equal, one becomes the parent and its rank increases by 1...
         -> Helps ensure the resulting tree remains shallow...

    3. unionBySize(u, v):
         -> Attaches the smaller-sized set under the larger-sized set...
         -> Updates the size of the resulting parent set...
         -> Efficient for minimizing tree height...

Working:
    -> Initially, every node is its own parent, making each node a separate set...
    -> The union operations gradually merge sets together based on rank or size...
    -> Path compression during findParent() ensures nearly constant-time lookups...

Example Execution Flow:
    -> For Union By Rank:
         union(0,1), union(1,2), union(2,3) merges the first group...
         union(4,5), union(5,6), union(3,6) finally connects all nodes together...
         All nodes eventually share the same ultimate parent...

    -> For Union By Size:
         A similar merging process happens based on the size of the sets...

Time and Space Complexity:
    -> With path compression and union optimizations, the amortized time complexity is effectively O(α(n)), 
       where α(n) is the inverse Ackermann function, which grows extremely slowly...
    -> Space complexity is O(n) for maintaining parent, rank, and size arrays...

Use Cases:
    -> Cycle detection in graphs...
    -> Kruskal's Minimum Spanning Tree algorithm...
    -> Network connectivity problems...
    -> Clustering and grouping problems...

*/

import java.util.ArrayList;

public class Disjoint_Set_Find_Parent_And_Union_Set {

    static class Disjoint {
        ArrayList<Integer> parent = new ArrayList<>();
        ArrayList<Integer> rank = new ArrayList<>();
        ArrayList<Integer> size = new ArrayList<>();

        public Disjoint(int n) {
            for (int i = 0; i < n; i++) {
                parent.add(i);
                rank.add(0);
                size.add(1);
            }
        }

        public int findParent(int node) {
            if (parent.get(node) == node) {
                return node;
            }

            int ultimateParent = findParent(parent.get(node));
            parent.set(node, ultimateParent);
            return parent.get(node);
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

    public static void main(String[] args) {

        // Union By Rank...
        Disjoint ds = new Disjoint(7);

        ds.unionByRank(0, 1);
        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);

        ds.unionByRank(4, 5);
        ds.unionByRank(5, 6);
        ds.unionByRank(3, 6);

        System.out.println("\n\nUnion By Rank...");

        System.out.println("Ultimate Parent of 1 is " + ds.findParent(1));
        System.out.println("Ultimate Parent of 1 is " + ds.findParent(2));
        System.out.println("Ultimate Parent of 1 is " + ds.findParent(3));

        System.out.println("Ultimate Parent of 1 is " + ds.findParent(4));
        System.out.println("Ultimate Parent of 1 is " + ds.findParent(5));
        System.out.println("Ultimate Parent of 1 is " + ds.findParent(6));

        // Union By Size...
        Disjoint ds2 = new Disjoint(7);

        ds2.unionBySize(1, 0);
        ds2.unionBySize(1, 2);
        ds2.unionBySize(2, 3);

        ds2.unionBySize(4, 5);
        ds2.unionBySize(5, 6);
        ds2.unionBySize(3, 6);

        System.out.println("\n\nUnion By Size...");

        System.out.println("Ultimate Parent of 1 is " + ds2.findParent(1));
        System.out.println("Ultimate Parent of 1 is " + ds2.findParent(2));
        System.out.println("Ultimate Parent of 1 is " + ds2.findParent(3));

        System.out.println("Ultimate Parent of 1 is " + ds2.findParent(4));
        System.out.println("Ultimate Parent of 1 is " + ds2.findParent(5));
        System.out.println("Ultimate Parent of 1 is " + ds2.findParent(6));

    }

}


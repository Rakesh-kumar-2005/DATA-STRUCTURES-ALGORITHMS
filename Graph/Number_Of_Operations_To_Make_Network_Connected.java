package Graph;

/*

Description:
  Following program demonstrates the solution to the "Number Of Operations To Make Network Connected" problem
      using Disjoint Set Union (DSU) with path compression and union by size...

Problem Statement:
  -> You are given 'n' computers labeled from 0 to n-1...
  -> You are also given a list of existing cable connections between computers...
  -> A connected network means all computers can reach each other directly or indirectly...
  -> You must determine the minimum number of operations needed to make the entire network connected...

Approach:
  > Using Disjoint Set Union (Union-Find):
     i. Treat each computer as a node in a graph...
     ii. Apply DSU to group computers into connected components...
     iii. Whenever a connection links two already-connected nodes, it is considered an extra cable...
     iv. Count total components and determine if extra cables can help connect them...

> Extra Cable Logic:
  -> If two computers have the same ultimate parent, the connection forms a cycle...
  -> Such connections are counted as extra edges that can be repurposed...
  -> To connect all components, we need (components - 1) edges...
  -> If extra cables are fewer than needed, the network cannot be fully connected...

> Algorithm Steps:
  -> Initialize DSU with each computer as its own parent...
  -> Traverse all connections:
     * If both nodes belong to the same component:
         - Increment extra cable count...
     * Else:
         - Merge the components using unionBySize...
  -> Count how many components exist by checking parent[i] == i...
  -> Required operations = components - 1...
  -> If extra cables >= required operations, return required operations...
  -> Otherwise, return -1...

> Implementation Note:
  -> Path compression optimizes the find operation by flattening the structure...
  -> Union by size ensures minimal depth and efficient merging...
  -> These optimizations make DSU extremely efficient even for large inputs...

> Example:
  -> For connections {{0,1}, {0,2}, {1,2}} and n = 4:
     * Connection {1,2} is extra since 1 and 2 are already connected...
     * Components formed are: {0,1,2}, {3}...
     * Needed operations = 1...
     * Extra cables = 1, so the network can be connected...

> Edge Cases:
  -> Fully connected network requiring 0 operations...
  -> Networks with insufficient extra cables return -1...
  -> Handles multiple redundant connections properly...

> Time and Space Complexity:
  -> Time Complexity: O(n + m * α(n)), where α(n) is the inverse Ackermann function...
  -> Space Complexity: O(n) for DSU parent and size arrays...

*/

import java.util.ArrayList;

public class Number_Of_Operations_To_Make_Network_Connected {

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
        int countExtraEdges = 0;

        for (int[] connection : connections) {

            int u = connection[0];
            int v = connection[1];

            if (ds.findParent(u) == ds.findParent(v)) {
                countExtraEdges++;
            } else {
                ds.unionBySize(u, v);
            }

        }

        int countComponents = 0;

        for (int i = 0; i < n; i++) {
            if (ds.parent.get(i) == i) {
                countComponents++;
            }
        }

        int ans = countComponents - 1;
        if (countExtraEdges >= ans) {
            return ans;
        }

        return - 1;

    }

    public static void main(String[] args) {

        int[][] connections = {{0, 1}, {0, 2}, {1, 2}};
        int n = 4;

        int ans = makeConnected(n, connections);
        System.out.println("Minimum Number of Operations to make Network Connected : " + ans);

    }

}

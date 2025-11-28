package Graph;

/*

Description:
  Following program demonstrates the solution to the "Most Stones Removed With Same Row Or Column" problem using Disjoint Set Union (DSU) to group stones that share rows or columns...

Problem Statement:
  -> You are given a list of stone coordinates placed on a 2D grid...
  -> A stone can be removed if there exists another stone in the same row or same column...
  -> Stones form connected components through shared rows and columns...
  -> From each connected component, you can remove all stones except one...
  -> The goal is to find the maximum number of stones that can be removed...

Approach:
  > Using Disjoint Set Union (Union-Find):
     i. Treat each distinct row index and column index as separate DSU nodes...
     ii. Map a stone at (row, col) by unioning the row node with the column node...
     iii. While unioning, maintain a set of all unique nodes that appear in input...
     iv. After unions, each connected component corresponds to one stone that must remain...

> Row–Column Node Mapping:
  -> All row indices represent DSU nodes from 0 to maxRow...
  -> All column indices are shifted by (maxRow + 1) to avoid overlap with row nodes...
  -> A stone at (r, c) unions: r ↔ (c + maxRow + 1)...
  -> This ensures rows and columns are placed in separate DSU ranges...

> Algorithm Steps:
  -> Find maximum row and column index to size the DSU structure...
  -> Initialize DSU with (maxRow + maxCol + 2) nodes...
  -> For each stone:
     * Compute row node and shifted column node...
     * Union them to group stones sharing row/column...
     * Add both row and column nodes to a HashSet to track active nodes...
  -> Count number of connected components by checking how many active nodes are their own parent...
  -> Maximum removable stones = total stones − number of components...
  -> Return the computed value...

> Implementation Note:
  -> DSU uses path compression for fast find operations...
  -> Union by size ensures minimal depth and efficient grouping of nodes...
  -> The HashSet ensures only relevant nodes are counted in components (unused DSU nodes are ignored)...
  -> Row/column separation using index shifting is crucial for correct grouping...

> Example:
  -> For stones: {0,0}, {0,1}, {1,0}, {1,1}:
       All belong to one component (share rows/columns)...
       Removable stones = 4 − 1 = 3...
  -> For multiple groups:
       Count components separately, remove stones within each group leaving one per group...

> Edge Cases:
  -> Single stone: cannot remove any (1 stone, 1 component)...
  -> Stones all in same row: form one component → remove all except one...
  -> Completely isolated stones with no shared rows/columns result in 0 removable stones...
  -> Works even when row/column indices are large or irregularly distributed...

> Time and Space Complexity:
  -> Time Complexity: O(n · α(n)), where n is number of stones and α is inverse Ackermann (almost constant)...
  -> Space Complexity: O(maxRow + maxCol) for DSU and active node set...

*/

import java.util.HashSet;

public class Most_Stones_Removed_With_Same_Row_Or_Column {

    static class DisjointSet {
        int[] parent;
        int[] size;

        public DisjointSet(int n) {
            
            parent = new int[n];
            size = new int[n];
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            
        }

        public int findParent(int node) {
            if (parent[node] == node) {
                return node;
            }
            return parent[node] = findParent(parent[node]);
        }

        public void unionBySize(int u, int v) {
            
            int ulp_u = findParent(u);
            int ulp_v = findParent(v);

            if (ulp_u == ulp_v) {
                return;
            }

            if (size[ulp_u] > size[ulp_v]) {
                parent[ulp_v] = ulp_u;
                size[ulp_u] += size[ulp_v];
            } else {
                parent[ulp_u] = ulp_v;
                size[ulp_v] += size[ulp_u];
            }
        }
    }

    private static int removeStones(int[][] stones) {

        int maxRow = 0;
        int maxCol = 0;
        int n = stones.length;

        for (int i = 0; i < n; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }

        DisjointSet ds = new DisjointSet(maxRow + maxCol + 2);
        HashSet<Integer> uniqueNodes = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int row = stones[i][0];
            int col = stones[i][1] + maxRow + 1;

            ds.unionBySize(row, col);
            uniqueNodes.add(row);
            uniqueNodes.add(col);
        }

        int components = 0;
        for (int node : uniqueNodes) {
            if (ds.findParent(node) == node) {
                components++;
            }
        }

        return n - components;
    }

    public static void main(String[] args) {

        System.out.println("=== Test Case 1: Simple 2x2 Grid ===");
        int[][] stones1 = {
            {0, 0}, {0, 1},
            {1, 0}, {1, 1}
        };
        System.out.println("Stones positions: [[0,0], [0,1], [1,0], [1,1]]");
        System.out.println("Visual representation:");

        System.out.println("  0 1");
        System.out.println("0 X X");
        System.out.println("1 X X");

        System.out.println("All stones share rows/columns, forming 1 component");
        System.out.println("Stones removable: " + removeStones(stones1));
        System.out.println("Expected: 3 (can remove all except 1)\n");

        System.out.println("=== Test Case 2: Two Separate Groups ===");
        int[][] stones2 = {
            {0, 0}, {0, 1}, {1, 0},  // Group 1
            {3, 3}, {3, 4}, {4, 3}   // Group 2
        };
        System.out.println("Stones positions: [[0,0], [0,1], [1,0], [3,3], [3,4], [4,3]]");
        System.out.println("Visual representation:");

        System.out.println("  0 1 2 3 4");
        System.out.println("0 X X");
        System.out.println("1 X");
        System.out.println("2");
        System.out.println("3       X X");
        System.out.println("4       X");

        System.out.println("Two disconnected components");
        System.out.println("Stones removable: " + removeStones(stones2));
        System.out.println("Expected: 4 (6 stones - 2 components)\n");

        System.out.println("=== Test Case 3: Single Stone ===");
        int[][] stones3 = {{0, 0}};

        System.out.println("Stones positions: [[0,0]]");
        System.out.println("Only one stone, cannot remove any");
        System.out.println("Stones removable: " + removeStones(stones3));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 4: All in Same Row ===");
        int[][] stones4 = {
            {0, 0}, {0, 1}, {0, 2}, {0, 3}
        };
        System.out.println("Stones positions: [[0,0], [0,1], [0,2], [0,3]]");

        System.out.println("Visual: X X X X (all in row 0)");

        System.out.println("All share the same row, forming 1 component");
        System.out.println("Stones removable: " + removeStones(stones4));
        System.out.println("Expected: 3 (keep only 1)\n");

        System.out.println("=== Test Case 5: Complex Connected Graph ===");
        int[][] stones5 = {
            {0, 0}, {0, 2},      // Row 0
            {1, 1},              // Connects col 1
            {2, 0}, {2, 2},      // Row 2
            {3, 1}               // Connects everything via col 1
        };
        System.out.println("Stones positions: [[0,0], [0,2], [1,1], [2,0], [2,2], [3,1]]");
        System.out.println("Visual representation:");

        System.out.println("  0 1 2");
        System.out.println("0 X   X");
        System.out.println("1   X");
        System.out.println("2 X   X");
        System.out.println("3   X");

        System.out.println("All stones connected through shared rows/columns");
        System.out.println("Stones removable: " + removeStones(stones5));
        System.out.println("Expected: 5 (6 stones - 1 component)\n");

        System.out.println("=== Test Case 6: Three Isolated Stones ===");
        int[][] stones6 = {
            {0, 0},
            {2, 2},
            {4, 4}
        };

        System.out.println("Stones positions: [[0,0], [2,2], [4,4]]");
        System.out.println("Three stones, no shared rows or columns");

        System.out.println("Stones removable: " + removeStones(stones6));
        System.out.println("Expected: 0 (3 components, can't remove any)\n");

    }

}

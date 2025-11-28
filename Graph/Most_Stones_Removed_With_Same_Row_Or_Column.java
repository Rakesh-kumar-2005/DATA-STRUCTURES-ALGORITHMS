package Graph;

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
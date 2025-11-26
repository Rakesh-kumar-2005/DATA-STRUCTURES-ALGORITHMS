package Graph;

/*

Description:
  Following program demonstrates the solution to the "Number Of Distinct Islands" problem using Disjoint Set Union (DSU) to dynamically track the number of islands as coordinates are activated on a grid...

Problem Statement:
  -> You are given an empty n × m grid where cells become land one by one as coordinates are processed...
  -> Each coordinate represents a land cell being added to the grid...
  -> After each activation, you must determine how many distinct islands exist at that moment...
  -> An island is a group of land cells connected 4-directionally (up, down, left, right)...

Approach:
  > Using Disjoint Set Union (Union-Find):
     i. Treat each cell of the n × m grid as a node in a DSU structure...
     ii. When a coordinate is activated, mark it visited and consider it as forming a new island...
     iii. Check all 4 neighboring cells; if any neighbor is already visited, union the two nodes...
     iv. Each successful union reduces the island count because two islands merge...

> DSU Node Mapping:
  -> Each cell (row, col) is mapped to a single DSU index using: index = row * m + col...
  -> This allows DSU to treat the grid as a single linear structure...
  -> Union operations ensure all connected land cells share the same parent...

> Algorithm Steps:
  -> Initialize DSU with n * m nodes...
  -> Maintain a visited matrix to mark activated land cells...
  -> For each coordinate in the input:
     * If already visited, island count remains unchanged; append current count to result...
     * Mark the cell as visited and increment island count...
     * Check 4 neighbors using direction vectors:
         - If neighbor is valid and already visited:
             · Convert both to DSU indices...
             · If their parents differ, union them and decrement island count...
     * Append updated island count to result list...
  -> Return the list containing island count after each activation...

> Implementation Note:
  -> The visited matrix prevents duplicate processing of the same coordinate...
  -> Direction arrays (dr, dc) simplify neighbor traversal...
  -> DSU with path compression and union by size ensures efficient merging of connected land...
  -> Results are stored incrementally to reflect the number of islands after each coordinate...

> Example:
  -> Consider coordinates: {0,0}, {0,1}, {0,2}, {2,2}, {2,3}, {3,2}...
     * First three cells form one island...
     * Next three form another island...
     * Output after each insertion shows dynamic island count (1, 1, 1, 2, 2, 2)...

> Edge Cases:
  -> Duplicate coordinates do not change island count...
  -> Random insertion order still correctly forms islands based on adjacency...
  -> Works for small or large grids since DSU scales efficiently...
  -> Handles merging multiple islands when a single cell connects them...

> Time and Space Complexity:
  -> Time Complexity: O(k * α(n * m)), where k is number of coordinates and α is inverse Ackermann (almost constant)...
  -> Space Complexity: O(n * m) for DSU arrays and visited matrix...

*/

import java.util.ArrayList;

public class Number_Of_Distinct_Islands_II {

    static class Disjoint {
        ArrayList<Integer> parent = new ArrayList<>();
        ArrayList<Integer> size = new ArrayList<>();

        public Disjoint(int n) {
            for (int i = 0; i < n; i++) {
                parent.add(i);
                size.add(1);
            }
        }

        public int findParent(int node) {
            if (parent.get(node) == node) {
                return node;
            }

            int ultimateParent = findParent(parent.get(node));
            parent.set(node, ultimateParent);
            return ultimateParent;
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

    private static boolean isValid(int nRow, int nCol, int n, int m) {
        return nRow >= 0 && nRow < n && nCol >= 0 && nCol < m;
    }

    public static ArrayList<Integer> noOfDistinctIslands(int n, int m, int[][] grid) {
        Disjoint ds = new Disjoint(n * m);
        int[][] visited = new int[n][m];
        ArrayList<Integer> ans = new ArrayList<>();

        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            int row = grid[i][0];
            int col = grid[i][1];

            if (visited[row][col] == 1) {
                ans.add(count);
                continue;
            }

            visited[row][col] = 1;
            count++;

            int[] dr = {- 1, 0, 1, 0};
            int[] dc = {0, 1, 0, - 1};

            for (int j = 0; j < 4; j++) {
                int nRow = row + dr[j];
                int nCol = col + dc[j];

                if (isValid(nRow, nCol, n, m) && visited[nRow][nCol] == 1) {
                    int nodeNo = row * m + col;
                    int adjNodeNo = nRow * m + nCol;

                    if (ds.findParent(nodeNo) != ds.findParent(adjNodeNo)) {
                        count--;
                        ds.unionBySize(nodeNo, adjNodeNo);
                    }
                }
            }

            ans.add(count);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println("=== Test Case 1: Two Separate Islands ===");
        int[][] coordinates1 = {
            {0, 0}, {0, 1}, {0, 2},  // Island 1 (horizontal line)
            {2, 2}, {2, 3}, {3, 2}   // Island 2 (L-shape)
        };
        ArrayList<Integer> result1 = noOfDistinctIslands(4, 4, coordinates1);
        System.out.println("Coordinates processed: ");
        for (int i = 0; i < coordinates1.length; i++) {
            System.out.println("  [" + coordinates1[i][0] + "," + coordinates1[i][1] + "] -> Islands: " + result1.get(i));
        }
        System.out.println("Final island count: " + result1.get(result1.size() - 1));
        System.out.println();

        System.out.println("=== Test Case 2: Single Connected Island (given in random order) ===");
        int[][] coordinates2 = {
            {1, 1}, {0, 1}, {1, 0}, {1, 2}, {2, 1}  // All connected in a + shape
        };
        ArrayList<Integer> result2 = noOfDistinctIslands(3, 3, coordinates2);
        System.out.println("Coordinates processed: ");
        for (int i = 0; i < coordinates2.length; i++) {
            System.out.println("  [" + coordinates2[i][0] + "," + coordinates2[i][1] + "] -> Islands: " + result2.get(i));
        }
        System.out.println("Final island count: " + result2.get(result2.size() - 1));
        System.out.println();

        System.out.println("=== Test Case 3: Three Islands, One Gets Connected Later ===");
        int[][] coordinates3 = {
            {0, 0},           // Island 1
            {0, 2},           // Island 2
            {2, 0}, {2, 1},   // Island 3
            {0, 1}            // Connects Island 1 and 2!
        };

        ArrayList<Integer> result3 = noOfDistinctIslands(3, 3, coordinates3);
        System.out.println("Coordinates processed: ");

        for (int i = 0; i < coordinates3.length; i++) {
            System.out.println("  [" + coordinates3[i][0] + "," + coordinates3[i][1] + "] -> Islands: " + result3.get(i));
        }

        System.out.println("Final island count: " + result3.get(result3.size() - 1));
        System.out.println("\n=== Test Case 4: Duplicate Coordinates ===");

        int[][] coordinates4 = {
            {0, 0}, {0, 1}, {0, 0}, {1, 1}  // {0,0} appears twice
        };

        ArrayList<Integer> result4 = noOfDistinctIslands(2, 2, coordinates4);
        System.out.println("Coordinates processed: ");

        for (int i = 0; i < coordinates4.length; i++) {
            System.out.println("  [" + coordinates4[i][0] + "," + coordinates4[i][1] + "] -> Islands: " + result4.get(i));
        }

        System.out.println("Final island count: " + result4.get(result4.size() - 1));

    }

}

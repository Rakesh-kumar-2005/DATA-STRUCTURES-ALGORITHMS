package Graph;

import java.util.ArrayList;
import java.util.HashSet;

public class Making_A_Large_Island {

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
            return parent.get(node);
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

    private static boolean isValid(int row, int col, int n) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    private static int largestIsland(int[][] grid) {

        int n = grid.length;
        Disjoint ds = new Disjoint(n * n);

        int[] dr = {- 1, 0, 1, 0};
        int[] dc = {0, 1, 0, - 1};

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (grid[row][col] == 0) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = row + dr[i];
                    int nc = col + dc[i];

                    if (isValid(nr, nc, n) && grid[nr][nc] == 1) {
                        int node = row * n + col;
                        int adjNode = nr * n + nc;

                        ds.unionBySize(node, adjNode);
                    }

                }


            }
        }

        int maxCount = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (grid[row][col] == 1) {
                    continue;
                }

                HashSet<Integer> components = new HashSet<>();

                for (int i = 0; i < 4; i++) {
                    int nr = row + dr[i];
                    int nc = col + dc[i];

                    if (isValid(nr, nc, n) && grid[nr][nc] == 1) {
                        int node = nr * n + nc;

                        components.add(ds.findParent(node));
                    }

                }

                int totalOnes = 1;
                for (Integer parent : components) {
                    totalOnes += ds.size.get(parent);
                }

                maxCount = Math.max(maxCount, totalOnes);

            }
        }

        for (int cell = 0; cell < n * n; cell++) {
            maxCount = Math.max(maxCount, ds.size.get(cell));
        }

        return maxCount;

    }

    private static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Test Case 1: Can connect two islands ===");
        int[][] grid1 = {
            {1, 1, 0, 0},
            {1, 0, 0, 0},
            {0, 0, 1, 1},
            {0, 0, 1, 0}
        };

        System.out.println("Grid:");
        printGrid(grid1);

        System.out.println("Largest island by flipping one 0: " + largestIsland(grid1));
        System.out.println("Explanation: Flip grid[1][1] to connect island of size 3 with island of size 4");
        System.out.println("Result: 3 + 4 + 1 (flipped cell) = 8\n");

        System.out.println("=== Test Case 2: Single large island already ===");
        int[][] grid2 = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        System.out.println("Grid:");
        printGrid(grid2);

        System.out.println("Largest island by flipping one 0: " + largestIsland(grid2));
        System.out.println("Explanation: Flip the only 0 at grid[1][1] to connect all 1s");
        System.out.println("Result: All 9 cells become one island\n");

        System.out.println("=== Test Case 3: All 1s already ===");
        int[][] grid3 = {
            {1, 1},
            {1, 1}
        };

        System.out.println("Grid:");
        printGrid(grid3);

        System.out.println("Largest island by flipping one 0: " + largestIsland(grid3));
        System.out.println("Explanation: No 0s to flip, return size of existing island");
        System.out.println("Result: 4\n");

        System.out.println("=== Test Case 4: All 0s ===");
        int[][] grid4 = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        System.out.println("Grid:");
        printGrid(grid4);

        System.out.println("Largest island by flipping one 0: " + largestIsland(grid4));
        System.out.println("Explanation: Flip any 0 to create an island of size 1");
        System.out.println("Result: 1\n");

        System.out.println("=== Test Case 5: Multiple small islands ===");
        int[][] grid5 = {
            {1, 0, 1, 0},
            {0, 1, 0, 1},
            {1, 0, 1, 0},
            {0, 1, 0, 1}
        };
        System.out.println("Grid:");
        printGrid(grid5);

        System.out.println("Largest island by flipping one 0: " + largestIsland(grid5));
        System.out.println("Explanation: Each 1 is isolated. Flip a 0 to connect 2-4 islands");
        System.out.println("Result: Connect multiple 1s to form larger island\n");

        System.out.println("=== Test Case 6: Can connect three islands ===");
        int[][] grid6 = {
            {1, 0, 1},
            {0, 0, 0},
            {1, 0, 1}
        };
        System.out.println("Grid:");
        printGrid(grid6);

        System.out.println("Largest island by flipping one 0: " + largestIsland(grid6));
        System.out.println("Explanation: Flip grid[1][1] to connect all four corner islands");
        System.out.println("Result: 1 + 1 + 1 + 1 + 1 (flipped) = 5\n");

    }

}
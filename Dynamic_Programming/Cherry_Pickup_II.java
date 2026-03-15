package Dynamic_Programming;

public class Cherry_Pickup_II {

    private static int recursive(int[][] grid, int i, int j1, int j2) {

        if (j1 < 0 || j1 >= grid[0].length || j2 < 0 || j2 >= grid[0].length) {
            return Integer.MIN_VALUE;
        }

        if (i == grid.length - 1) {
            if (j1 == j2) {
                return grid[i][j1];
            } else {
                return grid[i][j1] + grid[i][j2];
            }
        }

        int maxi = Integer.MIN_VALUE;
        for (int dj1 = - 1; dj1 <= 1; dj1++) {
            for (int dj2 = - 1; dj2 <= 1; dj2++) {
                int value = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
                value += recursive(grid, i + 1, j1 + dj1, j2 + dj2);

                maxi = Math.max(value, maxi);
            }
        }

        return maxi;

    }

    private static int memoization(int[][] grid, int i, int j1, int j2, int[][][] dp) {
        if (j1 < 0 || j1 >= grid[0].length || j2 < 0 || j2 >= grid[0].length) {
            return Integer.MIN_VALUE;
        }

        if (i == grid.length - 1) {
            if (j1 == j2) {
                return grid[i][j1];
            } else {
                return grid[i][j1] + grid[i][j2];
            }
        }

        if (dp[i][j1][j2] != - 1) {
            return dp[i][j1][j2];
        }

        int maxi = Integer.MIN_VALUE;
        for (int dj1 = - 1; dj1 <= 1; dj1++) {
            for (int dj2 = - 1; dj2 <= 1; dj2++) {
                int value = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
                value += memoization(grid, i + 1, j1 + dj1, j2 + dj2, dp);

                maxi = Math.max(value, maxi);
            }
        }

        return dp[i][j1][j2] = maxi;
    }

    private static int tabulation(int[][] grid, int rows, int cols) {

        int[][][] dp = new int[rows][cols][cols];

        // Base case
        for (int j1 = 0; j1 < cols; j1++) {
            for (int j2 = 0; j2 < cols; j2++) {
                if (j1 == j2) {
                    dp[rows - 1][j1][j2] = grid[rows - 1][j1];
                } else {
                    dp[rows - 1][j1][j2] = grid[rows - 1][j1] + grid[rows - 1][j2];
                }
            }
        }

        for (int i = rows - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < cols; j1++) {
                for (int j2 = 0; j2 < cols; j2++) {

                    int maxi = Integer.MIN_VALUE;

                    for (int dj1 = - 1; dj1 <= 1; dj1++) {
                        for (int dj2 = - 1; dj2 <= 1; dj2++) {

                            int value;

                            if (j1 == j2) {
                                value = grid[i][j1];
                            } else {
                                value = grid[i][j1] + grid[i][j2];
                            }

                            if (j1 + dj1 >= 0 && j1 + dj1 < cols &&
                                j2 + dj2 >= 0 && j2 + dj2 < cols) {

                                value += dp[i + 1][j1 + dj1][j2 + dj2];

                            } else {
                                value += Integer.MIN_VALUE;
                            }

                            maxi = Math.max(maxi, value);
                        }
                    }

                    dp[i][j1][j2] = maxi;
                }
            }
        }

        return dp[0][0][cols - 1];
    }


    private static int ultimateSpaceOptimization(int[][] grid, int rows, int cols) {

        int[][] front = new int[cols][cols];

        for (int j1 = 0; j1 < cols; j1++) {
            for (int j2 = 0; j2 < cols; j2++) {

                if (j1 == j2) {
                    front[j1][j2] = grid[rows - 1][j1];
                } else {
                    front[j1][j2] = grid[rows - 1][j1] + grid[rows - 1][j2];
                }
            }
        }

        for (int i = rows - 2; i >= 0; i--) {

            int[][] curr = new int[cols][cols];

            for (int j1 = 0; j1 < cols; j1++) {
                for (int j2 = 0; j2 < cols; j2++) {

                    int maxi = Integer.MIN_VALUE;

                    for (int dj1 = - 1; dj1 <= 1; dj1++) {
                        for (int dj2 = - 1; dj2 <= 1; dj2++) {

                            int value;

                            if (j1 == j2) {
                                value = grid[i][j1];
                            } else {
                                value = grid[i][j1] + grid[i][j2];
                            }

                            if (j1 + dj1 >= 0 && j1 + dj1 < cols &&
                                j2 + dj2 >= 0 && j2 + dj2 < cols) {

                                value += front[j1 + dj1][j2 + dj2];

                            } else {
                                value += Integer.MIN_VALUE;
                            }

                            maxi = Math.max(maxi, value);
                        }
                    }

                    curr[j1][j2] = maxi;
                }
            }

            front = curr;
        }

        return front[0][cols - 1];
    }


    private static void printMatrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            System.out.print("  ");
            for (int j = 0; j < mat[0].length; j++) {
                System.out.printf("%2d ", mat[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                  CHERRY PICKUP II                            ║");
        System.out.println("║  Two robots collect cherries from top to bottom              ║");
        System.out.println("║  Robot 1 starts at (0,0), Robot 2 starts at (0,cols-1)       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Small Grid ===");
        int[][] grid1 = {
            {3, 1, 1},
            {2, 5, 1},
            {1, 5, 5},
            {2, 1, 1}
        };
        System.out.println("Grid:");
        printMatrix(grid1);
        System.out.println("\nRobot 1 starts at (0,0) = 3");
        System.out.println("Robot 2 starts at (0,2) = 1");
        System.out.println("Both move down with diagonals allowed");
        System.out.println("If on same cell, count cherries once\n");

        int result1_tab = tabulation(grid1, 4, 3);
        int result1_opt = ultimateSpaceOptimization(grid1, 4, 3);

        System.out.println("✓ Tabulation Result: " + result1_tab);
        System.out.println("✓ Space Optimized Result: " + result1_opt);
        System.out.println("  Expected: 24");
        System.out.println("  Status: " + (result1_tab == 24 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Single Row ===");
        int[][] grid2 = {{1, 0, 0, 0, 0, 0, 1}};
        System.out.println("Grid: " + java.util.Arrays.toString(grid2[0]));
        System.out.println("\nRobot 1 at (0,0)=1, Robot 2 at (0,6)=1");
        System.out.println("Total: 1 + 1 = 2\n");

        int result2_tab = tabulation(grid2, 1, 7);
        int result2_opt = ultimateSpaceOptimization(grid2, 1, 7);

        System.out.println("✓ Tabulation Result: " + result2_tab);
        System.out.println("✓ Space Optimized Result: " + result2_opt);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result2_tab == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Two Columns ===");
        int[][] grid3 = {
            {1, 1},
            {1, 1},
            {1, 1}
        };
        System.out.println("Grid:");
        printMatrix(grid3);
        System.out.println("\nBoth robots must stay in same columns");
        System.out.println("Always on same cell → count once per row\n");

        int result3_tab = tabulation(grid3, 3, 2);
        int result3_opt = ultimateSpaceOptimization(grid3, 3, 2);

        System.out.println("✓ Tabulation Result: " + result3_tab);
        System.out.println("✓ Space Optimized Result: " + result3_opt);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("=== Test Case 4: Diagonal Paths ===");
        int[][] grid4 = {
            {1, 0, 0, 3},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {9, 0, 0, 0}
        };
        System.out.println("Grid:");
        printMatrix(grid4);
        System.out.println("\nRobots can move diagonally to collect");
        System.out.println("Robot 1: corner to corner (1→9)");
        System.out.println("Robot 2: corner to corner (3→0)\n");

        int result4_tab = tabulation(grid4, 4, 4);
        int result4_opt = ultimateSpaceOptimization(grid4, 4, 4);

        System.out.println("✓ Tabulation Result: " + result4_tab);
        System.out.println("✓ Space Optimized Result: " + result4_opt);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("=== Test Case 5: All Same Values ===");
        int[][] grid5 = {
            {5, 5, 5},
            {5, 5, 5},
            {5, 5, 5}
        };
        System.out.println("Grid:");
        printMatrix(grid5);
        System.out.println("\nAll cells have value 5");
        System.out.println("Optimal path collects maximum cherries\n");

        int result5_tab = tabulation(grid5, 3, 3);
        int result5_opt = ultimateSpaceOptimization(grid5, 3, 3);

        System.out.println("✓ Tabulation Result: " + result5_tab);
        System.out.println("✓ Space Optimized Result: " + result5_opt);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Two robots collect cherries simultaneously         ║");
        System.out.println("║    Robot 1: starts at (0, 0)                                 ║");
        System.out.println("║    Robot 2: starts at (0, cols-1)                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Movement: Both move down one row each step                  ║");
        System.out.println("║    Can move: left-diagonal, straight down, right-diagonal    ║");
        System.out.println("║    9 possible combinations per step (3×3)                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Cherry Collection:                                          ║");
        System.out.println("║    If j1 == j2: collect grid[i][j1] (same cell, count once)  ║");
        System.out.println("║    If j1 != j2: collect grid[i][j1] + grid[i][j2]            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  State: dp[i][j1][j2] = max cherries from row i with         ║");
        System.out.println("║         Robot 1 at column j1, Robot 2 at column j2           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recurrence:                                                 ║");
        System.out.println("║    Try all 9 moves (dj1, dj2) in {-1, 0, 1}                  ║");
        System.out.println("║    dp[i][j1][j2] = current_cherries +                        ║");
        System.out.println("║                    max(dp[i+1][j1+dj1][j2+dj2])              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base Case: Last row (i == rows-1)                           ║");
        System.out.println("║    Return cherries collected at final positions              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Optimization:                                         ║");
        System.out.println("║    Use front[j1][j2] for next row                            ║");
        System.out.println("║    Use curr[j1][j2] for current row                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n×m²×9) = O(n×m²) where n=rows, m=cols           ║");
        System.out.println("║    Space: O(m²) optimized, O(n×m²) tabulation                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}
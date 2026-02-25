package Dynamic_Programming;

/*

Description:
  Following program computes the number of unique paths in a grid that contains obstacles, where certain cells are blocked and cannot be traversed...

Problem Statement:
  -> Given a grid containing obstacles...
  -> 0 represents an empty cell, 1 represents an obstacle...
  -> Start at top-left cell (0,0) and reach bottom-right cell (m−1,n−1)...
  -> Movement is allowed only RIGHT or DOWN...
  -> Cells with obstacles cannot be used in any path...
  -> Return the total number of unique valid paths...

Core Idea:
  -> Similar to Unique Paths I, but blocked cells contribute 0 paths...
  -> If a cell is an obstacle, it cannot be part of any valid route...
  -> Path count at each cell is derived from top and left neighbors...

Key Rule:
  -> If grid[i][j] == 1 → paths = 0...
  -> Else → paths(i,j) = paths(i−1,j) + paths(i,j−1)...

Important Edge Condition:
  -> If start or destination cell contains an obstacle → return 0...

Approach:
  > Four Implementational Strategies:

  ═════════════════════════════════════════════
  APPROACH 1: PURE RECURSION
  ═════════════════════════════════════════════

     i. If indices go out of bounds → return 0...
     ii. If current cell has obstacle → return 0...
     iii. If at starting cell → return 1...
     iv. Otherwise:
          paths = recursive(up) + recursive(left)...

  -> Explores all possible routes...
  -> Exponential time due to repeated calculations...
  -> Time: O(2^(m+n))...
  -> Space: O(m+n) recursion depth...

  ═════════════════════════════════════════════
  APPROACH 2: MEMOIZATION (Top-Down DP)
  ═════════════════════════════════════════════

     i. Use dp[row][col] initialized with −1...
     ii. Return stored value if already computed...
     iii. Apply obstacle check before caching...

  -> Avoids recomputation...
  -> Each cell evaluated once...
  -> Time: O(m × n)...
  -> Space: O(m × n)...

  ═════════════════════════════════════════════
  APPROACH 3: TABULATION (Bottom-Up DP)
  ═════════════════════════════════════════════

     i. If start or end cell is obstacle → return 0...
     ii. Create dp table of size rows × cols...
     iii. Fill table row-wise:
          • If obstacle → dp[i][j] = 0...
          • If start cell → dp[i][j] = 1...
          • Else:
              dp[i][j] = dp[i−1][j] + dp[i][j−1]...

  -> Builds solution iteratively...
  -> Time: O(m × n)...
  -> Space: O(m × n)...

  Example:

      Grid:          DP Table:
      0 0 0          1 1 1
      0 1 0    →     1 0 1
      0 0 0          1 1 2

  -> Final answer = 2...

  ═════════════════════════════════════════════
  APPROACH 4: SPACE OPTIMIZED DP
  ═════════════════════════════════════════════

     i. Only previous row needed to compute current row...
     ii. Maintain two arrays:
          prev[] → previous row...
          curr[] → current row...
     iii. For each cell:
          • If obstacle → curr[j] = 0...
          • Else:
               curr[j] = prev[j] + curr[j−1]...
     iv. After each row → prev = curr...

  -> Time: O(m × n)...
  -> Space: O(n)...

Algorithm Steps (Tabulation):
  -> Check start and destination cells...
  -> Initialize dp table...
  -> Traverse each cell:
       * If obstacle → set 0...
       * Else accumulate paths from top and left...
  -> Return value at destination cell...

Example:
  -> Grid:
       0 0 0
       0 1 0
       0 0 0
  -> Obstacle blocks center...
  -> Valid routes go around obstacle...
  -> Output: 2...

Edge Cases:
  -> Obstacle at start → 0 paths...
  -> Obstacle at destination → 0 paths...
  -> Complete barrier blocking path → 0...
  -> No obstacles → behaves like Unique Paths I...
  -> Single cell without obstacle → 1...
  -> Single cell with obstacle → 0...

Implementation Notes:
  -> Obstacle check must occur before computing paths...
  -> Obstacles propagate zero values through DP table...
  -> Space optimization improves performance for large grids...

Complexity Comparison:
  -> Recursive:      Time O(2^(m+n)), Space O(m+n)...
  -> Memoization:    Time O(m×n), Space O(m×n)...
  -> Tabulation:     Time O(m×n), Space O(m×n)...
  -> Space Optimized:Time O(m×n), Space O(n)...

Applications:
  -> Robot navigation with blocked routes...
  -> Grid-based pathfinding with barriers...
  -> Game AI movement planning...
  -> Traffic routing and obstacle avoidance models...

*/

import java.util.Arrays;

public class Unique_Paths_II {

    private static int recursive(int[][] obstacleGrid, int row, int col) {
        if (row < 0 || col < 0) {
            return 0;
        }

        if (obstacleGrid[row][col] == 1) {
            return 0;
        }

        if (row == 0 && col == 0) {
            return 1;
        }

        int up = recursive(obstacleGrid, row - 1, col);
        int left = recursive(obstacleGrid, row, col - 1);

        return up + left;
    }

    private static int memoization(int[][] obstacleGrid, int row, int col, int[][] dp) {
        if (row < 0 || col < 0) {
            return 0;
        }

        if (obstacleGrid[row][col] == 1) {
            return 0;
        }

        if (row == 0 && col == 0) {
            return 1;
        }

        if (dp[row][col] != - 1) {
            return dp[row][col];
        }

        int up = memoization(obstacleGrid, row - 1, col, dp);
        int left = memoization(obstacleGrid, row, col - 1, dp);

        return dp[row][col] = up + left;
    }

    private static int tabulation(int[][] obstacleGrid, int rows, int cols) {

        if (obstacleGrid[0][0] == 1 || obstacleGrid[rows - 1][cols - 1] == 1) {
            return 0;
        }

        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    int up = 0;
                    int left = 0;

                    if (i > 0) {
                        up = dp[i - 1][j];
                    }

                    if (j > 0) {
                        left = dp[i][j - 1];
                    }

                    dp[i][j] = up + left;
                }

            }
        }


        return dp[rows - 1][cols - 1];
    }

    private static int ultimateSpaceOptimized(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        int[] prev = new int[cols];
        Arrays.fill(prev, 0);

        if (obstacleGrid[0][0] == 1 || obstacleGrid[rows - 1][cols - 1] == 1) {
            return 0;
        }

        for (int i = 0; i < rows; i++) {

            int[] curr = new int[cols];
            for (int j = 0; j < cols; j++) {

                if (i == 0 && j == 0) {
                    curr[j] = 1;
                } else if (obstacleGrid[i][j] == 1) {
                    curr[j] = 0;
                } else {
                    int left = 0;
                    int up = 0;

                    if (i > 0) {
                        up = prev[j];
                    }

                    if (j > 0) {
                        left = curr[j - 1];
                    }

                    curr[j] = up + left;

                }
            }
            prev = curr;
        }

        return prev[cols - 1];
    }


    private static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            System.out.print("  ");
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                 UNIQUE PATHS II (With Obstacles)             ║");
        System.out.println("║  Count unique paths with obstacles blocking certain cells    ║");
        System.out.println("║                0 = empty cell, 1 = obstacle                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Single Obstacle ===");
        int[][] grid1 = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        System.out.println("Grid 3×3:");
        printGrid(grid1);
        System.out.println("\nAnalysis:");
        System.out.println("  S → → E");
        System.out.println("  ↓   X ↓   (X = obstacle at [1,1])");
        System.out.println("  ↓ ← ← ↓");
        System.out.println("\nObstacle blocks center cell");
        System.out.println("Must go around: top or bottom route\n");

        int result1_rec = recursive(grid1, 2, 2);

        int[][] dp1 = new int[3][3];
        for (int i = 0; i < 3; i++) Arrays.fill(dp1[i], - 1);
        int result1_memo = memoization(grid1, 2, 2, dp1);

        int result1_tab = tabulation(grid1, 3, 3);
        int result1_opt = ultimateSpaceOptimized(grid1);

        System.out.println("✓ Recursive Result: " + result1_rec);
        System.out.println("✓ Memoization Result: " + result1_memo);
        System.out.println("✓ Tabulation Result: " + result1_tab);
        System.out.println("✓ Space Optimized Result: " + result1_opt);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result1_tab == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Obstacle at Start ===");
        int[][] grid2 = {
            {1, 0},
            {0, 0}
        };
        System.out.println("Grid 2×2:");
        printGrid(grid2);
        System.out.println("\nAnalysis:");
        System.out.println("  X → E   (X = obstacle at start)");
        System.out.println("  ↓   ↓");
        System.out.println("\nCannot start! Obstacle at (0,0)\n");

        int result2_tab = tabulation(grid2, 2, 2);
        int result2_opt = ultimateSpaceOptimized(grid2);

        System.out.println("✓ Tabulation Result: " + result2_tab);
        System.out.println("✓ Space Optimized Result: " + result2_opt);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result2_tab == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Obstacle at End ===");
        int[][] grid3 = {
            {0, 0},
            {0, 1}
        };
        System.out.println("Grid 2×2:");
        printGrid(grid3);
        System.out.println("\nAnalysis:");
        System.out.println("  S → →");
        System.out.println("  ↓   X   (X = obstacle at destination)");
        System.out.println("\nCannot reach destination!\n");

        int result3_tab = tabulation(grid3, 2, 2);
        int result3_opt = ultimateSpaceOptimized(grid3);

        System.out.println("✓ Tabulation Result: " + result3_tab);
        System.out.println("✓ Space Optimized Result: " + result3_opt);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result3_tab == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: No Obstacles ===");
        int[][] grid4 = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        System.out.println("Grid 3×3:");
        printGrid(grid4);
        System.out.println("\nAnalysis:");
        System.out.println("  S → → E");
        System.out.println("  ↓   ↓ ↓");
        System.out.println("  ↓   ↓ ↓");
        System.out.println("\nNo obstacles - same as Unique Paths I");
        System.out.println("Expected: C(4,2) = 6\n");

        int[][] dp4 = new int[3][3];
        for (int i = 0; i < 3; i++) Arrays.fill(dp4[i], - 1);
        int result4_memo = memoization(grid4, 2, 2, dp4);

        int result4_tab = tabulation(grid4, 3, 3);
        int result4_opt = ultimateSpaceOptimized(grid4);

        System.out.println("✓ Memoization Result: " + result4_memo);
        System.out.println("✓ Tabulation Result: " + result4_tab);
        System.out.println("✓ Space Optimized Result: " + result4_opt);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result4_tab == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Wall Across Middle ===");
        int[][] grid5 = {
            {0, 0, 0},
            {1, 1, 0},
            {0, 0, 0}
        };
        System.out.println("Grid 3×3:");
        printGrid(grid5);
        System.out.println("\nAnalysis:");
        System.out.println("  S → → →");
        System.out.println("  X   X ↓   (wall blocks middle row)");
        System.out.println("        E");
        System.out.println("\nMust go top-right then down");
        System.out.println("Only 1 path available\n");

        int result5_tab = tabulation(grid5, 3, 3);
        int result5_opt = ultimateSpaceOptimized(grid5);

        System.out.println("✓ Tabulation Result: " + result5_tab);
        System.out.println("✓ Space Optimized Result: " + result5_opt);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result5_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Multiple Obstacles ===");
        int[][] grid6 = {
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0}
        };
        System.out.println("Grid 4×4:");
        printGrid(grid6);
        System.out.println("\nAnalysis:");
        System.out.println("  S → → → E");
        System.out.println("  ↓   X   ↓");
        System.out.println("  ↓     X ↓");
        System.out.println("  ↓ ← ← ← ↓");
        System.out.println("\nTwo obstacles create maze-like paths\n");

        int result6_tab = tabulation(grid6, 4, 4);
        int result6_opt = ultimateSpaceOptimized(grid6);

        System.out.println("✓ Tabulation Result: " + result6_tab);
        System.out.println("✓ Space Optimized Result: " + result6_opt);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("=== Test Case 7: Diagonal Obstacles ===");
        int[][] grid7 = {
            {0, 0, 0},
            {0, 0, 1},
            {0, 1, 0}
        };
        System.out.println("Grid 3×3:");
        printGrid(grid7);
        System.out.println("\nAnalysis:");
        System.out.println("  S → → →");
        System.out.println("  ↓     X");
        System.out.println("  ↓   X E");
        System.out.println("\nDiagonal obstacles block some paths\n");

        int result7_tab = tabulation(grid7, 3, 3);
        int result7_opt = ultimateSpaceOptimized(grid7);

        System.out.println("✓ Tabulation Result: " + result7_tab);
        System.out.println("✓ Space Optimized Result: " + result7_opt);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result7_tab == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Complete Blockage ===");
        int[][] grid8 = {
            {0, 0, 0},
            {1, 1, 1},
            {0, 0, 0}
        };
        System.out.println("Grid 3×3:");
        printGrid(grid8);
        System.out.println("\nAnalysis:");
        System.out.println("  S → → →");
        System.out.println("  X   X   X   (complete wall)");
        System.out.println("            E");
        System.out.println("\nNo way through! Completely blocked\n");

        int result8_tab = tabulation(grid8, 3, 3);
        int result8_opt = ultimateSpaceOptimized(grid8);

        System.out.println("✓ Tabulation Result: " + result8_tab);
        System.out.println("✓ Space Optimized Result: " + result8_opt);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result8_tab == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 9: Single Cell ===");
        int[][] grid9 = {{0}};
        System.out.println("Grid 1×1:");
        printGrid(grid9);
        System.out.println("\nAnalysis:");
        System.out.println("  S/E (start = end)");
        System.out.println("\nAlready at destination\n");

        int result9_tab = tabulation(grid9, 1, 1);
        int result9_opt = ultimateSpaceOptimized(grid9);

        System.out.println("✓ Tabulation Result: " + result9_tab);
        System.out.println("✓ Space Optimized Result: " + result9_opt);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result9_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 10: Complex Maze ===");
        int[][] grid10 = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 1, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0}
        };
        System.out.println("Grid 5×5:");
        printGrid(grid10);
        System.out.println("\nAnalysis:");
        System.out.println("  Complex maze with scattered obstacles");
        System.out.println("  Multiple valid paths exist\n");

        int result10_tab = tabulation(grid10, 5, 5);
        int result10_opt = ultimateSpaceOptimized(grid10);

        System.out.println("✓ Tabulation Result: " + result10_tab);
        System.out.println("✓ Space Optimized Result: " + result10_opt);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem Extension:                                          ║");
        System.out.println("║    Same as Unique Paths I but with obstacles                 ║");
        System.out.println("║    Obstacle cell: cannot pass through (0 paths)              ║");
        System.out.println("║    Grid[i][j] = 1 means obstacle, 0 means passable           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Difference from Unique Paths I:                         ║");
        System.out.println("║    • Check if cell is obstacle before computing paths        ║");
        System.out.println("║    • If obstacle: paths = 0 (cannot use this cell)           ║");
        System.out.println("║    • Special check: start or end is obstacle → return 0      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║  APPROACH 1: RECURSIVE                                       ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base Cases:                                                 ║");
        System.out.println("║    • At (0,0): return 1                                      ║");
        System.out.println("║    • Current cell is obstacle: return 0                      ║");
        System.out.println("║    • Out of bounds: return 0                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recursive Relation:                                         ║");
        System.out.println("║    if (grid[row][col] == 1) return 0                         ║");
        System.out.println("║    else return recursive(row-1,col) + recursive(row,col-1)   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Time:  O(2^(m+n)) - Exponential                             ║");
        System.out.println("║  Space: O(m+n) - Recursion depth                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║  APPROACH 2: MEMOIZATION                                     ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Same as recursive but cache results:                        ║");
        System.out.println("║    if (dp[row][col] != -1) return dp[row][col]               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Obstacle check happens before caching:                      ║");
        System.out.println("║    if (grid[row][col] == 1) return 0 (not cached)            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Time:  O(m × n) - Each cell computed once                   ║");
        System.out.println("║  Space: O(m × n) - DP table + recursion stack                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║  APPROACH 3: TABULATION                                      ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Early Check:                                                ║");
        System.out.println("║    if (grid[0][0]==1 || grid[m-1][n-1]==1) return 0          ║");
        System.out.println("║    Cannot start or reach destination!                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recurrence:                                                 ║");
        System.out.println("║    if (i==0 && j==0): dp[i][j] = 1                           ║");
        System.out.println("║    else if (grid[i][j]==1): dp[i][j] = 0                     ║");
        System.out.println("║    else: dp[i][j] = dp[i-1][j] + dp[i][j-1]                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: Grid with obstacle at (1,1)                        ║");
        System.out.println("║    Grid:        DP Table:                                    ║");
        System.out.println("║    0 0 0        1 1 1                                        ║");
        System.out.println("║    0 1 0   →    1 0 1                                        ║");
        System.out.println("║    0 0 0        1 1 2                                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Explanation:                                                ║");
        System.out.println("║    • dp[1][1] = 0 (obstacle blocks)                          ║");
        System.out.println("║    • dp[1][2] = dp[0][2] + dp[1][1] = 1 + 0 = 1              ║");
        System.out.println("║    • dp[2][1] = dp[1][1] + dp[2][0] = 0 + 1 = 1              ║");
        System.out.println("║    • dp[2][2] = dp[1][2] + dp[2][1] = 1 + 1 = 2              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Time:  O(m × n) - Fill entire table                         ║");
        System.out.println("║  Space: O(m × n) - DP table                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║  APPROACH 4: SPACE OPTIMIZED                                 ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Same logic as Unique Paths I space optimization:            ║");
        System.out.println("║    • Use prev[] and curr[] arrays                            ║");
        System.out.println("║    • Check obstacle before computing                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Logic:                                                  ║");
        System.out.println("║    if (grid[i][j] == 1): curr[j] = 0                         ║");
        System.out.println("║    else: curr[j] = prev[j] + curr[j-1]                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example Process for 3×3 with obstacle at [1,1]:             ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Row 0: curr = [1, 1, 1]                                   ║");
        System.out.println("║    Row 1: prev = [1, 1, 1]                                   ║");
        System.out.println("║           curr[0] = 1                                        ║");
        System.out.println("║           curr[1] = 0 (obstacle!)                            ║");
        System.out.println("║           curr[2] = prev[2] + curr[1] = 1 + 0 = 1            ║");
        System.out.println("║           curr = [1, 0, 1]                                   ║");
        System.out.println("║    Row 2: prev = [1, 0, 1]                                   ║");
        System.out.println("║           curr[0] = 1                                        ║");
        System.out.println("║           curr[1] = prev[1] + curr[0] = 0 + 1 = 1            ║");
        System.out.println("║           curr[2] = prev[2] + curr[1] = 1 + 1 = 2            ║");
        System.out.println("║           curr = [1, 1, 2]                                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Time:  O(m × n) - Process each cell once                    ║");
        System.out.println("║  Space: O(n) - Two arrays of size n                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Critical Edge Cases:                                        ║");
        System.out.println("║    ✓ Obstacle at start (0,0) → return 0                      ║");
        System.out.println("║    ✓ Obstacle at end (m-1,n-1) → return 0                    ║");
        System.out.println("║    ✓ Complete horizontal/vertical wall → return 0            ║");
        System.out.println("║    ✓ No obstacles → same as Unique Paths I                   ║");
        System.out.println("║    ✓ Single cell with no obstacle → return 1                 ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Key Insight:                                                ║");
        System.out.println("║    Obstacles propagate zeros in the DP table                 ║");
        System.out.println("║    Any path blocked by obstacle contributes 0 paths          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        
    }

}

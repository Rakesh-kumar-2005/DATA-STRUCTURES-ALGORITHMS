package Dynamic_Programming;

/*

Description:
  Following program computes the number of unique paths in a grid from the top-left corner to the bottom-right corner using Dynamic Programming techniques...

Problem Statement:
  -> Given a grid of size m × n...
  -> Start at position (0,0) and reach position (m−1,n−1)...
  -> You can only move RIGHT or DOWN...
  -> Count the total number of distinct paths possible...

Core Idea:
  -> At any cell (i,j), you can reach it either:
       * From the cell above (i−1,j)...
       * From the cell to the left (i,j−1)...
  -> Therefore:
       paths(i,j) = paths(i−1,j) + paths(i,j−1)...

Mathematical Insight:
  -> Total moves required:
       (m−1) down moves + (n−1) right moves...
  -> Total moves = m + n − 2...
  -> Choose positions for down (or right) moves...
  -> Formula:
       C(m+n−2, m−1)...

Approach:
  > Four Implementations Provided:

  ═════════════════════════════════════════════
  APPROACH 1: PURE RECURSION
  ═════════════════════════════════════════════

     i. Base case:
          * If at (0,0) → return 1...
          * If out of bounds → return 0...
     ii. Recursively compute:
          paths(row,col) =
            paths(row−1,col) + paths(row,col−1)...

  -> Explores all possible paths...
  -> Contains overlapping subproblems...
  -> Time Complexity: O(2^(m+n))...
  -> Space Complexity: O(m+n) recursion stack...

  ═════════════════════════════════════════════
  APPROACH 2: MEMOIZATION (Top-Down DP)
  ═════════════════════════════════════════════

     i. Use dp[][] array initialized with −1...
     ii. Before computing, check if already stored...
     iii. Store result after computation...

  -> Eliminates repeated calculations...
  -> Each cell computed once...
  -> Time Complexity: O(m × n)...
  -> Space Complexity: O(m × n)...

  ═════════════════════════════════════════════
  APPROACH 3: TABULATION (Bottom-Up DP)
  ═════════════════════════════════════════════

     i. Create dp[rows][cols] table...
     ii. Initialize dp[0][0] = 1...
     iii. Fill row by row using:
          dp[i][j] = dp[i−1][j] + dp[i][j−1]...

  -> Builds solution iteratively...
  -> No recursion required...
  -> Time Complexity: O(m × n)...
  -> Space Complexity: O(m × n)...

  Example DP Table for 3×3 grid:

      1   1   1
      1   2   3
      1   3   6

  -> Final answer = dp[m−1][n−1]...

  ═════════════════════════════════════════════
  APPROACH 4: SPACE OPTIMIZED DP
  ═════════════════════════════════════════════

     i. Observation: Only previous row is needed...
     ii. Use two 1D arrays:
          * prev[] → previous row...
          * curr[] → current row...
     iii. Update:
          curr[j] = prev[j] + curr[j−1]...
     iv. After each row: prev = curr...

  -> Reduces space usage...
  -> Time Complexity: O(m × n)...
  -> Space Complexity: O(n)...

Algorithm Steps (Tabulation Version):
  -> Initialize dp table...
  -> Set dp[0][0] = 1...
  -> For each cell:
       * up = dp[i−1][j] if i>0...
       * left = dp[i][j−1] if j>0...
       * dp[i][j] = up + left...
  -> Return dp[m−1][n−1]...

Example:
  -> Input: m=3, n=3...
  -> Total moves: 2 down + 2 right = 4...
  -> Possible paths:
       RRDD, RDRD, RDDR, DRRD, DRDR, DDRR...
  -> Output: 6...

Edge Cases:
  -> 1×1 grid → return 1...
  -> Single row → only right moves → return 1...
  -> Single column → only down moves → return 1...
  -> Large grids handled efficiently using DP...

Implementation Notes:
  -> Recursive approach useful for conceptual understanding...
  -> Memoization avoids exponential growth...
  -> Tabulation preferred for iterative clarity...
  -> Space optimization best for large constraints...

Complexity Comparison:
  -> Recursive:      Time O(2^(m+n)), Space O(m+n)...
  -> Memoization:    Time O(m×n), Space O(m×n)...
  -> Tabulation:     Time O(m×n), Space O(m×n)...
  -> Space Optimized:Time O(m×n), Space O(n)...

Applications:
  -> Robot navigation problems...
  -> Grid-based dynamic programming models...
  -> Combinatorics and path counting problems...
  -> Foundation for obstacle-based and weighted grid variations...

*/

import java.util.Arrays;

public class Unique_Paths {

    private static int recursive(int row, int col) {
        if (row == 0 && col == 0) {
            return 1;
        }

        if (row < 0 || col < 0) {
            return 0;
        }

        int up = recursive(row - 1, col);
        int left = recursive(row, col - 1);

        return up + left;
    }

    private static int memoization(int row, int col, int[][] dp) {
        if (row == 0 && col == 0) {
            return 1;
        }

        if (row < 0 || col < 0) {
            return 0;
        }

        if (dp[row][col] != - 1) {
            return dp[row][col];
        }

        int up = memoization(row - 1, col, dp);
        int left = memoization(row, col - 1, dp);

        return dp[row][col] = up + left;
    }

    public static int tabulation(int rows, int cols) {
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(dp[i], - 1);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    int left = 0;
                    int up = 0;

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

    private static int ultimateSpaceOptimized(int rows, int cols) {
        int[] prev = new int[cols];
        Arrays.fill(prev, 0);

        for (int i = 0; i < rows; i++) {
            int[] curr = new int[cols];
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    curr[j] = 1;
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

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    UNIQUE PATHS                              ║");
        System.out.println("║  Count unique paths from top-left to bottom-right in grid    ║");
        System.out.println("║  Can only move RIGHT or DOWN                                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Small Grid 3×7 ===");
        int m1 = 3, n1 = 7;
        System.out.println("Grid dimensions: " + m1 + " rows × " + n1 + " columns");
        System.out.println("\nGrid visualization:");
        System.out.println("  S → → → → → → E");
        System.out.println("  ↓             ↓");
        System.out.println("  ↓             ↓");
        System.out.println("\nPossible moves: Right (→) or Down (↓)");
        System.out.println("Start: (0,0), End: (2,6)");
        System.out.println("Total moves needed: 2 down + 6 right = 8 moves\n");

        int result1_rec = recursive(m1 - 1, n1 - 1);

        int[][] dp1 = new int[m1][n1];
        for (int i = 0; i < m1; i++) Arrays.fill(dp1[i], - 1);
        int result1_memo = memoization(m1 - 1, n1 - 1, dp1);

        int result1_tab = tabulation(m1, n1);
        int result1_opt = ultimateSpaceOptimized(m1, n1);

        System.out.println("✓ Recursive Result: " + result1_rec);
        System.out.println("✓ Memoization Result: " + result1_memo);
        System.out.println("✓ Tabulation Result: " + result1_tab);
        System.out.println("✓ Space Optimized Result: " + result1_opt);
        System.out.println("  Expected: 28");
        System.out.println("  Status: " + (result1_tab == 28 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Square Grid 3×3 ===");
        int m2 = 3, n2 = 3;
        System.out.println("Grid dimensions: " + m2 + " rows × " + n2 + " columns");
        System.out.println("\nGrid visualization:");
        System.out.println("  S → → E");
        System.out.println("  ↓   ↓ ↓");
        System.out.println("  ↓   ↓ ↓");
        System.out.println("\nAnalysis:");
        System.out.println("  Total moves: 2 down + 2 right = 4 moves");
        System.out.println("  Sample paths:");
        System.out.println("    RRDD, RDRD, RDDR, DRRD, DRDR, DDRR\n");

        int result2_rec = recursive(m2 - 1, n2 - 1);

        int[][] dp2 = new int[m2][n2];
        for (int i = 0; i < m2; i++) Arrays.fill(dp2[i], - 1);
        int result2_memo = memoization(m2 - 1, n2 - 1, dp2);

        int result2_tab = tabulation(m2, n2);
        int result2_opt = ultimateSpaceOptimized(m2, n2);

        System.out.println("✓ Recursive Result: " + result2_rec);
        System.out.println("✓ Memoization Result: " + result2_memo);
        System.out.println("✓ Tabulation Result: " + result2_tab);
        System.out.println("✓ Space Optimized Result: " + result2_opt);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result2_tab == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Single Row 1×10 ===");
        int m3 = 1, n3 = 10;
        System.out.println("Grid dimensions: " + m3 + " row × " + n3 + " columns");
        System.out.println("\nGrid visualization:");
        System.out.println("  S → → → → → → → → → E");
        System.out.println("\nAnalysis:");
        System.out.println("  Only one path: move right 9 times");
        System.out.println("  No down moves possible (single row)\n");

        int result3_rec = recursive(m3 - 1, n3 - 1);

        int[][] dp3 = new int[m3][n3];
        for (int i = 0; i < m3; i++) Arrays.fill(dp3[i], - 1);
        int result3_memo = memoization(m3 - 1, n3 - 1, dp3);

        int result3_tab = tabulation(m3, n3);
        int result3_opt = ultimateSpaceOptimized(m3, n3);

        System.out.println("✓ Recursive Result: " + result3_rec);
        System.out.println("✓ Memoization Result: " + result3_memo);
        System.out.println("✓ Tabulation Result: " + result3_tab);
        System.out.println("✓ Space Optimized Result: " + result3_opt);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result3_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Single Column 10×1 ===");
        int m4 = 10, n4 = 1;
        System.out.println("Grid dimensions: " + m4 + " rows × " + n4 + " column");
        System.out.println("\nGrid visualization:");
        System.out.println("  S");
        System.out.println("  ↓");
        System.out.println("  ↓");
        System.out.println("  ⋮");
        System.out.println("  ↓");
        System.out.println("  E");
        System.out.println("\nAnalysis:");
        System.out.println("  Only one path: move down 9 times");
        System.out.println("  No right moves possible (single column)\n");

        int result4_rec = recursive(m4 - 1, n4 - 1);

        int[][] dp4 = new int[m4][n4];
        for (int i = 0; i < m4; i++) Arrays.fill(dp4[i], - 1);
        int result4_memo = memoization(m4 - 1, n4 - 1, dp4);

        int result4_tab = tabulation(m4, n4);
        int result4_opt = ultimateSpaceOptimized(m4, n4);

        System.out.println("✓ Recursive Result: " + result4_rec);
        System.out.println("✓ Memoization Result: " + result4_memo);
        System.out.println("✓ Tabulation Result: " + result4_tab);
        System.out.println("✓ Space Optimized Result: " + result4_opt);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result4_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Minimum Grid 1×1 ===");
        int m5 = 1, n5 = 1;
        System.out.println("Grid dimensions: " + m5 + " row × " + n5 + " column");
        System.out.println("\nGrid visualization:");
        System.out.println("  S/E (start and end at same position)");
        System.out.println("\nAnalysis:");
        System.out.println("  Already at destination");
        System.out.println("  Unique paths: 1 (stay in place)\n");

        int result5_rec = recursive(m5 - 1, n5 - 1);

        int[][] dp5 = new int[m5][n5];
        for (int i = 0; i < m5; i++) Arrays.fill(dp5[i], - 1);
        int result5_memo = memoization(m5 - 1, n5 - 1, dp5);

        int result5_tab = tabulation(m5, n5);
        int result5_opt = ultimateSpaceOptimized(m5, n5);

        System.out.println("✓ Recursive Result: " + result5_rec);
        System.out.println("✓ Memoization Result: " + result5_memo);
        System.out.println("✓ Tabulation Result: " + result5_tab);
        System.out.println("✓ Space Optimized Result: " + result5_opt);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result5_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Rectangle 2×5 ===");
        int m6 = 2, n6 = 5;
        System.out.println("Grid dimensions: " + m6 + " rows × " + n6 + " columns");
        System.out.println("\nGrid visualization:");
        System.out.println("  S → → → → E");
        System.out.println("  ↓         ↓");
        System.out.println("\nAnalysis:");
        System.out.println("  1 down + 4 right = 5 total moves");
        System.out.println("  Choose 1 position out of 5 for down move");
        System.out.println("  Formula: C(5,1) = 5\n");

        int[][] dp6 = new int[m6][n6];
        for (int i = 0; i < m6; i++) Arrays.fill(dp6[i], - 1);
        int result6_memo = memoization(m6 - 1, n6 - 1, dp6);

        int result6_tab = tabulation(m6, n6);
        int result6_opt = ultimateSpaceOptimized(m6, n6);

        System.out.println("✓ Memoization Result: " + result6_memo);
        System.out.println("✓ Tabulation Result: " + result6_tab);
        System.out.println("✓ Space Optimized Result: " + result6_opt);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result6_tab == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Larger Grid 5×5 ===");
        int m7 = 5, n7 = 5;
        System.out.println("Grid dimensions: " + m7 + " rows × " + n7 + " columns");
        System.out.println("\nGrid visualization:");
        System.out.println("  S → → → → E");
        System.out.println("  ↓         ↓");
        System.out.println("  ↓         ↓");
        System.out.println("  ↓         ↓");
        System.out.println("  ↓         ↓");
        System.out.println("\nAnalysis:");
        System.out.println("  4 down + 4 right = 8 total moves");
        System.out.println("  Formula: C(8,4) = 70\n");

        int[][] dp7 = new int[m7][n7];
        for (int i = 0; i < m7; i++) Arrays.fill(dp7[i], - 1);
        int result7_memo = memoization(m7 - 1, n7 - 1, dp7);

        int result7_tab = tabulation(m7, n7);
        int result7_opt = ultimateSpaceOptimized(m7, n7);

        System.out.println("✓ Memoization Result: " + result7_memo);
        System.out.println("✓ Tabulation Result: " + result7_tab);
        System.out.println("✓ Space Optimized Result: " + result7_opt);
        System.out.println("  Expected: 70");
        System.out.println("  Status: " + (result7_tab == 70 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Medium Rectangle 4×6 ===");
        int m8 = 4, n8 = 6;
        System.out.println("Grid dimensions: " + m8 + " rows × " + n8 + " columns");
        System.out.println("\nAnalysis:");
        System.out.println("  3 down + 5 right = 8 total moves");
        System.out.println("  Formula: C(8,3) = 56\n");

        int[][] dp8 = new int[m8][n8];
        for (int i = 0; i < m8; i++) Arrays.fill(dp8[i], - 1);
        int result8_memo = memoization(m8 - 1, n8 - 1, dp8);

        int result8_tab = tabulation(m8, n8);
        int result8_opt = ultimateSpaceOptimized(m8, n8);

        System.out.println("✓ Memoization Result: " + result8_memo);
        System.out.println("✓ Tabulation Result: " + result8_tab);
        System.out.println("✓ Space Optimized Result: " + result8_opt);
        System.out.println("  Expected: 56");
        System.out.println("  Status: " + (result8_tab == 56 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem Definition:                                         ║");
        System.out.println("║    Find number of unique paths from (0,0) to (m-1,n-1)       ║");
        System.out.println("║    Allowed moves: RIGHT or DOWN only                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Mathematical Formula:                                       ║");
        System.out.println("║    Paths = C(m+n-2, m-1) = (m+n-2)! / ((m-1)! × (n-1)!)      ║");
        System.out.println("║    This is a combination problem!                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why? Need (m-1) down moves and (n-1) right moves            ║");
        System.out.println("║  Total moves: (m-1) + (n-1) = m+n-2                          ║");
        System.out.println("║  Choose positions for down moves: C(m+n-2, m-1)              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║  APPROACH 1: RECURSIVE                                       ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base Cases:                                                 ║");
        System.out.println("║    • At (0,0): return 1 (reached destination)                ║");
        System.out.println("║    • Out of bounds (row<0 or col<0): return 0                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recursive Relation:                                         ║");
        System.out.println("║    paths(row,col) = paths(row-1,col) + paths(row,col-1)      ║");
        System.out.println("║    • Can come from cell above (up)                           ║");
        System.out.println("║    • Can come from cell to left (left)                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: 3×3 grid to reach (2,2)                            ║");
        System.out.println("║    paths(2,2) = paths(1,2) + paths(2,1)                      ║");
        System.out.println("║    paths(1,2) = paths(0,2) + paths(1,1)                      ║");
        System.out.println("║    paths(2,1) = paths(1,1) + paths(2,0)                      ║");
        System.out.println("║    ... continues recursively                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Time:  O(2^(m+n)) - Exponential (many overlapping calls)    ║");
        System.out.println("║  Space: O(m+n) - Recursion depth                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║  APPROACH 2: MEMOIZATION (Top-Down DP)                       ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Optimization: Store computed results in dp table            ║");
        System.out.println("║    if (dp[row][col] != -1) return dp[row][col]               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Before computing, check if already calculated               ║");
        System.out.println("║  Eliminates redundant recursive calls                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Time:  O(m × n) - Each cell computed once                   ║");
        System.out.println("║  Space: O(m × n) - DP table + O(m+n) recursion stack         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║  APPROACH 3: TABULATION (Bottom-Up DP)                       ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Build solution iteratively from (0,0) to (m-1,n-1)          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Initialization:                                             ║");
        System.out.println("║    dp[0][0] = 1 (base case)                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recurrence:                                                 ║");
        System.out.println("║    dp[i][j] = dp[i-1][j] + dp[i][j-1]                        ║");
        System.out.println("║    (if i>0 use dp[i-1][j], if j>0 use dp[i][j-1])            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  DP Table for 3×3 grid:                                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║    j→  0   1   2                                             ║");
        System.out.println("║  i↓                                                          ║");
        System.out.println("║   0    1   1   1                                             ║");
        System.out.println("║   1    1   2   3                                             ║");
        System.out.println("║   2    1   3   6                                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Explanation:                                                ║");
        System.out.println("║    • First row: 1 (only right moves)                         ║");
        System.out.println("║    • First col: 1 (only down moves)                          ║");
        System.out.println("║    • dp[1][1] = dp[0][1] + dp[1][0] = 1+1 = 2                ║");
        System.out.println("║    • dp[1][2] = dp[0][2] + dp[1][1] = 1+2 = 3                ║");
        System.out.println("║    • dp[2][2] = dp[1][2] + dp[2][1] = 3+3 = 6                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Time:  O(m × n) - Fill entire table                         ║");
        System.out.println("║  Space: O(m × n) - DP table                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║  APPROACH 4: SPACE OPTIMIZED                                 ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Observation: Only need previous row to compute current row  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Use two 1D arrays:                                          ║");
        System.out.println("║    • prev[]: stores previous row                             ║");
        System.out.println("║    • curr[]: computes current row                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  After computing each row: prev = curr                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example for 3×3:                                            ║");
        System.out.println("║    Row 0: curr = [1, 1, 1]                                   ║");
        System.out.println("║    Row 1: prev = [1, 1, 1], curr = [1, 2, 3]                 ║");
        System.out.println("║    Row 2: prev = [1, 2, 3], curr = [1, 3, 6]                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Time:  O(m × n) - Same as tabulation                        ║");
        System.out.println("║  Space: O(n) - Only two arrays of size n                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insight: curr[j] = prev[j] + curr[j-1]                  ║");
        System.out.println("║    • prev[j]: paths from cell above                          ║");
        System.out.println("║    • curr[j-1]: paths from cell to left                      ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity Comparison:                                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Method          │ Time       │ Space                        ║");
        System.out.println("║  ────────────────┼────────────┼─────────────                 ║");
        System.out.println("║  Recursive       │ O(2^(m+n)) │ O(m+n)                       ║");
        System.out.println("║  Memoization     │ O(m×n)     │ O(m×n) + O(m+n)              ║");
        System.out.println("║  Tabulation      │ O(m×n)     │ O(m×n)                       ║");
        System.out.println("║  Space Optimized │ O(m×n)     │ O(n)                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Best Choice: Space Optimized for large grids                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}

package Dynamic_Programming;

/*

Description:
  Following program demonstrates the solution to the "Minimum Falling Path Sum" problem using Dynamic Programming
      techniques to determine the minimum path sum from the top row to the bottom row of a matrix...

Problem Statement:
  -> You are given an n × m matrix of integers...
  -> A falling path starts at any element in the first row...
  -> From position (i, j) you can move to:
       • Down           → (i + 1, j)...
       • Diagonal-left  → (i + 1, j - 1)...
       • Diagonal-right → (i + 1, j + 1)...
  -> The goal is to compute the minimum possible sum of a valid falling path from the top row to the bottom row...

Core Idea:
  -> The minimum path to reach a cell depends on the minimum path of reachable cells from the previous row...
  -> For each cell we consider three possible transitions...

Recursive Relation:
  minPath(i, j) = grid[i][j] + min(
                       minPath(i-1, j),
                       minPath(i-1, j-1),
                       minPath(i-1, j+1)
                   )...

Base Case:
  -> If we reach the first row:
       return grid[0][j]...

Boundary Condition:
  -> If column index goes outside matrix:
       return Integer.MIN_VALUE to represent invalid move...

Approach:
  > Four Dynamic Programming implementations are demonstrated:
     i. Recursive solution (brute-force exploration)...
     ii. Memoization (top-down DP with caching)...
     iii. Tabulation (bottom-up DP)...
     iv. Space optimized DP using arrays...

------------------------------------------------------------

> Recursive Approach:

  -> Start from a cell in the last row...
  -> Recursively explore all valid paths upward...
  -> Select the minimum sum among the three possible directions...

Example:

       2  1  3
       6  5  4
       7  8  9

Possible paths:

       1 → 5 → 7 = 13
       1 → 4 → 8 = 13
       2 → 5 → 8 = 15

Minimum path sum = 13...

Time Complexity:
  -> O(3^n) exponential due to repeated exploration...

Space Complexity:
  -> O(n) recursion stack...

------------------------------------------------------------

> Memoization (Top-Down DP):

  -> Store results of subproblems in dp[i][j]...
  -> If a state is already computed, reuse stored value...

Steps:
  -> Initialize dp array with -1...
  -> Check dp before recursion...
  -> Store computed value...

Time Complexity:
  -> O(n × m)...

Space Complexity:
  -> O(n × m) + recursion stack...

------------------------------------------------------------

> Tabulation (Bottom-Up DP):

  -> Build solution row by row starting from the first row...

Steps:
  -> Initialize dp[0][j] = grid[0][j]...
  -> For each row i from 1 to n-1:
       compute minimum value using previous row...

Transition Formula:

       dp[i][j] = grid[i][j] + min(
                           dp[i-1][j],
                           dp[i-1][j-1],
                           dp[i-1][j+1]
                       )...

  -> Final answer is the minimum value in the last row...

Example DP Table:

       Grid:
         2  1  3
         6  5  4
         7  8  9

       DP Table:
         2  1  3
         7  6  5
        13 13 14

  Minimum falling path sum = 13...

Time Complexity:
  -> O(n × m)...

Space Complexity:
  -> O(n × m)...

------------------------------------------------------------

> Space Optimization:

  -> Each row only depends on the previous row...
  -> Use two arrays instead of full matrix...

Arrays Used:
       prev[] → values of previous row...
       curr[] → values of current row...

Transition:

       curr[j] = grid[i][j] + min(
                            prev[j],
                            prev[j-1],
                            prev[j+1]
                        )...

After processing each row:
       prev = curr...

Space Complexity reduces to O(m)...

------------------------------------------------------------

Example Execution:

Matrix:

       2   1   3
       6   5   4
       7   8   9

Minimum Path:

       1 → 5 → 7 = 13
       or
       1 → 4 → 8 = 13

Final Result:
       Minimum Falling Path Sum = 13...

------------------------------------------------------------

Edge Cases:
  -> Single row matrix → minimum element of that row...
  -> Single column matrix → path moves straight downward...
  -> Negative values allowed in matrix...
  -> Works for rectangular matrices...

------------------------------------------------------------

Why Dynamic Programming Works:
  -> Overlapping subproblems exist while computing paths...
  -> Optimal substructure property:
       best path to current cell depends on best path to previous row cells...

------------------------------------------------------------

Applications:
  -> Grid-based optimization problems...
  -> Pathfinding algorithms...
  -> Image processing seam calculations...
  -> Competitive programming DP problems...

------------------------------------------------------------

Time and Space Complexity Summary:

  Recursive:
       Time  → O(3^n)...
       Space → O(n)...

  Memoization:
       Time  → O(n × m)...
       Space → O(n × m)...

  Tabulation:
       Time  → O(n × m)...
       Space → O(n × m)...

  Space Optimized:
       Time  → O(n × m)...
       Space → O(m)...

*/

public class Minimum_Falling_Path_Sum {

    private static int recursive(int[][] grid, int i, int j) {

        if (j < 0 || j >= grid[0].length) {
            return Integer.MIN_VALUE;
        }

        if (i == 0) {
            return grid[0][j];
        }

        int up = grid[i][j] + recursive(grid, i - 1, j);
        int leftDiagonal = grid[i][j] + recursive(grid, i - 1, j - 1);
        int rightDiagonal = grid[i][j] + recursive(grid, i - 1, j + 1);

        return Math.min(up, Math.min(leftDiagonal, rightDiagonal));

    }

    private static int memoization(int[][] grid, int i, int j, int[][] dp) {

        if (j < 0 || j >= grid[0].length) {
            return Integer.MIN_VALUE;
        }

        if (i == 0) {
            return grid[0][j];
        }

        if (dp[i][j] != - 1) {
            return dp[i][j];
        }

        int up = grid[i][j] + memoization(grid, i - 1, j, dp);
        int leftDiagonal = grid[i][j] + memoization(grid, i - 1, j - 1, dp);
        int rightDiagonal = grid[i][j] + memoization(grid, i - 1, j + 1, dp);

        return dp[i][j] = Math.min(up, Math.min(leftDiagonal, rightDiagonal));

    }

    private static int tabulation(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];
        for (int i = 0; i < m; i++) {
            dp[0][i] = grid[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int up = grid[i][j] + dp[i - 1][j];
                int leftDiagonal = (j - 1 >= 0) ? grid[i][j] + dp[i - 1][j - 1] : Integer.MIN_VALUE;
                int rightDiagonal = (j + 1 < m) ? grid[i][j] + dp[i - 1][j + 1] : Integer.MIN_VALUE;

                dp[i][j] = Math.min(up, Math.min(leftDiagonal, rightDiagonal));
            }
        }

        int min = dp[n - 1][0];
        for (int i = 0; i < m; i++) {
            min = Math.min(min, dp[n - 1][i]);
        }

        return min;
    }

    private static int ultimateSpaceOptimization(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[] prev = new int[m];
        for (int i = 0; i < m; i++) {
            prev[i] = grid[0][i];
        }

        for (int i = 1; i < n; i++) {

            int[] curr = new int[m];

            for (int j = 0; j < m; j++) {

                int up = grid[i][j] + prev[j];
                int leftDiagonal = (j - 1 >= 0) ? grid[i][j] + prev[j - 1] : Integer.MIN_VALUE;
                int rightDiagonal = (j + 1 < m) ? grid[i][j] + prev[j + 1] : Integer.MIN_VALUE;

                curr[j] = Math.min(up, Math.min(leftDiagonal, rightDiagonal));
            }

            prev = curr;
        }

        int min = prev[0];
        for (int i = 0; i < m; i++) {
            min = Math.min(min, prev[i]);
        }

        return min;
    }


    private static void printMatrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            System.out.print("  ");
            for (int j = 0; j < mat[0].length; j++) {
                System.out.printf("%4d ", mat[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║            MINIMUM FALLING PATH SUM                          ║");
        System.out.println("║  Find minimum sum path from top row to bottom row            ║");
        System.out.println("║  Can move down, diagonal-left, or diagonal-right             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[][] grid1 = {
            {2, 1, 3},
            {6, 5, 4},
            {7, 8, 9}
        };
        System.out.println("Matrix:");
        printMatrix(grid1);
        System.out.println("\nPath Analysis:");
        System.out.println("  Possible paths:");
        System.out.println("  1 → 5 → 7 = 13");
        System.out.println("  1 → 4 → 8 = 13 ← minimum");
        System.out.println("  2 → 5 → 8 = 15\n");

        int result1_tab = tabulation(grid1);
        int result1_opt = ultimateSpaceOptimization(grid1);

        System.out.println("✓ Tabulation Result: " + result1_tab);
        System.out.println("✓ Space Optimized Result: " + result1_opt);
        System.out.println("  Expected: 13");
        System.out.println("  Status: " + (result1_tab == 13 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Negative Numbers ===");
        int[][] grid2 = {
            {- 19, 57},
            {- 40, - 5}
        };
        System.out.println("Matrix:");
        printMatrix(grid2);
        System.out.println("\nPath Analysis:");
        System.out.println("  -19 → -40 = -59 ← minimum");
        System.out.println("  57 → -5 = 52\n");

        int result2_tab = tabulation(grid2);
        int result2_opt = ultimateSpaceOptimization(grid2);

        System.out.println("✓ Tabulation Result: " + result2_tab);
        System.out.println("✓ Space Optimized Result: " + result2_opt);
        System.out.println("  Expected: -59");
        System.out.println("  Status: " + (result2_tab == - 59 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Single Row ===");
        int[][] grid3 = {{5, 3, 8}};
        System.out.println("Matrix: " + java.util.Arrays.toString(grid3[0]));
        System.out.println("\nOnly one row - minimum is 3\n");

        int result3_tab = tabulation(grid3);
        int result3_opt = ultimateSpaceOptimization(grid3);

        System.out.println("✓ Tabulation Result: " + result3_tab);
        System.out.println("✓ Space Optimized Result: " + result3_opt);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result3_tab == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: All Positive ===");
        int[][] grid4 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Matrix:");
        printMatrix(grid4);
        System.out.println("\nMinimum path: 1 → 4 → 7 = 12\n");

        int result4_tab = tabulation(grid4);
        int result4_opt = ultimateSpaceOptimization(grid4);

        System.out.println("✓ Tabulation Result: " + result4_tab);
        System.out.println("✓ Space Optimized Result: " + result4_opt);
        System.out.println("  Expected: 12");
        System.out.println("  Status: " + (result4_tab == 12 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Diagonal Advantage ===");
        int[][] grid5 = {
            {10, 1, 10},
            {10, 10, 1},
            {10, 10, 10}
        };
        System.out.println("Matrix:");
        printMatrix(grid5);
        System.out.println("\nDiagonal moves beneficial");
        System.out.println("Path: 1 → 1 → 10 = 12\n");

        int result5_tab = tabulation(grid5);
        int result5_opt = ultimateSpaceOptimization(grid5);

        System.out.println("✓ Tabulation Result: " + result5_tab);
        System.out.println("✓ Space Optimized Result: " + result5_opt);
        System.out.println("  Expected: 12");
        System.out.println("  Status: " + (result5_tab == 12 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Minimum sum path from top to bottom row            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Movement: Down, diagonal-left, or diagonal-right            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recursive Relation:                                         ║");
        System.out.println("║    minPath(i,j) = grid[i][j] +                               ║");
        System.out.println("║      min(minPath(i-1,j), minPath(i-1,j-1), minPath(i-1,j+1)) ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Tabulation:                                                 ║");
        System.out.println("║    Build from top row to bottom                              ║");
        System.out.println("║    dp[i][j] = grid[i][j] + min of previous row options       ║");
        System.out.println("║    Return minimum value in last row                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Optimization:                                         ║");
        System.out.println("║    Use prev[] array for previous row values                  ║");
        System.out.println("║    Update curr[] array for current row                       ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n×m) - Process each cell                         ║");
        System.out.println("║    Space: O(m) optimized, O(n×m) tabulation                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }
    
}

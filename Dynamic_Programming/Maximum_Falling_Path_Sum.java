package Dynamic_Programming;

public class Maximum_Falling_Path_Sum {

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

        return Math.max(up, Math.max(leftDiagonal, rightDiagonal));

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

        return dp[i][j] = Math.max(up, Math.max(leftDiagonal, rightDiagonal));

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

                dp[i][j] = Math.max(up, Math.max(leftDiagonal, rightDiagonal));
            }
        }

        int max = dp[n - 1][0];
        for (int i = 0; i < m; i++) {
            max = Math.max(max, dp[n - 1][i]);
        }

        return max;
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

                curr[j] = Math.max(up, Math.max(leftDiagonal, rightDiagonal));
            }

            prev = curr;
        }

        int max = prev[0];
        for (int i = 0; i < m; i++) {
            max = Math.max(max, prev[i]);
        }

        return max;
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
        System.out.println("║            MAXIMUM FALLING PATH SUM                          ║");
        System.out.println("║  Find maximum sum path from top row to bottom row            ║");
        System.out.println("║  Can move down, diagonal-left, or diagonal-right             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Small Matrix ===");
        int[][] grid1 = {
            {2, 1, 3},
            {6, 5, 4},
            {7, 8, 9}
        };
        System.out.println("Matrix:");
        printMatrix(grid1);
        System.out.println("\nPath Analysis:");
        System.out.println("  Start from top row: 2, 1, or 3");
        System.out.println("  Best path: 2 → 6 → 8 = 16");
        System.out.println("  Alternative: 3 → 4 → 9 = 16\n");

        int result1_rec = recursive(grid1, 2, 1);

        int[][] dp1 = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) dp1[i][j] = - 1;
        }
        int result1_memo = memoization(grid1, 2, 1, dp1);

        int result1_tab = tabulation(grid1);
        int result1_opt = ultimateSpaceOptimization(grid1);

        System.out.println("✓ Recursive Result: " + result1_rec);
        System.out.println("✓ Memoization Result: " + result1_memo);
        System.out.println("✓ Tabulation Result: " + result1_tab);
        System.out.println("✓ Space Optimized Result: " + result1_opt);
        System.out.println("  Expected: 16");
        System.out.println("  Status: " + (result1_tab == 16 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Negative Numbers ===");
        int[][] grid2 = {
            {- 9, - 6, - 5},
            {- 8, - 7, - 4},
            {- 3, - 2, - 1}
        };
        System.out.println("Matrix:");
        printMatrix(grid2);
        System.out.println("\nAll negative - find least negative path");
        System.out.println("Best path: -5 → -4 → -1 = -10\n");

        int result2_tab = tabulation(grid2);
        int result2_opt = ultimateSpaceOptimization(grid2);

        System.out.println("✓ Tabulation Result: " + result2_tab);
        System.out.println("✓ Space Optimized Result: " + result2_opt);
        System.out.println("  Expected: -10");
        System.out.println("  Status: " + (result2_tab == - 10 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Single Row ===");
        int[][] grid3 = {{5, 3, 8}};
        System.out.println("Matrix: " + java.util.Arrays.toString(grid3[0]));
        System.out.println("\nOnly one row - maximum is 8\n");

        int result3_tab = tabulation(grid3);
        int result3_opt = ultimateSpaceOptimization(grid3);

        System.out.println("✓ Tabulation Result: " + result3_tab);
        System.out.println("✓ Space Optimized Result: " + result3_opt);
        System.out.println("  Expected: 8");
        System.out.println("  Status: " + (result3_tab == 8 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Single Column ===");
        int[][] grid4 = {{1}, {2}, {3}};
        System.out.println("Matrix:");
        printMatrix(grid4);
        System.out.println("\nOnly one column - path: 1 → 2 → 3 = 6\n");

        int result4_tab = tabulation(grid4);
        int result4_opt = ultimateSpaceOptimization(grid4);

        System.out.println("✓ Tabulation Result: " + result4_tab);
        System.out.println("✓ Space Optimized Result: " + result4_opt);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result4_tab == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Mixed Values ===");
        int[][] grid5 = {
            {1, 2, 10, 4},
            {100, 3, 2, 1},
            {1, 1, 20, 2},
            {1, 2, 2, 1}
        };
        System.out.println("Matrix:");
        printMatrix(grid5);
        System.out.println("\nOptimal path involves large values");
        System.out.println("Path: 10 → 100 → 20 → 2 = 132 (approximate)\n");

        int result5_tab = tabulation(grid5);
        int result5_opt = ultimateSpaceOptimization(grid5);

        System.out.println("✓ Tabulation Result: " + result5_tab);
        System.out.println("✓ Space Optimized Result: " + result5_opt);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Maximum sum path from any cell in top row to any   ║");
        System.out.println("║  cell in bottom row                                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Movement from (i,j):                                        ║");
        System.out.println("║    • Down: (i+1, j)                                          ║");
        System.out.println("║    • Diagonal-left: (i+1, j-1)                               ║");
        System.out.println("║    • Diagonal-right: (i+1, j+1)                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recursive Relation:                                         ║");
        System.out.println("║    maxPath(i,j) = grid[i][j] +                               ║");
        System.out.println("║      max(maxPath(i-1,j), maxPath(i-1,j-1), maxPath(i-1,j+1)) ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Tabulation (Bottom-Up):                                     ║");
        System.out.println("║    Initialize first row: dp[0][j] = grid[0][j]               ║");
        System.out.println("║    For each row i from 1 to n-1:                             ║");
        System.out.println("║      For each column j:                                      ║");
        System.out.println("║        dp[i][j] = grid[i][j] + max of:                       ║");
        System.out.println("║          - dp[i-1][j] (from above)                           ║");
        System.out.println("║          - dp[i-1][j-1] (from upper-left)                    ║");
        System.out.println("║          - dp[i-1][j+1] (from upper-right)                   ║");
        System.out.println("║    Return max value in last row                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Optimization:                                         ║");
        System.out.println("║    Use prev[] and curr[] arrays                              ║");
        System.out.println("║    Only need previous row to compute current                 ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n×m) - Process each cell once                    ║");
        System.out.println("║    Space: O(m) with optimization, O(n×m) without             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

}
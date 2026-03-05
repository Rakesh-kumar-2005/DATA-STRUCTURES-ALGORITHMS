package Arrays;

public class Special_Positions_In_A_Binary_Matrix {

    private static boolean bruteForce(int[][] mat, int row, int col, int rows, int cols) {
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < cols; i++) {
            sum1 += mat[row][i];
        }

        for (int i = 0; i < rows; i++) {
            sum2 += mat[i][col];
        }

        return sum1 == 1 && sum2 == 1;
    }

    private static int optimized(int[][] mat, int rows, int cols) {

        int ans = 0;
        int[] rowSum = new int[rows];
        int[] colSum = new int[cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rowSum[i] += mat[i][j];
                colSum[j] += mat[i][j];
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1 && rowSum[i] == 1 && colSum[j] == 1) {
                    ans++;
                }
            }
        }

        return ans;

    }

    private static void printMatrix(int[][] mat) {
        for (int[] ints : mat) {
            System.out.print("  ");
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           SPECIAL POSITIONS IN A BINARY MATRIX               ║");
        System.out.println("║  Count positions where cell is 1 and all others in its      ║");
        System.out.println("║  row and column are 0                                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[][] mat1 = {
            {1, 0, 0},
            {0, 0, 1},
            {1, 0, 0}
        };
        System.out.println("Matrix:");
        printMatrix(mat1);
        System.out.println("\nAnalysis:");
        System.out.println("  Position (0,0): value=1");
        System.out.println("    Row 0: [1,0,0] sum=1 ✓");
        System.out.println("    Col 0: [1,0,1] sum=2 ✗ (not special)");
        System.out.println("\n  Position (1,2): value=1");
        System.out.println("    Row 1: [0,0,1] sum=1 ✓");
        System.out.println("    Col 2: [0,1,0] sum=1 ✓ (special!)");
        System.out.println("\n  Position (2,0): value=1");
        System.out.println("    Row 2: [1,0,0] sum=1 ✓");
        System.out.println("    Col 0: [1,0,1] sum=2 ✗ (not special)");
        System.out.println("\nSpecial positions: 1 (at position [1,2])\n");

        int result1 = optimized(mat1, 3, 3);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result1 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Multiple Special Positions ===");
        int[][] mat2 = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        System.out.println("Matrix (Identity):");
        printMatrix(mat2);
        System.out.println("\nAnalysis:");
        System.out.println("  Position (0,0): row sum=1, col sum=1 ✓");
        System.out.println("  Position (1,1): row sum=1, col sum=1 ✓");
        System.out.println("  Position (2,2): row sum=1, col sum=1 ✓");
        System.out.println("\nAll diagonal positions are special!\n");

        int result2 = optimized(mat2, 3, 3);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result2 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: No Special Positions ===");
        int[][] mat3 = {
            {0, 0, 0, 1},
            {1, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        };
        System.out.println("Matrix:");
        printMatrix(mat3);
        System.out.println("\nAnalysis:");
        System.out.println("  Position (0,3): row sum=1 ✓, col sum=1 ✓ (special!)");
        System.out.println("  Position (1,0): row sum=1 ✓, col sum=1 ✓ (special!)");
        System.out.println("  Position (2,1): row sum=2 ✗ (not special)");
        System.out.println("  Position (2,2): row sum=2 ✗ (not special)");
        System.out.println("\nSpecial positions: 2\n");

        int result3 = optimized(mat3, 4, 4);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result3 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: All Zeros ===");
        int[][] mat4 = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        System.out.println("Matrix:");
        printMatrix(mat4);
        System.out.println("\nNo 1s in matrix → no special positions\n");

        int result4 = optimized(mat4, 3, 3);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result4 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Single Element ===");
        int[][] mat5 = {{1}};
        System.out.println("Matrix: [1]");
        System.out.println("\nOnly element is 1, row sum=1, col sum=1");
        System.out.println("Special positions: 1\n");

        int result5 = optimized(mat5, 1, 1);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result5 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Single Row ===");
        int[][] mat6 = {{0, 0, 1, 0, 0}};
        System.out.println("Matrix: [0, 0, 1, 0, 0]");
        System.out.println("\nPosition (0,2):");
        System.out.println("  Row sum = 1 ✓");
        System.out.println("  Col sum = 1 ✓");
        System.out.println("Special positions: 1\n");

        int result6 = optimized(mat6, 1, 5);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result6 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Single Column ===");
        int[][] mat7 = {{0}, {1}, {0}, {0}};
        System.out.println("Matrix:");
        printMatrix(mat7);
        System.out.println("\nPosition (1,0):");
        System.out.println("  Row sum = 1 ✓");
        System.out.println("  Col sum = 1 ✓");
        System.out.println("Special positions: 1\n");

        int result7 = optimized(mat7, 4, 1);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result7 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Rectangle Matrix ===");
        int[][] mat8 = {
            {1, 0, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };
        System.out.println("Matrix:");
        printMatrix(mat8);
        System.out.println("\nAnalysis:");
        System.out.println("  (0,0): row sum=1, col sum=1 ✓");
        System.out.println("  (1,2): row sum=1, col sum=1 ✓");
        System.out.println("  (2,3): row sum=1, col sum=1 ✓");
        System.out.println("\nSpecial positions: 3\n");

        int result8 = optimized(mat8, 3, 4);
        System.out.println("✓ Result: " + result8);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result8 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Count positions (i,j) where:                       ║");
        System.out.println("║    • mat[i][j] = 1                                           ║");
        System.out.println("║    • All other elements in row i are 0                       ║");
        System.out.println("║    • All other elements in column j are 0                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Brute Force Approach:                                       ║");
        System.out.println("║    For each 1, check if row sum = 1 AND col sum = 1          ║");
        System.out.println("║    Time: O(m×n×(m+n)) - expensive!                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Optimized Approach (Preprocessing):                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 1: Calculate row and column sums                       ║");
        System.out.println("║    for each cell (i,j):                                      ║");
        System.out.println("║      rowSum[i] += mat[i][j]                                  ║");
        System.out.println("║      colSum[j] += mat[i][j]                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 2: Count special positions                             ║");
        System.out.println("║    for each cell (i,j):                                      ║");
        System.out.println("║      if mat[i][j]==1 && rowSum[i]==1 && colSum[j]==1:        ║");
        System.out.println("║        count++                                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insight:                                                ║");
        System.out.println("║    If rowSum[i]=1 and colSum[j]=1, then mat[i][j] is the     ║");
        System.out.println("║    ONLY 1 in both its row and column → special position      ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(m×n) - Two passes through matrix                 ║");
        System.out.println("║    Space: O(m+n) - Row and column sum arrays                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }
}
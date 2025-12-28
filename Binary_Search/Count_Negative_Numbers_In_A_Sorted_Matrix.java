package Binary_Search;

public class Count_Negative_Numbers_In_A_Sorted_Matrix {

    private static int countNegatives(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int i = n - 1;
        int j = 0;

        int res = 0;

        while (i >= 0 && j < m) {

            if (grid[i][j] < 0) {
                res += m - j;
                i--;
            } else {
                j++;
            }
        }

        return res;
    }

    private static void printGrid(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        System.out.print("       ");
        for (int j = 0; j < m; j++) {
            System.out.printf("Col%d ", j);
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.printf("  Row%d: ", i);
            for (int j = 0; j < m; j++) {
                System.out.printf("%4d ", grid[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║     COUNT NEGATIVE NUMBERS IN SORTED MATRIX                  ║");
        System.out.println("║  Matrix sorted in descending order (rows & columns)          ║");
        System.out.println("║  Start from bottom-left, move right or up                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[][] grid1 = {
            {4, 3, 2, - 1},
            {3, 2, 1, - 1},
            {1, 1, - 1, - 2},
            {- 1, - 1, - 2, - 3}
        };
        System.out.println("Grid:");
        printGrid(grid1);
        System.out.println("\nAlgorithm Steps:");
        System.out.println("  Start at bottom-left: grid[3][0] = -1 (negative)");
        System.out.println("    → All 4 elements in row 3 from col 0 onwards are negative");
        System.out.println("    → Count += 4, move up to row 2");
        System.out.println("  At grid[2][0] = 1 (positive)");
        System.out.println("    → Move right to grid[2][1] = 1 (positive)");
        System.out.println("    → Move right to grid[2][2] = -1 (negative)");
        System.out.println("    → Count += 2 (cols 2-3), move up to row 1");
        System.out.println("  At grid[1][2] = 1 (positive)");
        System.out.println("    → Move right to grid[1][3] = -1 (negative)");
        System.out.println("    → Count += 1, move up to row 0");
        System.out.println("  At grid[0][3] = -1 (negative)");
        System.out.println("    → Count += 1, move up (out of bounds, stop)");
        System.out.println("  Total: 4 + 2 + 1 + 1 = 8");
        int result1 = countNegatives(grid1);
        System.out.println("\n✓ Negative count: " + result1);
        System.out.println("  Expected: 8");
        System.out.println("  Status: " + (result1 == 8 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: All Positive ===");
        int[][] grid2 = {
            {5, 4, 3},
            {3, 2, 1},
            {2, 1, 0}
        };
        System.out.println("Grid:");
        printGrid(grid2);
        System.out.println("\nExplanation:");
        System.out.println("  All elements are non-negative");
        System.out.println("  Pointer traverses from bottom-left to top-right");
        System.out.println("  Never encounters a negative number");
        int result2 = countNegatives(grid2);
        System.out.println("\n✓ Negative count: " + result2);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result2 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: All Negative ===");
        int[][] grid3 = {
            {- 1, - 2, - 3},
            {- 2, - 3, - 4},
            {- 3, - 4, - 5}
        };
        System.out.println("Grid:");
        printGrid(grid3);
        System.out.println("\nExplanation:");
        System.out.println("  Start at grid[2][0] = -3 (negative)");
        System.out.println("  Row 2: Count += 3, move up");
        System.out.println("  Row 1: Count += 3, move up");
        System.out.println("  Row 0: Count += 3, move up (done)");
        System.out.println("  Total: 3 + 3 + 3 = 9 (all elements)");
        int result3 = countNegatives(grid3);
        System.out.println("\n✓ Negative count: " + result3);
        System.out.println("  Expected: 9");
        System.out.println("  Status: " + (result3 == 9 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Single Row ===");
        int[][] grid4 = {
            {3, 2, 1, 0, - 1, - 2, - 3}
        };
        System.out.println("Grid:");
        printGrid(grid4);
        System.out.println("\nExplanation:");
        System.out.println("  Start at grid[0][0] = 3 (positive)");
        System.out.println("  Move right: 2, 1, 0 (all positive)");
        System.out.println("  At grid[0][4] = -1 (negative)");
        System.out.println("  Count += 3 (columns 4, 5, 6)");
        System.out.println("  Total: 3");
        int result4 = countNegatives(grid4);
        System.out.println("\n✓ Negative count: " + result4);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result4 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Single Column ===");
        int[][] grid5 = {
            {5},
            {3},
            {1},
            {- 1},
            {- 3}
        };
        System.out.println("Grid:");
        printGrid(grid5);
        System.out.println("\nExplanation:");
        System.out.println("  Start at grid[4][0] = -3 (negative)");
        System.out.println("  Count += 1, move up to grid[3][0] = -1 (negative)");
        System.out.println("  Count += 1, move up to grid[2][0] = 1 (positive)");
        System.out.println("  Move right (out of bounds, stop)");
        System.out.println("  Total: 2");
        int result5 = countNegatives(grid5);
        System.out.println("\n✓ Negative count: " + result5);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result5 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Mixed Pattern ===");
        int[][] grid6 = {
            {10, 8, 6, 4, 2, 0},
            {9, 7, 5, 3, 1, - 1},
            {8, 6, 4, 2, - 1, - 2},
            {7, 5, 3, - 1, - 2, - 3}
        };
        System.out.println("Grid:");
        printGrid(grid6);
        System.out.println("\nExplanation:");
        System.out.println("  Diagonal pattern of negatives");
        System.out.println("  Bottom row: 3 negatives (cols 3-5)");
        System.out.println("  Row 2: 2 negatives (cols 4-5)");
        System.out.println("  Row 1: 1 negative (col 5)");
        System.out.println("  Row 0: 0 negatives");
        System.out.println("  Total: 3 + 2 + 1 + 0 = 6");
        int result6 = countNegatives(grid6);
        System.out.println("\n✓ Negative count: " + result6);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result6 == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Zeros Boundary ===");
        int[][] grid7 = {
            {5, 1, 0},
            {- 5, - 5, - 5}
        };
        System.out.println("Grid:");
        printGrid(grid7);
        System.out.println("\nExplanation:");
        System.out.println("  Start at grid[1][0] = -5 (negative)");
        System.out.println("  Count += 3 (entire row 1), move up");
        System.out.println("  At grid[0][0] = 5 (positive), move right");
        System.out.println("  Continue moving right, no more negatives");
        System.out.println("  Total: 3");
        int result7 = countNegatives(grid7);
        System.out.println("\n✓ Negative count: " + result7);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result7 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Large Grid ===");
        int[][] grid8 = {
            {100, 50, 20, 10, 5, 0, - 1, - 5},
            {90, 40, 15, 8, 3, - 1, - 2, - 6},
            {80, 30, 10, 5, - 1, - 2, - 3, - 7},
            {70, 20, 5, - 1, - 2, - 3, - 4, - 8},
            {60, 10, - 1, - 2, - 3, - 4, - 5, - 9}
        };
        System.out.println("Grid (5x8):");
        printGrid(grid8);
        System.out.println("\nExplanation:");
        System.out.println("  Large matrix with diagonal negative pattern");
        System.out.println("  Bottom-left to top-right traversal");
        System.out.println("  Efficiently counts all negatives in one pass");
        int result8 = countNegatives(grid8);
        System.out.println("\n✓ Negative count: " + result8);
        System.out.println("  Expected: 20");
        System.out.println("  Status: " + (result8 == 20 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM SUMMARY: Staircase Search                         ║");
        System.out.println("║  1. Start from bottom-left corner                            ║");
        System.out.println("║  2. If negative: count all right elements, move up           ║");
        System.out.println("║  3. If positive/zero: move right                             ║");
        System.out.println("║  4. Continue until out of bounds                             ║");
        System.out.println("║  Time Complexity: O(m + n) - at most m+n moves               ║");
        System.out.println("║  Space Complexity: O(1) - constant space                     ║");
        System.out.println("║  Why it works: Matrix sorted descending in both directions   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}
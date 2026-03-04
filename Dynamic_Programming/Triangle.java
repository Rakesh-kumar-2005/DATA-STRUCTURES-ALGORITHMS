package Dynamic_Programming;

import java.util.ArrayList;

public class Triangle {

    private static int recursive(ArrayList<ArrayList<Integer>> triangle, int i, int j, int n) {
        if (i == n - 1) {
            return triangle.get(n - 1).get(j);
        }

        int down = recursive(triangle, i + 1, j, n);
        int diagonal = recursive(triangle, i + 1, j + 1, n);

        return triangle.get(i).get(j) + Math.min(down, diagonal);
    }

    private static int memoization(ArrayList<ArrayList<Integer>> triangle, int i, int j, int n, int[][] dp) {
        if (i == n - 1) {
            return triangle.get(n - 1).get(j);
        }

        if (dp[i][j] != - 1) {
            return dp[i][j];
        }

        int down = memoization(triangle, i + 1, j, n, dp);
        int diagonal = memoization(triangle, i + 1, j + 1, n, dp);

        return dp[i][j] = triangle.get(i).get(j) + Math.min(down, diagonal);
    }

    private static int tabulation(ArrayList<ArrayList<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {

                int down = dp[i + 1][j];
                int diagonal = dp[i + 1][j + 1];

                dp[i][j] = triangle.get(i).get(j) + Math.min(down, diagonal);
            }
        }

        return dp[0][0];
    }

    private static int ultimateSpaceOptimization1(ArrayList<ArrayList<Integer>> triangle) {
        int n = triangle.size();

        int[] front = new int[n];

        for (int j = 0; j < n; j++) {
            front[j] = triangle.get(n - 1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            int[] curr = new int[n];

            for (int j = i; j >= 0; j--) {

                int down = front[j];
                int diagonal = front[j + 1];

                curr[j] = triangle.get(i).get(j) + Math.min(down, diagonal);
            }
            front = curr;
        }

        return front[0];
    }

    private static int ultimateSpaceOptimization2(ArrayList<ArrayList<Integer>> triangle) {
        int n = triangle.size();

        int[] dp = new int[n];

        for (int j = 0; j < n; j++)
            dp[j] = triangle.get(n - 1).get(j);

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    TRIANGLE MINIMUM PATH SUM                 ║");
        System.out.println("║  Find minimum path sum from top to bottom of triangle        ║");
        System.out.println("║  Can move to adjacent numbers on the row below              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Small Triangle ===");
        ArrayList<ArrayList<Integer>> triangle1 = new ArrayList<>();
        triangle1.add(new ArrayList<>() {{
            add(2);
        }});
        triangle1.add(new ArrayList<>() {{
            add(3);
            add(4);
        }});
        triangle1.add(new ArrayList<>() {{
            add(6);
            add(5);
            add(7);
        }});
        triangle1.add(new ArrayList<>() {{
            add(4);
            add(1);
            add(8);
            add(3);
        }});

        System.out.println("Triangle:");
        System.out.println("       2");
        System.out.println("      3 4");
        System.out.println("     6 5 7");
        System.out.println("    4 1 8 3");
        System.out.println("\nPath Analysis:");
        System.out.println("  Minimum path: 2 → 3 → 5 → 1 = 11\n");

        int result1_rec = recursive(triangle1, 0, 0, 4);

        int[][] dp1 = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) dp1[i][j] = - 1;
        }
        int result1_memo = memoization(triangle1, 0, 0, 4, dp1);

        int result1_tab = tabulation(triangle1);
        int result1_opt1 = ultimateSpaceOptimization1(triangle1);
        int result1_opt2 = ultimateSpaceOptimization2(triangle1);

        System.out.println("✓ Recursive Result: " + result1_rec);
        System.out.println("✓ Memoization Result: " + result1_memo);
        System.out.println("✓ Tabulation Result: " + result1_tab);
        System.out.println("✓ Space Optimized 1 Result: " + result1_opt1);
        System.out.println("✓ Space Optimized 2 Result: " + result1_opt2);
        System.out.println("  Expected: 11");
        System.out.println("  Status: " + (result1_tab == 11 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Minimal Triangle ===");
        ArrayList<ArrayList<Integer>> triangle2 = new ArrayList<>();
        triangle2.add(new ArrayList<>() {{
            add(- 10);
        }});

        System.out.println("Triangle: -10");
        System.out.println("Only one element → minimum = -10\n");

        int result2_tab = tabulation(triangle2);
        int result2_opt2 = ultimateSpaceOptimization2(triangle2);

        System.out.println("✓ Tabulation Result: " + result2_tab);
        System.out.println("✓ Space Optimized Result: " + result2_opt2);
        System.out.println("  Expected: -10");
        System.out.println("  Status: " + (result2_tab == - 10 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Two Rows ===");
        ArrayList<ArrayList<Integer>> triangle3 = new ArrayList<>();
        triangle3.add(new ArrayList<>() {{
            add(1);
        }});
        triangle3.add(new ArrayList<>() {{
            add(2);
            add(3);
        }});

        System.out.println("Triangle:");
        System.out.println("   1");
        System.out.println("  2 3");
        System.out.println("\nMinimum path: 1 → 2 = 3\n");

        int result3_tab = tabulation(triangle3);
        int result3_opt2 = ultimateSpaceOptimization2(triangle3);

        System.out.println("✓ Tabulation Result: " + result3_tab);
        System.out.println("✓ Space Optimized Result: " + result3_opt2);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result3_tab == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Negative Numbers ===");
        ArrayList<ArrayList<Integer>> triangle4 = new ArrayList<>();
        triangle4.add(new ArrayList<>() {{
            add(- 1);
        }});
        triangle4.add(new ArrayList<>() {{
            add(2);
            add(3);
        }});
        triangle4.add(new ArrayList<>() {{
            add(1);
            add(- 1);
            add(- 3);
        }});

        System.out.println("Triangle:");
        System.out.println("     -1");
        System.out.println("    2  3");
        System.out.println("   1 -1 -3");
        System.out.println("\nMinimum path: -1 → 3 → -3 = -1\n");

        int result4_tab = tabulation(triangle4);
        int result4_opt2 = ultimateSpaceOptimization2(triangle4);

        System.out.println("✓ Tabulation Result: " + result4_tab);
        System.out.println("✓ Space Optimized Result: " + result4_opt2);
        System.out.println("  Expected: -1");
        System.out.println("  Status: " + (result4_tab == - 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: All Same Values ===");
        ArrayList<ArrayList<Integer>> triangle5 = new ArrayList<>();
        triangle5.add(new ArrayList<>() {{
            add(5);
        }});
        triangle5.add(new ArrayList<>() {{
            add(5);
            add(5);
        }});
        triangle5.add(new ArrayList<>() {{
            add(5);
            add(5);
            add(5);
        }});

        System.out.println("Triangle:");
        System.out.println("    5");
        System.out.println("   5 5");
        System.out.println("  5 5 5");
        System.out.println("\nAll paths equal: 5 + 5 + 5 = 15\n");

        int result5_tab = tabulation(triangle5);
        int result5_opt2 = ultimateSpaceOptimization2(triangle5);

        System.out.println("✓ Tabulation Result: " + result5_tab);
        System.out.println("✓ Space Optimized Result: " + result5_opt2);
        System.out.println("  Expected: 15");
        System.out.println("  Status: " + (result5_tab == 15 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Find minimum path sum from top to bottom           ║");
        System.out.println("║  Movement: From position (i,j) → (i+1,j) or (i+1,j+1)        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recursive Relation:                                         ║");
        System.out.println("║    minPath(i,j) = triangle[i][j] +                           ║");
        System.out.println("║                   min(minPath(i+1,j), minPath(i+1,j+1))      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Tabulation (Bottom-Up):                                     ║");
        System.out.println("║    Start from last row, build upward                         ║");
        System.out.println("║    dp[i][j] = triangle[i][j] + min(dp[i+1][j], dp[i+1][j+1]) ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Optimization:                                         ║");
        System.out.println("║    Only need previous row → use single array                 ║");
        System.out.println("║    dp[j] = triangle[i][j] + min(dp[j], dp[j+1])              ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n²) where n = number of rows                     ║");
        System.out.println("║    Space: O(n) with optimization, O(n²) without              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }
}
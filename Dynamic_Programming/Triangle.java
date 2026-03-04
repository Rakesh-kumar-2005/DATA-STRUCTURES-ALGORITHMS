package Dynamic_Programming;

/*

Description:
  Following program demonstrates the solution to the "Triangle Minimum Path Sum" problem using multiple
      Dynamic Programming approaches to compute the minimum sum path from the top to the bottom of a triangle structure...

Problem Statement:
  -> You are given a triangle represented as a list of lists where each row contains one more element than the previous row...
  -> Starting from the top element, you may move to either:
       • the directly below element...
       • the diagonally right element...
  -> The goal is to find the minimum possible sum of values along a path from the top to the bottom of the triangle...

Triangle Movement Rule:
  -> From position (i, j) you may move to:
       (i + 1, j)       → Down...
       (i + 1, j + 1)   → Diagonal...
  -> These two positions represent adjacent numbers in the next row...

Approach:
  > Dynamic Programming Techniques:
     i. Recursive Solution (Brute Force)...
     ii. Memoization (Top-Down DP)...
     iii. Tabulation (Bottom-Up DP)...
     iv. Space Optimization using two arrays...
     v. Ultimate Space Optimization using single array...

Recursive Relation:
  -> Let f(i,j) represent the minimum path sum from position (i,j) to the bottom...
  -> Recurrence formula:
       f(i,j) = triangle[i][j] + min(
                    f(i+1, j),
                    f(i+1, j+1)
                )...

Base Case:
  -> If i == n - 1 (last row)...
       return triangle[n - 1][j]...

------------------------------------------------------------

> Recursive Approach:

  -> Start from the top element (0,0)...
  -> Recursively explore both downward and diagonal paths...
  -> Return the minimum cost among the two possibilities...

  Example:
       2
      3 4
     6 5 7
    4 1 8 3

  Possible minimum path:
       2 → 3 → 5 → 1
       Sum = 11...

  Time Complexity: O(2^n) due to repeated subproblems...
  Space Complexity: O(n) recursion stack...

------------------------------------------------------------

> Memoization (Top-Down DP):

  -> Store intermediate results in dp[i][j]...
  -> If a state is already computed, return stored result...
  -> Eliminates redundant recursive computations...

  Steps:
       1. Initialize dp array with -1...
       2. Before computing, check if dp[i][j] already exists...
       3. Store computed result...

  Time Complexity: O(n²)...
  Space Complexity: O(n²) + recursion stack...

------------------------------------------------------------

> Tabulation (Bottom-Up DP):

  -> Build solution starting from the last row...
  -> Last row values remain unchanged...
  -> Move upward computing minimum cost...

  Formula:
       dp[i][j] = triangle[i][j] + min(
                     dp[i+1][j],
                     dp[i+1][j+1]
                 )...

  Example DP Table Construction:

       Row 3: 4 1 8 3
       Row 2: 7 6 10
       Row 1: 9 10
       Row 0: 11

  Final answer = dp[0][0]...

  Time Complexity: O(n²)...
  Space Complexity: O(n²)...

------------------------------------------------------------

> Space Optimization (Using Two Arrays):

  -> Only next row values are needed to compute current row...
  -> Maintain:
       front[] → represents row below...
       curr[]  → represents current row...

  Steps:
       1. Initialize front[] with last row values...
       2. Compute curr[] for upper rows...
       3. Assign curr → front after each iteration...

  Space Complexity reduces to O(n)...

------------------------------------------------------------

> Ultimate Space Optimization (Single Array):

  -> Observe that updates can be performed in-place...
  -> Use one array dp[] representing the row below...

  Transition:
       dp[j] = triangle[i][j] + min(dp[j], dp[j+1])...

  Advantage:
       • No extra arrays required...
       • Most memory efficient solution...

  Time Complexity: O(n²)...
  Space Complexity: O(n)...

------------------------------------------------------------

Example Execution (from main):

  Triangle:

         2
        3 4
       6 5 7
      4 1 8 3

  Step-by-step minimum path calculation:

       Bottom row:   4 1 8 3
       Row above:    7 6 10
       Next row:     9 10
       Top:          11

  Minimum Path:
       2 → 3 → 5 → 1 = 11...

------------------------------------------------------------

Edge Cases:
  -> Triangle with single element...
  -> Negative numbers inside triangle...
  -> All elements identical...
  -> Two-row triangle...
  -> Large triangle inputs...

------------------------------------------------------------

Why Dynamic Programming Works:
  -> Overlapping subproblems exist when computing paths from lower nodes...
  -> Optimal substructure property holds:
       optimal solution for (i,j) depends on optimal solutions of child nodes...

------------------------------------------------------------

Time and Space Complexity Summary:

  Recursive:
       Time  → O(2^n)...
       Space → O(n)...

  Memoization:
       Time  → O(n²)...
       Space → O(n²)...

  Tabulation:
       Time  → O(n²)...
       Space → O(n²)...

  Space Optimized:
       Time  → O(n²)...
       Space → O(n)...

------------------------------------------------------------

Applications:
  -> Grid-based optimization problems...
  -> Graph shortest path variations...
  -> Dynamic programming path problems...
  -> Competitive programming interview questions...

*/
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
        System.out.println("║  Can move to adjacent numbers on the row below               ║");
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

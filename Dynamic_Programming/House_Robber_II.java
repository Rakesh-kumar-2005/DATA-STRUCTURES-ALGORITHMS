package Dynamic_Programming;

import java.util.Arrays;

public class House_Robber_II {

    private static int solveMemoization(int currentIndex, int[] houses, int[] memo) {

        if (currentIndex == 0) {
            return houses[currentIndex];
        }
        if (currentIndex < 0) {
            return 0;
        }

        if (memo[currentIndex] != - 1) {
            return memo[currentIndex];
        }

        int robCurrent = houses[currentIndex] + solveMemoization(currentIndex - 2, houses, memo);
        int skipCurrent = solveMemoization(currentIndex - 1, houses, memo);

        memo[currentIndex] = Math.max(robCurrent, skipCurrent);
        return memo[currentIndex];
    }

    public static int solveLinearHouses(int[] houses) {
        int n = houses.length;
        int[] memo = new int[n];
        Arrays.fill(memo, - 1);

        return solveMemoization(n - 1, houses, memo);
    }

    public static int rob(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] excludeFirst = Arrays.copyOfRange(nums, 1, n);
        int[] excludeLast = Arrays.copyOfRange(nums, 0, n - 1);

        int maxExcludingFirst = solveLinearHouses(excludeFirst);
        int maxExcludingLast = solveLinearHouses(excludeLast);

        return Math.max(maxExcludingFirst, maxExcludingLast);
        
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              HOUSE ROBBER II - CIRCULAR HOUSES               ║");
        System.out.println("║       Rob houses arranged in a circle to maximize money      ║");
        System.out.println("║           Constraint: Cannot rob adjacent houses             ║");
        System.out.println("║        NEW: First and last houses are also adjacent!         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Circle [2,3,2] ===");
        int[] houses1 = {2, 3, 2};
        System.out.println("Houses (circular): " + Arrays.toString(houses1));
        System.out.println("\nVisualization:");
        System.out.println("       2");
        System.out.println("      / \\");
        System.out.println("     3   2  (first and last are adjacent!)");
        System.out.println("\nKey constraint: Cannot rob houses 0 AND 2 (they're adjacent in circle)");
        System.out.println("\nTwo scenarios:");
        System.out.println("  Scenario 1: Exclude first house [0]");
        System.out.println("    Consider houses [3, 2] (indices 1-2)");
        System.out.println("    Rob house 1: 3 ✓");
        System.out.println("  Scenario 2: Exclude last house [2]");
        System.out.println("    Consider houses [2, 3] (indices 0-1)");
        System.out.println("    Rob house 1: 3 ✓");
        System.out.println("  Maximum: 3\n");

        int result1 = rob(houses1);
        System.out.println("✓ Maximum money: " + result1);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result1 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Better to Skip First [1,2,3,1] ===");
        int[] houses2 = {1, 2, 3, 1};
        System.out.println("Houses (circular): " + Arrays.toString(houses2));
        System.out.println("\nVisualization:");
        System.out.println("      1---2");
        System.out.println("      |   |");
        System.out.println("      1---3");
        System.out.println("\nTwo scenarios:");
        System.out.println("  Scenario 1: Exclude first [1]");
        System.out.println("    Houses [2, 3, 1] → rob 2 and 1 → 2+1 = 3");
        System.out.println("  Scenario 2: Exclude last [1]");
        System.out.println("    Houses [1, 2, 3] → rob 1 and 3 → 1+3 = 4 ✓");
        System.out.println("  Maximum: 4\n");

        int result2 = rob(houses2);
        System.out.println("✓ Maximum money: " + result2);
        System.out.println("  Expected: 4");
        System.out.println("  Status: " + (result2 == 4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Single House [10] ===");
        int[] houses3 = {10};
        System.out.println("Houses: " + Arrays.toString(houses3));
        System.out.println("\nOnly one house - rob it!");
        System.out.println("No adjacency constraint with single house");
        System.out.println("Maximum: 10\n");

        int result3 = rob(houses3);
        System.out.println("✓ Maximum money: " + result3);
        System.out.println("  Expected: 10");
        System.out.println("  Status: " + (result3 == 10 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Two Houses [5,8] ===");
        int[] houses4 = {5, 8};
        System.out.println("Houses: " + Arrays.toString(houses4));
        System.out.println("\nTwo houses in a circle:");
        System.out.println("  5 ← → 8 (adjacent)");
        System.out.println("  Can only rob one!");
        System.out.println("  Choose maximum: 8\n");

        int result4 = rob(houses4);
        System.out.println("✓ Maximum money: " + result4);
        System.out.println("  Expected: 8");
        System.out.println("  Status: " + (result4 == 8 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Alternating Values [1,3,1,3,1] ===");
        int[] houses5 = {1, 3, 1, 3, 1};
        System.out.println("Houses (circular): " + Arrays.toString(houses5));
        System.out.println("\nVisualization:");
        System.out.println("       1");
        System.out.println("      / \\");
        System.out.println("     3   1");
        System.out.println("      \\ /");
        System.out.println("    3---1");
        System.out.println("\nTwo scenarios:");
        System.out.println("  Scenario 1: Exclude first [1]");
        System.out.println("    Houses [3,1,3,1] → rob indices 0,2 → 3+3 = 6 ✓");
        System.out.println("  Scenario 2: Exclude last [1]");
        System.out.println("    Houses [1,3,1,3] → rob indices 1,3 → 3+3 = 6 ✓");
        System.out.println("  Maximum: 6\n");

        int result5 = rob(houses5);
        System.out.println("✓ Maximum money: " + result5);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result5 == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: High First, Low Last [200,3,140,20,10] ===");
        int[] houses6 = {200, 3, 140, 20, 10};
        System.out.println("Houses (circular): " + Arrays.toString(houses6));
        System.out.println("\nAnalysis:");
        System.out.println("  First house (200) is very valuable!");
        System.out.println("  Last house (10) is low value");
        System.out.println("\nTwo scenarios:");
        System.out.println("  Scenario 1: Exclude first [200]");
        System.out.println("    Houses [3,140,20,10] → rob 140+10 = 150");
        System.out.println("  Scenario 2: Exclude last [10]");
        System.out.println("    Houses [200,3,140,20] → rob 200+140 = 340 ✓");
        System.out.println("  Maximum: 340 (exclude low-value last house)\n");

        int result6 = rob(houses6);
        System.out.println("✓ Maximum money: " + result6);
        System.out.println("  Expected: 340");
        System.out.println("  Status: " + (result6 == 340 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: All Same Values [5,5,5,5,5,5] ===");
        int[] houses7 = {5, 5, 5, 5, 5, 5};
        System.out.println("Houses (circular): " + Arrays.toString(houses7));
        System.out.println("\nAll houses have equal value");
        System.out.println("\nStrategy: Rob every other house");
        System.out.println("  Can rob at most 3 houses (alternate pattern)");
        System.out.println("  3 × 5 = 15\n");

        int result7 = rob(houses7);
        System.out.println("✓ Maximum money: " + result7);
        System.out.println("  Expected: 15");
        System.out.println("  Status: " + (result7 == 15 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Large Circle [10,1,1,10,1,1,10,1,1] ===");
        int[] houses8 = {10, 1, 1, 10, 1, 1, 10, 1, 1};
        System.out.println("Houses (circular): " + Arrays.toString(houses8));
        System.out.println("\nPattern: High values every 3 houses");
        System.out.println("\nTwo scenarios:");
        System.out.println("  Scenario 1: Exclude first [10]");
        System.out.println("    Can rob: indices 1,3,5,7 or 2,4,6,8");
        System.out.println("  Scenario 2: Exclude last [1]");
        System.out.println("    Can rob: indices 0,3,6 → 10+10+10 = 30 ✓");
        System.out.println("  Maximum: 30\n");

        int result8 = rob(houses8);
        System.out.println("✓ Maximum money: " + result8);
        System.out.println("  Expected: 30");
        System.out.println("  Status: " + (result8 == 30 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM SUMMARY: BREAK THE CIRCLE                         ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Key Insight:                                                ║");
        System.out.println("║    In a circle, first and last houses are adjacent.          ║");
        System.out.println("║    We CANNOT rob both house[0] and house[n-1]                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Strategy: Break into TWO linear problems                    ║");
        System.out.println("║    1. Exclude first house  → rob houses [1...n-1]            ║");
        System.out.println("║    2. Exclude last house   → rob houses [0...n-2]            ║");
        System.out.println("║    3. Return max of both scenarios                           ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n) - solve two linear problems                   ║");
        System.out.println("║    Space: O(n) - memoization + array copies                  ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Edge Cases:                                                 ║");
        System.out.println("║    • n = 1: Return nums[0] (no circle)                       ║");
        System.out.println("║    • n = 2: Return max(nums[0], nums[1])                     ║");
        System.out.println("║    • n ≥ 3: Apply two-scenario logic                         ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  🏆 This is House Robber I applied twice!                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

}
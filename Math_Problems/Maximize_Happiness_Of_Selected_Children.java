package Math_Problems;

import java.util.Arrays;

public class Maximize_Happiness_Of_Selected_Children {

    private static long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);

        long ans = 0;
        int n = happiness.length - 1;
        int turn = 0;

        while (k > 0 && n >= 0) {

            long currentHappiness = happiness[n] - turn;

            if (currentHappiness <= 0) {
                break;
            }

            ans += currentHappiness;
            n--;
            turn++;
            k--;
        }

        return ans;
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║      MAXIMIZE HAPPINESS OF SELECTED CHILDREN                 ║");
        System.out.println("║  Select k children to maximize total happiness               ║");
        System.out.println("║  Each turn, unselected children lose 1 happiness             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] happiness1 = {1, 2, 3};
        int k1 = 2;
        System.out.println("Happiness values: " + Arrays.toString(happiness1));
        System.out.println("Children to select (k): " + k1);
        System.out.println("\nStrategy:");
        System.out.println("  Sorted (descending): [3, 2, 1]");
        System.out.println("  Turn 0: Select child with happiness 3 → 3-0 = 3 ✓");
        System.out.println("  Turn 1: Select child with happiness 2 → 2-1 = 1 ✓");
        System.out.println("          (unselected child decreased from 2 to 1)");
        System.out.println("  Total: 3 + 1 = 4");
        long result1 = maximumHappinessSum(happiness1, k1);
        System.out.println("\n✓ Maximum happiness: " + result1);
        System.out.println("  Expected: 4");
        System.out.println("  Status: " + (result1 == 4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Select All Children ===");
        int[] happiness2 = {1, 1, 1, 1};
        int k2 = 2;
        System.out.println("Happiness values: " + Arrays.toString(happiness2));
        System.out.println("Children to select (k): " + k2);
        System.out.println("\nStrategy:");
        System.out.println("  All children have equal happiness = 1");
        System.out.println("  Turn 0: Select any child → 1-0 = 1 ✓");
        System.out.println("  Turn 1: Select any child → 1-1 = 0 ✓");
        System.out.println("  Total: 1 + 0 = 1");
        long result2 = maximumHappinessSum(happiness2, k2);
        System.out.println("\n✓ Maximum happiness: " + result2);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result2 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Large Happiness Values ===");
        int[] happiness3 = {2, 3, 4, 5};
        int k3 = 1;
        System.out.println("Happiness values: " + Arrays.toString(happiness3));
        System.out.println("Children to select (k): " + k3);
        System.out.println("\nStrategy:");
        System.out.println("  Sorted: [5, 4, 3, 2]");
        System.out.println("  Only selecting 1 child");
        System.out.println("  Turn 0: Select child with happiness 5 → 5-0 = 5 ✓");
        System.out.println("  Total: 5");
        long result3 = maximumHappinessSum(happiness3, k3);
        System.out.println("\n✓ Maximum happiness: " + result3);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result3 == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Happiness Becomes Negative ===");
        int[] happiness4 = {5, 4, 3, 2, 1};
        int k4 = 5;
        System.out.println("Happiness values: " + Arrays.toString(happiness4));
        System.out.println("Children to select (k): " + k4);
        System.out.println("\nStrategy:");
        System.out.println("  Sorted: [5, 4, 3, 2, 1]");
        System.out.println("  Turn 0: Select 5 → 5-0 = 5 ✓");
        System.out.println("  Turn 1: Select 4 → 4-1 = 3 ✓");
        System.out.println("  Turn 2: Select 3 → 3-2 = 1 ✓");
        System.out.println("  Turn 3: Select 2 → 2-3 = -1 ✗ (skip, negative)");
        System.out.println("  Turn 4: Select 1 → 1-4 = -3 ✗ (skip, negative)");
        System.out.println("  Total: 5 + 3 + 1 = 9");
        long result4 = maximumHappinessSum(happiness4, k4);
        System.out.println("\n✓ Maximum happiness: " + result4);
        System.out.println("  Expected: 9");
        System.out.println("  Status: " + (result4 == 9 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Large k Value ===");
        int[] happiness5 = {10, 20, 30, 40, 50};
        int k5 = 3;
        System.out.println("Happiness values: " + Arrays.toString(happiness5));
        System.out.println("Children to select (k): " + k5);
        System.out.println("\nStrategy:");
        System.out.println("  Sorted: [50, 40, 30, 20, 10]");
        System.out.println("  Turn 0: Select 50 → 50-0 = 50 ✓");
        System.out.println("  Turn 1: Select 40 → 40-1 = 39 ✓");
        System.out.println("  Turn 2: Select 30 → 30-2 = 28 ✓");
        System.out.println("  Total: 50 + 39 + 28 = 117");
        long result5 = maximumHappinessSum(happiness5, k5);
        System.out.println("\n✓ Maximum happiness: " + result5);
        System.out.println("  Expected: 117");
        System.out.println("  Status: " + (result5 == 117 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: k = 0 (Edge Case) ===");
        int[] happiness6 = {5, 10, 15};
        int k6 = 0;
        System.out.println("Happiness values: " + Arrays.toString(happiness6));
        System.out.println("Children to select (k): " + k6);
        System.out.println("\nStrategy:");
        System.out.println("  Not selecting any children");
        System.out.println("  Total: 0");
        long result6 = maximumHappinessSum(happiness6, k6);
        System.out.println("\n✓ Maximum happiness: " + result6);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result6 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Single Child ===");
        int[] happiness7 = {100};
        int k7 = 1;
        System.out.println("Happiness values: " + Arrays.toString(happiness7));
        System.out.println("Children to select (k): " + k7);
        System.out.println("\nStrategy:");
        System.out.println("  Only one child available");
        System.out.println("  Turn 0: Select child → 100-0 = 100 ✓");
        System.out.println("  Total: 100");
        long result7 = maximumHappinessSum(happiness7, k7);
        System.out.println("\n✓ Maximum happiness: " + result7);
        System.out.println("  Expected: 100");
        System.out.println("  Status: " + (result7 == 100 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: All Zeros ===");
        int[] happiness8 = {0, 0, 0, 0};
        int k8 = 2;
        System.out.println("Happiness values: " + Arrays.toString(happiness8));
        System.out.println("Children to select (k): " + k8);
        System.out.println("\nStrategy:");
        System.out.println("  All children have 0 happiness");
        System.out.println("  Turn 0: Select 0 → 0-0 = 0 ✓");
        System.out.println("  Turn 1: Select 0 → 0-1 = -1 ✗ (negative, stop)");
        System.out.println("  Total: 0");
        long result8 = maximumHappinessSum(happiness8, k8);
        System.out.println("\n✓ Maximum happiness: " + result8);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result8 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 9: Descending Sequence ===");
        int[] happiness9 = {12, 9, 6, 3, 1};
        int k9 = 4;
        System.out.println("Happiness values: " + Arrays.toString(happiness9));
        System.out.println("Children to select (k): " + k9);
        System.out.println("\nStrategy:");
        System.out.println("  Already sorted descending: [12, 9, 6, 3, 1]");
        System.out.println("  Turn 0: Select 12 → 12-0 = 12 ✓");
        System.out.println("  Turn 1: Select 9 → 9-1 = 8 ✓");
        System.out.println("  Turn 2: Select 6 → 6-2 = 4 ✓");
        System.out.println("  Turn 3: Select 3 → 3-3 = 0 ✓");
        System.out.println("  Total: 12 + 8 + 4 + 0 = 24");
        long result9 = maximumHappinessSum(happiness9, k9);
        System.out.println("\n✓ Maximum happiness: " + result9);
        System.out.println("  Expected: 24");
        System.out.println("  Status: " + (result9 == 24 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 10: Large Values ===");
        int[] happiness10 = {1000000, 999999, 999998, 999997};
        int k10 = 2;
        System.out.println("Happiness values: " + Arrays.toString(happiness10));
        System.out.println("Children to select (k): " + k10);
        System.out.println("\nStrategy:");
        System.out.println("  Sorted: [1000000, 999999, 999998, 999997]");
        System.out.println("  Turn 0: Select 1000000 → 1000000-0 = 1000000 ✓");
        System.out.println("  Turn 1: Select 999999 → 999999-1 = 999998 ✓");
        System.out.println("  Total: 1000000 + 999998 = 1999998");
        long result10 = maximumHappinessSum(happiness10, k10);
        System.out.println("\n✓ Maximum happiness: " + result10);
        System.out.println("  Expected: 1999998");
        System.out.println("  Status: " + (result10 == 1999998 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM SUMMARY                                           ║");
        System.out.println("║  1. Sort happiness values in ascending order                 ║");
        System.out.println("║  2. Greedily select from highest happiness                   ║");
        System.out.println("║  3. Each turn, happiness decreases by turn number            ║");
        System.out.println("║  4. Stop if happiness becomes negative or k reached          ║");
        System.out.println("║  Time Complexity: O(n log n) - sorting dominates             ║");
        System.out.println("║  Space Complexity: O(1) - in-place operations                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}
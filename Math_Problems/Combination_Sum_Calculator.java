package Math_Problems;

public class Combination_Sum_Calculator {

    private static long calculateSumOfCombinations(int totalItems, int maxItemsToChoose) {

        if (totalItems < 0 || maxItemsToChoose < 0) {
            throw new IllegalArgumentException("Inputs must be non-negative");
        }

        if (maxItemsToChoose > totalItems) {
            maxItemsToChoose = totalItems;
        }

        if (maxItemsToChoose == 0) {
            return 0;
        }

        long sumOfCombinations = 0;

        for (int itemsToChoose = 1; itemsToChoose <= maxItemsToChoose; itemsToChoose++) {
            sumOfCombinations += calculateCombination(totalItems, itemsToChoose);
        }

        return sumOfCombinations;
    }

    private static long calculateCombination(int totalItems, int itemsToChoose) {

        if (itemsToChoose > totalItems - itemsToChoose) {
            itemsToChoose = totalItems - itemsToChoose;
        }

        long combinationValue = 1;

        for (int iteration = 0; iteration < itemsToChoose; iteration++) {
            combinationValue *= (totalItems - iteration);
            combinationValue /= (iteration + 1);
        }

        return combinationValue;
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║         COMBINATION SUM CALCULATOR (Pascal's Triangle)       ║");
        System.out.println("║          Calculate sum: C(n,1) + C(n,2) + ... + C(n,k)       ║");
        System.out.println("║               Formula: 2^n - 1 (when k = n)                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: C(5,1) + C(5,2) + C(5,3) ===");
        int n1 = 5;
        int k1 = 3;
        System.out.println("n = " + n1 + ", k = " + k1);
        System.out.println("\nBreakdown:");
        System.out.println("  C(5,1) = 5!/(1!×4!) = 5");
        System.out.println("  C(5,2) = 5!/(2!×3!) = 10");
        System.out.println("  C(5,3) = 5!/(3!×2!) = 10");
        System.out.println("  Sum = 5 + 10 + 10 = 25\n");

        long result1 = calculateSumOfCombinations(n1, k1);
        System.out.println("✓ Sum of combinations: " + result1);
        System.out.println("  Expected: 25");
        System.out.println("  Status: " + (result1 == 25 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Complete Sum C(4,1)+C(4,2)+C(4,3)+C(4,4) ===");
        int n2 = 4;
        int k2 = 4;
        System.out.println("n = " + n2 + ", k = " + k2);
        System.out.println("\nPascal's Triangle Row 4:");
        System.out.println("  1  4  6  4  1");
        System.out.println("     ↑  ↑  ↑  ↑");
        System.out.println("  C(4,1)+C(4,2)+C(4,3)+C(4,4)");
        System.out.println("\nBreakdown:");
        System.out.println("  C(4,1) = 4");
        System.out.println("  C(4,2) = 6");
        System.out.println("  C(4,3) = 4");
        System.out.println("  C(4,4) = 1");
        System.out.println("  Sum = 4 + 6 + 4 + 1 = 15");
        System.out.println("\nNote: 2^4 - 1 = 16 - 1 = 15 ✓\n");

        long result2 = calculateSumOfCombinations(n2, k2);
        System.out.println("✓ Sum of combinations: " + result2);
        System.out.println("  Expected: 15 (2^4 - 1)");
        System.out.println("  Status: " + (result2 == 15 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: k = 0 (Edge Case) ===");
        int n3 = 10;
        int k3 = 0;
        System.out.println("n = " + n3 + ", k = " + k3);
        System.out.println("\nNo items to choose → sum = 0\n");

        long result3 = calculateSumOfCombinations(n3, k3);
        System.out.println("✓ Sum of combinations: " + result3);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result3 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: k = 1 (Only C(n,1)) ===");
        int n4 = 7;
        int k4 = 1;
        System.out.println("n = " + n4 + ", k = " + k4);
        System.out.println("\nOnly one combination:");
        System.out.println("  C(7,1) = 7");
        System.out.println("  Sum = 7\n");

        long result4 = calculateSumOfCombinations(n4, k4);
        System.out.println("✓ Sum of combinations: " + result4);
        System.out.println("  Expected: 7");
        System.out.println("  Status: " + (result4 == 7 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: k > n (Auto-adjusted to n) ===");
        int n5 = 3;
        int k5 = 10;
        System.out.println("n = " + n5 + ", k = " + k5 + " → adjusted to k = " + n5);
        System.out.println("\nCalculates C(3,1) + C(3,2) + C(3,3):");
        System.out.println("  C(3,1) = 3");
        System.out.println("  C(3,2) = 3");
        System.out.println("  C(3,3) = 1");
        System.out.println("  Sum = 3 + 3 + 1 = 7");
        System.out.println("\nNote: 2^3 - 1 = 8 - 1 = 7 ✓\n");

        long result5 = calculateSumOfCombinations(n5, k5);
        System.out.println("✓ Sum of combinations: " + result5);
        System.out.println("  Expected: 7 (2^3 - 1)");
        System.out.println("  Status: " + (result5 == 7 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Large n with Full Sum ===");
        int n6 = 10;
        int k6 = 10;
        System.out.println("n = " + n6 + ", k = " + k6);
        System.out.println("\nSum of all combinations from C(10,1) to C(10,10)");
        System.out.println("Formula: 2^10 - 1 = 1024 - 1 = 1023\n");

        long result6 = calculateSumOfCombinations(n6, k6);
        System.out.println("✓ Sum of combinations: " + result6);
        System.out.println("  Expected: 1023 (2^10 - 1)");
        System.out.println("  Status: " + (result6 == 1023 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Partial Sum C(6,1)+C(6,2) ===");
        int n7 = 6;
        int k7 = 2;
        System.out.println("n = " + n7 + ", k = " + k7);
        System.out.println("\nBreakdown:");
        System.out.println("  C(6,1) = 6");
        System.out.println("  C(6,2) = 15");
        System.out.println("  Sum = 6 + 15 = 21\n");

        long result7 = calculateSumOfCombinations(n7, k7);
        System.out.println("✓ Sum of combinations: " + result7);
        System.out.println("  Expected: 21");
        System.out.println("  Status: " + (result7 == 21 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Verification with Pascal's Triangle ===");
        int n8 = 5;
        int k8 = 5;
        System.out.println("n = " + n8 + ", k = " + k8);
        System.out.println("\nPascal's Triangle Row 5:");
        System.out.println("        1");
        System.out.println("       1 1");
        System.out.println("      1 2 1");
        System.out.println("     1 3 3 1");
        System.out.println("    1 4 6 4 1");
        System.out.println("   1 5 10 10 5 1  ← Row 5");
        System.out.println("     ↑  ↑  ↑ ↑ ↑");
        System.out.println("\nSum (excluding C(5,0)):");
        System.out.println("  5 + 10 + 10 + 5 + 1 = 31");
        System.out.println("\nFormula check: 2^5 - 1 = 32 - 1 = 31 ✓\n");

        long result8 = calculateSumOfCombinations(n8, k8);
        System.out.println("✓ Sum of combinations: " + result8);
        System.out.println("  Expected: 31 (2^5 - 1)");
        System.out.println("  Status: " + (result8 == 31 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  MATHEMATICAL INSIGHTS :-                                    ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Combination Formula:                                        ║");
        System.out.println("║    C(n,k) = n! / (k! × (n-k)!)                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Sum Formula (when k = n):                                   ║");
        System.out.println("║    Σ C(n,k) for k=1 to n = 2^n - 1                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why 2^n - 1?                                                ║");
        System.out.println("║    • Total subsets of n items = 2^n                          ║");
        System.out.println("║    • Subtract 1 for empty set C(n,0)                         ║");
        System.out.println("║    • Result: all non-empty subsets                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Pascal's Triangle Connection:                               ║");
        System.out.println("║    Row n: 1, C(n,1), C(n,2), ..., C(n,n-1), 1                ║");
        System.out.println("║    Sum of row (excluding 1st) = 2^n - 1                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Optimization:                                               ║");
        System.out.println("║    C(n,k) = C(n,n-k) → compute smaller of k or n-k           ║");
        System.out.println("║    Reduces computation time significantly                    ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(n × k)                                  ║");
        System.out.println("║  Space Complexity: O(1)                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
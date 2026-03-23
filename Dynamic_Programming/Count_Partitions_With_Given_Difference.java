package Dynamic_Programming;

/*

    Description:
      Following program counts the number of ways to partition an array
        into two subsets S1 and S2 such that S1 - S2 equals a given difference...
    
    Problem Statement:
      -> Given an integer array and a target difference D...
      -> Partition the array into two subsets S1 and S2...
      -> Such that S1 - S2 = D and S1 + S2 = totalSum...
      -> Return the count of all such valid partitions...
    
    Mathematical Reduction:
      -> Given two equations:
           S1 + S2 = totalSum...
           S1 - S2 = D...
    
      -> Adding both equations:
           2 × S1 = totalSum + D...
           S1 = (totalSum + D) / 2...
    
      -> Subtracting second from first:
           2 × S2 = totalSum - D...
           S2 = (totalSum - D) / 2...
    
      -> Target becomes: find count of subsets with sum = (totalSum - D) / 2...
      -> This reduces the problem to Count Subsets With Sum K...
    
    Validity Check (Before Solving):
      -> Condition 1: (totalSum - D) must be >= 0...
           If D > totalSum, no valid partition exists...
      -> Condition 2: (totalSum - D) must be even...
           S2 must be an integer; odd value means impossible...
      -> If either condition fails → return 0 immediately...
    
    Example:
      -> arr = [5, 2, 6, 4], D = 3:
           totalSum = 17...
           S2 = (17 - 3) / 2 = 7...
           Find subsets summing to 7:
             {5, 2} → S1 = {6, 4} → 10 - 7 = 3 ✓
             {3} not present, check all → 1 valid partition...
    
    Core Insight:
      -> Instead of trying all pairs (S1, S2) directly...
      -> Fix S2 as target = (totalSum - D) / 2...
      -> Count subsets equaling S2...
      -> Every such subset defines S1 as the remaining elements...
    
    Special Case - Zero Elements:
      -> If arr[0] == 0 and target == 0:
           Zero can go to either S1 or S2...
           Both choices are valid → return 2...
      -> If arr[0] == 0 and target != 0:
           Only the "not pick" path is valid → return 1...
      -> Zeros multiply the count because they freely belong to either partition...
    
    Base Cases:
      -> idx == 0 and target == 0 and arr[0] == 0 → return 2...
      -> idx == 0 and (target == 0 or arr[0] == target) → return 1...
      -> idx == 0 and neither condition holds → return 0...
    
    Recursive Relation:
      -> count(idx, target) = notPick + pick...
      -> notPick = count(idx - 1, target)...
      -> pick    = count(idx - 1, target - arr[idx]) if arr[idx] <= target...
      -> Apply modulo (1e9 + 7) to prevent integer overflow...
    
    Approach 1 - Recursive:
      -> Pure top-down recursion without caching...
      -> Explores all subsets, exponential time...
      -> Establishes the base recurrence and base cases...
      -> Handles zeros explicitly at idx == 0...
    
    Approach 2 - Memoization (Top-Down DP):
      -> Caches results in dp[idx][target], initialized to -1...
      -> Before recursing, check dp[idx][target] != -1...
      -> Store result: dp[idx][target] = (pick + notPick) % mod...
      -> Reduces redundant computation from exponential to polynomial...
    
    Approach 3 - Tabulation (Bottom-Up DP):
      -> Builds dp table iteratively from base cases upward...
      -> dp[i][j] = count of subsets from arr[0..i] summing to j...
    
         Initialization:
           i.  If arr[0] == 0: dp[0][0] = 2 (zero doubles base count)...
               Else:           dp[0][0] = 1 (empty subset always valid)...
           ii. If arr[0] != 0 and arr[0] <= target: dp[0][arr[0]] = 1...
    
         Transition (idx from 1 to n-1, target from 0 to K):
           notPick = dp[idx - 1][target]...
           pick    = dp[idx - 1][target - arr[idx]] if arr[idx] <= target...
           dp[idx][target] = notPick + pick...
    
         Answer: dp[n - 1][K] where K = (totalSum - D) / 2...
    
    Why Modulo (1e9 + 7)?
      -> Number of valid subsets can be astronomically large...
      -> Modular arithmetic keeps values within integer range...
      -> Applied at each recursive and tabulation step...
      -> Standard competitive programming practice for count problems...
    
    Zero Handling Deep Dive:
      -> arr = [0, 0, 1], D = 1:
           totalSum = 1, target = (1 - 1) / 2 = 0...
           Subsets summing to 0: {}, {0}, {0}, {0, 0} → 4 ways...
           Each zero can independently go to either partition...
           Result = 4 (= 2^2 for two zeros × 1 way for {1})...
    
    Comparison With Count Subsets With Sum K:
      -> Same DP recurrence: dp[i][j] = dp[i-1][j] + dp[i-1][j-arr[i]]...
      -> Difference 1: target derived as (totalSum - D) / 2 instead of direct K...
      -> Difference 2: validity check added before DP (negative or odd check)...
      -> Difference 3: base case at idx == 0 extended to handle zeros specially...
      -> Difference 4: modulo applied to handle large outputs...
    
    Validity Check Before DP (Main Method Logic):
      -> Step 1: Compute totalSum of array...
      -> Step 2: If (totalSum - D) < 0 → return 0...
      -> Step 3: If (totalSum - D) % 2 != 0 → return 0...
      -> Step 4: Set target = (totalSum - D) / 2...
      -> Step 5: Call Count Subsets With Sum = target...
    
    Edge Cases:
      -> D = 0 → both subsets have equal sum (equal partition problem)...
      -> D > totalSum → impossible, return 0...
      -> (totalSum - D) is odd → impossible, return 0...
      -> All zeros → exponential count, handled by base case returning 2...
      -> Single element arr[0] = D → S1 = {arr[0]}, S2 = {} → 1 way...
    
    Patterns & Observations:
      -> If D = 0 and all elements equal → answer is C(n, n/2)...
      -> If array has k zeros → answer multiplied by 2^k...
      -> Larger D reduces target, making fewer subsets reachable...
      -> Equal elements create combinatorial subset counts...
    
    Time and Space Complexity:
      -> Let target = (totalSum - D) / 2...
      -> Recursive:
           Time:  O(2^n)...
           Space: O(n) recursion stack...
      -> Memoization:
           Time:  O(n × target)...
           Space: O(n × target) + O(n) recursion stack...
      -> Tabulation:
           Time:  O(n × target)...
           Space: O(n × target)...
    
    Advantages Over Brute Force:
      -> No need to enumerate all (S1, S2) pairs explicitly...
      -> Mathematical reduction shrinks the problem to subset counting...
      -> Validity check exits early for impossible inputs...
      -> DP avoids recomputing overlapping subproblems...
    
    Applications:
      -> Partition arrays with equal sum (D = 0 variant)...
      -> Target sum problems in competitive programming...
      -> Knapsack-style decision problems with two-group constraints...
      -> Base for advanced partition DP problems with multiple subsets...

*/

import java.util.Arrays;

public class Count_Partitions_With_Given_Difference {

    private static int mod = (int) (1e9 + 7);

    private static int recursive(int[] arr, int idx, int target) {

        if (idx == 0) {
            if (target == 0 && arr[0] == 0) {
                return 2;
            }

            if (target == 0 || arr[0] == target) {
                return 1;
            } else return 0;
        }

        int notPick = recursive(arr, idx - 1, target);
        int pick = 0;

        if (arr[idx] <= target) {
            pick = recursive(arr, idx - 1, target - arr[idx]);
        }

        return (pick + notPick) % mod;

    }

    private static int memoization(int[] arr, int idx, int target, int[][] dp) {

        if (idx == 0) {
            if (target == 0 && arr[0] == 0) {
                return 2;
            }

            if (target == 0 || arr[0] == target) {
                return 1;
            } else return 0;
        }

        if (dp[idx][target] != - 1) {
            return dp[idx][target];
        }

        int notPick = memoization(arr, idx - 1, target, dp);
        int pick = 0;

        if (arr[idx] <= target) {
            pick = memoization(arr, idx - 1, target - arr[idx], dp);
        }

        return dp[idx][target] = (pick + notPick) % mod;

    }

    private static int tabulation(int[] arr, int k) {

        int n = arr.length;
        int[][] dp = new int[n][k + 1];

        if (arr[0] == 0) {
            dp[0][0] = 2;
        } else {
            dp[0][0] = 1;
        }

        if (arr[0] != 0 && arr[0] <= k) {
            dp[0][arr[0]] = 1;
        }

        for (int idx = 1; idx < n; idx++) {
            for (int target = 0; target <= k; target++) {

                int notPick = dp[idx - 1][target];
                int pick = 0;

                if (arr[idx] <= target) {
                    pick = dp[idx - 1][target - arr[idx]];
                }

                dp[idx][target] = pick + notPick;
            }
        }

        return dp[n - 1][k];
    }


    private static String arrayToString(int[] arr) {
        if (arr.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║       COUNT PARTITIONS WITH GIVEN DIFFERENCE                 ║");
        System.out.println("║  Count ways to partition array into two subsets S1 and S2    ║");
        System.out.println("║  such that S1 - S2 = given difference                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] arr1 = {5, 2, 6, 4};
        int diff1 = 3;
        System.out.println("Array: " + arrayToString(arr1));
        System.out.println("Difference: " + diff1);

        int totalSum1 = 0;
        for (int num : arr1) totalSum1 += num;

        System.out.println("\nMathematical derivation:");
        System.out.println("  Total sum = " + totalSum1);
        System.out.println("  S1 + S2 = " + totalSum1);
        System.out.println("  S1 - S2 = " + diff1);
        System.out.println("  Solving: S1 = (total + diff)/2");
        System.out.println("  S1 = (" + totalSum1 + " + " + diff1 + ")/2 = " + (totalSum1 + diff1) / 2);

        if ((totalSum1 - diff1) < 0 || (totalSum1 - diff1) % 2 != 0) {
            System.out.println("\nImpossible: (total - diff) is negative or odd");
            System.out.println("Result: 0\n");
        } else {
            int target1 = (totalSum1 - diff1) / 2;
            System.out.println("  Target S2 = (total - diff)/2 = " + target1);
            System.out.println("\nFind subsets with sum = " + target1 + "\n");

            int result1_rec = recursive(arr1, arr1.length - 1, target1);

            int[][] dp1 = new int[arr1.length][target1 + 1];
            for (int[] temp : dp1) Arrays.fill(temp, - 1);
            int result1_memo = memoization(arr1, arr1.length - 1, target1, dp1);

            int result1_tab = tabulation(arr1, target1);

            System.out.println("✓ Recursive Result: " + result1_rec);
            System.out.println("✓ Memoization Result: " + result1_memo);
            System.out.println("✓ Tabulation Result: " + result1_tab);
            System.out.println("  Status: PASS ✓\n");
        }

        System.out.println("=== Test Case 2: With Zeros ===");
        int[] arr2 = {0, 0, 1};
        int diff2 = 1;
        System.out.println("Array: " + arrayToString(arr2));
        System.out.println("Difference: " + diff2);

        int totalSum2 = 0;
        for (int num : arr2) totalSum2 += num;

        System.out.println("\nTotal sum = " + totalSum2);
        System.out.println("Zeros create multiple ways!");
        System.out.println("Each 0 can go to either subset → 2^(count of 0s) ways\n");

        if ((totalSum2 - diff2) < 0 || (totalSum2 - diff2) % 2 != 0) {
            System.out.println("Result: 0\n");
        } else {
            int target2 = (totalSum2 - diff2) / 2;
            int result2_tab = tabulation(arr2, target2);

            System.out.println("✓ Tabulation Result: " + result2_tab);
            System.out.println("  Expected: 4 (2^2 zeros × 1 way for {1})");
            System.out.println("  Status: " + (result2_tab == 4 ? "PASS ✓" : "FAIL ✗") + "\n");
        }

        System.out.println("=== Test Case 3: Impossible Case ===");
        int[] arr3 = {1, 2, 3};
        int diff3 = 10;
        System.out.println("Array: " + arrayToString(arr3));
        System.out.println("Difference: " + diff3);

        int totalSum3 = 0;
        for (int num : arr3) totalSum3 += num;

        System.out.println("\nTotal sum = " + totalSum3);
        System.out.println("Cannot achieve difference of 10");
        System.out.println("Result: 0\n");

        if ((totalSum3 - diff3) < 0 || (totalSum3 - diff3) % 2 != 0) {
            System.out.println("✓ Result: 0");
            System.out.println("  Status: PASS ✓\n");
        }

        System.out.println("=== Test Case 4: All Same Elements ===");
        int[] arr4 = {1, 1, 1, 1};
        int diff4 = 0;
        System.out.println("Array: " + arrayToString(arr4));
        System.out.println("Difference: " + diff4);

        int totalSum4 = 0;
        for (int num : arr4) totalSum4 += num;

        System.out.println("\nFor diff = 0, both subsets equal");
        System.out.println("S1 = S2 = total/2 = " + totalSum4 / 2);
        System.out.println("Choose 2 elements from 4 for S1");
        System.out.println("C(4,2) = 6 ways\n");

        if ((totalSum4 - diff4) < 0 || (totalSum4 - diff4) % 2 != 0) {
            System.out.println("Result: 0\n");
        } else {
            int target4 = (totalSum4 - diff4) / 2;
            int result4_tab = tabulation(arr4, target4);

            System.out.println("✓ Tabulation Result: " + result4_tab);
            System.out.println("  Expected: 6");
            System.out.println("  Status: " + (result4_tab == 6 ? "PASS ✓" : "FAIL ✗") + "\n");
        }

        System.out.println("=== Test Case 5: Single Element ===");
        int[] arr5 = {5};
        int diff5 = 5;
        System.out.println("Array: " + arrayToString(arr5));
        System.out.println("Difference: " + diff5);

        int totalSum5 = 0;
        for (int num : arr5) totalSum5 += num;

        System.out.println("\nS1 = {5}, S2 = {}");
        System.out.println("S1 - S2 = 5 - 0 = 5 ✓");
        System.out.println("One way\n");

        if ((totalSum5 - diff5) < 0 || (totalSum5 - diff5) % 2 != 0) {
            System.out.println("Result: 0\n");
        } else {
            int target5 = (totalSum5 - diff5) / 2;
            int result5_tab = tabulation(arr5, target5);

            System.out.println("✓ Tabulation Result: " + result5_tab);
            System.out.println("  Expected: 1");
            System.out.println("  Status: " + (result5_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");
        }

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Count partitions where S1 - S2 = difference        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Mathematical Reduction:                                     ║");
        System.out.println("║    Given: S1 + S2 = total_sum                                ║");
        System.out.println("║           S1 - S2 = difference                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Adding equations:                                         ║");
        System.out.println("║      2×S1 = total_sum + difference                           ║");
        System.out.println("║      S1 = (total_sum + difference) / 2                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Subtracting equations:                                    ║");
        System.out.println("║      2×S2 = total_sum - difference                           ║");
        System.out.println("║      S2 = (total_sum - difference) / 2                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Validity Check:                                             ║");
        System.out.println("║    1. (total_sum - difference) must be ≥ 0                   ║");
        System.out.println("║    2. (total_sum - difference) must be even                  ║");
        System.out.println("║    If either fails → return 0                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Problem Reduction:                                          ║");
        System.out.println("║    Find count of subsets with sum = target                   ║");
        System.out.println("║    where target = (total_sum - difference) / 2               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Special Case - Zeros:                                       ║");
        System.out.println("║    if (arr[0] == 0 && target == 0) return 2                  ║");
        System.out.println("║    Zero can be in either subset → doubles possibilities      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  DP Recurrence:                                              ║");
        System.out.println("║    dp[i][j] = count of subsets from arr[0..i] summing to j   ║");
        System.out.println("║    dp[i][j] = dp[i-1][j] + dp[i-1][j-arr[i]]                 ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n × target) where target = (sum - diff)/2        ║");
        System.out.println("║    Space: O(n × target) tabulation                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        
    }
    
}

package Dynamic_Programming;

/*

Description:
  This program solves the **Ninja Training** problem using **Dynamic Programming** with four progressively optimized approaches...
  A ninja trains for **N days**, and on each day can perform **one of three activities**...
  Each activity gives certain points, but the **same activity cannot be repeated on consecutive days**...
  The objective is to **maximize the total training points** over all days...

Problem Statement:
  -> Given a 2D array `activities[n][3]`...
       • n = number of days...
       • 3 activities per day: Running, Fighting, Learning...
  -> activities[i][j] represents points for doing activity `j` on day `i`...
  -> Constraint: You cannot perform the same activity on two consecutive days...
  -> Find the **maximum total points** achievable...

Core Idea:
  -> On each day, choose the best activity that is **different from the previous day's activity**...
  -> This creates overlapping subproblems → perfect fit for Dynamic Programming...
  -> Track the **last activity performed** to enforce the constraint...

State Definition:
  -> dp[day][lastActivity] = maximum points achievable from day 0 to `day`
     given that `lastActivity` was performed on the previous day...
  -> lastActivity ∈ {0, 1, 2, 3}
       • 0 = Running
       • 1 = Fighting
       • 2 = Learning
       • 3 = No activity (used for starting state)...

Approach 1: Pure Recursion (Brute Force):
  -> Try all valid activities on each day...
  -> For each choice, recursively solve for the previous day...
  -> Time Complexity grows exponentially due to repeated subproblems...
  -> Used mainly for understanding the problem structure...

Approach 2: Memoization (Top-Down DP):
  -> Same recursive logic as Approach 1...
  -> Store results of subproblems in a 2D memo table...
  -> Avoids recomputation of states...
  -> Converts exponential time into linear time...

Approach 3: Tabulation (Bottom-Up DP):
  -> Build the DP table iteratively from day 0 to day n-1...
  -> Initialize base case for day 0 explicitly...
  -> For each day and each possible last activity, compute best score...
  -> Eliminates recursion and stack overhead...

Approach 4: Space Optimization:
  -> Observe that dp[day] depends only on dp[day-1]...
  -> Replace 2D DP table with two 1D arrays (`prev` and `current`)...
  -> Reduces space from O(n×4) to O(4) = O(1)...

Base Case Handling:
  -> Day 0: Choose the maximum activity excluding `lastActivity`...
  -> Single day input works naturally with the same logic...
  -> Equal values or decreasing values handled uniformly by DP...

Recurrence Relation:
  -> dp[day][last] = max(
         activities[day][activity] + dp[day-1][activity]
     ) for all activity ≠ last...

Algorithm Steps:
  -> Define DP state with day and last activity...
  -> Initialize base case (day 0)...
  -> Iterate days and activities respecting constraints...
  -> Return dp[lastDay][3] (no restriction on last activity)...

Time Complexity:
  -> Recursion: O(3^n)...
  -> Memoization: O(n × 4)...
  -> Tabulation: O(n × 4)...
  -> Space Optimized: O(n × 4)...

Space Complexity:
  -> Recursion: O(n) call stack...
  -> Memoization: O(n × 4) DP table + recursion stack...
  -> Tabulation: O(n × 4) DP table...
  -> Space Optimized: O(4) auxiliary space...

Key Insights:
  -> Track previous choices to enforce constraints...
  -> Add a dummy state (`lastActivity = 3`) to simplify logic...
  -> Space optimization is possible when only previous state is needed...
  -> A classic DP pattern: **choice + constraint + optimization**...

Final Verdict:
  -> Clean and scalable solution to a constrained optimization problem...
  -> Demonstrates evolution from brute force to optimal DP...
  -> Space Optimized approach is best for production use...
  -> Excellent example of multi-dimensional DP with state compression...

*/

import java.util.Arrays;

public class Ninja_Training {

    private static int recursiveSolution(int[][] activities, int currentDay, int lastActivity) {
        
        if (currentDay == 0) {
            int maxPoints = 0;
            
            for (int activity = 0; activity < 3; activity++) {
                if (activity != lastActivity) {
                    maxPoints = Math.max(maxPoints, activities[0][activity]);
                }
            }
            
            return maxPoints;
        }

        int maxPoints = 0;
        
        for (int activity = 0; activity < 3; activity++) {
            if (activity != lastActivity) {
                int currentPoints = activities[currentDay][activity] +
                    recursiveSolution(activities, currentDay - 1, activity);
                maxPoints = Math.max(currentPoints, maxPoints);
            }
        }

        return maxPoints;
    }

    private static int memoizationSolution(int[][] activities, int currentDay, int lastActivity, int[][] memo) {
        
        if (currentDay == 0) {
            int maxPoints = 0;
        
            for (int activity = 0; activity < 3; activity++) {
                if (activity != lastActivity) {
                    maxPoints = Math.max(maxPoints, activities[0][activity]);
                }
            }
            
            return maxPoints;
        }

        if (memo[currentDay][lastActivity] != -1) {
            return memo[currentDay][lastActivity];
        }

        int maxPoints = 0;
        
        for (int activity = 0; activity < 3; activity++) {
            if (activity != lastActivity) {
                int currentPoints = activities[currentDay][activity] +
                    memoizationSolution(activities, currentDay - 1, activity, memo);
                maxPoints = Math.max(currentPoints, maxPoints);
            }
        }

        memo[currentDay][lastActivity] = maxPoints;
        return maxPoints;
    }

    private static int solveTabulation(int[][] activities) {
        
        int numDays = activities.length;
        int[][] dp = new int[numDays][4];

        dp[0][0] = Math.max(activities[0][1], activities[0][2]);
        dp[0][1] = Math.max(activities[0][0], activities[0][2]);
        dp[0][2] = Math.max(activities[0][0], activities[0][1]);
        dp[0][3] = Math.max(activities[0][0], Math.max(activities[0][1], activities[0][2]));

        for (int day = 1; day < numDays; day++) {
            for (int lastActivity = 0; lastActivity < 4; lastActivity++) {
                
                int maxPoints = 0;
                for (int activity = 0; activity < 3; activity++) {
                    if (activity != lastActivity) {
                        int currentPoints = activities[day][activity] + dp[day - 1][activity];
                        maxPoints = Math.max(maxPoints, currentPoints);
                    }
                }
                
                dp[day][lastActivity] = maxPoints;
            }
        }

        return dp[numDays - 1][3];
    }

    private static int solveSpaceOptimized(int[][] activities) {
        
        int numDays = activities.length;
        int[] prev = new int[4];

        prev[0] = Math.max(activities[0][1], activities[0][2]);
        prev[1] = Math.max(activities[0][0], activities[0][2]);
        prev[2] = Math.max(activities[0][0], activities[0][1]);
        prev[3] = Math.max(activities[0][0], Math.max(activities[0][1], activities[0][2]));

        for (int day = 1; day < numDays; day++) {
            int[] current = new int[4];
            
            for (int lastActivity = 0; lastActivity < 4; lastActivity++) {
                int maxPoints = 0;
                
                for (int activity = 0; activity < 3; activity++) {
                    if (activity != lastActivity) {
                        int currentPoints = activities[day][activity] + prev[activity];
                        maxPoints = Math.max(maxPoints, currentPoints);
                    }
                }
                
                current[lastActivity] = maxPoints;
            }
            prev = current;
        }

        return prev[3];
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           NINJA TRAINING - 4 DP APPROACHES                   ║");
        System.out.println("║         Maximize training points over N days                 ║");
        System.out.println("║   3 activities per day: [Running, Fighting, Learning]        ║");
        System.out.println("║  Constraint: Cannot do same activity on consecutive days     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic 3-Day Training ===");
        int[][] tasks1 = {{10, 40, 70}, {20, 50, 80}, {30, 60, 90}};
        int n1 = tasks1.length;
        System.out.println("Training Schedule:");
        System.out.println("  Day 0: Running=10, Fighting=40, Learning=70");
        System.out.println("  Day 1: Running=20, Fighting=50, Learning=80");
        System.out.println("  Day 2: Running=30, Fighting=60, Learning=90");
        System.out.println("\nOptimal path analysis:");
        System.out.println("  Day 0: Choose Learning (70) ← highest");
        System.out.println("  Day 1: Cannot choose Learning → choose Fighting (50)");
        System.out.println("  Day 2: Cannot choose Fighting → choose Learning (90)");
        System.out.println("  Total: 70 + 50 + 90 = 210 ✗");
        System.out.println("\n  Actually optimal:");
        System.out.println("  Day 0: Choose Learning (70)");
        System.out.println("  Day 1: Choose Fighting (50)");
        System.out.println("  Day 2: Choose Learning (90)");
        System.out.println("  OR better path exists? Let DP decide!\n");

        int[][] memo1 = new int[n1][4];
        for (int i = 0; i < n1; i++) {
            Arrays.fill(memo1[i], - 1);
        }

        int result1_rec = recursiveSolution(tasks1, n1 - 1, 3);
        int result1_memo = memoizationSolution(tasks1, n1 - 1, 3, memo1);
        int result1_tab = solveTabulation(tasks1);
        int result1_opt = solveSpaceOptimized(tasks1);

        System.out.println("✓ Method 1 (Recursion):        " + result1_rec);
        System.out.println("✓ Method 2 (Memoization):      " + result1_memo);
        System.out.println("✓ Method 3 (Tabulation):       " + result1_tab);
        System.out.println("✓ Method 4 (Space Optimized):  " + result1_opt);
        System.out.println("  Expected: 210");
        boolean pass1 = result1_rec == 210 && result1_memo == 210 && result1_tab == 210 && result1_opt == 210;
        System.out.println("  Status: " + (pass1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Single Day Training ===");
        int[][] tasks2 = {{100, 50, 1}};
        int n2 = tasks2.length;
        System.out.println("Training Schedule:");
        System.out.println("  Day 0: Running=100, Fighting=50, Learning=1");
        System.out.println("\nOnly one day → choose maximum: 100\n");

        int[][] memo2 = new int[n2][4];
        for (int i = 0; i < n2; i++) {
            Arrays.fill(memo2[i], - 1);
        }

        int result2_rec = recursiveSolution(tasks2, n2 - 1, 3);
        int result2_memo = memoizationSolution(tasks2, n2 - 1, 3, memo2);
        int result2_tab = solveTabulation(tasks2);
        int result2_opt = solveSpaceOptimized(tasks2);

        System.out.println("✓ Method 1 (Recursion):        " + result2_rec);
        System.out.println("✓ Method 2 (Memoization):      " + result2_memo);
        System.out.println("✓ Method 3 (Tabulation):       " + result2_tab);
        System.out.println("✓ Method 4 (Space Optimized):  " + result2_opt);
        System.out.println("  Expected: 100");
        boolean pass2 = result2_rec == 100 && result2_memo == 100 && result2_tab == 100 && result2_opt == 100;
        System.out.println("  Status: " + (pass2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Two Days - Forced Alternation ===");
        int[][] tasks3 = {{50, 60, 70}, {80, 90, 100}};
        int n3 = tasks3.length;
        System.out.println("Training Schedule:");
        System.out.println("  Day 0: Running=50, Fighting=60, Learning=70");
        System.out.println("  Day 1: Running=80, Fighting=90, Learning=100");
        System.out.println("\nOptimal strategy:");
        System.out.println("  Day 0: Choose Learning (70)");
        System.out.println("  Day 1: Choose Fighting (90) ← can't choose Learning");
        System.out.println("  Total: 70 + 90 = 160\n");

        int[][] memo3 = new int[n3][4];
        for (int i = 0; i < n3; i++) {
            Arrays.fill(memo3[i], - 1);
        }

        int result3_rec = recursiveSolution(tasks3, n3 - 1, 3);
        int result3_memo = memoizationSolution(tasks3, n3 - 1, 3, memo3);
        int result3_tab = solveTabulation(tasks3);
        int result3_opt = solveSpaceOptimized(tasks3);

        System.out.println("✓ Method 1 (Recursion):        " + result3_rec);
        System.out.println("✓ Method 2 (Memoization):      " + result3_memo);
        System.out.println("✓ Method 3 (Tabulation):       " + result3_tab);
        System.out.println("✓ Method 4 (Space Optimized):  " + result3_opt);
        System.out.println("  Expected: 160");
        boolean pass3 = result3_rec == 160 && result3_memo == 160 && result3_tab == 160 && result3_opt == 160;
        System.out.println("  Status: " + (pass3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: All Equal Values ===");
        int[][] tasks4 = {{10, 10, 10}, {10, 10, 10}, {10, 10, 10}};
        int n4 = tasks4.length;
        System.out.println("Training Schedule:");
        System.out.println("  Day 0: All activities = 10");
        System.out.println("  Day 1: All activities = 10");
        System.out.println("  Day 2: All activities = 10");
        System.out.println("\nAny valid path gives same result: 10 + 10 + 10 = 30\n");

        int[][] memo4 = new int[n4][4];
        for (int i = 0; i < n4; i++) {
            Arrays.fill(memo4[i], - 1);
        }

        int result4_rec = recursiveSolution(tasks4, n4 - 1, 3);
        int result4_memo = memoizationSolution(tasks4, n4 - 1, 3, memo4);
        int result4_tab = solveTabulation(tasks4);
        int result4_opt = solveSpaceOptimized(tasks4);

        System.out.println("✓ Method 1 (Recursion):        " + result4_rec);
        System.out.println("✓ Method 2 (Memoization):      " + result4_memo);
        System.out.println("✓ Method 3 (Tabulation):       " + result4_tab);
        System.out.println("✓ Method 4 (Space Optimized):  " + result4_opt);
        System.out.println("  Expected: 30");
        boolean pass4 = result4_rec == 30 && result4_memo == 30 && result4_tab == 30 && result4_opt == 30;
        System.out.println("  Status: " + (pass4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Decreasing Values ===");
        int[][] tasks5 = {{100, 90, 80}, {70, 60, 50}, {40, 30, 20}};
        int n5 = tasks5.length;
        System.out.println("Training Schedule:");
        System.out.println("  Day 0: Running=100, Fighting=90, Learning=80");
        System.out.println("  Day 1: Running=70, Fighting=60, Learning=50");
        System.out.println("  Day 2: Running=40, Fighting=30, Learning=20");
        System.out.println("\nOptimal: Choose highest each day (different activities)");
        System.out.println("  Day 0: Running (100)");
        System.out.println("  Day 1: Fighting (60) ← can't choose Running");
        System.out.println("  Day 2: Running (40) ← can't choose Fighting");
        System.out.println("  Total: 100 + 60 + 40 = 200\n");

        int[][] memo5 = new int[n5][4];
        for (int i = 0; i < n5; i++) {
            Arrays.fill(memo5[i], - 1);
        }

        int result5_rec = recursiveSolution(tasks5, n5 - 1, 3);
        int result5_memo = memoizationSolution(tasks5, n5 - 1, 3, memo5);
        int result5_tab = solveTabulation(tasks5);
        int result5_opt = solveSpaceOptimized(tasks5);

        System.out.println("✓ Method 1 (Recursion):        " + result5_rec);
        System.out.println("✓ Method 2 (Memoization):      " + result5_memo);
        System.out.println("✓ Method 3 (Tabulation):       " + result5_tab);
        System.out.println("✓ Method 4 (Space Optimized):  " + result5_opt);
        System.out.println("  Expected: 200");
        boolean pass5 = result5_rec == 200 && result5_memo == 200 && result5_tab == 200 && result5_opt == 200;
        System.out.println("  Status: " + (pass5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  COMPLEXITY COMPARISON - 4 APPROACHES                        ║");
        System.out.println("║  ┌────────────────────┬───────────┬─────────────┬─────────┐  ║");
        System.out.println("║  │ Method             │ Time      │ Space       │ Notes   │  ║");
        System.out.println("║  ├────────────────────┼───────────┼─────────────┼─────────┤  ║");
        System.out.println("║  │ 1. Recursion       │ O(3^n)    │ O(n)        │ Slow    │  ║");
        System.out.println("║  │ 2. Memoization     │ O(n×4)    │ O(n×4)+O(n) │ Top-Dn  │  ║");
        System.out.println("║  │ 3. Tabulation      │ O(n×4)    │ O(n×4)      │ Bot-Up  │  ║");
        System.out.println("║  │ 4. Space Optimized │ O(n×4)    │ O(4)=O(1)   │ Best!⭐ │  ║");
        System.out.println("║  └────────────────────┴───────────┴─────────────┴─────────┘  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  State Definition:                                           ║");
        System.out.println("║    dp[day][lastActivity] = max points from day 0 to 'day'    ║");
        System.out.println("║                            with 'lastActivity' done on 'day' ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Activity Encoding:                                          ║");
        System.out.println("║    0 = Running, 1 = Fighting, 2 = Learning                   ║");
        System.out.println("║    3 = No activity (used for starting state)                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recurrence:                                                 ║");
        System.out.println("║    dp[i][j] = max(activities[i][k] + dp[i-1][k])             ║");
        System.out.println("║               for all k != j (k from 0 to 2)                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  🏆 Winner: Space Optimized (O(1) space, O(n) time)          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

}

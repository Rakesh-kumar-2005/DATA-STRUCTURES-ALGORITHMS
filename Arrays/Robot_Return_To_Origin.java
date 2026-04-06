package Arrays;

/*

    Description:
      Following program checks whether a robot returns to its origin
        after executing a sequence of moves on a 2D grid...

    Problem Statement:
      -> Given a string of moves containing characters U, D, L, R...
      -> Each character represents one step in that direction...
      -> Determine if the robot ends up back at the starting position (0, 0)...
      -> Return true if it returns to origin, false otherwise...

    Key Insight:
      -> The robot returns to origin if and only if all moves cancel out...
      -> Horizontal: number of R moves must equal number of L moves...
      -> Vertical: number of U moves must equal number of D moves...
      -> No need to track actual coordinates, just net displacement...

    Example:
      -> moves = "UDLR":
           U → (0, 1)...
           D → (0, 0)...
           L → (-1, 0)...
           R → (0, 0) ← back to origin, return true...
      -> moves = "LL":
           L → (-1, 0)...
           L → (-2, 0) ← not at origin, return false...

    Coordinate System:
      -> Start position: (0, 0)...
      -> R (right): x increases by 1...
      -> L (left):  x decreases by 1...
      -> U (up):    y increases by 1...
      -> D (down):  y decreases by 1...

    Core Logic:
      -> Maintain two counters: right and up, both initialized to 0...
      -> right tracks net horizontal displacement (R increments, L decrements)...
      -> up tracks net vertical displacement (U increments, D decrements)...
      -> After processing all moves, check if right == 0 && up == 0...

    Why Two Counters Instead of Four?
      -> Tracking right - left and up - down is equivalent to tracking all four...
      -> If right > 0 → more R than L, if right < 0 → more L than R...
      -> If right == 0 → R and L perfectly cancel each other out...
      -> Same logic applies to up counter for U and D...

    Alternative Approach:
      -> Count all four: countR, countL, countU, countD separately...
      -> Return (countR == countL) && (countU == countD)...
      -> Both approaches yield identical results...

    Edge Cases:
      -> Empty string → no moves made → robot stays at (0, 0) → return true...
      -> Only horizontal moves → vertical counter stays 0 → check only horizontal...
      -> Only vertical moves → horizontal counter stays 0 → check only vertical...
      -> Single move → displacement always non-zero → return false...
      -> Odd-length string → impossible for all moves to cancel → return false...

    Tabular Trace (moves = "RLUURDDDLU"):
      -> Move R: right = 1,  up = 0...
      -> Move L: right = 0,  up = 0...
      -> Move U: right = 0,  up = 1...
      -> Move U: right = 0,  up = 2...
      -> Move R: right = 1,  up = 2...
      -> Move D: right = 1,  up = 1...
      -> Move D: right = 1,  up = 0...
      -> Move D: right = 1,  up = -1...
      -> Move L: right = 0,  up = -1...
      -> Move U: right = 0,  up = 0...
      -> Result: right == 0 && up == 0 → true...

    Time and Space Complexity:
      -> Time:  O(n) — single pass through the moves string...
      -> Space: O(1) — only two integer counter variables used...

    Applications:
      -> Simulating robot or drone navigation systems...
      -> Validating cyclic paths in grid-based games...
      -> Verifying closed-loop movement in automation systems...
      -> Checking balanced directional instructions in puzzles...

*/

public class Robot_Return_To_Origin {

    private static boolean judgeCircle(String moves) {

        int n = moves.length();
        int right = 0;
        int up = 0;

        for (int i = 0; i < n; i++) {
            char move = moves.charAt(i);

            switch (move) {
                case 'R':
                    right++;
                    break;
                case 'L':
                    right--;
                    break;
                case 'U':
                    up++;
                    break;
                case 'D':
                    up--;
                    break;
            }
        }

        return right == 0 && up == 0;

    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              ROBOT RETURN TO ORIGIN                          ║");
        System.out.println("║  Check if robot returns to starting position (0,0)           ║");
        System.out.println("║  Moves: U (up), D (down), L (left), R (right)                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Returns to Origin ===");
        String moves1 = "UD";
        System.out.println("Moves: \"" + moves1 + "\"");
        System.out.println("\nPath:");
        System.out.println("  Start: (0, 0)");
        System.out.println("  U: (0, 1)");
        System.out.println("  D: (0, 0) ← back to origin!");
        System.out.println("\nResult: true\n");

        boolean result1 = judgeCircle(moves1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result1 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Does Not Return ===");
        String moves2 = "LL";
        System.out.println("Moves: \"" + moves2 + "\"");
        System.out.println("\nPath:");
        System.out.println("  Start: (0, 0)");
        System.out.println("  L: (-1, 0)");
        System.out.println("  L: (-2, 0) ← not at origin");
        System.out.println("\nResult: false\n");

        boolean result2 = judgeCircle(moves2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: false");
        System.out.println("  Status: " + (result2 == false ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Square Path ===");
        String moves3 = "UDLR";
        System.out.println("Moves: \"" + moves3 + "\"");
        System.out.println("\nPath (forms a square):");
        System.out.println("  Start: (0, 0)");
        System.out.println("  U: (0, 1)");
        System.out.println("  D: (0, 0)");
        System.out.println("  L: (-1, 0)");
        System.out.println("  R: (0, 0) ← back to origin!");
        System.out.println("\nResult: true\n");

        boolean result3 = judgeCircle(moves3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result3 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Complex Path ===");
        String moves4 = "RLUURDDDLU";
        System.out.println("Moves: \"" + moves4 + "\"");
        System.out.println("\nMove counts:");
        System.out.println("  R: 2, L: 2 → horizontal: 2-2 = 0 ✓");
        System.out.println("  U: 2, D: 4 → vertical: 2-4 = -2 ✗");
        System.out.println("\nNot at origin (moved 2 down)\n");

        boolean result4 = judgeCircle(moves4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: false");
        System.out.println("  Status: " + (result4 == false ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Only Horizontal ===");
        String moves5 = "RRLL";
        System.out.println("Moves: \"" + moves5 + "\"");
        System.out.println("\nMove counts:");
        System.out.println("  R: 2, L: 2 → horizontal: 2-2 = 0 ✓");
        System.out.println("  U: 0, D: 0 → vertical: 0 ✓");
        System.out.println("\nReturns to origin!\n");

        boolean result5 = judgeCircle(moves5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result5 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Only Vertical ===");
        String moves6 = "UUDD";
        System.out.println("Moves: \"" + moves6 + "\"");
        System.out.println("\nMove counts:");
        System.out.println("  R: 0, L: 0 → horizontal: 0 ✓");
        System.out.println("  U: 2, D: 2 → vertical: 2-2 = 0 ✓");
        System.out.println("\nReturns to origin!\n");

        boolean result6 = judgeCircle(moves6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result6 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Empty Moves ===");
        String moves7 = "";
        System.out.println("Moves: \"" + moves7 + "\" (empty)");
        System.out.println("\nNo moves → stays at origin\n");

        boolean result7 = judgeCircle(moves7);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result7 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Check if robot returns to starting position        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Coordinate System:                                          ║");
        System.out.println("║    Start position: (0, 0)                                    ║");
        System.out.println("║    R (right): x + 1                                          ║");
        System.out.println("║    L (left):  x - 1                                          ║");
        System.out.println("║    U (up):    y + 1                                          ║");
        System.out.println("║    D (down):  y - 1                                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insight:                                                ║");
        System.out.println("║    Robot returns to origin if and only if:                   ║");
        System.out.println("║    • Net horizontal displacement = 0                         ║");
        System.out.println("║    • Net vertical displacement = 0                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Simplified Tracking:                                        ║");
        System.out.println("║    Instead of tracking (x, y) coordinates:                   ║");
        System.out.println("║    • Track net horizontal: right - left                      ║");
        System.out.println("║    • Track net vertical: up - down                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Implementation:                                             ║");
        System.out.println("║    right = 0, up = 0                                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║    For each move:                                            ║");
        System.out.println("║      R: right++                                              ║");
        System.out.println("║      L: right--                                              ║");
        System.out.println("║      U: up++                                                 ║");
        System.out.println("║      D: up--                                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Return: (right == 0 && up == 0)                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: moves=\"UDLR\"                                       ║");
        System.out.println("║    U: up=1, right=0                                          ║");
        System.out.println("║    D: up=0, right=0                                          ║");
        System.out.println("║    L: up=0, right=-1                                         ║");
        System.out.println("║    R: up=0, right=0 ← both zero, returns true!               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why This Works:                                             ║");
        System.out.println("║    • Each R cancels one L (horizontal)                       ║");
        System.out.println("║    • Each U cancels one D (vertical)                         ║");
        System.out.println("║    • If both counts are zero, all moves cancelled out        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Alternative Approach:                                       ║");
        System.out.println("║    Count occurrences: countR, countL, countU, countD         ║");
        System.out.println("║    Return: (countR == countL) && (countU == countD)          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n) - Single pass through moves string            ║");
        System.out.println("║    Space: O(1) - Only two counter variables                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}

package Arrays;

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
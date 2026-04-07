package Arrays;

/*

    Description:
      Following program simulates a robot moving on an infinite 2D grid,
        processing a sequence of turn and move commands while avoiding obstacles...

    Problem Statement:
      -> Given an array of commands and a 2D array of obstacle positions...
      -> Commands: -2 (turn left), -1 (turn right), 1-9 (move forward k steps)...
      -> Robot starts at (0, 0) facing North...
      -> Robot cannot move through obstacles, it stops just before them...
      -> Return the maximum Euclidean distance squared reached at any point...

    Key Insight:
      -> Euclidean distance squared = x² + y² (no sqrt needed)...
      -> Track maximum at every step, not just at the end...
      -> Robot may reach its farthest point mid-simulation, not necessarily last...
      -> Move step-by-step (not all k steps at once) to detect obstacles correctly...

    Example:
      -> commands = [4, -1, 3], obstacles = []:
           Move North 4 steps → (0, 4)...
           Turn right → facing East...
           Move East 3 steps → (3, 4)...
           Max distance = 3² + 4² = 9 + 16 = 25...
      -> commands = [4, -1, 4, -2, 4], obstacles = [[2, 4]]:
           Move North 4 steps → (0, 4)...
           Turn right → facing East...
           Move East, blocked at (2, 4) → stops at (1, 4)...
           Turn left → facing North...
           Move North 4 steps → (1, 8)...
           Max distance = 1² + 8² = 1 + 64 = 65...

    Direction System (Clockwise Order):
      -> 0: North → (0,  1)...
      -> 1: East  → (1,  0)...
      -> 2: South → (0, -1)...
      -> 3: West  → (-1, 0)...
      -> Stored as directions[][] array, indexed by dir...

    Turn Logic:
      -> Turn right (-1): dir = (dir + 1) % 4...
           Cycles: North → East → South → West → North...
      -> Turn left  (-2): dir = (dir + 3) % 4...
           Equivalent to dir - 1 with wrap-around using modulo...
           Cycles: North → West → South → East → North...

    Obstacle Lookup Strategy:
      -> Store each obstacle as string key "x,y" in a HashSet...
      -> HashSet provides O(1) average-case lookup per step...
      -> Avoids O(S) linear scan through obstacle array on every move...
      -> String concatenation "x,y" uniquely identifies each grid cell...

    Movement Simulation (Per Step):
      -> Calculate next position: nx = x + directions[dir][0]...
                                  ny = y + directions[dir][1]...
      -> Check if (nx, ny) exists in blockedPoints HashSet...
      -> If blocked: break out of loop, robot stays at current position...
      -> If clear: update x = nx, y = ny...
      -> Update maxDist = Math.max(maxDist, x*x + y*y) after each valid step...

    Why Track Distance at Every Step?
      -> Robot might pass through a farther point before hitting an obstacle...
      -> Example: moving 5 steps but blocked at step 3...
                  step 1 and step 2 might be farther than any future position...
      -> Updating inside the while loop captures the true maximum...

    Why Use x² + y² Instead of sqrt(x² + y²)?
      -> Problem asks for squared Euclidean distance directly...
      -> Avoids floating point computation and precision issues...
      -> Comparing squares preserves the same ordering as comparing distances...

    Edge Cases:
      -> No obstacles → robot moves freely through all commands...
      -> Only turn commands → robot stays at (0, 0) → return 0...
      -> First step blocked → robot never moves → return 0...
      -> Obstacle behind robot → irrelevant, never encountered...
      -> Robot revisits origin → maxDist retains the previous maximum...

    Directions Array Visualization:
      -> directions = { {0,1}, {1,0}, {0,-1}, {-1,0} }
                           ↑       ↑       ↑        ↑
                         North   East   South    West...

    Time and Space Complexity:
      -> Time:  O(C + S) where C = total steps across all move commands...
                              S = number of obstacles (for HashSet construction)...
      -> Space: O(S) — HashSet stores one string entry per obstacle...

    Applications:
      -> Robotics path simulation and collision detection...
      -> Game character movement on grid-based maps...
      -> Autonomous vehicle navigation with blocked zones...
      -> Maze traversal and obstacle avoidance algorithms...

*/

import java.util.HashSet;

public class Walking_Robot_Simulation {

    public int robotSim(int[] commands, int[][] obstacles) {

        HashSet<String> blockedPoints = new HashSet<>();
        for (int[] obst : obstacles) {
            blockedPoints.add(obst[0] + "," + obst[1]);
        }

        int[][] directions = {
            {0, 1}, {1, 0}, {0, - 1}, {- 1, 0}
        };

        int x = 0;
        int y = 0;

        int dir = 0;
        int maxDist = 0;

        for (int cmd : commands) {
            if (cmd == - 1) {
                dir = (dir + 1) % 4;
            } else if (cmd == - 2) {
                dir = (dir + 3) % 4;
            } else {
                while (cmd-- > 0) {
                    int nx = x + directions[dir][0];
                    int ny = y + directions[dir][1];

                    if (blockedPoints.contains(nx + "," + ny)) {
                        break;
                    }

                    x = nx;
                    y = ny;

                    maxDist = Math.max(maxDist, x * x + y * y);
                }
            }
        }

        return maxDist;
    }

    public static void main(String[] args) {
        
        Walking_Robot_Simulation solver = new Walking_Robot_Simulation();

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              WALKING ROBOT SIMULATION                        ║");
        System.out.println("║  Simulate robot movement on 2D grid with obstacles           ║");
        System.out.println("║  Commands: -2 (left), -1 (right), 1-9 (move forward)         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Simple Path ===");
        int[] commands1 = {4, - 1, 3};
        int[][] obstacles1 = {};
        System.out.println("Commands: " + arrayToString(commands1));
        System.out.println("Obstacles: " + (obstacles1.length == 0 ? "none" : matrixToString(obstacles1)));
        System.out.println("\nSimulation:");
        System.out.println("  Start: (0,0), facing North");
        System.out.println("  Command 4: move 4 steps → (0,4)");
        System.out.println("  Command -1: turn right → facing East");
        System.out.println("  Command 3: move 3 steps → (3,4)");
        System.out.println("\nMax distance: 3² + 4² = 9 + 16 = 25\n");

        int result1 = solver.robotSim(commands1, obstacles1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 25");
        System.out.println("  Status: " + (result1 == 25 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: With Obstacles ===");
        int[] commands2 = {4, - 1, 4, - 2, 4};
        int[][] obstacles2 = {{2, 4}};
        System.out.println("Commands: " + arrayToString(commands2));
        System.out.println("Obstacles: " + matrixToString(obstacles2));
        System.out.println("\nSimulation:");
        System.out.println("  Start: (0,0), facing North");
        System.out.println("  Command 4: move 4 steps → (0,4)");
        System.out.println("  Command -1: turn right → facing East");
        System.out.println("  Command 4: move until obstacle at (2,4)");
        System.out.println("             → stops at (1,4)");
        System.out.println("  Command -2: turn left → facing North");
        System.out.println("  Command 4: move 4 steps → (1,8)");
        System.out.println("\nMax distance: 1² + 8² = 1 + 64 = 65\n");

        int result2 = solver.robotSim(commands2, obstacles2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: 65");
        System.out.println("  Status: " + (result2 == 65 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Only Turns ===");
        int[] commands3 = {- 1, - 1, - 1, - 1};
        int[][] obstacles3 = {};
        System.out.println("Commands: " + arrayToString(commands3));
        System.out.println("Obstacles: none");
        System.out.println("\nSimulation:");
        System.out.println("  Only turning, no movement");
        System.out.println("  Stays at (0,0)");
        System.out.println("\nMax distance: 0\n");

        int result3 = solver.robotSim(commands3, obstacles3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result3 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Blocked Immediately ===");
        int[] commands4 = {1};
        int[][] obstacles4 = {{0, 1}};
        System.out.println("Commands: " + arrayToString(commands4));
        System.out.println("Obstacles: " + matrixToString(obstacles4));
        System.out.println("\nSimulation:");
        System.out.println("  Start: (0,0), facing North");
        System.out.println("  Command 1: try to move to (0,1)");
        System.out.println("             → blocked by obstacle");
        System.out.println("  Stays at (0,0)");
        System.out.println("\nMax distance: 0\n");

        int result4 = solver.robotSim(commands4, obstacles4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result4 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Square Path ===");
        int[] commands5 = {2, - 1, 2, - 1, 2, - 1, 2};
        int[][] obstacles5 = {};
        System.out.println("Commands: " + arrayToString(commands5));
        System.out.println("Obstacles: none");
        System.out.println("\nSimulation (forms a square):");
        System.out.println("  North 2: (0,2)");
        System.out.println("  Turn right → East");
        System.out.println("  East 2: (2,2) → distance = 8");
        System.out.println("  Turn right → South");
        System.out.println("  South 2: (2,0)");
        System.out.println("  Turn right → West");
        System.out.println("  West 2: (0,0)");
        System.out.println("\nMax distance: 2² + 2² = 8\n");

        int result5 = solver.robotSim(commands5, obstacles5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 8");
        System.out.println("  Status: " + (result5 == 8 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Simulate robot on 2D grid with obstacles           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Commands:                                                   ║");
        System.out.println("║    -2: Turn left 90°                                         ║");
        System.out.println("║    -1: Turn right 90°                                        ║");
        System.out.println("║    1-9: Move forward k units (if not blocked)                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Directions (clockwise):                                     ║");
        System.out.println("║    0: North (0, 1)   → up                                    ║");
        System.out.println("║    1: East  (1, 0)   → right                                 ║");
        System.out.println("║    2: South (0, -1)  → down                                  ║");
        System.out.println("║    3: West  (-1, 0)  → left                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Direction Changes:                                          ║");
        System.out.println("║    Turn right (-1): dir = (dir + 1) % 4                      ║");
        System.out.println("║    Turn left (-2):  dir = (dir + 3) % 4                      ║");
        System.out.println("║                      (same as dir - 1 with wrap-around)      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Obstacle Storage:                                           ║");
        System.out.println("║    Use HashSet with string key: \"x,y\"                        ║");
        System.out.println("║    O(1) lookup to check if position blocked                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Movement Simulation:                                        ║");
        System.out.println("║    For each step in command k:                               ║");
        System.out.println("║      1. Calculate next position (nx, ny)                     ║");
        System.out.println("║      2. Check if obstacle at (nx, ny)                        ║");
        System.out.println("║      3. If blocked: break (stop moving)                      ║");
        System.out.println("║      4. If clear: update position                            ║");
        System.out.println("║      5. Update max distance: x² + y²                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Euclidean Distance Squared:                                 ║");
        System.out.println("║    distance² = x² + y²                                       ║");
        System.out.println("║    No need for sqrt (comparing squares is sufficient)        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Implementation Details:                                 ║");
        System.out.println("║    • Move step-by-step (not jump k units at once)            ║");
        System.out.println("║    • Stop immediately when obstacle encountered              ║");
        System.out.println("║    • Track max distance at every position                    ║");
        System.out.println("║    • Starting position (0,0), facing North                   ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(C + S) where C = sum of all move commands,       ║");
        System.out.println("║                       S = number of obstacles                ║");
        System.out.println("║    Space: O(S) - HashSet for obstacles                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        
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

    private static String matrixToString(int[][] mat) {

        if (mat.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < mat.length; i++) {
            sb.append("[");
            for (int j = 0; j < mat[i].length; j++) {
                sb.append(mat[i][j]);
                if (j < mat[i].length - 1) {
                    sb.append(", ");
                }
            }

            sb.append("]");
            if (i < mat.length - 1) sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

}

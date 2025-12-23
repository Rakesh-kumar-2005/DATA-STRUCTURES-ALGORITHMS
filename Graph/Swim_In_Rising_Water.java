package Graph;

/*

Description:
  This program solves the "Swim in Rising Water" problem using a modified Dijkstra’s algorithm, where the objective is to minimize the maximum elevation encountered along the path instead of minimizing total distance...

Problem Statement:
  -> You are given an n × n grid where each cell represents an elevation height...
  -> Water level starts at time t = 0 and rises by 1 unit per time unit...
  -> You start at the top-left cell (0,0) and want to reach the bottom-right cell (n-1,n-1)...
  -> You can move up, down, left, or right to adjacent cells...
  -> You can enter a cell only when the water level is at least equal to that cell’s elevation...
  -> Return the minimum time required to reach the destination...

Key Insight:
  -> The required time for a path is determined by the maximum elevation encountered on that path...
  -> We must find a path from start to end that minimizes this maximum elevation...
  -> This transforms the problem into a "minimize the maximum cost along a path" problem...

Approach:
  > Modified Dijkstra’s Algorithm (Minimax Path):
     i. Use a PriorityQueue that always expands the cell with the smallest current maximum height...
     ii. Each state stores:
           - row index...
           - column index...
           - maximum elevation encountered so far on the path...
     iii. Start from (0,0) with initial height = grid[0][0]...
     iv. For each move, the new path height becomes:
           max(currentPathHeight, grid[newCell])...
     v. The first time the destination cell is popped from the priority queue, its height is the answer...

> Algorithm Steps:
  -> Initialize a visited[][] matrix to avoid revisiting cells...
  -> Push the starting cell (0,0) into a min-heap ordered by path height...
  -> While the priority queue is not empty:
       * Extract the cell with the minimum current maximum height...
       * If this cell is the destination, return its height...
       * Otherwise, explore all 4 valid neighboring cells...
       * Mark neighbors as visited and push them with updated height...
  -> If destination is unreachable, return -1 (theoretically never happens)...

> Direction Handling:
  -> Use a fixed DIRECTIONS array for 4-way movement:
       {-1,0}, {0,-1}, {1,0}, {0,1}...
  -> This ensures clean and consistent traversal logic...

> Implementation Note:
  -> Unlike traditional Dijkstra which sums weights, this version propagates the maximum height...
  -> PriorityQueue comparator is based on current path height...
  -> visited[][] prevents infinite loops and redundant processing...
  -> Works efficiently even when grid values are large...

> Example:
  -> Grid:
       0  2
       1  3
  -> Possible paths:
       Path 1: 0 → 2 → 3 → max = 3...
       Path 2: 0 → 1 → 3 → max = 3...
  -> Minimum time required = 3...

> Edge Cases:
  -> Single cell grid:
       * Return grid[0][0] since start is destination...
  -> All cells have same elevation:
       * Time equals that common elevation...
  -> High barrier cells:
       * Algorithm correctly avoids them if alternative lower paths exist...
  -> Descending grids:
       * Starting cell height dominates the answer...

> Time and Space Complexity:
  -> Time Complexity: O(N² log N), each cell processed once with heap operations...
  -> Space Complexity: O(N²) for visited array and priority queue...

Summary:
  -> Efficient greedy solution using priority queue...
  -> Correctly models rising water constraint...
  -> Guarantees optimal minimum time to reach destination...
  -> Classic application of minimax Dijkstra on grids...

*/

import java.util.PriorityQueue;

public class Swim_In_Rising_Water {

    static class Pair {
        int row;
        int col;
        int height;

        public Pair(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    private static final int[][] DIRECTIONS = {{- 1, 0}, {0, - 1}, {1, 0}, {0, 1}};

    private static int swimInWater(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        if (n == 1 && m == 1) {
            return grid[0][0];
        }

        boolean[][] visited = new boolean[n][m];

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.height - b.height);

        pq.add(new Pair(0, 0, grid[0][0]));
        visited[0][0] = true;

        while (! pq.isEmpty()) {
            Pair currPair = pq.poll();

            if (currPair.row == n - 1 && currPair.col == m - 1) {
                return currPair.height;
            }

            for (int[] dir : DIRECTIONS) {
                int newRow = currPair.row + dir[0];
                int newCol = currPair.col + dir[1];

                if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m || visited[newRow][newCol]) {
                    continue;
                }

                visited[newRow][newCol] = true;
                int newHeight = Math.max(grid[newRow][newCol], currPair.height);

                pq.add(new Pair(newRow, newCol, newHeight));
            }
        }

        return - 1;
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║            SWIM IN RISING WATER - DIJKSTRA'S                 ║");
        System.out.println("║    Find minimum time to reach bottom-right from top-left     ║");
        System.out.println("║    Water level rises by 1 each time unit                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Simple 2x2 Grid ===");
        int[][] grid1 = {
            {0, 2},
            {1, 3}
        };
        System.out.println("Grid (elevation at each cell):");
        printGrid(grid1);
        System.out.println("\nExplanation:");
        System.out.println("  Starting at (0,0) with elevation 0");
        System.out.println("  Option 1: (0,0)[0] → (0,1)[2] → (1,1)[3] = max(0,2,3) = 3");
        System.out.println("  Option 2: (0,0)[0] → (1,0)[1] → (1,1)[3] = max(0,1,3) = 3");
        System.out.println("  Must wait until water level reaches highest cell in path");
        int result1 = swimInWater(grid1);
        System.out.println("\n✓ Minimum time to swim: " + result1);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result1 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Spiral 5x5 Grid ===");
        int[][] grid2 = {
            {0, 1, 2, 3, 4},
            {24, 23, 22, 21, 5},
            {12, 13, 14, 15, 16},
            {11, 17, 18, 19, 20},
            {10, 9, 8, 7, 6}
        };
        System.out.println("Grid (spiral pattern):");
        printGrid(grid2);
        System.out.println("\nExplanation:");
        System.out.println("  Start: (0,0) elevation 0");
        System.out.println("  Goal: (4,4) elevation 6");
        System.out.println("  Optimal path follows outer spiral:");
        System.out.println("  0→1→2→3→4→5→16→20→6 (max elevation = 16)");
        System.out.println("  Alternative paths have higher max elevations");
        int result2 = swimInWater(grid2);
        System.out.println("\n✓ Minimum time to swim: " + result2);
        System.out.println("  Expected: 16");
        System.out.println("  Status: " + (result2 == 16 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: All Same Elevation ===");
        int[][] grid3 = {
            {5, 5, 5},
            {5, 5, 5},
            {5, 5, 5}
        };
        System.out.println("Grid (uniform elevation):");
        printGrid(grid3);
        System.out.println("\nExplanation:");
        System.out.println("  All cells have elevation 5");
        System.out.println("  Any path works: shortest is diagonal-ish");
        System.out.println("  Water level must reach 5 to traverse any cell");
        System.out.println("  Time = max elevation in any valid path = 5");
        int result3 = swimInWater(grid3);
        System.out.println("\n✓ Minimum time to swim: " + result3);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result3 == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Ascending Diagonal Path ===");
        int[][] grid4 = {
            {0, 1, 2},
            {7, 6, 3},
            {8, 9, 4}
        };
        System.out.println("Grid:");
        printGrid(grid4);
        System.out.println("\nExplanation:");
        System.out.println("  Direct diagonal path: 0→1→2→3→4");
        System.out.println("    └─ max(0,1,2,3,4) = 4");
        System.out.println("  Alternative through middle: 0→1→6→9→4");
        System.out.println("    └─ max(0,1,6,9,4) = 9 (worse)");
        System.out.println("  Greedy approach: take path with lowest maximum");
        int result4 = swimInWater(grid4);
        System.out.println("\n✓ Minimum time to swim: " + result4);
        System.out.println("  Expected: 4");
        System.out.println("  Status: " + (result4 == 4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Forced High Elevation ===");
        int[][] grid5 = {
            {0, 10},
            {1, 100}
        };
        System.out.println("Grid:");
        printGrid(grid5);
        System.out.println("\nExplanation:");
        System.out.println("  Path 1: (0,0)[0] → (0,1)[10] → (1,1)[100]");
        System.out.println("    └─ max(0,10,100) = 100");
        System.out.println("  Path 2: (0,0)[0] → (1,0)[1] → (1,1)[100]");
        System.out.println("    └─ max(0,1,100) = 100");
        System.out.println("  Both paths forced through [100], no escape!");
        System.out.println("  Must wait until water level = 100");
        int result5 = swimInWater(grid5);
        System.out.println("\n✓ Minimum time to swim: " + result5);
        System.out.println("  Expected: 100");
        System.out.println("  Status: " + (result5 == 100 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Single Cell (Edge Case) ===");
        int[][] grid6 = {{7}};
        System.out.println("Grid: [[7]]");
        System.out.println("\nExplanation:");
        System.out.println("  Only one cell - already at destination!");
        System.out.println("  Still need to wait for water to reach elevation 7");
        System.out.println("  Time = starting cell elevation = 7");
        int result6 = swimInWater(grid6);
        System.out.println("\n✓ Minimum time to swim: " + result6);
        System.out.println("  Expected: 7");
        System.out.println("  Status: " + (result6 == 7 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Barrier Grid ===");
        int[][] grid7 = {
            {0, 1, 100},
            {5, 2, 100},
            {6, 3, 4}
        };
        System.out.println("Grid (wall of high cells):");
        printGrid(grid7);
        System.out.println("\nExplanation:");
        System.out.println("  High cells (100) create a barrier on right");
        System.out.println("  Must navigate through lower left cells");
        System.out.println("  Optimal: 0→1→2→3→4 (avoids the 100 barrier)");
        System.out.println("    └─ max(0,1,2,3,4) = 6");
        int result7 = swimInWater(grid7);
        System.out.println("\n✓ Minimum time to swim: " + result7);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result7 == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Descending Path ===");
        int[][] grid8 = {
            {10, 9, 8},
            {7, 6, 5},
            {4, 3, 2}
        };
        System.out.println("Grid (descending order):");
        printGrid(grid8);
        System.out.println("\nExplanation:");
        System.out.println("  Start at highest point (10)");
        System.out.println("  End at lowest point (2)");
        System.out.println("  Any path must include starting cell [10]");
        System.out.println("  Optimal path still requires time = 10");
        int result8 = swimInWater(grid8);
        System.out.println("\n✓ Minimum time to swim: " + result8);
        System.out.println("  Expected: 10");
        System.out.println("  Status: " + (result8 == 10 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM: Modified Dijkstra's (Minimizing Maximum)         ║");
        System.out.println("║    - Use PriorityQueue ordered by current max height         ║");
        System.out.println("║    - Track max height along path, not sum                    ║");
        System.out.println("║    - Greedy choice: always extend path with lowest max       ║");
        System.out.println("║  Time: O(N² log N) | Space: O(N²)                            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    private static void printGrid(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        System.out.print("      ");
        for (int j = 0; j < m; j++) {
            System.out.printf("Col%d ", j);
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.printf("  Row%d: ", i);
            for (int j = 0; j < m; j++) {
                System.out.printf("%4d ", grid[i][j]);
            }
            System.out.println();
        }
        
    }

}

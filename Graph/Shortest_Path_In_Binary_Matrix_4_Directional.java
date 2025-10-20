package Graph;

/*

Description:
    -> This program finds the shortest path from the top-left corner (0,0)
       to the bottom-right corner (n-1,m-1) in a given binary matrix, 
       where each cell can be either **0 (open path)** or 1 (blocked cell).

    -> Unlike 8-directional movement, this version allows movement in only **4 directions**:
       Up, Down, Left, and Right.

Problem Statement:
    -> Given an n × m binary matrix `grid`:
         * 0 represents a traversable cell.
         * 1 represents a blocked cell.
       You must find the length of the shortest clear path from (0,0) to (n-1,m-1)
       moving only in the 4 cardinal directions.
       If no such path exists, return **-1**.

Algorithm Used:
    -> **Breadth-First Search (BFS)** algorithm — since it finds the shortest path 
       in an unweighted grid efficiently.

Approach:
    1. Check initial conditions:
         - If the start (0,0) or end (n-1,m-1) cell is blocked, return -1.
         - If the matrix is 1×1 and the only cell is open, return 1.

    2. Use a queue for BFS:
         - Each element of the queue stores a `tuple(distance, row, col)`.
         - Start BFS from (0,0) with an initial distance of 1.

    3. Maintain a `distances[][]` matrix initialized to `Integer.MAX_VALUE` 
       to record the minimum distance to each cell.

    4. For each cell dequeued, explore all **4 directions**:
         Up:    (-1, 0)
         Right: (0, 1)
         Down:  (1, 0)
         Left:  (0, -1)

    5. For each valid and unblocked neighbor:
         - If a shorter distance is found, update it in `distances`.
         - If the destination (n-1,m-1) is reached, return the distance immediately.
         - Otherwise, enqueue the new position.

    6. If BFS completes without reaching the target, return -1.

Example Input:
    int[][] grid = {
        {0, 0, 0},
        {1, 1, 0},
        {1, 1, 0}
    };

Output:
    The Shortest Path in the Binary Matrix (from the first cell to the last cell 
    by the visited cell which has the value of 0) is : 5

Explanation:
    The shortest path of 5 steps:
        (0,0) → (0,1) → (0,2) → (1,2) → (2,2)

Edge Cases Considered:
    ✅ Start or end cell is blocked → return -1  
    ✅ Single cell matrix → return 1 if cell = 0, else -1  
    ✅ Fully blocked matrix → return -1  
    ✅ Path exists through 4-directional traversal only  

Key Concepts Used:
    -> Breadth-First Search (BFS)
    -> Queue-based level traversal
    -> Distance tracking using 2D array
    -> Boundary and validity checks

Time and Space Complexity:
    -> Time Complexity: O(N × M)
         - Each cell is visited at most once and explores 4 directions.
    -> Space Complexity: O(N × M)
         - For queue and distance matrix.

*/

import java.util.LinkedList;
import java.util.Queue;

public class Shortest_Path_In_Binary_Matrix_4_Directional {

    static class tuple {
        int distance;
        int row;
        int col;

        public tuple(int distance, int row, int col) {
            this.distance = distance;
            this.row = row;
            this.col = col;
        }
    }

    private static int shortestPathBinaryMatrix(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        if (grid[0][0] != 0 || grid[n - 1][m - 1] != 0) {
            return - 1;
        }

        if (n == 1 && m == 1) {
            return 1;
        }

        Queue<tuple> q = new LinkedList<>();
        q.add(new tuple(1, 0, 0));

        int[][] distances = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        distances[0][0] = 1;

        int[] dr = {- 1, 0, 1, 0};
        int[] dc = {0, 1, 0, - 1};

        while (! q.isEmpty()) {

            tuple currTuple = q.poll();

            int currDist = currTuple.distance;
            int currRow = currTuple.row;
            int currCol = currTuple.col;

            for (int i = 0; i < 4; i++) {
                int nRow = currRow + dr[i];
                int nCol = currCol + dc[i];

                if (nRow >= 0 && nCol >= 0 && nRow < n && nCol < m && grid[nRow][nCol] == 0) {
                    if (currDist + 1 < distances[nRow][nCol]) {
                        distances[nRow][nCol] = currDist + 1;

                        if (nRow == n - 1 && nCol == m - 1) {
                            return currDist + 1;
                        }

                        q.add(new tuple(distances[nRow][nCol], nRow, nCol));
                    }
                }
            }
        }

        return distances[n - 1][m - 1] == Integer.MAX_VALUE ? - 1 : distances[n - 1][m - 1];
    }

    public static void main(String[] args) {

        int[][] grid = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println("The Shortest Path in the Binary Matrix (from the first cell " +
            "to the last cell by the visited cell which has the value of 0) is : " + shortestPathBinaryMatrix(grid));

    }

}

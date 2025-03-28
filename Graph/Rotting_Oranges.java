package Graph;

/*

    Description:
        Following program demonstrates the solution to the "Rotting Oranges" problem
        using Breadth-First Search (BFS) Approach...

    Problem Statement:
        -> Given a 2D grid representing an orange farm where each cell can be in three states:
            1. Empty cell (0)...
            2. Fresh orange (1)...
            3. Rotten orange (2)...
        -> Determine the minimum time required to rot all fresh oranges...
        -> Each rotten orange can rot adjacent fresh oranges in four directions (up, right, down, left)...
        -> If not all fresh oranges can be rotten, return -1...

    Approach:
        > Breadth-First Search (BFS) with Time Tracking:
            i. Use a queue to perform multi-source BFS...
            ii. Initially, add all rotten oranges to the queue...
            iii. Keep track of fresh orange count...
            iv. Explore adjacent cells and rot fresh oranges...
            v. Track the maximum time taken to rot all oranges...

    Algorithm Steps:
        -> Initialize key data structures:
            1. Queue to store rotten oranges with their coordinates and time...
            2. Visited array to prevent re-processing oranges...
            3. Counter for fresh oranges...
        -> Preprocessing:
            1. Identify initial rotten oranges...
            2. Count total fresh oranges...
            3. Add rotten oranges to queue with initial time 0...
        -> BFS Traversal:
            1. Remove orange from queue...
            2. Explore four adjacent directions...
            3. If adjacent cell is a fresh orange:
                * Mark as visited...
                * Add to queue...
                * Increment rot count...
                * Update maximum time...
        -> Final Validation:
            1. Compare rotten orange count with initial fresh orange count...
            2. Return maximum time or -1 if not all oranges can be rotten...

    Key Characteristics:
        -> Handles multi-source rotting simultaneously...
        -> Efficiently tracks time of orange rotting...
        -> Uses directional arrays for adjacent cell exploration...
        -> Prevents redundant processing of oranges...

        > Rotting Mechanism:
            -> Uses queue for systematic orange processing...
            -> Tracks time taken for each orange to rot...
            -> Ensures comprehensive grid exploration...

    Time and Space Complexity:
        -> Time Complexity: O(N * M) where N and M are grid dimensions...
        -> Space Complexity: O(N * M) for queue and visited array...

*/

import java.util.LinkedList;
import java.util.Queue;

public class Rotting_Oranges {

    static class Pair {
        int row;
        int col;
        int time;

        public Pair(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }

    private static int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> q = new LinkedList<>();
        int[][] visited = new int[n][m];
        int countFresh = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.add(new Pair(i, j, 0));
                    visited[i][j] = 1;
                }
                if (grid[i][j] == 1) {
                    countFresh++;
                }
            }
        }

        int[] delRow = {- 1, 0, 1, 0};
        int[] delCol = {0, 1, 0, - 1};

        int maxTime = 0;
        int countRot = 0;

        while (! q.isEmpty()) {
            Pair curr = q.remove();
            int currRow = curr.row;
            int currCol = curr.col;
            int currTime = curr.time;
            maxTime = Math.max(maxTime, currTime);

            for (int i = 0; i < 4; i++) {
                int newRow = currRow + delRow[i];
                int newCol = currCol + delCol[i];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m &&
                    grid[newRow][newCol] == 1 && visited[newRow][newCol] == 0) {
                    visited[newRow][newCol] = 1;
                    q.add(new Pair(newRow, newCol, currTime + 1));
                    countRot++;
                }
            }
        }

        if (countRot != countFresh) return - 1;
        return maxTime;
    }

    public static void main(String[] args) {

        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int time = orangesRotting(grid);

        if (time != - 1) {
            System.out.println("The maximum time it'll take to rot every fresh oranges is = " + time);
        } else {
            System.out.println("We can't rot every fresh orange...");
        }
    }
}

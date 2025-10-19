package Graph;

/*

Description:
    -> This program finds the Shortest path from the top-left corner (0,0) 
       to the bottom-right corner (n-1, m-1) in a given binary matrix, 
       where each cell can either be **0 (open)** or **1 (blocked)**.

    -> The path can move in 8 possible directions — up, down, left, right, 
       and all four diagonals — as long as the next cell is within bounds 
       and has a value of **0**.

Problem Statement:
    -> You are given an n × n binary matrix grid, where:
           * 0 represents an open cell.
           * 1 represents a blocked cell.
       You must find the length of the shortest clear path from the top-left cell 
       (0,0) to the bottom-right cell (n-1,m-1).  
       The path may move to any of the 8 adjacent cells at each step.

    -> If no such path exists, return -1.

Algorithm Used:
    -> **Breadth-First Search (BFS)** algorithm is used since it efficiently finds 
       the shortest path in an **unweighted graph** or **grid**.

Approach:
    1. Check the base cases:
         - If the starting or ending cell is blocked (i.e., 1), return -1.
         - If the grid is a single cell (1x1) and is open, return 1.

    2. Use a queue to perform BFS:
         - Each element in the queue stores a `tuple` containing:
             (distance, row, col)
         - Start from cell (0,0) with distance = 1.

    3. For each cell, explore all 8 possible directions:
         (-1,0), (-1,1), (0,1), (1,1), (1,0), (1,-1), (0,-1), (-1,-1)

    4. For each valid adjacent cell (inside bounds and equal to 0):
         - If a shorter distance is found, update it in the `distances[][]` matrix.
         - If the destination (n-1, m-1) is reached, return the current distance + 1.
         - Otherwise, enqueue the cell for further exploration.

    5. If BFS completes without reaching the destination, return -1.

Key Concepts Used:
    -> Breadth-First Search (BFS)
    -> 8-Directional Movement
    -> Grid Boundary Checking
    -> Distance Tracking using a 2D matrix

Example Input:
    int[][] grid = {
        {0, 1, 1, 1, 1},
        {0, 1, 0, 0, 0},
        {0, 0, 0, 1, 0},
        {1, 1, 1, 1, 0},
        {1, 1, 1, 1, 1}
    };

Output:
    The Shortest Path in the Binary Matrix (from the first cell to the last cell 
    by the visited cell which has the value of 0) is : 7

Explanation:
    One possible path with minimum steps (7 moves):
        (0,0) → (1,0) → (2,0) → (2,1) → (2,2) → (1,3) → (1,4) → (3,4)
    (assuming those positions correspond to 0s)

Edge Cases Handled:
    -> Grid with blocked start or end cell.
    -> Grid with only one cell.
    -> No path available (fully blocked).
    -> Multiple possible paths — ensures shortest is chosen.

Time and Space Complexity:
    -> Time Complexity: O(N²)
         - Each cell is processed at most once, exploring up to 8 directions.
    -> Space Complexity: O(N²)
         - For the queue and the distance matrix.

*/

import java.util.LinkedList;
import java.util.Queue;

public class Shortest_Path_In_Binary_Matrix_8_Directional {

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

        int[] dr = {- 1, - 1, 0, 1, 1, 1, 0, - 1};
        int[] dc = {0, 1, 1, 1, 0, - 1, - 1, - 1};

        while (! q.isEmpty()) {

            tuple currTuple = q.poll();

            int currDist = currTuple.distance;
            int currRow = currTuple.row;
            int currCol = currTuple.col;

            for (int i = 0; i < 8; i++) {
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

        int[][] grid = {{0, 1, 1, 1, 1}, {0, 1, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 1, 1, 0}, {1, 1, 1, 1, 1}};
        System.out.println("The Shortest Path in the Binary Matrix (from the first cell to the last cell by the visited cell which has the value of 0) is : " + shortestPathBinaryMatrix(grid));

    }

}

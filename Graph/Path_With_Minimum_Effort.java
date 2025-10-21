package Graph;

/*
Description:
    This program finds the path from the top-left cell (0,0) to the bottom-right cell (n-1,m-1)
    in a 2D grid of heights such that the maximum absolute difference in heights between
    adjacent cells along the path is minimized. This difference is referred to as the "effort".

Problem Statement:
    Given an n × m grid 'heights', where heights[i][j] represents the height of a cell,
    find the minimum effort required to travel from (0,0) to (n-1,m-1).
    Effort is defined as the maximum absolute difference in heights between two consecutive cells
    along any path from the start to the destination.

Algorithm Used:
    Dijkstra’s algorithm is used because each cell represents a node and the cost between
    two adjacent cells is the absolute difference of their heights.
    The goal is to minimize the maximum edge weight encountered along the path.

Approach:
    1. Create a priority queue that stores tuples of the form (effort, row, col).
    2. Initialize a distance matrix with infinity to track the minimum effort required
       to reach each cell.
    3. Start from the top-left cell (0,0) with effort 0 and push it into the queue.
    4. While the queue is not empty:
         - Extract the cell with the smallest current effort.
         - If the cell is the bottom-right one, return its effort.
         - For each of the 4 neighboring cells (up, right, down, left):
             a. Calculate the new effort as the maximum of the current effort
                and the absolute height difference between the current and adjacent cell.
             b. If this new effort is smaller than the previously recorded effort for that cell,
                update it and push it into the queue.
    5. If no path is found, return 0.

Example Input:
    int[][] heights = {
        {1, 2, 2},
        {3, 8, 2},
        {5, 3, 5}
    };

Example Output:
    2

Explanation:
    The minimum effort path is (0,0) → (0,1) → (0,2) → (1,2) → (2,2)
    The maximum height difference along this path is 2.

Edge Cases Considered:
    - Single cell grid
    - All cells with same height
    - Path surrounded by large height differences
    - Non-square grids

Key Concepts Used:
    Dijkstra’s Algorithm
    Priority Queue for selecting minimum effort node
    Grid traversal using four directions

Time and Space Complexity:
    Time Complexity: O(N × M × log(N × M))
        Each cell is processed once and each insertion in the priority queue takes logarithmic time.
    Space Complexity: O(N × M)
        Used for the distance matrix and priority queue storage.
*/

import java.util.PriorityQueue;

public class Path_With_Minimum_Effort {

    static class Tuple {
        int distance;
        int row;
        int col;

        public Tuple(int distance, int row, int col) {
            this.distance = distance;
            this.row = row;
            this.col = col;
        }

    }

    private static int minimumEffortPath(int[][] heights) {

        int n = heights.length;
        int m = heights[0].length;

        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>((x, y) -> x.distance - y.distance);

        int[][] distances = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        distances[0][0] = 0;
        pq.add(new Tuple(0, 0, 0));

        int[] dr = {- 1, 0, 1, 0};
        int[] dc = {0, 1, 0, - 1};

        while (! pq.isEmpty()) {
            Tuple currTuple = pq.poll();

            int currDiff = currTuple.distance;
            int currRow = currTuple.row;
            int currCol = currTuple.col;

            if (currRow == n - 1 && currCol == m - 1) {
                return currDiff;
            }

            for (int i = 0; i < 4; i++) {
                int nRow = currRow + dr[i];
                int nCol = currCol + dc[i];

                if (nRow >= 0 && nCol >= 0 && nRow < n && nCol < m) {
                    int newEffort = Math.max(Math.abs(heights[currRow][currCol] - heights[nRow][nCol]), currDiff);

                    if (newEffort < distances[nRow][nCol]) {
                        distances[nRow][nCol] = newEffort;
                        pq.add(new Tuple(newEffort, nRow, nCol));
                    }
                    
                }

            }
            
        }

        return 0;

    }

    public static void main(String[] args) {
        
        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        System.out.println("The minium effort required to reach the bottom-right cell is : " + minimumEffortPath(heights));
    
    }
    
}

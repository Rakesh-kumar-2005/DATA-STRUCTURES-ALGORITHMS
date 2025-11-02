package Arrays;

/*

Description:
    -> This program calculates the number of unguarded cells in a grid after placing guards and walls...
    -> A guard can see in all four directions (up, down, left, right) until a wall or another guard blocks their view...
    -> Cells that are not seen by any guard are considered unguarded...

Problem Statement:
    Given:
        - A grid of size m × n...
        - A list of coordinates for guards and walls...
    Determine the number of cells in the grid that are not guarded by any guard...

Example:
    Input:
        m = 4, n = 6...
        guards = [[0, 0], [1, 1], [2, 3]]...
        walls = [[0, 1], [2, 2], [1, 4]]...
    Output:
        7...
    Explanation:
        After marking all cells guarded by the guards, 7 cells remain unguarded...

Approach:
    1. Represent the grid using a 2D array where:
        - 0 → Unguarded cell...
        - 1 → Guarded cell...
        - 2 → Guard cell...
        - 3 → Wall cell...
    2. Place all guards and walls in the grid based on their coordinates...
    3. For each guard, mark all visible cells in four directions (up, down, left, right)
       until a wall or another guard blocks the path...
    4. After processing all guards, count the remaining unguarded cells (cells with value 0)...

Helper Function (helper):
    -> Marks all cells visible to a given guard in four directions...
    -> Stops marking when a wall or another guard is encountered...

Key Observations:
    -> The grid is updated in-place, ensuring efficient marking...
    -> The helper function simplifies direction-based traversal...
    -> Guards do not see through walls or other guards...

Time and Space Complexity:
    -> Time Complexity: O(m × n)...
       (Each cell is visited a constant number of times in the worst case)...
    -> Space Complexity: O(m × n)...
       (For storing the grid)...

Example Walkthrough:
    Input:
        m = 3, n = 3...
        guards = [[0, 0]]...
        walls = [[1, 1]]...
    Process:
        Step 1: Guard at (0,0) marks its row and column until blocked...
        Step 2: Wall at (1,1) blocks downward view...
        Step 3: Count unguarded cells...
    Output:
        4 unguarded cells...

*/

public class Count_Unguarded_Cells_In_The_Grid {

    private static final int UNGUARDED = 0;
    private static final int GUARDED = 1;
    private static final int GUARD = 2;
    private static final int WALL = 3;

    private static void helper(int row, int col, int[][] grid) {

        // Upwards...
        for (int i = row - 1; i >= 0; i--) {
            if (grid[i][col] == GUARD || grid[i][col] == WALL) {
                break;
            }
            grid[i][col] = GUARDED;
        }

        // Downwards...
        for (int i = row + 1; i < grid.length; i++) {
            if (grid[i][col] == GUARD || grid[i][col] == WALL) {
                break;
            }
            grid[i][col] = GUARDED;
        }

        // Rightwards...
        for (int i = col + 1; i < grid[0].length; i++) {
            if (grid[row][i] == GUARD || grid[row][i] == WALL) {
                break;
            }
            grid[row][i] = GUARDED;
        }

        // Leftwards...
        for (int i = col - 1; i >= 0; i--) {
            if (grid[row][i] == GUARD || grid[row][i] == WALL) {
                break;
            }
            grid[row][i] = GUARDED;
        }

    }

    private static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {

        int[][] grid = new int[m][n];
        int count = 0;

        for (int[] guard : guards) {
            int row = guard[0];
            int col = guard[1];
            grid[row][col] = GUARD;
        }

        for (int[] wall : walls) {
            int row = wall[0];
            int col = wall[1];
            grid[row][col] = WALL;
        }

        for (int[] guard : guards) {
            helper(guard[0], guard[1], grid);
        }


        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == UNGUARDED) {
                    count++;
                }
            }
        }

        return count;

    }

    public static void main(String[] args) {

        int m = 4;
        int n = 6;
        int[][] guards = {{0, 0}, {1, 1}, {2, 3}};
        int[][] walls = {{0, 1}, {2, 2}, {1, 4}};

        System.out.println("The number of unguarded cells is : " + countUnguarded(m, n, guards, walls));

    }

}

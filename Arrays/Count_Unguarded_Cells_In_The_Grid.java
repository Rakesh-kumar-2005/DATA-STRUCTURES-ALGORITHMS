package Arrays;

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

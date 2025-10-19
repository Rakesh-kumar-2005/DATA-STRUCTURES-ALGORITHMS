package Graph;

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

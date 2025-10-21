package Graph;

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

    public int minimumEffortPath(int[][] heights) {

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

}

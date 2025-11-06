package Graph;

public class Floyd_Warshal_Algorithm {

    private static void floydWarshall(int[][] dist) {

        int n = dist.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == - 1) {
                    dist[i][j] = (int) 1e8;
                }

                if (i == j) {
                    dist[i][j] = 0;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dist[i][i] < 0) {
                System.out.println("There's a negative cycle in the graph...");
                return;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == (int) 1e8) {
                    dist[i][j] = - 1;
                }
            }
        }

    }

    public static void main(String[] args) {

        int[][] dist = {{0, 1, 4}, {1, 0, 2}, {4, 2, 0}};
        int n = dist.length;

        floydWarshall(dist);
        System.out.println("Following matrix represents the shortest distances between every pair of vertices : ");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == (int) 1e8) {
                    System.out.print("INFINITY ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }

    }

}
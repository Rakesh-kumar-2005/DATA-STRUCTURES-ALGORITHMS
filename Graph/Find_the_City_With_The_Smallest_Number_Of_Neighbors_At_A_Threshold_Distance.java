package Graph;

public class Find_the_City_With_The_Smallest_Number_Of_Neighbors_At_A_Threshold_Distance {

    private static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int m = edges.length;

        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            distance[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            distance[u][v] = wt;
            distance[v][u] = wt;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][k] == Integer.MAX_VALUE || distance[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }

                    distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]);
                }
            }
        }

        int countCity = n;
        int cityNo = - 1;

        for (int city = 0; city < n; city++) {
            int count = 0;

            for (int adjCity = 0; adjCity < n; adjCity++) {
                if (distance[city][adjCity] <= distanceThreshold) {
                    count++;
                }
            }

            if (count <= countCity) {
                countCity = count;
                cityNo = city;
            }
        }

        return cityNo;
    }

    public static void main(String[] args) {

        int n = 4;
        int[][] edges = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        int distanceThreshold = 4;

        int cityWithMinNeighbours = findTheCity(n, edges, distanceThreshold);
        System.out.println("The city with the smallest number of neighbors at a threshold distance is : " + cityWithMinNeighbours);
    }

}
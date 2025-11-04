package Graph;

public class Bellman_Ford_Algorithm {

    private static int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        for (int i = 0; i < V; i++) {
            dist[i] = (int) 1e8;
        }
        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int[] it : edges) {
                int u = it[0];
                int v = it[1];
                int w = it[2];

                if (dist[u] != (int) 1e8 && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        for (int[] it : edges) {
            int u = it[0];
            int v = it[1];
            int w = it[2];

            if (dist[u] != (int) 1e8 && dist[u] + w < dist[v]) {
                int[] temp = new int[1];
                temp[0] = - 1;
                return temp;
            }

        }

        return dist;

    }

    public static void main(String[] args) {

        int[][] edges = {{0, 1, 5}, {1, 2, 3}, {2, 3, 1}, {3, 4, 2}, {4, 5, 4}, {5, 6, 6}, {6, 7, 2}, {7, 0, 7}};
        int V = 8;
        int src = 0;

        int[] dist = bellmanFord(V, edges, src);

        for (int i = 0; i < V; i++) {
            System.out.println("Distance from " + src + " to " + i + " is " + dist[i]);
        }

    }

}

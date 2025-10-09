package Graph;

import java.util.PriorityQueue;

public class Dijkstra_Algorithm_Grid {

    static class Pair {
        int node;
        int distance;

        public Pair(int node, int dist) {
            this.node = node;
            this.distance = dist;
        }

    }


    private static int[] dijkstra(int[][] graph, int vertices, int src) {

        int[] distances = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y) -> x.distance - y.distance);

        distances[src] = 0;
        pq.add(new Pair(src, 0));

        while (! pq.isEmpty()) {

            Pair currPair = pq.poll();
            int currNode = currPair.node;
            int currDistance = currPair.distance;

            for (int adjNode = 0; adjNode < vertices; adjNode++) {

                int edgeWeight = graph[currNode][adjNode];

                if (edgeWeight > 0 && adjNode != currNode) {

                    if (currDistance + edgeWeight < distances[adjNode]) {
                        distances[adjNode] = currDistance + edgeWeight;
                        pq.add(new Pair(adjNode, distances[adjNode]));
                    }

                }

            }

        }

        return distances;
        
    }

    public static void main(String[] args) {

        int vertices = 5;
        int[][] graph = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = 0;
            }
        }

        // Node 0 edges
        graph[0][1] = 4;
        graph[0][2] = 1;

        // Node 1 edges
        graph[1][0] = 4;
        graph[1][2] = 2;
        graph[1][3] = 5;

        // Node 2 edges
        graph[2][0] = 1;
        graph[2][1] = 2;
        graph[2][3] = 3;
        graph[2][4] = 2;

        // Node 3 edges
        graph[3][1] = 5;
        graph[3][2] = 3;
        graph[3][4] = 1;

        // Node 4 edges
        graph[4][2] = 2;
        graph[4][3] = 1;

        int source = 0;

        System.out.println("Adjacency Matrix:");

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        int[] distances = dijkstra(graph, vertices, source);

        System.out.println("Shortest distances from source vertex " + source + ":");
        for (int i = 0; i < vertices; i++) {

            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("Vertex " + i + ": INFINITY (Not reachable)");
            } else {
                System.out.println("Vertex " + i + ": " + distances[i]);
            }

        }

    }

}
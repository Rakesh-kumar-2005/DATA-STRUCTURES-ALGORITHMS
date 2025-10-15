package Graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Print_Shortest_Path {

    static class Pair {
        int distance;
        int node;

        public Pair(int dist, int node) {
            this.distance = dist;
            this.node = node;
        }

    }

    private static ArrayList<Integer> printShortestPath(int[][] grid, int n, int m) {

        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            graph.get(grid[i][0]).add(new Pair(grid[i][1], grid[i][2]));
            graph.get(grid[i][1]).add(new Pair(grid[i][0], grid[i][2]));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.distance - y.distance);
        int[] distances = new int[n + 1];
        int[] parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            distances[i] = Integer.MAX_VALUE;
            parent[i] = i;
        }

        pq.add(new Pair(0, 1));

        while (! pq.isEmpty()) {
            Pair currPair = pq.poll();
            int currNode = currPair.node;
            int currDistance = currPair.distance;

            for (Pair adjacents : graph.get(currNode)) {
                int adjNode = adjacents.node;
                int adjDistance = adjacents.distance;

                if (currDistance + adjDistance < distances[adjNode]) {
                    distances[adjNode] = currDistance + adjDistance;
                    pq.add(new Pair(distances[adjNode], adjNode));
                    parent[adjNode] = currNode;
                }

            }

        }

        
    }

}

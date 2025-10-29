package Graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Network_Delay_Time {

    static class Pair {
        int node;
        int distance;

        public Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    private static int networkDelayTime(int[][] times, int n, int src) {

        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int m = times.length;
        for (int i = 0; i < m; i++) {
            graph.get(times[i][0]).add(new Pair(times[i][1], times[i][2]));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y) -> Integer.compare(x.distance, y.distance));
        pq.add(new Pair(src, 0));

        int[] distances = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        distances[src] = 0;

        while (! pq.isEmpty()) {

            Pair currPair = pq.poll();
            int currNode = currPair.node;
            int currDistance = currPair.distance;

            if (currDistance > distances[currNode]) {
                continue;
            }

            for (Pair adjPair : graph.get(currNode)) {

                int adjNode = adjPair.node;
                int edgeWeight = adjPair.distance;
                int newDistance = currDistance + edgeWeight;

                if (newDistance < distances[adjNode]) {
                    distances[adjNode] = newDistance;
                    pq.add(new Pair(adjNode, newDistance));
                }
            }
        }

        int maxDistance = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                return - 1;
            }
            maxDistance = Math.max(maxDistance, distances[i]);
        }

        return maxDistance;
    }

    public static void main(String[] args) {

        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4;
        int src = 2;

        int ans = networkDelayTime(times, n, src);
        System.out.println("The maximum time taken to reach all nodes is: " + ans);

    }
}

package Graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Number_Of_Ways_To_Arrive_At_Destination {

    static class Pair {
        int node;
        long distance;

        public Pair(int node, long distance) {
            this.node = node;
            this.distance = distance;
        }

    }

    private static int countPaths(int n, int[][] roads) {

        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int m = roads.length;
        for (int i = 0; i < m; i++) {
            graph.get(roads[i][0]).add(new Pair(roads[i][1], roads[i][2]));
            graph.get(roads[i][1]).add(new Pair(roads[i][0], roads[i][2]));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y) -> Long.compare(x.distance, y.distance));
        pq.add(new Pair(0, 0));

        long[] distances = new long[n];
        int[] ways = new int[n];

        for (int i = 0; i < n; i++) {
            distances[i] = Long.MAX_VALUE;
            ways[i] = 0;
        }

        distances[0] = 0;
        ways[0] = 1;
        int mod = (int) (1e9 + 7);

        while (! pq.isEmpty()) {

            Pair currPair = pq.poll();
            int currNode = currPair.node;
            long currDist = currPair.distance;

            if (currDist > distances[currNode]) {
                continue;
            }

            for (Pair adjPair : graph.get(currNode)) {

                int adjNode = adjPair.node;
                long edgeWeight = adjPair.distance;
                long newDistance = currDist + edgeWeight;

                if (newDistance < distances[adjNode]) {
                    distances[adjNode] = newDistance;
                    pq.add(new Pair(adjNode, newDistance));
                    ways[adjNode] = ways[currNode];
                } else if (newDistance == distances[adjNode]) {
                    ways[adjNode] = (ways[adjNode] + ways[currNode]) % mod;
                }
            }
        }

        return ways[n - 1] % mod;
    }

    public static void main(String[] args) {

        int n = 7;
        int[][] roads = {{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3}, {3, 5, 1}, {6, 5, 1}};

        System.out.println("The number of ways to arrive at the destination with the minimum cost is : " + countPaths(n, roads));

    }
}
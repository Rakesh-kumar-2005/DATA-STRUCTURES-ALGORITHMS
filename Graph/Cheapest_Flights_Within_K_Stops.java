package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Cheapest_Flights_Within_K_Stops {

    static class Pair {
        int node;
        int weight;

        public Pair(int node, int dist) {
            this.node = node;
            this.weight = dist;
        }
    }

    static class Tuple {
        int node;
        int dist;
        int stops;

        public Tuple(int node, int dist, int stops) {
            this.node = node;
            this.dist = dist;
            this.stops = stops;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int m = flights.length;
        for (int[] flight : flights) {
            graph.get(flight[0]).add(new Pair(flight[1], flight[2]));
        }

        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(src, 0, 0));

        int[] distances = new int[n];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;

        while (! q.isEmpty()) {
            Tuple currPair = q.poll();

            int currNode = currPair.node;
            int currDist = currPair.dist;
            int currStops = currPair.stops;

            if (currStops > k) {
                continue;
            }

            for (Pair adjacent : graph.get(currNode)) {

                int adjNode = adjacent.node;
                int edgeWeight = adjacent.weight;
                int newDistance = currDist + edgeWeight;

                if (distances[adjNode] > newDistance && currStops <= k) {
                    distances[adjNode] = newDistance;
                    q.add(new Tuple(adjNode, newDistance, currStops + 1));
                }
            }
        }

        if (distances[dst] == Integer.MAX_VALUE) {
            return - 1;
        }

        return distances[dst];
    }

}

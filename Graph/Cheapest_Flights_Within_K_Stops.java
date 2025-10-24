package Graph;

/*
Description:
    This program finds the cheapest price to travel from a source city to a destination city
    with at most K stops in between. Each flight between cities has a cost, and the goal is to
    minimize the total cost while respecting the stop constraint.

Problem Statement:
    You are given:
        - n: the total number of cities (numbered from 0 to n-1)
        - flights: a list of flight routes where each route is represented as [from, to, cost]
        - src: the starting city
        - dst: the destination city
        - k: the maximum number of stops allowed between src and dst

    You need to return the minimum cost to travel from src to dst with at most k stops.
    If it is not possible to reach the destination within the allowed stops, return -1.

Algorithm Used:
    This solution uses a modified Breadth-First Search (BFS) approach,
    often referred to as the Bellman-Ford (K-level) BFS variant.

Approach:
    1. Build an adjacency list to represent the graph, where each node points
       to its neighboring nodes along with the corresponding flight cost.
    2. Initialize a queue for BFS traversal. Each queue element stores:
         - current node
         - total cost to reach it
         - number of stops taken so far
    3. Use an array to store the minimum cost required to reach each city.
    4. Start the traversal from the source node with cost = 0 and stops = 0.
    5. While the queue is not empty:
         - Remove the current element from the queue.
         - If the number of stops exceeds k, skip further exploration.
         - For each neighboring city:
             a. Calculate the new total cost.
             b. If this cost is cheaper than the previously recorded cost for that city
                and the current stop count does not exceed k,
                update the cost and push the new city into the queue with stops + 1.
    6. After traversal, if the destination city’s cost remains infinity,
       return -1 (indicating it’s unreachable within k stops).
       Otherwise, return the recorded minimum cost.

Example Input:
    n = 4
    flights = {
        {0, 1, 100},
        {1, 2, 100},
        {2, 3, 100},
        {0, 3, 500}
    }
    src = 0, dst = 3, k = 1

Example Output:
    500

Explanation:
    The direct route 0 → 3 costs 500.
    The route 0 → 1 → 2 → 3 has two stops, exceeding k = 1.
    Hence, the cheapest valid path is the direct flight with cost 500.

Edge Cases Considered:
    - No available flights between src and dst
    - Destination unreachable within K stops
    - Multiple paths with the same cost
    - Source and destination are the same (cost = 0)
    - Large input sizes

Key Concepts Used:
    - Graph representation using adjacency lists
    - BFS traversal with level (stop) tracking
    - Cost optimization with pruning

Time and Space Complexity:
    Time Complexity: O(N + E), where E is the number of flights
        (Each edge is processed at most once per level)
    Space Complexity: O(N + E)
        (For storing the graph, queue, and distance array)
*/

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

    private static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

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

    public static void main(String[] args) {

        int n = 4;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};

        int src = 0;
        int dst = 3;

        int k = 1;
        int cheapestPrice = findCheapestPrice(n, flights, src, dst, k);

        System.out.println("From source " + src + " to destination " + dst + " with at most number of " + k + " stops will take us the distance of " + cheapestPrice);
    }

}

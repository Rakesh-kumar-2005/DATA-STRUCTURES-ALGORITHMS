package Graph;

import java.util.ArrayList;
import java.util.Collections;
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

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = grid[i][0];
            int v = grid[i][1];
            int weight = grid[i][2];

            graph.get(u).add(new Pair(weight, v));
            graph.get(v).add(new Pair(weight, u));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.distance - y.distance);
        int[] distances = new int[n + 1];
        int[] parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            distances[i] = Integer.MAX_VALUE;
            parent[i] = i;
        }

        distances[1] = 0;
        pq.add(new Pair(0, 1));

        while (! pq.isEmpty()) {

            Pair currPair = pq.poll();
            int currNode = currPair.node;
            int currDistance = currPair.distance;

            if (currDistance > distances[currNode]) {
                continue;
            }

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

        ArrayList<Integer> path = new ArrayList<>();

        if (distances[n] == Integer.MAX_VALUE) {
            path.add(- 1);
            return path;
        }

        int node = n;

        while (parent[node] != node) {
            path.add(node);
            node = parent[node];
        }

        path.add(1);
        Collections.reverse(path);

        return path;

    }

    private static void print(ArrayList<Integer> path) {

        if (path.size() == 1 && path.get(0) == - 1) {
            System.out.println("No path exists!");
            return;
        }

        for (int i = 0; i < path.size() - 1; i++) {
            System.out.print(path.get(i) + " -> ");
        }

        System.out.println(path.get(path.size() - 1));
    }

    public static void main(String[] args) {

        int[][] grid = {{1, 2, 1}, {2, 3, 1}, {3, 4, 1}, {4, 5, 1}};
        int n = 5;
        int m = 4;

        ArrayList<Integer> path = printShortestPath(grid, n, m);

        System.out.println("The Shortest Path is: ");
        print(path);

        System.out.println("\nShortest Distance: " + (path.get(0) == - 1 ? "Unreachable" : (path.size() - 1)));
    }
}

package Graph;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Shortest_Path_In_Undirected_Graph {

    private static int[] shortestPath(int[][] grid, int nodes, int edges, int src) {

        // Formation of The Graph...
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }

        // Connecting the edges...
        for (int i = 0; i < edges; i++) {
            graph.get(grid[i][0]).add(grid[i][1]);
            graph.get(grid[i][1]).add(grid[i][0]);
        }

        int[] distanceFromSrc = new int[nodes];
        Arrays.fill(distanceFromSrc, Integer.MAX_VALUE);

        Queue<Integer> q = new ArrayDeque<>();
        q.add(src);
        distanceFromSrc[src] = 0;

        while (! q.isEmpty()) {

            int currNode = q.poll();

            for (int adjacent : graph.get(currNode)) {

                if (distanceFromSrc[currNode] + 1 < distanceFromSrc[adjacent]) {
                    distanceFromSrc[adjacent] = distanceFromSrc[currNode] + 1;
                    q.add(adjacent);
                }

            }

        }

        return distanceFromSrc;

    }

    public static void main(String[] args) {

        int[][] grid = {{0, 1}, {0, 2}, {1, 2}, {1, 3}, {2, 3}, {3, 4}};
        int nodes = 5, edges = 6, source = 0;

        System.out.println("Shortest Path from " + source + " to all nodes in the graph is: ");

        int[] shortestPath = shortestPath(grid, nodes, edges, source);

        for (int i = 0; i < shortestPath.length; i++) {
            System.out.println("The shortest path from " + source + " to " + i + " is " + shortestPath[i]);
        }

    }

}
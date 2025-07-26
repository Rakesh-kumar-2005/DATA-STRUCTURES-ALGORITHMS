package Graph;

import java.util.ArrayList;
import java.util.Stack;

public class Shortest_Path_In_Directed_Acyclic_Graph_With_Weights {

    static class Pair {
        int destination;
        int weight;

        public Pair(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

    }

    private static void topoSort(int currNode, ArrayList<ArrayList<Pair>> adj, boolean[] visited, Stack<Integer> st) {

        visited[currNode] = true;

        for (Pair currPair : adj.get(currNode)) {
            if (! visited[currPair.destination]) {
                topoSort(currPair.destination, adj, visited, st);
            }
        }

        st.push(currNode);

    }

    private static int[] shortestPath(int[][] graph, int vertices, int m) {
        // Formation of the graph...
        ArrayList<ArrayList<Pair>> adjacencyList = new ArrayList<>();

        // Initialize adjacency list with empty lists
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Connecting the edges with corresponding weights...
        for (int i = 0; i < vertices; i++) {
            int src = graph[i][0];
            int dest = graph[i][1];
            int weight = graph[i][2];
            adjacencyList.get(src).add(new Pair(dest, weight));
        }

        boolean[] visited = new boolean[vertices];
        Stack<Integer> st = new Stack<>();

        // Topo sort...
        for (int i = 0; i < vertices; i++) {
            if (! visited[i]) {
                topoSort(i, adjacencyList, visited, st);
            }
        }

        int[] dist = new int[vertices];
        for (int i = 1; i < vertices; i++) {
            dist[i] = Integer.MAX_VALUE;
        }


        while (! st.isEmpty()) {
            int currNode = st.pop();

            for (Pair currPair : adjacencyList.get(currNode)) {

                int currWeight = currPair.weight;
                int currDestination = currPair.destination;

                if (dist[currDestination] > dist[currNode] + currWeight) {
                    dist[currDestination] = dist[currNode] + currWeight;
                }
                
            }

        }

        return dist;
    }

    public static void main(String[] args) {
        int[][] graph = {{0, 1, 1}, {0, 2, 3}, {1, 2, 1}, {1, 3, 1}, {2, 3, 1}};
        int[] ans = shortestPath(graph, 4, 3);

        for (int i = 1; i < ans.length; i++) {
            System.out.println("The shortest path from " + ans[0] + " to " + i + " is " + ans[i]);
        }

    }

}
package Graph;

/*

Description:
    -> This program computes the Shortest Path from a Source Node in a Directed Acyclic Graph (DAG) using Topological Sorting...
    -> The graph is weighted and directed, and the algorithm finds the minimum distance from the source node (assumed as node 0) to all other vertices...

Problem Statement:
    -> You are given a list of edges, where each edge is represented as a triplet {u, v, w}...
        - u: source node
        - v: destination node
        - w: weight of the directed edge from u to v...
    -> Also given: vertices (total number of nodes in the graph) and m (number of edges)...
    -> Your task is to find the shortest distances from node 0 to every other node in the graph...

Approach:
    -> Step 1: Build the Graph using an Adjacency List...
        - Initialize a list of empty lists (one for each vertex)...
        - For each edge {u, v, w}, add (v, w) to the adjacency list of u...

    -> Step 2: Perform Topological Sorting using DFS...
        - Use a visited[] array to mark nodes visited during DFS...
        - Recursively traverse unvisited neighbors, and push nodes onto a stack once all their dependencies are explored...

    -> Step 3: Initialize Distance Array...
        - Set distance of source node (0) to 0 and others to Integer.MAX_VALUE (infinity)...

    -> Step 4: Process Nodes in Topological Order to Relax Edges...
        - Pop nodes from the stack...
        - For each neighbor of the current node, update its distance if a shorter path is found...

Edge Case Handling:
    -> This algorithm assumes the input graph is a DAG...
    -> If the graph contains a cycle, the result may be incorrect, as topological sorting is undefined for cyclic graphs...

Time and Space Complexity:
    -> Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges...
    -> Space Complexity: O(V + E), for the adjacency list, visited array, and distance array...

Demonstration:
    -> Test Case:
        Input: edges = {{0, 1, 1}, {0, 2, 3}, {1, 2, 1}, {1, 3, 1}, {2, 3, 1}}, vertices = 4
        Output:
            The shortest path from 0 to 1 is 1
            The shortest path from 0 to 2 is 2
            The shortest path from 0 to 3 is 2

    -> Explanation:
        - 0 → 1 → 2 → 3 is the shortest path to node 3 with total cost 1 + 1 + 1 = 3
        - But 0 → 1 → 3 is shorter with cost = 1 + 1 = 2, hence it's selected...

*/

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

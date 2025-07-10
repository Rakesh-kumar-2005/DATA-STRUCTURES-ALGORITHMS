package Graph;

/*

Description:
    -> This program performs Topological Sorting on a Directed Acyclic Graph (DAG) using Kahn’s Algorithm (BFS-based approach)...
    -> It builds the topological order by repeatedly removing nodes with zero in-degree and updating the in-degrees of their neighbors...

Problem Statement:
    -> Given a directed graph with V vertices and a list of edges...
    -> Return a valid topological ordering of its nodes using Kahn’s Algorithm...
    -> Topological sorting is only possible if the graph is a DAG (Directed Acyclic Graph)...

Approach:
    > In-Degree Counting:
        -> Compute the in-degree (number of incoming edges) for each vertex...
        -> Nodes with in-degree 0 can be processed first...

    > BFS Queue Processing:
        -> Add all vertices with in-degree 0 to a queue...
        -> While the queue is not empty:
            --> Pop a node and add it to the topological order...
            --> For each neighbor, reduce its in-degree by 1...
            --> If in-degree becomes 0, add it to the queue...

Algorithm Steps:
    -> Step 1: Initialize an array `inDegrees[]` of size V to 0...
    -> Step 2: Traverse adjacency list to compute in-degrees of all nodes...
    -> Step 3: Add all nodes with in-degree 0 to a queue...
    -> Step 4: While queue is not empty:
        a. Pop node from queue, add to result array...
        b. For each neighbor, decrement its in-degree...
        c. If in-degree becomes 0, enqueue that neighbor...
    -> Step 5: Return the result array containing topological sort...

Key Characteristics:
    -> Ensures valid topological order using in-degree and queue-based traversal...
    -> Detects graph structure level-by-level like BFS...
    -> Suitable for scheduling and dependency resolution problems...

Time and Space Complexity:
    -> Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges...
    -> Space Complexity: O(V + E), for adjacency list and queue...

Demonstration:
    -> Input Graph:
        Vertices = 5
        Edges = { (0→1), (0→2), (0→3), (0→4), (1→2), (1→4), (2→3), (2→4) }
    -> One valid Topological Sort: 0 1 2 3 4
    -> Output: The topological sort is : 0 1 2 3 4

*/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Topological_Sorting_Kahn_Algorithm {

    private static void addEdge(ArrayList<ArrayList<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
    }

    private static int[] topologicalSorting(int V, ArrayList<ArrayList<Integer>> adjacencyList) {

        int[] inDegrees = new int[V];

        for (int i = 0; i < V; i++) {
            for (int adjacent : adjacencyList.get(i)) {
                inDegrees[adjacent]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < V; i++) {
            if (inDegrees[i] == 0) {
                q.add(i);
            }
        }

        int idx = 0;
        int[] topoSort = new int[V];

        while (! q.isEmpty()) {

            int currNode = q.poll();
            topoSort[idx++] = currNode;

            for (int adjacent : adjacencyList.get(currNode)) {

                inDegrees[adjacent]--;
                if (inDegrees[adjacent] == 0) {
                    q.add(adjacent);
                }
            }
        }

        return topoSort;
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int vertices = 5;

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 0, 3);
        addEdge(graph, 0, 4);
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 4);
        addEdge(graph, 2, 3);
        addEdge(graph, 2, 4);


        int[] ans = topologicalSorting(vertices, graph);
        System.out.println("The topological sort is : ");

        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }

    }
}

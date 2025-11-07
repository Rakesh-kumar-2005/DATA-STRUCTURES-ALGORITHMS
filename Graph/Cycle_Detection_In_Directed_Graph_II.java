package Graph;

/*

Description:
    -> This program detects whether a Directed graph contains a cycle using Kahn’s Algorithm (BFS-based Topological Sorting)...
    -> The presence of a cycle is determined by checking whether a valid topological ordering is possible...

Problem Statement:
    -> Given a directed graph with V vertices and a list of edges...
    -> Return true if the graph contains a cycle, otherwise return false...
    -> A graph has a cycle if and only if a valid topological sort is not possible...

Approach:
    > In-Degree Calculation:
        -> Calculate the in-degree (number of incoming edges) for each vertex...

    > Kahn’s Algorithm (BFS):
        -> Add all vertices with in-degree 0 to a queue...
        -> Repeatedly remove nodes from the queue and reduce in-degrees of adjacent nodes...
        -> Track how many nodes are processed...

    > Cycle Detection:
        -> If the number of processed nodes is less than the total number of vertices, a cycle exists...
        -> This means some nodes couldn't be sorted topologically due to circular dependencies...

Algorithm Steps:
    -> Step 1: Initialize `inDegrees[]` array of size V to 0...
    -> Step 2: Traverse the adjacency list and update in-degree of each node...
    -> Step 3: Add all nodes with in-degree 0 to a queue...
    -> Step 4: While the queue is not empty:
        a. Remove a node from the queue and increment the processed count...
        b. For each neighbor, decrement its in-degree...
        c. If a neighbor’s in-degree becomes 0, add it to the queue...
    -> Step 5: After processing, if count < V → cycle exists...

Key Characteristics:
    -> Uses BFS-style topological sort to detect cycles in a directed graph...
    -> Efficient for large graphs due to linear time traversal...
    -> Does not modify the original graph structure...

Time and Space Complexity:
    -> Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges...
    -> Space Complexity: O(V), for storing in-degrees and queue...

Demonstration:
    -> Input Edges:
        1→2→3→4→5→6
               ↓
               7→5
        and a separate cycle: 8→9→10→8
    -> Component with 8, 9, 10 forms a cycle...
    -> Output: This graph contains a cycle...

*/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Cycle_Detection_In_Directed_Graph_II {

    private static boolean isCyclic(ArrayList<ArrayList<Integer>> adjacencyList, int V) {

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

        int count = 0;

        while (! q.isEmpty()) {

            int currNode = q.poll();
            count++;

            for (int adjacent : adjacencyList.get(currNode)) {

                inDegrees[adjacent]--;
                if (inDegrees[adjacent] == 0) {
                    q.add(adjacent);
                }
            }
        }

        return count == V;
    }

    public static void main(String[] args) {

        int V = 11;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(7);
        adj.get(4).add(5);
        adj.get(5).add(6);
        adj.get(7).add(5);
        adj.get(8).add(9);
        adj.get(9).add(10);
        adj.get(10).add(8);

        if (isCyclic(adj, V)) {
            System.out.println("This graph contains a cycle...");
        } else {
            System.out.println("This graph doesn't contain a cycle...");
        }
    }

}


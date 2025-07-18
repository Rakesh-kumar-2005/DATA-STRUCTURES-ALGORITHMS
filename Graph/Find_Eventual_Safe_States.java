package Graph;

/*

Description:
    -> This program identifies all the **eventual safe nodes** in a directed graph...
    -> A node is considered safe if every possible path starting from it eventually ends at a terminal node (i.e., a node with no outgoing edges)...

Problem Statement:
    -> You are given a directed graph represented as an adjacency list `graph[i]`, which contains the list of nodes `i` has edges to...
    -> Your task is to return a list of all the safe nodes in ascending order...
    -> A node is **eventually safe** if all paths starting from it lead to terminal nodes and do not result in a cycle...

Approach:
    -> Reverse the graph:
        - Create a new graph where all edges are reversed (i.e., if `u → v` in the original graph, create `v → u`)...
        - This helps in treating terminal nodes as starting points in a topological sort...

    -> Count in-degrees:
        - For the reversed graph, compute in-degrees of all nodes...

    -> Kahn’s Algorithm (BFS Topological Sort):
        -> Start BFS with nodes having in-degree 0 (i.e., terminal nodes in original graph)...
        -> For each node:
            a. Add to result list...
            b. Decrease in-degrees of its neighbors...
            c. If any neighbor’s in-degree becomes 0, enqueue it...

    -> Finally, sort the result list to return safe nodes in ascending order...

Algorithm Steps:
    -> Step 1: Initialize the reversed graph and compute in-degrees...
    -> Step 2: Enqueue all nodes with in-degree 0 into a queue...
    -> Step 3: While the queue is not empty:
        a. Pop node and add to safe list...
        b. Decrease in-degrees of all neighbors...
        c. Enqueue neighbors whose in-degree becomes 0...
    -> Step 4: Sort the resulting list of safe nodes and return...

Key Characteristics:
    -> Detects all nodes that are not part of or leading into a cycle...
    -> Efficient for DAGs (Directed Acyclic Graphs) or graphs with sparse cycles...

Time and Space Complexity:
    -> Time Complexity: O(V + E), where V = number of nodes, E = number of edges...
    -> Space Complexity: O(V + E), for the graph, in-degree array, queue, and result list...

Demonstration:
    -> Input:
        graph = {
            {1, 2, 3, 4}, 
            {1, 2}, 
            {3, 4}, 
            {0, 4}, 
            {}
        }
    -> Output:
        The Eventual safe nodes in Ascending order are :
        [4]

    -> Explanation:
        Node 4 is a terminal node, and all paths from node 2 also lead to 4...
        But node 0 and 3 form a cycle (0 → 3 → 0), and node 1 has a self-loop (1 → 1)...
*/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Find_Eventual_Safe_States {

    private static ArrayList<Integer> eventualSafeNodes(int[][] graph) {

        int length = graph.length;

        // Formation of new graph in reverse direction...
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        int[] inDegrees = new int[length];

        // Connecting the edges in reverse direction...
        for (int i = 0; i < length; i++) {
            for (int adjacent : graph[i]) {
                adjacencyList.get(adjacent).add(i);
                inDegrees[i]++;
            }
        }


        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < length; i++) {
            if (inDegrees[i] == 0) {
                q.add(i);
            }
        }

        ArrayList<Integer> topoSort = new ArrayList<>();

        while (! q.isEmpty()) {
            int currNode = q.poll();
            topoSort.add(currNode);

            for (int adjacent : adjacencyList.get(currNode)) {
                inDegrees[adjacent]--;
                if (inDegrees[adjacent] == 0) {
                    q.add(adjacent);
                }
            }
        }

        Collections.sort(topoSort);
        return topoSort;

    }


    public static void main(String[] args) {

        int[][] graph = {{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}};
        ArrayList<Integer> safePlaces = eventualSafeNodes(graph);

        System.out.println("The Eventual safe nodes in Ascending order are : ");
        System.out.println(safePlaces);

    }
}

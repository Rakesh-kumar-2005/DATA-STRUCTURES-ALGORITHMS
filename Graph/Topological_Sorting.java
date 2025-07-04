package Graph;

/*

Description:
    -> This program performs Topological Sorting on a Directed Acyclic Graph (DAG) using Depth-First Search (DFS)...
    -> It detects cycles during traversal and throws an exception if the graph is not a valid DAG...
    -> The nodes are processed in post-order and pushed onto a stack to reverse the ordering...

Problem Statement:
    -> Given a directed graph with `V` vertices and a list of edges, return a valid topological ordering of its nodes...
    -> If the graph contains a cycle, report that topological sorting is not possible...

Approach:
    > DFS-Based Traversal:
        -> Use a DFS helper to recursively visit each unvisited node...
        -> Track visited nodes and recursion stack to detect cycles during traversal...
        -> Once a node finishes processing, push it onto a stack to maintain post-order...

    > Cycle Detection:
        -> Maintain a recursion stack to detect back edges...
        -> If a visited node appears again in the current DFS path, a cycle is detected...

Algorithm Steps:
    -> Step 1: Initialize `visited[]` and `recStack[]` to track visited nodes and recursion path...
    -> Step 2: For every unvisited node `i` from `0` to `V - 1`:
        a. Call DFS for node `i`...
        b. If DFS returns true (cycle found), throw an error...
    -> Step 3: After all nodes are visited, pop from the stack to get topological order...
    -> Step 4: Return the ordered array...

Key Characteristics:
    -> Uses post-order DFS for correct topological order...
    -> Detects cycles to ensure validity of result...
    -> Relies on stack to reverse DFS finishing order...

Time and Space Complexity:
    -> Time Complexity: O(V + E), where `V` is number of vertices and `E` is number of edges...
    -> Space Complexity: O(V), for visited arrays and recursion stack...

Demonstration:
    -> Input Graph: 5 vertices, Edges = { (0→1), (0→2), (0→3), (0→4), (1→2), (1→4), (2→3), (2→4) }
    -> Valid Topological Sort: 0 1 2 3 4
    -> Output: The topological sort is : 0 1 2 3 4

*/

import java.util.ArrayList;
import java.util.Stack;

public class Topological_Sorting {

    private static void addEdge(ArrayList<ArrayList<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
    }

    private static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {

        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (! visited[i]) {
                if (dfs(i, visited, recStack, st, adj)) {
                    throw new RuntimeException("Cycle detected, topological sort not possible");
                }
            }
        }

        int[] ans = new int[V];
        int idx = 0;

        while (! st.isEmpty()) {
            ans[idx++] = st.pop();
        }

        return ans;
    }


    private static boolean dfs(int node, boolean[] visited, boolean[] recStack, Stack<Integer> st, ArrayList<ArrayList<Integer>> adj) {

        visited[node] = true;
        recStack[node] = true;

        for (int neighbor : adj.get(node)) {

            if (! visited[neighbor]) {
                if (dfs(neighbor, visited, recStack, st, adj)) return true;
            } else if (recStack[neighbor]) {
                return true;
            }

        }

        recStack[node] = false;
        st.push(node);

        return false;

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


        int[] ans = topoSort(vertices, graph);
        System.out.println("The topological sort is : ");

        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }

    }

}

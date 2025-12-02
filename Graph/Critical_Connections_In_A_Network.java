package Graph;

/*

Description:
  Following program demonstrates the solution to the "Critical Connections In A Network" problem using Tarjan’s Algorithm to identify all bridges in an undirected graph where removing such edges increases the number of connected components...

Problem Statement:
  -> You are given an undirected graph with n nodes and a list of edges (connections)...
  -> A critical connection (also called a bridge) is an edge that, if removed, disconnects part of the network...
  -> The goal is to find all such bridges efficiently using depth-first search and low-link values...

Approach:
  > Tarjan’s Bridge-Finding Algorithm:
     i. Perform DFS traversal while maintaining two arrays:
        - tInsert[] : discovery time or time of insertion of each node...
        - lowestTInsert[] : lowest reachable discovery time via DFS tree edges or back edges...
     ii. When exploring a neighbor:
        - Use recursive DFS call if neighbor is unvisited...
        - Update low-link values after the DFS call returns...
     iii. A bridge is found when:
        lowestTInsert[child] > tInsert[parent]...
     iv. Back edges update low-link values using tInsert[nei], not lowestTInsert...

> DFS Traversal Logic:
  -> For each node:
       - Mark it visited and set both discovery and low times...
       - For each adjacent neighbor:
           · Skip the parent node...
           · If unvisited: DFS recursively, update low-link...
           · If visited and not parent: update low-link using back-edge...
  -> After DFS completes, every bridge is added to the result list...

> Algorithm Steps:
  -> Initialize adjacency list for n nodes...
  -> Build the undirected graph from the list of connections...
  -> Prepare visited[], tInsert[], lowestTInsert[] arrays...
  -> Run DFS starting from node 0:
       * Assign discovery time using a global timer...
       * Track lowest reachable discovery time for each node...
       * Add (u, v) to bridges list if the condition lowestTInsert[v] > tInsert[u] holds...
  -> Return the list of all identified critical edges...

> Implementation Note:
  -> timer must be reset for each new test case to ensure correct ordering...
  -> Back-edge case must use tInsert[] to avoid incorrect low-link propagation...
  -> Bridges are stored as Integer pairs inside ArrayList for easy printing...
  -> Graph is zero-indexed and undirected, so every edge is added both ways...

> Example:
  -> Chain graph: 0—1—2—3...
       Every edge is a bridge since removing any edge disconnects the chain...
  -> Triangle graph: 0—1—2—0...
       No bridges because each node has multiple alternative paths...
  -> Cycle with a tail:
       Cycle edges are safe, but the tail edge becomes a bridge...

> Edge Cases:
  -> Fully connected graph (complete graph) has no bridges...
  -> Graph with no cycles becomes a tree where all edges are bridges...
  -> Single node or empty graph has zero bridges...
  -> Graph may contain multiple disconnected components (DFS handles each)...  

> Time and Space Complexity:
  -> Time Complexity: O(V + E) due to single DFS traversal...
  -> Space Complexity: O(V + E) for adjacency list and recursion stack...

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Critical_Connections_In_A_Network {

    private static int timer = 1;

    private static void dfs(
        int node,
        int parent,
        boolean[] visited,
        ArrayList<ArrayList<Integer>> graph,
        int[] tInsert,
        int[] lowestTInsert,
        ArrayList<ArrayList<Integer>> bridges
    ) {

        visited[node] = true;
        lowestTInsert[node] = tInsert[node] = timer++;

        for (int neighbour : graph.get(node)) {

            if (neighbour == parent)
                continue;

            if (! visited[neighbour]) {

                dfs(neighbour, node, visited, graph, tInsert, lowestTInsert, bridges);

                lowestTInsert[node] = Math.min(lowestTInsert[node], lowestTInsert[neighbour]);

                // condition for bridge
                if (lowestTInsert[neighbour] > tInsert[node]) {
                    bridges.add(new ArrayList<>(Arrays.asList(node, neighbour)));
                }

            } else {
                // back-edge case (IMPORTANT FIX → use tInsert[])
                lowestTInsert[node] = Math.min(lowestTInsert[node], tInsert[neighbour]);
            }
            
        }
        
    }

    public static ArrayList<ArrayList<Integer>> criticalConnections(int n, ArrayList<ArrayList<Integer>> connections) {

        timer = 1; // reset for each new test

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (ArrayList<Integer> conn : connections) {
            int u = conn.get(0);
            int v = conn.get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        int[] tInsert = new int[n];
        int[] lowestTInsert = new int[n];

        ArrayList<ArrayList<Integer>> bridges = new ArrayList<>();
        dfs(0, -1, visited, graph, tInsert, lowestTInsert, bridges);

        return bridges;
    }

    public static void main(String[] args) {

        // Helper to print edges
        java.util.function.Consumer<List<ArrayList<Integer>>> printEdges = list -> {
            if (list.isEmpty()) {
                System.out.println("No critical connections (graph stays connected if any edge fails).");
                return;
            }
            System.out.println("Critical connections (bridges): ");
            for (ArrayList<Integer> e : list) {
                System.out.println(" → Edge (" + e.get(0) + " — " + e.get(1) + ")");
            }
        };

        System.out.println("\n============================");
        System.out.println(" Example 1: Simple Chain ");
        System.out.println("============================");

        ArrayList<ArrayList<Integer>> ex1 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(0, 1)),
            new ArrayList<>(Arrays.asList(1, 2)),
            new ArrayList<>(Arrays.asList(2, 3))
        ));
        System.out.println("Graph: 0 — 1 — 2 — 3");
        var bridges1 = criticalConnections(4, ex1);
        printEdges.accept(bridges1);
        System.out.println("Explanation: Every edge is a bridge. Removing any edge disconnects the chain.\n");

        System.out.println("\n============================");
        System.out.println(" Example 2: Triangle (No Bridges)");
        System.out.println("============================");

        ArrayList<ArrayList<Integer>> ex2 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(0, 1)),
            new ArrayList<>(Arrays.asList(1, 2)),
            new ArrayList<>(Arrays.asList(2, 0))
        ));
        System.out.println("Graph: Triangle 0—1—2—0");
        var bridges2 = criticalConnections(3, ex2);
        printEdges.accept(bridges2);
        System.out.println("Explanation: No bridge because every node has two paths.\n");

        System.out.println("\n============================");
        System.out.println(" Example 3: Star Graph ");
        System.out.println("============================");

        ArrayList<ArrayList<Integer>> ex3 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(0, 1)),
            new ArrayList<>(Arrays.asList(0, 2)),
            new ArrayList<>(Arrays.asList(0, 3)),
            new ArrayList<>(Arrays.asList(0, 4))
        ));
        System.out.println("Graph:   1");
        System.out.println("        /");
        System.out.println("      0—2—3—4 (each leaf only has 1 connection)");
        var bridges3 = criticalConnections(5, ex3);
        printEdges.accept(bridges3);
        System.out.println("Explanation: Every edge from center to leaf is a bridge.\n");

        System.out.println("\n============================");
        System.out.println(" Example 4: A square with a tail ");
        System.out.println("============================");

        ArrayList<ArrayList<Integer>> ex4 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(0, 1)),
            new ArrayList<>(Arrays.asList(1, 2)),
            new ArrayList<>(Arrays.asList(2, 3)),
            new ArrayList<>(Arrays.asList(3, 0)),
            new ArrayList<>(Arrays.asList(2, 4))
        ));
        System.out.println("Graph: 0—1—2—3 forms a cycle, but 2—4 is a tail");
        var bridges4 = criticalConnections(5, ex4);
        printEdges.accept(bridges4);
        System.out.println("Explanation: Only edge (2—4) is a bridge.\n");

        System.out.println("\n============================");
        System.out.println(" Example 5: Mixed graph with multiple cycles ");
        System.out.println("============================");

        ArrayList<ArrayList<Integer>> ex5 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(0, 1)),
            new ArrayList<>(Arrays.asList(1, 2)),
            new ArrayList<>(Arrays.asList(2, 0)),  // cycle A
            new ArrayList<>(Arrays.asList(1, 3)),
            new ArrayList<>(Arrays.asList(3, 4)),
            new ArrayList<>(Arrays.asList(4, 5)),
            new ArrayList<>(Arrays.asList(5, 3)),  // cycle B
            new ArrayList<>(Arrays.asList(2, 6))
        ));
        System.out.println("Graph contains two cycles (0,1,2) and (3,4,5), plus a branch at 2—6");
        var bridges5 = criticalConnections(7, ex5);
        printEdges.accept(bridges5);
        System.out.println("Explanation: Only edge (2—6) is a bridge.\n");

        System.out.println("\n============== END ==============\n");
    }

}

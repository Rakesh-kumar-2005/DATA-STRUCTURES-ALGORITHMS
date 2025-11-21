package Graph;

/*

    Description:
        Following program demonstrates the solution to the "Eventual Safe States" problem
        using Depth-First Search (DFS) and Cycle Detection Approach...

    Problem Statement:
        -> Given a directed graph represented as an adjacency list...
        -> Find all safe nodes in the graph...
        -> A safe node is a node from which all paths lead to terminal nodes...
        -> Terminal nodes are nodes with no outgoing edges...
        -> A node is eventual safe if it's either a terminal node or all its paths lead to terminal nodes...

    Approach:
        > DFS with Path-Based Cycle Detection:
            i. Use DFS to traverse the graph and identify cycles...
            ii. Use three boolean arrays: 'visited', 'pathVisited', and 'check'...
            iii. Nodes that are not part of any cycle are eventual safe nodes...
            iv. Terminal nodes are by definition safe nodes...
            v. Mark nodes as 'check' if they don't lead to a cycle...

    Algorithm Steps:
        -> Initialize three boolean arrays:
            1. 'visited' to track overall graph exploration...
            2. 'pathVisited' to track vertices in current DFS path...
            3. 'check' to mark nodes as eventual safe...
        -> Perform DFS from each unvisited node:
            1. Mark current node as visited and path-visited...
            2. Explore all adjacent nodes...
            3. If any path leads to a cycle, propagate this information upwards...
            4. If a node is already in the current path, a cycle is detected...
            5. If no cycles are detected, mark node as safe (check[node] = true)...
        -> Collect all safe nodes:
            1. Iterate through all nodes...
            2. Add nodes marked as 'check' to the result list...
            3. Return the list of safe nodes...

    Key Characteristics:
        -> Identifies all nodes that don't lead to cycles...
        -> Uses modified cycle detection algorithm...
        -> Handles terminal nodes automatically...
        -> Works with both connected and disconnected graphs...

        > Safety Determination Mechanism:
            -> A node is safe if it's not part of a cycle...
            -> A node is safe if all its paths lead to terminal nodes...
            -> Upon completing DFS exploration without finding cycles, nodes are marked safe...
            -> Uses pathVisited array to track potential cycles...

    Time and Space Complexity:
        -> Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges...
        -> Space Complexity: O(V) for the three boolean arrays and recursion stack...

*/

import java.util.ArrayList;

public class Eventual_Safe_States {

    private static boolean dfsCheck(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] pathVisited, boolean[] check) {
        
        visited[start] = true;
        pathVisited[start] = true;

        for (int adjacentNodes : adj.get(start)) {
            if (! visited[adjacentNodes]) {
                if (dfsCheck(adjacentNodes, adj, visited, pathVisited, check)) {
                    return true;
                }
            }

            if (pathVisited[adjacentNodes]) {
                return true;
            }
        }

        check[start] = true;
        pathVisited[start] = false;
        
        return false;
    
    }

    private static ArrayList<Integer> eventualSafeNodes(int V, ArrayList<ArrayList<Integer>> adj) {

        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];
        boolean[] check = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (! visited[i]) {
                dfsCheck(i, adj, visited, pathVisited, check);
            }
        }

        ArrayList<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (check[i]) {
                safeNodes.add(i);
            }
        }
        return safeNodes;
    }

    public static void main(String[] args) {

        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(2).add(3);
        adj.get(3).add(1);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(5).add(0);
        adj.get(5).add(2);

        ArrayList<Integer> ans = eventualSafeNodes(V, adj);
        System.out.println(ans);
    }

}


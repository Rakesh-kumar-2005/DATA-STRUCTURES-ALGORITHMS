package Graph;

/*

    Description:
        Following program demonstrates the solution to the "Cycle Detection in Directed Graph"
        problem using Depth-First Search (DFS) Approach...

    Problem Statement:
        -> Given a directed graph represented as an adjacency list...
        -> Determine whether the graph contains a cycle or not...
        -> A cycle in a directed graph exists when there is a path from a vertex back to itself...

    Approach:
        > Depth-First Search (DFS) with Path Tracking:
            i. Use two boolean arrays: 'visited' and 'pathVisited'...
            ii. Perform DFS traversal from each unvisited vertex...
            iii. Mark vertices as visited during traversal...
            iv. Use 'pathVisited' to detect cycles in the current DFS path...
            v. If an already path-visited vertex is encountered, a cycle exists...

    Algorithm Steps:
        -> Initialize two boolean arrays:
            1. 'visited' to track overall graph exploration...
            2. 'pathVisited' to track vertices in current DFS path...
        -> Iterate through all vertices in the graph...
        -> For each unvisited vertex, perform DFS:
            1. Mark current vertex as visited and path-visited...
            2. Explore all adjacent vertices...
            3. For unvisited adjacent vertices, recursively perform DFS...
            4. If a vertex in current path is revisited, return true (cycle detected)...
            5. Unmark the current vertex from path-visited after exploration...
        -> If no cycle is found after exploring all vertices, return false...

    Key Characteristics:
        -> Handles directed graphs with multiple components...
        -> Detects back edges that indicate cycle presence...
        -> Uses recursive DFS for comprehensive graph traversal...
        -> Efficiently tracks path-specific vertex visits...

        > Path Tracking Mechanism:
            -> 'visited' array prevents re-exploring entire graph components...
            -> 'pathVisited' array specifically tracks current DFS exploration path...
            -> Backtracking by unmarking 'pathVisited' ensures accurate cycle detection...

    Time and Space Complexity:
        -> Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges...
        -> Space Complexity: O(V) for visited and path-visited arrays, and recursive call stack...

*/

import java.util.ArrayList;

public class Cycle_Detection_In_Directed_Graph {

    private static boolean dfsCheck(int curr, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] pathVisited) {

        visited[curr] = true;
        pathVisited[curr] = true;

        for (int node : adj.get(curr)) {
            if (! visited[node]) {
                if (dfsCheck(node, adj, visited, pathVisited)) {
                    return true;
                }
            }

            if (pathVisited[node]) {
                return true;
            }
        }

        pathVisited[curr] = false;
        return false;
    }

    public static boolean isCyclic(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (! visited[i]) {
                if (dfsCheck(i, adj, visited, pathVisited)) {
                    return true;
                }
            }
        }
        return false;
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

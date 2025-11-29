package Graph;

import java.util.ArrayList;
import java.util.Stack;

public class Kosarajus_Algorithm_Strongly_Connected_Component {

    private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> st) {

        visited[node] = true;

        for (int adjNode : adj.get(node)) {
            if (! visited[adjNode]) {
                dfs(adjNode, adj, visited, st);
            }
        }

        st.push(node);
    }

    private static void dfsT(int node, ArrayList<ArrayList<Integer>> adjT, boolean[] visited) {

        visited[node] = true;

        for (int adjNode : adjT.get(node)) {
            if (! visited[adjNode]) {
                dfsT(adjNode, adjT, visited);
            }
        }

    }

    private static int stronglyConnectedComponent(int V, ArrayList<ArrayList<Integer>> adj) {

        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) {
                if (! visited[it]) {
                    dfs(it, adj, visited, st);
                }
            }
        }

        ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjT.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            visited[i] = false;
            for (int it : adj.get(i)) {
                adjT.get(it).add(i);
            }
        }

        int componentCount = 0;

        while (! st.isEmpty()) {
            int node = st.pop();

            if (! visited[node]) {
                componentCount++;
                dfsT(node, adjT, visited);
            }
        }

        return componentCount;

    }

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║       KOSARAJU'S ALGORITHM - STRONGLY CONNECTED COMPONENTS     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");

        // Test Case 1: Simple SCC
        System.out.println("═══ Test Case 1: Single Strongly Connected Component ═══");
        int V1 = 3;
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        for (int i = 0; i < V1; i++) {
            adj1.add(new ArrayList<>());
        }
        adj1.get(0).add(1);
        adj1.get(1).add(2);
        adj1.get(2).add(0);

        System.out.println("Graph Structure:");
        System.out.println("    0 → 1");
        System.out.println("    ↑   ↓");
        System.out.println("    └─2←┘");
        System.out.println("Edges: 0→1, 1→2, 2→0");
        System.out.println("All nodes form a cycle - they can reach each other");
        System.out.println("Number of SCCs: " + stronglyConnectedComponent(V1, adj1));
        System.out.println("Expected: 1 (all nodes in one SCC)\n");

        // Test Case 2: Two SCCs
        System.out.println("═══ Test Case 2: Two Strongly Connected Components ═══");
        int V2 = 5;
        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < V2; i++) {
            adj2.add(new ArrayList<>());
        }
        adj2.get(0).add(1);
        adj2.get(1).add(2);
        adj2.get(2).add(0);
        adj2.get(1).add(3);
        adj2.get(3).add(4);

        System.out.println("Graph Structure:");
        System.out.println("    ┌─0─┐");
        System.out.println("    ↓   ↑");
        System.out.println("    1 → 2     (SCC 1: cycle 0→1→2→0)");
        System.out.println("    ↓");
        System.out.println("    3 → 4     (SCC 2: nodes 3, 4 separately)");

        System.out.println("Edges: 0→1, 1→2, 2→0, 1→3, 3→4");
        System.out.println("Number of SCCs: " + stronglyConnectedComponent(V2, adj2));
        System.out.println("Expected: 3 (one cycle + two individual nodes)\n");

        // Test Case 3: Classic example with multiple SCCs
        System.out.println("═══ Test Case 3: Multiple SCCs - Classic Example ═══");
        int V3 = 8;
        ArrayList<ArrayList<Integer>> adj3 = new ArrayList<>();
        for (int i = 0; i < V3; i++) {
            adj3.add(new ArrayList<>());
        }
        adj3.get(0).add(1);
        adj3.get(1).add(2);
        adj3.get(2).add(3);
        adj3.get(2).add(4);
        adj3.get(3).add(0);
        adj3.get(4).add(5);
        adj3.get(5).add(6);
        adj3.get(6).add(4);
        adj3.get(6).add(7);

        System.out.println("Graph Structure:");
        System.out.println("    ┌→0→1→2→3→┐      ");
        System.out.println("    └────────←┘      (SCC 1: 0,1,2,3 form a cycle)");
        System.out.println("         ↓           ");
        System.out.println("    ┌→4←6→7          ");
        System.out.println("    ↓   ↑            (SCC 2: 4,5,6 form a cycle)");
        System.out.println("    └→5─┘            (SCC 3: 7 is alone)");

        System.out.println("Edges: 0→1, 1→2, 2→3, 3→0, 2→4, 4→5, 5→6, 6→4, 6→7");
        System.out.println("Number of SCCs: " + stronglyConnectedComponent(V3, adj3));
        System.out.println("Expected: 3 SCCs\n");

        // Test Case 4: All nodes separate (no cycles)
        System.out.println("═══ Test Case 4: Linear Chain - No Cycles ═══");
        int V4 = 4;
        ArrayList<ArrayList<Integer>> adj4 = new ArrayList<>();
        for (int i = 0; i < V4; i++) {
            adj4.add(new ArrayList<>());
        }
        adj4.get(0).add(1);
        adj4.get(1).add(2);
        adj4.get(2).add(3);

        System.out.println("Graph Structure:");
        System.out.println("    0 → 1 → 2 → 3");

        System.out.println("Linear path, no way to go back");
        System.out.println("Each node is its own SCC");
        System.out.println("Number of SCCs: " + stronglyConnectedComponent(V4, adj4));
        System.out.println("Expected: 4 (each node is separate SCC)\n");

        // Test Case 5: Complete cycle (all nodes strongly connected)
        System.out.println("═══ Test Case 5: Complete Strongly Connected Graph ═══");
        int V5 = 4;
        ArrayList<ArrayList<Integer>> adj5 = new ArrayList<>();
        for (int i = 0; i < V5; i++) {
            adj5.add(new ArrayList<>());
        }
        adj5.get(0).add(1);
        adj5.get(1).add(2);
        adj5.get(2).add(3);
        adj5.get(3).add(0);
        adj5.get(0).add(2);
        adj5.get(1).add(3);

        System.out.println("Graph Structure:");
        System.out.println("    ┌→0→1→┐");
        System.out.println("    ↑  ↓ ↓ ↓");
        System.out.println("    └→2←3←┘");

        System.out.println("Multiple paths between all nodes");
        System.out.println("Everyone can reach everyone");
        System.out.println("Number of SCCs: " + stronglyConnectedComponent(V5, adj5));
        System.out.println("Expected: 1 (all nodes form one SCC)\n");

        // Test Case 6: Disconnected components
        System.out.println("═══ Test Case 6: Disconnected Graph ═══");
        int V6 = 6;
        ArrayList<ArrayList<Integer>> adj6 = new ArrayList<>();
        for (int i = 0; i < V6; i++) {
            adj6.add(new ArrayList<>());
        }
        adj6.get(0).add(1);
        adj6.get(1).add(0);
        adj6.get(2).add(3);
        adj6.get(3).add(2);
        adj6.get(4).add(5);
        adj6.get(5).add(4);

        System.out.println("Graph Structure:");
        System.out.println("    0 ⟷ 1     (SCC 1)");
        System.out.println("    ");
        System.out.println("    2 ⟷ 3     (SCC 2)");
        System.out.println("    ");
        System.out.println("    4 ⟷ 5     (SCC 3)");

        System.out.println("Three separate bidirectional pairs");
        System.out.println("Number of SCCs: " + stronglyConnectedComponent(V6, adj6));
        System.out.println("Expected: 3 (three separate SCCs)\n");

        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║  Algorithm: Kosaraju's finds SCCs in O(V+E) time complexity   ║");
        System.out.println("║  Key Idea: Uses two DFS passes and graph transposition        ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");

    }

}

package Graph;

import java.util.ArrayList;

public class Articulation_Points {


    private static int timer = 1;

    private static void dfs(int node, int parent, ArrayList<ArrayList<Integer>> graph, boolean[] visited, int[] tInsert, int[] lowestTInsert, int[] artPoints) {

        visited[node] = true;
        lowestTInsert[node] = tInsert[node] = timer++;

        int child = 0;
        for (Integer it : graph.get(node)) {

            if (it == parent) {
                continue;
            }

            if (! visited[it]) {
                dfs(it, node, graph, visited, tInsert, lowestTInsert, artPoints);

                lowestTInsert[node] = Math.min(lowestTInsert[node], lowestTInsert[it]);

                if (lowestTInsert[it] >= tInsert[node] && parent != - 1) {
                    artPoints[node] = 1;
                }

                child++;
            } else {
                lowestTInsert[node] = Math.min(lowestTInsert[node], tInsert[it]);
            }
        }

        if (child > 1 && parent == - 1) {
            artPoints[node] = 1;
        }
    }

    private static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        // code here

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[V];
        int[] artPoints = new int[V];
        int[] tInsert = new int[V];
        int[] lowestTInsert = new int[V];


        for (int i = 0; i < V; i++) {
            if (! visited[i]) {
                dfs(i, - 1, graph, visited, tInsert, lowestTInsert, artPoints);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            if (artPoints[i] == 1) {
                ans.add(i);
            }
        }

        if (ans.isEmpty()) {
            ans.add(- 1);
        }

        return ans;

    }

    public static void main(String[] args) {

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         ARTICULATION POINTS FINDER                         ║");
        System.out.println("║  (Critical nodes whose removal disconnects the graph)      ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        timer = 1;

        System.out.println("=== Test Case 1: Simple Bridge Example ===");
        int[][] edges1 = {
            {0, 1}, {1, 2}, {2, 3}, {3, 4}
        };
        int V1 = 5;

        System.out.println("Vertices: 5");
        System.out.println("Edges: 0-1, 1-2, 2-3, 3-4");
        System.out.println("Visual: 0---1---2---3---4");
        System.out.println("Explanation: Nodes 1, 2, 3 are articulation points");
        System.out.println("  - Removing 1 disconnects 0 from rest");
        System.out.println("  - Removing 2 disconnects 0,1 from 3,4");
        System.out.println("  - Removing 3 disconnects 4 from rest");
        ArrayList<Integer> result1 = articulationPoints(V1, edges1);
        System.out.println("Articulation Points: " + result1);
        System.out.println("Expected: [1, 2, 3]\n");

        timer = 1; // Reset timer

        System.out.println("=== Test Case 2: Triangle (No Articulation Points) ===");
        int[][] edges2 = {
            {0, 1}, {1, 2}, {2, 0}
        };
        int V2 = 3;

        System.out.println("Vertices: 3");
        System.out.println("Edges: 0-1, 1-2, 2-0");
        System.out.println("Visual:");
        System.out.println("    0");
        System.out.println("   / \\");
        System.out.println("  1---2");
        System.out.println("Explanation: Triangle - removing any node still leaves graph connected");
        ArrayList<Integer> result2 = articulationPoints(V2, edges2);
        System.out.println("Articulation Points: " + result2);
        System.out.println("Expected: [-1] (no articulation points)\n");

        timer = 1;

        System.out.println("=== Test Case 3: Star Graph ===");
        int[][] edges3 = {
            {0, 1}, {0, 2}, {0, 3}, {0, 4}
        };
        int V3 = 5;

        System.out.println("Vertices: 5");
        System.out.println("Edges: 0-1, 0-2, 0-3, 0-4");
        System.out.println("Visual:");
        System.out.println("  1   2");
        System.out.println("   \\ /");
        System.out.println("    0");
        System.out.println("   / \\");
        System.out.println("  3   4");
        System.out.println("Explanation: Node 0 is center - removing it disconnects all");
        ArrayList<Integer> result3 = articulationPoints(V3, edges3);
        System.out.println("Articulation Points: " + result3);
        System.out.println("Expected: [0]\n");

        timer = 1;

        System.out.println("=== Test Case 4: Complex Graph with Multiple AP ===");
        int[][] edges4 = {
            {0, 1}, {0, 2}, {1, 2}, // Triangle
            {1, 3}, {3, 4}          // Bridge through 3
        };
        int V4 = 5;

        System.out.println("Vertices: 5");
        System.out.println("Edges: 0-1, 0-2, 1-2, 1-3, 3-4");
        System.out.println("Visual:");
        System.out.println("  0---2");
        System.out.println("  |  /");
        System.out.println("  1---3---4");
        System.out.println("Explanation:");
        System.out.println("  - Node 1 connects triangle to rest");
        System.out.println("  - Node 3 is bridge to node 4");
        ArrayList<Integer> result4 = articulationPoints(V4, edges4);
        System.out.println("Articulation Points: " + result4);
        System.out.println("Expected: [1, 3]\n");

        timer = 1;

        System.out.println("=== Test Case 5: Two Connected Components via Bridge ===");
        int[][] edges5 = {
            {0, 1}, {1, 2}, {2, 0}, // First triangle
            {2, 3},                 // Bridge
            {3, 4}, {4, 5}, {5, 3}  // Second triangle
        };
        int V5 = 6;

        System.out.println("Vertices: 6");
        System.out.println("Edges: 0-1, 1-2, 2-0, 2-3, 3-4, 4-5, 5-3");
        System.out.println("Visual:");
        System.out.println("    0           3");
        System.out.println("   / \\         / \\");
        System.out.println("  1---2-------3---5");
        System.out.println("              4");
        System.out.println("Explanation: Nodes 2 and 3 bridge two triangles");
        ArrayList<Integer> result5 = articulationPoints(V5, edges5);
        System.out.println("Articulation Points: " + result5);
        System.out.println("Expected: [2, 3]\n");

        timer = 1;

        System.out.println("=== Test Case 6: Single Edge (2 Nodes) ===");
        int[][] edges6 = {
            {0, 1}
        };
        int V6 = 2;

        System.out.println("Vertices: 2");
        System.out.println("Edges: 0-1");
        System.out.println("Visual: 0---1");
        System.out.println("Explanation: No articulation point (removing either disconnects already)");
        ArrayList<Integer> result6 = articulationPoints(V6, edges6);
        System.out.println("Articulation Points: " + result6);
        System.out.println("Expected: [-1]\n");

        timer = 1;

        System.out.println("=== Test Case 7: Disconnected Graph ===");
        int[][] edges7 = {
            {0, 1}, {1, 2}, // Component 1
            {3, 4}          // Component 2
        };
        int V7 = 5;

        System.out.println("Vertices: 5");
        System.out.println("Edges: 0-1, 1-2, 3-4");
        System.out.println("Visual:");
        System.out.println("  0---1---2    3---4");
        System.out.println("Explanation: Node 1 is articulation point in first component");
        ArrayList<Integer> result7 = articulationPoints(V7, edges7);
        System.out.println("Articulation Points: " + result7);
        System.out.println("Expected: [1]\n");

        timer = 1;

        System.out.println("=== Test Case 8: Complete Graph K4 ===");
        int[][] edges8 = {
            {0, 1}, {0, 2}, {0, 3},
            {1, 2}, {1, 3}, {2, 3}
        };
        int V8 = 4;

        System.out.println("Vertices: 4");
        System.out.println("Edges: All pairs connected");
        System.out.println("Visual: Complete graph (every node connected to every other)");
        System.out.println("Explanation: No articulation points - highly connected");
        ArrayList<Integer> result8 = articulationPoints(V8, edges8);
        System.out.println("Articulation Points: " + result8);
        System.out.println("Expected: [-1]\n");

        timer = 1;

        System.out.println("=== Test Case 9: Root with Multiple Children ===");
        int[][] edges9 = {
            {0, 1}, {0, 2}, // Root 0 with 2 separate branches
            {1, 3}, {2, 4}
        };
        int V9 = 5;

        System.out.println("Vertices: 5");
        System.out.println("Edges: 0-1, 0-2, 1-3, 2-4");
        System.out.println("Visual:");
        System.out.println("    0");
        System.out.println("   / \\");
        System.out.println("  1   2");
        System.out.println("  |   |");
        System.out.println("  3   4");
        System.out.println("Explanation: Root 0 has 2+ children, nodes 1,2 are bridges");
        ArrayList<Integer> result9 = articulationPoints(V9, edges9);
        System.out.println("Articulation Points: " + result9);
        System.out.println("Expected: [0, 1, 2]\n");

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  All test cases completed!                                 ║");
        System.out.println("║  Articulation points are critical for network reliability  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

    }

}
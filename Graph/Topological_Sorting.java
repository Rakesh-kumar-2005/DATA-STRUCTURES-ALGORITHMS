package Graph;

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
package Graph;

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

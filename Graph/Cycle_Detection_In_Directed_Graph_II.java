package Graph;

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
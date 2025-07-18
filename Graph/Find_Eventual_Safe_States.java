package Graph;

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

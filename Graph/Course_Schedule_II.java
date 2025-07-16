package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Course_Schedule_II {

    private static int[] findOrder(int numCourses, int[][] prerequisites) {

        // Formation of Graph...
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Connecting the edges...
        for (int i = 0; i < prerequisites.length; i++) {
            adjacencyList.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        int[] inDegrees = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (int j : adjacencyList.get(i)) {
                inDegrees[j]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                q.add(i);
            }
        }

        int[] orderedNodes = new int[numCourses];
        int index = 0;

        while (! q.isEmpty()) {

            int currNode = q.poll();
            orderedNodes[index++] = currNode;

            for (int adjacent : adjacencyList.get(currNode)) {
                inDegrees[adjacent]--;

                if (inDegrees[adjacent] == 0) {
                    q.add(adjacent);
                }
            }
        }

        if (index == numCourses) {
            return orderedNodes;
        }

        return new int[]{};
    }

    public static void main(String[] args) {

        int[][] prequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}, {4, 3}};
        System.out.println("The order of courses is to complete the task : ");

        int[] orderedNodes = findOrder(5, prequisites);

        for (int i = 0; i < orderedNodes.length - 1; i++) {
            System.out.print(orderedNodes[i] + " -> ");
        }
        System.out.println(orderedNodes[orderedNodes.length - 1]);

    }

}
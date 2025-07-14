package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Course_Schedule {

    private static boolean canFinish(int numCourses, int[][] prerequisites) {

        // Form the graph...
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Connect the edges...
        for (int i = 0; i < prerequisites.length; i++) {
            adjacencyList.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            for (int j : adjacencyList.get(i)) {
                inDegree[j]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        int count = 0;

        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        while (! q.isEmpty()) {
            int currentNode = q.poll();
            count++;

            for (int adjacentNode : adjacencyList.get(currentNode)) {
                inDegree[adjacentNode]--;
                if (inDegree[adjacentNode] == 0) {
                    q.add(adjacentNode);
                }
            }
        }

        return count == numCourses;
    }

    public static void main(String[] args) {

        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};

        boolean ans = canFinish(numCourses, prerequisites);

        if (ans) {
            System.out.println("Yes, it is possible to finish all the courses...");
        } else {
            System.out.println("No, it is not possible to finish all the courses...");
        }

    }

}
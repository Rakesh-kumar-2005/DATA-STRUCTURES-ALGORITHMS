package Graph;

/*

Description:
    -> This program checks whether it is possible to finish all courses given a list of prerequisites...
    -> It models the problem as a cycle detection in a **directed graph** using **Kahn's Algorithm** (BFS-based topological sorting)...

Problem Statement:
    -> You are given `numCourses` representing the total number of courses labeled from 0 to numCourses - 1...
    -> And an array `prerequisites` where each pair [a, b] indicates that to take course `a`, you must first take course `b`...
    -> Determine if it is possible to finish all courses without running into cyclic dependencies...

Approach:
    > Graph Construction:
        -> Represent the course prerequisites as a directed graph using an adjacency list...
        -> Add a directed edge from `a` to `b` (a → b means `a` depends on `b`)...

    > In-Degree Calculation:
        -> Compute the in-degree (number of prerequisites) for each course...

    > Kahn’s Algorithm (Topological Sort via BFS):
        -> Add all courses with in-degree 0 to a queue (courses that can be taken immediately)...
        -> While the queue is not empty:
            a. Remove a course and increment the count of courses completed...
            b. For each dependent course, reduce its in-degree...
            c. If a dependent course's in-degree becomes 0, enqueue it...

    > Cycle Detection:
        -> If all courses can be processed (i.e., `count == numCourses`), return true...
        -> Otherwise, return false — indicating a cycle exists, making it impossible to finish all courses...

Algorithm Steps:
    -> Step 1: Create adjacency list of size `numCourses`...
    -> Step 2: Populate the list using the prerequisites array...
    -> Step 3: Compute in-degrees of all nodes...
    -> Step 4: Enqueue all courses with in-degree 0...
    -> Step 5: Apply BFS and count how many courses are processed...
    -> Step 6: Return true if count == numCourses, otherwise false...

Key Characteristics:
    -> Solves a real-world scheduling problem using graph theory...
    -> Efficiently detects cycles in a directed graph using topological sort...
    -> Supports large graphs with thousands of nodes and dependencies...

Time and Space Complexity:
    -> Time Complexity: O(V + E), where V = numCourses and E = number of prerequisite pairs...
    -> Space Complexity: O(V + E), for adjacency list, in-degree array, and queue...

Demonstration:
    -> Input:
        numCourses = 2
        prerequisites = { {1, 0}, {0, 1} }
    -> There is a cycle: 0 → 1 → 0
    -> Output: No, it is not possible to finish all the courses...

*/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Course_Schedule_I {

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

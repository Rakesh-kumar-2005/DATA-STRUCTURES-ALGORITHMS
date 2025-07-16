package Graph;

/*

Description:
    -> This program determines a valid order in which a student can complete all courses given a list of course prerequisites...
    -> It uses **Kahn’s Algorithm** (BFS-based topological sort) to build the course order from dependency constraints...

Problem Statement:
    -> You are given `numCourses` labeled from 0 to numCourses - 1...
    -> You are also given a list of prerequisite pairs `prerequisites` where [a, b] means to take course `a`, you must first complete course `b`...
    -> Return any valid ordering of the courses such that all prerequisites are satisfied...
    -> If there is no valid order (i.e., a cycle exists), return an empty array...

Approach:
    > Graph Formation:
        -> Represent the prerequisites as a **directed graph** using an adjacency list...
        -> Each directed edge `b → a` indicates `b` must be completed before `a`...

    > In-Degree Counting:
        -> For each course, compute how many prerequisites (in-degrees) it has...

    > Kahn’s Algorithm (BFS):
        -> Initialize a queue with all courses having in-degree 0 (can be taken immediately)...
        -> Process each course:
            a. Add to result array...
            b. Decrease in-degrees of its adjacent courses...
            c. If a course's in-degree becomes 0, enqueue it...

    > Cycle Check:
        -> If the number of processed courses equals `numCourses`, return the ordering...
        -> Otherwise, return an empty array indicating a cycle (impossible to complete all courses)...

Algorithm Steps:
    -> Step 1: Create an adjacency list of size `numCourses`...
    -> Step 2: Build the graph using the prerequisites array...
    -> Step 3: Compute in-degrees for each course...
    -> Step 4: Add courses with in-degree 0 to the queue...
    -> Step 5: While the queue is not empty:
        a. Poll a course, add to result...
        b. For each neighbor, decrement in-degree...
        c. If in-degree becomes 0, enqueue the course...
    -> Step 6: If all courses are included in result → return the array...
              Else → return empty array...

Key Characteristics:
    -> Efficient for detecting and resolving course dependencies...
    -> Suitable for scheduling tasks, pipelines, and build systems...
    -> Handles multiple valid course orderings due to DAG nature...

Time and Space Complexity:
    -> Time Complexity: O(V + E), where V = number of courses and E = number of prerequisites...
    -> Space Complexity: O(V + E), for the graph, in-degree array, queue, and result array...

Demonstration:
    -> Input:
        numCourses = 5
        prerequisites = { {1, 0}, {2, 0}, {3, 1}, {3, 2}, {4, 3} }
    -> Valid ordering: 0 → 1 → 2 → 3 → 4 (or 0 → 2 → 1 → 3 → 4)
    -> Output: The order of courses is to complete the task : 0 -> 1 -> 2 -> 3 -> 4

*/

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

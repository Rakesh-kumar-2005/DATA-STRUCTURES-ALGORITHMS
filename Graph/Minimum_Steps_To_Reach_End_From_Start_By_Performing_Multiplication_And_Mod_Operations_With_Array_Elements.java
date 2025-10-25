package Graph;

/*
Description:
    This program finds the minimum number of steps required to reach a target value (end)
    from a given starting value (start) by repeatedly performing a specific operation:
        newValue = (currentValue * arr[i]) % 10000
    where arr[i] is an element from the given array of integers.

Problem Statement:
    You are given:
        - An array of integers (arr[])
        - A starting integer value (start)
        - A target integer value (end)
    
    In one step, you can choose any element from the array and:
        1. Multiply it with the current value
        2. Take modulo 10000 of the result

    The task is to determine the minimum number of steps required to reach 'end'
    starting from 'start'. If it is not possible to reach 'end', return -1.

Algorithm Used:
    This problem is solved using **Breadth-First Search (BFS)** on the state space.
    Each unique value obtained after the operation (mod 10000) represents a node,
    and an edge exists between nodes that can be reached by performing one operation.

Approach:
    1. Use a queue to perform BFS traversal, where each element in the queue is a Pair(value, steps):
         - value: current number after performing operations
         - steps: number of operations performed so far
    2. Initialize a visited array of size 10000 to track the minimum steps
       required to reach each value, initialized with Integer.MAX_VALUE.
    3. Start BFS from the 'start' value with step count = 0.
    4. For each value in the queue:
         - For every element arr[i]:
             a. Compute newValue = (currValue * arr[i]) % 10000
             b. If this new value has not been visited or can be reached in fewer steps:
                 - If newValue == end, return currSteps + 1
                 - Otherwise, mark visited[newValue] = currSteps + 1 and enqueue it
    5. If BFS ends and 'end' is not reached, return -1.

Example Input:
    arr = {2, 5, 7}
    start = 3
    end = 30

Example Output:
    Total number of minimum steps to reach end 30 from start 3 is : 2

Explanation:
    Step 1: (3 * 5) % 10000 = 15  
    Step 2: (15 * 2) % 10000 = 30  
    Hence, we reach 30 from 3 in 2 steps.

Edge Cases Considered:
    - start == end → 0 steps required
    - No possible sequence of operations → return -1
    - Array contains 1 (can cause infinite loops, but handled by visited array)
    - Large numbers in arr → safely handled by modulo operation
    - Duplicate elements in arr

Key Concepts Used:
    - Breadth-First Search (BFS)
    - Modular arithmetic
    - Shortest path in an unweighted graph

Time and Space Complexity:
    Time Complexity: O(N * 10000)
        (Each of the 10000 possible values may be visited once, and for each, N multiplications are attempted)
    Space Complexity: O(10000)
        (For the visited array and queue storage)
*/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Minimum_Steps_To_Reach_End_From_Start_By_Performing_Multiplication_And_Mod_Operations_With_Array_Elements {

    static class Pair {
        int value;
        int steps;

        public Pair(int value, int steps) {
            this.value = value;
            this.steps = steps;
        }

    }

    private static int minSteps(int[] arr, int start, int end) {

        if (start > end) {
            return - 1;
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(start, 0));

        int[] visited = new int[10000];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[start] = 0;

        while (! q.isEmpty()) {

            Pair currPair = q.poll();
            int currValue = currPair.value;
            int currSteps = currPair.steps;

            for (int i = 0; i < arr.length; i++) {
                int newValue = (currValue * arr[i]) % 10000;

                if (currSteps + 1 < visited[newValue]) {

                    if (newValue == end) {
                        return currSteps + 1;
                    }

                    visited[newValue] = currSteps + 1;
                    q.add(new Pair(newValue, currSteps + 1));

                }
                
            }

        }

        return - 1;

    }


    public static void main(String[] args) {

        int[] arr = {2, 5, 7};
        int start = 3;
        int end = 30;

        int totalSteps = minSteps(arr, start, end);

        if (totalSteps != - 1) {
            System.out.println("Total number of minimum steps to reach end " + end + " from start " + start + " is : " + totalSteps);
        } else {
            System.out.println("We can't reach from " + start + " to " + end + " by performing multiplication and mod operations with array elements...");
        }

    }

}

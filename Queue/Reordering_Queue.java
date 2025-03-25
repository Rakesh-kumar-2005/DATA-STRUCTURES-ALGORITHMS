package Queue;

/*  

Description:
  Following program demonstrates the implementation of reordering a queue
  by interleaving its first and second half using a stack-based approach in Java.

Problem Statement:
  -> Given a queue with an even number of elements...
  -> Reorder the queue by interleaving the first and second halves...
  -> Modify the queue in-place without creating a new data structure...

Approach:
  > Stack-Assisted Queue Manipulation Strategy:
     i. Use a stack as an auxiliary data structure...
     ii. Divide the queue into two halves...
     iii. Manipulate elements to create an interleaved arrangement...

> Algorithm Steps:
  -> First Half Separation:
     * Remove first half of queue elements and push to stack...
     * Preserve the order of elements during transfer...

  -> First Interleaving Phase:
     * Push the remaining first half back to queue...
     * Alternate between stack and queue elements...

  -> Second Interleaving Phase:
     * Remove the first half of elements again...
     * Interleave elements from stack and queue...

  -> Final Reordering:
     * Transfer remaining elements to stack...
     * Restore original queue order...

> Key Insight:
  -> Efficiently reorders queue using minimal additional data structures...
  -> Handles queue manipulation without losing element order...
  -> Provides a flexible approach to queue reorganization...

> Example:
  -> For input queue: 1 -> 2 -> 3 -> 4 -> 5 -> 6
  -> After reordering: 1 -> 4 -> 2 -> 5 -> 3 -> 6
  -> First and second halves are systematically interleaved...

> Edge Cases:
  -> Requires queue with even number of elements...
  -> Manages queues of different sizes...
  -> Preserves relative order of elements...

> Time and Space Complexity:
  -> Time Complexity: O(n) where n is the number of queue elements...
  -> Space Complexity: O(n) due to stack usage...

> Performance Characteristics:
  -> Multiple passes through the queue...
  -> Requires additional stack space...
  -> Linear time complexity...
  -> Flexible queue manipulation technique...

*/

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Reordering_Queue {

    private static void reorder(Queue<Integer> q){
        Stack<Integer> st = new Stack<>();
        int size = q.size();

        for (int i = 0; i < size/2; i++){
            st.push(q.remove());
        }

        while (!st.isEmpty()){
            q.add(st.pop());
        }

        for (int i = 0; i < size/2; i++){
            st.push(q.remove());
        }

        while (!st.isEmpty()){
            q.add(st.pop());
            q.add(q.remove());
        }

        while (!q.isEmpty()) {
            st.push(q.remove());
        }

        while (!st.isEmpty()){
            q.add(st.pop());
        }
    }

    private static void printQueue(Queue<Integer> q){
        int size = q.size();

        while (size != 0){
            System.out.print(q.peek() + " ");
            q.add(q.remove());
            size--;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.add(6);
        System.out.println("Original Queue is : ");
        printQueue(q);
        reorder(q);
        System.out.println("After interleaved Queue is : ");
        printQueue(q);
    }
}

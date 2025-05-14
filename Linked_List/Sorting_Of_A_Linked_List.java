package Linked_List;

/*

Description:
    Following program demonstrates the solution to "Sorting of a Linked List" problem using min-heap (priority queue)...

Problem Statement:
    -> Given a singly linked list with potentially unsorted elements...
    -> The task is to sort the linked list in ascending order of node values...
    -> The original linked list structure cannot be modified, a new sorted list must be returned...


Approach:
    > Priority Queue Based Sorting:
        i. Extract all values from the linked list into a min-heap (priority queue)...
        ii. Construct a new linked list by extracting values from the priority queue in order...
        iii. This ensures elements come out in ascending order naturally due to min-heap properties...

    > Algorithm Steps:
        -> First pass - Extract values into priority queue:
            1. Traverse the original linked list from head to tail...
            2. Add each node's value into the priority queue...
            3. The priority queue automatically maintains the min-heap property...

        -> Second pass - Create sorted linked list:
            1. Create a dummy head node to simplify the new list construction...
            2. Poll values from the priority queue one by one (automatically in ascending order)...
            3. For each value, create a new node and add it to the result linked list...
            4. Return the next of dummy node as the head of sorted linked list...

    > Key Characteristics:
        -> Creates a new linked list rather than modifying the original structure...
        -> Handles arbitrary length linked lists with any integer values...
        -> Works efficiently for both partially sorted and completely unsorted lists...
        -> Uses Java's built-in PriorityQueue which implements a min-heap...
        -> Time complexity depends on heap operations rather than comparison-based sorting...

    > Implementation Details:
        -> Uses a static nested Node class to represent linked list nodes...
        -> Creates a dummy head node with value -1 to simplify list construction...
        -> Handles edge cases when the list is empty or has only one element...
        -> Returns the sorted linked list head for further operations...
        -> Demonstrates usage with a test case in the main method...

    > Time and Space Complexity:
        -> Time Complexity: O(n log n) where n is the number of nodes in the linked list...
        -> Space Complexity: O(n) for storing all values in the priority queue...
        -> Additional O(n) space for the newly created sorted linked list...

*/

import java.util.PriorityQueue;

public class Sorting_Of_A_Linked_List {

    private static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static Node sort(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        Node temp = head;
        while (temp != null) {
            pq.add(temp.val);
            temp = temp.next;
        }

        Node ans = new Node(- 1);
        temp = ans;

        while (! pq.isEmpty()) {
            temp.next = new Node(pq.poll());
            temp = temp.next;
        }

        return ans.next;
    }

    public static void main(String[] args) {

        Node a = new Node(33);
        Node b = new Node(1);
        Node c = new Node(46);
        Node d = new Node(19);
        Node e = new Node(10);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        Node ans = sort(a);

        while (ans != null) {
            System.out.print(ans.val + " ");
            ans = ans.next;
        }

    }

}

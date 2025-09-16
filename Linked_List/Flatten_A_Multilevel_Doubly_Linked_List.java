package Linked_List;

/*

Description:
    -> This program flattens a **multilevel doubly linked list** into a single-level doubly linked list...
    -> In a multilevel doubly linked list, each node has:
         - A `next` pointer (points to the next node),
         - A `prev` pointer (points to the previous node),
         - A `child` pointer (points to a sublist which may contain its own children)...
    -> The goal is to flatten the list so that all nodes appear in a single sequence using depth-first traversal...

Problem Statement:
    -> Given the head of a multilevel doubly linked list, return the head of the flattened list...
    -> The flattening process should:
         1. Insert each child list between the node and its next pointer...
         2. Erase all child pointers after flattening...

Approach:
    > Recursive Depth-First Flattening:
        -> Traverse the list starting from the head...
        -> If a node has a child:
            - Recursively flatten the child list...
            - Attach the flattened child list between the current node and its next pointer...
            - Connect the end of the child list back to the next node (if exists)...
        -> Continue until the end of the list is reached...
        -> At each step, set `child = null` to remove unnecessary pointers...

Algorithm Steps:
    -> Initialization:
        1. Start from the head node...
        2. Traverse sequentially using a temporary pointer...

    -> Flattening Process:
        1. If `temp.child == null`, move to the next node...
        2. If `temp.child != null`:
             - Recursively flatten the child list...
             - Insert the flattened list after the current node...
             - Traverse to the end of the flattened child list...
             - Connect its tail to the original next node...
             - Remove the `child` pointer...

    -> Continuation:
        1. Move the pointer forward and repeat the process until the end is reached...

    -> Return:
        1. Return the head of the fully flattened doubly linked list...

Key Characteristics:
    -> Recursive DFS-based approach ensures correct order of nodes...
    -> Child lists are inserted immediately after their parent nodes...
    -> Modifies the list in-place without using extra data structures...
    -> Maintains both `prev` and `next` connections correctly...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the total number of nodes across all levels (each node is visited once)...
    -> Space Complexity: O(d), where d is the maximum depth of recursion (due to recursive calls)...

Demonstration:
    -> Input Multilevel List:
         1 -> 2
              |
              3
              |
              4
              |
              5
              |
              6

    -> Flattening Steps:
         - Start at node 1 → move to 2 (has child 3)...
         - Flatten child list [3 -> 4 -> 5 -> 6] and attach it after 2...
         - Final connections: 1 -> 2 -> 3 -> 4 -> 5 -> 6

    -> Output Flattened List:
         1 -> 2 -> 3 -> 4 -> 5 -> 6

*/

public class Flatten_A_Multilevel_Doubly_Linked_List {

    private static class Node {
        int val;
        Node next;

        Node prev;
        Node child;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
            this.child = null;
        }
    }

    private static Node flatten(Node head) {

        Node temp = head;

        while (temp != null) {
            Node t = temp.next;

            if (temp.child != null) {

                Node helper = flatten(temp.child);
                temp.next = helper;
                helper.prev = temp;

                while (helper.next != null) {
                    helper = helper.next;
                }

                helper.next = t;
                if (t != null) {
                    t.prev = helper;
                }
            }

            temp.child = null; //have to erase the child pointer
            temp = t;

        }

        return head;
    }

    private static void displayList(Node head) {
        Node temp = head;
        while (temp.next != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println(temp.val);
        System.out.println();
    }


    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.child = new Node(3);
        head.next.child.child = new Node(4);
        head.next.child.child.child = new Node(5);
        head.next.child.child.child.child = new Node(6);

        System.out.println("Original Linked List: ");
        displayList(head);

        Node ans = flatten(head);

        System.out.println("Flattened Linked List: ");
        displayList(ans);

    }

}

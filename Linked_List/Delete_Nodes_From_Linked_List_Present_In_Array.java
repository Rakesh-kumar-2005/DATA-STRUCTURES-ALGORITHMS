package Linked_List;

/*

Description:
    -> This program removes all nodes from a singly linked list whose values are present
       in a given array of integers...
    -> The linked list and the array represent integer values, and the task is to
       delete all nodes that match any element in the array...
    -> The modified linked list (after deletions) is then returned...

Problem Statement:
    Given:
        - A singly linked list containing integer values...
        - An integer array `numbers`...
    Remove all nodes from the linked list that have values present in `numbers`...
    The remaining nodes should preserve their original order...

Example:
    Input:
        Linked List: 1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → 9 → 10 → 11 → 12 → 13 → 14 → 15 → 16 → 17 → 18 → 19 → 20 → 21...
        numbers = [2, 5, 6, 9, 10, 11, 12, 14, 15, 17, 20]...
    Output:
        Modified Linked List: 1 → 3 → 4 → 7 → 8 → 13 → 16 → 18 → 19 → 21...

Approach:
    1. Store all values from the array `numbers` in a HashSet for quick O(1) lookups...
    2. Handle the case where the head of the linked list contains a value to be deleted...
       - Move the head pointer forward until it points to a node not present in the HashSet...
    3. Traverse the linked list...
       - For each node, check if the next node’s value exists in the HashSet...
       - If yes, bypass (delete) that node by adjusting the `next` pointer...
    4. Continue until the end of the list is reached...
    5. Return the modified linked list...

Key Observations:
    -> Using a HashSet improves efficiency by avoiding repeated linear searches...
    -> Careful handling of head deletion avoids null pointer issues...
    -> The algorithm only changes pointers; it does not create new nodes...

Time and Space Complexity:
    -> Time Complexity: O(n + m)...
       (where n = number of nodes in the linked list, m = size of the numbers array)...
    -> Space Complexity: O(m)...
       (for storing elements of `numbers` in a HashSet)...

Example Walkthrough:
    Input:
        Linked List: 1 → 2 → 3 → 4 → 5...
        numbers = [2, 5]...
    Process:
        Step 1: Store {2, 5} in HashSet...
        Step 2: Delete node with value 2 → List becomes 1 → 3 → 4 → 5...
        Step 3: Delete node with value 5 → List becomes 1 → 3 → 4...
    Output:
        Modified Linked List: 1 → 3 → 4...

*/

import java.util.HashSet;

public class Delete_Nodes_From_Linked_List_Present_In_Array {

    private static class Node {
        int val;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private static Node modifiedList(int[] numbers, Node head) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : numbers) {
            set.add(num);
        }

        while (head != null && set.contains(head.val)) {
            head = head.next;
        }

        Node temp = head;

        while (temp != null && temp.next != null) {
            while (temp.next != null && set.contains(temp.next.val)) {
                temp.next = temp.next.next;
            }
            temp = temp.next;
        }
        return head;
    }

    private static void displayList(Node ans) {

        Node temp = ans;
        while (temp.next != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.print(temp.val);
        System.out.println();

    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);
        head.next.next.next.next.next.next.next.next = new Node(9);
        head.next.next.next.next.next.next.next.next.next = new Node(10);
        head.next.next.next.next.next.next.next.next.next.next = new Node(11);
        head.next.next.next.next.next.next.next.next.next.next.next = new Node(12);
        head.next.next.next.next.next.next.next.next.next.next.next.next = new Node(13);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next = new Node(14);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new Node(15);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new Node(16);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new Node(17);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new Node(18);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new Node(19);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new Node(20);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new Node(21);

        int[] numbers = {2, 5, 6, 9, 10, 11, 12, 14, 15, 17, 20};

        System.out.println("Original Linked List: ");
        displayList(head);

        Node ans = modifiedList(numbers, head);

        System.out.println("After removing nodes from linked list: ");
        displayList(ans);
    }

}

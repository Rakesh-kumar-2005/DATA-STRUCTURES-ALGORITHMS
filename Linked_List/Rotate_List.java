package Linked_List;

/*

Description:
    -> This program performs a right rotation on a singly linked list...
    -> A rotation by `k` positions means the last `k` nodes are moved to the front of the list...
    -> The relative order of nodes is preserved after rotation...

Problem Statement:
    -> Given a singly linked list and an integer `k`, rotate the list to the right by `k` places...
    -> Example: For list [1 -> 2 -> 3 -> 4 -> 5] and k = 2 → Result: [4 -> 5 -> 1 -> 2 -> 3]...

Approach:
    > Linked List Traversal and Rearrangement:
        -> Compute the length of the linked list by traversal...
        -> Adjust `k` using `k % length` to handle cases where k ≥ length...
        -> Find the `(length - k)`-th node, which becomes the new breaking point...
        -> Split the list into two parts and reconnect them with proper links...
        -> Return the new head node of the rotated list...

Algorithm Steps:
    -> Initialization:
        1. Handle base cases: if list is empty, has one node, or k = 0, return head directly...
        2. Traverse the list once to compute its length...

    -> Rotation Adjustment:
        1. Update k = k % length to avoid unnecessary rotations...
        2. If k = 0 after adjustment, return head (no change needed)...

    -> Breaking and Reconnecting:
        1. Traverse to the `(length - k)`-th node...
        2. Break the link at this node and set the next node as the new head...
        3. Traverse to the end of the new list’s second part and connect it back to the old head...

    -> Return:
        1. Return the new head of the rotated list...

Key Characteristics:
    -> Efficient single-pass list traversal for length calculation and rearrangement...
    -> No extra data structures required; rotation is performed in-place...
    -> Works correctly for all cases, including when k ≥ length...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the linked list (due to traversal)...
    -> Space Complexity: O(1), as no extra memory is used apart from pointers...

Demonstration:
    -> Input Linked List: 1 -> 2 -> 3 -> 4 -> 5
    -> Rotation k = 2
    -> Steps:
       - Length = 5
       - k = 2 % 5 = 2
       - Break after (5 - 2) = 3rd node → split into [1 -> 2 -> 3] and [4 -> 5]
       - Reconnect: [4 -> 5 -> 1 -> 2 -> 3]
    -> Output Linked List: 4 -> 5 -> 1 -> 2 -> 3

*/

public class Rotate_List {

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

    private static Node rotateRight(Node head, int k) {

        if (head == null || head.next == null || k == 0) {
            return head;
        }

        Node temp = head;
        int length = 0;
        while (temp != null) {
            temp = temp.next;
            length++;
        }

        k = k % length;
        if (k == 0) {
            return head;
        }

        int steps = length - k;
        temp = head;
        while (steps > 1) {
            temp = temp.next;
            steps--;
        }

        Node right = temp.next;
        temp.next = null;

        Node tempRight = right;
        while (tempRight.next != null) {
            tempRight = tempRight.next;
        }
        tempRight.next = head;

        return right;
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
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.println("Original Linked List: ");
        displayList(head);

        int k = 2;
        Node ans = rotateRight(head, k);

        System.out.println("Rotated Linked List");
        displayList(ans);

    }

}

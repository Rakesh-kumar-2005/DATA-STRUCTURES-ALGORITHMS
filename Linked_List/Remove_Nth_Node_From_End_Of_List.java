package Linked_List;

/*

Description:
    -> This program removes the Nth node from the end of a singly linked list.
    -> The linked list is represented using a custom `Node` class, where each node
       contains an integer value and a reference to the next node.
    -> The approach involves first finding the total length of the linked list,
       then calculating the position of the node to remove from the start.

Problem Statement:
    -> Given the head of a singly linked list and an integer n, remove the Nth node from the end.
    -> Return the head of the modified list.
    -> If n equals the length of the list, remove the head node.

Approach:
    > Two-Pass Length Counting Method:
        -> First traverse the list to determine its length.
        -> If n == length, the head node is removed by returning head.next.
        -> Otherwise:
            1. Calculate the index of the node to remove from the start:
                position = length - n
            2. Traverse again until reaching the node before the target node.
            3. Adjust its `next` pointer to skip the target node.
        -> Return the updated head.

Algorithm Steps:
    1. Initialize a temporary pointer `temp` to head and set `length = 0`.
    2. Traverse the list once to find its total length.
    3. If n == length:
        a. Remove the head node → return head.next.
    4. Calculate position from start: length = length - n.
    5. Reset `temp` to head.
    6. Traverse until length > 1:
        a. Decrement length.
        b. Move `temp` to temp.next.
    7. Skip the target node by setting:
        temp.next = temp.next.next
    8. Return head.

Key Characteristics:
    -> Uses two passes through the linked list (O(2n) = O(n) time complexity).
    -> Simple to implement without advanced pointer tricks.
    -> Works for any valid value of n (1 ≤ n ≤ length of list).

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the number of nodes in the list.
    -> Space Complexity: O(1) (no extra data structures used).

Example:
    Input:
        Linked list: 1 -> 2 -> 3 -> ... -> 21
        n = 3
    Output:
        After removing 3rd node from end:
        1 -> 2 -> 3 -> ... -> 18 -> 20 -> 21

*/

public class Remove_Nth_Node_From_End_Of_List {

    private static class Node {

        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }

    }

    private static Node removeNthFromEnd(Node head, int n) {

        Node temp = head;
        int length = 0;

        while (temp != null) {
            length++;
            temp = temp.next;
        }

        if (n == length) {
            return head.next;
        }

        temp = head;
        length = length - n;

        while (length > 1) {
            length--;
            temp = temp.next;
        }

        temp.next = temp.next.next;
        return head;

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

        int n = 3;
        Node ans = removeNthFromEnd(head, n);

        System.out.println("After removing " + n + "th node from end of linked list: ");

        while (ans.next != null) {
            System.out.print(ans.val + " -> ");
            ans = ans.next;
        }

        System.out.println(ans.val);

    }


}

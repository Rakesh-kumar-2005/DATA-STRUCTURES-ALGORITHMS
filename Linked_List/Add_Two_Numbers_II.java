package Linked_List;

/*

Description:
    -> This program adds two non-empty linked lists that represent two non-negative integers.
    -> The digits are stored in forward order (most significant digit comes first).
    -> Each node in the linked list contains a single digit.
    -> The output is also returned as a linked list in forward order.

Problem Statement:
    -> Given two linked lists l1 and l2 representing two integers:
        l1 = 7 → 2 → 4 → 3  (represents 7243)
        l2 = 5 → 6 → 4      (represents 564)
    -> The sum is 7243 + 564 = 7807.
    -> The result should be returned as a linked list: 7 → 8 → 0 → 7.

Approach:
    1. Use two stacks (st1, st2) to reverse the order of digits from both lists.
    2. Pop elements from both stacks and add them with a carry.
    3. For each sum, create a new node and insert it at the front of the result list.
    4. Continue until both stacks are empty and carry is 0.

Algorithm Steps:
    1. Traverse both linked lists and push their values into two stacks.
    2. Initialize carry = 0 and result list as null.
    3. While either stack is non-empty or carry != 0:
        a. Pop values from both stacks (or take 0 if empty).
        b. Compute sum = val1 + val2 + carry.
        c. Update carry = sum / 10.
        d. Create a new node with value = sum % 10.
        e. Insert this node at the front of the result list.
    4. Return the result list head.

Key Observations:
    -> Using stacks simulates reverse traversal since digits are stored in forward order.
    -> Unlike the simpler "Add Two Numbers I" problem (digits in reverse order), 
       here we cannot directly traverse and add from head to tail.
    -> Carry must always be handled carefully when building the new list.

Time and Space Complexity:
    -> Time Complexity: O(m + n), where m and n are the lengths of the two linked lists.
    -> Space Complexity: O(m + n), for storing values in stacks.

Example Walkthrough:
    Input:
        l1 = 7 → 2 → 4 → 3
        l2 = 5 → 6 → 4
    Process:
        Stack1 = [7, 2, 4, 3], Stack2 = [5, 6, 4]
        Pop values and add with carry:
            3 + 4 = 7 → Node(7)
            4 + 6 = 10 → Node(0), carry = 1
            2 + 5 + 1 = 8 → Node(8)
            7 + 0 = 7 → Node(7)
    Output:
        Result = 7 → 8 → 0 → 7

*/

import java.util.Stack;

public class Add_Two_Numbers_II {

    private static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private static Node addTwoNumbers(Node l1, Node l2) {

        Node temp = new Node(0);

        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();

        temp = temp.next;
        Node t1 = l1;
        Node t2 = l2;
        int carry = 0;

        while (t1 != null) {
            st1.push(t1.val);
            t1 = t1.next;
        }

        while (t2 != null) {
            st2.push(t2.val);
            t2 = t2.next;
        }

        while (! st1.isEmpty() || ! st2.isEmpty() || carry != 0) {

            int val1 = (! st1.isEmpty()) ? st1.pop() : 0;
            int val2 = (! st2.isEmpty()) ? st2.pop() : 0;

            int sum = val1 + val2 + carry;
            carry = sum / 10;
            sum %= 10;

            Node t = new Node(sum);
            t.next = temp;
            temp = t;
        }

        return temp;
    }

    private static void displayNode(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node l1 = new Node(7);
        l1.next = new Node(2);
        l1.next.next = new Node(4);
        l1.next.next.next = new Node(3);

        Node l2 = new Node(5);
        l2.next = new Node(6);
        l2.next.next = new Node(4);

        System.out.println("First Linked List : ");
        displayNode(l1);

        System.out.println("Second Linked List : ");
        displayNode(l2);

        Node ans = addTwoNumbers(l1, l2);
        System.out.println("Addition of the above Linked List is : ");
        displayNode(ans);

    }

}

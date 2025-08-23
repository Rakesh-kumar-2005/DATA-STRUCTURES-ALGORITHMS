package Linked_List;

/*

Description:
    -> This program adds two non-negative integers represented as linked lists.
    -> Each node in the linked list contains a single digit.
    -> The digits are stored in reverse order, meaning the 1's digit is at the head.
    -> The result of the addition is also returned as a linked list in the same format.

Problem Statement:
    Given two non-empty linked lists l1 and l2, which represent two non-negative integers,
    add the two numbers and return the sum as a linked list.
    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:
    Input:  l1 = [2 → 4 → 3], l2 = [5 → 6 → 4]
            (represents 342 + 465)
    Output: [7 → 0 → 8]
            (represents 807)

Approach:
    1. Initialize a dummy node (res) to store the result.
    2. Use a pointer (temp) to build the result list.
    3. Maintain a carry variable for sums ≥ 10.
    4. Traverse both lists simultaneously:
        - Add corresponding digits and the carry.
        - Create a new node with (sum % 10).
        - Update carry = sum / 10.
    5. If one list is longer, continue with remaining nodes.
    6. If carry remains at the end, add a new node with carry value.
    7. Return res.next (ignoring the dummy head).

Key Observations:
    -> Using a dummy head simplifies edge cases (like empty list or extra carry).
    -> Each digit is handled independently, mimicking manual addition.

Time and Space Complexity:
    -> Time Complexity: O(max(m, n)), where m and n are the lengths of the two linked lists.
    -> Space Complexity: O(max(m, n)), for storing the resulting linked list.

Example Walkthrough:
    Input:  l1 = [2 → 4 → 3], l2 = [5 → 6 → 4]
    Process:
        Step 1: (2 + 5) = 7 → Node(7), carry = 0
        Step 2: (4 + 6) = 10 → Node(0), carry = 1
        Step 3: (3 + 4 + 1) = 8 → Node(8), carry = 0
    Output: [7 → 0 → 8]

*/

public class Add_Two_Numbers_I {

    private static class Node {
        int val;
        Node next;

        public Node() {}

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private static Node addTwoNumbers(Node l1, Node l2) {

        Node res = new Node();
        Node temp = res;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {

            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            int sum = val1 + val2 + carry;
            carry = sum / 10;
            sum %= 10;

            Node num = new Node(sum);
            temp.next = num;
            temp = temp.next;

            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;

        }

        return res.next;

    }

    private static void displayNode(Node ans) {

        while (ans != null) {
            System.out.print(ans.val + " ");
            ans = ans.next;
        }
        System.out.println();

    }

    public static void main(String[] args) {
        Node l1 = new Node(2);
        l1.next = new Node(4);
        l1.next.next = new Node(3);

        Node l2 = new Node(5);
        l2.next = new Node(6);
        l2.next.next = new Node(4);

        Node ans = addTwoNumbers(l1, l2);

        System.out.println("First Linked List : ");
        displayNode(l1);

        System.out.println("Second Linked List : ");
        displayNode(l2);

        System.out.println("Addition of the above Linked List is : ");
        displayNode(ans);

    }
    
}

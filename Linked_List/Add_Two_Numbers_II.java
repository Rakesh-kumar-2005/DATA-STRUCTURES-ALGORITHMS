package Linked_List;

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
package Linked_List;

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
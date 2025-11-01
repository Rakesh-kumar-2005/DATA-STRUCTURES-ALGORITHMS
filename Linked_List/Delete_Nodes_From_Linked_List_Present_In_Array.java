package Linked_List;

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

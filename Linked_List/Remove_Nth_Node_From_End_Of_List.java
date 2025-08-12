package Linked_List;

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
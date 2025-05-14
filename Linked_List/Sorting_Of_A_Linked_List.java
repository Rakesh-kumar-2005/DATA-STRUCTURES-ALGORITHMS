package Linked_List;

import java.util.PriorityQueue;

public class Sorting_Of_A_Linked_List {

    private static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static Node sort(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        Node temp = head;
        while (temp != null) {
            pq.add(temp.val);
            temp = temp.next;
        }

        Node ans = new Node(- 1);
        temp = ans;

        while (! pq.isEmpty()) {
            temp.next = new Node(pq.poll());
            temp = temp.next;
        }

        return ans.next;
    }

    public static void main(String[] args) {

        Node a = new Node(33);
        Node b = new Node(1);
        Node c = new Node(46);
        Node d = new Node(19);
        Node e = new Node(10);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        Node ans = sort(a);

        while (ans != null) {
            System.out.print(ans.val + " ");
            ans = ans.next;
        }

    }

}
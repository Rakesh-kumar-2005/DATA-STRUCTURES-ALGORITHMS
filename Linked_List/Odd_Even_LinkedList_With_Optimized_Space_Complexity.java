package Linked_List;

/*
        Description :-
            We have to find rearrange a linked list in odd even manner...
            e.g :-
                input = 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
                output = 1 -> 3 -> 5 -> 7 -> 9 -> 2 -> 4 -> 6 -> 8

        Approach :-
            -> Initialization:
               > Start by creating pointers to manage the odd-indexed and even-indexed nodes.
               > odd pointer will begin at the head (first node).
               > even pointer will begin at the second node.
               > Keep a reference to the head of the even list (evenHead).

            -> Traversing and Rearranging:
               > Loop through the list while ensuring that the even node and its next node exist.
               > Adjust the next pointers to link odd nodes together and even nodes together.
                    This involves skipping over the opposite type (odd skipping even, and vice versa).

            -> Connecting Odd and Even Lists:
               > After the loop, link the last node of the odd-indexed list to the head of the
                    even-indexed list (evenHead).

        Benefits of This Approach :-
            -> Optimized Space Complexity:
               > The approach uses O(1) extra space as it doesn’t require additional data structures,
                    just a few pointers.

            -> Linear Time Complexity:
               > The algorithm runs in O(n) time where n is the number of nodes in the linked list,
                as it traverses each node exactly once.
*/

public class Odd_Even_LinkedList_With_Optimized_Space_Complexity {

    private static class Node {
        public int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private static Node oddEvenList(Node head) {
        if (head == null || head.next == null) return head;

        Node odd = head;
        Node even = head.next;
        Node evenHead = even;


        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(3);
        Node c = new Node(5);
        Node d = new Node(7);
        Node e = new Node(9);
        Node f = new Node(11);
        Node g = new Node(2);
        Node h = new Node(4);
        Node i = new Node(6);
        Node j = new Node(8);
        Node k = new Node(10);
        Node l = new Node(12);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = h;
        h.next = i;
        i.next = j;
        j.next = k;
        k.next = l;

        Node ans = oddEvenList(a);
        while (ans != null) {
            System.out.print(ans.val + " ");
            ans = ans.next;
        }
    }
}

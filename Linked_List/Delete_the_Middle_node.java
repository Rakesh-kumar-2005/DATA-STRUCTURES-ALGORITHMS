package Linked_List;

/*

Description:
   Following program demonstrates the solution to the "Delete the Middle Node of a Linked List" problem using the Fast and Slow Pointer technique.

Problem Statement:
   -> Given a singly linked list, delete the middle node.
   -> If the list has an even number of nodes, there will be two middle nodes.
   -> The program implements two approaches: delete the left middle node (in case of even length) and delete the right middle node.

Approach:
   > Fast and Slow Pointer Technique:
      i. Use two pointers: fast and slow, both initially pointing to the head of the list.
      ii. Move the fast pointer at twice the speed of the slow pointer.
      iii. When the fast pointer reaches the end, the slow pointer will be at the middle (or just before it).
      iv. Delete the node next to the slow pointer, which is the middle node.

> Left Middle Node Deletion (delete_leftMiddle):
   -> If list has odd length (e.g., 7 nodes), deletes the exact middle (4th node).
   -> If list has even length (e.g., 8 nodes), deletes the left middle (4th node).
   -> Fast pointer advances until fast.next.next becomes null.
   -> When fast pointer can't advance by two more steps, slow pointer is just before the middle node.

> Right Middle Node Deletion (delete_rightMiddle):
   -> If list has odd length (e.g., 7 nodes), deletes the exact middle (4th node).
   -> If list has even length (e.g., 8 nodes), deletes the right middle (5th node).
   -> Fast pointer advances until both fast.next.next and fast.next.next.next become null.
   -> Additional condition ensures proper handling of even-length lists.

> Edge Cases:
   -> For empty lists or single-node lists, returns null.
   -> For lists with multiple nodes, preserves the head while removing the middle node.

> Delete Operation:
   -> Deletion is performed by updating the next pointer of the node before the middle node.
   -> No actual memory deallocation is needed as Java's garbage collector handles this.

> Time and Space Complexity:
   -> Time Complexity: O(n) where n is the number of nodes in the linked list.
   -> Space Complexity: O(1) as only a constant amount of extra space is used.

*/

public class Delete_the_Middle_node {
    private static class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }

    private static Node delete_leftMiddle(Node head){
        if (head == null || head.next == null){
            return null;
        }

        Node fast = head;
        Node slow = head;

        while (fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    private static Node delete_rightMiddle(Node head){
        if (head == null || head.next == null){
            return null;
        }

        Node fast = head;
        Node slow = head;

        while (fast.next.next != null && fast.next.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }
    public static void main(String[] args) {
        Node a = new Node(2);
        Node b = new Node(4);
        Node c = new Node(6);
        Node d = new Node(8);
        Node e = new Node(10);
        Node f = new Node(12);
        Node g = new Node(42);
        Node i = new Node(69);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = i;

        Node ans = delete_leftMiddle(a);
        while (ans != null){
            System.out.print(ans.val + " ");
            ans = ans.next;
        }

        ans = delete_rightMiddle(a);
        while (ans != null){
            System.out.print(ans.val + " ");
            ans = ans.next;
        }
    }

}

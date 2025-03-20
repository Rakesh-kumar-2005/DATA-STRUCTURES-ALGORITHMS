package Linked_List;

/*

Description:
  Following program demonstrates the solution to the "Flatten a Multilevel Doubly Linked List" problem using recursive traversal.

Problem Statement:
  -> Given a doubly linked list where each node has a next, prev, and a child pointer.
  -> The child pointer may point to a separate doubly linked list, which could also have nodes with child pointers.
  -> The task is to flatten this multilevel data structure into a single-level doubly linked list.
  -> The flattened list should maintain the same order as if each sublevel list was inserted at the node with the child pointer.

Approach:
  > Recursive Depth-First Traversal:
     i. Traverse the main list from head to tail.
     ii. When a node with a child pointer is encountered, recursively flatten the child list.
     iii. Insert the flattened child list between the current node and its next node.
     iv. Continue flattening the remaining part of the original list.

> Algorithm Steps:
  -> Iterate through the linked list from head to tail.
  -> For each node with a child pointer:
     1. Store the next node in a temporary variable.
     2. Recursively flatten the child list.
     3. Connect the flattened child list between the current node and the next node.
     4. Traverse to the end of the flattened child list to reconnect with the original next node.
     5. Set the child pointer to null to satisfy the problem's requirement.
  -> Return the original head which now points to a fully flattened list.

> Reconnection Logic:
  -> After flattening the child list, properly update the prev pointers to maintain the doubly-linked property.
  -> When reconnecting the end of the flattened child list to the original next node, ensure both next and prev pointers are updated.

> Edge Cases:
  -> Handle null head case.
  -> Handle nested child lists of arbitrary depth through recursion.
  -> Properly nullify all child pointers in the final flattened list.

> Time and Space Complexity:
  -> Time Complexity: O(n) where n is the total number of nodes across all levels.
  -> Space Complexity: O(d) for the recursion stack, where d is the maximum depth of nested lists.

*/

public class Flatten_Doubly_LIst {
    private static class Node{
        int val;
        Node next;
        Node prev;
        Node child;

        public Node(int val){
            this.val = val;
            this.next = null;
            this.prev = null;
            this.child = null;
        }
    }

    private static Node flatten(Node head) {

        Node temp = head;
        while(temp != null) {
            Node t = temp.next;
            if(temp.child != null) {
                Node helper = flatten(temp.child);
                temp.next = helper;
                helper.prev = temp;

                while (helper.next != null) {
                    helper = helper.next;
                }
                helper.next = t;
                if (t != null) t.prev = helper;
            }
            temp.child = null; //have to erase the child pointer
            temp = t;
        }
        return head;
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(3);
        Node c = new Node(5);
        Node d = new Node(7);
        Node e = new Node(9);
        Node f = new Node(11);
        Node g = new Node(13);
        Node h = new Node(14);
        Node i = new Node(15);
        Node j = new Node(16);
        Node k = new Node(17);
        Node l = new Node(18);

        a.next = b;     c.child = g;    h.child = j;
        b.prev = a;
        b.next = c;     g.next = h;     j.next = k;
        c.prev = b;     h.prev = g;     k.prev = j;
        c.next = d;     h.next = i;     k.next = l;
        d.prev = c;     i.prev = h;     l.prev = k;
        d.next = e;
        e.prev = d;
        e.next = f;
        f.prev = e;

        Node ans = flatten(a);
        while (ans != null){
            System.out.print(ans.val + " ");
            ans = ans.next;
        }
    }
}

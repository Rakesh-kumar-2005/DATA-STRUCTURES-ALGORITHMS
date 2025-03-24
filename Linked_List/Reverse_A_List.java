package Linked_List;

/*  

Description:
  Following program demonstrates the implementation of reversing a singly linked list in Java,
  using an iterative approach to rearrange node connections.

Problem Statement:
  -> Given a singly linked list...
  -> Reverse the order of nodes in-place...
  -> Transform the list from original order to completely reversed order...

Approach:
  > Three-Pointer Traversal Strategy:
     i. Use three pointers to track current, previous, and next nodes...
     ii. Systematically rearrange node connections to reverse direction...
     iii. Modify node links without using additional data structures...

> Algorithm Steps:
  -> Initial Checks:
     * Handle edge cases of empty or single-node lists...
     * Return the original list if no reversal is needed...

  -> Reversal Process:
     * Initialize three pointers: prev (null), curr (head), after (curr.next)...
     * Systematically move pointers while changing node connections...
     * Redirect each node's next pointer to its previous node...
     * Continue until entire list is traversed...

  -> Pointer Manipulation:
     * Break original forward links...
     * Create reversed links by pointing each node to its previous node...
     * Ensure no nodes are lost during the process...

> Key Insight:
  -> Efficiently reverses list in single pass...
  -> Uses constant extra space...
  -> Preserves all original node values during reversal...

> Example:
  -> For input list: 2 -> 4 -> 6 -> 8 -> 10 -> 12
  -> After reversal: 12 -> 10 -> 8 -> 6 -> 4 -> 2
  -> Complete directional change of the list...

> Edge Cases:
  -> Handles empty lists...
  -> Manages single-node lists...
  -> Works with lists of various lengths...
  -> Preserves list integrity during reversal...

> Time and Space Complexity:
  -> Time Complexity: O(n) where n is the number of nodes...
  -> Space Complexity: O(1) as reversal happens in-place...

> Performance Characteristics:
  -> Constant extra space requirement...
  -> Linear time complexity...
  -> No additional memory allocation...
  -> Minimal computational overhead...

*/

public class Reverse_A_List {
    private static class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }
    private static Node reverse(Node head){
        if (head == null || head.next == null) return head;

        Node prev = null;
        Node curr = head;
        Node after = curr.next;
        curr.next = null;

        while (after != null){
            prev = curr;
            curr = after;
            after = after.next;
            curr.next = prev;
        }
        return curr;
    }

    public static void main(String[] args) {
        Node a = new Node(2);
        Node b = new Node(4);
        Node c = new Node(6);
        Node d = new Node(8);
        Node e = new Node(10);
        Node f = new Node(12);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;

        Node ans = reverse(a);
        while (ans != null){
            System.out.print(ans.val + " ");
            ans = ans.next;
        }
    }
}

package Linked_List;

/*  

Description:
  Following program demonstrates the solution to removing duplicate elements
  from a sorted singly linked list in Java, maintaining the list's original structure.

Problem Statement:
  -> Given a sorted linked list with potential duplicate elements...
  -> Remove all duplicate elements, keeping only unique values...
  -> Modify the list in-place without creating a new data structure...

Approach:

> Single Traversal Duplicate Elimination Strategy:
     i. Traverse the linked list using a temporary pointer...
     ii. Compare current node with its next node...
     iii. If duplicates are found, bypass the duplicate nodes...
     iv. Maintain the list's sorted order during the process...

> Algorithm Steps:
  -> Initial Checks:
     * Handle edge cases of empty or single-element lists...
     * Return the original list if no further processing is needed...

  -> Duplicate Removal Process:
     * Use a temporary pointer to traverse the list...
     * Compare current node's value with the next node's value...
     * If values match, skip the duplicate node by updating next pointers...
     * Continue until the entire list is processed...

  -> Node Manipulation:
     * Use direct pointer manipulation to remove duplicate nodes...
     * Ensure no memory leaks by properly linking nodes...

> Key Insight:
  -> Works efficiently on already sorted linked lists...
  -> Performs in-place modification with minimal additional memory...
  -> Preserves the original list structure while removing duplicates...

> Example:
  -> For input list: 2 -> 2 -> 6 -> 6 -> 10 -> 10
  -> After duplicate removal: 2 -> 6 -> 10
  -> Each unique value is retained only once...

> Edge Cases:
  -> Handles empty lists gracefully...
  -> Manages lists with all unique elements...
  -> Processes lists with multiple consecutive duplicates...
  -> Works correctly with lists of various lengths...

> Time and Space Complexity:
  -> Time Complexity: O(n) where n is the number of nodes in the list...
  -> Space Complexity: O(1) as modification happens in-place...

> Performance Characteristics:
  -> Single-pass algorithm with constant extra space...
  -> Minimal overhead for duplicate removal...
  -> Optimal for sorted linked lists...

*/

public class Remove_Duplicates {
    private static class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }

    private static Node removeDuplicates(Node head){
        if(head == null || head.next == null) return head;

        Node temp = head;
        while (temp != null && temp.next != null){
            if(temp.val != temp.next.val) temp = temp.next;
            if(temp.next == null) return head;
            if (temp.val == temp.next.val)temp.next = temp.next.next;
        }
        return head;
    }
    public static void main(String[] args) {
        Node a = new Node(2);
        Node b = new Node(2);
        Node c = new Node(6);
        Node d = new Node(6);
        Node e = new Node(10);
        Node f = new Node(10);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;

        Node ans = removeDuplicates(a);
        while (ans != null){
            System.out.print(ans.val + " ");
            ans = ans.next;
        }
    }
}

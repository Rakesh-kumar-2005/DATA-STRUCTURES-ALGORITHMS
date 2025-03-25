package Linked_List;

/*  

Description:
  Following program demonstrates the implementation of swapping adjacent nodes
  in a singly linked list using a recursive approach in Java.

Problem Statement:
  -> Given a linked list...
  -> Swap every two adjacent nodes...
  -> Modify the list in-place without creating a new data structure...

Approach:
  > Recursive Node Swapping Strategy:
     i. Use recursion to swap pairs of nodes...
     ii. Rearrange node connections for each pair...
     iii. Recursively process the remaining list...

> Algorithm Steps:
  -> Initial Checks:
     * Handle edge cases of empty or single-node lists...
     * Return the original list if no swapping is possible...

  -> Pair Swapping Process:
     * Store the second node as a temporary reference...
     * Redirect the first node's next pointer...
     * Connect the second node to the first node...
     * Recursively process the remaining list...
     * Update the head to be the second node...

  -> Recursive Mechanism:
     * Break down the list into pairs...
     * Swap each pair recursively...
     * Ensure proper reconnection of swapped pairs...

> Key Insight:
  -> Elegantly swaps nodes using recursive method...
  -> Handles lists of varying lengths...
  -> Performs in-place modification...

> Example:
  -> For input list: 1 -> 3 -> 5 -> 7 -> 9 -> 11
  -> After pair swapping: 3 -> 1 -> 7 -> 5 -> 11 -> 9
  -> Each adjacent pair is interchanged...

> Edge Cases:
  -> Manages empty lists...
  -> Handles lists with odd number of nodes...
  -> Works with lists of different lengths...
  -> Preserves list structure during swapping...

> Time and Space Complexity:
  -> Time Complexity: O(n) where n is the number of nodes...
  -> Space Complexity: O(n) due to recursive call stack...

> Performance Characteristics:
  -> Recursive implementation...
  -> Linear time complexity...
  -> Additional space used for recursive calls...
  -> Elegant and concise solution...

*/

public class Swapping_Nodes_in_Pair {
    private static class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }

    private static Node swapInPairs(Node head){
        if(head == null || head.next == null) return head;

        Node temp = head.next;
        head.next = head.next.next;
        temp.next = head;
        head.next = swapInPairs(head.next);
        head = temp;

        return head;
    }
    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(3);
        Node c = new Node(5);
        Node d = new Node(7);
        Node e = new Node(9);
        Node f = new Node(11);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;

        Node ans = swapInPairs(a);
        while (ans != null){
            System.out.print(ans.val + " ");
            ans = ans.next;
        }
    }
}

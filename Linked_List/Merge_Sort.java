package Linked_List;

/*

Description:
  Following program demonstrates the solution to the "Merge Two Sorted Linked Lists" problem using an iterative approach...

Problem Statement:
  -> Given two sorted singly linked lists...
  -> Merge them into a single sorted linked list...
  -> Return the head of the merged list...

Approach:
  > Iterative Comparison and Node Linking:
     i. Use a dummy node to simplify edge cases and track the head of the merged list...
     ii. Compare current nodes from both lists and link the smaller one to the result...
     iii. Advance the pointer of the list whose node was just added...
     iv. After one list is exhausted, append the remaining nodes of the other list...

> Algorithm Steps:
  -> Create a dummy node with an arbitrary value (100) to serve as the starting point...
  -> Maintain a helper pointer to build the merged list...
  -> Iterate through both lists simultaneously:
     * Compare values of current nodes from both lists...
     * Link the node with smaller value to the merged list...
     * Advance the pointer of the list whose node was just added...
  -> When one list is exhausted, append all remaining nodes from the other list...
  -> Return the next of the dummy node, which is the head of the merged list...

> Key Insight:
  -> Using a dummy node eliminates the need to handle special cases for the head of the merged list...
  -> The algorithm preserves the original order of equal elements (stable sorting)...

> Example:
  -> The example creates two sorted linked lists:
     * First list: 1->3->5->7->9->11
     * Second list: 2->4->6->8->10->12
     * The merged list: 1->2->3->4->5->6->7->8->9->10->11->12

> Edge Cases:
  -> Handles cases where one list is empty...
  -> Works correctly even if lists have different lengths...

> Time and Space Complexity:
  -> Time Complexity: O(m + n) where m and n are the lengths of the two lists...
  -> Space Complexity: O(1) as only a constant amount of extra space is used...

*/

public class Merge_Sort {
    private static class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }

    private static Node mergeSort(Node head1, Node head2){
        Node tempA = head1;
        Node tempB = head2;
        Node ans = new Node(100);
        Node helper = ans;

        while (tempA != null && tempB != null){
            if(tempA.val > tempB.val){
                helper.next = tempB;
                tempB = tempB.next;
                helper = helper.next;
            } else {
                helper.next = tempA;
                tempA = tempA.next;
                helper = helper.next;
            }
        }
        if (tempA != null) helper.next = tempA;
        else helper.next = tempB;

        return ans.next;
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

        g.next = h;
        h.next = i;
        i.next = j;
        j.next = k;
        k.next = l;

        Node ans = mergeSort(a,g);
        while (ans != null){
            System.out.print(ans.val + " ");
            ans = ans.next;
        }
    }
}

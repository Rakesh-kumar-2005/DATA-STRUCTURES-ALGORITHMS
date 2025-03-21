package Linked_List;

/*

Description:
  Following program demonstrates the solution to the "Find Intersection Point of Two Linked Lists" problem using the two-pointer approach.

Problem Statement:
  -> Given two singly linked lists that might intersect at some point.
  -> Find the node where the two lists intersect.
  -> If there is no intersection, return null.

Approach:
  > Two-Pointer Method with Length Calculation:
     i. Calculate the lengths of both linked lists.
     ii. Determine the length difference between the two lists.
     iii. Advance the pointer of the longer list by the difference in length.
     iv. Move both pointers simultaneously until they meet at the intersection point.

> Algorithm Steps:
  -> Calculate the length of the first linked list by traversing it.
  -> Calculate the length of the second linked list by traversing it.
  -> Reset both pointers to the heads of their respective lists.
  -> If the first list is longer, advance its pointer by (length1 - length2) steps.
  -> If the second list is longer, advance its pointer by (length2 - length1) steps.
  -> Traverse both lists simultaneously until the pointers meet at the same node.
  -> Return this node as the intersection point.

> Key Insight:
  -> By advancing the pointer of the longer list, we ensure that both pointers have an equal distance to travel to the intersection point.
  -> This guarantees that if there is an intersection, both pointers will reach it at the same time.

> Example:
  -> The example creates two linked lists:
     * First list: 2->4->6->8->10
     * Second list: 42->72->2->2->2->2->6->8->10
     * The intersection point is at node with value 6

> Edge Cases:
  -> Handles lists of different lengths correctly.
  -> If there is no intersection, both pointers will eventually become null.

> Time and Space Complexity:
  -> Time Complexity: O(m + n) where m and n are the lengths of the two lists.
  -> Space Complexity: O(1) as only a constant amount of extra space is used.

*/

public class Intersection_of_two_lists {
    private static class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }

    private static Node getIntersection(Node head1, Node head2){
        Node temp1 = head1;
        Node temp2 = head2;
        int length1 = 0 , length2 = 0;

        while (temp1 != null){
            temp1 = temp1.next;
            length1++;
        }
        while (temp2 != null){
            temp2 = temp2.next;
            length2++;
        }

        temp1 = head1;
        temp2 = head2;

        if(length1 > length2){
            int steps = length1 - length2;
            for (int i = 0; i < steps; i++){
                temp1 = temp1.next;
            }
        }else {
            int steps = length2 - length1;
            for (int i = 0; i < steps; i++){
                temp2 = temp2.next;
            }
        }

        while (temp1 != temp2){
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return temp1;
    }

    public static void main(String[] args) {
        Node a = new Node(2);
        Node b = new Node(4);
        Node c = new Node(6);
        Node d = new Node(8);
        Node e = new Node(10);
        Node f = new Node(12);
        Node g = new Node(42);
        Node h = new Node(72);
        Node i = new Node(2);
        Node j = new Node(2);
        Node k = new Node(2);
        Node l = new Node(2);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        f.next = g;
        h.next = i;
        i.next = j;
        j.next = k;
        k.next = l;
        l.next = c;

        Node ans = getIntersection(a,h);
        System.out.println("The value of the node from where the intersection starts : " + ans.val);
    }
}

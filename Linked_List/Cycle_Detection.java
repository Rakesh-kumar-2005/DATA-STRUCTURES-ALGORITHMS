package Linked_List;

/*
Description:
   Following program demonstrates the solution to the Linked List Cycle Detection problem using Floyd's Tortoise and Hare algorithm (slow and fast pointer approach).

Problem Statement:
   -> Given a linked list, determine if it contains a cycle.
   -> If a cycle exists, find the node where the cycle begins.
   -> A cycle occurs when a node's next pointer points back to a previous node in the list.

Approach:
> Two-Part Solution:
      i. First detect whether a cycle exists in the linked list.
      ii. If a cycle exists, find the starting point of the cycle.

> Cycle Detection (cyclePresent method):
   -> Use two pointers: slow and fast.
   -> The slow pointer moves one step at a time, while the fast pointer moves two steps.
   -> If there is no cycle, the fast pointer will eventually reach the end of the list.
   -> If there is a cycle, the two pointers will eventually meet inside the cycle.
   -> Handle edge cases: empty list or list with only one node.

> Finding Cycle Starting Point (cyclePosition method):
   -> First use the same approach as cyclePresent to detect if a cycle exists and find a meeting point.
   -> Once the pointers meet, reset one pointer to the head of the list.
   -> Move both pointers one step at a time until they meet again.
   -> The meeting point is the start of the cycle.
   -> Mathematical proof: If the distance from the head to the cycle start is 'a', the meeting point is 'b' steps into the cycle, and the cycle length is 'c', then the meeting point will be exactly at the cycle start when both pointers move 'a' steps from their respective positions.

> Implementation Details:
   -> Create a Node class with value and next pointer.
   -> Implement the two methods: cyclePresent and cyclePosition.
   -> In main, create a linked list with a cycle for demonstration.

> Time and Space Complexity:
   -> Time Complexity: O(n) where n is the number of nodes in the linked list.
   -> Space Complexity: O(1) as we only use a constant number of pointers regardless of input size.

> Note:
   -> The cyclePosition method assumes a cycle exists and doesn't handle the case where there is no cycle.
   -> In practice, it should be called only after confirming a cycle exists using cyclePresent.
*/

public class Cycle_Detection {
    private static class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }

    private static Node cyclePosition(Node head){
        Node fast = head;
        Node slow = head;

        while(fast != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)  break;
        }

        Node temp = head;
        while (temp != slow){
            temp = temp.next;
            slow = slow.next;
        }
        return slow;

    }
    private static boolean cyclePresent(Node head){
        if (head == null || head.next == null) return false;

        Node fast = head;
        Node slow = head;

        while (fast != null){
            slow = slow.next;
            if (fast.next == null) return false;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
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
        Node i = new Node(2654);
        Node j = new Node(43);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = h;
        h.next = i;
        i.next = j;
        j.next = c;

        System.out.println("Result of presence of a cycle : " + cyclePresent(a));
        System.out.println("Cycle Starts from the node with the value :  "+ cyclePosition(a).val);
    }
}

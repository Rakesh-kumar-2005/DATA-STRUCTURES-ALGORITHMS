package Linked_List;

/*
Description:
   Following program demonstrates the solution to the "Deep Copy of a Linked List with Random Pointers" problem using an interleaving approach...

Problem Statement:
   -> Given a linked list where each node contains a next pointer and a random pointer...
   -> The next pointer points to the next node in the sequence...
   -> The random pointer can point to any node in the list or be null...
   -> The task is to create a deep copy of this linked list such that the new list's structure is identical but completely separate in memory...
   -> A deep copy means that modifying the original list should not affect the copied list and vice versa...

Approach:
   > Three-phase Interleaving Technique:
      i. Create a new list with the same values (but without setting random pointers)...
      ii. Interweave the two lists by adjusting next pointers to link them together...
      iii. Set random pointers in the copied list using the interleaved structure...
      iv. Separate the two lists back into independent structures...

> Phase 1: Creating the Copied List:
   -> Traverse the original list and create a new list with the same values...
   -> This new list will only have the correct 'next' pointers without the 'random' pointers...

> Phase 2: Interweaving Lists:
   -> Create a temporary helper linked list where nodes alternate between original and copied list...
   -> After this phase: original_1 -> copy_1 -> original_2 -> copy_2 -> original_3 -> copy_3...
   -> This interleaving makes it easy to find the corresponding copy of any original node...

> Phase 3: Setting Random Pointers:
   -> For each node in the original list, set its copy's random pointer to the next of the original's random...
   -> If original.random = original_k, then copy.random = original_k.next (which is copy_k)...
   -> If original.random is null, then copy.random is also set to null...

> Phase 4: Separating Lists:
   -> Traverse the interleaved list and separate the original and copied lists...
   -> Restore the next pointers for both lists to their correct values...
   -> After this phase, we have two separate, independent linked lists...

> Example Walkthrough:
   -> The example creates a linked list with 6 nodes (values 1,3,5,7,9,11) with various random pointers...
   -> The algorithm creates a deep copy and the main method prints each node's value and its random pointer's value...

> Time and Space Complexity:
   -> Time Complexity: O(n) where n is the number of nodes in the linked list...
   -> Space Complexity: O(n) for storing the copied list...
   
*/

public class Deep_copy_with_Random_pointers {
    private static class Node{
        int val;
        Node next;
        Node random;

        public Node(int val){
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    private static Node deepCopy(Node head){

        Node temp1 = head;
        Node head2 = new Node(0);
        Node temp2 = head2;

        while (temp1 != null){
            Node t = new Node(temp1.val);
            temp2.next = t;
            temp2 = temp2.next;
            temp1 = temp1.next;
        }
        head2 = head2.next;
        temp1 = head;
        temp2 = head2;

        Node temp = new Node(-1);
        while (temp1 != null){
            temp.next = temp1;
            temp1 = temp1.next;
            temp = temp.next;

            temp.next = temp2;
            temp2 = temp2.next;
            temp = temp.next;
        }

        temp1 = head;
        temp2 = head2;
        while (temp1 != null){
            if(temp1.random == null) temp2.random = null;
            else temp2.random = temp1.random.next;
            temp1 = temp2.next;
            if (temp1 != null) temp2 = temp1.next;
        }

        temp1 = head;
        temp2 = head2;

        while (temp1 != null){
            temp1.next = temp2.next;
            temp1 = temp1.next;
            if (temp1 == null) break;
            temp2.next = temp1.next;
            if (temp2.next == null) break;
            temp2 = temp2.next;
        }
        return head2;
    }
    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(3);
        Node c = new Node(5);
        Node d = new Node(7);
        Node e = new Node(9);
        Node f = new Node(11);

        a.next = b; a.random = f;
        b.next = c; b.random = d;
        c.next = d; c.random = null;
        d.next = e; d.random = b;
        e.next = f; f.random = a;

        Node ans = deepCopy(a);
        while (ans != null){
            System.out.println(ans.val + " " + ans.random.val);
            ans = ans.next;
        }
    }
}

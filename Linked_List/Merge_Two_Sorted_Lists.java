package Linked_List;

/*

Description:
    -> This program merges two sorted linked lists into a single sorted linked list.
    -> It uses a dummy (helper) node to simplify pointer handling while constructing the new list.
    -> The result is a new linked list that maintains the sorted order.

Problem Statement:
    Given the heads of two sorted linked lists, merge them into one sorted linked list 
    and return its head.

Example:
    Input:
        List 1: 1 -> 3 -> 5
        List 2: 2 -> 4 -> 6
    Output:
        Merged List: 1 -> 2 -> 3 -> 4 -> 5 -> 6

Approach:
    1. Create a dummy node (`ans`) to serve as the head placeholder.
    2. Use a pointer (`helper`) to build the new list by comparing nodes from both lists.
    3. At each step, append the smaller value node to the result and move the respective pointer.
    4. Once one list is exhausted, append the remaining nodes of the other list.
    5. Return the merged list starting from `ans.next`.

Helper Methods:
    -> displayList(Node head): Prints all nodes of the linked list in "a->b->c" format.

Key Variables:
    -> temp1, temp2 : pointers for traversing input lists.
    -> helper       : pointer to build the new merged list.
    -> ans          : dummy node, whose next points to the merged list.

Time and Space Complexity:
    -> Time Complexity: O(n + m), where n and m are the lengths of the two lists.
    -> Space Complexity: O(n + m) in this implementation (since new nodes are created).
       (If reusing existing nodes, space can be O(1)).

Conclusion:
    This program efficiently merges two sorted linked lists into a single sorted list 
    while preserving their order.

*/

public class Merge_Two_Sorted_Lists {

    private static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }

    }

    private static Node mergeTwoLists(Node list1, Node list2) {

        Node temp1 = list1;
        Node temp2 = list2;

        Node ans = new Node(100);
        Node helper = ans;

        while (temp1 != null && temp2 != null) {

            if (temp1.val < temp2.val) {
                Node t = new Node(temp1.val);
                helper.next = t;
                helper = helper.next;
                temp1 = temp1.next;
            } else {
                Node t = new Node(temp2.val);
                helper.next = t;
                helper = helper.next;
                temp2 = temp2.next;
            }

        }

        if (temp1 == null) {
            helper.next = temp2;
        } else {
            helper.next = temp1;
        }

        return ans.next;

    }

    private static void displayList(Node head) {
        Node temp = head;
        while (temp.next != null) {
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        System.out.println(temp.val);
        System.out.println();
    }

    public static void main(String[] args) {

        Node list1 = new Node(1);
        list1.next = new Node(3);
        list1.next.next = new Node(5);

        Node list2 = new Node(2);
        list2.next = new Node(4);
        list2.next.next = new Node(6);

        System.out.println("List 1 : ");
        displayList(list1);
        System.out.println("List 2 : ");
        displayList(list2);

        Node mergedList = mergeTwoLists(list1, list2);
        System.out.println("After merging the new list is : ");
        displayList(mergedList);

    }

}

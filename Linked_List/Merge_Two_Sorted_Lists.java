package Linked_List;

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
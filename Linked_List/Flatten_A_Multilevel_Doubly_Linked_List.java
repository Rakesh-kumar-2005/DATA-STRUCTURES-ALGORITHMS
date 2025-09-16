package Linked_List;

public class Flatten_A_Multilevel_Doubly_Linked_List {

    private static class Node {
        int val;
        Node next;

        Node prev;
        Node child;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
            this.child = null;
        }
    }

    private static Node flatten(Node head) {

        Node temp = head;

        while (temp != null) {
            Node t = temp.next;

            if (temp.child != null) {

                Node helper = flatten(temp.child);
                temp.next = helper;
                helper.prev = temp;

                while (helper.next != null) {
                    helper = helper.next;
                }

                helper.next = t;
                if (t != null) {
                    t.prev = helper;
                }
            }

            temp.child = null; //have to erase the child pointer
            temp = t;

        }

        return head;
    }

    private static void displayList(Node head) {
        Node temp = head;
        while (temp.next != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println(temp.val);
        System.out.println();
    }


    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.child = new Node(3);
        head.next.child.child = new Node(4);
        head.next.child.child.child = new Node(5);
        head.next.child.child.child.child = new Node(6);

        System.out.println("Original Linked List: ");
        displayList(head);

        Node ans = flatten(head);

        System.out.println("Flattened Linked List: ");
        displayList(ans);

    }

}

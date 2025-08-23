package Linked_List;

public class Add_Two_Numbers_I {

    private static class Node {
        int val;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private static Node addTwoNumbers(Node l1, Node l2) {

        Node res = new Node();
        Node temp = res;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {

            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            int sum = val1 + val2 + carry;
            carry = sum / 10;
            sum %= 10;

            Node num = new Node(sum);
            temp.next = num;
            temp = temp.next;

            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;

        }

        return res.next;

    }

    private static void displayNode(Node ans) {

        while (ans != null) {
            System.out.print(ans.val + " ");
            ans = ans.next;
        }
        System.out.println();

    }

    public static void main(String[] args) {
        Node l1 = new Node(2);
        l1.next = new Node(4);
        l1.next.next = new Node(3);

        Node l2 = new Node(5);
        l2.next = new Node(6);
        l2.next.next = new Node(4);

        Node ans = addTwoNumbers(l1, l2);

        System.out.println("First Linked List : ");
        displayNode(l1);

        System.out.println("Second Linked List : ");
        displayNode(l2);

        System.out.println("Addition of the above Linked List is : ");
        displayNode(ans);

    }
    
}
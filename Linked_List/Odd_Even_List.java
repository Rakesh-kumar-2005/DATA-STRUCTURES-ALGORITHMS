package Linked_List;
public class Odd_Even_List {
    private static class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }

    private static Node oddEvenList(Node head){
        if (head == null || head.next == null) return head;

        Node odd = new Node(100);
        Node even = new Node(100);
        Node tempO = odd;
        Node tempE = even;
        Node temp = head;

        while (temp != null){
            tempO.next = temp;
            temp = temp.next;
            tempO = tempO.next;
            tempE.next = temp;
            if(temp.next == null) break;
            temp = temp.next;
            tempE = tempE.next;
        }
        odd = odd.next;
        tempO.next = even.next;
        return odd;
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
        f.next = g;
        g.next = h;
        h.next = i;
        i.next = j;
        j.next = k;
        k.next = l;

        Node ans = oddEvenList(a);
        while (ans != null){
            System.out.print(ans.val + " ");
            ans = ans.next;
        }
    }
}

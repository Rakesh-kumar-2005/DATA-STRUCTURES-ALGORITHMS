package Queue;
public class Queue_implementation_using_LinkedList {

/*
        Description :-
            We have to implement Queue using Linked List...

        Approach :-
            > we have to maintain two pointer Nodes :
                i. head - for removing elements from the front.
                ii, tail - for adding elements in the back.
            > so at the time of adding any element just shift the tail node to its next,
                and at the time of deletion shift the head node to its next.
*/

    public static class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }

    public static class Queue{
        Node head = null;
        Node tail = null;
        int size = 0;
        public void add(int val){
            Node temp = new Node(val);

            if(size == 0){
                head = tail = temp;
                size++;
            }else{
                tail.next = temp;
                tail = tail.next;
                size++;
            }
        }

        public int remove(){
            if(head == null){
                System.out.println("There's nothing to remove...");
                return -1;
            }
            int val = head.val;
            head = head.next;
            return val;
        }

        public int peek(){
            if(head == null){
                System.out.println("There's nothing to show...");
                return -1;
            }
           return head.val;
        }

        public int size(){
            return size;
        }

        public boolean isEmpty(){
            if(size == 0) return true;
            return false;
        }

        public void display(){
            Node temp = head;

            while (temp != null) {
                System.out.print(temp.val + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        q.add(10);
        q.add(23);
        q.add(35);
        q.add(46);
        q.add(58);
        q.display();
        q.remove();
        System.out.println(q.remove());
        System.out.println(q.peek());
        q.display();
    }
}

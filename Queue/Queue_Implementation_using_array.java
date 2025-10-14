package Queue;

/*
        Description :-
            We have to implement Queue using Array...

        Approach :-
            > we have to maintain two pointers :
                i. front - for removing elements from the front.
                ii, rear - for adding elements in the back.
            > so at the time of adding any element just increase the rear,
                and at the time of deletion increase the front pointer.
*/

public class Queue_Implementation_using_array {

    public static class Queue{
        int front = -1;
        int rear = -1;
        int size = 0;
        int[] arr = new int[10];

        public void add(int val){
            if(rear == arr.length - 1){
                System.out.println("Queue is full...");
                return;
            } else if (front == -1) {
                arr[0] = val;
                front = 0;
                rear = 0;
                size++;
            }else {
                arr[++rear] = val;
                size++;
            }
        }

        public int remove(){
            if(front == -1){
                System.out.println("Queue is Empty, Nothing to delete...");
                return -1;
            }else {
                front += 1;
                size--;
                return arr[front-1];
            }
        }

        public int peek(){
            if(front == -1){
                System.out.println("Queue is Empty, Nothing to show...");
                return -1;
            }
            else return arr[front];
        }

        public int size(){
            return size;
        }

        public boolean isEmpty(){
            if(size == 0) return true;
            return false;
        }

        public void display(){
            if(front == -1){
                System.out.println("Queue is Empty, Nothing to display...");
                return;
            }
            for (int i = front; i <= rear; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.display();
        q.remove();
        System.out.println(q.remove());
        System.out.println(q.peek());
        q.display();
    }
}


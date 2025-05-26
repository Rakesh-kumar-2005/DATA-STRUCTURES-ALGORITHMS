package Queue;

class MyCircularQueue {
    int[] arr;
    int front;
    int rear;
    int n;

    public MyCircularQueue(int k) {
        n = k;
        arr = new int[n];
        rear = front = - 1;
    }

    public boolean enQueue(int value) {

        if (front == - 1) {
            front = rear = 0;
            arr[rear] = value;
            return true;
        }

        if ((rear + 1) % n == front) {
            return false;
        }

        rear = (rear + 1) % n;
        arr[rear] = value;
        return true;
    }

    public boolean deQueue() {

        if (front == - 1) {
            return false;
        }

        if (front == rear) {
            front = rear = - 1;
            return true;
        }

        front = (front + 1) % n;
        return true;

    }

    public int Front() {

        if (front == - 1) {
            return - 1;
        }

        return arr[front];

    }

    public int Rear() {

        if (rear == - 1) {
            return - 1;
        }

        return arr[rear];
    }

    public boolean isEmpty() {

        if (front == - 1) {
            return true;
        }

        return false;

    }

    public boolean isFull() {

        if ((rear + 1) % n == front) {
            return true;
        }

        return false;

    }

}

public class Design_Circular_Queue_Using_Array {

    public static void main(String[] args) {

        MyCircularQueue queue = new MyCircularQueue(3);

        System.out.println(queue.enQueue(2));
        System.out.println(queue.enQueue(3));
        System.out.println(queue.enQueue(4));

        System.out.println(queue.Rear());
        System.out.println(queue.isFull());

        System.out.println(queue.deQueue());
        System.out.println(queue.enQueue(4));
        System.out.println(queue.Rear());

    }

}
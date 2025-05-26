package Queue;

/*

Description:
    -> This program implements a fixed-size circular queue using an array in Java...
    -> It supports standard queue operations: enqueue, dequeue, front, rear, isEmpty, and isFull...
    -> The circular nature allows efficient utilization of space by wrapping around when the end of the array is reached...

Problem Statement:
    -> Design a circular queue with the following functionalities:
        - enQueue(value): Insert an element into the circular queue...
        - deQueue(): Delete an element from the circular queue...
        - Front(): Get the front item from the queue...
        - Rear(): Get the last item from the queue...
        - isEmpty(): Checks whether the circular queue is empty...
        - isFull(): Checks whether the circular queue is full...

Approach:
    > Circular Queue Using Array:
        -> Utilize a fixed-size array to store elements...
        -> Maintain two pointers, `front` and `rear`, to track the start and end positions...
        -> Use modulo operation to wrap around the indices when they reach the end...

Algorithm Steps:
    -> Initialization:
        1. Define an array `arr` of size `n` to store queue elements...
        2. Initialize `front` and `rear` to -1 indicating an empty queue...

    -> enQueue(value):
        1. If the queue is full ((rear + 1) % n == front), return false...
        2. If the queue is empty (front == -1), set front = rear = 0...
        3. Else, set rear = (rear + 1) % n...
        4. Insert the value at arr[rear] and return true...

    -> deQueue():
        1. If the queue is empty (front == -1), return false...
        2. If there's only one element (front == rear), reset front = rear = -1...
        3. Else, set front = (front + 1) % n...
        4. Return true...

    -> Front():
        1. If the queue is empty (front == -1), return -1...
        2. Else, return arr[front]...

    -> Rear():
        1. If the queue is empty (rear == -1), return -1...
        2. Else, return arr[rear]...

    -> isEmpty():
        1. Return true if front == -1, else false...

    -> isFull():
        1. Return true if (rear + 1) % n == front, else false...

Key Characteristics:
    -> Efficiently utilizes space by wrapping around the array...
    -> All operations are performed in O(1) time complexity...
    -> Avoids the need to shift elements after dequeue operations...

Time and Space Complexity:
    -> Time Complexity: O(1) for all operations...
    -> Space Complexity: O(n), where n is the size of the queue...

Demonstration:
    -> Initialize a queue with size 3...
    -> Perform a series of enQueue and deQueue operations...
    -> Observe the behavior of front, rear, isEmpty, and isFull methods...

*/

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

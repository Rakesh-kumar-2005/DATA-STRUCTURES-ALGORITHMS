package Queue;

/*

        Description :-
            Let's say we have a queue with elements [1, 2, 3, 4, 5, 6].
            We split this queue into two halves:
                First half: [1, 2, 3].
                Second half: [4, 5, 6].
            So, the interleaved queue would be [1, 4, 2, 5, 3, 6].

        Approach :-
            We can do it easily by array.
            But here we can learn more about Queue's usage...
            So, first of all we reverse the first half of the element using a stack,
            then add into the queue,then reverse the next half elements and add to the queue alternatively,
            then reverse the whole Queue...

*/

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Reordering_Queue {

    private static void reorder(Queue<Integer> q){
        Stack<Integer> st = new Stack<>();
        int size = q.size();

        for (int i = 0; i < size/2; i++){
            st.push(q.remove());
        }

        while (!st.isEmpty()){
            q.add(st.pop());
        }

        for (int i = 0; i < size/2; i++){
            st.push(q.remove());
        }

        while (!st.isEmpty()){
            q.add(st.pop());
            q.add(q.remove());
        }

        while (!q.isEmpty()) {
            st.push(q.remove());
        }

        while (!st.isEmpty()){
            q.add(st.pop());
        }
    }

    private static void printQueue(Queue<Integer> q){
        int size = q.size();

        while (size != 0){
            System.out.print(q.peek() + " ");
            q.add(q.remove());
            size--;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.add(6);
        System.out.println("Original Queue is : ");
        printQueue(q);
        reorder(q);
        System.out.println("After interleaved Queue is : ");
        printQueue(q);
    }
}

package Queue;
import java.util.Stack;

/*
        Description :-
            We have to implement Queue using Stacks...

        Approach :-
            > We have to use 2 stacks (one to maintain and another as helper to perform any kind of operation)...
            > The following method is more suitable for pop() and peek() operation.
            > so,when we add more than one elements into the stack, we reverse it by the helper stack,
                so that we have direct access to the first element for constant time pop() and peek() operation...
*/

class myQueue{
    Stack<Integer> st = new Stack<>();
    Stack<Integer> helper= new Stack<>();

    //add method...
    public void add(int val){
        if(st.isEmpty()) st.push(val);
        else {
            st.push(val);
            while (st.size() > 1){
                helper.push(st.peek());
            }
            while (!helper.isEmpty()){
                st.push(helper.pop());
            }
        }
    }

    // Remove method...
    public int remove() {
        return st.pop();
    }

    // Peek method...
    public int peek() {
        return st.peek();
    }

    // isEmpty method...
    public boolean isEmpty(){
        return st.isEmpty();
    }

    // Display method...
    public void display(){
        while (!st.isEmpty()){
            System.out.print(st.peek());
            helper.push(st.pop());
        }
        while (!helper.isEmpty()){
            st.push(helper.pop());
        }
        System.out.println();
    }
}
public class Queue_implementation_using_Stacks {
    public static void main(String[] args) {
        myQueue q = new myQueue();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.display();
        System.out.println(q.remove());
        q.display();
        System.out.println(q.peek());
        System.out.println(q.isEmpty());
    }
}
package Queue;

/*
    Description :-
        Given an array of integers and an integer 𝐾, you need to find the first negative integer
        in each window of size 𝐾. If a window does not contain a negative integer, print 0 for that window.

        > Example :-
            Consider the array [−10,20,−30,−40,50,60,−70,80,90] and 𝐾 = 3.

            The output should be = [−10,−30,−30,−40,−70,−70,−70].


    Approach :-
        Initialization:
            > A queue (q) is created to store the indices of negative numbers in the array.
            > An array (ans) is created to store the results for each sub array of size k.

        Populating the Queue:
            > The first loop iterates through the entire array. For each negative number found,
                its index is added to the queue.

        Processing Sub arrays:
            > The second loop iterates through the possible starting indices of sub arrays of size k (from 0 to n-k).

        Inside this loop:
            > The code checks if the front of the queue (q.peek()) is out of the current sub array's range.
                If it is, that index is removed from the queue.
            > It checks if there is a valid negative number within the current sub array.
                If so, it assigns the value of that negative number to the answer array. If no negative number exists in the current sub array
                it assigns 0.


*/


import java.util.ArrayDeque;
import java.util.Queue;

public class First_negative_in_each_window {

    private static int[] firstNegative(int[] arr,int n,int k){
        Queue<Integer> q = new ArrayDeque<>();
        int[] ans = new int[n-k+1];

        for(int i = 0; i < n; i++){
            if(arr[i] < 0) q.add(i);
        }

        for(int i = 0; i < n-k+1; i++) {
            if (!q.isEmpty() && q.peek() < i) {
                q.remove();
            }

            if (!q.isEmpty() && i + k - 1 >= q.peek()) {
                ans[i] = arr[q.peek()];
            } else ans[i] = 0;
        }
        return ans;
    }

    private static void print(int[] arr){
        for(int num : arr){
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {-1,3,4,-10,4,-23,493,-46,344,-3,432,-43};
        System.out.println("Original Array is : ");
        print(arr);
        int key = 3;
        int[] ans = firstNegative(arr,arr.length,key);
        System.out.println("First negative number in each " + key + " window are : ");
        print(ans);
    }
}

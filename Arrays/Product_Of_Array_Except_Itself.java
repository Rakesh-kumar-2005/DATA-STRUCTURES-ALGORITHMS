package Arrays;

/*
        Description :-
            Given an array of integers, create a new array where each element is the product of
            all numbers in the original array except the number at that position, without using division...

        Approach :-
            > The solution implements a clever approach using prefix and suffix products...
            > It first creates a prefix array that stores the running product of all elements before
                    the current index, with prefix[0] initialized to 1 since there are no elements before
                    the first position...
            > Then it builds a suffix array starting from the end, storing products of all elements after
                    each index, with suffix[n-1] set to 1...
            > Finally, for each position i, it multiplies the corresponding prefix[i] and suffix[i] values to
                    obtain the product of all elements except the one at position i...
            > Time Complexity = O(n) because of single traversal through the array 3 times...
            > Space Complexity = O(n) because we only used 2 arrays to keep prefix and suffix...
*/

import java.util.Arrays;

public class Product_Of_Array_Except_Itself {

    private static int[] product(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] ans = new int[n];
        prefix[0] = 1;

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * arr[i - 1];
        }

        suffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * arr[i + 1];
        }

        for (int i = 0; i < n; i++) {
            ans[i] = prefix[i] * suffix[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int[] ans = product(arr);

        System.out.println(Arrays.toString(ans));
    }
}

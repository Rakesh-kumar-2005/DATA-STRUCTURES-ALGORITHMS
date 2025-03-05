package Arrays;

/*
        Description :-
            Given an array containing only 0s, 1s, and 2s, sort it in-place without
            using built-in sorting functions. The goal is to arrange the elements in
            increasing order efficiently...

        Approach :-
            > The problem requires sorting an array containing only 0s, 1s, and 2s without
                using built-in sorting functions...
            > The approach follows the Dutch "National Flag algorithm", which efficiently
                sorts the array in O(n) time complexity with constant space...
            > Three pointers are used: i for the position of 0s, j for scanning the array,
                and k for the position of 2s. If arr[j] is 0, it is swapped with arr[i], and both
                i and j are incremented. If arr[j] is 1, j is simply incremented. If arr[j] is 2,
                it is swapped with arr[k], and k is decremented...
            > This ensures an in-place sorting of the array without additional space...
*/

import java.util.Arrays;

public class Sort_Colors {

    private static void sortColors(int[] arr) {
        int i = 0;
        int j = 0;
        int k = arr.length - 1;

        while (j <= k) {
            if (arr[j] == 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j++;
            } else if (arr[j] == 1) {
                j++;
            } else {
                int temp = arr[k];
                arr[k] = arr[j];
                arr[j] = temp;
                k--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 0, 2, 1, 1, 0, 0, 1, 2, 0};
        System.out.println("Before sorting the colors :");
        System.out.println(Arrays.toString(arr));
        sortColors(arr);
        System.out.println("After sorting the colors");
        System.out.println(Arrays.toString(arr));
    }
}

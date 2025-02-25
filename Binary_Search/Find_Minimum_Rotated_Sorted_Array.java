package Binary_Search;

import java.util.Arrays;

/*
        Description :-
            We have to find the minimum element in the given rotated sorted Array...
            But we have condition - Time complexity shouldn't exceed O(log(n))...

        Approach :-
            > So,the typical left and right pointer,then iterate until left <= right...
            > then we have to check the sorted part by checking the comparing the left
                with the mid value and if it satisfies then we have to check for the remaining
                space and vice-verse for the else condition...
*/

public class Find_Minimum_Rotated_Sorted_Array {

    private static int findMin(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int ans = 2147483647;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[left] <= arr[right]) {
                ans = Math.min(arr[left], ans);
                break;
            }


            if (arr[mid] >= arr[left]) {
                ans = Math.min(ans, arr[left]);
                left = mid + 1;
            } else {
                ans = Math.min(ans, arr[mid]);
                right = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {6, 7, 1, 2, 3, 4, 5};
        System.out.println("Minimum element in the arr " + Arrays.toString(arr) + " is = " + findMin(arr));
    }
}

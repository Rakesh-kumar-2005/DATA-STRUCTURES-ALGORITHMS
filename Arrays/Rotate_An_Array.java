package Arrays;

/*

Description:
    -> This program rotates an array to the right by 'k' steps using the reversal algorithm.
    -> Rotation means shifting elements circularly, so elements that move past the last position
       come back to the beginning.

Problem Statement:
    Given an integer array 'arr' and an integer 'k',
    rotate the array to the right by 'k' steps.

Example:
    Input:  arr = [1, 2, 3, 4, 5, 6, 7], k = 3
    Output: [5, 6, 7, 1, 2, 3, 4]

Approach (Reversal Algorithm):
    1. Reverse the entire array.
       Example: [1, 2, 3, 4, 5, 6, 7] → [7, 6, 5, 4, 3, 2, 1]
    2. Reverse the first k elements.
       Example: [7, 6, 5, 4, 3, 2, 1] → [5, 6, 7, 4, 3, 2, 1]
    3. Reverse the remaining n-k elements.
       Example: [5, 6, 7, 4, 3, 2, 1] → [5, 6, 7, 1, 2, 3, 4]
    4. The array is now rotated by k steps.

Why k %= n?
    - If k is greater than the array length, rotating n times results in the same array.
    - Example: For arr = [1,2,3], k = 5 → rotate by (5 % 3 = 2).

Helper Functions:
    -> reverse(int[] arr, int i, int j):
         Swaps elements between indices i and j to reverse part of the array.
    -> display(int[] arr):
         Prints the array elements.

Time and Space Complexity:
    -> Time Complexity: O(n), since each element is reversed at most twice.
    -> Space Complexity: O(1), in-place rotation without extra array.

Example Walkthrough:
    Input: arr = [1, 2, 3, 4, 5, 6, 7], k = 3
    Steps:
       Reverse whole: [7, 6, 5, 4, 3, 2, 1]
       Reverse first 3: [5, 6, 7, 4, 3, 2, 1]
       Reverse last 4: [5, 6, 7, 1, 2, 3, 4]
    Output: [5, 6, 7, 1, 2, 3, 4]

*/

public class Rotate_An_Array {

    private static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    private static void rotate(int[] arr, int k) {
        int n = arr.length;
        k %= n;
        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);

    }

    private static void display(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Original Array: ");
        display(arr);

        rotate(arr, 3);
        System.out.println("After Rotating the Array with the key 3: ");
        display(arr);

    }

}

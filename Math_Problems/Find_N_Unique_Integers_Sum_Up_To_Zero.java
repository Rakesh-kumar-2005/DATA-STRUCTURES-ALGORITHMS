package Math_Problems;

/*

Description:
    -> This program generates an array of `n` unique integers such that their sum equals zero...
    -> It provides two different methods (`sumZero1` and `sumZero2`) to construct the required array...
    -> Both methods ensure that the resulting array satisfies the uniqueness and sum conditions...

Problem Statement:
    -> Given an integer `n`, find `n` unique integers whose sum is equal to zero...
    -> Return the array of such integers...

Approach:
    > Method 1 (Arithmetic Symmetry):
        -> Construct the array using a formula `arr[i] = i * 2 - n + 1`...
        -> This generates numbers symmetrically distributed around zero...
        -> Ensures that their sum equals zero...

    > Method 2 (Adjustment with First Element):
        -> Place a calculated starting value at index `0`: `arr[0] = n * (1 - n) / 2`...
        -> Fill the remaining positions with sequential integers starting from `1`...
        -> The chosen first element ensures the sum of the entire array equals zero...

Algorithm Steps:
    -> Initialization:
        1. Define an integer array `arr` of size `n`...
    
    -> Method 1:
        1. Loop from `i = 0` to `n-1`...
        2. Assign `arr[i] = i * 2 - n + 1`...
        3. This balances positive and negative numbers...
    
    -> Method 2:
        1. Compute the first element using formula: `arr[0] = n * (1 - n) / 2`...
        2. Fill remaining array with `i` for `i = 1` to `n-1`...
        3. Ensures the total sum is zero...
    
    -> Verification:
        1. A helper method `sumOfElements()` calculates the sum of array elements...
        2. Another helper method `displayArray()` prints the array for demonstration...

Key Characteristics:
    -> Two different yet correct approaches to solve the problem...
    -> Guarantees uniqueness of integers in the array...
    -> Works for any positive integer `n`...
    -> Simple arithmetic ensures correctness and efficiency...

Time and Space Complexity:
    -> Time Complexity: O(n), as each element is computed once...
    -> Space Complexity: O(n), as an array of size `n` is required...

Demonstration:
    -> Input: n = 5
    -> Method 1 Output Array: [-4, -2, 0, 2, 4]
       → Sum = -4 + -2 + 0 + 2 + 4 = 0
    -> Method 2 Output Array: [-10, 1, 2, 3, 4]
       → Sum = -10 + 1 + 2 + 3 + 4 = 0
    -> Both methods return valid solutions with unique integers summing to zero...

*/

public class Find_N_Unique_Integers_Sum_Up_To_Zero {

    private static int[] sumZero1(int n) {

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i * 2 - n + 1;
        }

        return arr;
    }

    private static int[] sumZero2(int n) {
        int[] arr = new int[n];
        arr[0] = n * (1 - n) / 2;

        for (int i = 1; i < n; i++) {
            arr[i] = i;
        }

        return arr;
    }

    private static void displayArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static int sumOfElements(int[] arr) {

        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        return sum;

    }

    public static void main(String[] args) {

        int n = 5;

        int[] arr1 = sumZero1(n);
        int[] arr2 = sumZero2(n);

        System.out.println("Result Array from method 1: ");
        displayArray(arr1);
        System.out.println("Sum of the elements of the above array is = " + sumOfElements(arr1));
        System.out.println("Result Array from method 2: ");
        displayArray(arr2);
        System.out.println("Sum of the elements of the above array is = " + sumOfElements(arr2));

    }
    
}

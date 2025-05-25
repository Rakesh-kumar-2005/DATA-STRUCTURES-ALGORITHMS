package Arrays;

/* 

Description:
    -> This program identifies the smallest missing positive integer from an unsorted array using a HashSet for efficient lookup...
    -> It operates by storing all elements in a HashSet and then incrementally checking for the first missing positive integer...

Problem Statement:
    -> Given an unsorted integer array `arr`...
    -> Find and return the smallest missing positive integer...

Approach:
    > HashSet-Based Lookup:
        -> Utilize a HashSet to store all elements from the array...
        -> Initialize a variable `ans` to 1, representing the smallest positive integer candidate...
        -> Increment `ans` until it is not found in the HashSet...

Algorithm Steps:
    -> Initialization:
        1. Create an empty HashSet `st`...
        2. Initialize `ans` to 1...

    -> Populate HashSet:
        1. Iterate through each element `i` in `arr`...
        2. Add `i` to the HashSet `st`...

    -> Find Missing Positive:
        1. While `st` contains `ans`, increment `ans` by 1...
        2. Once `ans` is not found in `st`, it is the smallest missing positive integer...

    -> Return Result:
        1. Return the value of `ans`...

Key Characteristics:
    -> Efficient lookup using HashSet provides O(1) average-case time complexity for contains operation...
    -> Simple and intuitive approach suitable for small to medium-sized datasets...
    -> Does not modify the original array...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the number of elements in the array...
    -> Space Complexity: O(n), due to the additional space used by the HashSet...

Demonstration:
    -> For input array: {1, 2, 0, 3, -1, 4, 5, 6}...
    -> The HashSet will contain: {0, 1, 2, 3, 4, 5, 6, -1}...
    -> The smallest missing positive integer is 7...

*/

import java.util.HashSet;

public class First_Missing_Positive {

    private static int firstMissingPositive(int[] arr) {

        HashSet<Integer> st = new HashSet<>();
        int ans = 1;

        for (int i : arr) {
            st.add(i);
        }

        while (st.contains(ans)) {
            ans++;
        }

        return ans;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 0, 3, - 1, 4, 5, 6};
        System.out.println("First missing positive number in the array is : " + firstMissingPositive(arr));
    }

}

package Arrays;

/*

Description:
    -> This program implements a class to efficiently find the number of pairs across two arrays whose sum equals a given target...
    -> It supports dynamic updates to the second array and allows repeated queries for target sums...

Problem Statement:
    -> You are given two integer arrays: nums1 and nums2...
    -> Implement a class that supports:
        a. Updating an element in nums2 by adding a given value to it...
        b. Counting the number of pairs (i, j) such that nums1[i] + nums2[j] equals a given target sum...

Approach:
    > Preprocessing with HashMap:
        -> Store frequencies of elements in nums2 using a HashMap...
        -> This allows O(1) lookup when checking how many elements in nums2 can pair with an element in nums1 to form the target sum...

    > Update Operation (add):
        -> When updating an element in nums2:
            --> Decrease the frequency of the original value...
            --> Update the value at the given index...
            --> Increase the frequency of the new value...

    > Count Operation:
        -> For each element in nums1:
            --> Calculate the required complement value to reach the target...
            --> Add the frequency of that complement from the HashMap...

Algorithm Steps:
    -> Constructor:
        a. Store both arrays...
        b. Initialize the frequency map for nums2...

    -> add(index, val):
        a. Decrement frequency of old value at arr2[index]...
        b. Update arr2[index] by adding val...
        c. Increment frequency of the new value...

    -> count(tot):
        a. Initialize count = 0...
        b. For each element in arr1:
            --> Check how many times (tot - element) occurs in arr2 using the map...
            --> Add that to the count...

Key Characteristics:
    -> Efficient sum-count queries using HashMap-based frequency lookups...
    -> Supports dynamic updates to nums2 without reconstructing the entire structure...
    -> Separation of concerns via class-based design for clarity...

Time and Space Complexity:
    -> Constructor Time: O(n), where n is length of nums2...
    -> add() Time: O(1), constant time update...
    -> count() Time: O(m), where m is length of nums1...
    -> Space Complexity: O(n), for storing frequency map of nums2...

Demonstration:
    -> Input: nums1 = [1, 3, 2, 5], nums2 = [2, 1, 3, 4]
    -> Operation: add(1, 1) → nums2 becomes [2, 2, 3, 4]
    -> Operation: count(5)
        --> Valid pairs: (1,4), (3,2), (2,3), etc.
    -> Output: The number of pairs with sum 5 is : 3

*/

import java.util.Arrays;
import java.util.HashMap;

public class Finding_Pairs_With_A_Certain_Sum {

    static class FindSumPairs {

        int[] arr1;
        int[] arr2;
        HashMap<Integer, Integer> mp = new HashMap<>();

        public FindSumPairs(int[] nums1, int[] nums2) {
            arr1 = nums1;
            arr2 = nums2;

            for (int num : arr2) {
                mp.put(num, mp.getOrDefault(num, 0) + 1);
            }
        }

        public void add(int index, int val) {
            mp.put(arr2[index], mp.get(arr2[index]) - 1);
            arr2[index] += val;
            mp.put(arr2[index], mp.getOrDefault(arr2[index], 0) + 1);
        }

        public int count(int tot) {

            int count = 0;
            for (int num : arr1) {
                count += mp.getOrDefault(tot - num, 0);
            }

            return count;
        }

    }

    public static void main(String[] args) {

        int[] nums1 = {1, 3, 2, 5};
        int[] nums2 = {2, 1, 3, 4};

        FindSumPairs findSumPairs = new FindSumPairs(nums1, nums2);
        System.out.println("After adding 1 to the second array on index 1: ");

        findSumPairs.add(1, 1);
        System.out.println(Arrays.toString(findSumPairs.arr2));

        System.out.println("The number of pairs with sum 5 is : " + findSumPairs.count(5));
    }

}

package Arrays;

/*

Description:
    -> This program finds the largest lucky integer in an array...
    -> A lucky integer is defined as a number that appears in the array exactly as many times as its value...

Problem Statement:
    -> Given an integer array, find the largest lucky integer in the array...
    -> A lucky integer is an integer x such that the frequency of x in the array is exactly x...
    -> If no lucky integer exists, return -1...

Approach:
    > Method 1 (Using HashMap):
        -> Use a HashMap to count the frequency of each number in the array...
        -> Traverse the map to find the largest number x such that x appears exactly x times...

    > Method 2 (Using Frequency Array):
        -> Since the input values are within a known bound (e.g., ≤ 500), use a frequency array for constant-time access...
        -> Traverse the frequency array from the highest index downward to find the largest valid lucky integer...

Algorithm Steps:
    -> Step 1: Count the frequency of each element using either a HashMap or array...
    -> Step 2: Check for each key (or index) whether frequency == value...
    -> Step 3: Keep track of the maximum such lucky integer found...
    -> Step 4: If no lucky integer is found, return -1...

Key Characteristics:
    -> Method 1 is flexible for any input range using HashMap...
    -> Method 2 is optimized for bounded input with O(1) lookup...
    -> Both methods ensure the largest lucky number is returned...

Time and Space Complexity:
    -> Method 1:
        --> Time Complexity: O(n), where n is the number of elements...
        --> Space Complexity: O(n), for storing frequencies in HashMap...

    -> Method 2:
        --> Time Complexity: O(n), for counting and scanning...
        --> Space Complexity: O(1), fixed-size array of 501 elements...

Demonstration:
    -> Input: arr = {2, 2, 3, 4}
    -> Frequencies: 2 → 2 times, 3 → 1 time, 4 → 1 time
    -> Lucky integers: 2 (since it appears 2 times)
    -> Output: The maximum lucky integer which has the same frequency as its number is : 2

*/

import java.util.HashMap;

public class Find_Lucky_Integer_In_An_Array {

    private static int findLucky1(int[] arr) {

        HashMap<Integer, Integer> mp = new HashMap<>();
        int ans = - 1;

        for (int i : arr) {
            if (! mp.containsKey(i)) {
                mp.put(i, 1);
            } else {
                mp.put(i, mp.get(i) + 1);
            }
        }

        for (int num : mp.keySet()) {
            if (num == mp.get(num)) {
                ans = Math.max(ans, num);
            }
        }

        return ans;
    }

    private static int findLucky2(int[] arr) {

        int ans = - 1;
        int[] freq = new int[501];

        for (int i : arr) {
            freq[i]++;
        }

        for (int i = 500; i >= 1; i--) {
            if (freq[i] == i) {
                ans = i;
                break;
            }
        }

        return ans;

    }


    public static void main(String[] args) {

        int[] arr = {2, 2, 3, 4};

        System.out.println("From method 1 : ");
        System.out.println("The maximum lucky integer which has the same frequency as its number is : " + findLucky1(arr));

        System.out.println("From method 2 : ");
        System.out.println("The maximum lucky integer which has the same frequency as its number is : " + findLucky1(arr));

    }

}

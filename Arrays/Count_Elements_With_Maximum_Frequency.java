package Arrays;

/*

Description:
    -> This program counts the total number of elements in an array that appear with the maximum frequency...
    -> It uses a frequency array to track how many times each number appears, and then calculates how many
       elements share the highest frequency...

Problem Statement:
    -> Given an integer array `nums`, find the sum of frequencies of elements that occur with the maximum frequency...
    -> Example: If the most frequent element appears 4 times, and another element also appears 4 times,
       the answer should be 4 + 4 = 8...

Approach:
    > Frequency Counting:
        -> Use an auxiliary array `frequency[]` to count occurrences of each number (0–100 range assumed)...
        -> Traverse the array:
             - Increment the frequency of the current number...
             - Track the current maximum frequency `max`...
             - Update the result:
                 - If a new maximum is found, reset result to this frequency...
                 - If another number matches the maximum frequency, add its frequency to the result...
        -> Finally, return the result as the total count of maximum frequency elements...

Algorithm Steps:
    -> Initialization:
        1. Create an integer array `frequency[101]` initialized to 0...
        2. Initialize `max = 0` and `result = 0`...

    -> Traversal:
        1. For each element `num` in `nums`:
             - Increment `frequency[num]` by 1 (store in `currFrequency`)...
             - If `currFrequency > max`:
                  * Update `max = currFrequency`...
                  * Reset `result = currFrequency`...
             - Else if `currFrequency == max`:
                  * Add `currFrequency` to `result`...

    -> Return:
        1. Return `result` as the sum of counts of elements with maximum frequency...

Key Characteristics:
    -> Efficient frequency counting with constant-time updates...
    -> Uses a fixed-size auxiliary array (suitable since values are bounded by 0–100)...
    -> Handles multiple elements with the same maximum frequency correctly...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the array (single traversal)...
    -> Space Complexity: O(1), since frequency array size is fixed (101)...

Demonstration:
    -> Input: [1, 2, 2, 3, 3, 3, 4, 4, 4, 4]
    -> Frequency table:
         1 → 1
         2 → 2
         3 → 3
         4 → 4
       → Maximum frequency = 4
    -> Only number 4 has maximum frequency...
    -> Result = 4
    -> Output: "The maximum frequency elements in the array is : 4"

*/

public class Count_Elements_With_Maximum_Frequency {

    private static int maxFrequencyElements(int[] nums) {

        int[] frequency = new int[101];
        int max = 0;
        int result = 0;

        for (int num : nums) {

            int currFrequency = ++ frequency[num];

            if (currFrequency > max) {
                max = currFrequency;
                result = currFrequency;
            } else if (max == currFrequency) {
                result += currFrequency;
            }

        }

        return result;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        System.out.println("The maximum frequency elements in the array is : " + maxFrequencyElements(arr));

    }

}

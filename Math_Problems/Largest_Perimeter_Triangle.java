package Math_Problems;

/*

Description:
    -> This program finds the largest perimeter of a valid triangle 
       that can be formed using side lengths from a given integer array...
    -> A valid triangle must satisfy the Triangle Inequality Theorem:
         - For any three sides a, b, c:
           a + b > c
           b + c > a
           a + c > b
    -> Since the array is sorted in ascending order, we only need to check
       the condition: largest side < sum of the other two sides...

Problem Statement:
    -> Given an array of integers representing side lengths,
       return the largest perimeter of a non-degenerate triangle 
       (a triangle with positive area) that can be formed.
    -> If no valid triangle exists, return 0.

Approach:
    > Sorting + Greedy:
        -> Sort the array in ascending order...
        -> Start checking triplets from the largest side (end of array)...
        -> If lengths[i] < lengths[i-1] + lengths[i-2], 
           we found the largest valid perimeter (since sorted array ensures maximal perimeter)...
        -> Otherwise, continue checking smaller triplets...
        -> If no valid triangle is found, return 0...

Algorithm Steps:
    1. Sort the input array...
    2. Iterate from the last index down to index 2:
         - Let a = lengths[i], b = lengths[i-1], c = lengths[i-2]...
         - If (a < b + c), return a + b + c...
    3. If no valid triangle exists, return 0...

Key Characteristics:
    -> Greedy approach ensures the first valid triangle found has the largest perimeter...
    -> Efficient and simple logic after sorting...

Time and Space Complexity:
    -> Time Complexity: O(n log n) due to sorting...
    -> Space Complexity: O(1), no extra data structures used...

Demonstration:
    Example 1:
      Input: lengths = [1, 2, 1, 10]
      Sorted = [1, 1, 2, 10]
      Triplets checked:
        (10, 2, 1) → invalid (10 ≥ 2+1)
        (2, 1, 1) → valid → perimeter = 4
      Output: "The largest perimeter is : 4"

*/

import java.util.Arrays;

public class Largest_Perimeter_Triangle {

    private static int largestPerimeter(int[] lengths) {

        Arrays.sort(lengths);

        for (int i = lengths.length - 1; i >= 2; i--) {

            int a = lengths[i];
            int b = lengths[i - 1];
            int c = lengths[i - 2];

            if (a < b + c) {
                return a + b + c;
            }

        }

        return 0;

    }

    public static void main(String[] args) {

        int[] lengths = {1, 2, 1, 10};
        int largestPerimeter = largestPerimeter(lengths);

        System.out.println("The largest perimeter is : " + largestPerimeter);

    }

}

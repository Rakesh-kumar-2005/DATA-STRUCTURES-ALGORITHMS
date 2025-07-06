package Binary_Search;

/*

Description:
    -> This program finds the row in a binary matrix that contains the maximum number of 1's...
    -> Each row in the matrix is sorted in non-decreasing order (i.e., all 0's appear before 1's)...
    -> It uses binary search to efficiently locate the first occurrence of 1 in each row...

Problem Statement:
    -> Given a binary matrix of size n x m where each row is sorted in non-decreasing order...
    -> Find the index of the row that contains the maximum number of 1's...
    -> If multiple rows have the same maximum number of 1's, return the one with the smallest index...

Approach:
    > Binary Search (Lower Bound):
        -> For each row, perform a binary search to find the first occurrence (lower bound) of 1...
        -> The number of 1's in the row is equal to (m - index of first 1)...

    > Tracking Maximum:
        -> Keep track of the row index with the maximum number of 1's seen so far...
        -> Update the result whenever a new row has more 1's than the previous max...

Algorithm Steps:
    -> Step 1: Initialize `maxIndex = -1` and `maxCount = 0`...
    -> Step 2: For each row in the matrix:
        a. Find the first index of 1 using binary search...
        b. Calculate the count of 1's as `m - lowerBoundIndex`...
        c. If current row has more 1's than `maxCount`, update `maxCount` and `maxIndex`...
    -> Step 3: Return `maxIndex`...

Key Characteristics:
    -> Leverages binary search for optimal row-wise 1 detection...
    -> Handles sorted binary rows efficiently in logarithmic time...
    -> Ensures the result is the row with the highest number of 1's and smallest index in case of ties...

Time and Space Complexity:
    -> Time Complexity: O(n * log m), where n = number of rows and m = number of columns...
    -> Space Complexity: O(1), using constant extra space...

Demonstration:
    -> Input Matrix:
       {0, 0, 1, 1}
       {0, 0, 0, 1}
       {0, 1, 1, 1}
       {1, 1, 1, 1}
    -> Output: The Row with maximum number of 1's = 3

*/

public class Row_With_Maximum_ones {

    private static int lowerBound(int[] arr, int n, int x) {

        int low = 0;
        int high = n - 1;
        int ans = 0;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (arr[mid] >= x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private static int rowWithMax1s(int[][] arr, int n, int m) {

        int maxIndex = - 1;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            int currCount = lowerBound(arr[i], m, 1);

            if (currCount > maxCount) {
                maxCount = currCount;
                maxIndex = i;
            }

        }

        return maxIndex;
    }

    public static void main(String[] args) {

        int n = 4;
        int m = 4;

        int[][] arr = {{0, 0, 1, 1},
            {0, 0, 0, 1},
            {0, 1, 1, 1},
            {1, 1, 1, 1}};

        int rowWithMaxOnes = rowWithMax1s(arr, n, m);
        System.out.println("The Row with maximum number of 1's = " + rowWithMaxOnes);

    }

}

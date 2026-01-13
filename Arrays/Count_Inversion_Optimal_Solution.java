package Arrays;

/*

    Description:
        Following program demonstrates the solution to the "Count Inversions" problem
        using an Optimal Merge Sort Approach...

    Problem Statement:
        -> Given an array of integers...
        -> An inversion occurs when elements at positions i and j satisfy: i < j and arr[i] > arr[j]...
        -> The task is to count the total number of inversions in the array...
        -> Inversions indicate how far the array is from being sorted...
        -> A sorted array has zero inversions, while a reverse sorted array has maximum inversions...

    Approach:
        > Modified Merge Sort with Inversion Counting:
            i. Use divide and conquer methodology of merge sort...
            ii. Count inversions during the merge step...
            iii. Split the inversion counting into three parts:
                a. Inversions in left sub-array...
                b. Inversions in right sub-array...
                c. Split inversions across the two sub-arrays...
            iv. Combine counts from all three sources...

    Algorithm Steps:
        -> Recursive Merge Sort Implementation:
            1. Divide array into two halves...
            2. Recursively count inversions in left half...
            3. Recursively count inversions in right half...
            4. Count split inversions during merge operation...
            5. Return sum of all three counts...
        -> Modified Merge Procedure:
            1. Create temporary arrays for left and right portions...
            2. Initialize counters and indices...
            3. Compare elements from both arrays...
            4. When right element is smaller than left:
               - Add (length1 - i) to inversion count...
               - This counts all remaining elements in left array as contributing to inversions...
            5. Merge the arrays in sorted order...

    Key Characteristics:
        -> Efficiently counts inversions in O(N log N) time...
        -> Simultaneously sorts the array while counting inversions...
        -> Handles large arrays effectively...
        -> Leverages the divide-and-conquer paradigm...

        > Counting Mechanism:
            -> Uses the property of merge sort to count split inversions efficiently...
            -> When an element from right sub-array is placed before elements in left sub-array,
               all remaining elements in left sub-array form inversions...
            -> Avoids comparing each pair explicitly...

    Time and Space Complexity:
        -> Time Complexity: O(N log N) where N is the length of the array...
        -> Space Complexity: O(N) for the temporary arrays used during merging...

*/

public class Count_Inversion_Optimal_Solution {

    private static int merge(int[] arr, int low, int mid, int high) {

        int length1 = mid - low + 1;
        int length2 = high - mid;

        int[] left = new int[length1];
        int[] right = new int[length2];

        for (int i = 0; i < length1; i++) {
            left[i] = arr[low + i];
        }

        for (int i = 0; i < length2; i++) {
            right[i] = arr[mid + 1 + i];
        }

        int i = 0;
        int j = 0;
        int idx = low;
        int count = 0;

        while (i < length1 && j < length2) {

            if (left[i] <= right[j]) {
                arr[idx++] = left[i++];
            } else {
                count += (length1 - i);
                arr[idx++] = right[j++];
            }
        }

        while (i < length1) {
            arr[idx++] = left[i++];
        }

        while (j < length2) {
            arr[idx++] = right[j++];
        }

        return count;
    }

    private static int mergeSort(int[] arr, int low, int high) {

        int count = 0;

        if (low < high) {
            int mid = low + (high - low) / 2;

            count += mergeSort(arr, low, mid);
            count += mergeSort(arr, mid + 1, high);

            count += merge(arr, low, mid, high);
        }
        
        return count;
    }

    private static int countInversion(int[] arr) {
        int[] temp = arr;
        return mergeSort(temp, 0, temp.length - 1);
    }

    public static void main(String[] args) {
        
        int[] arr = {8, 7, 6, 5, 4, 3, 2, 1};
        int count = countInversion(arr);
        
        System.out.println("Total number of inversions is = " + count);

    }

}

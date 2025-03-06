package Arrays;

/*
        Description :-
            Given a rotated sorted array and a target sum, we need to determine if there
            exists a pair of elements whose sum equals the target. A rotated sorted
            array is an originally sorted array that has been shifted at some pivot point.

        Approach :-
            > To solve this, we first find the pivot—the index where the array is rotated—by
                checking where the order breaks...
            > Using a two-pointer approach, we set low at the smallest element (pivot + 1) and
                high at the largest element (pivot). We then check the sum of these two elements...
            > If the sum matches the target, we print the pair. If the sum is smaller than the
                target, we move low forward; if larger, we move high backward...
            > This process continues until low meets high or we find the required pair...
            > Time Complexity = O(n)...
            > Space Complexity = O(1)...

         Another Approach :-
            > Instead of using the two-pointer approach, we can solve this problem using a HashSet...
            > The idea is to iterate through the array and check if the complement (i.e., target - arr[i])
                already exists in the set. If it does, we found the required pair. If not, we add the
                current element to the set and continue...
            > It would've taken O(n) space complexity with O(n) time complexity...
*/

public class Pair_Sum_In_Rotated_Sorted_Array {

    private static int findPivot(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return n - 1;
    }

    private static boolean findSum(int[] arr, int target) {
        int n = arr.length;
        int pivot = findPivot(arr, n);

        int low = (pivot + 1) % n;
        int high = pivot;

        while (low != high) {
            int currSum = arr[low] + arr[high];

            if (currSum == target) {
                System.out.println("The target is the sum of : " + arr[low] + " and " + arr[high]);
                return true;
            }

            if (currSum < target) {
                low = (low + 1) % n;
            } else {
                high = (high + n - 1) % n;
            }
        }

        System.out.println("Target sum couldn't found in the array...");
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {7, 8, 9, 10, 33, 54, 1, 2, 3, 4, 5};
        int target = 7;
        findSum(arr, target);
    }

}

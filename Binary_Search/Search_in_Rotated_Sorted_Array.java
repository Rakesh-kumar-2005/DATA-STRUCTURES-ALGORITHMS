package Binary_Search;

/*
        Description :-
            We have to find the index of the given target in the rotated sorted Array...
            But we have condition that the time complexity shouldn't exceed O(log(n))...

        Approach :-
            > From the condition we must know that we have to use binary search...
            > So,the typical left and right pointer,then iterate until left <= right...
            > then we have to check the sorted part by checking the comparing the left
                with the mid-value and after that check for the target if it lies between that
                section and update left and right value according to it...
*/

public class Search_in_Rotated_Sorted_Array {

    public static int search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) return mid;

            if (arr[left] <= arr[mid]) {
                if (target >= arr[left] && target <= arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target >= arr[mid] && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {66, 77, 88, 99, 11, 22, 33, 44, 55};
        int target = 44;
        System.out.println("The index of the target " + target + " is : " + search(arr, target));
    }
}

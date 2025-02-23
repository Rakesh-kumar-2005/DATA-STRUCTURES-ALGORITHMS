package Binary_Search;

/*
        Description :-
            We have to check for the occurrence of the given target in the rotated sorted Array...
            But we have duplicates elements in the array...

        Approach :-
            > So,the typical left and right pointer,then iterate until left <= right...
            > then we have to check the sorted part by checking the comparing the left
                with the mid value and after that check for the duplicate elements and
                decrease our search space ,then check for the target if it lies between
                that section and update left and right value according to it...
*/

public class Search_in_Rotated_Sorted_Array_II {

    private static boolean search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) return true;

            if (arr[left] == arr[mid] && arr[mid] == arr[right]) {
                left++;
                right--;
                continue;
            }

            if (arr[mid] >= arr[left]) {
                if (target <= arr[mid] && target >= arr[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target >= arr[mid] && arr[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {11, 11, 33, 33, 44, 44, 44, 55, 55, 88, 99};
        int target = 44;
        System.out.println("The occurrence of the target " + target + " in the array is : " + search(arr, target));
    }
}

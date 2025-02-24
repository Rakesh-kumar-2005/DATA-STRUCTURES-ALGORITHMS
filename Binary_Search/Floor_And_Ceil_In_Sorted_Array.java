package Binary_Search;

/*
        Description :-
            We have to check for the floor and ceil value of the given target in the sorted Array...
            floor value - Minimum but greater than or equal to the target in the array
            ceil value - Maximum but Less than or equal to the target in the array

        Approach :-
            > So,the typical left and right pointer,then iterate until left <= right...
            > Then we have to check the sorted part by checking the comparing the left
                with the mid-value(if it's <= target) and assign that value to the floor,
                then move the left pointer to the mid + 1...
            > Just like floor, for ceil value comparison between the mid and target
                (if it's >= target) , then move the right pointer to the mid - 1;
*/

public class Floor_And_Ceil_In_Sorted_Array {

    public static int[] getFloorAndCeil(int[] a, int x) {
        int[] arr = new int[2];
        arr[0] = floor(a, x);
        arr[1] = ceil(a, x);
        return arr;
    }

    private static int floor(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        int floor = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] <= target) {
                floor = arr[mid];
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return floor;
    }

    private static int ceil(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        int ceil = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                ceil = arr[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ceil;
    }

    public static void main(String[] args) {
        int[] arr = {13, 23, 34, 54, 875, 4545, 9979};
        int target = 54;
        int[] ans = getFloorAndCeil(arr, target);
        System.out.println("The floor value of the target " + target + " is = " + ans[0]);
        System.out.println("The ceil value of the target " + target + " is = " + ans[1]);
    }
}

package Binary_Search;

/*
        Description :-
            We have to find the index to insert the given target in the sorted Array...
            But we have condition that the time complexity shouldn't exceed O(log(n))...

        Approach :-
            > From the condition we must know that we have to use binary search...
            > So,the typical left and right pointer,then iterate until left <= right...
            > Then we have to compare every mid-value with the given element and reduce
                our search space into it's half-length...
*/

public class Search_Insertion_Position {

    private static int searchInsert(int[] nums, int target) {
        int st = 0;
        int ed = nums.length - 1;

        while (st <= ed) {
            int mid = st + (ed - st) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                st = mid + 1;
            } else {
                ed = mid - 1;
            }
        }
        return st;
    }

    public static void main(String[] args) {
        int[] arr = {22, 66, 88, 123, 764, 3356};
        int element = 35;
        System.out.println("Correct index for the element " + element + " is : " + searchInsert(arr, element));
    }
}

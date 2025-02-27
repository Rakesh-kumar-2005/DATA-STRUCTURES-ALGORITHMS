package Binary_Search;

/*
        Description :-
            We have to find out the single element in the sorted array...
            Given - Others are exactly present twice...
            But we have condition - Time complexity shouldn't exceed O(log(n))...

        Approach :-
            > So,the typical low and high pointer,then iterate until low <= high...
            > then we have to check the index's value if it's odd and array[mid] same as the next
                element then the single OR the index is even and array[mid] same as the previous
                element then the single element must exist before this. Otherwise, it's after this...
*/

public class Find_The_Single_Element_In_Sorted_Array {

    private static int singleNonDuplicate(int[] arr) {
        int n = arr.length - 1;
        if (arr.length == 1) return arr[0];
        if (arr[0] != arr[1]) return arr[0];
        if (arr[n] != arr[n - 1]) return arr[n];
        int low = 1;
        int high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] != arr[mid - 1] && arr[mid] != arr[mid + 1]) {
                return arr[mid];
            }

            if ((mid % 2 == 0 && arr[mid] == arr[mid + 1]) || (mid % 2 == 1 && arr[mid] == arr[mid - 1])) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7};
        System.out.println("The single element in the sorted array is = " + singleNonDuplicate(arr));
    }
}

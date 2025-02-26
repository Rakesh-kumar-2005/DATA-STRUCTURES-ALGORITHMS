package Binary_Search;

import java.util.ArrayList;
import java.util.List;

/*
        Description :-
            We have to find out the how many times the List has been rotated...
            But we have condition - Time complexity shouldn't exceed O(log(n))...

        Approach :-
            > So,the typical left and right pointer,then iterate until left <= right...
            > then we have to check the sorted part by checking the comparing the left
                with the mid-value and we have to compare the ans and idx variable with
                the smallest, index at that time and update the ans and idx variable and
                if it satisfies then we have to check for the remaining space and vice-verse
                for the else condition...
*/

public class Number_Of_Rotation_In_The_List {

    private static int findKRotation(List<Integer> arr) {
        int left = 0;
        int right = arr.size() - 1;
        int idx = -1;
        int ans = Integer.MAX_VALUE;

        while (left <= right) {

            if (arr.get(left) <= arr.get(right)) {
                if (arr.get(left) < ans) {
                    ans = arr.get(left);
                    idx = left;
                    break;
                }
            }

            int mid = (left + right) / 2;

            if (arr.get(left) <= arr.get(mid)) {
                if (ans > arr.get(left)) {
                    ans = arr.get(left);
                    idx = left;
                }
                left = mid + 1;
            } else {
                if (ans > arr.get(mid)) {
                    ans = arr.get(mid);
                    idx = mid;
                }
                right = mid - 1;
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(6);
        arr.add(7);
        arr.add(8);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);

        System.out.println("The List has been rotated : " + findKRotation(arr) + " times");
    }
}

package Binary_Search;

/*

        Description :-
            We have to find out the Peak element in the array...
            Peak element - a element which is strictly greater than it's neighbours...
            But we have condition - Time complexity shouldn't exceed O(log(n))...

        Approach :-
            > So,the typical low and high pointer,then iterate until low <= high...
            > We have to get the mid-index ,then check for its neighbours ,if it satisfies
                the condition then return it's value else check for if it's less than the next
                element then then update low otherwise update high...
                
*/

import java.util.Arrays;

public class Find_Peak_Element {

    private static int findPeakElement(int[] arr) {
        
        if (arr.length == 1) return 0;
        int n = arr.length - 1;
        
        if (arr[0] > arr[1]) return 0;
        if (arr[n] > arr[n - 1]) return n;
        
        int low = 1;
        int high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            }

            if (arr[mid] < arr[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            
        }
        
        return -1;
    }

    public static void main(String[] args) {
        
        int[] arr = {1, 2, 3, 4, 3, 2, 4, 1};
        System.out.println("Peak element in the arr " + Arrays.toString(arr) + " is = " + findPeakElement(arr));
  
    }
    
}

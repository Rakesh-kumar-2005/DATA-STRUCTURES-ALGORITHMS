package Arrays;

/*
        Description :-
                Here's the problem is to reverse the array, without using any extra space...

        Approach :-
              > Take two pointers left and right and assign them the value of 0 index and array''s last index....
              > then take a while loop until left becomes greater than or equal to right, and swap every values...
              > Time Complexity = O(n)...
              > Space Complexity = O(1)...
*/

import java.util.Arrays;

public class Reverse_the_Array {

    private static void reverseArray(int[] arr) {
	if (arr.length == 1) {
	    return;
	}

	int left = 0;
	int right = arr.length - 1;

	while (left < right) {
	    int temp = arr[ left ];
	    arr[ left ] = arr[ right ];
	    arr[ right ] = temp;
	    left++;
	    right--;
	}
    }

    public static void main(String[] args) {
	int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
	System.out.println("Original Array : ");
	System.out.println(Arrays.toString(arr));
	reverseArray(arr);
	System.out.println("After Reversing the Array : ");
	System.out.println(Arrays.toString(arr));
    }

}

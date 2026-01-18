package Arrays;

/*
        Description :-
                The problem asks for the minimum and maximum value in an unsorted array...

        Approach :-
              > Take two variables maxValue and minValue and assign them as Integer min value
                    and max value initially, which would be easy for us to compare...
              > Then compare with every integer and check for the greater element and smaller element...
              > Time Complexity = O(n)...
              > Space Complexity = O(1)...
*/

import java.util.Arrays;

public class Maximum_and_Minimum_Element_In_An_Array {

    public static void main(String[] args) {
	
		int[] arr = {2, 3, 44, 11, 666, 754, 854, 8635};
	
		int maxValue = Integer.MIN_VALUE;
		int minValue = Integer.MAX_VALUE;

	
		for (int number : arr) {
			maxValue = Math.max(number, maxValue);
			minValue = Math.min(number, minValue);
		}

	
		System.out.println(Arrays.toString(arr));
		System.out.println("Maximum value in the array is = " + maxValue);
		System.out.println("Minimum value in the array is = " + minValue);
		
	}
	
}

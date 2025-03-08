package Arrays;

/*
        Description :-
                Given an array where each element represents the height of a vertical line on the x-axis,
                find two lines that form a container with the maximum water capacity...
                The capacity is determined by the shorter line and the width between them...

        Approach :-
             > The approach uses the two-pointer technique to maximize the water capacity efficiently...
             > We initialize two pointers: low at the beginning and high at the end. The water capacity between
                    two lines is calculated as width × min( height [ low ], height [ high ])...
             > We update the maximum capacity found so far. Since water capacity depends on the shorter
                line, we move the pointer pointing to the smaller height inward to explore a potentially larger
                    container. This process continues until low meets high....
             > Time Complexity = O(n)...
             > Space Complexity = O(1)...
*/

public class Container_With_Max_Water_Capacity {

    private static int maxArea(int[] height) {
        int low = 0;
        int high = height.length - 1;
        int max_capacity = Integer.MIN_VALUE;

        while (low < high) {
            int currWaterCapacity = (high - low) * Math.min(height[low], height[high]);
            max_capacity = Math.max(max_capacity, currWaterCapacity);

            if (height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return max_capacity;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Maximum water capacity = " + maxArea(heights));
    }
}

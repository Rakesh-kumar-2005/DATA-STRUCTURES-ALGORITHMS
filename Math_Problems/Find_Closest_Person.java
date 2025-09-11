package Math_Problems;

/*

Description:
    -> This program determines which of two persons (located at positions x and y) 
       is closer to a target person located at position z on a number line.

Problem Statement:
    You are given three integer positions:
        - x : Position of the first person
        - y : Position of the second person
        - z : Position of the target person
    Find which person (x or y) is closer to z.

    Output:
        - 1 → if person at x is closer
        - 2 → if person at y is closer
        - 0 → if both are equally close

Example:
    Input:
        x = 1, y = 2, z = 3
    Output:
        2
    Explanation:
        - Distance from x to z = |1 - 3| = 2
        - Distance from y to z = |2 - 3| = 1
        - Since 1 < 2, person y is closer.

    Input:
        x = 4, y = 7, z = 5
    Output:
        1
    Explanation:
        - Distance from x to z = |4 - 5| = 1
        - Distance from y to z = |7 - 5| = 2
        - Since 1 < 2, person x is closer.

    Input:
        x = 2, y = 6, z = 4
    Output:
        0
    Explanation:
        - Distance from x to z = |2 - 4| = 2
        - Distance from y to z = |6 - 4| = 2
        - Both distances equal → return 0.

Approach:
    1. Compute distance of x from z → |x - z|
    2. Compute distance of y from z → |y - z|
    3. Compare:
        - If distanceX < distanceY → return 1
        - If distanceY < distanceX → return 2
        - If equal → return 0

Why this works:
    - Absolute difference gives the correct distance between two positions 
      regardless of direction on the number line.
    - Simple comparison determines closeness.

Time and Space Complexity:
    -> Time Complexity: O(1), only a few arithmetic operations.
    -> Space Complexity: O(1), no extra storage required.

Conclusion:
    This solution efficiently finds which person is closer to the target position 
    or if both are equidistant.

*/

public class Find_Closest_Person {

    private static int findClosest(int x, int y, int z) {

        int distanceFromX = Math.abs(x - z);
        int distanceFromY = Math.abs(y - z);

        if (distanceFromX > distanceFromY) {
            return 2;
        } else if (distanceFromX < distanceFromY) {
            return 1;
        } else {
            return 0;
        }

    }

    public static void main(String[] args) {

        int x = 1;
        int y = 2;
        int z = 3;

        System.out.println("The closest person is : " + findClosest(x, y, z));

    }

}

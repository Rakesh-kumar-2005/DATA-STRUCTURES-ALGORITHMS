package Math_Problems;

/*

Description:
    -> This program solves the "Alice and Bob Playing Flower Game" problem.
    -> Alice and Bob pick flowers alternately from two different gardens.
    -> The rule is: a valid pair of flowers is formed only when one flower count is odd 
       and the other is even.
    -> The task is to count the total number of such valid pairs possible 
       given n flowers in one garden and m flowers in another.

Problem Statement:
    Given two integers n and m (number of flowers in each garden),
    return the number of valid pairs (i, j) such that:
        -> 1 ≤ i ≤ n
        -> 1 ≤ j ≤ m
        -> (i + j) is odd (one must be odd, the other even).

Example:
    Input:
        n = 3, m = 4
    Output:
        6
    Explanation:
        Possible pairs = (1,2), (1,4), (2,1), (2,3), (3,2), (3,4)
        Total = 6

Approach:
    1. Total possible pairs = n × m.
    2. Exactly half of them will have (i + j) odd (odd + even).
    3. Hence, result = (n × m) / 2.

Key Variables:
    -> n, m         : number of flowers in each garden.
    -> combinations : number of valid pairs Alice and Bob can collect.

Time and Space Complexity:
    -> Time Complexity: O(1), direct formula calculation.
    -> Space Complexity: O(1).

Conclusion:
    The program efficiently calculates valid pairs of flowers using 
    a simple mathematical observation without iteration.

*/

public class Alice_And_Bob_Playing_Flower_Game {

    private static long flowerGame(int n, int m) {
        return ((long) m * n) / 2;
    }

    public static void main(String[] args) {

        int n = 3;
        int m = 4;

        long combinations = flowerGame(n, m);
        System.out.println("The number of flowers Alice and Bob can collect is : " + combinations);

    }

}

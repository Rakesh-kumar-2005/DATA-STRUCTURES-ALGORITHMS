package Math_Problems;

/*

Description:
    -> This program counts the number of operations required to make either of two given numbers zero...
    -> In each operation, the larger number is reduced by the smaller number...
    -> The process continues until one of the numbers becomes zero...

Problem Statement:
    You are given two non-negative integers, `num1` and `num2`...
    In one operation, you can perform the following step:
        - If `num1 >= num2`, replace `num1` with `num1 - num2`...
        - Else, replace `num2` with `num2 - num1`...
    The task is to count how many such operations are required until either `num1` or `num2` becomes zero...

Example:
    Input:
        num1 = 5
        num2 = 9...
    Output:
        The number of operations to obtain zero from either one number is : 5...

Explanation:
    - Step 1: num1 = 5, num2 = 9 → num2 = 9 - 5 = 4...
    - Step 2: num1 = 5, num2 = 4 → num1 = 5 - 4 = 1...
    - Step 3: num1 = 1, num2 = 4 → num2 = 4 - 1 = 3...
    - Step 4: num1 = 1, num2 = 3 → num2 = 3 - 1 = 2...
    - Step 5: num1 = 1, num2 = 2 → num2 = 2 - 1 = 1...
    - Step 6: num1 = 1, num2 = 1 → num1 = num1 - num2 = 0...
    - Total operations = 6...

Approach:
    1. Print the original values of `num1` and `num2` for clarity...
    2. Handle base cases:
        -> If any number is zero, no operation is needed...
        -> If both numbers are equal, only one operation is needed...
    3. Use a loop to repeatedly subtract the smaller number from the larger one...
    4. Count each subtraction as one operation...
    5. Continue until one of the numbers becomes zero...
    6. Return the total number of operations performed...

Key Observations:
    -> This approach mimics the subtraction-based Euclidean Algorithm for finding GCD...
    -> The number of operations is determined by how quickly one number becomes zero through successive subtractions...
    -> The process ensures termination since the numbers strictly decrease each time...

Time and Space Complexity:
    -> Time Complexity: O(max(num1, num2)) in the worst case...
    -> Space Complexity: O(1), as no extra space is used beyond a few variables...

Example Walkthrough:
    Input: num1 = 5, num2 = 9...
    Output: 6 operations required to make one number zero...
    Explanation: The subtraction continues until num1 becomes zero while tracking the number of steps...

*/

public class Count_Operations_To_Obtain_Zero {

    private static int countOperations(int num1, int num2) {

        System.out.println("Original numbers are : num1 = " + num1 + " num2 = " + num2);

        if (num1 == 0 || num2 == 0) {
            System.out.println("One of the number is zero...");
            return 0;
        }

        if (num1 == num2) {
            System.out.println("Both the numbers are same...");
            return 1;
        }

        int count = 0;
        while (num1 != 0 || num2 != 0) {

            if (num1 < num2) {
                count++;
                num2 -= num1;
                System.out.println("Step no. " + count + " num1 = " + num1 + " num2 = " + num2);
            } else if (num1 > num2) {
                count++;
                num1 -= num2;
                System.out.println("Step no. " + count + " num1 = " + num1 + " num2 = " + num2);
            } else {
                num1 -= num2;
                System.out.println("Final numbers are : num1 = " + num1 + " num2 = " + num2);
                return count + 1;
            }

        }

        System.out.println("Final numbers are : num1 = " + num1 + " num2 = " + num2);
        return count;

    }

    public static void main(String[] args) {

        int num1 = 5;
        int num2 = 9;

        int noOfOperations = countOperations(num1, num2);
        System.out.println("The number of operations to obtain zero from either one number is : " + noOfOperations);

    }

}

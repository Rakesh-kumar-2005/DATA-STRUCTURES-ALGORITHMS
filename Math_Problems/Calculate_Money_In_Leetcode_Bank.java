package Math_Problems;

/*
Description:
    This program calculates the total amount of money deposited in the "Leetcode Bank"
    after `n` days, following a specific saving pattern.

Problem Statement:
    On Monday, you deposit $1.
    Every subsequent day of the same week, you deposit $1 more than the previous day.
    Every new week (starting Monday), you deposit $1 more than the Monday of the previous week.

    Example pattern of deposits:
        Week 1: 1, 2, 3, 4, 5, 6, 7  → Total = 28
        Week 2: 2, 3, 4, 5, 6, 7, 8  → Total = 35
        Week 3: 3, 4, 5, 6, 7, 8, 9  → Total = 42
        and so on...

    You need to find the total amount of money after `n` days.

Approach 1 (Mathematical Formula):
    - Let `weeks = n / 7` → Number of complete weeks.
    - Let `days = n % 7` → Remaining days of the last incomplete week.
    - Each complete week’s total increases by 7 from the previous week.
      For example: 28, 35, 42, ...
    - Formula for the sum of all complete weeks:
        total += weeks * (28 + (weeks - 1) * 7 / 2)
    - For the remaining days:
        total += days * (1 + weeks + (days - 1) / 2)
    - This formula directly computes the total money without looping.

Approach 2 (Simulation / Iterative):
    - Simulate the daily deposits day-by-day.
    - Keep track of:
        - `money`: The daily deposit amount (resets to 1 every Monday).
        - `weekTrack`: The number added to the base deposit for each new week.
    - For each day:
        1. Add the current deposit to total.
        2. Increment `money`.
        3. Every 7th day, increase `weekTrack` and reset `money` to 1.

Example Input:
    n = 20

Example Output:
    Total money in Leetcode Bank (Approach 1) : 96
    Total money in Leetcode Bank (Approach 2) : 96

Explanation:
    The deposits are:
        Week 1: 1, 2, 3, 4, 5, 6, 7  → 28
        Week 2: 2, 3, 4, 5, 6, 7, 8  → 35
        Week 3 (only 6 days): 3, 4, 5, 6, 7, 8  → 33
    Total = 28 + 35 + 33 = 96

Key Concepts Used:
    - Arithmetic progression
    - Modular arithmetic for weekly reset
    - Mathematical vs iterative solution comparison

Time and Space Complexity:
    Approach 1:
        Time Complexity: O(1)
        Space Complexity: O(1)
    Approach 2:
        Time Complexity: O(n)
        Space Complexity: O(1)
*/

public class Calculate_Money_In_Leetcode_Bank {

    private static int totalMoney1(int n) {

        int weeks = n / 7;
        int days = n % 7;
        int total = 0;

        total += weeks * (28 + (weeks - 1) * 7 / 2);
        total += days * (1 + weeks + (days - 1) / 2);

        return total;

    }

    private static int totalMoney2(int n) {

        int total = 0;
        int money = 1;
        int weekTrack = 0;


        for (int day = 1; day <= n; day++) {

            total += money + weekTrack;
            money++;

            if (day % 7 == 0) {
                weekTrack++;
                money = 1;
            }

        }

        return total;

    }

    public static void main(String[] args) {
        
        int n = 20;
        System.out.println("Total money in Leetcode Bank (Approach 1) : " + totalMoney1(n));
        System.out.println("Total money in Leetcode Bank (Approach 2) : " + totalMoney2(n));
    
    }

}

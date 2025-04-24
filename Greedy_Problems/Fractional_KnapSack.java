package Greedy_Problems;

/*

Description:
  Following program demonstrates the solution to the "Fractional Knapsack"
    problem using a Greedy approach with profit-to-weight ratio sorting...

Problem Statement:
  -> Given a set of items, each with a weight and profit value...
  -> And a knapsack with a maximum weight capacity...
  -> Select items (or fractions of items) to include in the knapsack...
  -> Maximize the total profit while keeping the total weight within capacity...
  -> Unlike 0/1 Knapsack, items can be broken into smaller units (fractional parts)...

Approach:
  > Greedy Strategy with Profit-to-Weight Ratio:
     i. Calculate the profit-to-weight ratio for each item...
     ii. Sort items in descending order of their profit-to-weight ratios...
     iii. Select items greedily, taking as much as possible of each item in the sorted order...
     iv. If an item cannot be fully included, take a fraction of it to fill the remaining capacity...

> Algorithm Steps:
  -> Calculate profit-to-weight ratio for each item...
  -> Sort items based on their profit-to-weight ratios in descending order...
  -> Initialize total profit to 0...
  -> Iterate through the sorted items:
     1. If the current item can fit completely, add its profit to the total...
     2. If only a fraction can fit, add the proportional profit and stop...
     3. Update the remaining capacity accordingly...
  -> Return the final total profit value...

> Key Characteristics:
  -> Uses sorting based on profit-to-weight ratio to maximize profit...
  -> Allows fractional selection of items...
  -> Guaranteed to give optimal solution for the fractional knapsack problem...
  -> Implements classic greedy algorithm approach...

> Implementation Details:
  -> Uses custom comparator to sort indices based on profit-to-weight ratios...
  -> Maintains original arrays intact while working with sorted indices...
  -> Handles both full item and fractional item selections appropriately...

> Time and Space Complexity:
  -> Time Complexity: O(n log n) due to sorting operation...
  -> Space Complexity: O(n) for storing the indices array...

*/

import java.util.Arrays;

public class Fractional_KnapSack {

    private static double maxProfit(double capacity, double[] weight, double[] profit, int n) {

        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (i, j) -> {
            double ratio1 = profit[i] / weight[i];
            double ratio2 = profit[j] / weight[j];
            return Double.compare(ratio2, ratio1);
        });

        double totalProfit = 0.0;
        for (int idx : indices) {

            if (weight[idx] <= capacity) {
                totalProfit += profit[idx];
                capacity -= weight[idx];
            } else {
                totalProfit += profit[idx] / weight[idx] * capacity;
                break;
            }

        }
        return totalProfit;
    }

    public static void main(String[] args) {

        double[] weights = {10, 5, 15, 7, 20, 12, 8, 4, 18, 9};
        double[] profits = {60, 40, 90, 50, 100, 80, 70, 30, 120, 75};

        double knapsackCapacity = 75;

        double maxProfit = maxProfit(knapsackCapacity, weights, profits, weights.length);

        System.out.println("Maximum profit obtainable = " + maxProfit);

    }
}

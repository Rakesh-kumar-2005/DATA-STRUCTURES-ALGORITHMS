package Greedy_Problems;

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

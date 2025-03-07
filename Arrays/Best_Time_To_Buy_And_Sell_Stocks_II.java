package Arrays;

/*
        Description :-
            We have to find the maximum profit by buying and selling stocks multiple times, where
            you can complete as many transactions as you want but must sell before buying again and
            you cam only keep the product for one day...

        Approach :-
            > The approach uses a greedy algorithm that captures all possible profits...
            > It iterates through the price array and adds to the total profit whenever there's a
                price increase between consecutive days. This works because any upward price
                movement represents a potential profit opportunity...
            > The implementation simply checks if the current price is less than the next day's price,
                and if so, adds the difference to the total profit...
*/

public class Best_Time_To_Buy_And_Sell_Stocks_II {

    private static int maxProfit(int[] arr) {
        int total_profit = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                total_profit += (arr[i + 1] - arr[i]);
            }
        }
        return total_profit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Max Profit in The consecutive Prices is = " + maxProfit(prices));
    }

}

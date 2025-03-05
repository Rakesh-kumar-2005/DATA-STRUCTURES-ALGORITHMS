package Arrays;

/*
        Description :-
            The code finds the maximum profit from stock prices by keeping track of
            the minimum price and calculating the maximum profit possible by selling
            at the current price...


        Approach :-
            > The approach uses a single pass through the prices array.
            > It initializes min to track the lowest stock price encountered
                and max_profit to track the highest profit achieved...
            > For each price in the array, it updates min if the current price is
                lower than the previous minimum. It then calculates the potential
                profit by subtracting min from the current price and updates max_profit
                if the new profit is greater than the previous maximum...
            > This ensures an efficient solution with O(n) complexity...
*/

public class Best_Time_To_Buy_And_Sell_Stocks_i {

    private static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max_profit = Integer.MIN_VALUE;

        for (int price : prices) {
            min = Math.min(price, min);
            max_profit = Math.max(price - min, max_profit);
        }
        return max_profit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("The Maximum Profit we acn get = " + maxProfit(prices));
    }
}

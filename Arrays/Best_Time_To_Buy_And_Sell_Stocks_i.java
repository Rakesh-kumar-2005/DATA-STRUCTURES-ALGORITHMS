package Arrays;


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

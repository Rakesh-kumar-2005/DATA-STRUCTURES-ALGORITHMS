package Math_Problems;

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
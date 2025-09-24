package Math_Problems;

public class Sum_Multiples {

    private static int sumOfMultiples(int n) {
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {

        int n = 7;
        System.out.println("The sum of multiples of 3, 5, and 7 up to " + n + " is: " + sumOfMultiples(n));

    }

}
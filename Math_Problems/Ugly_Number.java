package Math_Problems;

public class Ugly_Number {

    private static boolean isUgly(int n) {

        if (n == 0) {
            return false;
        }

        if (n <= 3 && n > 0) {
            return true;
        }

        if (n % 2 == 0) {
            return isUgly(n / 2);
        }

        if (n % 3 == 0) {
            return isUgly(n / 3);
        }

        if (n % 5 == 0) {
            return isUgly(n / 5);
        }

        return false;
        
    }

    public static void main(String[] args) {

        int n = 6;
        int m = 14;

        System.out.println("Is " + n + " ugly : " + isUgly(n));
        System.out.println("Is " + m + " ugly : " + isUgly(m));

    }

}
package Math_Problems;

public class Power_Of_Four {

    private static boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 4 == 0) {
            n /= 4;
        }

        return n == 1;

    }

    public static void main(String[] args) {

        int n = 16;
        boolean result = isPowerOfFour(n);

        if (result) {
            System.out.println("The number " + n + " is a power of four.");
        } else {
            System.out.println("The number " + n + " is not a power of four.");
        }

    }

}
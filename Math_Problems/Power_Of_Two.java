package Math_Problems;

public class Power_Of_Two {

    private static boolean isPowerOfTwo(int n) {
        return (n > 0) && ((n & (n - 1)) == 0);
    }

    public static void main(String[] args) {

        int number = 16;
        boolean result = isPowerOfTwo(number);

        if (result) {
            System.out.println("The number " + number + " is a power of two.");
        } else {

            System.out.println("The number " + number + " is not a power of two.");
        }

    }

}
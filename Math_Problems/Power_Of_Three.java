package Math_Problems;

public class Power_Of_Three {

    private static boolean isPowerOfThree1(int n) {

        int maxPowerOf3 = 1162261467;
        return n > 0 && maxPowerOf3 % n == 0;

    }

    private static boolean isPowerOfThree2(int n) {

        if (n <= 0) {
            return false;
        }

        while (n % 3 == 0) {
            n = n / 3;
        }

        return n == 1;

    }

    public static void main(String[] args) {

        int n = 27;
        boolean ans1 = isPowerOfThree1(n);
        boolean ans2 = isPowerOfThree2(n);

        if (ans1) {
            System.out.println("The number " + n + " is a power of 3 using method 1 : " + ans1);
        } else {
            System.out.println("The number " + n + " is not a power of 3 using method 1 : " + ans1);
        }

        if (ans2) {
            System.out.println("The number " + n + " is a power of 3 using method 2 : " + ans2);
        } else {
            System.out.println("The number " + n + " is not a power of 3 using method 2 : " + ans2);
        }

    }

}
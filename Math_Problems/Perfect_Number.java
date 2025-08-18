package Math_Problems;

public class Perfect_Number {

    private static boolean checkPerfectNumber1(int num) {
        int ans = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) ans += i;
        }
        return ans == num;
    }

    private static boolean checkPerfectNumber2(int num) {
        return (num == 6 || num == 28 || num == 496 || num == 8128 || num == 33550336);
    }

    public static void main(String[] args) {

        int num = 28;
        boolean ans1 = checkPerfectNumber1(num);
        boolean ans2 = checkPerfectNumber2(num);

        if (ans1) {
            System.out.println("The number " + num + " is a perfect number by method 1");
        } else {
            System.out.println("The number " + num + " is not a perfect number by method 1");

        }

        if (ans2) {
            System.out.println("The number " + num + " is a perfect number by method 2");
        } else {
            System.out.println("The number " + num + " is not a perfect number by method 2");

        }

    }

}
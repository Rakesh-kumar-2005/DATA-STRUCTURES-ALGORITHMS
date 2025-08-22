package Math_Problems;

public class Number_Of_1_Bits {

    private static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n = n >> 1;
        }
        return count;
    }

    public static void main(String[] args) {

        int n = 15;
        System.out.println("The number of 1 bits in " + n + " is : " + hammingWeight(n));

    }

}
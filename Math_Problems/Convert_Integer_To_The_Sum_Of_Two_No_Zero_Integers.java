package Math_Problems;

public class Convert_Integer_To_The_Sum_Of_Two_No_Zero_Integers {

    private static int[] getNoZeroIntegers(int n) {

        for (int i = 1; i < n; i++) {
            int j = n - i;

            if (! String.valueOf(i).contains("0") && ! String.valueOf(j).contains("0")) {
                return new int[]{i, j};
            }
        }

        return new int[2];
    }

    public static void main(String[] args) {

        int n = 2;
        int[] ans = getNoZeroIntegers(n);

        System.out.println("The two Non-Zero numbers are which sum up to " + n + " are : ");
        System.out.println(ans[0] + " " + ans[1]);

    }

}

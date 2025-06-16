package Math_Problems;

public class Maximum_Difference_By_Remapping_A_Digit {

    private static int minMaxDifference(int num) {

        String number = Integer.toString(num);
        char[] maxNum = number.toCharArray();
        char[] minNum = number.toCharArray();

        int n = number.length();
        char replaceForMax = ' ';

        for (char curr : maxNum) {
            if (curr != '9') {
                replaceForMax = curr;
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (maxNum[i] == replaceForMax) {
                maxNum[i] = '9';
            }
        }

        int replaceForMin = minNum[0];

        for (int i = 0; i < n; i++) {
            if (minNum[i] == replaceForMin) {
                minNum[i] = '0';
            }
        }

        int maxValue = Integer.parseInt(new String(maxNum));
        int minValue = Integer.parseInt(new String(minNum));

        System.out.println("The maximum value of the number " + num + " after remapping is = " + maxValue);
        System.out.println("The minimum value of the number " + num + " after remapping is = " + minValue);

        return maxValue - minValue;

    }

    public static void main(String[] args) {

        int num = 1234;
        System.out.println("The maximum difference in the number " + num + " is = " + minMaxDifference(num));

    }

}

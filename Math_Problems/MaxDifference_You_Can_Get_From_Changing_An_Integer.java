package Math_Problems;

public class MaxDifference_You_Can_Get_From_Changing_An_Integer {

    private static int maxDiff(int num) {

        String number = Integer.toString(num);
        char[] maxNum = number.toCharArray();
        char[] minNum = number.toCharArray();

        char replaceForMax = ' ';

        for (char curr : maxNum) {
            if (curr != '9') {
                replaceForMax = curr;
                break;
            }
        }

        for (int i = 0; i < maxNum.length; i++) {
            if (maxNum[i] == replaceForMax) {
                maxNum[i] = '9';
            }
        }

        char replaceForMin = ' ';
        char replaceWithMin = ' ';

        if (minNum[0] != '1') {
            replaceForMin = minNum[0];
            replaceWithMin = '1';
        } else {
            for (int i = 1; i < minNum.length; i++) {
                if (minNum[i] != '0' && minNum[i] != '1') {
                    replaceForMin = minNum[i];
                    replaceWithMin = '0';
                    break;
                }
            }
        }

        for (int i = 0; i < minNum.length; i++) {
            if (minNum[i] == replaceForMin) {
                minNum[i] = replaceWithMin;
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
        System.out.println("The maximum difference in the number " + num + " is = " + maxDiff(num));

    }

}
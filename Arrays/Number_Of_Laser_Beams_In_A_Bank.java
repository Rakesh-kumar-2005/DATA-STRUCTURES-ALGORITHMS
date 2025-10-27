package Arrays;

public class Number_Of_Laser_Beams_In_A_Bank {

    private static int helper(String s) {

        int numberOfLasers = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                numberOfLasers++;
            }
        }

        return numberOfLasers;

    }

    private static int numberOfBeams(String[] bank) {

        int currBeams = 0;
        int prevBeams = 0;
        int totalBeams = 0;
        int n = bank.length;

        for (String s : bank) {
            currBeams = helper(s);
            totalBeams += (currBeams * prevBeams);

            if (currBeams != 0) {
                prevBeams = currBeams;
            }
        }

        return totalBeams;
    }

    public static void main(String[] args) {

        String[] bank = {"011001", "000000", "010100", "001000"};
        System.out.println("The total number of beams in the bank is : " + numberOfBeams(bank));

    }

}
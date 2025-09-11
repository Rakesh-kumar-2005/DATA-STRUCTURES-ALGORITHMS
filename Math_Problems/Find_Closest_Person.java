package Math_Problems;

public class Find_Closest_Person {

    private static int findClosest(int x, int y, int z) {

        int distanceFromX = Math.abs(x - z);
        int distanceFromY = Math.abs(y - z);

        if (distanceFromX > distanceFromY) {
            return 2;
        } else if (distanceFromX < distanceFromY) {
            return 1;
        } else {
            return 0;
        }

    }

    public static void main(String[] args) {

        int x = 1;
        int y = 2;
        int z = 3;

        System.out.println("The closest person is : " + findClosest(x, y, z));

    }

}

package Math_Problems;

public class Alice_And_Bob_Playing_Flower_Game {

    private static long flowerGame(int n, int m) {
        return ((long) m * n) / 2;
    }

    public static void main(String[] args) {

        int n = 3;
        int m = 4;

        long combinations = flowerGame(n, m);
        System.out.println("The number of flowers Alice and Bob can collect is : " + combinations);

    }

}
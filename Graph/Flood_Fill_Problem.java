package Graph;

/*

    Description:
        Following program demonstrates the solution to the "Flood Fill" problem
        using Depth-First Search (DFS) Approach...

    Problem Statement:
        -> Given a 2D image represented as a 2D integer array...
        -> Starting from a specified pixel (sr, sc), change all connected pixels of the same color...
        -> Connected pixels are those that share an edge (not diagonally connected)...
        -> The task is to fill all connected pixels having the same initial color with a new color...
        -> Similar to the "paint bucket" tool in image editing software...

    Approach:
        > Depth-First Search (DFS) with Recursive Traversal:
            i. Use DFS to explore connected pixels of the same color...
            ii. Change color of each visited pixel to the new color...
            iii. Explore in four directions: up, right, down, left...
            iv. Continue until all connected pixels of the initial color are processed...
            v. Handle boundary conditions and already colored pixels...

    Algorithm Steps:
        -> Initialize directional arrays for moving in four directions:
            1. delRow = {-1, 0, 1, 0} for up, right, down, left movements...
            2. delCol = {0, 1, 0, -1} for corresponding column movements...
        -> Recursive DFS Implementation:
            1. Change the color of the current pixel...
            2. Explore in all four directions from the current pixel...
            3. For each direction, check boundary conditions...
            4. Process only pixels matching the previous color and not already filled...
            5. Recursively apply the same process to valid adjacent pixels...
        -> Main Flood Fill Function:
            1. Store the initial color of the starting pixel...
            2. Call helper function to perform the recursive DFS...
            3. Return the modified image...

    Key Characteristics:
        -> Uses recursive DFS for systematic pixel exploration...
        -> Handles boundary conditions of the image...
        -> Prevents reprocessing of already colored pixels...
        -> Efficiently fills connected components of the same color...

        > Exploration Mechanism:
            -> Uses directional arrays to explore in four directions...
            -> Ensures connectivity through edge-sharing pixels only...
            -> Maintains the original image structure...

    Time and Space Complexity:
        -> Time Complexity: O(N * M) where N and M are the dimensions of the image...
        -> Space Complexity: O(N * M) for recursion stack in worst case...

*/

public class Flood_Fill_Problem {
    private static void helper(int sr, int sc, int[][] image, int[][] ans, int newColor, int prevColor, int[] delRow, int[] delCol) {
        ans[sr][sc] = newColor;
        int n = ans.length;
        int m = ans[0].length;

        for (int i = 0; i < 4; i++) {

            int newRow = sr + delRow[i];
            int newCol = sc + delCol[i];

            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m &&
                image[newRow][newCol] == prevColor && ans[newRow][newCol] != newColor) {

                helper(newRow, newCol, image, ans, newColor, prevColor, delRow, delCol);
            }
        }
    }

    private static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int[] delRow = {- 1, 0, 1, 0};
        int[] delCol = {0, 1, 0, - 1};

        int[][] ans;
        ans = image;
        int prevColor = image[sr][sc];

        helper(sr, sc, image, ans, newColor, prevColor, delRow, delCol);

        return ans;

    }

    private static void printArray(int[][] arr) {

        for (int[] i : arr) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int newColor = 2;
        int sr = 1;
        int sc = 1;

        System.out.println("Original array : ");
        printArray(image);

        int[][] ans = floodFill(image, sr, sc, newColor);

        System.out.println("After flood filling :");
        printArray(ans);
    }
}

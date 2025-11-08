package Graph;

/*

Description:
    -> This program finds the city with the smallest number of neighboring cities that can be reached within a given distance threshold...
    -> If multiple cities have the same number of reachable neighbors, the city with the greatest index is chosen...
    -> The algorithm is based on the Floyd-Warshall algorithm, which computes all-pairs shortest paths...

Problem Statement:
    You are given an undirected weighted graph with `n` cities (numbered from 0 to n - 1) and a list of edges...
    Each edge is represented as {u, v, wt}, indicating that city `u` and city `v` are connected with a distance `wt`...
    You are also given an integer `distanceThreshold`...
    The goal is to determine the city that can reach the fewest other cities within the `distanceThreshold` distance...

Example:
    Input:
        n = 4
        edges = {
            {0, 1, 3},
            {1, 2, 1},
            {1, 3, 4},
            {2, 3, 1}
        }
        distanceThreshold = 4...
    Output:
        The city with the smallest number of neighbors at a threshold distance is : 3...

Explanation:
    - The distance matrix after applying Floyd-Warshall gives the shortest path between all pairs of cities...
    - Each city is checked to count how many other cities are reachable within distance ≤ 4...
    - City 3 has the fewest reachable cities, hence it is the answer...

Approach:
    1. Initialize a 2D distance matrix `distance[n][n]` with `Integer.MAX_VALUE` for all pairs...
    2. Set `distance[i][i] = 0` for all cities...
    3. Fill in the direct edge distances using the given `edges` array...
    4. Use the **Floyd-Warshall Algorithm** to compute shortest paths between every pair of cities...
       -> For each intermediate city `k`, update `distance[i][j] = min(distance[i][j], distance[i][k] + distance[k][j])`...
    5. For each city, count how many other cities are reachable within `distanceThreshold`...
    6. Keep track of the city with the smallest count (and largest index in case of a tie)...
    7. Return that city index as the result...

Key Observations:
    -> Floyd-Warshall helps compute all-pairs shortest paths efficiently for small graphs...
    -> The problem requires checking reachability under a distance constraint rather than simply finding the shortest path...
    -> The city index comparison ensures that the largest-numbered city is chosen in case of a tie...

Time and Space Complexity:
    -> Time Complexity: O(V³)...
       (Due to three nested loops in the Floyd-Warshall algorithm)...
    -> Space Complexity: O(V²)...
       (For storing the distance matrix)...

Example Walkthrough:
    Step 1: Compute all-pairs shortest paths using Floyd-Warshall...
    Step 2: For each city, count reachable neighbors within the threshold...
    Step 3: Return the city with the smallest count, or the largest index if tied...
    Step 4: Output the result as the final city index...

*/

public class Find_the_City_With_The_Smallest_Number_Of_Neighbors_At_A_Threshold_Distance {

    private static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int m = edges.length;

        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            distance[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            distance[u][v] = wt;
            distance[v][u] = wt;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][k] == Integer.MAX_VALUE || distance[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }

                    distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]);
                }
            }
        }

        int countCity = n;
        int cityNo = - 1;

        for (int city = 0; city < n; city++) {
            int count = 0;

            for (int adjCity = 0; adjCity < n; adjCity++) {
                if (distance[city][adjCity] <= distanceThreshold) {
                    count++;
                }
            }

            if (count <= countCity) {
                countCity = count;
                cityNo = city;
            }
        }

        return cityNo;
    }

    public static void main(String[] args) {

        int n = 4;
        int[][] edges = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        int distanceThreshold = 4;

        int cityWithMinNeighbours = findTheCity(n, edges, distanceThreshold);
        System.out.println("The city with the smallest number of neighbors at a threshold distance is : " + cityWithMinNeighbours);
    }

}

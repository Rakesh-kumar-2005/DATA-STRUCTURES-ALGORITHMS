package Hashing;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

/*
        Description :-
            We have to find one sequential travel route, so that we can start from one station/airport
            and visit all the cities...

        Approach :-
            > Store Paths in a HashMap: Store the flight paths in a hashmap where the key is the departure city,
                and the value is the destination city...
            > Identify the Start Point: To find the starting point of the journey, identify a city that is not a
                destination in any of the given paths. This is achieved using a hashset to track all destination cities
                and then finding a departure city that isn’t in that set...
            > Reconstruct the Itinerary: Once the start city is identified, use it to trace the entire path by continuously
                getting the next destination from the hashmap until no more destinations are found...

*/

public class Reconstruct_A_Itinerary_Tickets {

    private static ArrayList<String> reconstructTickets(HashMap<String,String> paths){
        ArrayList<String> ans = new ArrayList<>();

        String start = getStart(paths);
        ans.add(start);
        while(paths.containsKey(start)){
            ans.add(paths.get(start));
            start = paths.get(start);
        }
        return ans;
    }

    private static String getStart(HashMap<String,String> paths){
        HashSet<String> st = new HashSet<>();

        for(String key : paths.keySet()){
            st.add(paths.get(key));
        }

        for(String key : paths.keySet()){
            if(!st.contains(key)){
                return key;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap<String,String> paths = new HashMap<>();

        paths.put("Chennai","Bengaluru");
        paths.put("Mumbai","Delhi");
        paths.put("Goa","Chennai");
        paths.put("Delhi","Goa");

        ArrayList<String> sequenceWiseTickets = reconstructTickets(paths);
        for (String stops : sequenceWiseTickets){
            System.out.print(stops + " ");
        }
    }
}

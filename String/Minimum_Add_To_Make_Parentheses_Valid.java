package String;

public class Minimum_Add_To_Make_Parentheses_Valid {

    private static int minAddToMakeValid(String sequence) {

        int close = 0;
        int open = 0;

        for (int i = 0; i < sequence.length(); i++) {
            char currChar = sequence.charAt(i);

            if (currChar == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    close++;
                }
            }

        }

        return close + open;
        
    }

    public static void main(String[] args) {

        String sequence = "))((";
        System.out.println("Minimum number of parentheses to form a valid sequence is = " + minAddToMakeValid(sequence));

    }

}
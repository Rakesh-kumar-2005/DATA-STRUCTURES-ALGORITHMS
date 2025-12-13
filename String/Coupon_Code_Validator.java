package String;

import java.util.ArrayList;
import java.util.HashMap;

public class Coupon_Code_Validator {

    static class Pair {
        int priority;
        String code;

        public Pair(int p, String c) {
            this.priority = p;
            this.code = c;
        }

    }

    private static boolean isValidCode(String code) {
        if (code.isEmpty()) {
            return false;
        }

        for (char c : code.toCharArray()) {
            if (! Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }

        return true;
    }

    public ArrayList<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        HashMap<String, Integer> couponTypes = new HashMap<>();
        couponTypes.put("electronics", 0);
        couponTypes.put("grocery", 1);
        couponTypes.put("pharmacy", 2);
        couponTypes.put("restaurant", 3);

        ArrayList<Pair> validPairs = new ArrayList<>();
        for (int i = 0; i < code.length; i++) {
            if (isActive[i] && couponTypes.containsKey(businessLine[i]) && isValidCode(code[i])) {
                validPairs.add(new Pair(couponTypes.get(businessLine[i]), code[i]));
            }
        }

        validPairs.sort((a, b) -> {
            if (a.priority != b.priority) {
                return a.priority - b.priority;
            }

            return a.code.compareTo(b.code);
        });

        ArrayList<String> validCodes = new ArrayList<>();

        for (Pair p : validPairs) {
            validCodes.add(p.code);
        }

        return validCodes;

    }

    public static void main(String[] args) {
        Coupon_Code_Validator validator = new Coupon_Code_Validator();

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                  COUPON CODE VALIDATOR                   ║");
        System.out.println("║     Validates and sorts coupons by business priority     ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Validation ===");
        String[] codes1 = {"GROCERY15", "ELECTRONICS_50", "DISCOUNT10"};
        String[] lines1 = {"grocery", "electronics", "invalid"};
        boolean[] active1 = {false, true, true};

        System.out.println("Input:");
        for (int i = 0; i < codes1.length; i++) {
            System.out.printf("  %s - %s - Active: %b\n", codes1[i], lines1[i], active1[i]);
        }

        ArrayList<String> result1 = validator.validateCoupons(codes1, lines1, active1);
        System.out.println("\nValid Coupons (sorted by priority): " + result1);
        System.out.println("Expected: [ELECTRONICS_50]");
        System.out.println("Reason:");
        System.out.println("  - GROCERY15: Inactive ❌");
        System.out.println("  - ELECTRONICS_50: Valid ✓");
        System.out.println("  - DISCOUNT10: Invalid business line ❌\n");

        System.out.println("=== Test Case 2: All Valid, Different Priorities ===");
        String[] codes2 = {"REST100", "PHARM50", "ELEC200", "GRO30"};
        String[] lines2 = {"restaurant", "pharmacy", "electronics", "grocery"};
        boolean[] active2 = {true, true, true, true};

        System.out.println("Input:");
        for (int i = 0; i < codes2.length; i++) {
            System.out.printf("  %s - %s - Active: %b\n", codes2[i], lines2[i], active2[i]);
        }

        ArrayList<String> result2 = validator.validateCoupons(codes2, lines2, active2);
        System.out.println("\nValid Coupons (sorted by priority): " + result2);
        System.out.println("Expected: [ELEC200, GRO30, PHARM50, REST100]");
        System.out.println("Priority: electronics(0) < grocery(1) < pharmacy(2) < restaurant(3)\n");

        System.out.println("=== Test Case 3: Same Priority, Alphabetical Sort ===");
        String[] codes3 = {"ZINC50", "APPLE20", "MANGO30", "BANANA15"};
        String[] lines3 = {"grocery", "grocery", "grocery", "grocery"};
        boolean[] active3 = {true, true, true, true};

        System.out.println("Input:");
        for (int i = 0; i < codes3.length; i++) {
            System.out.printf("  %s - %s - Active: %b\n", codes3[i], lines3[i], active3[i]);
        }

        ArrayList<String> result3 = validator.validateCoupons(codes3, lines3, active3);
        System.out.println("\nValid Coupons (sorted alphabetically): " + result3);
        System.out.println("Expected: [APPLE20, BANANA15, MANGO30, ZINC50]");
        System.out.println("Same priority → sorted alphabetically\n");

        System.out.println("=== Test Case 4: Invalid Codes with Special Characters ===");
        String[] codes4 = {"CODE@123", "VALID_CODE", "NO SPACE", "GOOD123", "BAD#CODE"};
        String[] lines4 = {"electronics", "electronics", "electronics", "grocery", "pharmacy"};
        boolean[] active4 = {true, true, true, true, true};

        System.out.println("Input:");
        for (int i = 0; i < codes4.length; i++) {
            System.out.printf("  %s - %s - Active: %b\n", codes4[i], lines4[i], active4[i]);
        }

        ArrayList<String> result4 = validator.validateCoupons(codes4, lines4, active4);
        System.out.println("\nValid Coupons: " + result4);
        System.out.println("Expected: [VALID_CODE, GOOD123]");
        System.out.println("Validation:");
        System.out.println("  - CODE@123: Contains @ ❌");
        System.out.println("  - VALID_CODE: Valid (underscore allowed) ✓");
        System.out.println("  - NO SPACE: Contains space ❌");
        System.out.println("  - GOOD123: Valid ✓");
        System.out.println("  - BAD#CODE: Contains # ❌\n");

        System.out.println("=== Test Case 5: Mixed Active/Inactive ===");
        String[] codes5 = {"SALE50", "DEAL30", "OFFER20", "PROMO10"};
        String[] lines5 = {"electronics", "grocery", "pharmacy", "restaurant"};
        boolean[] active5 = {true, false, true, false};

        System.out.println("Input:");
        for (int i = 0; i < codes5.length; i++) {
            System.out.printf("  %s - %s - Active: %b\n", codes5[i], lines5[i], active5[i]);
        }

        ArrayList<String> result5 = validator.validateCoupons(codes5, lines5, active5);
        System.out.println("\nValid Coupons: " + result5);
        System.out.println("Expected: [SALE50, OFFER20]");
        System.out.println("Only active coupons pass validation\n");

        System.out.println("=== Test Case 6: Empty and Edge Cases ===");
        String[] codes6 = {"", "A", "12345", "_UNDER_"};
        String[] lines6 = {"electronics", "grocery", "pharmacy", "restaurant"};
        boolean[] active6 = {true, true, true, true};

        System.out.println("Input:");
        for (int i = 0; i < codes6.length; i++) {
            System.out.printf("  '%s' - %s - Active: %b\n", codes6[i], lines6[i], active6[i]);
        }

        ArrayList<String> result6 = validator.validateCoupons(codes6, lines6, active6);
        System.out.println("\nValid Coupons: " + result6);
        System.out.println("Expected: [A, 12345, _UNDER_]");
        System.out.println("Empty string is invalid ❌\n");

        System.out.println("=== Test Case 7: All Invalid Business Lines ===");
        String[] codes7 = {"CODE1", "CODE2", "CODE3"};
        String[] lines7 = {"fashion", "sports", "books"};
        boolean[] active7 = {true, true, true};

        System.out.println("Input:");
        for (int i = 0; i < codes7.length; i++) {
            System.out.printf("  %s - %s - Active: %b\n", codes7[i], lines7[i], active7[i]);
        }

        ArrayList<String> result7 = validator.validateCoupons(codes7, lines7, active7);
        System.out.println("\nValid Coupons: " + result7);
        System.out.println("Expected: []");
        System.out.println("No valid business lines\n");

        System.out.println("=== Test Case 8: Complex Mixed Scenario ===");
        String[] codes8 = {"ELEC_A", "ELEC_Z", "GRO_A", "PHARM_X", "REST_M", "INVALID@"};
        String[] lines8 = {"electronics", "electronics", "grocery", "pharmacy", "restaurant", "electronics"};
        boolean[] active8 = {true, true, true, false, true, true};

        System.out.println("Input:");
        for (int i = 0; i < codes8.length; i++) {
            System.out.printf("  %s - %s - Active: %b\n", codes8[i], lines8[i], active8[i]);
        }

        ArrayList<String> result8 = validator.validateCoupons(codes8, lines8, active8);
        System.out.println("\nValid Coupons: " + result8);
        System.out.println("Expected: [ELEC_A, ELEC_Z, GRO_A, REST_M]");
        System.out.println("Sorted by: priority first, then alphabetically\n");

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║  Priority Order: electronics(0) > grocery(1)             ║");
        System.out.println("║                  > pharmacy(2) > restaurant(3)           ║");
        System.out.println("║  Valid chars: Letters, Digits, Underscores only          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

}

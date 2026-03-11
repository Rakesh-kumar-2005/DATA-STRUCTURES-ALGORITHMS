package Recursion;

/*
    Description:
        Following program demonstrates the conversion of decimal numbers to binary representation
        using a recursive approach...

    Problem Statement:
        -> Given a decimal (base-10) integer...
        -> The task is to convert it to its equivalent binary (base-2) representation...
        -> Binary representation consists only of 0s and 1s...
        -> The conversion needs to handle any non-negative integer input...

    Approach:
        > Recursive Binary Conversion:
            i. Use the mathematical property of binary number representation...
            ii. Apply the divide-by-2 methodology recursively...
            iii. Build the binary number from right to left by:
                a. Finding the remainder when divided by 2 (0 or 1)...
                b. Recursively converting the quotient...
                c. Combining the results in proper order...
            iv. Use the base case to terminate recursion...

    Algorithm Steps:
        -> Recursive Binary Conversion Implementation:
            1. Check if n equals 0 (base case)...
            2. If base case is met, return 0...
            3. Otherwise, compute n % 2 to get the least significant bit...
            4. Recursively call findBinary(n / 2) for higher order bits...
            5. Multiply recursive result by 10 and add current bit...
            6. Return the combined result...
        -> Main Method Flow:
            1. Call findBinary with input 45...
            2. Call findBinary with input 31...
            3. Print the resulting binary representations...

    Key Characteristics:
        -> Uses recursion to naturally build the binary representation...
        -> Works for any non-negative integer input...
        -> Produces the correct binary representation as a decimal number...
        -> Elegantly applies the fundamental binary conversion algorithm...

        > Mathematical Basis:
            -> Each digit in binary represents a power of 2...
            -> The algorithm determines which powers of 2 sum to the given number...
            -> Using remainder and integer division naturally extracts these digits...

    Time and Space Complexity:
        -> Time Complexity: O(log n) where n is the input decimal number...
        -> Space Complexity: O(log n) for the recursion stack...
            (The number of recursive calls equals the number of binary digits)
*/

public class Binary_Conversion {
    
    private static int findBinary(int n){
        if (n == 0) {
            return 0;
        } else {
            return (n % 2) + 10 * findBinary(n / 2);
        }
        
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║          DECIMAL TO BINARY CONVERSION (RECURSIVE)            ║");
        System.out.println("║  Convert decimal number to binary representation             ║");
        System.out.println("║  Returns binary as integer (not string)                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
    
        System.out.println("=== Test Case 1: n = 0 ===");
        int n1 = 0;
        System.out.println("Input: " + n1);
        System.out.println("Binary: 0\n");
        int result1 = findBinary(n1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result1 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 2: n = 1 ===");
        int n2 = 1;
        System.out.println("Input: " + n2);
        System.out.println("Binary: 1\n");
        int result2 = findBinary(n2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result2 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 3: n = 2 ===");
        int n3 = 2;
        System.out.println("Input: " + n3);
        System.out.println("Conversion:");
        System.out.println("  2 ÷ 2 = 1 remainder 0");
        System.out.println("  1 ÷ 2 = 0 remainder 1");
        System.out.println("  Binary: 10\n");
        int result3 = findBinary(n3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 10");
        System.out.println("  Status: " + (result3 == 10 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 4: n = 5 ===");
        int n4 = 5;
        System.out.println("Input: " + n4);
        System.out.println("Conversion:");
        System.out.println("  5 ÷ 2 = 2 remainder 1");
        System.out.println("  2 ÷ 2 = 1 remainder 0");
        System.out.println("  1 ÷ 2 = 0 remainder 1");
        System.out.println("  Binary: 101\n");
        int result4 = findBinary(n4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 101");
        System.out.println("  Status: " + (result4 == 101 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 5: n = 10 ===");
        int n5 = 10;
        System.out.println("Input: " + n5);
        System.out.println("Binary: 1010\n");
        int result5 = findBinary(n5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 1010");
        System.out.println("  Status: " + (result5 == 1010 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 6: n = 31 ===");
        int n6 = 31;
        System.out.println("Input: " + n6);
        System.out.println("Recursion trace:");
        System.out.println("  findBinary(31) → 31%2=1, recurse(15)");
        System.out.println("  findBinary(15) → 15%2=1, recurse(7)");
        System.out.println("  findBinary(7)  → 7%2=1, recurse(3)");
        System.out.println("  findBinary(3)  → 3%2=1, recurse(1)");
        System.out.println("  findBinary(1)  → 1%2=1, recurse(0)");
        System.out.println("  findBinary(0)  → return 0");
        System.out.println("\nBuild result: 1 + 10×(1 + 10×(1 + 10×(1 + 10×(1))))");
        System.out.println("Binary: 11111\n");
        int result6 = findBinary(n6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: 11111");
        System.out.println("  Status: " + (result6 == 11111 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 7: n = 45 ===");
        int n7 = 45;
        System.out.println("Input: " + n7);
        System.out.println("Conversion:");
        System.out.println("  45 ÷ 2 = 22 remainder 1");
        System.out.println("  22 ÷ 2 = 11 remainder 0");
        System.out.println("  11 ÷ 2 = 5 remainder 1");
        System.out.println("  5 ÷ 2 = 2 remainder 1");
        System.out.println("  2 ÷ 2 = 1 remainder 0");
        System.out.println("  1 ÷ 2 = 0 remainder 1");
        System.out.println("  Binary: 101101\n");
        int result7 = findBinary(n7);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Expected: 101101");
        System.out.println("  Status: " + (result7 == 101101 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 8: n = 8 (Power of 2) ===");
        int n8 = 8;
        System.out.println("Input: " + n8);
        System.out.println("Power of 2 → single 1 followed by zeros");
        System.out.println("Binary: 1000\n");
        int result8 = findBinary(n8);
        System.out.println("✓ Result: " + result8);
        System.out.println("  Expected: 1000");
        System.out.println("  Status: " + (result8 == 1000 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Recursive Binary Conversion                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base Case: if (n == 0) return 0                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recursive Case:                                             ║");
        System.out.println("║    (n % 2) + 10 × findBinary(n / 2)                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  How It Works:                                               ║");
        System.out.println("║    • n % 2: Gets rightmost bit (0 or 1)                      ║");
        System.out.println("║    • n / 2: Shifts number right by 1 bit                     ║");
        System.out.println("║    • × 10: Shifts previous result left (in decimal)          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: findBinary(5)                                      ║");
        System.out.println("║    findBinary(5) = 1 + 10 × findBinary(2)                    ║");
        System.out.println("║    findBinary(2) = 0 + 10 × findBinary(1)                    ║");
        System.out.println("║    findBinary(1) = 1 + 10 × findBinary(0)                    ║");
        System.out.println("║    findBinary(0) = 0                                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Unwinding:                                                ║");
        System.out.println("║    findBinary(1) = 1 + 10 × 0 = 1                            ║");
        System.out.println("║    findBinary(2) = 0 + 10 × 1 = 10                           ║");
        System.out.println("║    findBinary(5) = 1 + 10 × 10 = 101                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Note: Returns integer, not string                           ║");
        System.out.println("║    Binary 101 stored as decimal integer 101                  ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(log n) - Divide by 2 each recursion              ║");
        System.out.println("║    Space: O(log n) - Recursion stack depth                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        
    }
    
}

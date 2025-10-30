package Math_Problems;

/*
Description:
    This program simulates a simplified banking system that supports multiple accounts
    and basic operations such as deposit, withdrawal, and money transfer between accounts.

Problem Statement:
    Design a banking system where each account has an initial balance. The system must
    handle the following operations safely:
        1. Transfer money from one account to another.
        2. Deposit money into an account.
        3. Withdraw money from an account.
    Each operation should return whether it was successful or not based on certain conditions
    (e.g., valid account numbers, sufficient balance, etc.).

Example:
    Input:
        balances = [10, 100, 20, 50, 30]
        Operations:
            transfer(1, 2, 50)
            deposit(1, 20)
            withdraw(1, 20)
    Output:
        Transferring 50 from account 1 to account 2 : Done
        Deposit 20 rupees in account 1 : Done
        Withdrawal of 20 rupees from account 1 : Done

Explanation:
    - Account 1 starts with 10 units.
    - Transferring 50 to account 2 fails initially since balance < 50.
    - Depositing 20 increases account 1’s balance to 30.
    - Withdrawing 20 reduces it to 10 again.

Approach:
    1. Define a `Bank` class that encapsulates account balances and operations.
    2. Store balances in an array where index `i` represents account `i + 1`.
    3. Implement methods for each operation:
        - `transfer(account1, account2, money)` → Transfers money between accounts.
        - `deposit(account, money)` → Adds money to an account.
        - `withdraw(account, money)` → Removes money if balance is sufficient.
    4. Include validation checks to ensure:
        - Account numbers are valid.
        - Sufficient funds exist for withdrawals or transfers.
    5. Return `true` for successful operations and `false` otherwise.

Key Concepts Used:
    - Object-Oriented Programming (Encapsulation)
    - Array-based data management
    - Input validation and conditional logic

Time and Space Complexity:
    Time Complexity: O(1) per operation
        (All account operations are direct lookups or updates)
    Space Complexity: O(N)
        (Where N is the total number of accounts, stored in an array)
*/

public class Simple_Bank_System {

    static class Bank {
        private static long[] balance;
        private static int n;

        private Bank(long[] bal) {
            balance = bal;
            n = bal.length;
        }

        public boolean transfer(int account1, int account2, long money) {
            if (notValidAccount(account1) || notValidAccount(account2) || balance[account1 - 1] < money) {
                return false;
            }

            balance[account1 - 1] -= money;
            balance[account2 - 1] += money;

            return true;
        }

        public boolean deposit(int account, long money) {
            if (notValidAccount(account)) {
                return false;
            }

            balance[account - 1] += money;
            return true;

        }

        public boolean withdraw(int account, long money) {
            if (notValidAccount(account) || balance[account - 1] < money) {
                return false;
            }

            balance[account - 1] -= money;
            return true;
        }

        private boolean notValidAccount(int account) {
            return account <= 0 || account > n;
        }
    }

    public static void main(String[] args) {
        long[] bal = {10, 100, 20, 50, 30};
        Bank bank = new Bank(bal);

        System.out.println("Transferring 50 from account 1 to account 2 : " + (bank.transfer(1, 2, 50) ? "Done" : "Failed"));
        System.out.println("Deposit 20 rupees in account 1 : " + (bank.deposit(1, 20) ? "Done" : "Failed"));
        System.out.println("Withdrawal of 20 rupees from account 1" + (bank.withdraw(1, 20) ? "Done" : "Failed"));
    }

}

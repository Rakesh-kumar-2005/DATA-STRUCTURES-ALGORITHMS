package Math_Problems;

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
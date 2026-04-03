public class BankApp {
    public static void main(String[] args) {

        BankAccount account = new BankAccount(500);

        System.out.println("Balance: $500.00");

        try {
            account.withdraw(200);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        try {
            account.withdraw(400);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        try {
            account.withdraw(100);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
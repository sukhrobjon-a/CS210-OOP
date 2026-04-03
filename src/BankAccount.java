public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            double shortfall = amount - balance;
            throw new InsufficientFundsException(shortfall);
        }
        balance -= amount;
        System.out.println("Withdrew $" + String.format("%.2f", amount) +
                ". Remaining balance: $" + String.format("%.2f", balance));
    }

    public double getBalance() {
        return balance;
    }
}
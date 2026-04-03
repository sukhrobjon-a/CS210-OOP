public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(double shortfall) {
        super("Insufficient funds. You are short by $" +
                String.format("%.2f", shortfall));
    }
}
import java.util.Scanner;
public class SafeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter first number (or 'exit'): ");
                String input1 = scanner.nextLine();

                if (input1.equalsIgnoreCase("exit")) {
                    System.out.println("Goodbye!");
                    break;
                }
                int num1 = Integer.parseInt(input1);

                System.out.print("Enter second number: ");
                String input2 = scanner.nextLine();
                int num2 = Integer.parseInt(input2);

                int result = num1 / num2;

                System.out.println("Result: " + num1 + " / " + num2 + " = " + result);

            } catch (ArithmeticException e) {
                System.out.println("Error: Cannot divide by zero. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter valid integers only.");
            }
        }
        scanner.close();
    }
}
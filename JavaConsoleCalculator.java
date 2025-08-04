package Task1;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaConsoleCalculator {

   private static final Logger logger = Logger.getLogger((JavaConsoleCalculator.class.getName()));
   private static final double MAX_NUMBER = 1e12;  // 1 trillion Limit
    private static final int MAX_CALCULATIONS = 1000;

    // Method for addintion
    public static double add( double a, double b) {
        return a + b;
    }

    // Method for Subtraction
    public static double subtract( double a, double b) {
        return a - b;
    }

    // Method for  multiplication
    public static double multiply( double a, double b) {
        if (Math.abs(a) > 1e150 && Math.abs(b) > 1e150) {
            logger.warning("Multiplication overflow risk detected");
            return Double.NaN;
        }
        return a * b;
    }

    // Method for division
    public static double divide( double a, double b) {
        if (b == 0) {
            logger.warning("Divide by Zero attempt.");
            System.out.println("Error: Division by zero is not allowed.");
            return Double.NaN;
        }
        return a / b;
    }

    // Validate number within safe range
    private static boolean isNumberSafe(double num) {
        return Math.abs(num) <= MAX_NUMBER;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalc = true;
        int calculationCount = 0;

        logger.info("Calculator session started.");
        System.out.println("=== Secure Java Console Calculator ===");

        while (continueCalc) {
            if (calculationCount >= MAX_CALCULATIONS) {
                System.out.println("MAx calculation limit reached. Please restart the calculator.");
                logger.warning("Calculation limit reached");
                break;
            }

            try {
                System.out.println("\nChoose an operation:");
                System.out.println("1. Addition (+)");
                System.out.println("2. Subtraction (-)");
                System.out.println("3. Multiplication (*)");
                System.out.println("4. Division (/)");
                System.out.println("5. Exit");

                System.out.print("Enter your choice (1-5):");

                int choice = scanner.nextInt();

                if (choice == 5) {
                    continueCalc = false;
                    System.out.println("Existing Calculator. Goodbye!");
                    break;
                }
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice. Please select a valid option.");
                    continue;
                }

                System.out.print("Enter first number: ");
                double num1 = scanner.nextDouble();
                if (!isNumberSafe(num1)) {
                    System.out.println("First number is too large. Please enter a value below " + MAX_NUMBER);
                    continue;
                }

                System.out.print("Enter second number: ");
                double num2 = scanner.nextDouble();
                if (!isNumberSafe(num2)) {
                    System.out.println("Second number is too large. Please enter a value below " + MAX_NUMBER);
                    continue;
                }

                double result;
                calculationCount++;

                switch (choice) {
                    case 1:
                        result = add(num1, num2);
                        System.out.printf("Result: %4f%n", result);
                        logger.info("Performed addition.");
                        break;
                    case 2:
                        result = subtract(num1, num2);
                        System.out.printf("Result: %4f%n", result);
                        logger.info("Performed subtraction.");
                        break;

                    case 3:
                        result = subtract(num1, num2);
                        if (!Double.isNaN(result)) {
                            System.out.printf("Result: %4f%n", result);
                        }
                        logger.info("Performed multiplication.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input Please enter numeric value only.");
                logger.log(Level.WARNING, "Invalid numeric input detected", e);

                scanner.nextLine(); // clear invalid input
            }
        }
        scanner.close();

    }

}


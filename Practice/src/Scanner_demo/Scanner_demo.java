package Scanner_demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Scanner_demo {
    public static void main(String[] args) {

        // Try-with-resources ensures the scanner is closed automatically
        try (Scanner scanner = new Scanner(System.in)) {

            // Get integer input
            System.out.print("Enter an integer: ");
            int number = Integer.parseInt(scanner.nextLine());

            // Get string input after integer
            System.out.print("Enter a string: ");
            String text = scanner.nextLine();

            // Output results
            System.out.println("You entered the number: " + number);
            System.out.println("You entered the string: " + text);

            //if throw
            if (number < 0) {
                throw new RuntimeException("You number can't be negative.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        } catch (InputMismatchException e) {
            System.out.println("Input type mismatch. Please try again.");
        }
        // No need for a finally block because try-with-resources closes the scanner automatically
    }
}


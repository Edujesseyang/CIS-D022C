package palindrome;

import java.util.Objects;
import java.util.Scanner;

/**
 * palindrome checker
 * Check if user input is a palindrome string.
 * @author Jesse Yang
 * @version 1.0
 */
public class palindrome {
    // define a scanner
    static Scanner sc = new Scanner(System.in);

    // main enter start here
    public static void main(String[] args) {
        // print user welcome information and usage
        System.out.println("::: Welcome to palindrome checker :::");
        System.out.println(" This program will ask you to enter a string, then tells you if it is a palindrome string.");
        // define a ready checker and use it for continuing the while loop
        System.out.println("Are you ready to start? (y/n)");
        String isReady = sc.next();
        sc.nextLine();
        // start the main process
        while (Objects.equals(isReady, "y") || Objects.equals(isReady, "Y")) {
            // get input
            String userIn = getUserInput(sc);
            // make a stack
            LinkedStack<Character> stack = createStack(userIn);
            // make a queue
            LinkedQueue<Character> queue = createQueue(userIn);
            // compare them and display result
            displayResult(compare(stack, queue));
            // ask if user wants to continue
            System.out.println("Do you want to check another one? (y/n)");
            // update the ready checker variable
            isReady = sc.next();
            sc.nextLine();
        }
        // print the goodbye information
        System.out.println("Thank you for using! Bye-bye");
    }

    /**
     * get an input string from user
     *
     * @param sc: scanner
     * @return string: user input
     */
    public static String getUserInput(Scanner sc) {
        String input = "";
        while (input.isEmpty()) {
            // prompt the input
            System.out.println("Please enter the string that you want to check: ");
            input = sc.nextLine();
            // remove all white space
            input = input.replaceAll("\\s", "");
            // prompt user if input is empty or all whitespaces.
            if (input.isEmpty()) {
                System.out.println("String can't be empty.");
            }
        }
        // remove all symbols
        input = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return input;
    }

    /**
     * make a character stack from a string
     *
     * @param input: a string
     * @return a character stack
     */
    public static LinkedStack<Character> createStack(String input) {
        LinkedStack<Character> returnStuck = new LinkedStack<Character>();
        for (int i = 0; i < input.length(); i++) {
            returnStuck.push(input.charAt(i));
        }
        return returnStuck;
    }

    /**
     * make a character queue from a string
     *
     * @param input: a string
     * @return a character queue
     */
    public static LinkedQueue<Character> createQueue(String input) {
        LinkedQueue<Character> returnQueue = new LinkedQueue<Character>();
        for (int i = 0; i < input.length(); i++) {
            returnQueue.enqueue(input.charAt(i));
        }
        return returnQueue;
    }

    /**
     * checker, return true/false, check for is stack and queue are match
     * @param stack: character stack
     * @param queue: character queue
     * @return boolean: if they are matched
     */
    public static boolean compare(LinkedStack<Character> stack, LinkedQueue<Character> queue) {
        boolean isPalindrome = true;
        for (int i = 0; i < stack.getSize(); i++) {
            if (stack.pop() != queue.dequeue()) {
                isPalindrome = false;
            }
        }
        return isPalindrome;
    }

    /**
     * print out result information to user
     *
     * @param result: boolean
     */
    public static void displayResult(boolean result) {
        if (result) {
            System.out.println("Yes! This string is a palindrome string.");
        } else {
            System.out.println("No! This string is NOT a palindrome string.");
        }
    }
}

package Recursion.Factorial;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the number you want to factorial: ");
            int num = sc.nextInt();
            System.out.println("The result is: " + factorial(num));
        }

    }

    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}

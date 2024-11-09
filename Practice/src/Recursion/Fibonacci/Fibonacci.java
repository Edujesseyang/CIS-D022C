package Recursion.Fibonacci;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        int len = 0;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the length of your fibonacci sequence: ");
            len = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }

        List<Integer> fiboList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            fiboList.add(fibonacci(i));
        }
        System.out.println(fiboList);

    }

    public static int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}

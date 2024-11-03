package FansBook;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Launcher {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        User u1 = createUser(sc);

        u1.printUser();
        System.out.println(u1.getIdNum());

    }

    public static void signOn() {

    }

    public static User createUser(Scanner sc) {
        String name = null;
        String password = "";
        String info = "";
        try {
            System.out.println("Welcome to sign up a new user:");
            System.out.println("Please enter your name: ");
            name = sc.nextLine();

            while (Objects.equals(password, "")) {
                System.out.println("Please enter your password: ");
                password = sc.nextLine();
                if (password.length() > 10) {
                    System.out.println("Password is too long!!");
                    password = "";
                }
            }
            System.out.println("Please enter you basic information:");
            info = sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }

        return new User(name, password, info);
    }

    public static void logIn() {

    }
}

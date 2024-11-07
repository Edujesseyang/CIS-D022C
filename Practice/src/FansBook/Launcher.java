package FansBook;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Launcher {
    static Scanner sc = new Scanner(System.in);
    static UserDB mainUserDB = new UserDB();

    public static void main(String[] args) {

        User du1 = new User("Chloe", "00000", "Hello, im chloe");
        User du2 = new User("Lucky", "00000", "Meaw!");
        User u1 = new User("Jesse", "12345", "im jc");

        u1.printUser();
        u1.getFriendsList().printList();
        u1.addFriend(du2);
        u1.addFriend(du1);
        u1.getFriendsList().printList();
        u1.deleteFriend("Lucky");
        u1.getFriendsList().printList();


    }

    public static void signOn() {

    }

    public static User createUser(Scanner sc) {
        String name = null;
        String password = "";
        String info = "";
        try {
            System.out.println("Welcome to sign up a new user:");
            while (true) {
                System.out.println("Please enter your name: ");
                name = sc.nextLine();
                if (mainUserDB.contain(name)) {
                    System.out.println("This user name is already existed, change another one.");
                } else {
                    break;
                }
            }
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
        User newUser = new User(name, password, info);
        mainUserDB.add(newUser);
        return newUser;
    }

    public static void logIn() {

    }
}

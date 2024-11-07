package FansBook;

import java.util.Scanner;


public class Launcher {
    static Scanner sc = new Scanner(System.in);
    static UserDB mainUserDB = new UserDB();
    static User currentUser = null;

    public static void main(String[] args) {
        // default user for testing:
        // we can use them to test add friend or search user.
        User u1 = new User("Chloe", "00000");
        u1.setContactInfo("6566251256");
        u1.setPersonInfo("Hello, I am Chloe, Im 34, I like dance!");
        User u2 = new User("Lucky", "00000");
        u2.setContactInfo("6566253525");
        u2.setPersonInfo("Hello, I am Lucky, Im a 6 year old cat, I eat too much!");
        User u3 = new User("Jesse", "12345");
        u3.setContactInfo("12332626525");
        u3.setPersonInfo("Hello, I am Jesse, Im software engineering student, I love coding!");
        User u4 = new User("Mike", "12345");
        u4.setContactInfo("14155525");
        u4.setPersonInfo("Hello, I am Mike, Im 64 year old, I love running!");
        mainUserDB.add(u1);
        mainUserDB.add(u2);
        mainUserDB.add(u3);
        mainUserDB.add(u4);

        // main menu:
        while (true) {
            System.out.println("\n********** Welcome to Fansbook **********");
            System.out.println("Menu:");
            System.out.println("1. Log in.");
            System.out.println("2. Sign up.");
            System.out.println("3. Help.");
            System.out.println("4. Exit.");
            int selection = selection(4);
            if (selection == 1) {
                login(sc);
                loginMenu(sc);
            }
            if (selection == 2) {
                createUser(sc);
            }
            if (selection == 3) {
                help();
            }
            if (selection == 4) {
                System.out.println("\n*** Thank you for using, hope you have a wonderful day! ***\n");
                break;
            }
        }
    }

    public static int selection(int numOfChoice) {
        int selection;
        while (true) {
            System.out.println("Enter your selection:");
            selection = sc.nextInt();
            sc.nextLine();
            if (selection >= 1 && selection <= numOfChoice) {
                break;
            }
            System.out.println("Please enter correct option.\n");
        }
        return selection;
    }

    public static void login(Scanner sc) {
        System.out.println("\n*** Welcome to log in ***");
        while (true) {
            System.out.print("User name: ");
            String userName = sc.nextLine();
            System.out.println();
            currentUser = mainUserDB.findUser(userName);
            if (currentUser != null) {
                break;
            }
            System.out.println("User name not found.");
        }
        while (true) {
            System.out.print("Password: ");
            String password = sc.nextLine();
            System.out.println();
            if (currentUser.getPassword().equals(password)) {
                System.out.println("\n--------------------------------------------------\n\nLog in successful!\nWelcome back!");
                break;
            }
            System.out.println("Incorrect Password, please try again!\n");
        }
    }

    public static void loginMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--------------------------------------------------\nHi, " + currentUser.getName());
            System.out.println("\nPlease select option: ");
            System.out.println("1. Edit my profile.");
            System.out.println("2. Search another user.");
            System.out.println("3. Print your information.");
            System.out.println("4. Go to friend list.");
            System.out.println("5. Log out.");
            int select = selection(5);
            if (select == 1) {
                editInfo(sc);
            }
            if (select == 2) {
                searchUser(sc);
            }
            if (select == 3) {
                System.out.println("\n--------------------------------------------------\n");
                currentUser.printUser();
            }
            if (select == 4) {
                friendMenu(sc);
            }
            if (select == 5) {
                logout();
                break;
            }
        }
    }

    public static void friendMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--------------------------------------------------\nFriend List Menu:");
            System.out.println("1. View your friend list:");
            System.out.println("2. Delete a friend:");
            System.out.println("3. View your friend's information:");
            System.out.println("4. Go back.");

            int select = selection(4);

            if (select == 1) {
                System.out.println("\n--------------------------------------------------\n");
                currentUser.printFriendList();
            }
            if (select == 2) {
                System.out.println("Enter the name your want to delete:");
                String name = sc.nextLine();
                if (currentUser.deleteFriend(name)) {
                    System.out.println("Friend Deleted.");
                } else {
                    System.out.println("Friend not found.");
                }
            }
            if (select == 3) {
                System.out.println("Enter the name your want to view:");
                String name = sc.nextLine();
                currentUser.printFriend(name);
            }
            if (select == 4) {
                return;
            }
        }
    }

    public static void searchUser(Scanner sc) {

        System.out.println("\n--------------------------------------------------\nEnter the username you want to search:");
        String name = sc.nextLine();
        User target = mainUserDB.findUser(name);
        if (target == null) {
            System.out.println("\nUser not found!");
        } else {
            while (true) {
                System.out.println("\n--------------------------------------------------\nUser found: \n1. Add to friend list.\n2. View information.\n3. Go back.");
                int confirm = sc.nextInt();
                sc.nextLine();
                if (confirm == 1) {
                    if (currentUser.getFriendsList().contain(name)) {
                        System.out.println("Friend already in your list:");

                    } else {
                        currentUser.getFriendsList().add(target);
                        System.out.println("Friend added.");
                    }
                    break;
                }
                if (confirm == 2) {
                    target.printUser();
                }
                if (confirm == 3) {
                    break;
                }
            }
        }
    }

    public static void editInfo(Scanner sc) {
        System.out.println("\n--------------------------------------------------\n1. Change password.");
        System.out.println("2. Edit personal information.");
        System.out.println("3. Edit contact information.");

        int select = selection(3);
        if (select == 1) {
            while (true) {
                System.out.println("Enter new password:");
                String password = sc.nextLine();
                System.out.println("Confirm password:");
                String confirm = sc.nextLine();
                if (password.equals(confirm)) {
                    System.out.println("Password changed!");
                    currentUser.setPassword(password);
                    break;
                }
            }
        }
        if (select == 2) {
            System.out.println("Enter your information:");
            String Info = sc.nextLine();
            currentUser.setPersonInfo(Info);
            System.out.println("Saved");
        }
        if (select == 3) {
            System.out.println("Enter your contact:");
            String Info = sc.nextLine();
            currentUser.setContactInfo(Info);
            System.out.println("Saved");
        }
    }

    public static void logout() {
        System.out.println("\n--------------------------------------------------\nThank you for using.\n");
        currentUser = null;
    }

    public static void createUser(Scanner sc) {
        String name;
        String password = "";

        System.out.println("\n--------------------------------------------------\nWelcome to sign up a new user:");
        while (true) {
            System.out.println("Please enter your name: ");
            name = sc.nextLine();
            if (mainUserDB.contain(name)) {
                System.out.println("This user name is already existed, change another one.");
            } else {
                break;
            }
        }
        while (password.isEmpty()) {
            System.out.println("Please enter your password: ");
            password = sc.nextLine();
            if (password.length() > 10) {
                System.out.println("Password is too long!!");
                password = "";
            }
            System.out.println("Please confirm your password:");
            String confirm = sc.nextLine();
            if (!confirm.equals(password)) {
                System.out.println("They are not match!");
                password = "";
            }
        }

        User newUser = new User(name, password);
        mainUserDB.add(newUser);
        System.out.println("... User created successfully, backing to main menu...");
    }

    public static void help() {
        System.out.println("""
                ---------------------------------------------------------------
                This program allow user to create an account,
                with a username and password, after login account successfully.
                User can edit personal information, contact information,
                and search other user by the username.
                And also add other user to the friend list.
                ---------------------------------------------------------------
                """);
    }

}

package FansBook;

import java.util.Scanner;


public class Launcher {
    static Scanner sc = new Scanner(System.in);
    static UserDB mainUserDB = new UserDB();
    static User currentUser = null;
    static ConnectionGraph allUserNet = new ConnectionGraph();
    static final String ADMIN_PASSWORD = "12345";

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
        allUserNet.addVertex(u1);
        allUserNet.addVertex(u2);
        allUserNet.addVertex(u3);
        allUserNet.addVertex(u4);

        // add few connections to try connection.
        allUserNet.connect(u1, u2);
        u1.addConnection(new Edge(u1, u2));
        allUserNet.connect(u2, u3);
        u2.addConnection(new Edge(u2, u3));
        allUserNet.connect(u2, u1);
        u2.addConnection(new Edge(u2, u1));
        allUserNet.connect(u3, u4);
        u3.addConnection(new Edge(u3, u4));
        allUserNet.connect(u4, u2);
        u4.addConnection(new Edge(u4, u2));


        // main menu:
        while (true) {
            System.out.println("\n********** Welcome to Fansbook **********");
            System.out.println("Menu:");
            System.out.println("1. Log in.");
            System.out.println("2. Sign up.");
            System.out.println("3. Help.");
            System.out.println("4. admin.");
            System.out.println("5. Exit.");
            int selection = selection(5);
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
                admin(sc);
            }
            if (selection == 5) {
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
            System.out.println("\n--------------------------------------------------\n");
            currentUser.printFriendList();
            System.out.println("\n1. Select a friend");
            System.out.println("2. Go back.");
            int choose = selection(2);
            if (choose == 1) {
                System.out.println("Select a friend by enter name:");
                String name = sc.nextLine();
                User friend = currentUser.getFriendsList().findFriend(name);
                if (friend == null) {
                    System.out.println("Can't find this user...");
                    break;
                } else {
                    while (true) {
                        System.out.println("\n\nUser: " + friend.getName());
                        System.out.println("1. Print information.");
                        System.out.println("2. Print this friend's connection.");
                        System.out.println("3. Delete this friend.");
                        System.out.println("4. Go back.");

                        int choice = selection(4);

                        if (choice == 1) {
                            friend.printUser();
                        } else if (choice == 2) {
                            System.out.println("Connections of " + name + ": ");
                            friend.printConnection();
                        } else if (choice == 3) {
                            if (currentUser.deleteFriend(name)) {
                                allUserNet.disconnect(currentUser, friend);
                                currentUser.removeConnection(currentUser, friend);
                                System.out.println("Friend: " + name + " is deleted.");
                                break;
                            }
                        } else if (choice == 4) {
                            break;
                        }
                    }
                }
            } else if (choose == 2) {
                break;
            }
        }
    }


    public static void searchUser(Scanner sc) {

        System.out.println("\n--------------------------------------------------\n");
        System.out.println("Enter the username you want to search:\n");
        String name = sc.nextLine();
        User target = mainUserDB.findUser(name);
        if (target == null) {
            System.out.println("\nUser not found!");
        } else {
            while (true) {
                System.out.println("\n--------------------------------------------------\n");
                System.out.println("User found: ");
                System.out.println("1. Add to friend list.");
                System.out.println("2. View information.");
                System.out.println("3. Go back.\n");
                int confirm = sc.nextInt();
                sc.nextLine();
                if (confirm == 1) {
                    if (currentUser.getFriendsList().contain(name)) {
                        System.out.println("Friend already in your list:");

                    } else {
                        currentUser.getFriendsList().add(target);
                        allUserNet.connect(currentUser, target);
                        currentUser.addConnection(new Edge(currentUser, target));
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
        allUserNet.addVertex(newUser);
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

    public static void admin(Scanner sc) {
        System.out.println("Enter you admin password:");
        String password = sc.nextLine();
        if (!password.equals(ADMIN_PASSWORD)) {
            System.out.println("Password is wrong!");
            return;
        }
        while (true) {

            int select = selection(3);

            System.out.println("\n\nWelcome to admin:");
            System.out.println("1. Print all users.");
            System.out.println("2. Print all connections.");
            System.out.println("3. GO back.\n");

            if (select == 1) {
                mainUserDB.print();
            } else if (select == 2) {
                allUserNet.printAllEdges();
            }

        }
    }
}


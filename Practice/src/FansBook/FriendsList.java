package FansBook;

public class FriendsList {
    private User head;
    private int numOfFriends = 0;


    public boolean add(User friend) {
        if (numOfFriends == 0) {
            head = friend;
        } else {
            User current = head;
            while (current.getNextUser() != null) {
                current = current.getNextUser();
            }
            current.setNextUser(friend);
        }
        numOfFriends++;
        return true;
    }

    public boolean delete(String name) {
        if (numOfFriends == 0) {
            return false;
        }
        User current = head;
        if (current.getName().equals(name)) {
            head = current.getNextUser();
            current.setNextUser(null);
            numOfFriends--;
            return true;
        }

        while (current.getNextUser() != null) {
            if (current.getNextUser().getName().equals(name)) {
                User toDelete = current.getNextUser();
                current.setNextUser(toDelete.getNextUser());
                toDelete.setNextUser(null);
                numOfFriends--;
                return true;
            }
            current = current.getNextUser();
        }

        return false;
    }

    public boolean contain(String name) {
        User current = head;
        while (current != null) {
            if (current.getName().equals(name)) {
                return true;
            }
            current = current.getNextUser();
        }
        return false;
    }

    public User findFriend(String name) {
        User current = head;
        while (current != null) {
            if (current.getName().equals(name)) {
                return current;
            }
            current = current.getNextUser();
        }
        return null;
    }

    public void printList() {
        System.out.println("Friends List: " + numOfFriends + " friends.");
        if (head == null) {
            System.out.println("You don't have any friend.");
            return;
        }
        User current = head;
        while (current != null) {
            System.out.println(current.getName());
            current = current.getNextUser();
        }
        System.out.println();
    }

    public void printItem(String name) {
        if (!this.contain(name)) {
            System.out.println("No fired found.");
            return;
        }
        User current = head;
        while (current != null) {
            if (current.getName().equals(name)) {
                current.printUser();
            }
            current = current.getNextUser();
        }
    }
}

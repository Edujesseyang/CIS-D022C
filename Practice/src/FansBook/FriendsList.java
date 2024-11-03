package FansBook;

public class FriendsList {
    private User head;
    private int numOfFriends = 0;

    public User getHead() {
        return head;
    }

    public void setHead(User head) {
        this.head = head;
    }

    public int getNumOfFriends() {
        return numOfFriends;
    }

    public void setNumOfFriends(int numOfFriends) {
        this.numOfFriends = numOfFriends;
    }

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

    public void printList() {
        System.out.println("Friends List: " + numOfFriends);
        if (head == null) {
            System.out.println("You don't have any friend.");
            return;
        }
        User current = head;
        while (current != null) {
            System.out.print(current.getName() + ", ");
            current = current.getNextUser();
        }
        System.out.println();
    }
}

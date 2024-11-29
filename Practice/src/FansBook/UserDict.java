package FansBook;


public class UserDict {
    private static class Pair {
        private User user;
        private String name;
        private Pair next;

        public Pair() {

        }

        public Pair(String name, User user) {
            this.name = name;
            this.user = user;
            this.next = null;
        }

    }

    private Pair head;
    private int size = 0;

    public void add(String name, User user) {
        Pair newNode = new Pair(name, user);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
        Pair current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
    }

    public boolean remove(String name) {
        if (head == null) {
            return false;
        }
        Pair current = head;
        if (current.name.equals(name)) {
            current = current.next;
            size--;
        }
        while (current.next != null) {
            if (current.next.name.equals(name)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public boolean contain(String name) {
        if (head == null) {
            return false;
        }
        Pair current = head;
        while (current != null) {
            if (current.name.equals(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public User getUser(String name) {
        if (head == null) {
            return null;
        }
        Pair current = head;
        while (current != null) {
            if (current.name.equals(name)) {
                return current.user;
            }
            current = current.next;
        }
        return null;
    }

}

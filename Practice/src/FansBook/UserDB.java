package FansBook;

import java.util.Arrays;

/**
 *  User database, this is for storing all users.
 */

public class UserDB {
    private static final int[] PRIME_LIST = {
            17, 31, 53, 71, 97, 127, 167, 211, 277, 359, 467, 607,
            787, 1021, 1321, 1721, 2239, 2903, 3779, 4909, 6389, 8317,
            10037, 13051, 16967, 22061, 28687, 37319, 48521, 63077, 82013, 106631};
    private static final double LOAD_FACTOR = 0.7;
    private int primeIndex;
    private User[] hashTable;
    private int numOfUser;
    private int tableSlotOccupied;

    public UserDB() {
        this.numOfUser = 0;
        this.tableSlotOccupied = 0;
        this.hashTable = new User[PRIME_LIST[primeIndex]];
    }

    public void rehash() {
        double loadRate = (double) numOfUser / tableSlotOccupied;
        if (loadRate > LOAD_FACTOR && primeIndex < PRIME_LIST.length - 1) {

            User[] newTable = new User[PRIME_LIST[++primeIndex]];
            User[] oldTable = hashTable;
            hashTable = newTable;

            for (User user : oldTable) {
                User current = user;
                while (current != null) {
                    add(current);
                    current = current.getNextUser();
                }
            }
        }
    }

    public int getHashedIndex(String key) {
        int result = 0;
        for (Character c : key.toCharArray()) {
            result = result * 31 + c;
        }
        return Math.abs(result % PRIME_LIST[primeIndex]);
    }

    public void add(User newUser) {
        if (primeIndex < PRIME_LIST.length - 1) {
            rehash();
        }
        int hashedIndex = getHashedIndex(newUser.getName());
        if (hashTable[hashedIndex] == null) {
            hashTable[hashedIndex] = newUser;
            tableSlotOccupied++;
        } else {
            User current = hashTable[hashedIndex];
            while (current.getNextUser() != null) {
                current = current.getNextUser();
            }
            current.setNextUser(newUser);
        }
        numOfUser++;
    }

    public boolean contain(String name) {
        int hashIndex = getHashedIndex(name);
        User current = hashTable[hashIndex];
        while (current != null) {
            if (current.getName().equals(name)) {
                return true;
            }
            current = current.getNextUser();
        }
        return false;
    }

    public User findUser(String name) {
        int hashIndex = getHashedIndex(name);
        User current = hashTable[hashIndex];
        while (current != null) {
            if (current.getName().equals(name)) {
                return current;
            }
            current = current.getNextUser();
        }
        return null;
    }

    public boolean deleteUser(String name) {
        int hashIndex = getHashedIndex(name);
        User current = hashTable[hashIndex];
        User previous = null;
        while (current != null) {
            if (current.getName().equals(name)) {
                if (previous == null) {
                    hashTable[hashIndex] = current.getNextUser();
                    if (hashTable[hashIndex] == null) {
                        tableSlotOccupied--;
                    }
                } else {
                    previous.setNextUser(current.getNextUser());
                }
                current.setNextUser(null);
                numOfUser--;
                return true;
            }
            previous = current;
            current = current.getNextUser();
        }
        return false;
    }

    public void clear() {
        Arrays.fill(hashTable, null);
        numOfUser = 0;
        tableSlotOccupied = 0;
    }

    public void print() {
        for (User user : hashTable) {
            User current = user;
            while (current != null) {
                current.printUser();
                current = current.getNextUser();
            }
        }
    }

}

package AList;

import java.util.Arrays;

public class AList<T> implements ListInterface<T> {
    private T[] list; // Array of list entries; ignore list[0]
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    // constructors
    public AList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public AList(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("ERROR: Capacity oversize.");
        } else {
            list = (T[]) new Object[capacity];
            numberOfEntries = 0;
            initialized = true;
        }

    }

    /**
     * Adds a new entry to the end of this list.
     * Entries currently in the list are unaffected.
     * The list's size is increased by 1.
     *
     * @param newEntry The object to be added as a new entry.
     */
    @Override
    public void add(T newEntry) {
        checkInitialization();
        if (list.length == numberOfEntries) {
            int capacity = list.length + 1;
            list = Arrays.copyOf(list, capacity);
            list[capacity - 1] = newEntry;
        } else {
            list[numberOfEntries] = newEntry;
        }
        numberOfEntries++;
    }

    /**
     * Adds a new entry at a specified position within this list.
     * Entries originally at and above the specified position
     * are at the next higher position within the list.
     * The list's size is increased by 1.
     *
     * @param newPosition An integer that specifies the desired
     *                    position of the new entry.
     * @param newEntry    The object to be added as a new entry.
     * @throws IndexOutOfBoundsException if either
     *                                   newPosition less than 1, or
     *                                   newPosition greater than getLength()+1.
     */
    @Override
    public void add(int newPosition, T newEntry) {
        checkInitialization();
        if (newPosition < 0 || newPosition > numberOfEntries + 1) {
            throw new IndexOutOfBoundsException("ERROR: index out of bounds.");
        }
        if (list.length >= numberOfEntries) {
            int capacity = list.length + 1;
            list = Arrays.copyOf(list, capacity);
        }
        for (int i = numberOfEntries; i > newPosition; i--) {
            list[i] = list[i - 1];
        }
        list[newPosition] = newEntry;
        numberOfEntries++;
    }

    /**
     * Removes the entry at a given position from this list.
     * Entries originally at positions higher than the given
     * position are at the next lower position within the list,
     * and the list's size is decreased by 1.
     *
     * @param givenPosition An integer that indicates the position of
     *                      the entry to be removed.
     * @return A reference to the removed entry.
     * @throws IndexOutOfBoundsException if either
     *                                   givenPosition less than 1, or
     *                                   givenPosition greater than getLength()+1.
     */
    @Override
    public T remove(int givenPosition) {
        checkInitialization();
        if (givenPosition < 0 || givenPosition > numberOfEntries) {
            throw new IndexOutOfBoundsException("ERROR: index out of bounds.");
        }
        T deletedElement = list[givenPosition];
        for (int i = givenPosition; i < numberOfEntries - 1; i++) {
            list[i] = list[i + 1];
        }
        list[numberOfEntries - 1] = null;
        numberOfEntries--;
        return deletedElement;
    }

    /**
     * Removes all entries from this list.
     */
    @Override
    public void clear() {
        checkInitialization();
        list = Arrays.copyOf(list, 0);
        numberOfEntries = 0;
    }

    /**
     * Replaces the entry at a given position in this list.
     *
     * @param givenPosition An integer that indicates the position of the
     *                      entry to be replaced.
     * @param newEntry      The object that will replace the entry at the
     *                      position givenPosition.
     * @return The original entry that was replaced.
     * @throws IndexOutOfBoundsException if either
     *                                   givenPosition less than 1, or
     *                                   givenPosition greater than getLength()+1.
     */
    @Override
    public T replace(int givenPosition, T newEntry) {
        checkInitialization();
        T replacedElement = list[givenPosition];
        list[givenPosition] = newEntry;
        return replacedElement;
    }

    /**
     * Retrieves the entry at a given position in this list.
     *
     * @param givenPosition An integer that indicates the position of
     *                      the desired entry.
     * @return A reference to the indicated entry.
     * @throws IndexOutOfBoundsException if either
     *                                   givenPosition less than 1, or
     *                                   givenPosition greater than getLength()+1.
     */
    @Override
    public T getEntry(int givenPosition) {
        checkInitialization();
        if (givenPosition < 0 || givenPosition > numberOfEntries) {
            throw new IndexOutOfBoundsException("ERROR: index out of bounds.");
        }
        return list[givenPosition];
    }

    /**
     * Sees whether this list contains a given entry.
     *
     * @param anEntry The object that is the desired entry.
     * @return True if the list contains anEntry, or false if not.
     */
    @Override
    public boolean contains(T anEntry) {
        checkInitialization();
        for (T e : list) {
            if (anEntry.equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the length of this list.
     *
     * @return The integer number of entries currently in the list.
     */
    @Override
    public int getLength() {
        return numberOfEntries;
    }

    /**
     * Sees whether this list is empty.
     *
     * @return True if the list is empty, or false if not.
     */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    /**
     * Retrieves all entries that are in this list in the order in which
     * they occur in the list.
     *
     * @return A newly allocated array of all the entries in the list.
     */
    @Override
    public T[] toArray() {
        checkInitialization();
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = list[index];
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "{ ";
        for (int i = 0; i < numberOfEntries; i++) {
            result = result + "<" + list[i] + "> ";
        }
        result = result + "}";
        return result;
    }

    // Doubles the capacity of the array list if it is full.
    // Precondition: checkInitialization has been called.
    private void ensureCapacity() {
        int capacity = list.length - 1;
        if (numberOfEntries >= capacity) {
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity); // Is capacity too big?
            list = Arrays.copyOf(list, newCapacity + 1);
        } // end if
    } // end ensureCapacity


    // Makes room for a new entry at newPosition.
    // Precondition: 1 <= newPosition <= numberOfEntries + 1;
    // numberOfEntries is list's length before addition;
    // checkInitialization has been called.
    private void makeRoom(int newPosition) {
        assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);

        int newIndex = newPosition;
        int lastIndex = numberOfEntries;

        // Move each entry to next higher index, starting at end of
        // list and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--)
            list[index + 1] = list[index];
    } // end makeRoom


    // Shifts entries that are beyond the entry to be removed to the
    // next lower position.
    // Precondition: 1 <= givenPosition < numberOfEntries;
    // numberOfEntries is list's length before removal;
    // checkInitialization has been called.
    private void removeGap(int givenPosition) {
        assert (givenPosition >= 1) && (givenPosition < numberOfEntries);

        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;

        for (int index = removedIndex; index < lastIndex; index++)
            list[index] = list[index + 1];
    } // end removeGap


    // Throws an exception if this object is not initialized.
    private void checkInitialization() {
        if (!initialized)
            throw new SecurityException("AList object is not initialized properly.");
    } // end checkInitialization


    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a list " +
                    "whose capacity exceeds " +
                    "allowed maximum.");
    } // end checkCapacity


}


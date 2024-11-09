package BagClient;

import java.util.Arrays;

public class ArrayBag<T> implements BagInterface<T> {
    private int numOfEntries;
    private T[] masterArray;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    @SuppressWarnings("unchecked")
    public ArrayBag() {
        numOfEntries = 0;
        masterArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Gets the current number of entries in this bag.
     *
     * @return The integer number of entries currently in the bag.
     */
    @Override
    public int getCurrentSize() {
        return numOfEntries;
    }

    /**
     * Sees whether this bag is empty.
     *
     * @return True if the bag is empty, or false if not.
     */
    @Override
    public boolean isEmpty() {
        return numOfEntries == 0;
    }

    /**
     * Adds a new entry to this bag.
     *
     * @param newEntry The object to be added as a new entry.
     * @return True if the addition is successful, or false if not.
     */
    @Override
    public boolean add(T newEntry) {
        if (numOfEntries >= masterArray.length) {
            if (numOfEntries * 2 <= MAX_CAPACITY) {
                masterArray = Arrays.copyOf(masterArray, masterArray.length * 2);
            } else {
                throw new RuntimeException("Array Over size.");
            }
        }
        masterArray[numOfEntries] = newEntry;
        numOfEntries++;
        return true;
    }

    /**
     * Removes one unspecified entry from this bag, if possible.
     *
     * @return Either the removed entry, if the removal.
     * Was successful, or null.
     */
    @Override
    public T remove() {
        if (numOfEntries == 0) {
            return null;
        }
        T removedEntry = masterArray[numOfEntries - 1];
        masterArray[numOfEntries - 1] = null;
        numOfEntries--;
        return removedEntry;
    }

    /**
     * Removes one occurrence of a given entry from this bag.
     *
     * @param anEntry The entry to be removed.
     * @return True if the removal was successful, or false if not.
     */
    @Override
    public boolean remove(T anEntry) {
        for (int i = 0; i < numOfEntries; i++) {
            if (masterArray[i].equals(anEntry)) {
                for (int j = i; j < numOfEntries - 1; j++) {
                    masterArray[j] = masterArray[j + 1];
                }
                masterArray[numOfEntries - 1] = null;
                numOfEntries--;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all entries from this bag.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        masterArray = (T[]) new Object[DEFAULT_CAPACITY];
        numOfEntries = 0;
    }

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in the bag.
     */
    @Override
    public int getFrequencyOf(T anEntry) {
        int count = 0;
        for (int i = 0; i < numOfEntries; i++) {
            if (masterArray[i].equals(anEntry)) {
                count++;
            }
        }
        return count;

    }

    /**
     * Tests whether this bag contains a given entry.
     *
     * @param anEntry The entry to locate.
     * @return True if the bag contains anEntry, or false if not.
     */
    @Override
    public boolean contains(T anEntry) {
        for (int i = 0; i < numOfEntries; i++) {
            if (masterArray[i].equals(anEntry)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves all entries that are in this bag.
     *
     * @return A newly allocated array of all the entries in the bag.
     * Note: If the bag is empty, the returned array is empty.
     */
    @Override
    public T[] toArray() {
        return masterArray;
    }

    @Override
    public String toString() {
        String result = "{";
        if (numOfEntries == 0) {
            result += "}";
            return result;
        }
        for (int i = 0; i < numOfEntries - 1; i++) {
            result += masterArray[i] + ", ";
        }
        result += masterArray[numOfEntries - 1];
        result += "}";
        return result;
    }
}

package LinkedBag;

class Node<T> {
    private T data;
    private Node<T> next;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return this.data;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}

public class LinkedBag<T> implements BagInterface<T> {

    private Node<T> firstNode;
    private int numOfEntries;

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
        return firstNode == null;
    }

    /**
     * Adds a new entry to this bag.
     *
     * @param newEntry The object to be added as a new entry.
     * @return True if the addition is successful, or false if not.
     */
    @Override
    public boolean add(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);
        if (numOfEntries == 0) {
            firstNode = newNode;
        } else {
            Node<T> current = firstNode;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        numOfEntries++;
        return true;
    }

    /**
     * Removes one unspecified entry from this bag, if possible.
     *
     * @return Either the removed entry, if the removal.
     * was successful, or null.
     */
    @Override
    public T remove() {
        if (firstNode == null) {
            return null;
        }
        Node<T> current = firstNode;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        T data = current.getData();
        current.setNext(null);
        return data;
    }

    /**
     * Removes one occurrence of a given entry from this bag.
     *
     * @param anEntry The entry to be removed.
     * @return True if the removal was successful, or false if not.
     */
    @Override
    public boolean remove(T anEntry) {
        if (firstNode == null) {
            return false;
        }

        if (firstNode.getData().equals(anEntry)) {
            Node<T> target = firstNode;
            firstNode = firstNode.getNext();
            target.setNext(null);
            numOfEntries--;
            return true;
        }

        Node<T> current = firstNode;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(anEntry)) {
                Node<T> target = current.getNext();
                Node<T> after = target.getNext();
                current.setNext(after);
                target.setNext(null);
                numOfEntries--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Removes all entries from this bag.
     */
    @Override
    public void clear() {
        firstNode = null;
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
        if (firstNode == null) {
            return 0;
        }
        int itemCount = 0;
        Node<T> current = firstNode;
        while (current != null) {
            if (current.getData().equals(anEntry)) {
                itemCount++;
            }
            current = current.getNext();
        }
        return itemCount;
    }

    /**
     * Tests whether this bag contains a given entry.
     *
     * @param anEntry The entry to locate.
     * @return True if the bag contains anEntry, or false if not.
     */
    @Override
    public boolean contains(T anEntry) {
        if (firstNode == null) {
            return false;
        }
        Node<T> current = firstNode;
        while (current != null) {
            if (current.getData().equals(anEntry)) {
                return true;
            }
            current = current.getNext();
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
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        Object[] array = new Object[numOfEntries];

        if (firstNode == null) {
            return (T[]) array;
        }

        Node<T> current = firstNode;
        for (int i = 0; i < numOfEntries; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }
        return (T[]) array;
    }


}

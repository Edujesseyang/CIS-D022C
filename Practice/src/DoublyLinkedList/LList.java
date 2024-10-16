package DoublyLinkedList;

import java.lang.reflect.Array;

class Node<T> {
    private T data;
    private Node<T> next;
    private Node<T> previous;

    public Node() {
        // Empty constructor, next and previous remain null by default
    }

    public Node(T data) {
        this.data = data;
        // next and previous remain null by default for a single node
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public T getData() {
        return this.data;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public Node<T> getPrevious() {
        return this.previous;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}

public class LList<T> implements ListInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int numOfEntries;
    // I add this final variable, it can be used to change it simply to convert indexing based on between 0 ro 1
    private final int INDEX_BASED_ON = 1;

    public LList() {
        this.head = null;
        this.tail = null;
        this.numOfEntries = 0;
    }

    /**
     * Adds a new entry to the end of this list.
     * Entries currently in the list are unaffected.
     * The list's size increased by 1.
     *
     * @param newEntry The object to be added as a new entry.
     */

    @Override
    public void add(T newEntry) {
        // create a new node with input data
        Node<T> newNode = new Node<>(newEntry);
        // handle the case if the list is empty
        if (numOfEntries == 0) {
            head = newNode;
        } else { // handle the case if the list is not empty
            tail.setNext(newNode);
            newNode.setPrevious(tail);
        }
        // update tail
        tail = newNode;
        // update num of entries
        numOfEntries++;
    }

    /**
     * Adds a new entry at a specified position within this list.
     * Entries originally at and above the specified position
     * are at the next higher position within the list.
     * The list's size increased by 1.
     *
     * @param index An integer that specifies the desired
     *                    position of the new entry.
     * @param data    The object to be added as a new entry.
     * @throws IndexOutOfBoundsException if either
     *                                   newPosition less than 1, or
     *                                   newPosition greater than getLength()+1.
     */

    @Override
    public void add(int index, T data) {
        if (INDEX_BASED_ON == 1) {
            index -= INDEX_BASED_ON;
        }
        // throw exception if index out of bounds
        if (index < 0 || index > numOfEntries) {
            throw new IndexOutOfBoundsException("Out of bounds!");
        }
        // create a new node
        Node<T> newNode = new Node<>(data);
        // handle if add to the last
        if (index == 0) { // handle if add to the first
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                head.setPrevious(newNode);
                newNode.setNext(head);
                head = newNode;
            }

        } else if (index == numOfEntries) {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;


        } else { // adding to anywhere else than first and last
            // handle if adding to the first half
            if (index <= numOfEntries / 2) {
                // define current
                Node<T> currentNode = head;
                // traversing to the index
                for (int i = 0; i < index - 1; i++) {
                    currentNode = currentNode.getNext();
                }
                // define nextNode
                Node<T> nextNode = currentNode.getNext();
                // link new note to current and nextNode
                newNode.setPrevious(currentNode);
                newNode.setNext(nextNode);
                // link both ends to the new node
                nextNode.setPrevious(newNode);
                currentNode.setNext(newNode);
            } else { // handle if index is in the last half
                // define current as tail
                Node<T> currentNode = tail;
                // traversing from the tail to index
                for (int i = 0; i < numOfEntries - index - 1; i++) {
                    currentNode = currentNode.getPrevious();
                }
                // define pre of current
                Node<T> preNode = currentNode.getPrevious();
                // link new node to both sides
                newNode.setPrevious(preNode);
                newNode.setNext(currentNode);
                // link both sides to the new node
                currentNode.setPrevious(newNode);
                preNode.setNext(newNode);
            }
        }
        // update the num of entries
        numOfEntries++;
    }

    /** Removes the entry at a given position from this list.
     * Entries originally at positions higher than the given
     * position are at the next lower position within the list,
     * and the list's size is decreased by 1.
     * @param index An integer that indicates the position of
     * the entry to be removed.
     * @return A reference to the removed entry.
     * @throws IndexOutOfBoundsException if either
     *    givenPosition less than 1, or
     *    givenPosition greater than getLength()+1.
     */
    public T remove(int index) {
        if (INDEX_BASED_ON == 1) {
            index -= INDEX_BASED_ON;
        }
        // throw exception if index out of bounds
        if (index < 0 || index >= numOfEntries) {
            throw new IndexOutOfBoundsException("Index Out of Bounds");
        }

        // handle the case that removing the first entry
        if (index == 0) {
            T removed = head.getData();
            // handle the case that there is only one entry
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                // define the new head
                Node<T> first = head.getNext();
                // disconnect new head's pre
                first.setPrevious(null);
                // disconnect old head's next
                head.setNext(null);
                // set head is new head
                head = first;
            }
            // update num of entries
            numOfEntries--;
            // return after the job done
            return removed;
        }

        // handle the case that removing last entry
        if (index == numOfEntries - 1) {
            T removed = tail.getData();
            // define the new tail
            Node<T> last = tail.getPrevious();
            // disconnect new tail's next
            last.setNext(null);
            // disconnect old tail's pre
            tail.setPrevious(null);
            // set tail is new tail
            tail = last;
            // update num of entries
            numOfEntries--;
            // return after the job done
            return removed;
        }

        // define 3 nodes,
        Node<T> current;
        Node<T> before;
        Node<T> after;
        // handle the case if index is in first half
        if (index < numOfEntries / 2) {
            // traverse from the head
            current = head;
            // traverse to one node before the one needs to be removed
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else { // handle the case if the removing node is in the last half
            // traverse from the tail
            current = tail;
            // traverse to the node after the one needs to be removed
            for (int i = 0; i < numOfEntries - index - 1; i++) {
                current = current.getPrevious();
            }
        }
        T removed = current.getData();
        // set before and current
        before = current.getPrevious();
        after = current.getNext();

        // connecting before to after
        before.setNext(after);
        after.setPrevious(before);
        // disconnect current both side
        current.setNext(null);
        current.setPrevious(null);

        // update num of entries
        numOfEntries--;
        return removed;
    }

    /**
     * Removes all entries from this list.
     */
    public void clear() {
        if (head != null) {
            head.setNext(null);
        }
        if (tail != null) {
            tail.setPrevious(null);
        }
        head = null;
        tail = null;
        numOfEntries = 0;
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

    public T replace(int givenPosition, T newEntry) {
        if (INDEX_BASED_ON == 1) {
            givenPosition -= INDEX_BASED_ON;
        }
        if (givenPosition < 0 || givenPosition >= numOfEntries) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        T originalData;
        if (givenPosition == 0) {
            originalData = head.getData();
            head.setData(newEntry);
            return originalData;
        }
        if (givenPosition == numOfEntries - 1) {
            originalData = tail.getData();
            tail.setData(newEntry);
            return originalData;
        }

        Node<T> current;
        // handle the case if index is in first half
        if (givenPosition < numOfEntries / 2) {
            // traverse from the head
            current = head;
            // traverse to one node before the one needs to be removed
            for (int i = 0; i < givenPosition; i++) {
                current = current.getNext();
            }
        } else { // handle the case if the removing node is in the last half
            // traverse from the tail
            current = tail;
            // traverse to the node after the one needs to be removed
            for (int i = 0; i < numOfEntries - givenPosition - 1; i++) {
                current = current.getPrevious();
            }
        }
        originalData = current.getData();
        current.setData(newEntry);
        return originalData;
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

    public T getEntry(int givenPosition) {
        if (INDEX_BASED_ON == 1) {
            givenPosition -= INDEX_BASED_ON;
        }
        if (givenPosition < 0 || givenPosition >= numOfEntries) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (givenPosition == 0) {
            return head.getData();
        }
        if (givenPosition == numOfEntries - 1) {
            return tail.getData();
        }

        Node<T> current;
        // handle the case if index is in first half
        if (givenPosition < numOfEntries / 2) {
            // traverse from the head
            current = head;
            // traverse to one node before the one needs to be removed
            for (int i = 0; i < givenPosition; i++) {
                current = current.getNext();
            }
        } else { // handle the case if the removing node is in the last half
            // traverse from the tail
            current = tail;
            // traverse to the node after the one needs to be removed
            for (int i = 0; i < numOfEntries - givenPosition - 1; i++) {
                current = current.getPrevious();
            }
        }
        return current.getData();
    }


    /**
     * Sees whether this list contains a given entry.
     *
     * @param anEntry The object that is the desired entry.
     * @return True if the list contains anEntry, or false if not.
     */

    public boolean contains(T anEntry) {
        Node<T> current = head;
        for (int i = 0; i < this.numOfEntries - 1; i++) {
            if (current.getData().equals(anEntry)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Gets the length of this list.
     *
     * @return The integer number of entries currently in the list.
     */

    public int getLength() {
        return numOfEntries;
    }

    /**
     * Sees whether this list is empty.
     *
     * @return True if the list is empty, or false if not.
     */

    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Retrieves all entries that are in this list in the order in which
     * they occur in the list.
     *
     * @return A newly allocated array of all the entries in the list.
     */

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        if (numOfEntries == 0) {
            return (T[]) Array.newInstance(Object.class, 0);
        }
        Node<T> current = head;
        T[] array = (T[]) Array.newInstance(head.getData().getClass(), numOfEntries);
        for (int i = 0; i < numOfEntries; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }
        return array;
    }

    /**
     * Retrieves all entries that are in this list in the order in which
     * they occur in the list.
     *
     * @return A newly allocated array of all the entries in the list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        Node<T> currentNode = head;
        for (int i = 0; i < numOfEntries; i++) {
            if (currentNode != null) {
                result.append(currentNode.getData() != null ? currentNode.getData().toString() : "null");
                if (i < numOfEntries - 1) { // Add a separator for all but the last element
                    result.append(", ");
                }
                currentNode = currentNode.getNext();
            } else {
                result.append("null-node, ");
            }
        }
        result.append("}");
        return result.toString();
    }
}
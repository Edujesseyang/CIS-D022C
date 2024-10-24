package LinkedQueue;

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

    public String toString() {
        return data.toString();
    }
}

public class LinkedQueue<T> implements QueueInterface<T> {
    private int numOfEntries = 0;
    private Node<T> head;
    private Node<T> tail;

    // use default constructor.

    /**
     * Adds a new entry to the back of this queue.
     *
     * @param newEntry An object to be added.
     */
    @Override
    public void enqueue(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);
        if (numOfEntries == 0) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        numOfEntries++;
    }

    /**
     * Removes and returns the entry at the front of this queue.
     *
     * @return The object at the front of the queue.
     * @throws EmptyQueueException if the queue is empty before the operation.
     */
    @Override
    public T dequeue() {
        if (numOfEntries == 0) {
            throw new EmptyQueueException("Queue is empty!");
        }
        T returnData = head.getData();
        head = head.getNext();
        numOfEntries--;
        return returnData;
    }

    /**
     * Retrieves the entry at the front of this queue.
     *
     * @return The object at the front of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    @Override
    public T getFront() {
        if (numOfEntries == 0) {
            throw new EmptyQueueException("Queue is empty!");
        }
        return head.getData();
    }

    /**
     * Detects whether this queue is empty.
     *
     * @return True if the queue is empty, or false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return numOfEntries == 0;
    }

    /**
     * Removes all entries from this queue.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        numOfEntries = 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<T> current = head;
        while (current.getNext() != null) {
            result.append(current).append(", ");
            current = current.getNext();
        }
        result.append(current);
        return result + "]";
    }
}

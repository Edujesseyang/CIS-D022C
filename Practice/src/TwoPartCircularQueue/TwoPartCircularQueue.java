package TwoPartCircularQueue;

import java.util.Arrays;

public class TwoPartCircularQueue<T> implements QueueInterface<T> {
    private final int SIZE = 50;
    private T[] queue;

    private int front;
    private int rear;
    private int numOfEntries;

    @SuppressWarnings("unchecked")
    public TwoPartCircularQueue() {
        this.queue = (T[]) new Object[SIZE];
        front = 0;
        rear = 0;
        numOfEntries = 0;
    }

    /**
     * Adds a new entry to the back of this queue.
     *
     * @param newEntry An object to be added.
     */
    @Override
    public void enqueue(T newEntry) {
        if ((rear + 1) % SIZE == front) {
            throw new IllegalStateException("Queue is full. Cannot add new entry.");
        }
        queue[rear] = newEntry;
        rear = (rear + 1) % SIZE;
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
        if (front == rear) {
            throw new EmptyQueueException("Queue is empty. Cannot remove entry.");
        }
        T deleted = queue[front];
        queue[front] = null;
        front = (front + 1) % SIZE;
        numOfEntries--;
        return deleted;
    }

    /**
     * Retrieves the entry at the front of this queue.
     *
     * @return The object at the front of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty. Cannot retrieve entry.");
        }
        return queue[front];
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
        Arrays.fill(queue, null);
        front = 0;
        rear = 0;
        numOfEntries = 0;
    }
}

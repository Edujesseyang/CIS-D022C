package Deque;

public class LinkedDeque<T> implements DequeInterface<T> {
    class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> previous;

        public Node() {

        }

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }

        @Override
        public String toString() {
            return "<" + this.data + ">";
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int numOfEntries = 0;

    /**
     * Adds a new entry to the front/back of this dequeue.
     *
     * @param newEntry An object to be added.
     */
    @Override
    public void addToFront(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);
        if (numOfEntries == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        numOfEntries++;
    }

    @Override
    public void addToBack(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);
        if (numOfEntries == 0) {
            tail = newNode;
            head = newNode;
        } else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        numOfEntries++;
    }

    /**
     * Removes and returns the front/back entry of this dequeue.
     *
     * @return The object at the front/back of the dequeue.
     * @throws EmptyQueueException if the dequeue is empty before the operation.
     */
    @Override
    public T removeFront() {
        if (this.isEmpty()) {
            throw new EmptyQueueException("Queue is empty!");
        }
        T returnData = head.getData();
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        numOfEntries--;
        return returnData;
    }

    @Override
    public T removeBack() {
        if (this.isEmpty()) {
            throw new EmptyQueueException("Queue is empty!");
        }
        T returnData = tail.getData();
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
        }
        numOfEntries--;
        return returnData;
    }

    /**
     * Retrieves the front/back entry of this dequeue.
     *
     * @return The object at the front/back of the dequeue.
     * @throws EmptyQueueException if the dequeue is empty before the operation.
     */
    @Override
    public T getFront() {
        return this.head.getData();
    }

    @Override
    public T getBack() {
        return this.tail.getData();
    }

    @Override
    public boolean isEmpty() {
        return numOfEntries == 0;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        numOfEntries = 0;
    }
}

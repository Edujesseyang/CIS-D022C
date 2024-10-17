package LinkedStack;


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
}

public class LinkedStack<T> implements StackInterface<T> {

    private Node<T> topNode;
    private int numOfEntries = 0;


    /**
     * Adds a new entry to the top of this stack.
     *
     * @param newEntry An object to be added to the stack.
     */
    @Override
    public void push(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);
        newNode.setNext(topNode);
        topNode = newNode;
        numOfEntries++;
    }

    /**
     * Removes and returns this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty before the operation.
     */
    @Override
    public T pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        T data = topNode.getData();
        topNode = topNode.getNext();
        numOfEntries--;
        return data;
    }

    /**
     * Retrieves this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return topNode.getData();
    }

    /**
     * Detects whether this stack is empty.
     *
     * @return True if the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    /**
     * Removes all entries from this stack.
     */
    @Override
    public void clear() {
        topNode = null;
        numOfEntries = 0;
    }
}

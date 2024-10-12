package DoublyLinkedList;

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

    public Node(T data, Node<T> next, Node<T> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
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

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int numOfEntries;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.numOfEntries = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (this.numOfEntries == 0) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
        }
        tail = newNode;
        numOfEntries++;
    }

    public void add(int index, T data) {
        if (index < 0 || index > numOfEntries) {
            throw new IndexOutOfBoundsException("Out of bounds!");
        }
        Node<T> newNode = new Node<T>(data);
        if (index == numOfEntries) {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        } else if (index == 0) {
            head.setPrevious(newNode);
            newNode.setNext(head);
            head = newNode;
        } else {
            if (index <= numOfEntries / 2) {
                Node<T> currentNode = head;
                for (int i = 0; i < index; i++) {
                    currentNode = currentNode.getNext();
                }
                Node<T> nextNode = currentNode.getNext();
                newNode.setPrevious(currentNode);
                newNode.setNext(nextNode);
                nextNode.setPrevious(newNode);
                currentNode.setNext(newNode);
            } else {
                Node<T> currentNode = tail;
                for (int i = 0; i < numOfEntries - index; i++) {
                    currentNode = currentNode.getPrevious();
                }
                Node<T> preNode = currentNode.getPrevious();
                newNode.setPrevious(preNode);
                newNode.setNext(currentNode);
                currentNode.setPrevious(newNode);
                preNode.setNext(newNode);
            }
        }
        numOfEntries++;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder("DoublyLinkedList{head=" + head + ", tail=" + tail + ", numOfEntries={");

        Node<T> currentNode = head;
        for (int i = 0; i < numOfEntries; i++) {
            result.append(currentNode.getData().toString());
            if (i < numOfEntries - 1) { // Add a separator for all but the last element
                result.append(", ");
            }
            currentNode = currentNode.getNext();
        }

        result.append("}");
        return result.toString();


    }
}
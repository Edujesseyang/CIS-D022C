package LList;


public class LList<T> implements ListInterface<T> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this(data, null);
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return this.data;
        }

        public Node getNext() {
            return this.next;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node firstNode;
    private int numOfEntries;

    // constructor
    public LList() {
        firstNode = null;
        numOfEntries = 0;
    }

    @Override
    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (numOfEntries == 0) {
            firstNode = newNode;
        } else {
            Node currentNode = firstNode;
            while (currentNode.next != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
        numOfEntries++;
    }

    @Override
    public void add(int newPosition, T newEntry) {
        if (newPosition <= 0 || newPosition > numOfEntries + 1) {
            throw new IndexOutOfBoundsException("ERROR: out of bounds.");
        } else {
            Node newNode = new Node(newEntry);
            if (newPosition == 1) {
                newNode.setNext(firstNode);
                firstNode = newNode;
            } else {
                Node currentNode = firstNode;
                for (int i = 1; i < newPosition - 1; i++) {
                    currentNode = currentNode.getNext();
                }
                newNode.setNext(currentNode.getNext());
                currentNode.setNext(newNode);
            }
            numOfEntries++;
        }
    }

    @Override
    public T remove(int givenPosition) {
        if (givenPosition < 1 || givenPosition > numOfEntries) {
            throw new IndexOutOfBoundsException("ERROR: out of bounds");
        }
        T returnData;
        if (givenPosition == 1) {
            returnData = firstNode.getData();
            firstNode = firstNode.getNext();
        } else {
            Node currentNode = firstNode;
            for (int i = 1; i < givenPosition - 1; i++) {
                currentNode = currentNode.getNext();
            }
            Node nodeToRemove = currentNode.getNext();
            returnData = nodeToRemove.getData();
            currentNode.setNext(nodeToRemove.getNext());
        }
        return returnData;
    }

    @Override
    public void clear() {
        firstNode = null;
        numOfEntries = 0;
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        if (givenPosition < 1 || givenPosition > numOfEntries) {
            throw new IndexOutOfBoundsException("ERROR: out of bounds");
        }
        Node currentNode = firstNode;
        for (int i = 1; i < givenPosition; i++) {
            currentNode = currentNode.getNext();
        }
        T returnData = currentNode.getData();
        currentNode.setData(newEntry);
        return returnData;
    }

    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition < 1 || givenPosition > numOfEntries) {
            throw new IndexOutOfBoundsException("ERROR: out of bounds");
        }
        Node currentNode = firstNode;
        for (int i = 1; i < givenPosition; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }

    @Override
    public boolean contains(T anEntry) {
        Node currentNode = firstNode;
        while (currentNode.getNext() != null) {
            if (currentNode.getData().equals(anEntry)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Override
    public int getLength() {
        int len = 0;
        if (firstNode == null) {
            return len;
        } else {
            Node currentNode = firstNode;
            while (currentNode != null) {
                len++;
                currentNode = currentNode.getNext();
            }
        }
        return len;
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] result = (T[]) new Object[numOfEntries];
        Node currentNode = firstNode;
        int i = 0;
        while (currentNode != null) {
            result[i] = currentNode.getData();
            i++;
            currentNode = currentNode.getNext();
        }
        return result;
    }


}

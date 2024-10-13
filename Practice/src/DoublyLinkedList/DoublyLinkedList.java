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
        // create a new node with input data
        Node<T> newNode = new Node<>(data);
        // handle the case if the list is empty
        if (this.numOfEntries == 0) {
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

    public void add(int index, T data) {
        // throw exception if index out of bounds
        if (index < 0 || index > numOfEntries) {
            throw new IndexOutOfBoundsException("Out of bounds!");
        }
        // create a new node
        Node<T> newNode = new Node<T>(data);
        // handle if add to the last
        if (index == numOfEntries) {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        } else if (index == 0) { // handle if add to the first
            head.setPrevious(newNode);
            newNode.setNext(head);
            head = newNode;
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

    public void remove(int index) {
        // throw exception if index out of bounds
        if (index < 0 || index >= numOfEntries) {
            throw new IndexOutOfBoundsException("Index Out of Bounds");
        }

        // handle the case that removing the first entry
        if (index == 0) {
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
            return;
        }

        // handle the case that removing last entry
        if (index == numOfEntries - 1) {
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
            return;
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
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        Node<T> currentNode = head;
        for (int i = 0; i < numOfEntries; i++) {
            if (currentNode != null) {
                result.append(currentNode.getData().toString());
                if (i < numOfEntries - 1) { // Add a separator for all but the last element
                    result.append(", ");
                }
                currentNode = currentNode.getNext();
            } else {
                result.append("null, ");
            }
        }

        result.append("}");
        return result.toString();


    }
}
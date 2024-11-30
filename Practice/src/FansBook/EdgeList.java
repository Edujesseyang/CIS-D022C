package FansBook;

public class EdgeList {
    private Edge head;
    private Edge tail;
    private int size = 0;

    public boolean add(Edge newEdge) {
        if (newEdge == null) {
            return false;
        }

        if (head == null) {
            head = newEdge;
        } else {
            tail.setNext(newEdge);
        }
        tail = newEdge;
        size++;
        return true;
    }

    public boolean remove(Edge edge) {
        if (edge == null) {
            throw new IllegalArgumentException("Edge cannot be null");
        }

        if (head == null) {
            return false;
        }

        if (head.equals(edge)) {
            head = head.getNext();
            size--;
            return true;
        }

        Edge previous = head;
        Edge current = head.getNext();

        while (current != null) {
            if (current.equals(edge)) {
                previous.setNext(current.getNext());
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    public boolean remove(User begin, User end) {

        if (head == null) {
            return false;
        }

        if (head.getStartVertex().equals(begin) && head.getEndVertex().equals(end)) {
            head = head.getNext();
            size--;
            return true;
        }

        Edge previous = head;
        Edge current = head.getNext();

        while (current != null) {
            if (current.getStartVertex().equals(begin) && current.getEndVertex().equals(end)) {
                previous.setNext(current.getNext());
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean contain(Edge edge) {
        if (head == null) {
            return false;
        }
        Edge current = head;
        while (current != null) {
            if (current.equals(edge)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public Edge findEdge(User begin, User end) {
        if (head == null) {
            return null;
        }
        Edge current = head;
        while (current != null) {
            if (current.getStartVertex() == begin && current.getEndVertex() == end) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public void print() {
        if (head == null) {
            System.out.println("There is no connection.");
            return;
        }
        Edge current = head;
        while (current != null) {
            System.out.println(current.getStartVertex().getName() + " --> " + current.getEndVertex().getName());
            current = current.getNext();
        }
    }
}

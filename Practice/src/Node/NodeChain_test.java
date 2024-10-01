package Node;

public class NodeChain_test {
    public static void main(String[] args) {
        Node<Integer> i5 = new Node<>(5, null);
        Node<Integer> i4 = new Node<Integer>(4, i5);
        Node<Integer> i3 = new Node<Integer>(3, i4);
        Node<Integer> i2 = new Node<Integer>(2, i3);
        Node<Integer> i1 = new Node<Integer>(1, i2);
        i5.setNext(i1); // set the tail pointing the head.

        Node<Integer> current = i1;
        for (int i = 0; i < 100; i++) {
            current.print();
            current = current.getNext();
        }
    }
}
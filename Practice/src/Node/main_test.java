package Node;

public class main_test {
    public static void main(String[] args) {
        Node<String> n5 = new Node<>("!");
        Node<String> n4 = new Node<String>("?", n5);
        Node<String> n3 = new Node<String>("World", n4);
        Node<String> n2 = new Node<String>(",", n3);
        Node<String> n1 = new Node<String>("Hello", n2);
        printNodes(n1);

        Node<String> n2a = new Node<String>("Java", n3);
        n2.setNext(n2a);
        System.out.println("After inserted a node...");
        printNodes(n1);

    }

    public static <T> void printNodes(Node<T> start) {
        while (start != null) {
            start.print();
            start = start.getNext();
        }
        System.out.println();
    }
}

package DoublyLinkedList;


public class test {
    public static void main(String[] args) {
        DoublyLinkedList<String> myList = new DoublyLinkedList<String>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("5");
        myList.add("6");
        System.out.println(myList);
        myList.add(4, "4");
        System.out.println(myList);

        DoublyLinkedList<String> myList2 = new DoublyLinkedList<String>();
        myList2.add("2");
        myList2.add("3");
        myList2.add("4");
        myList2.add("5");
        myList2.add("6");
        System.out.println(myList2);
        myList2.add(1, "1");
        System.out.println(myList2);

        DoublyLinkedList<String> myList3 = new DoublyLinkedList<String>();
        myList3.add("1");
        myList3.add("2");
        myList3.add("3");
        myList3.add("4");
        myList3.add("5");
        myList3.add("6");
        System.out.println(myList3);
        myList3.add(7, "7");
        System.out.println(myList3);

    }
}

package DoublyLinkedList;


public class test {
    public static void main(String[] args) {
        System.out.println("************************* Testing adding ****************************");
        DoublyLinkedList<String> myList = new DoublyLinkedList<String>();
        myList.add("0");
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("5");
        myList.add("6");

        System.out.println("Target list:\n" + myList + "\t\t(Missing 4)");
        myList.add(4, "4");
        System.out.println("After adding to the missing index:\n" + myList);

        DoublyLinkedList<String> myList2 = new DoublyLinkedList<String>();
        myList2.add("0");
        myList2.add("2");
        myList2.add("3");
        myList2.add("4");
        myList2.add("5");
        myList2.add("6");
        System.out.println("Target list:\n" + myList2 + "\t\t(Missing 1)");
        myList2.add(1, "1");
        System.out.println("After adding to the missing index:\n" + myList2);

        DoublyLinkedList<String> myList3 = new DoublyLinkedList<String>();
        myList3.add("0");
        myList3.add("1");
        myList3.add("2");
        myList3.add("3");
        myList3.add("4");
        myList3.add("5");
        myList3.add("6");
        System.out.println("Target list:\n" + myList3 + "\t\t(Missing 7)");
        myList3.add(7, "7");
        System.out.println("After adding to the missing index:\n" + myList3);

        DoublyLinkedList<String> myList4 = new DoublyLinkedList<String>();
        myList4.add("0");
        myList4.add("1");
        myList4.add("3");
        myList4.add("4");
        myList4.add("5");
        myList4.add("6");
        myList4.add("7");
        System.out.println("Target list:\n" + myList4 + "\t\t(Missing 2)");
        myList4.add(2, "2");
        System.out.println("After adding to the missing index:\n" + myList4);

        System.out.println("\n******************** Test remove ******************************");
        DoublyLinkedList<String> myList5 = new DoublyLinkedList<String>();
        myList5.add("0");
        myList5.add("1");
        myList5.add("2");
        myList5.add("3");
        myList5.add("4");
        myList5.add("5");
        myList5.add("6");
        myList5.add("7");
        System.out.println("Old list: " + myList5);
        myList5.remove(0);
        System.out.println("After removed 0 : " + myList5);

        DoublyLinkedList<String> myList6 = new DoublyLinkedList<String>();
        myList6.add("0");
        myList6.add("1");
        myList6.add("2");
        myList6.add("3");
        myList6.add("4");
        myList6.add("5");
        myList6.add("6");
        myList6.add("7");
        System.out.println("Old list: " + myList6);
        myList6.remove(7);
        System.out.println("After removed 7 : " + myList6);

        DoublyLinkedList<String> myList7 = new DoublyLinkedList<String>();
        myList7.add("0");
        myList7.add("1");
        myList7.add("2");
        myList7.add("3");
        myList7.add("4");
        myList7.add("5");
        myList7.add("6");
        myList7.add("7");
        System.out.println("Old list: " + myList7);
        myList7.remove(2);
        System.out.println("After removed 2 : " + myList7);

        DoublyLinkedList<String> myList8 = new DoublyLinkedList<String>();
        myList8.add("0");
        myList8.add("1");
        myList8.add("2");
        myList8.add("3");
        myList8.add("4");
        myList8.add("5");
        myList8.add("6");
        myList8.add("7");
        System.out.println("Old list: " + myList8);
        myList8.remove(5);
        System.out.println("After removed 5 : " + myList8);

    }
}

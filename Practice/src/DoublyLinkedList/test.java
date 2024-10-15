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

        DoublyLinkedList<String> myList9 = new DoublyLinkedList<String>();
        myList9.add("0");
        myList9.add("1");
        myList9.add("2");
        myList9.add("3");
        myList9.add("4");
        myList9.add("5");
        myList9.add("6");
        myList9.add("7");
        myList9.add("8");
        System.out.println("Old list: " + myList9);
        myList9.remove(4);
        System.out.println("After removed 4 : " + myList9);

        System.out.println("\n******************** Test replace ******************************");
        DoublyLinkedList<String> myList10 = new DoublyLinkedList<String>();
        myList10.add("0");
        myList10.add("1");
        myList10.add("2");
        myList10.add("3");
        myList10.add("4");
        myList10.add("5");
        myList10.add("6");
        myList10.add("7");
        myList10.add("8");
        System.out.println("Old list: " + myList10);
        myList10.replace(0, "replaced");
        System.out.println("After replaced 0 : " + myList10);
        myList10.replace(1, "replaced");
        System.out.println("After replaced 1 : " + myList10);
        myList10.replace(2, "replaced");
        System.out.println("After replaced 2 : " + myList10);
        myList10.replace(4, "replaced");
        System.out.println("After replaced 4 : " + myList10);
        myList10.replace(7, "replaced");
        System.out.println("After replaced 7 : " + myList10);
        myList10.replace(6, "replaced");
        System.out.println("After replaced 6 : " + myList10);
        myList10.replace(8, "replaced");
        System.out.println("After replaced 8 : " + myList10);

        System.out.println("\n******************** Test contain ******************************");
        boolean isContain = myList10.contains("3");
        boolean isContain2 = myList10.contains("5");
        boolean isContain3 = myList10.contains("4");
        System.out.println("Is 3 in last list: " + isContain);
        System.out.println("Is 5 in last list: " + isContain2);
        System.out.println("Is 4 in last list: " + isContain3);

        System.out.println("\n******************** Test getEntry ******************************");
        String e1 = myList10.getEntry(3);
        String e2 = myList10.getEntry(5);
        String e3 = myList10.getEntry(7);
        System.out.println("entry at 3: " + e1);
        System.out.println("entry at 3: " + e2);
        System.out.println("entry at 3: " + e3);

        System.out.println("\n******************** Test getLength ******************************");
        System.out.println("List 1 : " + myList.getLength());
        System.out.println("List 2 : " + myList2.getLength());
        System.out.println("List 3 : " + myList4.getLength());
        System.out.println("List 4 : " + myList10.getLength());

        System.out.println("\n******************** Test toArray ******************************");
        String[] array = myList10.toArray();
        System.out.println("Array address: " + array);
        System.out.print("Array elements: ");
        for (String s : array) {
            System.out.print("{" + s + "}, ");
        }
        System.out.println();

        System.out.println("\n******************** Test clear and isEmpty ******************************");
        myList10.clear();
        System.out.println(myList10);
        System.out.println("Is list empty: " + myList10.isEmpty());


    }
}

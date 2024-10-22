package ArrayBag;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        System.out.println("****************** ArrayBag Test *******************");
        System.out.println("\nTesting constructor: ");
        BagInterface<String> a1 = new ArrayBag<String>();
        System.out.println("constructed");
        System.out.println("\nTesting add(x):");
        a1.add("Hello");
        a1.add("world");
        a1.add("How");
        a1.add("are");
        a1.add("you");
        System.out.println(a1);
        System.out.println("\nTesting getCurrentSize():");
        System.out.println("Size=" + a1.getCurrentSize());
        System.out.println("\nTesting remove():");
        System.out.println("removing: '" + a1.remove() + "'");
        System.out.println(a1);
        System.out.println("\nTesting remove(x):");
        System.out.println("remove complete: " + a1.remove("How"));
        System.out.println(a1);
        System.out.println("\nTesting contains(x):");
        System.out.println("Is bag contains 'world' : " + a1.contains("world"));
        System.out.println("Is bag contains 'Hello' : " + a1.contains("Hello"));
        System.out.println("Is bag contains 'you' : " + a1.contains("you"));
        System.out.println("\nTesting getFreq(x):");
        a1.add("you");
        a1.add("you");
        a1.add("you");
        a1.add("are");
        a1.add("are");
        a1.add("you");
        a1.add("Hello");
        System.out.println(a1);
        System.out.println("How many times 'you' presented: " + a1.getFrequencyOf("you"));
        System.out.println("How many times 'Hello' presented: " + a1.getFrequencyOf("Hello"));
        System.out.println("How many times 'are' presented: " + a1.getFrequencyOf("are"));
        System.out.println("How many times 'How' presented: " + a1.getFrequencyOf("How"));
        System.out.println("\nTesting toArray():");
        Object[] array = a1.toArray();
        System.out.println(Arrays.toString(array));
        System.out.println("\nTesting clear(): ");
        a1.clear();
        System.out.println(a1);
        System.out.println("The size: " + a1.getCurrentSize());
        System.out.println("\nTest re-size feature: ");
        for (int i = 0; i < 31; i++) {
            a1.add("<" + i + ">");
        }
        System.out.println(a1);
        Object[] array1 = a1.toArray();
        System.out.println(Arrays.toString(array1));
        System.out.println("The capacity is now: " + array1.length);
    }
}

package AList;

public class test {
    public static void main(String[] args) {
        AList<Integer> list1 = new AList<Integer>(5);
        list1.add(0);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        System.out.println(list1);

        list1.add(3, 15);
        System.out.println(list1);
        list1.add(5, 20);
        System.out.println(list1);
        list1.add(8, 99);
        System.out.println(list1);
        list1.remove(3);
        System.out.println(list1);


        Object[] array = list1.toArray();
        for (Object i : array) {
            System.out.print(i + ", ");
        }
        System.out.println();

    }


}

package Counter;

public class test {
    public static void main(String[] args) {
        Counter c1 = new Counter(10, 20);
        c1.increase();
        c1.increase();
        c1.increase();
        c1.increase();
        c1.increase();
        System.out.println(c1);
        c1.increase();
        c1.increase();
        c1.increase();
        c1.increase();
        c1.increase();
        c1.increase();
        System.out.println(c1);
        System.out.println(c1.rolledOver());
    }
}

package CreateTriangle;

public class CreateTriangle {
    public static void main(String[] args) {
        printTriangle('*', 10);
        printTriangle('*', 0);
        printTriangle('0', 1);
        printTriangle('X', 4);

        try {
            printTriangle('*', -1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printTriangle(char symbol, int level) {
        if (level < 0) {
            throw new IllegalArgumentException("level can't be negative.");
        }
        if (level == 0) {
            System.out.println();
        }
        if (level > 0) { // goto deepest recursive level
            printTriangle(symbol, level - 1);
        }
        for (int i = 0; i < level; i++) {  // print from lower to upper
            System.out.print(symbol);
        }
        System.out.println();  // break line
    }

}

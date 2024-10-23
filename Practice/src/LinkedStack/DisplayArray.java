package LinkedStack;


public class DisplayArray {
    public static void main(String[] args) {

        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Character[] array2 = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        String[] array3 = {"one", "two", "three", "four", "five", "six"};

        System.out.println("\nDisplaying array: ");
        displayArrayRecursively(array, 0, array.length - 1);
        System.out.println("\nDisplaying backward: ");
        displayArrayWithStack(array, 0, array.length - 1);

        System.out.println("\nDisplaying array: ");
        displayArrayRecursively(array2, 0, array2.length - 1);
        System.out.println("\nDisplaying backward: ");
        displayArrayWithStack(array2, 0, array2.length - 1);

        System.out.println("\nDisplaying array: ");
        displayArrayRecursively(array3, 0, array3.length - 1);
        System.out.println("\nDisplaying backward: ");
        displayArrayWithStack(array3, 0, array3.length - 1);
    }

    /**
     * display an array
     *
     * @param start : starting index
     * @param end : ending index
     */
    public static <T> void displayArrayRecursively(T[] array, int start, int end) {
        if (start <= end) {
            displayArrayRecursively(array, start, end - 1);
            System.out.print(array[end] + ", ");
        }
    }

    /**
     * display and array backward
     *
     * @param start : starting index
     * @param end : ending index
     */
    public static <T> void displayArrayWithStack(T[] array, int start, int end) {
        if (start <= end) {
            StackInterface<T> stack = new LinkedStack<T>();
            for (int i = start; i <= end; i++) {
                stack.push(array[i]);
            }
            System.out.print(stack.pop() + ", ");
            displayArrayWithStack(array, start, end - 1);
        }
    }
}
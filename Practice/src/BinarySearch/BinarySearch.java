package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
        int test1 = 15;
        int test2 = 25;
        System.out.println("Test binary search method: ");
        verify(binarySearch(list1, test1));
        verify(binarySearch(list1, test2));
        System.out.println("Test binary search recursively method: ");
        System.out.println("Is " + test1 + " in the list? ");
        System.out.println(binarySearchRecursively(list1, test1));
        System.out.println("Is " + test2 + " in the list? ");
        System.out.println(binarySearchRecursively(list1, test2));

    }

    public static int binarySearch(List<Integer> list, Integer target) {
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else if (list.get(mid) > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static boolean binarySearchRecursively(List<Integer> list, Integer target) {
        if (list.isEmpty()) {
            return false;
        } else {
            int mid = list.size() / 2;
            if (list.get(mid) > target) {
                return binarySearchRecursively(list.subList(0, mid), target);
            } else if (list.get(mid) < target) {
                return binarySearchRecursively(list.subList(mid + 1, list.size()), target);
            } else {
                return true;
            }
        }
    }

    public static void verify(int result) {
        System.out.println(result == -1 ? "Can't find the number." : "The index of the number is: " + result);
    }
}

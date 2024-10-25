package perfectNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * A perfect number has a formula:
 *  (2^p - 1) * 2^(p - 1)
 *  and also satisfies that
 *  (2^p - 1) is a prime number.
 *  The range of p is from one to infinite
 */
public class PerfectNumber {
    public static void main(String[] args) {

        List<Integer> myList = findPerfectUpTo(1000000000);
        System.out.println(myList);

    }

    public static List<Integer> findPerfectUpTo(int n) {
        List<Integer> result = new ArrayList<>();
        int num;

        for (int i = 1; ; i++) {
            num = (int) (Math.pow(2, i - 1) * (Math.pow(2, i) - 1));
            if (num >= n) {
                break;
            }
            if (isMersennePrime(i)) {
                result.add(num);
            }

        }
        return result;
    }

    // Helper function to check if 2^i - 1 is prime (Mersenne prime)
    private static boolean isMersennePrime(int p) {
        int candidate = (int) (Math.pow(2, p) - 1);
        return isPrime(candidate);
    }

    // Helper function to check if a number is prime
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}

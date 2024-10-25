package findPrime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class findPrime {
    public static void main(String[] args) {

        List<Integer> primeList = allPrimesUpTo(50);
        System.out.println("Total: " + primeList.size() + "\t" + primeList);
        primeList = allPrimesUpTo(500);
        System.out.println("Total: " + primeList.size() + "\t" + primeList);
        primeList = allPrimesUpTo(5000);
        System.out.println("Total: " + primeList.size() + "\t" + primeList);
        primeList = allPrimesUpTo(50000);
        System.out.println("Total: " + primeList.size() + "\t" + primeList);

        System.out.println("Is 42221 is prime? \t" + isPrime(42221));

        System.out.println("Try perfect num: ");
        List<Integer> perfectNum = allPerfectNumUpTo(999999999);
        System.out.println(perfectNum);
    }

    public static List<Integer> allPrimesUpTo(int n) {
        // create a result list
        List<Integer> primes = new ArrayList<>();
        // return empty result is input less than 2
        if (n < 2) {
            return primes;
        }
        // create a boolean list to represent a number list
        boolean[] isPrime = new boolean[n + 1]; // add 1 to make sure we can handle n
        Arrays.fill(isPrime, true); // set all boolean to true as default

        // this loop is iterating from 2 to n, "i" is the index of boolean array
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                // if the index is set as prime, then add it to the result list;
                primes.add(i);
                // then iterate to n, from double of i, set all multiples of i to be false.
                for (int j = i * 2; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        // return result
        return primes;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> allPerfectNumUpTo(int n) {
        List<Integer> result = new ArrayList<>();
        int num = 0;
        for (int i = 0; ; i++) {
            if (num >= n) {
                break;
            }
            num = (int) ((Math.pow(2, i) - 1) * Math.pow(2, i - 1));
            if (isPrime((int) (Math.pow(2, i) - 1))) {
                result.add(num);
            }
        }
        return result;
    }
}

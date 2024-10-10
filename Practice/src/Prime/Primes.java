package Prime;

import AList.AList;
import AList.ListInterface;

import java.util.*;

/**
 * Primes is a program that will compute prime numbers using the sieve of Eratosthenes.
 *
 * @author Charles Hoot
 * @version 4.0
 */


public class Primes {

    public static void main(String[] args) {

        int max;

        System.out.println("Please enter the maximum value to test for primality");
        max = getInt("   It should be an integer value greater than or equal to 2.");

        // COMPLETE THE MAIN
        // create a target List to store int from 2 to max.
        ListInterface<Integer> targetList = new AList<Integer>();
        for (int i = 2; i <= max; i++) {
            targetList.add(i);
        }

        // print out the target list
        System.out.println("Target List: ");
        System.out.println(targetList);

        // create two empty lists to use for getComposite method.
        ListInterface<Integer> primeList = new AList<Integer>();
        ListInterface<Integer> compositeList = new AList<Integer>();

        // init the start prime index
        int primeIndex = 0;

        // loop through targetList
        while (primeIndex < targetList.getLength()) {
            // init and reset the prime we are working on
            Integer primeNum = targetList.getEntry(primeIndex);

            // add prime to the primeList
            primeList.add(primeNum);

            // call method to update targetList and compositeList
            getComposites(targetList, compositeList, primeNum);

            // increase primeIndex,
            primeIndex++;
        }

        // print out primeList
        System.out.println("Prime List: ");
        System.out.println(primeList);
    }


    /**
     * getComposites - Remove the composite values from a possible list and
     * put them in the composite list.
     *
     * @param  candidates   A list of integers holding the possible values.
     * @param  composites   A list of integers holding the composite values.
     * @param  prime   An Integer that is prime.
     */
    public static void getComposites(ListInterface<Integer> candidates, ListInterface<Integer> composites, Integer prime) {
        // COMPLETE THIS METHOD

        // iterate all candidate list
        for (int i = 0; i < candidates.getLength(); i++) {
            // init/reset the candidateNUm
            Integer candidateNum = candidates.getEntry(i);
            // check if candidateNum is not null, is not prime, is not equals to param prime.
            if (candidateNum != null && candidateNum % prime == 0 && !candidateNum.equals(prime)) {
                // add candidateNum into composite List
                composites.add(candidateNum);
                // remove candidate number after checked
                candidates.remove(i);
            }
        }


    }


    /**
     * Get an integer value.
     *
     * @return An integer.
     */
    private static int getInt(String rangePrompt) {
        Scanner input;
        int result = 10;        //Default value is 10
        try {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();

        } catch (NumberFormatException e) {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        } catch (Exception e) {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;

    }

}

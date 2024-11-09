package Recursion.Fibonacci;

/**
 * A class holding different recursive methods to compute fibonacci numbers.
 * 0, 1, 1, 2, 3, 5, 8, ...
 * 
 * @author Charles Hoot
 * @version 4.0
 */
public class RecursiveFibonacci
{


    /**
     * basic - The simple version of fibonacci.
     * 
     * @param  n   A positive integer. 
     * @return     The nth fibonacci number.
     */
    public long basic(long n)
    {
        long result = 1;
        
        if( n <= 0)
            result = 0;
        else if (n == 1)
            result = 1;
        else
            result = basic(n-1) + basic(n-2);
        
        return result;
    }
    
    
    /**
     * better - A better version of fibonacci. (Height Limited Double Recursion)
     * 
     * @param  n   A positive integer.
     * @return     The nth fibonacci number.
     */
    public long better(long n) {
        return betterHelper(n, new long[(int) n + 1]);
    }

    private long betterHelper(long n, long[] memo) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        // check if the num have had calc
        if (memo[(int) n] != 0) return memo[(int) n];

        // recur and save the result
        memo[(int) n] = betterHelper(n - 1, memo) + betterHelper(n - 2, memo);
        return memo[(int) n];
    }


    /**
     * tailRecursive - A tail recursive version of fibonacci. 
     *              (Height limited, Two problems per level)
     * 
     * @param  n   A positive integer. 
     * @return     Tge nth fibonacci number.
     */
    public long tailRecursive(long n) {
        return tailRecursiveHelper(n, 0, 1);
    }

    private long tailRecursiveHelper(long n, long a, long b) {
        if (n == 0) return a;
        if (n == 1) return b;
        return tailRecursiveHelper(n - 1, b, a + b);
    }
    

    /**
     * secondMSB - Determine the value of the second most significant bit.
     * 
     * @param  n   A positive integer 
     * @return     True if the second most significant bit is 1, false otherwise.
     */
    public boolean secondMSB(long n) {
        if (n <= 1) return false;

        int highestBit = Long.SIZE - Long.numberOfLeadingZeros(n) - 1;
        int secondMSBPosition = highestBit - 1;

        return (n & (1L << secondMSBPosition)) != 0;
    }


    /**
     * reduceBy2ndMSB - Reduce the number by removing the second most significant bit
     * from the representation.
     * 
     * @param  n   A positive integer > 1
     * @return     The integer value equivalent to removing the 2nd most significant bit
     *              from n.
     */    
    public long reduceBy2ndMSB(long n)
    {
        if (n <= 1) throw new IllegalArgumentException("n must be greater than 1");

        int highestBit = Long.SIZE - Long.numberOfLeadingZeros(n) - 1;
        int secondMSBPosition = highestBit - 1;

        long mask = ~(1L << secondMSBPosition);
        return n & mask;
    }
    
    
}

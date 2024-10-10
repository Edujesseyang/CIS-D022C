package Counter;

/**
 * The counter-class implements a counter that will roll over to the initial
 * value when it hits the maximum value.
 *
 * @author Charles Hoot
 * @version 4.0
 */
public class Counter {
    // PUT PRIVATE DATA FIELDS HERE
    private int num;
    private int min;
    private int max;
    private boolean isRollovere = false;

    /**
     * The default constructor for objects of class Counter. The Minimum is zero
     * and the maximum is the largest possible integer.
     */
    public Counter() {
        this.num = 0;
        this.min = 0;
        this.max = Integer.MAX_VALUE;
    }

    /**
     * The alternate constructor for objects of class Counter. The minimum and
     maximum values are given as parameters.
     * The counter starts at the minimum value.
     * @param min The minimum value that the counter can have
     * @param max The maximum value that the counter can have
     * */
    public Counter(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Minimum must be less than maximum.");
        }
        this.num = min;
        this.min = min;
        this.max = max;
    }

    /**
     * Determine if two counters are in the same state
     *
     * @param otherObject the object to test against for equality
     * @return true if the objects are in the same state
     */
    public boolean equals(Object otherObject) {
        boolean result = true;
        if (otherObject instanceof Counter otherCounter) {
            if (this.num != otherCounter.num || this.min != otherCounter.min || this.max != otherCounter.max) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Increases the counter by one
     */
    public void increase() {
        if (num < max) {
            num++;
            isRollovere = false;
        } else {
            num = min;
            isRollovere = true;
        }
    }

    /**
     * Decreases the counter by one
     */
    public void decrease() {
        if (num >= min) {
            num--;
            isRollovere = false;
        } else {
            num = max;
            isRollovere = true;
        }
    }

    /**
     * Get the value of the counter
     *
     * @return the current value of the counter
     */
    public int value() {
        return this.num;
    }

    /**
     * Accessor that allows the client to determine if the counter
     * rolled over on the last count
     *
     * @return true if the counter rolled over
     */
    public boolean rolledOver() {
        return this.isRollovere;
    }

    /**
     * Override the toString method to provide a more informative
     * description of the counter
     *
     * @return a descriptive string about the object
     */
    public String toString() {
        return "Counter{ value: " + num + "\tRange : (" + min + " - " + max + ") }";
    }
}

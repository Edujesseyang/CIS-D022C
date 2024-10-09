package RationalNumber;

/**
 * Usage: A Rational class implementation.
 * Purpose: For CIS D022C 50Z, Lab 1 assignment.
 * @author Jesse Yang
 */
class ZeroDenominatorException extends RuntimeException {
    public ZeroDenominatorException(String message) {
        super(message);
    }
}


public class Rational implements RationalInterface {
    private int denominator;
    private int numerator;

    // constructors:
    public Rational() {
    }

    /**
     * parameter constructor:
     * 1. it will check if denominator is less than zero, if so, switch sign for both numerator and denominator.
     * 2. it will call simplify method to write the rational to most simplified format.
     * @param numerator int
     * @param denominator int
     * @throws ZeroDenominatorException if {@param denominator} is zero
     */

    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ZeroDenominatorException("Denominator can't be zero.");
        }
        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }
        if (numerator == 0) {
            denominator = 1;
        }
        this.denominator = denominator;
        this.numerator = numerator;
        this.simplify();
    }

    /**
     * Get the denominator
     *
     * @return int: denominator
     */
    @Override
    public int getDenominator() {
        return this.denominator;
    }

    /**
     * Get the numerator
     *
     * @return int: numerator
     */
    @Override
    public int getNumerator() {
        return this.numerator;
    }

    /**
     * Set denominator
     *
     * @param denominator int
     * @throws ZeroDenominatorException if {@param denominator} is zero
     */
    @Override
    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new ZeroDenominatorException("Denominator can't be zero.");
        }
        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }
        this.denominator = denominator;
    }

    /**
     * Set numerator
     *
     * @param numerator int
     */
    @Override
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    /**
     * negation rational
     */
    @Override
    public Rational negate() {
        return new Rational(-this.numerator, denominator);
    }


    /**
     * convert rational to decimal
     *
     * @return a decimal number (double)
     */
    @Override
    public double toDecimal() {
        return this.numerator / (double) this.denominator;
    }

    /**
     * check if two rationals are equal
     *
     * @param another (Rational)
     * @return true if they are equal/false if they are NOT equal (boolean)
     */
    @Override
    public boolean isEqual(Rational another) {
        return this.denominator == another.getDenominator() && this.numerator == another.getNumerator();
    }

    /**
     * get the sum of two rationals
     *
     * @param another (Rational)
     * @return a new object that equal to the sum of two rationals (Rational)
     */
    @Override
    public Rational add(Rational another) {
        int newDenominator = this.denominator * another.getDenominator();
        int newNumerator = this.numerator * another.getDenominator() + another.getNumerator() * this.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    /**
     * get the difference of two rationals
     *
     * @param another (Rational)
     * @return a new object that equal to the difference of two rationals (Rational)
     */
    @Override
    public Rational subtract(Rational another) {
        int newDenominator = this.denominator * another.getDenominator();
        int newNumerator = this.numerator * another.getDenominator() - another.getNumerator() * this.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    /**
     * get the product of two rationals
     *
     * @param another (Rational)
     * @return a new object that equal to the product of two rationals (Rational)
     */
    @Override
    public Rational multiply(Rational another) {
        int newDenominator = this.denominator * another.getDenominator();
        int newNumerator = this.numerator * another.getNumerator();
        return new Rational(newNumerator, newDenominator);
    }

    /**
     * get the quotient of two rationals
     *
     * @param another (Rational)
     * @return a new object that equal to the quotient of two rationals (Rational)
     * @throws ZeroDenominatorException if {@param another} has a numerator is 0.
     */
    @Override
    public Rational divide(Rational another) {
        if (another.getNumerator() == 0) {
            throw new ZeroDenominatorException("Divider can't be zero.");
        }
        int newDenominator = this.denominator * another.getNumerator();
        int newNumerator = this.numerator * another.getDenominator();
        return new Rational(newNumerator, newDenominator);
    }

    /**
     * get the reciprocal of the rational number
     *
     * @return reciprocal of this rational number (Rational)
     * @throws ZeroDenominatorException if numerator equal to 0.
     */
    @Override
    public Rational invert() {
        if (numerator == 0) {
            throw new ZeroDenominatorException("Zero can't be denominator.");
        }
        return new Rational(this.denominator, this.numerator);
    }

    /**
     * print the rational number
     */
    @Override
    public void print() {
        System.out.print(numerator + "/" + denominator);
    }

    /**
     * convert the rational to string
     *
     * @return a string that represents the rational (String)
     */
    @Override
    public String toString() {
        return this.numerator + "/" + denominator;

    }

    /**
     * simplify the rational
     */
    @Override
    public void simplify() {
        if (denominator == numerator) {
            // return 1/1 if denominator equal to numerator
            this.denominator = 1;
            this.numerator = 1;
            return;
        }

        // find the greatest common divisor
        int smallerComponent = Math.min(Math.abs(denominator), Math.abs(numerator));
        int greatestCommonDivisor = 1;
        for (int i = smallerComponent; i >= 1; i--) {
            if (denominator % i == 0 && numerator % i == 0) {
                greatestCommonDivisor = i;
                break;
            }
        }
        // update denominator and numerator
        this.denominator = this.denominator / greatestCommonDivisor;
        this.numerator = this.numerator / greatestCommonDivisor;
    }
}

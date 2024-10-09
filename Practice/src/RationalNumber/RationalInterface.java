package RationalNumber;

/**
 * Usage: An interface for Rational class.
 * Purpose: For CIS D022C 50Z, Lab 1 assignment.
 * @author Jesse Yang
 */

public interface RationalInterface {
    /**
     * Get the denominator
     *
     * @return int: denominator
     */
    int getDenominator();

    /**
     * Get the numerator
     *
     * @return int: numerator
     */
    int getNumerator();

    /**
     * Set denominator
     *
     * @param denominator int
     */
    void setDenominator(int denominator);

    /**
     * Set numerator
     *
     * @param numerator int
     */
    void setNumerator(int numerator);

    /**
     * negation rational
     */
    Rational negate();

    /**
     * convert rational to decimal
     *
     * @return a decimal number (double)
     */
    double toDecimal();

    /**
     * check if two rationals are equal
     *
     * @param another (Rational)
     * @return true/false (boolean)
     */
    boolean isEqual(Rational another);

    /**
     * get the sum of two rationals
     *
     * @param another (Rational)
     * @return sum of two rationals (Rational)
     */
    Rational add(Rational another);

    /**
     * get the difference of two rationals
     *
     * @param another (Rational)
     * @return difference of two rationals (Rational)
     */
    Rational subtract(Rational another);

    /**
     * get the product of two rationals
     *
     * @param another (Rational)
     * @return product of two rationals (Rational)
     */
    Rational multiply(Rational another);

    /**
     * get the quotient of two rationals
     *
     * @param another (Rational)
     * @return quotient of two rationals (Rational)
     */
    Rational divide(Rational another);

    /**
     * get the reciprocal of the rational number
     *
     * @return reciprocal of this rational number (Rational)
     */
    Rational invert();

    /**
     * print the rational number
     */
    void print();

    /**
     * convert the rational to string
     *
     * @return a string that represents the rational.
     */
    String toString();

    /**
     * simplify the rational
     */
    void simplify();
}

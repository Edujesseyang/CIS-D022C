package RationalNumber;

/**
 * Usage: A test file for testing Rational class.
 * Purpose: For CIS D022C 50Z, Lab 1 assignment.
 * @author Jesse Yang
 */

public class test {
    static String pass = "\u001B[32mPASS\u001B[0m";
    static String fail = "\u001B[31mFAIL\u001B[0m";

    public static void main(String[] args) {
        boolean allPass = true;

        System.out.println("************** Testing constructors *****************");
        Rational r1 = new Rational();
        System.out.println("Default constructor: " + pass);

        Rational r2 = new Rational(2, 3);
        System.out.println("parameters constructor: " + pass);
        try {
            Rational r3 = new Rational(2, 0);
        } catch (ZeroDenominatorException e) {
            if (e.getMessage().equals("Denominator can't be zero.")) {
                System.out.println("zero denominator exception: " + pass);
            } else {
                System.out.println("zero denominator exception: " + fail);
                allPass = false;
            }
        }

        System.out.println("************** Testing polymorphism *****************");
        RationalInterface ri1 = new Rational();
        RationalInterface ri2 = new Rational(3, 4);
        ri1.setDenominator(4);
        ri1.setNumerator(3);
        if (ri1.isEqual((Rational) ri2)) {
            System.out.println("polymorphism constructing: " + pass);
        } else {
            System.out.println("polymorphism constructing: " + fail);
            allPass = false;
        }

        System.out.println("************** Testing getters *****************");
        int d4 = 5;
        int n4 = 4;
        Rational r4 = new Rational(n4, d4);
        if (r4.getNumerator() == n4 && r4.getDenominator() == d4) {
            System.out.println("Getter for denominator and numerator: " + pass);
        } else {
            System.out.println("Getter for denominator and numerator: " + fail);
            allPass = false;
        }
        System.out.println("************** Testing setters *****************");
        r1.setNumerator(n4);
        r1.setDenominator(d4);
        if (r1.getNumerator() == n4 && r1.getDenominator() == d4) {
            System.out.println("Setter for denominator and numerator: " + pass);
        } else {
            System.out.println("Setter for denominator and numerator: " + fail);
            allPass = false;
        }
        try {
            r1.setDenominator(0);
        } catch (ZeroDenominatorException e) {
            if (e.getMessage().equals("Denominator can't be zero.")) {
                System.out.println("throw exception for setting 0 denominator: " + pass);
            } else {
                System.out.println("throw exception for setting 0 denominator: " + fail);
                allPass = false;
            }
        }

        System.out.println("************** Testing negation *****************");
        Rational r5 = new Rational(2, 3);
        Rational r5_n = r5.negate();
        if (r5_n.getDenominator() == 3 && r5_n.getNumerator() == -2) {
            System.out.println("Negation method: " + pass);
        } else {
            System.out.println("Negation method: " + fail);
            allPass = false;
        }

        System.out.println("************** Testing toDecimal *****************");
        Rational r6 = new Rational(1, 200);
        Rational r7 = new Rational(1, 125);
        if (r6.toDecimal() == 0.005 && r7.toDecimal() == 0.008) {
            System.out.println("toDecimal method: " + pass);
        } else {
            System.out.println("toDecimal method: " + fail);
            allPass = false;
        }

        System.out.println("************** Testing isEqual *****************");
        Rational r6_e = new Rational(1, 200);
        Rational r7_e = new Rational(1, 125);
        if (r6.isEqual(r6_e) && r7.isEqual(r7_e) && r7_e.isEqual(r7) && r6_e.isEqual(r6)) {
            System.out.println("isEqual method: " + pass);
        } else {
            System.out.println("isEqual method: " + fail);
            allPass = false;
        }

        System.out.println("************** Testing simplify *****************");
        Rational r8 = new Rational(2, 10);
        Rational r9 = new Rational(1, 5);
        Rational r10 = new Rational(3, 15);
        if (r8.isEqual(r9) && r8.isEqual(r10) && r9.isEqual(r10)) {
            System.out.println("simplify method: " + pass);
        } else {
            System.out.println("simplify method: " + fail);
            allPass = false;
        }

        System.out.println("************** Testing sum *****************");
        Rational s1 = new Rational(1, 2);
        Rational s2 = new Rational(2, 3);
        Rational s3 = new Rational(3, 10);
        Rational sumResult = s1.add(s2);
        Rational sumResult2 = s2.add(s3);
        Rational sumResult3 = s1.add(s3);

        if (sumResult.getNumerator() == 7 && sumResult.getDenominator() == 6 && sumResult2.getNumerator() == 29 && sumResult2.getDenominator() == 30 && sumResult3.getNumerator() == 4 && sumResult3.getDenominator() == 5) {
            System.out.println("sum method: " + pass);
        } else {
            System.out.println("sum method: " + fail);
            allPass = false;
        }

        System.out.println("************** Testing subtract *****************");
        Rational sub1 = new Rational(5, 10);
        Rational sub2 = new Rational(2, 5);
        Rational subResult = sub1.subtract(sub2);
        Rational subResult2 = sub2.subtract(sub1);
        if (subResult.getNumerator() == 1 && subResult.getDenominator() == 10 && subResult2.getNumerator() == -1 && subResult2.getDenominator() == 10) {
            System.out.println("subtract method: " + pass);
        } else {
            System.out.println("subtract method: " + fail);
            allPass = false;
        }

        System.out.println("************** Testing multiply *****************");
        Rational mul1 = new Rational(15, -23);
        Rational mul2 = new Rational(36, 57);
        Rational mulResult = mul1.multiply(mul2);
        if (mulResult.isEqual(new Rational(-180, 437))) {
            System.out.println("multiply method: " + pass);
        } else {
            System.out.println("multiply method: " + fail);
            allPass = false;
        }

        System.out.println("************** Testing divide *****************");
        Rational div1 = new Rational(2, -3);
        Rational div2 = new Rational(5, -6);
        if (div1.divide(div2).isEqual(new Rational(4, 5))) {
            System.out.println("division method: " + pass);
        } else {
            System.out.println("division method: " + fail);
            allPass = false;
        }
        Rational div3 = new Rational(0, 2);
        try {
            Rational div4 = div1.divide(div3);
        } catch (ZeroDenominatorException e) {
            if (e.getMessage().equals("Divider can't be zero.")) {
                System.out.println("throw exception divide by zero: " + pass);
            } else {
                System.out.println("throw exception divide by zero: " + fail);
                allPass = false;
            }
        }

        System.out.println("************** Testing toReciprocal *****************");
        Rational rec1 = new Rational(-2, 3);
        Rational rec2 = new Rational(-3, 2);
        if (rec2.isEqual(rec1.invert())) {
            System.out.println("toReciprocal method: " + pass);
        } else {
            System.out.println("toReciprocal method: " + fail);
            allPass = false;
        }

        System.out.println("************** Testing toString *****************");
        if (rec2.toString().equals("-3/2") && rec1.toString().equals("-2/3") && r1.toString().equals("4/5") && r2.toString().equals("2/3")) {
            System.out.println("toString method: " + pass);
        } else {
            System.out.println("toString method: " + fail);
            allPass = false;
        }

        System.out.println("************** Testing print *****************");
        Rational[] rationals = {rec2, rec1, r1, r2, sumResult3, r4, r5, div3, s1, sub1, sub2, subResult2, sumResult2, div3, div1};
        System.out.println("Printing:");
        for (Rational r : rationals) {
            r.print();
            System.out.print("\t");
        }
        System.out.println();
        System.out.println("print method: " + pass);

        System.out.println("\n\n************** Testing completed *****************");
        if (allPass) {
            System.out.println("Test result: all component " + pass);
        } else {
            System.out.println("Test result: " + fail);
        }
    }
}

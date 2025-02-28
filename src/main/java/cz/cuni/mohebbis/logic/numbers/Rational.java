package cz.cuni.mohebbis.logic.numbers;

import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
/**
 * Implementation of the rational numbers.
 * uses Integer class for the numerator and denominator.
 */
public class Rational {
    Integer numerator;
    Integer denominator;
    /**
     * Constructor to create a Rational number with a specified numerator and denominator.
     * It also ensures the denominator is not zero and simplifies the rational number.
     *
     * @param numerator   The numerator of the rational number.
     * @param denominator The denominator of the rational number.
     * @throws DivisionByZeroException If the denominator is zero.
     */
    public Rational(Integer numerator, Integer denominator) {
        if (denominator.absoluteValue.IsZero()){
            throw new DivisionByZeroException("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.Simplify();
    }
    /**
     * Adds two rational numbers and returns the result as a new Rational number.
     *
     * @param other The rational number to add to the current rational number.
     * @return The sum as a new Rational number.
     */
    public Rational Add(Rational other) {
        Integer p = this.numerator;
        Integer q = this.denominator;
        Integer r = other.numerator;
        Integer s = other.denominator;

        Integer newNumerator = (p.Multiply(s)).Add(q.Multiply(r));
        Integer newDenominator = (q.Multiply(s));

        return new Rational(newNumerator, newDenominator);
    }
    /**
     * Subtracts another rational number from the current rational number.
     * Returns the result as a new Rational number.
     *
     * @param other The rational number to subtract.
     * @return The difference as a new Rational number.
     */
    public Rational Subtract(Rational other) {
        Integer p = this.numerator;
        Integer q = this.denominator;
        Integer r = other.numerator;
        Integer s = other.denominator;

        Integer newNumerator = (p.Multiply(s)).Subtract(q.Multiply(r));
        Integer newDenominator = (q.Multiply(s));

        return new Rational(newNumerator, newDenominator);
    }
    /**
     * Checks if the rational number is zero.
     *
     * @return {@code true} if the rational number is zero, {@code false} otherwise.
     */
    public boolean IsZero() {
        return this.numerator.absoluteValue.IsZero();
    }
    /**
     * Negates the rational number and returns the result as a new Rational number.
     *
     * @return The negated Rational number.
     */
    public Rational Negate() {
        Integer p = this.numerator.Negate();
        Integer q = this.denominator;

        return new Rational(p, q);
    }
    /**
     * Multiplies the current rational number with another rational number and returns the result.
     *
     * @param Q The rational number to multiply with.
     * @return The product as a new Rational number.
     */
    public Rational Multiply(Rational Q) {
        Integer p = this.numerator;
        Integer q = this.denominator;
        Integer r = Q.numerator;
        Integer s = Q.denominator;

        return new Rational(
                p.Multiply(r),
                q.Multiply(s)
        );
    }
    /**
     * Divides the current rational number by another rational number and returns the result.
     * This operation is equivalent to multiplying by the reciprocal of the other rational number.
     *
     * @param Q The rational number to divide by.
     * @return The quotient as a new Rational number.
     * @throws DivisionByZeroException If the numerator of the other rational number is zero.
     */
    public Rational Divide(Rational Q) {
        Integer p = this.numerator;
        Integer q = this.denominator;
        Integer r = Q.numerator;
        Integer s = Q.denominator;

        if (Q.numerator.absoluteValue.IsZero()) {

            throw new DivisionByZeroException();
        } else {
            return new Rational(
                    p.Multiply(s),
                    q.Multiply(r)
            );
        }
    }
    /**
     * Simplifies the rational number by dividing both the numerator and denominator
     * by their greatest common divisor (GCD).
     */
    private void Simplify() {
        Natural gcd = Natural.GCD(numerator.absoluteValue, denominator.absoluteValue);
        boolean negative = false;
        if (numerator.isNegative && denominator.isNegative) {
            negative = false;
        }else{
            negative = numerator.isNegative || denominator.isNegative;
        }


        numerator = new Integer(negative, numerator.absoluteValue.Divide(gcd)[0]);
        denominator = new Integer(false, denominator.absoluteValue.Divide(gcd)[0]);
    }

    /**
     * Converts the rational number to a string representation.
     *
     * @return A string representing the rational number in the format "numerator/denominator".
     * If the denominator is 1, only the numerator is returned.
     */
    public String ToString() {
        String numerater = this.numerator.ToString();
        String denomerater = this.denominator.ToString();

        if (this.denominator.absoluteValue.IsOne()) {
            return numerater;
        }
        else {
            return numerater + "/" + denomerater;
        }
    }
}

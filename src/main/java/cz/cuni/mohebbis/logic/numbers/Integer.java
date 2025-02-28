package cz.cuni.mohebbis.logic.numbers;
/**
 * Implementation of the integer numbers.
 * uses a natural number to represent the absolute value, and
 * a boolean to represent negativity.(if negative {@code true} otherwise {@code false}.
 */
public class Integer {
    Natural absoluteValue;
    public boolean isNegative;
    /**
     * Default constructor that initializes the integer to 0 (positive).
     */
    public Integer() {
        this.absoluteValue = new Natural();
        isNegative = false;
    }
    /**
     * Constructor to create an Integer with a specific negative flag and absolute value (Natural number).
     *
     * @param negative Boolean flag indicating if the integer is negative.
     * @param N The absolute value of the integer.
     */
    public Integer(boolean negative, Natural N) {
        this.absoluteValue = N;
        this.isNegative = negative;
    }
    /**
     * Constructor to create an Integer from an java.lang.int value. Determines the absolute value and sign.
     *
     * @param N The java.lang.int value to convert.
     */
    public Integer(int N) {
        this.absoluteValue = new Natural(Math.abs(N));
        this.isNegative = N < 0;
    }

    /**
     * Add the current Integer to another Integer and returns the result.
     *
     * @param N The Integer to add.
     * @return The sum as a new Integer.
     */
    public Integer Add(Integer N) {
        Integer sum = new Integer();

        if (!this.isNegative && !N.isNegative) {
            sum.absoluteValue = this.absoluteValue.Add(N.absoluteValue);
            sum.isNegative = false;
        }
        else if (!this.isNegative && N.isNegative) {
            boolean[] neg = new boolean[1];
            sum.absoluteValue = this.absoluteValue.Subtract(N.absoluteValue, neg);
            boolean negative = neg[0];
            if (negative) {
                sum.absoluteValue = N.absoluteValue.Subtract(this.absoluteValue, new boolean[1]);
                sum.isNegative = true;
            }
            else {
                sum.isNegative = false;
            }
        }
        else if (this.isNegative && !N.isNegative) {

            boolean[] neg = new boolean[1];
            sum.absoluteValue = this.absoluteValue.Subtract(N.absoluteValue, neg);
            boolean negative = neg[0];
            if (negative) {
                sum.absoluteValue = N.absoluteValue.Subtract(this.absoluteValue, new boolean[1]);
                sum.isNegative = false;
            }
            else {
                sum.isNegative = true;
            }
        }
        else {
            sum.absoluteValue = this.absoluteValue.Add(N.absoluteValue);
            sum.isNegative = true;
        }

        return sum;
    }
    /**
     * Subtract the current Integer from another Integer and returns the result.
     *
     * @param N The Integer to Subtract.
     * @return The result as a new Integer.
     */
    public Integer Subtract(Integer N) {
        Integer difference = new Integer();
        Integer negation = new Integer();

        negation.absoluteValue = N.absoluteValue;
        negation.isNegative = !N.isNegative;
        difference = this.Add(negation);

        return difference;
    }
    /**
     * Multiplies the current Integer by another Integer and returns the result.
     *
     * @param N The Integer to multiply with.
     * @return The product as a new Integer.
     */
    public Integer Multiply(Integer N) {
        Integer product = new Integer();

        product.absoluteValue = this.absoluteValue.Multiply(N.absoluteValue);
        product.isNegative = this.isNegative ^ N.isNegative;

        return product;
    }
    /**
     * Divides the current Integer by a Natural number and returns the result.
     *
     * @param N The Natural number to divide by.
     * @return The quotient as a new Integer.
     */
    public Integer Divide(Integer N) {
        Integer quotient = new Integer();

        quotient.absoluteValue = this.absoluteValue.Divide(N.absoluteValue)[0];
        if (this.isNegative && N.isNegative) {
            quotient.isNegative = false;
        }else {
            quotient.isNegative = this.isNegative || N.isNegative;
        }
        return quotient;
    }
    /**
     * Negates the current Integer and returns the result.
     *
     * @return The negated Integer.
     */
    public Integer Negate() {
        Integer n = new Integer();

        n.absoluteValue = this.absoluteValue;
        n.isNegative = !this.isNegative;

        return n;
    }
    /**
     * Returns the absolute value of the current Integer as a Natural number.
     *
     * @return The absolute value of the current Integer.
     */
    public Natural AbsoluteValue() {
        return this.absoluteValue;
    }
    /**
     * Converts the current Integer to a string representation.
     *
     * @return A string representing the current Integer.
     */
    public String ToString() {
        String s = this.absoluteValue.ToString();
        if (this.absoluteValue.IsZero()) {
            return "0";
        }

        if (this.isNegative) {
            s = '-' + s;
        }

        return s;
    }
}

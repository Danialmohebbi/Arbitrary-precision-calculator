package cz.cuni.mohebbis.logic.numbers;

public class Integer {
    Natural absoluteValue;
    public boolean isNegative;

    public Integer() {
        this.absoluteValue = new Natural();
        isNegative = false;
    }

    public Integer(boolean negative, Natural N) {
        this.absoluteValue = N;
        this.isNegative = negative;
    }


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
            System.out.println(sum.isNegative);
            sum.isNegative = true;
        }

        return sum;
    }

    public Integer Subtract(Integer N) {
        Integer difference = new Integer();
        Integer negation = new Integer();

        negation.absoluteValue = N.absoluteValue;
        negation.isNegative = !N.isNegative;
        difference = this.Add(negation);

        return difference;
    }

    public Integer Multiply(Integer N) {
        Integer product = new Integer();

        product.absoluteValue = this.absoluteValue.Multiply(N.absoluteValue);
        product.isNegative = this.isNegative ^ N.isNegative;

        return product;
    }

    public Integer Divide(Natural N) {
        Integer quotient = new Integer();

        quotient.absoluteValue = this.absoluteValue.Divide(N)[0];
        quotient.isNegative = this.isNegative;

        return quotient;
    }
    public Integer Divide(Integer N) {
        Integer quotient = new Integer();

        quotient.absoluteValue = this.absoluteValue.Divide(N.absoluteValue)[0];
        quotient.isNegative = this.isNegative || N.isNegative;

        return quotient;
    }

    public Integer Negate() {
        Integer n = new Integer();

        n.absoluteValue = this.absoluteValue;
        n.isNegative = !this.isNegative;

        return n;
    }

    public Natural AbsoluteValue() {
        return this.absoluteValue;
    }

    public String ToString() {
        String s = this.absoluteValue.ToString();
        System.out.println(s);
        if (this.absoluteValue.IsZero()) {
            return "0";
        }

        if (this.isNegative) {
            s = '-' + s;
        }

        return s;
    }
}

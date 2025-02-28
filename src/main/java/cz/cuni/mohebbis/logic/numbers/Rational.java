package cz.cuni.mohebbis.logic.numbers;

import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;

public class Rational {
    Integer numerator;
    Integer denominator;

    public Rational(Integer numerator, Integer denominator) {
        if (denominator.absoluteValue.IsZero()){
            throw new DivisionByZeroException("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.Simplify();
    }

    public Rational Add(Rational other) {
        Integer p = this.numerator;
        Integer q = this.denominator;
        Integer r = other.numerator;
        Integer s = other.denominator;

        Integer newNumerator = (p.Multiply(s)).Add(q.Multiply(r));
        System.out.println("newNumerator = " + newNumerator.ToString());
        Integer newDenominator = (q.Multiply(s));
        System.out.println("newDenominator = " + newDenominator.ToString());

        return new Rational(newNumerator, newDenominator);
    }

    public Rational Subtract(Rational other) {
        Integer p = this.numerator;
        Integer q = this.denominator;
        Integer r = other.numerator;
        Integer s = other.denominator;

        Integer newNumerator = (p.Multiply(s)).Subtract(q.Multiply(r));
        Integer newDenominator = (q.Multiply(s));

        return new Rational(newNumerator, newDenominator);
    }

    public boolean IsZero() {
        return this.numerator.absoluteValue.IsZero();
    }

    public Rational Negate() {
        Integer p = this.numerator.Negate();
        Integer q = this.denominator;

        return new Rational(p, q);
    }

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
    public boolean IsOne(){
            return (this.denominator.absoluteValue.blocks.length == 1) && (this.denominator.absoluteValue.blocks[0] == 1);

    }

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

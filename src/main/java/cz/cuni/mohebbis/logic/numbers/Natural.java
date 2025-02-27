package cz.cuni.mohebbis.logic.numbers;

import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.exceptions.InvalidDigitException;

import java.math.BigInteger;

public class Natural {
    public int[] blocks;

    public Natural(int number) {
        assert number > 0;
        this.blocks = new int[1];
        this.blocks[0] = number;
    }
    public Natural(){
        this.blocks = new int[1];
    }


    public int safeAdd(int a, int b) {
        long sum = (long) a + b;
        return (int) (sum & 0x7FFFFFFF);
    }

    public boolean willAdditionOverflow(int a, int b) {
        try {
            Math.addExact(a, b);
            return false;
        } catch (ArithmeticException e) {
            return true;
        }
    }


    public Natural Add(Natural N) {
        Natural max, min;
        if (this.blocks.length >= N.blocks.length) {
            max = this;
            min = N;
        }
        else {
            max = N;
            min = this;
        }


        int[] sum = new int[max.blocks.length];
        boolean overflow = false;

        for (int i = 0; i < max.blocks.length; i++) {
            boolean incoming = overflow;
            int blockSum = 0;
            blockSum = safeAdd(max.blocks[i], (i < min.blocks.length) ? min.blocks[i] : 0);
            if (willAdditionOverflow(max.blocks[i], (i < min.blocks.length) ? min.blocks[i] : 0)) {
                overflow = true;
            }
            if (incoming) {

                overflow = willAdditionOverflow(blockSum, 1);
                blockSum = safeAdd(1, blockSum);
            }
            sum[i] = blockSum;
        }

        Natural result = new Natural();

        if (overflow) {
            result.blocks = new int[max.blocks.length + 1];
            for (int i = 0; i < max.blocks.length; i++) {
                result.blocks[i] = sum[i];
            }
            result.blocks[max.blocks.length] = 1;

        }
        else{
            result.blocks = sum;
        }

        return result;
    }

    public Natural MultiplyByTwo() {
        return this.Add(this);
    }

    public Natural Multiply(Natural N) {
        Natural result = new Natural();
        for (int block = N.blocks.length - 1; block >= 0; --block) {
            for (int offset = 31; offset >= 0; --offset) {

                result = result.MultiplyByTwo();
                if (((N.blocks[block] >> offset) & 1) == 1) {
                    result = result.Add(this);
                }
            }
        }
        return result;
    }

    public int safeSubtract(int a, int b) {
        long result = (long) a - b;
        return (int) (result & 0x7FFFFFFF);
    }

    public boolean willSubtractionOverflow(int a, int b) {
        try {
            int result = Math.subtractExact(a, b);
            return result < 0;
        } catch (ArithmeticException e) {
            return true;
        }
    }


    public Natural Subtract(Natural N,boolean[] negative) {
        if (N.blocks.length > this.blocks.length) {
            negative[0] = true;
        }
        Natural max, min;
        if (this.blocks.length >= N.blocks.length) {
            max = this;
            min = N;
        }
        else {
            max = N;
            min = this;
        }


        int[] subResult = new int[max.blocks.length];
        boolean borrow = false;

        for (int i = 0; i < max.blocks.length; i++) {
            boolean incoming = borrow;
            int blockSum = 0;
            blockSum = safeSubtract(max.blocks[i], (i < min.blocks.length) ? min.blocks[i] : 0);
            if (willSubtractionOverflow(max.blocks[i], (i < min.blocks.length) ? min.blocks[i] : 0)) {
                borrow = true;
            }
            if (incoming) {

                borrow = willSubtractionOverflow(blockSum, 1);
                blockSum = safeSubtract(blockSum, 1);
            }
            subResult[i] = blockSum;
        }

        Natural sub = new Natural();
        if (borrow) {

            negative[0] = true;
        }

        sub.blocks = subResult;
        sub.CorrectSize();

        return sub;
    }



    public boolean GreaterThan(Natural other) {
        if (this.blocks.length > other.blocks.length) {
            return true;
        } else if (this.blocks.length < other.blocks.length) {
            return false;
        }

        for (int i = this.blocks.length - 1; i >= 0; i--) {
            if (this.blocks[i] > other.blocks[i]) {
                return true;
            } else if (this.blocks[i] < other.blocks[i]) {
                return false;
            }
        }

        return false;
    }

    private void CorrectSize() {
        int k = this.blocks.length - 1;
        while ((k >= 0) && (this.blocks[k] == 0)) {
            k--;
        }
        int size = (k == -1) ? 1 : (k + 1);
        int[] new_blocks = new int[size];

        for (int i = 0; i < size; ++i) {
            new_blocks[i] = this.blocks[i];
        }

        this.blocks = new_blocks;
    }
    public boolean IsZero() {
            return (this.blocks.length == 1) && (blocks[0] == 0);

    }

    /* Property that determines whether a given natural is equal to
    one. */
    public boolean IsOne (){
            return (this.blocks.length == 1) && (blocks[0] == 1);

    }

    public Natural[] Divide(Natural N) {
        if (N.IsZero()) {
            throw new DivisionByZeroException();
        }
        else {
            Natural quotient = new Natural();
            quotient.blocks = new int[this.blocks.length];
            Natural remainder = new Natural();

            for (int block = this.blocks.length - 1; block >= 0; --block) {
                for (int offset = 30; offset >= 0; --offset) {
                    int bit = (this.blocks[block] >> offset) & 1;
                    remainder = remainder.MultiplyByTwo();
                    remainder.blocks[0] |= bit;

                    if (!(N.GreaterThan(remainder))) {
                        remainder = remainder.Subtract(N,new boolean[1]);
                        quotient.blocks[block] |= ((int)1 << offset);
                    }
                }
            }

            quotient.CorrectSize();

            return new Natural[] {quotient,remainder};
        }
    }

    public String ToString() {
        Natural TEN = new Natural(10);
        String s = "";

        Natural n = this;
        if (n.IsZero()) {
            return "0";
        }

        while (!n.IsZero()) {
            Natural[] result = n.Divide(TEN);
            n = result[0];
            Natural r = result[1];
            char digit = (char)((int)'0' + (int)(r.blocks[0]));
            s = digit + s;
        }


        return s;
    }

    public static Natural GCD(Natural N, Natural M) {
        Natural P = N;
        Natural Q = M;

        while (!Q.IsZero()) {
            Natural temp = P.Divide(Q)[1];
            P = Q;
            Q = temp;
        }

        return P;
    }


}
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
            for (int offset = 15; offset >= 0; --offset) {

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


    public Natural Subtract(Natural N) {
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
                blockSum = safeSubtract(1, blockSum);
            }
            subResult[i] = blockSum;
        }

        Natural sub = new Natural();

        if (borrow) {
        }
        else{
            sub.blocks = subResult;
        }

        return sub;
    }



}
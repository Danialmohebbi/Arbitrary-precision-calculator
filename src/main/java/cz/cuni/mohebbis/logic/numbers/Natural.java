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

    public boolean isOverflow(int a,int b) {
        int sum = a+b;
        if (a > 0 && b > 0 && sum < 0) {
            return true;
        }
        return sum < a;
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




}
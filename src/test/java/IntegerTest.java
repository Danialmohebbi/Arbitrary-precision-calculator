import cz.cuni.mohebbis.logic.numbers.Natural;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import cz.cuni.mohebbis.logic.numbers.Integer;

public class IntegerTest {
    @Test
    public void SumTwoSmallNumbers_Test() {
        Integer num1 = new Integer(false,new Natural(12));
        Integer num2 = new Integer(true,new Natural(13));

        Integer num3 = num1.Add(num1);
        Integer num4 = num2.Add(num2);
        Integer num5 = num1.Add(num2);

        assertTrue(num3.ToString().equals("24"));
        assertTrue(num4.ToString().equals("-26"));
        assertTrue(num5.ToString().equals("-1"));

    }

    @Test
    public void SumTwoLargeNumbers_Test() {
        Integer num1 = new Integer(false,new Natural(java.lang.Integer.MAX_VALUE));
        Integer num2 = new Integer(true,new Natural(java.lang.Integer.MAX_VALUE));

        Integer num3 = num1.Add(num1);
        Integer num4 = num2.Add(num2);
        Integer num5 = num1.Add(num2);

        assertTrue(num3.ToString().equals("4294967294"));
        assertTrue(num4.ToString().equals("-4294967294"));
        assertTrue(num5.ToString().equals("0"));

    }
    @Test
    public void SumOneBigNumberOneSmallNumber_Test() {
        Integer num1 = new Integer(false,new Natural(java.lang.Integer.MAX_VALUE).Multiply(new Natural(java.lang.Integer.MAX_VALUE)));
        Integer num2 = new Integer(true,new Natural(2));

        Integer num3 = num1.Add(num2);

        assertTrue(num3.ToString().equals("4611686014132420607"));
    }


    @Test
    public void NegateSmallNumber_Test() {
        Integer num1 = new Integer(false,new Natural(12));
        Integer num2 = new Integer(true,new Natural(13));

        Integer num3 = num1.Add(num2).Negate();

        assertTrue(num3.ToString().equals("1"));

    }

    @Test
    public void NegateLargeNumber_Test() {
        Integer num1 = new Integer(false,new Natural(java.lang.Integer.MAX_VALUE));
        Integer num3 = num1.Add(num1).Negate();

        assertTrue(num3.ToString().equals("-4294967294"));

    }

    @Test
    public void MultiplyTwoSmallNumbers_Test() {
        Integer num1 = new Integer(false,new Natural(12));
        Integer num2 = new Integer(true,new Natural(13));

        Integer num3 = num1.Multiply(num1);
        Integer num4 = num2.Multiply(num2);
        Integer num5 = num1.Multiply(num2);

        assertTrue(num3.ToString().equals("144"));
        assertTrue(num4.ToString().equals("169"));
        assertTrue(num5.ToString().equals("-156"));

    }

    @Test
    public void MultiplyTwoLargeNumbers_Test() {
        Integer num1 = new Integer(false,new Natural(java.lang.Integer.MAX_VALUE));
        Integer num2 = new Integer(true,new Natural(java.lang.Integer.MAX_VALUE));

        Integer num3 = num1.Multiply(num1);
        Integer num4 = num2.Multiply(num2);
        Integer num5 = num1.Multiply(num2);

        assertTrue(num3.ToString().equals("4611686014132420609"));
        assertTrue(num4.ToString().equals("4611686014132420609"));
        assertTrue(num5.ToString().equals("-4611686014132420609"));

    }
    @Test
    public void MultiplyOneBigNumberOneSmallNumber_Test() {
        Integer num1 = new Integer(false,new Natural(java.lang.Integer.MAX_VALUE).Multiply(new Natural(java.lang.Integer.MAX_VALUE)));
        Integer num2 = new Integer(true,new Natural(2));

        Integer num3 = num1.Multiply(num2);

        assertTrue(num3.ToString().equals("-9223372028264841218"));
    }




    @Test
    public void DivisionTwoSmallNumbers_Test() {
        Integer num1 = new Integer(false,new Natural(12));
        Integer num2 = new Integer(true,new Natural(2));

        Integer num3 = num1.Divide(num1);
        Integer num4 = num2.Divide(num2);
        Integer num5 = num1.Divide(num2);

        assertTrue(num3.ToString().equals("1"));
        assertTrue(num4.ToString().equals("1"));
        assertTrue(num5.ToString().equals("-6"));

    }

    @Test
    public void DivisionTwoLargeNumbers_Test() {
        Integer num1 = new Integer(false,new Natural(java.lang.Integer.MAX_VALUE));
        Integer num2 = new Integer(true,new Natural(java.lang.Integer.MAX_VALUE));

        Integer num3 = num1.Divide(num1);
        Integer num4 = num2.Divide(num2);
        Integer num5 = num1.Divide(num2);

        assertTrue(num3.ToString().equals("1"));
        assertTrue(num4.ToString().equals("1"));
        assertTrue(num5.ToString().equals("-1"));

    }
    @Test
    public void DivisionOneBigNumberOneSmallNumber_Test() {
        Integer num1 = new Integer(false,new Natural(java.lang.Integer.MAX_VALUE).Multiply(new Natural(java.lang.Integer.MAX_VALUE)));
        Integer num2 = new Integer(true,new Natural(2));

        Integer num3 = num1.Divide(num2);

        assertTrue(num3.ToString().equals("-2305843007066210304"));
    }




    @Test
    public void SubtractionTwoSmallNumbers_Test() {
        Integer num1 = new Integer(false,new Natural(12));
        Integer num2 = new Integer(true,new Natural(2));

        Integer num3 = num1.Subtract(num2);
        Integer num4 = num2.Subtract(num1);
        Integer num5 = num2.Subtract(num2);

        assertTrue(num3.ToString().equals("14"));
        assertTrue(num4.ToString().equals("-14"));
        assertTrue(num5.ToString().equals("0"));

    }

    @Test
    public void SubtractionTwoLargeNumbers_Test() {
        Integer num1 = new Integer(false,new Natural(java.lang.Integer.MAX_VALUE).Add(new Natural(1)));
        Integer num2 = new Integer(true,new Natural(java.lang.Integer.MAX_VALUE));

        Integer num3 = num1.Subtract(num2);
        Integer num4 = num2.Subtract(num1);
        Integer num5 = num2.Subtract(num2);

        assertTrue(num3.ToString().equals("4294967295"));
        assertTrue(num4.ToString().equals("-4294967295"));
        assertTrue(num5.ToString().equals("0"));

    }
    @Test
    public void SubtractOneBigNumberOneSmallNumber_Test() {
        Integer num1 = new Integer(false,new Natural(java.lang.Integer.MAX_VALUE).Multiply(new Natural(java.lang.Integer.MAX_VALUE)));
        Integer num2 = new Integer(true,new Natural(2));

        Integer num3 = num1.Subtract(num2);
        Integer num4 = num2.Subtract(num1);

        assertTrue(num3.ToString().equals("4611686014132420611"));
        assertTrue(num4.ToString().equals("-4611686014132420611"));
    }

}

import cz.cuni.mohebbis.logic.numbers.Natural;
import cz.cuni.mohebbis.logic.numbers.Rational;
import cz.cuni.mohebbis.logic.numbers.Integer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class RationalTest {
    @Test
    public void SumTwoSmallNumbers_Test() {
        Rational num1 = new Rational(new Integer(1), new Integer(13));
        Rational num2 = new Rational(new Integer(2), new Integer(13));

        Rational num3 = num1.Add(num2);

        assertTrue(num3.ToString().equals("3/13"));

    }

    @Test
    public void SumTwoLargeNumbers_Test() {
        Rational num1 = new Rational(new Integer(java.lang.Integer.MAX_VALUE), new Integer(13));
        Rational num2 = new Rational(new Integer(java.lang.Integer.MAX_VALUE),new Integer(13));

        Rational num3 = num1.Add(num2);

        assertTrue(num3.ToString().equals("4294967294/13"));

    }
    @Test
    public void SumOneBigNumberOneSmallNumber_Test() {
        Rational num1 = new Rational(new Integer(java.lang.Integer.MAX_VALUE),new Integer(1));
        Rational num2 = new Rational(new Integer(1),new Integer(1));

        Rational num3 = num1.Add(num2);

        assertTrue(num3.ToString().equals("2147483648"));
    }

    @Test
    public void MultiplyTwoSmallNumbers_Test() {
        Rational num1 = new Rational(new Integer(7), new Integer(13));
        Rational num2 = new Rational(new Integer(9), new Integer(13));

        Rational num3 = num1.Multiply(num2);

        assertTrue(num3.ToString().equals("63/169"));

    }

    @Test
    public void MultiplyTwoLargeNumbers_Test() {
        Rational num1 = new Rational(new Integer(java.lang.Integer.MAX_VALUE), new Integer(13));
        Rational num2 = new Rational(new Integer(java.lang.Integer.MAX_VALUE),new Integer(13));

        Rational num3 = num1.Multiply(num2);

        assertTrue(num3.ToString().equals("4611686014132420609/169"));

    }
    @Test
    public void MultiplyOneBigNumberOneSmallNumber_Test() {
        Rational num1 = new Rational(new Integer(java.lang.Integer.MAX_VALUE),new Integer(23));
        Rational num2 = new Rational(new Integer(2),new Integer(3));

        Rational num3 = num1.Multiply(num2);

        assertTrue(num3.ToString().equals("4294967294/69"));
    }

    @Test
    public void SubtractTwoSmallNumbers_Test() {
        Rational num1 = new Rational(new Integer(3), new Integer(13));
        Rational num2 = new Rational(new Integer(1), new Integer(13));

        Rational num3 = num2.Subtract(num1);

        assertTrue(num3.ToString().equals("-2/13"));

    }

    @Test
    public void SubtractTwoLargeNumbers_Test() {
        Rational num1 = new Rational(new Integer(java.lang.Integer.MAX_VALUE), new Integer(13));
        Rational num2 = new Rational(new Integer(java.lang.Integer.MAX_VALUE),new Integer(13));

        Rational num3 = num1.Subtract(num2);

        assertTrue(num3.ToString().equals("0"));

    }
    @Test
    public void SubtractOneBigNumberOneSmallNumber_Test() {
        Rational num1 = new Rational(new Integer(java.lang.Integer.MAX_VALUE),new Integer(23));
        Rational num2 = new Rational(new Integer(2),new Integer(3));

        Rational num3 = num2.Subtract(num1);

        assertTrue(num3.ToString().equals("-6442450895/69"));
    }




    @Test
    public void DivisionTwoSmallNumbers_Test() {
        Rational num1 = new Rational(new Integer(2), new Integer(13));
        Rational num2 = new Rational(new Integer(3), new Integer(12));

        Rational num3 = num1.Divide(num2);

        assertTrue(num3.ToString().equals("8/13"));

    }

    @Test
    public void DivisiontTwoLargeNumbers_Test() {
        Rational num1 = new Rational(new Integer(java.lang.Integer.MAX_VALUE), new Integer(13));
        Rational num2 = new Rational(new Integer(java.lang.Integer.MAX_VALUE),new Integer(13));

        Rational num3 = num1.Divide(num2);

        assertTrue(num3.ToString().equals("1"));

    }
    @Test
    public void DisionOneBigNumberOneSmallNumber_Test() {
        Rational num1 = new Rational(new Integer(java.lang.Integer.MAX_VALUE),new Integer(23));
        Rational num2 = new Rational(new Integer(2),new Integer(3));

        Rational num3 = num1.Divide(num2);

        assertTrue(num3.ToString().equals("6442450941/46"));
    }
}



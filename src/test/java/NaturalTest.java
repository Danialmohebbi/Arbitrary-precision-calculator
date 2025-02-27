import cz.cuni.mohebbis.logic.numbers.Natural;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NaturalTest {

    @Test
    public void SumTwoSmallNumbers_Test() {
        Natural num1 = new Natural(12);
        Natural num2 = new Natural(13);

        Natural num3 = num1.Add(num2);

        assertTrue(num3.ToString().equals("25"));

    }

    @Test
    public void SumTwoLargeNumbers_Test() {
        Natural num1 = new Natural(Integer.MAX_VALUE);
        Natural num2 = new Natural(Integer.MAX_VALUE);

        Natural num3 = num1.Add(num2);

        assertTrue(num3.ToString().equals("4294967294"));

    }
    @Test
    public void SumOneBigNumberOneSmallNumber_Test() {
        Natural num1 = new Natural(Integer.MAX_VALUE);
        Natural num2 = new Natural(1);

        Natural num3 = num1.Add(num2);

        assertTrue(num3.ToString().equals("2147483648"));
    }
    @Test
    public void MultiplyTwoSmallNumbers_Test() {
        Natural num1 = new Natural(10);
        Natural num2 = new Natural(23);

        Natural num3 = num1.Multiply(num2);

        assertTrue(num3.ToString().equals("230"));
    }

    @Test
    public void MultiplyTwoBigNumbers_Test() {
        Natural num1 = new Natural(Integer.MAX_VALUE);
        Natural num2 = new Natural(Integer.MAX_VALUE);

        Natural num3 = num1.Multiply(num2);

        assertTrue(num3.ToString().equals("4611686014132420609"));
    }

    @Test
    public void MultiplySmallNumberBigNumberNumbers_Test() {
        Natural num1 = new Natural(Integer.MAX_VALUE);
        Natural num2 = new Natural(3);

        Natural num3 = num1.Multiply(num2);

        assertTrue(num3.ToString().equals("6442450941"));
    }


    @Test
    public void DivisionTwoSmallNumbers_Test() {
        Natural num1 = new Natural(10);
        Natural num2 = new Natural(2);

        Natural num3 = num1.Divide(num2)[0];

        assertTrue(num3.ToString().equals("5"));
    }

    @Test
    public void DivisionSmallNumberBigNumberNumbers_Test() {
        Natural num1 = new Natural(Integer.MAX_VALUE);
        Natural num2 = new Natural(Integer.MAX_VALUE);

        Natural num3 = num1.Multiply(num2);

        Natural num4 = num3.Divide(new Natural(2))[0];

        assertTrue(num4.ToString().equals("2305843007066210304"));
    }

    @Test
    public void DivisionTwoBigNumberNumbers_Test() {
        Natural num1 = new Natural(Integer.MAX_VALUE);
        Natural num2 = new Natural(3);

        Natural num3 = num1.Multiply(num1).Divide(num2)[0];

        assertTrue(num3.ToString().equals("1537228671377473536"));
    }



    @Test
    public void SubtractionTwoSmallNumbers_Test() {
        Natural num1 = new Natural(10);
        Natural num2 = new Natural(2);

        Natural num3 = num1.Subtract(num2,new boolean[1]);

        assertTrue(num3.ToString().equals("8"));
    }

    @Test
    public void SubtractionSmallNumberBigNumberNumbers_Test() {
        Natural num1 = new Natural(Integer.MAX_VALUE);
        Natural num2 = new Natural(Integer.MAX_VALUE);

        Natural num3 = num1.Subtract(num2,new boolean[1]);


        assertTrue(num3.ToString().equals("0"));
    }

    @Test
    public void SubtractionTwoBigNumberNumbers_Test() {
        Natural num1 = new Natural(Integer.MAX_VALUE);
        Natural num2 = new Natural(3);

        Natural num3 = num2.Subtract(num1,new boolean[1]);

        assertTrue(num3.ToString().equals("4"));
    }
}

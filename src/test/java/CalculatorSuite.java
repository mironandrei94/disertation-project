import calculator.Constants;
import calculator.MainMethods;
import org.testng.annotations.Test;

import java.util.BitSet;

public class CalculatorSuite extends MainMethods {

    @Test
    public void testMultiplyOperation(){
        clickNumber(9);
        operation(Constants.MULTIPLY);
        clickNumber(8);
        calculate();
        verifyValues(72);
    }


    @Test
    public void testAddOperation(){
        clickNumber(9);
        operation(Constants.PLUS);
        clickNumber(8);
        calculate();
        verifyValues(17);
    }

    @Test
    public void testMinusOperation(){
        clickNumber(9);
        operation(Constants.MINUS);
        clickNumber(8);
        calculate();
        verifyValues(1);
    }

    @Test
    public void testDivideOperation(){
        clickNumber(1000);
        operation(Constants.DELETE);
        operation(Constants.DIVIDE);
        clickNumber(10);
        calculate();
        verifyValues(10);
    }

    @Test
    public void testPowerOperation(){
        clickNumber(2);
        operation(Constants.POWER);
        clickNumber(3);
        calculate();
        verifyValues(8);
    }

}

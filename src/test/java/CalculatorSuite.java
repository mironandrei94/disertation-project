import calculator.Constants;
import calculator.MainMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.BitSet;

public class CalculatorSuite extends MainMethods {


    String appPackage = "com.sec.android.app.popupcalculator";
    String appActivity = "com.sec.android.app.popupcalculator.Calculator";

    //Logger
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(CalculatorSuite.class.getName());
    private static final Marker This = MarkerManager.getMarker(CalculatorSuite.class.getName());

    //Driver initialization
    @BeforeMethod
    public void tearup() throws IOException
    {
        // Init driver and PageObject
        driver = setUp(appPackage, appActivity);
    }

    @AfterMethod
    public void teardown()
    {
        driver.quit();
    }
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

package calculator;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import resources.Base;

import java.util.Collection;

public class MainMethods extends Base {

    protected void clickNumber(int number){
        for (char c: String.valueOf(number).toCharArray()) {
                clickDigit(c);
            }
        }

    private void clickDigit(char digit){

        MobileElement el1 = (MobileElement) driver.findElementById(String.format("com.sec.android.app.popupcalculator:id/bt_0%s", String.valueOf(digit)));
        el1.click();
        }

    protected void operation(String oper){
        MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId(oper);
        el2.click();
    }

    protected void calculate(){
        MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("Egal");
        el4.click();
    }

    protected void verifyValues(int number){
        Assert.assertEquals(driver.findElementById("com.sec.android.app.popupcalculator:id/txtCalc").getText(), String.valueOf(number));
    }
}

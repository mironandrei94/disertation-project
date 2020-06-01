package droidweight;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainMethods extends DriverSetup {

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(game2048.MainMethods.class.getName());
    private static final Marker This = MarkerManager.getMarker(game2048.MainMethods.class.getName());


    //locators
    public PointOption minusminus = PointOption.point(212, 381);
    public PointOption minus = PointOption.point(334, 381);
    public PointOption plusplus = PointOption.point(849, 381);
    public PointOption plus = PointOption.point(734, 381);
    public PointOption setCurrentWeight = PointOption.point(565, 532);
    By weight = By.id("de.delusions.measure:id/measure");
    By newWeight = By.id("de.delusions.measure:id/input");
    By comment = By.id("de.delusions.measure:id/comment");
    By okButton = By.id("de.delusions.measure:id/ok");

    public void enterCurrentWeight(String value) throws InterruptedException {
        log.info(This, "click text box");
        MobileElement text = driver.findElementByClassName("android.widget.TextView");
        (new TouchAction(driver)).tap(PointOption.point(544, 388)).perform();
        Thread.sleep(2000);
        driver.findElementByClassName("android.widget.EditText").sendKeys(value);
        driver.findElementById("android:id/button1").click();
    }

    public void clickButton(PointOption locator){
        (new TouchAction(driver)).tap(locator).perform();
    }


    public String getLastWeight(){
        List<AndroidElement> values = driver.findElements(weight);
        if (values.size() != 0){
            return values.get(0).getText();
        }
        else return null;
    }

    public void longClick(){
        (new TouchAction(driver))
                .tap(PointOption.point(567, 682))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                .perform();
    }

    public void modifyWeight(String newWeight){
        MobileElement element = driver.findElement(this.newWeight);
        element.clear();
        element.sendKeys(newWeight);
        driver.findElement(comment).sendKeys("modified");
        driver.findElement(okButton).click();
    }

    public void multiTouchAction(){

        int height = driver.manage().window().getSize().getHeight(); //To get the mobile screen height
        int width = driver.manage().window().getSize().getWidth();//To get the mobile screen width

        TouchAction touch1 = new TouchAction(((AndroidDriver)driver));
        touch1.press(new PointOption().point(width/2, (height/2)-60)).
                waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).
                moveTo(new PointOption().point((width/2)-10,(height/2)-400)).
                release();
        TouchAction touch2 = new TouchAction(((AndroidDriver)driver));
        touch2.press(new PointOption().point((width/2)-30, (height/2)+60)).
                waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).
                moveTo(new PointOption().point((width/2)-50,(height/2)+400)).
                release();

        MultiTouchAction multi = new MultiTouchAction(driver);
        multi.add(touch1).add(touch2).perform();
    }

    public void dragAction(){
        TouchAction action = new TouchAction(((AndroidDriver)driver));
        action.press(new PointOption().point(100,100))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                .moveTo(new PointOption().point(100,200))
                .perform()
                .release();
    }

}

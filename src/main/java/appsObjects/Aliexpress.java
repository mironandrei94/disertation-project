package aliexpress;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.touch.TouchActions;
import resources.Base;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Aliexpress extends Base {

    //locators
    By stay = By.xpath(".//div[@class='smb-dialog-btn smb-dialog-btn__no']");
    By input = By.id("com.alibaba.aliexpresshd:id/rl_search_box");
    By inputText = By.id("com.alibaba.aliexpresshd:id/abs__search_src_text");
    By image= By.className("android.widget.ImageView");
    By search = By.id("com.alibaba.aliexpresshd:id/abs__search_go_btn");

    protected void searchAliexpress(String inputString) throws InterruptedException {
//        driver.findElement(stay).click();
        Thread.sleep(4000);
        driver.findElement(input).click();
        driver.findElement(inputText).sendKeys(inputString);
        Thread.sleep(5000);
//        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.findElement(search).click();
        Thread.sleep(5000);
    }

    public void scrollDownUntilElementVisible(AndroidElement element){
        Point source = element.getCenter();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(),
                source.x / 2, source.y + 600));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(800),
                PointerInput.Origin.viewport(), source.getX() / 2, source.y / 2));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        driver.perform(Arrays.asList(dragNDrop));


    }

    public AndroidElement getElementByIndex(int elementIndex){
        ArrayList<AndroidElement> images = (ArrayList<AndroidElement>) driver.findElements(image);
        return images.get(elementIndex);

    }

    public void click(PointOption locator){
        (new TouchAction(driver)).tap(locator).perform();
    }






}

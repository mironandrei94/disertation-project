package googleMaps;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;

public class MainMethods extends DriverSetup{

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(MainMethods.class.getName());
    private static final Marker This = MarkerManager.getMarker(MainMethods.class.getName());
    public String newLine = System.getProperty("line.separator");

    //locators
    By searchLocation = By.id("com.google.android.apps.maps:id/search_omnibox_text_box");
    By searchLocationTextEdit = By.id("com.google.android.apps.maps:id/search_omnibox_edit_text");
    By startNavigation = By.xpath("//android.widget.Button[@content-desc='Începeți navigarea']");
    By approve = By.id("android:id/button1");
    By duration = By.id("com.google.android.apps.maps:id/navigation_time_remaining_label");
    By actualSpeed = By.id("com.google.android.apps.maps:id/speedometer");
    By recenter = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.TextView");
    By goButton = By.xpath("//android.widget.Button[@content-desc='Indicații de orientare spre Galați']");
    By destination = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.EditText[2]/android.widget.LinearLayout/android.widget.TextView");

    public enum DIRECTION {
        DOWN, UP, LEFT, RIGHT;
    }

    public static void swipe(DIRECTION direction, long duration) {
        Dimension size = driver.manage().window().getSize();
        log.info(This,"Swipe in the direction: " + direction);
        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;

        switch (direction){
            case RIGHT:
//                LOGGER.info("Swiped Right");
                startY = (int) (size.height /2);
                startX = (int) (size.width * 0.90);
                endX = (int) (size.width * 0.05);
                break;

            case LEFT:
//                LOGGER.info("Swiped Left");
                startY = (int) (size.height /2);
                startX = (int) (size.width * 0.05);
                endX = (int) (size.width * 0.90);
                break;

            case UP:
//                LOGGER.info("Swiped Up");
                endY= (int) (size.height * 0.70);
                startY  = (int) (size.height * 0.30);
                startX = (size.width / 2);
                break;


            case DOWN:
//                LOGGER.info("Swiped Down");
                startY = (int) (size.height * 0.70);
                endY = (int) (size.height * 0.30);
                startX = (size.width / 2);

                break;

        }

        new TouchAction(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                .moveTo(PointOption.point(endX, startY))
                .release()
                .perform();

    }


    public void searchGoogleMapsLocation(String location) throws InterruptedException {
        driver.findElement(searchLocation).click();
        driver.findElement(searchLocationTextEdit).sendKeys(location);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        Thread.sleep(10000);
        driver.findElement(goButton).click();
        Thread.sleep(5000);
        startNavigation();

    }

    public void startNavigation() throws InterruptedException {
        log.info(This,"Start navigation to the destination");
        driver.findElement(startNavigation).click();
        Thread.sleep(2000);
        driver.findElement(approve).click();
    }

    public String getDuration(){
        log.info(This,"Get duration!");
        return driver.findElement(duration).getText();
    }

    public String getSpeed(){
        log.info(This,"Get actual speed!");
        return driver.findElement(actualSpeed).getAttribute("content-desc");
    }

    public void recenterView(){
        log.info(This,"Press recenter");
        driver.findElement(recenter).click();
        log.info(This,"Maps screen is recenter");
    }
}

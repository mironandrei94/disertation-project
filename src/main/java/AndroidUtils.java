import game2048.MainMethods;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class AndroidUtils {

    public AndroidDriver driver;
    public Logger log;
    public Marker marker;

    By notification = By.xpath(".//android.widget.TextView[@resource-id='android:id/title']");

    public AndroidUtils(AndroidDriver driver, Logger log, Marker marker) {
        this.driver = driver;
        this.log = log;
        this.marker = marker;
    }

    public enum DIRECTION {
        DOWN, UP, LEFT, RIGHT;
    }

    public void setAllConnectionOff(){
        try {
            driver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
            driver.setConnection(new ConnectionStateBuilder().withAirplaneModeDisabled().build());
            driver.setConnection(new ConnectionStateBuilder().withDataDisabled().build());
            log.info(marker,"Switching OFF the connection : " + driver.getConnection());
        } catch (Exception e) {
            log.info(marker,"Connection could not be switch OFF");
        }
    }

    @SuppressWarnings("rawtypes")
    public void pressFlightMode(int waitingTime){
        try {
            ((AndroidDriver)driver).toggleAirplaneMode();
            sleep(waitingTime);
        } catch (Exception e ) {
            System.out.println("Error turning on flight mode.");
        }

    }

    @SuppressWarnings("rawtypes")
    public void pressMobileData(int waitingTime){
        try {
            ((AndroidDriver)driver).toggleData();
            sleep(waitingTime);
        } catch (Exception e ) {
            System.out.println("Error turning on flight mode.");
        }

    }

    @SuppressWarnings("rawtypes")
    public void pressWifi(int waitingTime){
        try {
            ((AndroidDriver)driver).toggleWifi();
            sleep(waitingTime);
        } catch (Exception e ) {
            System.out.println("Error turning off flight mode.");
        }

    }

    public void takeScreenShot(String testname) throws IOException {
        File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C:\\Users\\Andre\\Desktop\\disertatie\\disertatie\\src\\main\\java\\screenshots\\"+testname+".jpg"));
    }

    public String getNotification(){
        String notificationText = driver.findElement(notification).getText();
        log.info(marker, "notification: "+ notificationText);
        return notificationText;
    }


    public void swipe(MainMethods.DIRECTION direction, long duration) {
        Dimension size = driver.manage().window().getSize();
        log.info(marker,"Swipe in the direction: " + direction);
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
}

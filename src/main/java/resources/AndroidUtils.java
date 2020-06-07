package resources;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
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
import java.util.Base64;
import java.util.List;

import static java.lang.Thread.sleep;

public class AndroidUtils {

    public AndroidDriver driver;
    public Logger log;
    public Marker marker;


    //locators
    By notification = By.xpath(".//android.widget.TextView[@resource-id='android:id/title']");
    By thumbObject = By.className("com.sec.samsung.gallery.glview.composeView.ThumbObject");
    By firstPhoto = By.xpath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.view.View/com.sec.samsung.gallery.glview.composeView.ThumbObject[1]");

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


    public void swipe(DIRECTION direction, long duration) {
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

    public void startRecordTest(){
        driver.startRecordingScreen();
    }

    public void stopRecordingTest(String videoName) throws IOException, InterruptedException {
        Thread.sleep(5000);
        String s = ((AndroidDriver) driver).stopRecordingScreen();
        byte[] decode = Base64.getDecoder().decode(s);
        FileUtils.writeByteArrayToFile(new File("C:\\Users\\Andre\\Desktop\\disertatie\\disertatie\\src\\main\\java\\screenshots\\"+videoName+".mp4"),
                decode);
    }

    public void uploadFile(String imageNameAndExtension) throws IOException, InterruptedException {

        driver.pushFile("/storage/emulated/0/Download/"+imageNameAndExtension,
                new File("C:\\Users\\Andre\\Desktop\\disertatie\\disertatie\\src\\main\\java\\resources\\filesForUpload\\"+imageNameAndExtension));
    }

    public List<AndroidElement> getImagesDisplayed(){
        return driver.findElements(thumbObject);
    }

    public void clickFirstPhoto(){
        driver.findElement(firstPhoto).click();
    }

    public void click(PointOption locator){
        (new TouchAction(driver)).tap(locator).perform();
    }
}

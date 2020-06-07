package appsObjects;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import io.appium.java_client.TouchAction;
import resources.Base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class Game2048 extends Base {

    //Selectors
    By refreshButton = By.xpath(".//android.widget.ImageButton[@resource-id='com.scn.twok48:id/btn_refresh']");
    By backButton = By.xpath(".//android.widget.ImageButton[@resource-id='com.scn.twok48:id/btn_undo']");
    By resetButton = By.xpath(".//android.widget.Button[@resource-id='android:id/button1']");
    By score = By.id("com.scn.twok48:id/txt_score");
    By notification = By.xpath(".//android.widget.TextView[@resource-id='android:id/title']");

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(Game2048.class.getName());
    private static final Marker This = MarkerManager.getMarker(Game2048.class.getName());
    public String newLine = System.getProperty("line.separator");

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

    public void setAllConnectionOff(){
        try {
            driver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
            driver.setConnection(new ConnectionStateBuilder().withAirplaneModeDisabled().build());
            driver.setConnection(new ConnectionStateBuilder().withDataDisabled().build());
            log.info(This,"Switching OFF the connection : " + driver.getConnection());
        } catch (Exception e) {
            log.info(This,"Connection could not be switch OFF");
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

    /**
     * Method used format strings for logs
     * @param stepNo Step number
     * @param stepDescription used for step description
     * @return formated message
     */
    public String logStepFormatMessage(String stepNo, String stepDescription){
        String charForSeparator = "#";
        String separator = this.multiplyString(charForSeparator, 20);
        String step = separator + "  Step " + stepNo + "  " + separator;
        String stepDescriptionSeparator = this.multiplyString(charForSeparator, step.length());
        return this.newLine + step + this.newLine + stepDescription + this.newLine + stepDescriptionSeparator;
    }

    /**
     * Method used multiply strings
     * @param str String to be multiplied
     * @param length Time to multiply string
     * @return string multiplied
     */
    public String multiplyString(String str, int length)
    {
        StringBuilder string = new StringBuilder();
        for(int i=0; i<length ; i++)
        {
            string.append(str);
        }
        return string.toString();
    }

    public void clickRefresh(){
        this.clickButton(refreshButton);
    }

    public void clickBackButton(){
        this.clickButton(backButton);
    }

    public void clickResetButton(){
        this.clickButton(resetButton);
    }

    public void clickButton(By selector){
        driver.findElement(selector).click();
    }

    public void longClick(){
        Actions action = new Actions(driver);
        action.clickAndHold(driver.findElement(refreshButton));
        action.perform();
    }

    public void dragAction(){
        TouchAction action = new TouchAction(((AndroidDriver)driver));
        action.press(new PointOption().point(100,100))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                .moveTo(new PointOption().point(100,200))
                .perform()
                .release();
    }

    public String getScore(){
        return driver.findElement(score).getText();
    }

    public void takeScreenShot() throws IOException {
        File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C:\\Personale\\documente personale\\disertatie\\project\\src\\main\\java\\screenshots\\Screenshot.jpg"));
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

    public String getNotification(){
        String notificationText = driver.findElement(notification).getText();
        log.info(This, "notification: "+ notificationText);
        return notificationText;
    }

}

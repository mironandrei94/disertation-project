import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class AndroidUtils {

    public AndroidDriver driver;
    public Logger log;
    public Marker marker;

    public AndroidUtils(AndroidDriver driver, Logger log, Marker marker) {
        this.driver = driver;
        this.log = log;
        this.marker = marker;
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

}

package game2048;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import resources.AppiumServerJava;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverSetup {

    public static AndroidDriver<AndroidElement> driver;
    AppiumServerJava server = new AppiumServerJava();

    @BeforeTest
    public void setUp() throws MalformedURLException {
        server.startServer();
        DesiredCapabilities desire = new DesiredCapabilities();
        //galaxy a5
        //desire.setCapability(MobileCapabilityType.DEVICE_NAME, "3300914cbc31634d");

        //emulated device
        desire.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        desire.setCapability("platformName", "android");
        desire.setCapability("appPackage", "com.scn.twok48");
        desire.setCapability("appActivity", "com.scn.twok48.MenuActivity");
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), desire);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        driver.findElementById("com.scn.twok48:id/btn_start_game1") .click();
        (new TouchAction(driver)).tap(PointOption.point(592, 1432)).perform();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

//    @AfterTest
//    public void End() {
//        driver.rotate(ScreenOrientation.PORTRAIT);
//        driver.quit();
//        server.stopServer();
//    }



}

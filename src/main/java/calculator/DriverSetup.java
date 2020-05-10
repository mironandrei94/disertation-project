package calculator;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import resources.AppiumServerJava;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import resources.AppiumServerJava;

public class DriverSetup {

    public AndroidDriver<AndroidElement> driver;
    private AppiumServerJava server = new AppiumServerJava();

    @BeforeTest
    public void setUp() throws MalformedURLException{
        server.startServer();
        DesiredCapabilities desire = new DesiredCapabilities();
//        desire.setCapability(MobileCapabilityType.DEVICE_NAME, "3300914cbc31634d");
        desire.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");

        desire.setCapability("platformName", "android");
        desire.setCapability("appPackage", "com.sec.android.app.popupcalculator");
        desire.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), desire);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    @AfterTest
    public void End() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        server.stopServer();
        }

}

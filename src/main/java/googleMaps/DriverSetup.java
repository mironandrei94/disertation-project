package googleMaps;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
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

        //emulated device
        desire.setCapability(MobileCapabilityType.DEVICE_NAME, "3300914cbc31634d");
        desire.setCapability("platformName", "android");
        desire.setCapability("appPackage", "com.google.android.apps.maps");
        desire.setCapability("appActivity", "com.google.android.maps.MapsActivity");
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), desire);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
}

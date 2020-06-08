package chrome;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import resources.AppiumServerJava;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class DriverSetup {

    public AndroidDriver<AndroidElement> driver;

    private AppiumServerJava server = new AppiumServerJava();

    @BeforeTest
    public void setUp() throws IOException {
        server.startServer();
        DesiredCapabilities desire = new DesiredCapabilities();
        desire.setCapability(MobileCapabilityType.DEVICE_NAME, "3300914cbc31634d");
//        desire.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        desire.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        desire.setCapability(CapabilityType.VERSION, "7.0");
        desire.setCapability(CapabilityType.PLATFORM_NAME, "WINDOWS");
        desire.setCapability("platformName", "Android");
        desire.setCapability("autoGrantPermissions","true");
        desire.setCapability("noReset", "true");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<>(url, desire);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);


    }


//    @AfterTest
//    public void End() {
////        server.stopServer();
//        driver.close();
//    }

}

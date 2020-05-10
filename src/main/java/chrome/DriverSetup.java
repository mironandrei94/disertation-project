package chrome;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import resources.AppiumServerJava;
import io.appium.java_client.android.AndroidDriver;


public class DriverSetup {

    public AndroidDriver<AndroidElement> driver;

    private AppiumServerJava server = new AppiumServerJava();

    @BeforeTest
    public void setUp() throws IOException {
        server.startServer();
        DesiredCapabilities desire = new DesiredCapabilities();
        desire.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        desire.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        desire.setCapability(CapabilityType.VERSION, "7.0");
        desire.setCapability(CapabilityType.PLATFORM_NAME, "WINDOWS");
        desire.setCapability("platformName", "Android");
        desire.setCapability("autoGrantPermissions","true");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<>(url, desire);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }



//    @AfterTest
//    public void End() {
////        server.stopServer();
//        driver.close();
//    }

}

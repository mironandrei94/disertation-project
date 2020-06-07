package resources;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import resources.AppiumServerJava;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Base {

    public static AndroidDriver<AndroidElement> driver;
    AppiumServerJava server = new AppiumServerJava();


    public AndroidDriver<AndroidElement> setUp(String appPackage, String appActivity) throws MalformedURLException {
        server.startServer();
        DesiredCapabilities desire = new DesiredCapabilities();
        //galaxy a5
        desire.setCapability(MobileCapabilityType.DEVICE_NAME, "3300914cbc31634d");

        //emulated device
        desire.setCapability("platformName", "android");
        desire.setCapability("appPackage", appPackage);
        desire.setCapability("appActivity", appActivity);
        desire.setCapability("automationName", "UiAutomator2");
        desire.setCapability("noReset", "true");
        desire.setAcceptInsecureCerts(true);
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), desire);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;

    }


}
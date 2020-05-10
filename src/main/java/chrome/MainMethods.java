package chrome;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.touch.TouchActions;
import game2048.MainMethods.DIRECTION;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainMethods extends DriverSetup {

    protected void searchYouTube(String field){
        driver.get("http://www.youtube.com");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        MobileElement searchButton = driver.findElementByXPath(".//button[@class='icon-button topbar-menu-button-avatar-button']");
        searchButton.click();
        MobileElement searchField = driver.findElementByXPath(".//input[@class='searchbox-input title']");
        searchField.sendKeys(field);
        searchField.sendKeys(Keys.ENTER);
        MobileElement video = driver.findElementByXPath(".//h4[@class='compact-media-item-headline']");
        video.click();
    }


    protected void searchGoogle(String input){
        driver.get("http://www.google.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MobileElement searchField = driver.findElementByXPath(".//input[@type='search']");
        searchField.sendKeys(input);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }





}

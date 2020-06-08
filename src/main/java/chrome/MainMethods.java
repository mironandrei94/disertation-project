package chrome;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.touch.TouchActions;

import javax.swing.text.Keymap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainMethods extends DriverSetup {

    //locators
    By stay = By.xpath(".//div[@class='smb-dialog-btn smb-dialog-btn__no']");
    By input = By.xpath(".//input[@type='text']");

    protected void searchYouTube(String field) throws InterruptedException {
        driver.get("http://www.youtube.com");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        MobileElement searchButton = driver.findElementByXPath(".//button[@class='icon-button topbar-menu-button-avatar-button']");
        searchButton.click();
        MobileElement searchField = driver.findElementByXPath(".//input[@class='searchbox-input title']");
        searchField.sendKeys(field);
        searchField.sendKeys(Keys.ENTER);
        Thread.sleep(10000);
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


    protected void searchAliexpress(String inputString) throws InterruptedException {
        driver.get("https://best.aliexpress.com/");
        Thread.sleep(1000);
        driver.findElement(stay).click();
        driver.findElement(input).sendKeys(inputString);
        Thread.sleep(3000);
//        driver.pressKey(new KeyEvent(AndroidKey.BACK));
//        Thread.sleep(1000);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        Keyboard keyboard = driver.getKeyboard();
        keyboard.pressKey("Acces");

    }





}

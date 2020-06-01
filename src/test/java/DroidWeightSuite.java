import droidweight.MainMethods;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DroidWeightSuite extends MainMethods {

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(DroidWeightSuite.class.getName());
    private static final Marker This = MarkerManager.getMarker(DroidWeightSuite.class.getName());

    @Test(priority = 1)
    public void testScreenItems() throws InterruptedException {
        ///E1,E2,B1
        log.info(This, "Start test");
        log.info(This, "Session id : " + driver.getSessionId());
        log.info(This, "Session current weight to: "+ 100);
        enterCurrentWeight("100");
        log.info(This, "click minus minus button");
        clickButton(minusminus);
        log.info(This, "click minus button");
        clickButton(minus);
        log.info(This, "click plus plus button");
        clickButton(plusplus);
        clickButton(plusplus);
        log.info(This, "click plus button");
        clickButton(plus);
        log.info(This, "click set current weight");
        clickButton(setCurrentWeight);
        String currentWeight = getLastWeight();
        Assert.assertEquals("101 kg", currentWeight);
        log.info(This, "Test passed");
    }

    @Test(priority = 2)
    public void testInternetConnection(){
        //A3
        AndroidUtils utils = new AndroidUtils(driver,log,This);
        log.info(This,"Start test");
        log.info(This,"Switching OFF mobile data, wifi, airplane");
        utils.setAllConnectionOff();
        log.info(This,"Switching ON mobile data");
        utils.pressMobileData(3000);
        log.info(This,"Switching ON Wifi");
        utils.pressWifi(3000);
    }

    @Test(priority = 3)
    public void testLongClickGesture(){
        //long click gesture
        log.info(This,"Start test");
        log.info(This, "Session id : "+ driver.getSessionId());
        log.info(This,"Long click gesture");
        longClick();
        log.info(This,"Set new weight");
        modifyWeight("102");
        String currentWeight = getLastWeight();
        Assert.assertEquals("102 kg", currentWeight);
    }

    @Test(priority = 4)
    public void testScreenshots() throws IOException, InterruptedException {
        AndroidUtils utils = new AndroidUtils(driver,log,This);
        log.info(This,"Start test");
        sleep(2000);
        log.info(This,"Take screen shot");
        utils.takeScreenShot("testScreenShot2");
    }

    @Test(priority = 5)
    public void testSoftKeyboard(){
        log.info(This,"Start test");
        boolean isKeyboardShown = driver.isKeyboardShown();
        assertFalse(isKeyboardShown);
        log.info(This,"Keyboard is not shown.");
    }



    @Test(priority = 6)
    public void testHardwareButtons(){
        log.info(This,"Start test");
        log.info(This,"Press home button");
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        log.info(This,"Press recent apps");
        driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
        log.info(This,"Press back button");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

    }

    @Test(priority = 7)
    public void testMultiTouchActions(){
        log.info(This,"Start test");
        log.info(This,"Multi touch action");
        multiTouchAction();
    }

    @Test(priority = 8)
    public void testNotificationsBar() throws InterruptedException {
        AndroidUtils utils = new AndroidUtils(driver,log,This);
        log.info(This,"Start test");
        log.info(This, "Open notifications bar");
        driver.openNotifications();
        sleep(2000);
        utils.getNotification();
        log.info(This, "Close notifications bar");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        log.info(This, "Session id : "+ driver.getSessionId());
    }

    @Test(priority = 9)
    public void testSwipes(){
        //swipe actions
        AndroidUtils utils = new AndroidUtils(driver,log,This);
        log.info(This,"Start test");
        utils.swipe(game2048.MainMethods.DIRECTION.RIGHT, 200);
        utils.swipe(game2048.MainMethods.DIRECTION.UP, 200);
        utils.swipe(game2048.MainMethods.DIRECTION.LEFT, 200);
        utils.swipe(game2048.MainMethods.DIRECTION.DOWN, 200);
    }

    @Test(priority = 10)
    public void testDragActions(){
        //drag action
        log.info(This,"Start test");
        log.info(This,"Drag action from (100,100) to (100,200)");
        dragAction();
        log.info(This, "Session id : "+ driver.getSessionId());
    }
}

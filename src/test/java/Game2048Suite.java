import appsObjects.Game2048;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.management.Notification;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.testng.Assert.*;


public class Game2048Suite extends Game2048 {

    String appPackage = "com.scn.twok48";
    String appActivity = "com.scn.twok48.MenuActivity";
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(Game2048Suite.class.getName());
    private static final Marker This = MarkerManager.getMarker(Game2048Suite.class.getName());

    @BeforeMethod
    public void tearup() throws IOException
    {
        // Init driver and PageObject
        driver = setUp(appPackage, appActivity);
    }

    @AfterMethod
    public void teardown()
    {
        driver.quit();
    }

    @Test(priority = 1)
    public void testInternetConnection(){
        //A3
        log.info(This,"Start test");
        log.info(This,"Switching OFF mobile data, wifi, airplane");
        setAllConnectionOff();
        log.info(This,"Switching ON mobile data");
        pressMobileData(3000);
        log.info(This,"Switching ON Wifi");
        pressWifi(3000);
    }

    @Test(priority = 2)
    public void testScreenItems() throws InterruptedException {
        ///B1
        log.info(This,"Start test");
        log.info(This,"Execute 2 swipes");
        swipe(DIRECTION.RIGHT, 200);
        swipe(DIRECTION.RIGHT, 200);
        log.info(This, "Click back button");
        clickBackButton();
        log.info(This, "Click refresh");
        clickRefresh();
        log.info(This, "Sleep 2 seconds");
        sleep(2000);
        log.info(This, "Click reset button");
        clickResetButton();
        log.info(This, "Session id : "+ driver.getSessionId());
    }

    @Test(priority = 3)
    public void testLongClickGesture(){
        //long click gesture
        log.info(This,"Start test");
        swipe(DIRECTION.RIGHT, 200);
        swipe(DIRECTION.RIGHT, 200);
        log.info(This,"Long click gesture");
        longClick();
        log.info(This, "Session id : "+ driver.getSessionId());
    }


    @Test(priority = 3)
    public void testDragActions(){
        //drag action
        log.info(This,"Start test");
        log.info(This,"Drag action from (100,100) to (100,200)");
        dragAction();
        log.info(This, "Session id : "+ driver.getSessionId());
    }

    @Test(priority = 4)
    public void testAllSwipes(){
        //swipe actions
        log.info(This,"Start test");
        for (int i=0 ; i< 2; i++){
            swipe(DIRECTION.RIGHT, 200);
            swipe(DIRECTION.RIGHT, 200);
            swipe(DIRECTION.RIGHT, 200);
            swipe(DIRECTION.UP, 200);
            swipe(DIRECTION.UP, 200);
            swipe(DIRECTION.UP, 200);
            swipe(DIRECTION.LEFT, 200);
            swipe(DIRECTION.LEFT, 200);
            swipe(DIRECTION.LEFT, 200);
            swipe(DIRECTION.DOWN, 200);
            swipe(DIRECTION.DOWN, 200);
            swipe(DIRECTION.DOWN, 200);
        }
        log.info(This, "Session id : "+ driver.getSessionId());
        assertTrue(Integer.parseInt(getScore()) > 0);
        log.info(This, "Final score is : "+ getScore());
    }

    @Test(priority = 4)
    public void testScreenshots() throws IOException, InterruptedException {
        log.info(This,"Start test");
        sleep(2000);
        log.info(This,"Take screen shot");
        takeScreenShot();
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
        log.info(This,"Start test");
        log.info(This, "Open notifications bar");
        driver.openNotifications();
        sleep(2000);
        getNotification();
        log.info(This, "Close notifications bar");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        log.info(This, "Session id : "+ driver.getSessionId());
    }
}

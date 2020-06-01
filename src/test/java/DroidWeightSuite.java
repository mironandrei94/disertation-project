import droidweight.MainMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class DroidWeightSuite extends MainMethods {

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(Game2048Suite.class.getName());
    private static final Marker This = MarkerManager.getMarker(Game2048Suite.class.getName());

    @Test
    public void firstTest(){
        log.info(This, "Session id : "+ driver.getSessionId());


    }

    @Test(priority = 1)
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

    @Test(priority = 2)
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
}

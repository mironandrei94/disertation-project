import aliexpress.Aliexpress;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.AndroidUtils;

import java.io.IOException;

public class AliexpressSuite extends Aliexpress {

    //appPackage and appActivity
    String appPackage = "com.alibaba.aliexpresshd";
    String appActivity = ".home.ui.MainActivity";

    //Logger
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(AliexpressSuite.class.getName());
    private static final Marker This = MarkerManager.getMarker(AliexpressSuite.class.getName());

    //Driver initialization
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

    //Actual test
    @Test
    public void searchAliexpres() throws InterruptedException, IOException {
        log.info(This, "Start test");
        log.info(This, "Session id : "+ driver.getSessionId());
        AndroidUtils utils = new AndroidUtils(driver,log,This);
        log.info(This, "Start recording");
        utils.startRecordTest();
        log.info(This, "Search for item");
        searchAliexpress("weightlifting shoes");
        AndroidElement image = getElementByIndex(3);
        log.info(This, "Scroll page");
        scrollDownUntilElementVisible(image);
        image = getElementByIndex(3);
        log.info(This, "Click item");
        click(PointOption.point(image.getCenter()));
        log.info(This, "Stop recording");
        utils.stopRecordingTest("aliexpressVideo");
    }
}

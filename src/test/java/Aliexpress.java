import aliexpress.MainMethods;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.testng.annotations.Test;

import java.io.IOException;

public class Aliexpress extends MainMethods {

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(Aliexpress.class.getName());
    private static final Marker This = MarkerManager.getMarker(Aliexpress.class.getName());

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

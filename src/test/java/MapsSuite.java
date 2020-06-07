import googleMaps.MainMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MapsSuite extends MainMethods {

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(MapsSuite.class.getName());
    private static final Marker This = MarkerManager.getMarker(MapsSuite.class.getName());

    @Test
    public void searchLocationAndStartNavigation() throws InterruptedException, IOException {
        log.info(This, "Session id : "+ driver.getSessionId());
        AndroidUtils utils = new AndroidUtils(driver,log,This);
        utils.startRecordTest();
        log.info(This,"Start test");
        String locatieDeplasare = "Galati";
        searchGoogleMapsLocation(locatieDeplasare);
        Assert.assertNotNull(getDuration());
        Assert.assertNotNull(getSpeed());

        // get metrics
        log.info(This,"Durata deplasarii pana la locatie "+ locatieDeplasare+ " este de: "+ getDuration());
        log.info(This, getSpeed());

        //swipes all directions
        utils.swipe(AndroidUtils.DIRECTION.RIGHT, 200);
        utils.swipe(AndroidUtils.DIRECTION.UP, 200);
        utils.swipe(AndroidUtils.DIRECTION.LEFT, 200);
        utils.swipe(AndroidUtils.DIRECTION.LEFT, 200);
        utils.swipe(AndroidUtils.DIRECTION.DOWN, 200);
        utils.swipe(AndroidUtils.DIRECTION.DOWN, 200);
        recenterView();

        //zoom in and zoom out
        testZooms();
        utils.stopRecordingTest("googleMapsVideo");

    }
}

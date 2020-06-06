import googleMaps.MainMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MapsSuite extends MainMethods {

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(Game2048Suite.class.getName());
    private static final Marker This = MarkerManager.getMarker(Game2048Suite.class.getName());

    @Test
    public void searchLocationAndStartNavigation() throws InterruptedException {
        log.info(This, "Session id : "+ driver.getSessionId());
        log.info(This,"Start test");
        String locatieDeplasare = "Galati";
        searchGoogleMapsLocation(locatieDeplasare);
        Assert.assertNotNull(getDuration());
        Assert.assertNotNull(getSpeed());
        log.info(This,"Durata deplasarii pana la locatie "+ locatieDeplasare+ " este de: "+ getDuration());
        log.info(This, getSpeed());
        swipe(DIRECTION.RIGHT, 200);
        swipe(DIRECTION.UP, 200);
        swipe(DIRECTION.LEFT, 200);
        swipe(DIRECTION.LEFT, 200);
        swipe(DIRECTION.DOWN, 200);
        swipe(DIRECTION.DOWN, 200);
        recenterView();


    }
}

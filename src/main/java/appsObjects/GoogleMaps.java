package googleMaps;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import resources.Base;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

public class GoogleMaps extends Base {

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(googleMaps.GoogleMaps.class.getName());
    private static final Marker This = MarkerManager.getMarker(googleMaps.GoogleMaps.class.getName());
    public String newLine = System.getProperty("line.separator");

    //locators
    By searchLocation = By.id("com.google.android.apps.maps:id/search_omnibox_text_box");
    By searchLocationTextEdit = By.id("com.google.android.apps.maps:id/search_omnibox_edit_text");
    By startNavigation = By.xpath("//android.widget.Button[@content-desc='Începeți navigarea']");
    By approve = By.id("android:id/button1");
    By duration = By.id("com.google.android.apps.maps:id/navigation_time_remaining_label");
    By actualSpeed = By.id("com.google.android.apps.maps:id/speedometer");
    By recenter = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.TextView");
    By goButton = By.xpath("//android.widget.Button[@content-desc='Indicații de orientare spre Galați']");
    By destination = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.EditText[2]/android.widget.LinearLayout/android.widget.TextView");

   public void searchGoogleMapsLocation(String location) throws InterruptedException {
        driver.findElement(searchLocation).click();
        driver.findElement(searchLocationTextEdit).sendKeys(location);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        Thread.sleep(10000);
        driver.findElement(goButton).click();
        Thread.sleep(5000);
        startNavigation();

    }

    public void startNavigation() throws InterruptedException {
        log.info(This,"Start navigation to the destination");
        driver.findElement(startNavigation).click();
        Thread.sleep(2000);
        driver.findElement(approve).click();
    }

    public String getDuration(){
        log.info(This,"Get duration!");
        return driver.findElement(duration).getText();
    }

    public String getSpeed(){
        log.info(This,"Get actual speed!");
        return driver.findElement(actualSpeed).getAttribute("content-desc");
    }

    public void recenterView(){
        log.info(This,"Press recenter");
        driver.findElement(recenter).click();
        log.info(This,"Maps screen is recenter");
    }

    public WebElement getMap(){
       return driver.findElementById("com.google.android.apps.maps:id/mainmap_container");
    }

    private Sequence zoomSinglefinger(String fingerName, Point locus, int startRadius, int endRadius, double angle, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, fingerName);
        Sequence fingerPath = new Sequence(finger, 0);

        double midpointRadius = startRadius + (endRadius > startRadius ? 1 : -1) * 20;

        // find coordinates for starting point of action (converting from polar coordinates to cartesian)
        int fingerStartx = (int)Math.floor(locus.x + startRadius * Math.cos(angle));
        int fingerStarty = (int)Math.floor(locus.y - startRadius * Math.sin(angle));

        // find coordinates for first point that pingers move quickly to
        int fingerMidx = (int)Math.floor(locus.x + (midpointRadius * Math.cos(angle)));
        int fingerMidy = (int)Math.floor(locus.y - (midpointRadius * Math.sin(angle)));

        // find coordinates for ending point of action (converting from polar coordinates to cartesian)
        int fingerEndx = (int)Math.floor(locus.x + endRadius * Math.cos(angle));
        int fingerEndy = (int)Math.floor(locus.y - endRadius * Math.sin(angle));

        // move finger into start position
        fingerPath.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), fingerStartx, fingerStarty));
        // finger comes down into contact with screen
        fingerPath.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        // finger moves a small amount very quickly
        fingerPath.addAction(finger.createPointerMove(Duration.ofMillis(1), PointerInput.Origin.viewport(), fingerMidx, fingerMidy));
        // pause for a little bit
        fingerPath.addAction(new Pause(finger, Duration.ofMillis(100)));
        // finger moves to end position
        fingerPath.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), fingerEndx, fingerEndy));
        // finger lets up, off the screen
        fingerPath.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        return fingerPath;
    }

    private Collection<Sequence> zoom(Point locus, int startRadius, int endRadius, int pinchAngle, Duration duration) {
        // convert degree angle into radians. 0/360 is top (12 O'clock).
        double angle = Math.PI / 2 - (2 * Math.PI / 360 * pinchAngle);

        // create the gesture for one finger
        Sequence fingerAPath = zoomSinglefinger("fingerA", locus, startRadius, endRadius, angle, duration);

        // flip the angle around to the other side of the locus and get the gesture for the second finger
        angle = angle + Math.PI;
        Sequence fingerBPath = zoomSinglefinger("fingerB", locus, startRadius, endRadius, angle, duration);

        return Arrays.asList(fingerAPath, fingerBPath);
    }


    protected Collection<Sequence> zoomIn(Point locus, int distance) {
        return zoom(locus, 200, 200 + distance, 45, Duration.ofMillis(25));
    }

    protected Collection<Sequence> zoomOut(Point locus, int distance) {
        return zoom(locus, 200 + distance, 200, 45, Duration.ofMillis(25));
    }

    protected Point getCenter(Rectangle rect) {
        return new Point(rect.x + rect.getWidth() / 2, rect.y + rect.getHeight() / 2);
    }

    protected void testZooms() throws InterruptedException {
        WebElement map = getMap();
        map.click();

        Rectangle mapCoordinates = map.getRect();
        Point center = getCenter(mapCoordinates);

        log.info(This,"Zoom out!");
        driver.perform(zoomOut(center, 450));
        Thread.sleep(1000);
        log.info(This,"Zoom in!");
        driver.perform(zoomIn(center, 1050));

        Thread.sleep(1000);
        log.info(This,"Zoom out!");
        driver.perform(zoomOut(center.moveBy(0, 250), 300));

        Thread.sleep(1000);
        log.info(This,"Zoom in!");
        driver.perform(zoomIn(center.moveBy(0, -250), 300));

        Thread.sleep(3000);
    }

}

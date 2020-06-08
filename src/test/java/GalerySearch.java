import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.AndroidUtils;
import resources.Base;

import java.io.IOException;
import java.util.List;

public class GalerySearch extends Base {

    String appPackage = "com.sec.android.gallery3d";
    String appActivity = ".app.GalleryOpaqueActivity";
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(GalerySearch.class.getName());
    private static final Marker This = MarkerManager.getMarker(GalerySearch.class.getName());


    @BeforeMethod
    public void tearup() throws IOException
    {
        // Init driver and PageObject
        driver = setUp(appPackage, appActivity);
    }

//    @AfterMethod
//    public void teardown()
//    {
//        driver.quit();
//    }

    @Test
    public void SearchPhoto() throws IOException, InterruptedException {
        log.info(This, "Start test");
        log.info(This, "Session id : "+ driver.getSessionId());
        AndroidUtils utils = new AndroidUtils(driver,log,This);
        utils.startRecordTest();
        log.info(This, "Upload 4 images into the device");
        utils.uploadFile("image1.jpg");
        utils.uploadFile("image2.jpg");
        utils.uploadFile("image3.jpeg");
        utils.uploadFile("image4.jpg");
        Thread.sleep(3000);
        log.info(This, "Verify the number of images on the device is 4");
        List<AndroidElement> images= utils.getImagesDisplayed();
        Assert.assertEquals(4, images.size());
        log.info(This, "Click on the first image");
        utils.clickFirstPhoto();
        utils.stopRecordingTest("SearchPhoto");
    }
}

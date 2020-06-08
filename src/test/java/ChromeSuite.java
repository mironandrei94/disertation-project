import calculator.Constants;
import chrome.MainMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.testng.annotations.Test;


public class ChromeSuite extends MainMethods{

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(ChromeSuite.class.getName());
    private static final Marker This = MarkerManager.getMarker(ChromeSuite.class.getName());

    @Test
    public void youTubeSearch() throws InterruptedException {
        searchYouTube("we are the champions");

    }
    @Test
    public void googleScroll(){
        searchGoogle("honda civic 5D");
    }
}

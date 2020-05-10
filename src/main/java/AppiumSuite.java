import calculator.Constants;
import chrome.MainMethods;
import org.testng.annotations.Test;


public class AppiumSuite extends MainMethods{

    @Test
    public void youTubeSearch(){
        searchYouTube("miami bici");

    }
    @Test
    public void googleScroll(){
        searchGoogle("honda civic 5D");
    }


}

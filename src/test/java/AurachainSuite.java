import aurachain.PortalPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.annotations.Test;

import java.io.IOException;


public class AurachainSuite extends PortalPage {
    //vars
    String proceduraDorita = "somaj tehnic potrivit";
    String institutieDorita ="Galati";
    String numePrenume = "MironAndrei";
    String numeFirma = "Android local device submit";
    String mail = "mironandreicostin@gmail.com";
    String nrTelefon = "0722594767";


    @Test
    public void loginAurachain() throws InterruptedException {
        String url = "https://qa-master.kb02.aurachain.ch/app/";
        getAurachainURl(url);
        login();
    }

    @Test
    public void perfPortalTest() throws InterruptedException, IOException {
        String url = "https://perf-portal.kb03.aurachain.ch/home";
        getAurachainURl(url);
        submitFullRequest(
                proceduraDorita,
                institutieDorita,
                numePrenume,
                numeFirma,
                mail,
                nrTelefon
                );
    }

}

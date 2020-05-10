package aurachain;

import chrome.DriverSetup;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PortalPage extends DriverSetup {

    // Locators
    By file = By.xpath(".//div[@class='col-auto upload row items-center justify-between cursor']");
    By numeSiPrenume = By.xpath("(//input[@tabindex])[1]");
    By numeFirma = By.xpath("(//input[@tabindex])[2]");
    By adresaMail = By.xpath("(//input[@tabindex])[3]");
    By nrTelefon = By.xpath("(//input[@tabindex])[4]");
    By button = By.xpath("//button");
    By procedura = By.xpath("(//input[@type])[2]");
    By institutie = By.xpath("(//input[@type])[2]");
    By selection = By.xpath("//div[@tabindex][contains(@class,'q-item')]");
    By searchProcedura = By.xpath("(//div[@class='q-field__prepend q-field__marginal row no-wrap items-center'])[1]");
    By searchInstitutie = By.xpath("(//div[@class='q-field__prepend q-field__marginal row no-wrap items-center'])[2]");

    protected void getAurachainURl(String url){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public WebElement getField(By element) {
        return driver.findElement(element);
    }

    public WebElement getSelection() {
        return driver.findElement(selection);
    }

    public WebElement getSearchButton(By search){
        return driver.findElement(search);
    }

    public WebElement getButton() {
        return this.driver.findElement(button);
    }

    protected void login() throws InterruptedException {
        driver.findElementByXPath("//input[@type='text']").sendKeys("testautomation@aurachain.ch");
        clickContinue();
        driver.findElementByXPath("//input[@type='password']").sendKeys("Parola123!@#");
        driver.hideKeyboard();
        Thread.sleep(2000);
        loginButton();
    }

    public void submitFullRequest(String procedura,
                                  String institutie,
                                  String numeSiPrenume,
                                  String firma,
                                  String mail,
                                  String nrTel) throws InterruptedException, IOException {
        this.selectProceduraInstitutie(procedura, institutie);
        Thread.sleep(5000);
        this.insertNameAndEmail(numeSiPrenume, firma, mail, nrTel);
        driver.hideKeyboard();
        Thread.sleep(5000);
        this.uploadFile();

    }

    public void uploadFile() throws IOException, InterruptedException {

        driver.pushFile("/storage/emulated/0/Download/Performance_Portal_upload.pdf",
                new File("C:\\test-automation\\testautomation\\src\\test\\java\\aurachain\\testResources\\Performance_Portal_upload.pdf"));
        List<AndroidElement> documente = this.getUploadFiles();
        for (int i = 0; i < documente.size(); i ++) {
            this.getField(file).click();
            Thread.sleep(1500);
            driver.context("NATIVE_APP");
            if (i ==0) driver.findElement(By.xpath(".//android.widget.Button[@text='Only this time']")).click();
//            if (i ==0) driver.findElement(By.xpath(".//android.widget.Button[@text='Allow']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath(".//android.widget.TextView[@text='Files']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(".//android.widget.TextView[@text='Performance_Portal_upload.pdf']")).click();
            driver.context("CHROMIUM");
            Map<String, Object> caps = driver.getSessionDetails();
            System.out.println(caps.toString());
            Thread.sleep(2000);
        }
        this.getButton().click();
    }


    public List<AndroidElement> getUploadFiles() {
        By uploadFile = By.xpath("//input[@type='file']");
        return this.driver.findElements(uploadFile);
    }

    public void selectProceduraInstitutie(String procedura, String institutie){
        this.getSearchButton(searchProcedura).click();
        this.getField(this.procedura).sendKeys(procedura);
        this.getSelection().click();
        this.getSearchButton(searchInstitutie).click();
        this.getField(this.institutie).sendKeys(institutie);
        this.getSelection().click();
        this.getButton().click();
    }

    public void insertNameAndEmail(String nume,String firma, String email, String nrTelefon){
        this.getField(this.numeSiPrenume).sendKeys(nume);
        this.getField(this.numeFirma).sendKeys(firma);
        this.getField(this.adresaMail).sendKeys(email);
        this.getField(this.nrTelefon).sendKeys(nrTelefon);

    }

    protected void clickContinue(){
        driver.findElementByXPath("//button[@at-id='validate-email-btn']").click();
    }

    protected void loginButton(){
        driver.findElementByXPath("//button[@at-id='go-to-login-btn']").click();
    }



}

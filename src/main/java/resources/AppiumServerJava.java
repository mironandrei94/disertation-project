package resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppiumServerJava {

    private static String sdkPath = "C:\\Users\\Andre\\AppData\\Local\\Android\\Sdk\\";
    private static String adbPath = sdkPath + "platform-tools" + File.separator + "adb";

    Runtime runtime = Runtime.getRuntime();

    public void startServer() {
        try {
//            if (!isEmulatorOrDeviceRunning())
//                startAndroidSimulator();
//                Thread.sleep(100000);
            runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\" --chromedriver-executable C:\\Users\\Andre\\node_modules\\appium\\node_modules\\appium-chromedriver\\chromedriver\\win\\chromedriver.exe");
            Thread.sleep(10000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void startAndroidSimulator() throws IOException {
        runtime.exec("cmd /c start cmd.exe /K emulator -avd Pixel_XL_API_R -wipe-data");
        runtime.exec("adb start-server");
    }
    public void stopServer() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("taskkill /F /IM node.exe");
            runtime.exec("taskkill /F /IM cmd.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isEmulatorOrDeviceRunning() {

        try {
            String[] commandDevices = new String[] { adbPath, "devices" };
            Process process = new ProcessBuilder(commandDevices).start();

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String output = "";
            String line = null;
            while ((line = inputStream.readLine()) != null) {
                System.out.println(line);
                output = output + line;
            }
            if (!output.replace("List of devices attached", "").trim().equals("")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
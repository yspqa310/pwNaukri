package StepDefinition;

import com.microsoft.playwright.Page;
import io.cucumber.java.*;
import utilities.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

public class Hooks extends genericMethods {
    static String url = "";

    @Before
    public static void before() throws IOException {
        launchURL();
        writeLogInfo("Successfully launched the browser and url");
    }

    @After
    public static void after(Scenario scenario) throws IOException {
        closeBrowser(scenario);
        writeLogInfo("Successfully closed browser");
    }

    public static void launchURL() throws IOException {
        InitiatingBrowser();
        Page page = getPage();
        String Environmet = GetProperty("Environment");
        if (Environmet.equalsIgnoreCase("sit")) {
            url = GetProperty("SITURL");
            page.navigate(url);
        } else if (Environmet.equalsIgnoreCase("UAT")) {
            url = GetProperty("UATURL");
            page.navigate(url);
        } else if (Environmet.equalsIgnoreCase("STG")) {
            url = GetProperty("STGURL");
            page.navigate(url);
        }
        writeLogInfo(page.title() + " Application has opened successfully");
    }

    public static void closeBrowser(Scenario scenario) throws IOException {
        try {
            if (scenario.isFailed()) {
                final byte[] screenshot = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./reports/ScreenShots/" + scenario.getName() + ".png")).setFullPage(true));
                scenario.attach(screenshot, "failed" + scenario.getName() + "/png", scenario.getName());
                writeLogInfo("Successfully Captured screenShot for  " + scenario.getName());
            }
        } catch (Exception pasha) {
            writeLogInfo("Facing issue Capturing ScreenShot : " + pasha);
            System.err.println("Facing issue while capturing ScreenShot : " + pasha);
        }
        getPage().close();
        getContext().close();
        getBrowser().close();
        getPlaywright().close();
    }

    public static void afterEveryStep(Scenario scenario) {
        try {
            final byte[] screenshot = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./reports/ScreenShots/" + scenario.getName() + RandomNum() + ".png")).setFullPage(true));
            scenario.attach(screenshot, "failed" + scenario.getLine() + RandomNum() + "/png", scenario.getLine().toString());
            writeLogInfo("Successfully Captured screenShot for  " + scenario.getName());
        } catch (Exception pasha) {
            writeLogInfo("Facing issue Capturing ScreenShot : " + pasha);
            System.err.println("Facing issue while capturing ScreenShot : " + pasha);
        }
    }

    public static String RandomNum() {
        Random r = new Random();
        int num = r.nextInt(99999);
        return Integer.toString(num);
    }
}

package StepDefinition;

import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

import static utilities.myBrowser.*;

public class Hooks extends genericMethods {
    static String url = "";

    @Before
    public static void before() throws IOException {
        launchURL();
    }

    @After
    public static void after(Scenario scenario) throws IOException {
        closeBrowser(scenario);
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
        Date date = new Date();

        try {
            if (scenario.isFailed()) {
                final byte[] screenshot = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./reports/ScreenShots/"+ scenario.getName() +".png")).setFullPage(true));
                scenario.attach(screenshot, "failed" + scenario.getName() + "/png", scenario.getName());
                writeLogInfo("Successfully Captured screenShot for  " + scenario.getName());
            }
        } catch (Exception pasha) {
            writeLogInfo("Facing issue Capturing ScreenShot : " + pasha);
            System.err.println("Facing issue while capturing ScreenShot : " + pasha);
        }
        getPage().close();
        getBrowser().close();
        getPlaywright().close();
    }

}

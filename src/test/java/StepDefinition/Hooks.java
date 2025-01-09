package StepDefinition;

import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.*;

import java.io.IOException;

import static utilities.myBrowser.*;

public class Hooks extends genericMethods {
    static String url = "";

    @Before
    public static void before() throws IOException {
        launchURL();
    }

    @After
    public static void after(Scenario scenario) throws IOException {
        closeBrowser();
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

    public static void closeBrowser() throws IOException {
        getPage().close();
        getBrowser().close();
        getPlaywright().close();
    }

}

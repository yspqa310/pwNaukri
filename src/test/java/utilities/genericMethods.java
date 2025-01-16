package utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

import java.awt.*;
import java.util.Scanner;

public class genericMethods extends myBrowser {
    static Page page;
    public static Logger LOGGER = Logger.getLogger(genericMethods.class);
    Scanner scanner = new Scanner(System.in);

    //    below methods are for the Logging purpose
    public static void writeLogInfo(String loginfo) {
        LOGGER.info("*************  " + loginfo + "  *************");
    }

    public void writeLoginfo(String loginfo) {
        LOGGER.info("*************  " + loginfo + "  *************");
    }

    public static void writeLogWarn(String logWarn) {
        LOGGER.warn("*************  " + logWarn + "  *************");
    }

    public void writeLogwarn(String logWarn) {
        LOGGER.warn("*************  " + logWarn + "  *************");
    }

    public static void writeLogDebug(String logWarn) {
        LOGGER.debug("*************  " + logWarn + "  *************");
    }

    public void writeLogdebug(String logWarn) {
        LOGGER.debug("*************  " + logWarn + "  *************");
    }

    public static void writeLogError(String logError) {
        LOGGER.error("*************  " + logError + "  *************");
    }

    public void writeLogerror(String logError) {
        LOGGER.error("*************  " + logError + "  *************");
    }

    public static Page Page() {
        page = getPage();
        return page;
    }

    public void highlight(Locator locator) {
        JavascriptExecutor JavascriptExecutor = null;
        final Object o = JavascriptExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", locator);
    }

    public void enterText(Locator locator, String value) {
        locator.highlight();
        locator.fill(value);
    }

}
